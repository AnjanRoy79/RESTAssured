package com.RestAPINonBDD;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SecondAPITestWithValidation {
  @Test
  public void testGETSingleUserWithValidation() 
  {
	  Response res=RestAssured.get("https://reqres.in/api/users/2");
	  
	  int expstatuscode=200;
	  int actstatuscode=res.getStatusCode();
	  
	  Assert.assertEquals(actstatuscode,expstatuscode,"Status code not matched!");
	  System.out.println("API Status code matched...Test pass!");
	  
	  
	  
	  
	  
  }
}
