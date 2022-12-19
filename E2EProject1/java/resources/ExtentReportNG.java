package resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
		static ExtentReports extent;
		
		public static ExtentReports getReportObject() {
			String path=System.getProperty("user.dir")+"\\reports\\index.html";
			ExtentSparkReporter reporter= new ExtentSparkReporter(path);
		    reporter.config().setReportName("Web Automation Results");
			reporter.config().setDocumentTitle("Test Results");  
			
			extent= new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("Tester", "Narendra");
			return extent;
		}

		
}
