package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	WebDriver d;
	private By email =By.id("user_email");
	private By password =By.id("user_password");
	private By login =By.name("commit");
	
	
	public LoginPage(WebDriver d) {
		// TODO Auto-generated constructor stub
		this.d=d;
	}


	public WebElement getEmail() {
		return d.findElement(email);
	}
	public WebElement getPassword() {
		return d.findElement(password);
	}
	public WebElement getLogin() {
		return d.findElement(login);
	}

}
