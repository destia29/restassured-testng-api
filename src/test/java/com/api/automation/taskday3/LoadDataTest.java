package com.api.automation.taskday3;
import com.api.automation.base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test; 
import static org.testng.Assert.assertEquals;

public class LoadDataTest extends BaseTest {
    @Test
    public void testGetDepartment() {
        RestAssured.baseURI = "https://whitesmokehouse.com";

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/webhook/api/department");

        // Debug output
        System.out.println("Get All Department Response:\n" + response.getBody().asPrettyString());

        // Assert status code
        assertEquals(response.getStatusCode(), 200, "Expected status code 200");
    }
    
}
