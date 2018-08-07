package Utils;
import org.testng.IReporter;
import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public abstract class ExtentManager implements IReporter{
	private static ExtentReports extent;
	public static ExtentTest parent;

	private ExtentManager() {
		throw new IllegalStateException("This is Utility class. Please do not instantiate, rather call static functions directly");
	}

	public static ExtentReports getExtent(String filePath, String reportName, String platform) {
		extent = new ExtentReports();
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));         
		extent.setSystemInfo("platformName", platform);
		extent.setSystemInfo("Team", "99Acres QA");    
		extent.setAnalysisStrategy(AnalysisStrategy.CLASS);
		extent.attachReporter(getHtmlReporter(filePath, reportName));
		return extent;
	}

	private static ExtentHtmlReporter getHtmlReporter(String filePath, String reportName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);  
		htmlReporter.config().setChartVisibilityOnOpen(false);   
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle("Automation");
		htmlReporter.config().setReportName(reportName);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM); 
		htmlReporter.config().getLevel();                                      
		htmlReporter.config().setCSS("css-string");
		htmlReporter.config().setJS("js-string");     
		return htmlReporter;
	}

	public static ExtentTest createTest(String name, String description){
		parent = extent.createTest(name, description);
		return parent;
	}

}
