package test;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SaopResquest {
	
	@Test
	public void test() throws IOException {
		
		File f=new File("./SoapRequest/body.xml");
		if(f.exists()) {
			System.out.println("File present");
		}
		FileInputStream fis=new FileInputStream(f);
		String req=IOUtils.toString(fis, "UTF-8");
		
		baseURI="http://www.dneonline.com";
		
		given().
		  contentType("text/xmk").
		  accept(ContentType.XML).
		  body(req).
		when().post("/calculator.asmx").
		then().statusCode(415).log().all().
		and().
		body("//*:AddResult.text()",equalTo("8")).
		and().assertThat().body(matchesXsdInClasspath("Calculator.xsd"));
		
	}

}
