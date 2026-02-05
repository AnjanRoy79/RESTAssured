package com.TelecomProject;

import com.TelecomProject_POJO.*;
import io.restassured.http.ContentType;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserApiTest extends BaseTest {
	private String contactId; // for update/get/delete

    @Test(priority = 1)
    public void AddNewUser() {
        emailId = "anjan" + System.currentTimeMillis() + "@gmail.com";
        UserRequest user = new UserRequest("Anjan", "Roy", emailId, "test@123");

        RegisterResponse response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract().as(RegisterResponse.class);

        Assert.assertNotNull(response.getToken(), "Token should not be null");
        Assert.assertEquals(response.getUser().getEmail(), emailId);

        token = response.getToken();
        System.out.println("Generated Token: " + token);
        
    }

    @Test(priority = 2, dependsOnMethods = "AddNewUser")
    public void GetUserProfile() {
        User response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/users/me")
                .then()
                .statusCode(200)
                .extract().as(User.class);

        Assert.assertEquals(response.getEmail(), emailId);
        System.out.println("User Profile: " + response.getFirstName() + " " + response.getLastName());
    }

    @Test(priority = 3, dependsOnMethods = "AddNewUser")
    public void UpdateUser() {
        String updatedEmail = "anjanr" + System.currentTimeMillis() + "@gmail.com";
        UserRequest updatedUser = new UserRequest("AnjanR", "RoyDa", updatedEmail, "test1234");

        User response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(updatedUser)
                .when()
                .patch("/users/me")
                .then()
                .statusCode(200)
                .extract().as(User.class);

        Assert.assertEquals(response.getFirstName(), "AnjanR");
        Assert.assertEquals(response.getLastName(), "RoyDa");
        Assert.assertEquals(response.getEmail(), updatedEmail);

        System.out.println("Updated Profile: " + response.getFirstName() + " " + response.getLastName());
    }
    
 // Test Case 4: Login User
    @Test(priority = 4)
    public void LoginUser() {
        LoginRequest login = new LoginRequest("anjanroy@gmail.com", "test@12345");

        RegisterResponse response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(login)
                .when()
                .post("/users/login")
                .then()
                .statusCode(200)
                .extract().as(RegisterResponse.class);

        Assert.assertNotNull(response.getToken(), "Login token should not be null");
        token = response.getToken();
        System.out.println("Login Token: " + token);
    }
    
 // Test Case 5: Add Contact
    @Test(priority = 5, dependsOnMethods = "LoginUser")
    public void AddContact() {
        ContactRequest contact = new ContactRequest(
                "John", "Doe", "1970-01-01", "jdoe@gmail.com", "8005555555",
                "1 Main St.", "Apartment A", "Anytown", "KS", "12345", "USA"
        );

        ContactResponse response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(contact)
                .when()
                .post("/contacts")
                .then()
                .statusCode(201)
                .extract().as(ContactResponse.class);

        Assert.assertEquals(response.getFirstName(), "John");
        Assert.assertEquals(response.getLastName(), "Doe");

        contactId = response.get_id();
        System.out.println("Contact created with ID: " + contactId);
    }
    // Test Case 6: Get Contact List
    @Test(priority = 6, dependsOnMethods = "AddContact")
    public void GetContactList() {
        ContactResponse[] response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/contacts")
                .then()
                .statusCode(200)
                .extract().as(ContactResponse[].class);

        Assert.assertTrue(response.length > 0, "Contact list should not be empty");

        System.out.println("Total contacts: " + response.length);
        System.out.println("===== Contact List =====");
        for (ContactResponse contact : response) {
            System.out.println("ID: " + contact.get_id());
            System.out.println("Name: " + contact.getFirstName() + " " + contact.getLastName());
            System.out.println("Email: " + contact.getEmail());
            System.out.println("Phone: " + contact.getPhone());
            System.out.println("City: " + contact.getCity());
            System.out.println("Country: " + contact.getCountry());
            System.out.println("------------------------------");
        }
    }

    
    // Test Case 7: Get Contact by ID
    @Test(priority = 7, dependsOnMethods = "AddContact")
    public void testGetContact() {
        ContactResponse response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/contacts/" + contactId)
                .then()
                .statusCode(200)
                .extract().as(ContactResponse.class);

        Assert.assertEquals(response.get_id(), contactId);
        System.out.println("Fetched Contact: " + response.getFirstName() + " " + response.getLastName());
    }
    
    // Test Case 8: Update Contact (PUT)
    @Test(priority = 8, dependsOnMethods = "AddContact")
    public void UpdateContact() {
        ContactRequest updatedContact = new ContactRequest(
                "Amy", "Miller", "1992-02-02", "amiller@yahoo.com", "9005554242",
                "13 School St.", "Apt. 5", "Washington", "QC", "A1A1A1", "Canada"
        );

        ContactResponse response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(updatedContact)
                .when()
                .put("/contacts/" + contactId)
                .then()
                .statusCode(200)
                .extract().as(ContactResponse.class);

        Assert.assertEquals(response.getEmail(), "amiller@yahoo.com");
        System.out.println("Updated Contact Email: " + response.getEmail());
    }
    
    // Test Case 9: Update Contact (PATCH)
    @Test(priority = 9, dependsOnMethods = "AddContact")
    public void PatchContact() {
        String patchPayload = "{ \"firstName\": \"Anna\" }";

        ContactResponse response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(patchPayload)
                .when()
                .patch("/contacts/" + contactId)
                .then()
                .statusCode(200)
                .extract().as(ContactResponse.class);

        Assert.assertEquals(response.getFirstName(), "Anna");
        System.out.println("Patched Contact First Name: " + response.getFirstName());
    }
    
    // Test Case 10: Logout
    @Test(priority = 10, dependsOnMethods = "LoginUser")
    public void LogoutUser() {
        RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .when()
                .post("/users/logout")
                .then()
                .statusCode(200);

        System.out.println("User logged out successfully.");
    }
    
}
