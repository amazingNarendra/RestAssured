package files;
import static io.restassured.RestAssured.*;

import java.util.List;

import javax.sql.rowset.WebRowSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.Api;
import pojo.GetCourse;

public class Sec10OauthTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
//		get exchange code from web ui
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\nagae\\eclipse-workspace\\SeleniumJavaFramework\\Drivers\\chomeDriver\\chromedriver.exe");
//		WebDriver d = new ChromeDriver();
//		d.get("https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&service=lso&o2v=2&flowName=GeneralOAuthFlow");
//		d.findElement(By.cssSelector("div[data-email='15691a0399@gmail.com']"));
//		Thread.sleep(4000);
//		String url = d.getCurrentUrl();     //GOOGLE DISABLED AUTOMATION OF API
		String url="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AfgeXvvbfyJIW0Dn6JzI2n_2CxSvqdV3mzModlZ0v8RSIh19Uri5jiGRTOfTNuvCj18oyA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		String pcode = url.split("code=")[1];
		String code = pcode.split("&scope")[0];
				

		// getting access token
 		String accessToken = given().urlEncodingEnabled(false).log().all().queryParams("code",code) //adding urlcode
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js = new JsonPath(accessToken);
		String access = js.getString("access_token");
		
		
//		directly access token
//		String res=given().log().all().queryParam("access_token", access)  //adding access token
//		.when().get("https://rahulshettyacademy.com/getCourse.php").asString();
//		
//		System.out.println(res);

		
		//    using pojo class
		GetCourse gs=given().queryParam("access_token", access).expect().defaultParser(Parser.JSON)  //adding access token
				.when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
		
		System.out.println(gs.getLinkedIn());
		
		System.out.println(gs.getCourses().getApi().get(1).getCourseTitle());
		
		List<Api> getApiC = gs.getCourses().getApi();
		for(int i=0;i<getApiC.size();i++) {
			if(getApiC.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
				System.out.println(getApiC.get(i).getPrice());
		}
	
	
	
	}

}
