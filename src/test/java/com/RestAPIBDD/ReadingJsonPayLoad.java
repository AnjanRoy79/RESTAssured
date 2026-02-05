package com.RestAPIBDD;

import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.RestAPI_POJO.AuthenticationPOJO;
import io.restassured.response.Response;

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

public class ReadingJsonPayLoad
{
	/*
	 * {
    "username" : "admin",
    "password" : "password123"
}
	 */
  @Test(priority=1)
  public void postCallUsingHashMap() {
	  
	  System.out.println("Post call using HashMap");
	  
	  //create payload
	  HashMap<String,Object> data=new HashMap<String,Object>();
	  data.put("username","admin");
	  data.put("password","password123");
	  
	  
	  Response res=given()
			  	.header("Content-Type","application/json")
			  	.body(data)
	  
			  	.when().post("https://restful-booker.herokuapp.com/auth");
	  
	  System.out.println(res.getStatusCode());
	  
	  //log the reponse
	  res.then().log().body();
	  
	  //get the token from response :jsonPath()
	  String tokenValue=res.jsonPath().getString("token");
	  System.out.println("Token is: "+tokenValue);
	  
	    
  }
  
  @Test(priority=2)
  public void testPostWithPOJO()
  {
	  System.out.println("Post call using POJO");
	  //create payload
	 AuthenticationPOJO data=new AuthenticationPOJO();
	 data.setUsername("admin");
	 data.setPassword("password123");
	  
	  
	  Response res=given()
			  	.header("Content-Type","application/json")
			  	.body(data)
	  
			  	.when().post("https://restful-booker.herokuapp.com/auth");
	  
	  System.out.println(res.getStatusCode());
	  
	  //log the reponse
	  res.then().log().body();
	  
	  //get the token from response :jsonPath()
	  String tokenValue=res.jsonPath().getString("token");
	  System.out.println("Token is: "+tokenValue);
	  
	  //username should be admin
	 String name=data.getUsername();
	 Assert.assertEquals(name,"admin");
	 System.out.println("Name matched!");
  
  }
  
  @Test(priority=3)
  public void postCallUsingJSONObjectClass()
  {
	  System.out.println("Post call using JSONObjectClass");
	  //create payload

	  JSONObject obj=new JSONObject();
	  obj.put("username", "admin");
	  obj.put("password","password123");
	  
	  
	  
	  Response res=given()
			  	.header("Content-Type","application/json")
			  	.body(obj.toString())//proper json format
	  
			  	.when().post("https://restful-booker.herokuapp.com/auth");
	  
	  System.out.println(res.getStatusCode());
	  
	  //log the reponse
	  res.then().log().body();
	  
	  //get the token from response :jsonPath()
	  String tokenValue=res.jsonPath().getString("token");
	  System.out.println("Token is: "+tokenValue);
  }
  
  @Test(priority=4)
  public void postCallUsingJSONFile() throws FileNotFoundException
  {
	  System.out.println("Post call using jsonFile");
	  //create payload

	  //file path
	  File f1=new File(System.getProperty("user.dir")+"//JsonFiles//AuthFile.json");
	  
	  //red the file content
	  FileReader fr=new FileReader(f1);
	  
	  //file reader data convert it into token:JSONTokener class
	   JSONTokener token=new JSONTokener(fr);
	  
	  //then token we can read as json object
	  JSONObject obj=new JSONObject(token);
	  
	    	  
	  Response res=given()
			  	.header("Content-Type","application/json")
			  	.body(obj.toString())// convert to proper json format using obj.string
	  
			  	.when().post("https://restful-booker.herokuapp.com/auth");
	  
	  System.out.println(res.getStatusCode());
	  
	  //log the reponse
	  res.then().log().body();
	  
	  //get the token from response :jsonPath()
	  String tokenValue=res.jsonPath().getString("token");
	  System.out.println("Token is: "+tokenValue);
	  
	  
	  String tokendata=res.jsonPath().getString("token");
	  System.out.println(tokendata);
  }
  
}
