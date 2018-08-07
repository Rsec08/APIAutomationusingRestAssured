package com.acres.datasource;

import java.io.RandomAccessFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ExcelFileReader {
	private static final Logger LOGGER = Logger.getLogger(ExcelFileReader.class);
	
	private ExcelFileReader() {
	    throw new IllegalStateException("Utility class");
	  }

	/**
	 * Below method is used to get the data from excel sheet
	 * @param colName
	 * @param condParam
	 * @param sheetName
	 * @param urlSheet
	 * @return
	 * @throws FilloException
	 * @throws InterruptedException
	 */
	public static String getData(String colName, int condParam, String sheetName, String urlSheet)
			throws FilloException,InterruptedException {
		String data = null;		
		Fillo fillo = new Fillo();
		try {
			com.codoid.products.fillo.Connection connection = fillo.getConnection(urlSheet);
			String strQuery = "Select * from " + sheetName + " where SNO=" + condParam + "";
			System.out.println("strQuery----" + strQuery);

			Recordset recordset = connection.executeQuery(strQuery);
			while (recordset.next()) {
				data = recordset.getField(colName);	
				
			}
			recordset.close();
			connection.close();
		} catch (RuntimeException  e) {
			LOGGER.error("Exception thrown by getData method : "+e);
			System.out.println(e);

		}


		return data;

	}
	/**
	 * Below is the method which is used for getting single data value
	 * @param colName
	 * @param condParam
	 * @param sheetName
	 * @param urlSheet
	 * @return
	 * @throws FilloException
	 * @throws InterruptedException
	 */
	public static String getsingleData(String colName, int condParam, String sheetName, String urlSheet)
			throws FilloException {
		String data = null;
		Fillo fillo = new Fillo();
		try {
			com.codoid.products.fillo.Connection connection = fillo.getConnection(urlSheet);
			String strQuery = "Select * from " + sheetName + " where SNO=" + condParam + "";
			System.out.println("strQuery----" + strQuery);

			Recordset recordset = connection.executeQuery(strQuery);
			while (recordset.next()) {
				data = recordset.getField(colName);				
				
			}
			recordset.close();
			connection.close();
		} catch (RuntimeException  e) {			
			LOGGER.error("Exception thorugh by getsingleDataMethod", e);
		
			}
		return data;
		}

		

	
	/**
	 * Below method is used to update value in excel sheet
	 * @param updateParameter
	 * @param conditionalParameter
	 * @param colName
	 * @param sheetName
	 * @param urlSheet
	 * @throws FilloException
	 * @throws InterruptedException
	 */
	public static void updateSheet(String updateParameter, int conditionalParameter, String colName, String sheetName,
			String urlSheet) throws FilloException, InterruptedException {

		try {
			Fillo fillo = new Fillo();
			com.codoid.products.fillo.Connection connection = fillo.getConnection(urlSheet);
			String strQuery = "Update " + sheetName + " Set " + colName + "=" + "'" + updateParameter + "' where SNO="
					+ conditionalParameter + "";		
			connection.executeUpdate(strQuery);
			connection.close();			
		} catch (RuntimeException  e) {
			LOGGER.error("Exception thorugh by updateSheet", e);

		}

	}
	/**
	 * Below method is used to check the file open and close status
	 * @param File
	 * @param FilePath
	 * @return
	 * @throws InterruptedException
	 */
	@SuppressWarnings({ "resource", "unused" })
	private static boolean isFileClosed(String File, String FilePath) throws InterruptedException {
		boolean closed;
		Object channel = null;
		try {
			channel = new RandomAccessFile(File, FilePath).getChannel();
			closed = true;
		} catch (Exception ex) {
			closed = false;
		} finally {
			if (channel != null) {
				channel.wait(1000);
			}
		}
		return closed;
	}
	

	public static Map<String, String> getDataHashMap(int rowNum,
			String sheetName, String dataSheet) throws FilloException {
		Fillo fillo = new Fillo();
		System.out.println(dataSheet);
		HashMap<String, String> dataMap = new HashMap<String, String>();
		try {
			com.codoid.products.fillo.Connection connection = fillo.getConnection(dataSheet);
			String strQuery = "Select * from " + sheetName + " where SNO="
					+ rowNum;
			System.out.println("strQuery----" + strQuery);
			Recordset recordset = connection.executeQuery(strQuery);
			while (recordset.next()) {
				ArrayList<String> fieldsName = recordset.getFieldNames();

				for (int j = 0; j < fieldsName.size(); j++) {
					String colName = fieldsName.get(j);
					String colValue = recordset.getField(colName);
					dataMap.put(colName, colValue);
				}
			}
			recordset.close();
			connection.close();
		} catch (RuntimeException  e) {
			
			LOGGER.error("Exception thorugh by getDataHashMap", e);
		}

		return dataMap;
	}

	public static int getCount(String sheetName, String urlSheet)
			throws FilloException {

		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(urlSheet);
		String strQuery = "Select * from " + sheetName + "";
		Recordset recordset = connection.executeQuery(strQuery);
		int count = recordset.getCount();

		recordset.close();
		connection.close();
		return count;

	}
	public static ArrayList<String> getSerialNo(String colName, String sheetName,
			String urlSheet) throws FilloException {
		String data = null;
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(urlSheet);
		String strQuery = "Select * from " + sheetName + "";
		System.out.println("strQuery----" + strQuery);
		ArrayList<String> arrSerialNoData = new ArrayList<String>();
		Recordset recordset = connection.executeQuery(strQuery);
		while (recordset.next()) {
			data = recordset.getField(colName);
			arrSerialNoData.add(data);
			System.out.println("sheet data output::::::::" + data);
		}
		recordset.close();
		connection.close();
		return arrSerialNoData;

	}
	public static ArrayList<String> getScript(String colName, String sheetName,
			String urlSheet,String suiteName,String colSuite,String colExecutable,String flag) throws FilloException {

		String data = null;
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(urlSheet);
		ArrayList<String> arrSerialNoData = new ArrayList<String>();
		Recordset recordset = connection.executeQuery("Select * from "+sheetName).where(colSuite+" ='"+suiteName+"'").where(colExecutable+" ='"+flag+"'");
		while (recordset.next()) {
			data = recordset.getField(colName);
			arrSerialNoData.add(data);
			System.out.println("sheet data output::::::::" + data);
		}
		recordset.close();
		connection.close();
		return arrSerialNoData;

	}
}
