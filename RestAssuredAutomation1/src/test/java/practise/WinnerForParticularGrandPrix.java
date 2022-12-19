package practise;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.util.Scanner;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class WinnerForParticularGrandPrix {
	
	@Test
	public void test1() {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter a grand prix race name");
		String raceGrandPrixName = sc.nextLine();
		
		System.out.println("Enter  year range  blw 1995 and 2022 ");
		System.out.println("Enter staring year ");
		int startYear = sc.nextInt();
		System.out.println("Enter ending year ");
		int endYear = sc.nextInt();
				
		for(int year=startYear;year<endYear;year++) 
		{
		System.out.println(raceGrandPrixName+"  winner details for the year :"+year);
		baseURI="http://ergast.com";
		String res = given()
		.when().get("/api/f1/"+year+"/results/1.json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js =new JsonPath(res);
		int total=js.getInt("MRData.total");
		for(int i=0;i<total;i++) 
		{
			
			String raceName=js.getString("MRData.RaceTable.Races["+i+"].raceName");
			if(raceName.contains(raceGrandPrixName)) 
			{
			String firstName = js.getString("MRData.RaceTable.Races["+i+"].Results[0].Driver.givenName");
			String familyname = js.getString("MRData.RaceTable.Races["+i+"].Results[0].Driver.familyName");
			String driverId=js.getString("MRData.RaceTable.Races["+i+"].Results[0].Driver.driverId");
			String nationality=js.getString("MRData.RaceTable.Races["+i+"].Results[0].Driver.nationality");
			System.out.println("the driver details are");
			System.out.println("first name : "+firstName);
			System.out.println("family name :"+familyname);
			System.out.println("driver Id :"+driverId);
			System.out.println("Nationality :"+nationality);
			System.out.println("---------------------------------------------");
			}
		}
	}
	}
}
