package com.RestAPIBDD;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestPath_QueryParameters {
  @Test
  public void f() 
  {	  
	  //https://api.restful-api.dev/objects?id=3&id=5&id=10
	  
	  RestAssured.baseURI="https://api.restful-api.dev";
	 Response res= given()
	  .pathParam("path","objects")
	  .queryParam("id",3)
	  .queryParam("id",5)
	  .queryParam("id",10)
	  
	  //.when().get("https://api.restful-api.dev/objects?id=3&id=5&id=10");
	  //.when().get("https://api.restful-api.dev/{path}");
	  .when().get("/{path}");
	  
	 System.out.println("Status code is: "+res.getStatusCode());
	 
	 //log the payload
	 res.then().log().body();
	  
	  
  }
}
