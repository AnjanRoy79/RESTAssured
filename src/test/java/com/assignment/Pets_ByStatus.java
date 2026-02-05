package com.assignment;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Pets_ByStatus {
  @Test
  public void Status() {
	//send the request
	  Response res=RestAssured.get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");
	  System.out.println("Response code is: "+res.getStatusCode()); // getting response status code
	  System.out.println("REsponse status line: "+res.getStatusLine()); // getting response status message
	  System.out.println("REsponse Time in ms: "+res.getTimeIn(TimeUnit.MILLISECONDS));
	 	  
	  //System.out.println("Response in JSON");
	  System.out.println(res.asPrettyString()); // getting response body/JSON as JSON string format
  }
}
