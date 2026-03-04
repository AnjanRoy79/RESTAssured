package com.RestAPINonBDD;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SecondAPITestWithValidation {
  @Test
  public void testGETSingleUserWithValidation() 
  {
	  Response res=RestAssured.get("https://reqres.in/api/users/2");
	  RequestSpecification request = RestAssured.given();
	  request.header("x-api-key", "reqres_dce1220dd31f4daf9149d6396d70f5bc");
	  
	  int expstatuscode=403;
	  int actstatuscode=res.getStatusCode();
	  
	  Assert.assertEquals(actstatuscode,expstatuscode,"Status code not matched!");
	  System.out.println("API Status code matched...Test pass!");
	  
	  
	  
	  
	  
  }
}
