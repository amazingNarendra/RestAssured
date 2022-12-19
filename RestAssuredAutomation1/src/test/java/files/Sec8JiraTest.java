package files;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.io.File;

public class Sec8JiraTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="http://localhost:8080";
	   	
//		login to jira
		SessionFilter s=new SessionFilter();
		String res=given().relaxedHTTPSValidation().log().all().header("Content-Type","application/json").
		   body("{ \"username\": \"15691a0399\", \"password\": \"Narendra399@\" }")
	    .filter(s).when().post("/rest/auth/1/session")
	    .then().log().all().extract().response().asString();
	
//	    creating issue
		given().log().all().pathParam("key", "10002").headers("Content-Type","application/json").body("{\r\n" + 
				"    \"body\": \"updateing the comments again\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}")
		.when().filter(s).post("/rest/api/2/issue/{key}/comment")
		.then().log().all().assertThat().statusCode(201);

		
//		attaching file
		given().header("X-Atlassian-Token","no-check").pathParam("key", "10002").filter(s).header("Content-Type","multipart/form-data")
		       .multiPart("file",new File("jira.txt"))
	   .when().post("/rest/api/2/issue/{key}/attachments")
	   .then().log().all().assertThat().statusCode(200);
		
//		get issue
		String issueGet=given().pathParam("key", "10002").queryParam("fields", "comment").filter(s).log().all()
		.when().get("/rest/api/2/issue/{key}")
		.then().extract().response().asString();
		System.out.println(issueGet);
	}
	

	
	

}
