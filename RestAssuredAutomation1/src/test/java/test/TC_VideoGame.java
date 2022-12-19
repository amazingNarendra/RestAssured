package test;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class TC_VideoGame {
	
	@Test
	public void  Get_allVideoGames() {
		
		given().
		
		when().
		  get("http://localhost:8080/app/videogames").
		then().
		  statusCode(200).
		  log().all();
	}

}
