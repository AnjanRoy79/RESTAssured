package com.RestAPINonBDD;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FirstAPITest {

    @Test
    public void testGETWithAuth() {

        // Base URI (ONLY domain)
        RestAssured.baseURI = "https://reqres.in";

        // Create request specification
        RequestSpecification request = RestAssured.given();

        // Add API Key header
        request.header("x-api-key", "reqres_dce1220dd31f4daf9149d6396d70f5bc");

        //  Endpoint
        Response res = request.get("/api/users/2");

        // Print response
        System.out.println("Status Code: " + res.getStatusCode());
        System.out.println("Status Line: " + res.getStatusLine());
        System.out.println("Response Time: " +
                res.getTimeIn(TimeUnit.MILLISECONDS));

        System.out.println(res.asPrettyString());
    }
}