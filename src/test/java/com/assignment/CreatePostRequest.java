package com.assignment;


import static io.restassured.RestAssured.given;
import java.util.HashMap;
import org.testng.annotations.Test;
import com.Data_POJO.Data;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreatePostRequest {

    @Test
    public void createObjectWithHashMapAndPojo() {

        // Create POJO instance for "data"
        Data data = new Data();
        data.setYear(2019);
        data.setPrice(1849.99);
        data.setCPUmodel("Intel Core i9");
        data.setHarddisksize("1 TB");

        // Create  HashMap
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Apple MacBook Pro 16");
        requestBody.put("data", data); // POJO used here

        // Send POST request
        Response response = given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post("https://api.restful-api.dev/objects");

        // Print response
        response.prettyPrint();

        // Validation
        response.then().statusCode(200);
    }
}
