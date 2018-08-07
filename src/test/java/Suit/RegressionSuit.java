package Suit;



import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.codoid.products.exception.FilloException;

import Utils.ExtentManager;
import acress.API.UserLogin;


public class RegressionSuit {

	public ExtentReports extent;
	public ExtentTest test;
	public ExtentTest parent;

	@BeforeSuite
	public void beforeSuit() {
		extent = ExtentManager.getExtent("Reports/ExecutionReportAPI.html", "API", "Mobile_APP");

	}

	@Test
	public void Verify_Login_API() throws FilloException, InterruptedException, Exception {
		parent = extent.createTest("Verifyinh user Login", "User Login");
		UserLogin login = new UserLogin(parent);
		login.Verify_UserLogin();

	}

	/*@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.isSuccess()) {
			test.log(Status.PASS, result.getName());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getName());
			test.log(Status.ERROR, result.getName());
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getName());
		}

	}*/

	@AfterSuite
	public void tearDown() {
		extent.flush();
	}

	
}
