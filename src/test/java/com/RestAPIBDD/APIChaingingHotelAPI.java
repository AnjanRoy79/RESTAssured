package com.RestAPIBDD;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import org.testng.annotations.Test;
import com.RestAPI_POJO.AuthenticationPOJO;
import com.RestAPI_POJO.Booking;
import com.RestAPI_POJO.BookingDates;
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

public class APIChaingingHotelAPI 
{
	
	int bookingid;
	String tokenvalue;
	
	
  @Test(priority=1)
  public void CreateBooking() 
  {
	  System.out.println("First Create new Booking");
	  
	  //create request payload
	  
	  BookingDates date=new BookingDates();
	  date.setCheckin("2025-07-09");
	  date.setCheckout("2025-07-10");
	  
	  Booking booking=new Booking();
	  booking.setFirstname("Anjan");
	  booking.setLastname("Roy");
	  booking.setTotalprice(9999);
	  booking.setDepositpaid(true);
	  booking.setBookingdates(date);
	  booking.setAdditionalneeds("Dinner");
	  
	  
	  
	 Response res= given()
	  .header("Content-Type","application/json")
	  .body(booking)
	  
	  .when().post("https://restful-booker.herokuapp.com/booking");
	  
	 
	 //get the status code
	 System.out.println(res.getStatusCode());
	 
	 //log the response
	 res.then().log().body();
	 
	 //get the booking id
	  bookingid=res.jsonPath().getInt("bookingid");
	 System.out.println("Booking id generated: "+bookingid);
	 
	 
  }
  
  @Test (priority=2)
  public void GetSameBookingDetails()
  {
	  System.out.println("Get the details for New Booking");
	  
	  Response res=given()
	  .when().get("https://restful-booker.herokuapp.com/booking/"+bookingid);
	  
	  
	  //log the response
	  res.then().log().body();
	  
  }
  
  @Test(priority=3)
  public void CreateToken()
  {
	  System.out.println("Get the token value");
	  AuthenticationPOJO auth=new AuthenticationPOJO();
	  auth.setUsername("admin");
	  auth.setPassword("password123");
	  
	  Response res=given()
			  .header("Content-Type","application/json")
			  .body(auth)
	  
			  .when().post("https://restful-booker.herokuapp.com/auth");
	  
	  //get the token and store it into variable
	  tokenvalue=res.jsonPath().getString("token");
	  System.out.println("Token created: "+tokenvalue);
	  
  }
  
  @Test(priority=4)
  public void FullUpdate()
  {
	  System.out.println("Get the details for Full Update");
//create request payload
	  
	  BookingDates date=new BookingDates();
	  date.setCheckin("2025-07-09");
	  date.setCheckout("2025-07-10");
	  
	  Booking booking=new Booking();
	  booking.setFirstname("Raja");
	  booking.setLastname("Roy");
	  booking.setTotalprice(8888);
	  booking.setDepositpaid(true);
	  booking.setBookingdates(date);
	  booking.setAdditionalneeds("lunch");
	  
	  
	  
	 Response res= given()
	  .header("Content-Type","application/json")
	  .header("Accept","application/json")
	  .header("Cookie","token="+tokenvalue)
	  
	  .body(booking)
	  
	  .when().put("https://restful-booker.herokuapp.com/booking/"+bookingid);
	  
	 
	 //get the status code
	 System.out.println(res.getStatusCode());
	 
	 //log the response
	 res.then().log().body();
	 
	 System.out.println("Booking updated with id: "+bookingid);
	 
	 
  }
  
  @Test(priority=5)
  public void PartialUpdate()
  {
	  System.out.println("Get the details for Partial update");
	  //create request payload
		  
	  HashMap<String, Object> partialData = new HashMap<String, Object>();
	    partialData.put("firstname", "James");
	    partialData.put("lastname", "Brown");

	    Response res = given()
	        .header("Content-Type", "application/json")
	        .header("Accept", "application/json")
	        .header("Cookie", "token=" + tokenvalue)
	        .body(partialData)
	        .when()
	        .patch("https://restful-booker.herokuapp.com/booking/" + bookingid);
	    System.out.println(res.getStatusCode());
	    res.then().log().body();
	 
  }
  
  @Test(priority=6)
  public void DeleteRequest()
  {
	  System.out.println("Get the details for Delete Request");
	 
		  
	    Response res = given()
	        .header("Content-Type", "application/json")
	        .header("Accept", "application/json")
	        .header("Cookie", "token=" + tokenvalue)
	       
	        .when()
	        .delete("https://restful-booker.herokuapp.com/booking/" + bookingid);
	    System.out.println(res.getStatusCode());
	    res.then().log().body();
	 
  }
}
