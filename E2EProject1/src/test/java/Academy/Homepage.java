package Academy;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.Base;

public class Homepage extends Base {
	public WebDriver d;
	private static Logger log=LogManager.getLogger(Homepage.class.getName());
	
		
		
	

	@Test(dataProvider="getData")
	public void PageNavigation(String email,String password,String text) throws IOException {
		d=initializeDatadriven();  //Base class r
		d.get(prop.getProperty("url"));
		LandingPage l= new LandingPage(d);
		LoginPage lp=l.Signin();		
		lp.getEmail().sendKeys(email);
		lp.getPassword().sendKeys(password);
		System.out.println(text);
		lp.getLogin().click();
		log.info("success");
		d.close();
	}
	
	
	@DataProvider
	public Object[][] getData() {
		Object[][] data=new Object[2][3];
		
		data[0][0]="non@mail.com";
		data[0][1]="1234";
		data[0][2]="non@mail.com";
			
		data[1][0]="access@mail.com";
		data[1][1]="5678";
		data[1][2]="access";
		
		return data;
		
		
	}
	

}
