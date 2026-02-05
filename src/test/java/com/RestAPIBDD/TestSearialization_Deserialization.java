package com.RestAPIBDD;

import org.testng.annotations.Test;


import com.RestAPI_POJO.AuthenticationPOJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestSearialization_Deserialization {
  @Test
  public void testSearialization() throws JsonProcessingException
  {
	  //POJO====>JSON
	  
	  AuthenticationPOJO auth=new AuthenticationPOJO();
	  auth.setUsername("Priyanka");
	  auth.setPassword("test123");
	  
	  ObjectMapper obj=new ObjectMapper();
	  String jsonData=obj.writerWithDefaultPrettyPrinter().writeValueAsString(auth);
	  System.out.println(jsonData);
  }
  
  @Test
  public void testDeserialization() throws JsonMappingException, JsonProcessingException
  {
	  String jsonData="{\n"
	  		+ "  \"username\" : \"Priyanka\",\n"
	  		+ "  \"password\" : \"test123\"\n"
	  		+ "}";
	  
	  ObjectMapper obj=new ObjectMapper();
	 AuthenticationPOJO auth= obj.readValue(jsonData,AuthenticationPOJO.class);
	  
	  System.out.println(auth.getUsername());
	  
  }
}
