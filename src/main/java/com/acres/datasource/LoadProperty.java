package com.acres.datasource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class LoadProperty {

	private static final Logger LOGGER = Logger.getLogger(LoadProperty.class);

	private LoadProperty() {
		throw new IllegalStateException("This is Utility class. Please do not instantiate, rather call static functions directly");
	}

	public static Properties getPropertiesFile(String fileName) {
		Properties props = new Properties();
		try {
			String path = System.getProperty("user.dir")+"/";
			props.load(new FileInputStream(path.concat(fileName).concat(".properties")));
		} catch (IOException e) {
			LOGGER.error("Exception occurred while loading " + fileName + " properties file " + e);
		}
		return props;
	}

	public static Properties getGenericPropertiesFile(String fileName) {
		Properties props = new Properties();
		try {
			props.load(LoadProperty.class.getResourceAsStream("/".concat(fileName).concat(".properties")));
		} catch (IOException e) {
			LOGGER.error("Exception occurred while loading generic " + fileName + " properties file " + e);
		}
		return props;
	}
}
