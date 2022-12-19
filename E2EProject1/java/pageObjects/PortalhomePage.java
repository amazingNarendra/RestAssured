package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PortalhomePage {
	WebDriver d;
	public By invalidEmail=By.xpath("/html[1]/body[1]/section[1]/div[1]/div[1]");
	
	
	public PortalhomePage(WebDriver d) {
		this.d=d;
		
	}
	
	public WebElement invalidEmail() {
		return d.findElement(invalidEmail);
	}

}
