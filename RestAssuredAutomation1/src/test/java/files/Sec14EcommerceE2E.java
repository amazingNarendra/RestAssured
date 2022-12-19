package files;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojo.Sec14LoginReq;
import pojo.Sec14LoginRes;

import static io.restassured.RestAssured.*;

import java.io.File;

public class Sec14EcommerceE2E {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		
		Sec14LoginReq lReq=new Sec14LoginReq();  //pojo serialization
		lReq.setUserEmail("postmanra@gmail.com");
		lReq.setUserPassword("Nare1234@");
		
		//login application
		RequestSpecification logReq = given().log().all().spec(reqSpec).body(lReq);  //given
		
		Sec14LoginRes logRes = logReq.when().post("/api/ecom/auth/login").then().log().all().extract().response()  //
				.as(Sec14LoginRes.class);
		String token = logRes.getToken();
		String userId = logRes.getUserId();
		System.out.println(logRes.getToken());
		System.out.println(logRes.getUserId());
		
		System.out.println("***********************ADD PRODUCT*****************************");
		//add product
		RequestSpecification addProdBaseReqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("authorization", token).build();
		
		RequestSpecification reqAddProd = given().log().all().spec(addProdBaseReqSpec).param("productName", "laptop")
		.param("productAddedBy", userId).param("productCategory", "fashion").param("productSubCategory", "shirts")
		.param("productPrice","1150").param("productDescription", "Addias Originals").param("productFor", "women")
		.multiPart("productimage", new File("C:\\Users\\nagae\\Postman\\files\\frescoplay score.png"));
		
		String ResAddProd = reqAddProd.when().post("/api/ecom/product/add-product").then().log().all().extract().response().asString();
		JsonPath js = new JsonPath(ResAddProd);
		String productId = js.get("productId");
		
		//getting error for add products stopped other
		

	}

}
