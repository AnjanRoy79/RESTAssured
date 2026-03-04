package com.RestAPINonBDD;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JsonValidationTest {
	
  @Test (priority=1)
  public void testJsonBody() {
	  
	  Response res = RestAssured
			  .given()
			  .header("x-api-key", "reqres_dce1220dd31f4daf9149d6396d70f5bc")   // ✅ API Key Added
			  .when()
			  .get("https://reqres.in/api/users/2");
	  
	  System.out.println(res.asPrettyString());
	  
	  // validate id should be 2
	  int id = res.jsonPath().getInt("data.id");
	  Assert.assertTrue(id == 2);
	  System.out.println("Id matched!");
	  
	  // validate for email should have @reqres.in
	  String Email = res.jsonPath().getString("data.email");
	  Assert.assertTrue(Email.contains("@reqres.in"), 
			  "Email does not contain the expected domain");
	  System.out.println("Email Matched");
	 
	  // validate firstname
	  String FirstName = res.jsonPath().getString("data.first_name");
	  Assert.assertTrue(FirstName.contains("Janet"), 
			  "Name Doesn't match");
	  System.out.println("Name Matched");
  }
  
  
  @Test (priority=2)
  public void testListOfUsers() {
	  
	  Response res = RestAssured
			  .given()
			  .header("x-api-key", "reqres_dce1220dd31f4daf9149d6396d70f5bc")   // ✅ API Key Added
			  .when()
			  .get("https://reqres.in/api/users?page=2");
	  
	  res.then().log().body();
	  
	  // scenario: "total": 12
	  int value = res.jsonPath().getInt("total");
	  Assert.assertEquals(value, 12);
	  System.out.println("Total value is matched!");
	  
	  // array id should be 8 at 2nd position
	  int id = res.jsonPath().getInt("data[1].id");
	  Assert.assertEquals(id, 8);
	  System.out.println("Id matched!");
	  
	  // list scenario get all ids using list
	  List<Integer> allId = res.jsonPath().getList("data.id");
	  System.out.println("Total ids are: " + allId.size());
	  
	  System.out.println("------All Ids-----");
	  for(Integer i : allId) {
		  System.out.println(i);
	  }
  }
}