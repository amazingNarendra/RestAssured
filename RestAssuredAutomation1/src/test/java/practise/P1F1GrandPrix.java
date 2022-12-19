package practise;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class P1F1GrandPrix {
	
	@Test
	public void test1() {
		baseURI="http://ergast.com";
		String res = given()
		.when().get("/api/f1/2008/results/1.json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js =new JsonPath(res);
		
		for(int i=0;i<10;i++) 
		{
		String firstName = js.getString("MRData.RaceTable.Races["+i+"].Results[0].Driver.givenName");
		String familyname = js.getString("MRData.RaceTable.Races["+i+"].Results[0].Driver.familyName");
		String driverId=js.getString("MRData.RaceTable.Races["+i+"].Results[0].Driver.driverId");
		String permanentNumber=js.getString("MRData.RaceTable.Races["+i+"].Results[0].Driver.permanentNumber");
		String raceName=js.getString("MRData.RaceTable.Races["+i+"].raceName");
		System.out.println("the race name is "+raceName);
		System.out.println("the driver details are");
		System.out.println("first name : "+firstName);
		System.out.println("family name :"+familyname);
		System.out.println("driver Id"+driverId);
		System.out.println("Permanent Number :"+permanentNumber);
		System.out.println("---------------------------------------------");
		}
		
	}

}
