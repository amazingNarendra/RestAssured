package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Sec07DynamicJson {

	@Test(dataProvider = "Bookdata")
	public void addBook(String a,String b) {
	
		RestAssured.baseURI="http://216.10.245.166";
		String res=given().log().all().header("Content-Type","application/json").body(Payloads.addBook(a,b))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		
//		System.out.println(res);
		
		JsonPath js= new JsonPath(res);
		String id = js.get("ID");
		System.out.println(id);
	}
	
	@DataProvider(name = "Bookdata")
	public Object[][] getData() {
		return new Object[][] {{"abd","127"},{"dex","456"}};
	}
}
