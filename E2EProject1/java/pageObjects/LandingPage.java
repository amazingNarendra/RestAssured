package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
	
	WebDriver d;
	private By signIn =By.cssSelector("a[href='https://rahulshettyacademy.com/sign_in/']");
	private By title =By.xpath("//h2[contains(text(),'Featured Courses')]");
	private By navigationBar=By.cssSelector("ul[class='nav navbar-nav navbar-right'] li a");
	private By headder=By.cssSelector("div[class*='video-banner'] h3");
	private By popup=By.xpath("//button[text()='NO THANKS']");
	
	
	public LandingPage(WebDriver d) {
		// TODO Auto-generated constructor stub
		this.d=d;
	}


	public LoginPage Signin() {
		d.findElement(signIn).click();
		LoginPage lp=new LoginPage(d);
		 return lp;
		
	}
	public WebElement getTitle() {
		return d.findElement(title);
	}
	public WebElement getNavBar() {
		return d.findElement(navigationBar);
	}
	public WebElement getHeadder() {
		return d.findElement(headder);
	}
	public WebElement getpopUp() {
		return d.findElement(popup);
	}
	public List<WebElement> getPopUpSize() {
		return d.findElements(popup);
	}

}
