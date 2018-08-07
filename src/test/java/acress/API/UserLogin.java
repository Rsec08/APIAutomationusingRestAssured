package acress.API;

import org.testng.annotations.Test;

import com.acres.datasource.ExcelFileReader;
import com.acres.datasource.LoadProperty;
import com.acres.responsevalidation.VerifyLoginAPIOutput;
import com.aventstack.extentreports.ExtentTest;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import com.codoid.products.exception.FilloException;
import com.jayway.restassured.response.Response;
import Utils.GenerateAuthKey;
import Utils.WebservicesRequest;
import Utils.StaticValues;

public class UserLogin {
	static Response RESPONSEVALUE;
	static ExtentTest parent;
	static ExtentTest testnode;
	protected static Properties CONFIG = LoadProperty.getPropertiesFile("config");
	private static String SHEET_URL = System.getProperty("user.dir")+CONFIG.getProperty("DataFolder")+CONFIG.getProperty("propertydetailpagee_API");
	private static String WEB_URL = CONFIG.getProperty("WebUrl");
	private static String API_FOLDER = CONFIG.getProperty("apiFoler");
	private static String RESPONSE_TYPE = CONFIG.getProperty("responseType");
	private static String SHEET_NAME = "";

	public UserLogin(ExtentTest parent) {
		this.parent = parent;
		this.testnode =testnode;
	}

	@Test
	public void Verify_UserLogin() throws FilloException, InterruptedException, Exception {

		SHEET_NAME = "Login";
		int rowCount = 0;
		try {
		rowCount = ExcelFileReader.getCount(SHEET_NAME, SHEET_URL);		
		ArrayList<String> serialNo = null;
		
			serialNo = ExcelFileReader.getSerialNo("SNO", SHEET_NAME, SHEET_URL);
		
		serialNo.toArray();
		System.out.println("serialNo----" + serialNo);

		for (int i = 0; i < rowCount; i++) {
			String rowNum = (String) serialNo.get(i);
			if (rowNum.contains("#") || rowNum.contains("null")) {
				continue;
			} else {
				int rowNumMain = Integer.valueOf(rowNum);
				testnode = parent.createNode("Test Data  :"+String.valueOf(rowNumMain),"Verifying user Login");
				Map<String, String> inputValue = ExcelFileReader.getDataHashMap(rowNumMain, SHEET_NAME, SHEET_URL);
				inputValue.remove("SNo");

				String authKey = GenerateAuthKey.generateAPIAuthKey();
				String main_url = WEB_URL + API_FOLDER + StaticValues.loginAPINAME + authKey + RESPONSE_TYPE;
				if (main_url.contains(" ")) {
					main_url = main_url.replace(" ", "%20");
				}
				System.out.println("main_url:::::::::::::::::::::::" + main_url);
				RESPONSEVALUE = WebservicesRequest.post(main_url, inputValue);
				if (RESPONSEVALUE.statusCode() == 200) {
					String reponsevalue = RESPONSEVALUE.asString();
					VerifyLoginAPIOutput loginoutput = new VerifyLoginAPIOutput(reponsevalue);
					loginoutput.verify_Response(testnode);
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
