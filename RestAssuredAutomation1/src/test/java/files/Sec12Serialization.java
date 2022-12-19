package files;

import io.restassured.RestAssured;
import pojo.Location;
import pojo.Sec12AddPlace;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;

public class Sec12Serialization {
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
		String res = given().queryParam("key", "qaclick123").body(p)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(res);
	}
	

}
