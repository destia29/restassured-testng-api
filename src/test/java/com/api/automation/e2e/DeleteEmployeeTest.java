package com.api.automation.e2e;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DeleteEmployeeTest {
    private static final String BASE_URL = "https://whitesmokehouse.com";

    @Test(dependsOnGroups = "employee-registration")
    public void deleteEmployee() {
        // 1. Get shared test data
        String id = "527";
        String authToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjUyNyIsImlhdCI6MTc0ODIyMzQ0MH0.KrruKvshk-_vXg395mYDPA4if2KMh6YG8-ZUh9ixMl0";

        // 2. Send DELETE request
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + authToken)
                .contentType("application/json")
                .delete(BASE_URL + "/webhook/employee/delete/" + id);

        // 3. Debug output
        System.out.println("Delete Response:\n" + response.asPrettyString());

        // 4. Validate response (handling array format)
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getBoolean("[0].success"), is(true));
        
        // 5. Verify employee is removed
        verifyEmployeeDeletion(id, authToken);
    }

    private void verifyEmployeeDeletion(String employeeId, String authToken) {
        Response getResponse = RestAssured.given()
                .header("Authorization", "Bearer " + authToken)
                .get(BASE_URL + "/webhook/employee/");

        // Expecting 404 or specific "not found" response
        assertThat(getResponse.statusCode(), equalTo(404));
    }
}