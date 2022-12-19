package test;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;
 
public class GetDemo {

	@Test
	public void test_1() {
		Response res = get("https://reqres.in/api/users?page=2");
		System.out.println(res.getStatusCode());
		System.out.println(res.getTime());
		System.out.println(res.getStatusLine());
		System.out.println(res.getHeader("content-type"));
		System.out.println(res.getBody().asString());
	}
//	@Test
	public void test_2() {
		baseURI="https://reqres.in/api";
		given().
		get("/users?page=2").
		then().
		statusCode(200).
		body("data[1].email", equalTo("lindsay.ferguson@reqres.in")).
		body("data.last_name",hasItems("Ferguson","Funke")).
		log().all();
	}
//	@Test
	public void post() {
		Map<String, Object> m= new HashMap<String, Object>();
//		m.put("name", "Naru");
//		m.put("job","IT");
//		System.out.println(m);
		
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
		  post("/users").
		then().
		  statusCode(201).
		  log().all();
		
	}
}

