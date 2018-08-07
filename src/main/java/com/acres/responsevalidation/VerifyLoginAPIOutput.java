package com.acres.responsevalidation;
import com.acres.responsepojoclasses.Login_Output;
import com.aventstack.extentreports.ExtentTest;
import com.google.gson.Gson;

import Utils.commonMethods;
public class VerifyLoginAPIOutput {
	Gson gson;
	String response;
	public VerifyLoginAPIOutput(String response) {
		this.response = response;
		gson = new Gson();
	}

	public void verify_Response(ExtentTest test) {
		System.out.println();
		Login_Output value = gson.fromJson(response, Login_Output.class);
		
		commonMethods method = new commonMethods();
		commonMethods.verifyText("Rahul sharma",value.getUser().getName(),"Verifying UserName after Login", test);
		

	}

}
