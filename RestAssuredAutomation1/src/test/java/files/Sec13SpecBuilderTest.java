package files;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import pojo.Sec12AddPlace;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

public class Sec13SpecBuilderTest {
	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		Sec12AddPlace p=new Sec12AddPlace();
		p.setAccuracy(50);
		p.setName("Frontline house");
		p.setAddress("29, side layout, cohen 09");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		p.setLanguage("French-IN");
		
		ArrayList<String> a=new ArrayList<String>();
		a.add("shoe park");
		a.add("shop");
		p.setTypes(a);
				
		Location l= new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		RequestSpecification specReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
				
		RequestSpecification req = given().spec(specReq).body(p);//given
				
		String res = req.when().post("/maps/api/place/add/json") //when
		.then().spec(resSpec).extract().response().asString();   //then
		
	
		System.out.println(res);
	}
	

}
