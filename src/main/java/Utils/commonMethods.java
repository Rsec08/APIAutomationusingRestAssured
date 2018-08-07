package Utils;


import java.util.Random;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;



public class commonMethods {

	private static Random rnd = new Random();

	public static String fg_getLimitedString(int j) {
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		for (int i = 0; i < j; i++) {
			char c = (char) (r.nextInt(26) + 'a');
			sb.append(c);
		}
		return sb.toString();

	}
	public static String getRandomNumber(int digCount) {
		String finalNumber = null;
		StringBuilder sb = new StringBuilder(digCount);
		for (int i = 0; i < digCount; i++)
			sb.append((char) ('0' + rnd.nextInt(10)));
		finalNumber = sb.toString();
		String firstDigit = finalNumber.substring(0, 1);
		if (firstDigit.matches("0")) {
			finalNumber = sb.toString().replaceFirst(".{1}", "5");

		}
		return finalNumber;
	}

	public static void verifyText(String object, String object2, String verificationName, ExtentTest test)
	 {
	test.log(Status.INFO, "Going to Verify Actual and Expected Results");
		try {
			Assert.assertEquals(object, object2);
			printPassLog(test, verificationName + ":PASSED", object, object2);

		} catch (Error e) {
		printFailureLog(test, verificationName + ": FAILED", e.getLocalizedMessage(), object, object2);

		}
	}

	private static void printPassLog(ExtentTest test, String Msg, String object, String object2) {
		if (object != "NA" && object2 != "NA") {
			test.log(Status.INFO, "Actual Output: " + object);
			test.log(Status.INFO, "Expected Output: " + object2);
		}
		test.pass("<font size = 3 color=\"#3ADF00\">" + Msg + "</font>");

	}

	private static void printFailureLog(ExtentTest test, String Msg, String e, Object object, Object object2) {
		if (object != "NA" && object2 != "NA") {
			test.log(Status.INFO, "Actual Output: " + object);
			test.log(Status.INFO, "Expected Output: " + object2);
		}
		test.fail("\"<font size = 3 color=\\\"#FE2E2E\\\">" + Msg
				+ "</font>\" + \"  \"+ \"<font size = 2 color=\\\"#FA5858\\\"><i>\" +e +\"</i></font>\"");
	
	}
}
