package acress.API;

import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import org.testng.annotations.Test;

import com.acres.datasource.ExcelFileReader;
import com.acres.datasource.LoadProperty;
import com.acres.responsevalidation.VerifyLoginAPIOutput;
import com.aventstack.extentreports.ExtentReports;
import com.codoid.products.exception.FilloException;
import com.jayway.restassured.response.Response;

import Utils.GenerateAuthKey;
import Utils.StaticValues;
import Utils.WebservicesRequest;
import Utils.commonMethods;


public class UserRegistration {
	static Response RESPONSEVALUE;
	ExtentReports extent;
	
	protected static Properties CONFIG = LoadProperty.getPropertiesFile("config");
	private static String SHEET_URL = System.getProperty("user.dir") + CONFIG.getProperty("DataFolder")
			+ CONFIG.getProperty("Registration_API");
	private static String WEB_URL = CONFIG.getProperty("WebUrl");
	private static String API_FOLDER = CONFIG.getProperty("apiFoler");
	private static String RESPONSE_TYPE = CONFIG.getProperty("responseType");
	private static String SHEET_NAME = "";
	static commonMethods comm;

	public UserRegistration(ExtentReports extent) {
		this.extent = extent;
	
		
	}

	@Test
	public static void verify_UserResgistration() throws FilloException, InterruptedException, Exception {

		SHEET_NAME = "Registration";
		int rowCount = 0;
		try {
		rowCount = ExcelFileReader.getCount(SHEET_NAME, SHEET_URL);	
		ArrayList<String> serialNo = null;
		serialNo = ExcelFileReader.getSerialNo("SNO", SHEET_NAME, SHEET_URL);		
		serialNo.toArray();
		for (int i = 0; i < rowCount; i++) {
			String rowNum = (String) serialNo.get(i);
			if (rowNum.contains("#") || rowNum.contains("null")) {
				continue;
			} else {
				int rowNumMain = Integer.valueOf(rowNum);
				Map<String, String> inputValue = ExcelFileReader.getDataHashMap(rowNumMain, SHEET_NAME, SHEET_URL);
				String length = inputValue.get("Length");
				String specialChar = inputValue.get("Special char");
				int val = Integer.valueOf(length);
			    commonMethods.getRandomNumber(val);
				
				inputValue.remove("SNo");

				String authKey = GenerateAuthKey.generateAPIAuthKey();
				String main_url = WEB_URL + API_FOLDER + StaticValues.RegisterUserAPIName + authKey + RESPONSE_TYPE;
				if (main_url.contains(" ")) {
					main_url = main_url.replace(" ", "%20");
				}
				System.out.println("main_url:::::::::::::::::::::::" + main_url);
				RESPONSEVALUE = WebservicesRequest.post(main_url, inputValue);
				if (RESPONSEVALUE.statusCode() == 200) {
					String reponsevalue = RESPONSEVALUE.asString();
					VerifyLoginAPIOutput loginoutput = new VerifyLoginAPIOutput(reponsevalue);
					loginoutput.verify_Response(null);
				} else {
					System.out.println(RESPONSEVALUE.statusCode());
				}
			}
		}
	}catch(Exception ex) {
		
		System.out.println("Exception thorugh my Login API class......"+ex);
		
		
		}
	}
}
