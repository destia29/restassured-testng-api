package com.api.automation.e2e;

import com.api.automation.pojo.EmployeeRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.apache.commons.lang3.RandomStringUtils;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class RegisterEmployeeTest {
    private static final String BASE_URL = "https://whitesmokehouse.com";
    private static final String COMPANY_ID = "41a9698d-d8b0-42df-9ddc-89c0a1a1aa79"; // for search purposes
    private static String email;
    private static String password;
    private static String employeeId;
    private static String fullName;
    private static String authToken; // Now storing token for authenticated search

    @BeforeSuite
    public void setupTestData() {
        // Generate random test data
        String randomString = RandomStringUtils.randomAlphabetic(4);

        email = "testuser_" + randomString + "@mail.com";
        password = "securePassword123!";
        fullName = "Destia Test POJO";
    }

    @Test(priority = 1)
    public void registerEmployee() {
        // 1. Prepare POJO
        EmployeeRequest newEmployee = new EmployeeRequest();
        newEmployee.setEmail(email);
        newEmployee.setPassword(password);
        newEmployee.setFull_name(fullName);
        newEmployee.setDepartment("Technology");
        newEmployee.setTitle("QA Engineer");

        // 2. Send POST request
        Response response = RestAssured.given()
                .contentType("application/json")
                .body(newEmployee)
                .post(BASE_URL + "/webhook/employee/add");

        // 3. Validate response
        System.out.println("Registration Response:\n" + response.asPrettyString());
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("[0].email"), equalTo(email));

        // Store data for future tests
        employeeId = response.jsonPath().getString("[0].id"); // Store employee ID
        authToken = response.jsonPath().getString("[0].token"); // Store auth token for login

    }

    @Test(priority = 2, dependsOnMethods = "registerEmployee")
    public void loginEmployee() {
        // 1. Prepare request body
        EmployeeRequest loginRequest = new EmployeeRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);

        // 2. Send POST request
        Response response = RestAssured.given()
                .contentType("application/json")
                .body(loginRequest)
                .post(BASE_URL + "/webhook/employee/login");

        // 3. Validate response
        System.out.println("Login Response:\n" + response.asPrettyString());
        assertThat(response.statusCode(), equalTo(200));

        System.out.println("DEBUG - Full Login Response:\n" + response.asPrettyString()); // Add this line
        System.out.println("DEBUG - All JSON Paths: " + response.jsonPath().get().toString());

        authToken = response.jsonPath().getString("token");

        assertThat(authToken, notNullValue()); // Ensure token is not null
        System.out.println("Login successful. Token acquired.");
    }

    @Test(priority = 3)
    public void searchEmployee() {
        String searchTerm = "Destia";
        String encodedName = URLEncoder.encode(searchTerm, StandardCharsets.UTF_8);
        String authToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjUyNiIsImlhdCI6MTc0ODIyMTk2OH0.Hy-a8Mis30-hEV9kSbFmgG1ZfYkqNEsFi_HkhV6hzf4";

        Response response = RestAssured.given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + authToken) // Using login token
                .get(BASE_URL + "/webhook/" + COMPANY_ID + "/employee/search/" + encodedName);

        System.out.println("Search Results:\n" + response.asPrettyString());

        assertThat(response.statusCode(), equalTo(200));
    }
}