package com.api.automation.base;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BaseTest {

    protected static String token;

    static {
        // Auto-fetch token once for all tests
        RestAssured.baseURI = "https://whitesmokehouse.com";

        String loginPayload = "{\n" +
                "    \"email\": \"test_destia@gmail.com\",\n" +
                "    \"password\": \"@dmin123\"\n" +
                "}";

        Response response = RestAssured
            .given()
                .contentType("application/json")
                .body(loginPayload)
            .when()
                .post("/webhook/api/login");

        if (response.statusCode() == 200) {
            token = response.jsonPath().getString("token");
            System.out.println("🔑 Token fetched: " + token);
        } else {
            throw new RuntimeException("❌ Failed to login: " + response.getStatusLine());
        }
    }
}
