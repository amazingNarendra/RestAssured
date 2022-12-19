package test;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class RunOnLocal {
	
//	@Test
	public void get() {
		baseURI="http://localhost:3000";
		given().get("/subjects").then().statusCode(200).log().all();
		
	}
//	@Test
	public void post() {
		baseURI="http://localhost:3000";
		JSONObject req= new JSONObject();
		req.put("name", "azure");
		
		given().contentType(ContentType.JSON).accept(ContentType.JSON).body(req.toJSONString()).
		when().post("/subjects").
		then().statusCode(201);
		
	}
//	@Test
	public void put() {
		baseURI="http://localhost:3000";
		JSONObject req= new JSONObject();
		req.put("name", "dbdeveloper");
		
		given().contentType(ContentType.JSON).accept(ContentType.JSON).body(req.toJSONString()).
		when().put("/subjects/4").
		then().statusCode(200);
		
	}
	@Test
	public void delete() {
		baseURI="http://localhost:3000";
				
		given().
		when().delete("/subjects/4").
		then().statusCode(404);
		
	}



}
