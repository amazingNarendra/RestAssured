package seleniumGrid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class GridChrome {
	@Test
	public void HomePageCheck() throws MalformedURLException {
		
		DesiredCapabilities caps=new DesiredCapabilities();
		caps.setBrowserName("chrome");
		
		WebDriver d= new RemoteWebDriver(new URL("http://192.168.43.123:4444"),caps);
		d.get("https://www.google.com");
		System.out.println(d.getTitle());
		d.findElement(By.name("q")).sendKeys("rahulsheettyacademy");
		d.close();
	}

}
