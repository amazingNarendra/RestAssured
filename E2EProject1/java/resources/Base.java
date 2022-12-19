package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import com.google.common.io.Files;

public class Base {
	
	public static WebDriver d;
	public Properties prop;
	public WebDriver initializeDatadriven() throws IOException
	{
		prop = new Properties();
		String userDirPath=System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(userDirPath+"\\java\\resources\\data.properties");
		prop.load(fis);
//		String browserName=System.getProperty("browser");
		String browserName=prop.getProperty("browser");
		System.out.println(browserName);
		if(browserName.contains("chrome"))
		{
			//execute chrome
			System.setProperty("webdriver.chrome.driver","C:\\Users\\nagae\\eclipse-workspace\\SeleniumJavaFramework\\Drivers\\chomeDriver\\chromedriver.exe");
			ChromeOptions options= new ChromeOptions();
			if(browserName.contains("headless"))
			{
			options.addArguments("headless");
			}
			//WebDriverManager.chromedriver().setup();
			d = new ChromeDriver(options);
		}
		else if(browserName.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver","C:\\Users\\nagae\\eclipse-workspace\\SeleniumJavaFramework\\Drivers\\geckodriver\\geckodriver-v0.30.0-win64\\geckodriver.exe");
			d = new FirefoxDriver();
		}
	d.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	return d;
	}
	
	public String Screenshot(String testCaseName, WebDriver d) throws IOException {
		
		File source=((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
		String destiFile= System.getProperty("user.dir")+"\\Reports\\"+testCaseName+".png";
		FileHandler.copy(source, new File(destiFile));
		return destiFile;
	}

}