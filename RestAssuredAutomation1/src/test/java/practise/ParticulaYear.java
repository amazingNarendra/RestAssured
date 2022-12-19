package practise;
import static io.restassured.RestAssured.*;

import java.util.Scanner;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
public class ParticulaYear {
	
	@Test
	public void particularYearWinnerDetails() {
		
		Scanner sc= new Scanner(System.in);
		System.out.println("enter an year b/w 1995 and 2022 ");
		
		int year = sc.nextInt();
		System.out.println("winner details for the year :"+year);
		baseURI="http://ergast.com";
		String res = given()
		.when().get("/api/f1/"+year+"/results/1.json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js =new JsonPath(res);
		int total=js.getInt("MRData.total");
		for(int i=0;i<total;i++) 
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
