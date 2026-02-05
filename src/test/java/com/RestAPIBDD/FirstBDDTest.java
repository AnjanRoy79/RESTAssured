package com.RestAPIBDD;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;


/*
 * given(): prerequisite
 * ----------------------
 * header,request path and query parameter,Request Pay load,authorization
 * 
 * when(): REquest type+ end point
 * -------------------------
 * GET,POST,PUT,PATCH,DELETE
 * 
 * then(): validate response
 * ----------------------------
 * status code,message,response time,header,cookies,pay load
 */

public class FirstBDDTest {
  @Test
  public void testSingleRequest()
  {
	  
	  
	  Response res= given()
	 .when().get("https://reqres.in/api/users/2");
	  	 
	 int statusCode=res.getStatusCode();
	 Assert.assertEquals(statusCode,200);
	 System.out.println("Status code matched!");
	 
	 //log the response

	 res.then().log().body();
   }
    
  @Test
  public void getBookingIds()
  {
	  
	  Response res=given()
	  .when().get("https://restful-booker.herokuapp.com/booking");
	  
	  
	  //validate status code

	  Assert.assertEquals(res.getStatusCode(),200);
	  System.out.println("Satus code matched!");
	  
	  
	  //log the response

	  res.then().log().body();
   
  }
   
}
