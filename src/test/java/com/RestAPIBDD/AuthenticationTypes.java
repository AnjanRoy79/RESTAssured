package com.RestAPIBDD;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.response.Response;

/*
 * given(): prerequisite
 * ----------------------
 * header,request path and query paramater,Request Payload,authorization
 * 
 * when(): REquest type+endpoint
 * -------------------------
 * GET,POST,PUT,PATCH,DELETE
 * 
 * then(): validate reponse
 * ----------------------------
 * status code,message,response time,header,cookies,payload
 */

public class AuthenticationTypes {
  @Test
  public void testBasicAuth() 
  {
	  
	  //Basic Auth: Base64 algorithm
	 Response res= given()
			 	.auth().basic("postman","password")
			 		.when().get("https://postman-echo.com/basic-auth");
	 
	 //log the body
	 res.then().log().body();
  }
  
  
  @Test
  public void testDigestAuth() 
  {
	  
	  //Basic Auth: Base64 algorithm
	 Response res= given()
			 .auth().digest("postman","password")
			 		.when().get("https://postman-echo.com/digest-auth");
	 
	 //log the body
	 res.then().log().body();
	 
  }
  
  @Test
  public void testBearerToken()
  {
	  String token="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";
	  Response res=given()
			  		.header("Authorization","Bearer "+token)
			  
			  		.when().get("https://api.github.com/user/repos");
	  
	  //log the body
	  res.then().log().body();
  
  }
  
  @Test
  public void testOauth2()
  {
	  Response res=given()
			  			.auth().oauth2("12345")
	  
			  		.when().get("https://postman-echo.com");
	  
	  res.then().log().body();
  
  }
 
}