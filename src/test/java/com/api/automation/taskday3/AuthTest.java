package com.api.automation.taskday3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

public class AuthTest {
    @Test
    public void testRegisterUser() {

        RestAssured.baseURI = "https://whitesmokehouse.com";

        String requestBody = "{"
                + "\"email\": \"test_destia-VScode@gmail.com\","
                + "\"full_name\": \"Destia Latifah A\","
                + "\"password\": \"@dmin123\","
                + "\"department\": \"Technology\","
                + "\"phone_number\": \"082281609857\""
                + "}";

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/webhook/api/register");

        // Print response
        System.out.println("REGISTER Response:\n" + response.getBody().asString());

        // Assert HTTP 200 or 201 (depends on API behavior)
        int statusCode = response.getStatusCode();
        assertEquals(statusCode == 200 || statusCode == 201, true, "Status should be 200 or 201");
    }

    @Test
    public void testLoginUser() {
        RestAssured.baseURI = "https://whitesmokehouse.com";

        String requestBody = "{\n" +
                "    \"email\": \"test_destia@gmail.com\",\n" +
                "    \"password\": \"@dmin123\"\n" +
                "}";

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/webhook/api/login");

        // Output response for debug
        System.out.println("Login response:");
        System.out.println(response.getBody().asString());

        // Check if login is successful
        int statusCode = response.getStatusCode();
        assertEquals(statusCode == 200 || statusCode == 201, true, "Login should succeed");

        // Optional: Extract token from response
        String token = response.jsonPath().getString("token");
        System.out.println("Extracted token: " + token);
    }
}
