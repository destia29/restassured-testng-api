package com.api.automation.e2e;

import com.api.automation.pojo.EmployeeRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UpdateEmployeeTest {
    private static final String BASE_URL = "https://whitesmokehouse.com";
    @Test
    public void updateEmployee() {
        // 1. Prepare updated data
        EmployeeRequest updateData = new EmployeeRequest();
        String id = "527"; // Assuming this is the employee ID to update
        updateData.setEmail("baruedit_EDITBARU@mail.com");
        updateData.setPassword("newSecurePassword123!");
        updateData.setFull_name("Destia Latifah A EDIT");
        updateData.setDepartment("Technology");
        updateData.setTitle("Senior EDIT QA Engineer");
        

        // 2. Get auth token from login
        String authToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjUyNiIsImlhdCI6MTc0ODIyMTk2OH0.Hy-a8Mis30-hEV9kSbFmgG1ZfYkqNEsFi_HkhV6hzf4";

        // 3. Send PUT request
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + authToken)
                .contentType("application/json")
                .body(updateData)
                .put(BASE_URL + "/webhook/employee/update/");

        // 4. Validate response
        System.out.println("Update Response:\n" + response.asPrettyString());
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("[0].email"), equalTo(updateData.getEmail()));
        assertThat(response.jsonPath().getString("[0].full_name"), equalTo(updateData.getFull_name()));
        assertThat(response.jsonPath().getString("[0].department"), equalTo(updateData.getDepartment()));
        assertThat(response.jsonPath().getString("[0].title"), equalTo(updateData.getTitle()));
    }
}