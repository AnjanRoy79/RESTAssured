package com.assignment;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import java.util.Collections;
import org.testng.annotations.Test;
import com.Pets_POJO.Category;
import com.Pets_POJO.Pet;
import com.Pets_POJO.Tag;


public class PostPet {

	int ID;
    @Test (priority=1)
    public void AddNewPet() {
    	
    	System.out.println("Creating new pet ");
        // Create Pet object
    	
        Pet pet = new Pet();
        pet.setId(111);
        pet.setName("doggie");
        pet.setStatus("available");
        
        // Create Category object
        
        Category category = new Category();
        category.setId(0);
        category.setName("string");
        pet.setCategory(category);

        pet.setPhotoUrls(Collections.singletonList("string"));
        
        // Create Tag object
        
        Tag tags = new Tag();
        tags.setId(0);
        tags.setName("string");
        pet.setTags(Collections.singletonList(tags));

        // POST Request using RestAssured and asserting
        
        Response res = given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType("application/json")
                .accept("application/json")
                .body(pet)
                .when()
                .post("/pet");
        
        		 res.then().statusCode(200)
        		.body("name", equalTo("doggie"))
        		.log().body();
        		 ID=res.jsonPath().getInt("id");
        		 
    }
    
    // getting newly created pet id and display the same.
    
    @Test (priority=2, dependsOnMethods = "AddNewPet")
    public void GetPetID() {
    	
    	System.out.println("Details for New Created ID :"+ "" + ID);
    	Response res= given()
    				
    			.baseUri("https://petstore.swagger.io/v2")
    			.when()
                .get("/pet/" + ID);
    			  
    			  
    			  //log the response
    			  res.then().statusCode(200);
    			  res.then().log().body();
    	
    }
    
 // Update Pet Object with same ID but different name or status
    
    @Test(priority = 3, dependsOnMethods = "GetPetID")
    public void updateExistingPet() {
        
    	System.out.println("Updated Existing Pet: " + "" + ID);
        Pet updatedPet = new Pet();
        updatedPet.setName("doggie");
        updatedPet.setStatus("available");

        // Update Category
        Category category = new Category();
        category.setId(0);
        category.setName("String");
        updatedPet.setCategory(category);

        // Photo URLs
        updatedPet.setPhotoUrls(Collections.singletonList("updated_photo_url"));

        // Tags
        Tag tag = new Tag();
        tag.setId(0);
        tag.setName("String");
        updatedPet.setTags(Collections.singletonList(tag));

        // PUT request to update pet
        Response res = given()
                .baseUri("https://petstore.swagger.io/v2/")
                .contentType("application/json")
                .accept("application/json")
                .body(updatedPet)
        .when()
                .put("/pet");

        // Validate response
        res.then()
            .statusCode(200)
            .body("name", equalTo("doggie"))
            .body("status", equalTo("available"))
            .log().body();
    }
    
    // Deleting the ID
    
    @Test(priority = 4, dependsOnMethods = "AddNewPet")
    public void deletePetById() 
    {
        System.out.println("Deleting Pet with ID: " + ID);
        
        // Delete request to delete pet
        
        Response res = given()
                .baseUri("https://petstore.swagger.io/v2")
                .accept("application/json")
        .when()
                .delete("/pet/" + ID);

        // Validate the deletion response
        res.then()
            .statusCode(200)
            .body("message", equalTo(String.valueOf(ID)))  // API returns the deleted ID as message
            .log().body();
    }

    
    
}

