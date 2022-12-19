package  stepDefinations;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import pageObjects.PortalhomePage;
import resources.Base;

import static org.testng.Assert.assertTrue;

import org.junit.runner.RunWith;

 
@RunWith(Cucumber.class)
public class StepDefination extends Base {
	public LoginPage lp;

	@Given("^Initializing the browser with chrome$")
	public void initializing_the_browser_with_chrome() throws Throwable {
		d=initializeDatadriven();  //Base class r
	    
	}

	@Given("^Navigating \"([^\"]*)\" site$")
	public void navigating_site(String arg1) throws Throwable {
		d.get(arg1);
	  
	}

	@Given("^Click on login link to land on sign in page$")
	public void click_on_login_link_to_land_on_sign_in_page() throws Throwable {
		LandingPage l= new LandingPage(d);
		if(l.getPopUpSize().size()>0) {
			l.getpopUp().click();
		}
		lp=l.Signin();
	}
	
	  @When("^User enters (.+) and (.+) and logs in$")
	    public void user_enters_and_and_logs_in(String user, String password) throws Throwable {
	       
	        lp.getEmail().sendKeys(user);
			lp.getPassword().sendKeys(password);
			System.out.println(user +"  "+password);
			lp.getLogin().click();
	    }

	
	@Then("^Verify the user is successfully login$")
	public void verify_the_user_is_successfully_login() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	  PortalhomePage ph= new PortalhomePage(d)   ;
	  System.out.println(ph.invalidEmail().getText());
	}
	 @And("^close the browsers$")
	    public void close_the_browsers() throws Throwable {
	        d.quit();
	    }

	

}