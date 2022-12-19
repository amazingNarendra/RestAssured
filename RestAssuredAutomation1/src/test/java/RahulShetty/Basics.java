package RahulShetty;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;


import files.Payloads;
public class Basics {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	
//	add place
	RestAssured.baseURI="https://rahulshettyacademy.com";
	String res = given().log().all().
	  queryParam("key", "qaclick123").headers("Content-Type","application/json").
	  body(Files.readAllBytes(Paths.get("C:\\Users\\nagae\\eclipse-workspace\\RestAssuredAutomation1\\src\\test\\java\\files\\Addplace.json")))
	.when().post("/maps/api/place/add/json")
	.then().statusCode(200).body("scope",equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();

	System.out.println(res);
	System.out.println("----------------------------------------------------------------------");
	
	JsonPath js=new JsonPath(res);
	String pid=js.getString("place_id");
	System.out.println(pid);
	
	//update place
	String newAdr="89 Summar Walk";  //new address
	given().log().all().
	  queryParam("key", "qaclick123").headers("Content-Type","application/json").
	  body("{\r\n" + 
	  		"\"place_id\":\""+pid+"\",\r\n" + 
	  		"\"address\":\""+newAdr+"\",\r\n" + 
	  		"\"key\":\"qaclick123\"\r\n" + 
	  		"}")
	  .when().put("/maps/api/place/update/json")
	  .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
	System.out.println("----------------------------------------------------------------------");
	
	//get place
	
	String getres=given().queryParam("key", "qaclick123").queryParam("place_id",pid)
	.when().get("/maps/api/place/get/json")
	.then().log().all().statusCode(200).extract().response().asString();
	
	JsonPath js1=new JsonPath(getres);
	String actualAdr= js1.getString("address");
	System.out.println(actualAdr);
	Assert.assertEquals(actualAdr, newAdr);
	
	
	}

}
