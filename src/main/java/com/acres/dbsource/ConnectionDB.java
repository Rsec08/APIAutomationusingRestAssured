package com.acres.dbsource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class ConnectionDB {

	private static final Logger LOGGER = Logger.getLogger(ConnectionDB.class);

	private ConnectionDB() {
		throw new IllegalStateException("This is Utility class. Please do not instantiate, rather call static functions directly");
	}

	@Deprecated
	public static String getDBData(String strQuery, String colName,String userNameDB,String pwdTestDB,String portNo,String portTestDB) throws SQLException {

		String strData = null;
		Connection con;
		Statement st;
		ResultSet rs;		


		String dbUrl = "jdbc:mysql://" + portTestDB + ":" + portNo;

		try {
			Class.forName("com.mysql.jdbc.Driver");


			System.out.println("OOOOOOOOOOO----====" + dbUrl + "----" + userNameDB + "-----" + pwdTestDB);
			con = DriverManager.getConnection(dbUrl, userNameDB, pwdTestDB);
			st = con.createStatement();
			rs = st.executeQuery(strQuery);			

			while (rs.next()) {
				System.out.println("colName============" + colName);
				strData = rs.getString(colName);
				System.out.println("strData----------: " + strData);

			}
			con.close();
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}

		return strData;
	}

	public static String selectQuery(String query, String url, String userName, String password) {		

		Connection connection = getConnection(url, userName, password);
		Statement statement = null;
		ResultSet result = null;
		String columnValues = "";

		try {
			statement = connection.createStatement();
			result = statement.executeQuery(query);
			ResultSetMetaData rsmd = result.getMetaData();
			int totalColumns = rsmd.getColumnCount();

			if(result.isBeforeFirst()) {
				while(result.next()) {
					for(int values = 1; values <= totalColumns; values++) {
						columnValues = fetchValues(result, columnValues, values);
					}
				}
				int lastColonValue = columnValues.lastIndexOf(";;;");
				columnValues = columnValues.substring(0, lastColonValue).trim();
			}
		} catch (Exception e) {
			LOGGER.error("Exception occurred while executing query " + e);
		} finally {
			closeResources(statement, result, connection);
		}

		return columnValues ;
	}

	private static String fetchValues(ResultSet result, String columnValues, int values) {
		try	{
			columnValues = result.getString(values).concat(";;;").concat(columnValues);
		}
		catch(SQLException e) {
			LOGGER.error("Exception occurred while fetching query result " + e);
		}
		return columnValues;
	}

	private static void closeResources(Statement statement, ResultSet result, Connection connection) {
		try {
			if (statement != null)
				statement.close();
			if (result != null)
				result.close();
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			LOGGER.error("Exception occurred while closing resources " + e);
		}

	}

	private static Connection getConnection(String dbUrl, String userName, String password) {	
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://".concat(dbUrl).concat("/?zeroDateTimeBehavior=convertToNull&autoReconnect=true"), userName, password);
		} 
		catch (Exception e) {
			LOGGER.error("Exception occurred while creating connection " + e);
		}

		return con;
	}

}
