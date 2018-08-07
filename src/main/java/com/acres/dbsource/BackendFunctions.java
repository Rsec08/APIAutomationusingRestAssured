package com.acres.dbsource;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class BackendFunctions {

	private static final Logger LOGGER = Logger.getLogger(BackendFunctions.class);
	private static final String CRON_SYNTAX = "/usr/local/php/bin/php";

	private BackendFunctions() {
		throw new IllegalStateException("This is Utility class. Please do not instantiate, rather call static functions directly");
	}

	public static StringBuffer executecron(String cronName, String puttyUsername, String hostName, int puttyPort, String puttyPassword, String htDocsPath) {
		return (executeCommand(cronName, CRON_SYNTAX, puttyUsername, hostName, puttyPort, puttyPassword, htDocsPath));
	}

	private static StringBuffer executeCommand(String cronName, String syntax, String puttyUsername, String hostName, int puttyPort, String puttyPassword, String htDocsPath) {
		String command = syntax + " " + htDocsPath + cronName;
		Session session = null;
		Channel channel = null;
		InputStream in = null;
		StringBuffer sb = new StringBuffer();
		JSch jsch = new JSch();

		try	{
			session = connectSession(puttyUsername, hostName, puttyPort, puttyPassword, jsch);

			channel = connectChannel(command, session);
			in = channel.getInputStream();
			while (!channel.isClosed()) {
				sb = sb.append(readChannelInputStream(channel, in));
			}
		} catch(Exception e) {
			LOGGER.error("Exception occurred while executing backend commands " + e);
		} finally {
			closeResources(session, channel, in);
		}
		return sb;
	}

	private static Channel connectChannel(String command, Session session) throws JSchException {
		Channel channel;
		channel = session.openChannel("exec");			
		((ChannelExec) channel).setCommand(command);
		channel.setXForwarding(true);
		channel.connect();
		return channel;
	}

	private static Session connectSession(String puttyUsername, String hostName, int puttyPort, String puttyPassword,
			JSch jsch) throws JSchException {
		Session session;
		session = jsch.getSession(puttyUsername, hostName, puttyPort);

		// To handle the UnknownHostKey
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);

		session.setPassword(puttyPassword);
		session.connect();
		return session;
	}

	private static StringBuffer readChannelInputStream(Channel channel, InputStream in) {
		StringBuffer sb = new StringBuffer();
		try {
			byte[] tmp = new byte[10240];
			while(in.available() > 0) {
				int i = in.read(tmp, 0, 10240);
				sb.append(new String(tmp, 0, i));
				if(i < 0) {
					break;
				}
				LOGGER.info(new String(tmp, 0, i));
			}
		} catch (Exception e) {
			LOGGER.error("Exception occurred while reading channel input stream " + e);
		}
		return sb;
	}

	private static void closeResources(Session session, Channel channel, InputStream in) {
		if(channel != null) {
			channel.disconnect();
		}
		if(session != null) {
			session.disconnect();
		}
		try {
			if (in != null) {
				in.close();
			} 
		} catch (IOException e) {
			LOGGER.error("Exception while closing inputStream " + e);
		}
	}

}
