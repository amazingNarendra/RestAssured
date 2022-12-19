package test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PutPatchDelete {
//	@Test
	public void put() {
				
		JSONObject req=new JSONObject();
		req.put("name", "Naru");
		req.put("job","IT");
		System.out.println(req.toJSONString());
		
		baseURI="https://reqres.in/api";
		given().
		  header("Content-Type", "application").
		  contentType(ContentType.JSON).
		  accept(ContentType.JSON).
		  body(req.toJSONString()).
		when().
//		  put("/users/2").
		patch("/users/2").
		then().
		  statusCode(200).
		  log().all();
		
		
	}
	@Test
	public void delete() {
		baseURI="https://reqres.in/api";		
		
		given().
		when().
//		  put("/users/2").
		delete("/users/2").
		then().
		  statusCode(204).
		  log().all();
		
		
	}
}
