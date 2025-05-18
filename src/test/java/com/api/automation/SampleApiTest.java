package com.api.automation;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SampleApiTest {
    @Test
    public void getExampleTest() {
        // Base URI
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send GET request
        Response response = RestAssured
                .given()
                .when()
                .get("/posts/1");

        // Print response
        System.out.println("Response Body:");
        System.out.println(response.getBody().asString());

        // Assert status code
        assertEquals(response.getStatusCode(), 200, "Status Code should be 200");
    }
}
