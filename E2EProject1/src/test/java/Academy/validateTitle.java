package Academy;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.Base;

public class validateTitle extends Base {
	public WebDriver d;
	public LandingPage l;
	private static Logger log=LogManager.getLogger(validateTitle.class.getName());
	@BeforeTest
	public void initilize() throws IOException {
		d=initializeDatadriven();
		log.info("initilizing");
		d.get(prop.getProperty("url"));
		log.info("navigating to url");
		System.out.println(prop.getProperty("url"));
	}
	@Test
	public void PageNavigation() throws IOException {
		
		
		l= new LandingPage(d);
		System.out.println(l.getTitle().getText());
	    Assert.assertEquals(l.getTitle().getText(), "FEATURED 1COURSES");
	   boolean flag= l.getNavBar().isDisplayed();
	   Assert.assertTrue(flag);
	   log.info("success");
	}
	@Test
   public void validateHeadder() throws IOException {
		
		
		l= new LandingPage(d);
		System.out.println(l.getHeadder().getText());
	    Assert.assertEquals(l.getHeadder().getText(), "AN ACADEMY TO LEARN EVERYTHING ABOUT TESTING");
	   boolean flag= l.getNavBar().isDisplayed();
	   Assert.assertTrue(flag);
	   log.info("success");
	}
	@AfterTest
	public void teardown() {
		d.close();
	}
}