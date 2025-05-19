package com.api.automation.taskday3;

import com.api.automation.base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ObjectTest extends BaseTest {
    @Test
    public void testGetAllObjects() {
        RestAssured.baseURI = "https://whitesmokehouse.com";

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/webhook/api/objects");

        // Debug output
        System.out.println("Get All Objects Response:\n" + response.getBody().asPrettyString());

        // Assert status code
        assertEquals(response.getStatusCode(), 200, "Expected status code 200");
    }

    @Test
    public void testGetObjectById() {
        RestAssured.baseURI = "https://whitesmokehouse.com";

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/webhook/api/objectslistId?id=41");

        // Debug output
        System.out.println("Get Object by ID Response:\n" + response.getBody().asPrettyString());

        // Assert status code
        assertEquals(response.getStatusCode(), 200, "Expected status code 200");
    }

    @Test
    public void testGetSingleObject() {
        RestAssured.baseURI = "https://whitesmokehouse.com";

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/webhook/8749129e-f5f7-4ae6-9b03-93be7252443c/api/objects/156");

        // Debug output
        System.out.println("Get Object by Name Response:\n" + response.getBody().asPrettyString());

        // Assert status code
        assertEquals(response.getStatusCode(), 200, "Expected status code 200");
    }

    @Test
    public void testAddObject() {
        RestAssured.baseURI = "https://whitesmokehouse.com";

        String requestBody = "{\n" +
                "    \"name\": \"Add object Destia via TestNG\",\n" +
                "    \"data\": {\n" +
                "        \"year\": 2025,\n" +
                "        \"price\": 1849.99,\n" +
                "        \"cpu_model\": \"ADD Intel Core i9\",\n" +
                "        \"hard_disk_size\": \"1 TB\",\n" +
                "        \"capacity\": \"2 cpu\",\n" +
                "        \"screen_size\": \"14 Inch\",\n" +
                "        \"color\": \"red\"\n" +
                "    }\n" +
                "}";

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/webhook/api/objects");

        System.out.println("Add Object Response:\n" + response.getBody().asPrettyString());

        // Response validation
        assertEquals(response.getStatusCode(), 200, "Expected status code 200 OK");
    }
    @Test
    public void testUpdateObject() {
        RestAssured.baseURI = "https://whitesmokehouse.com";

        String requestBody = "{\n" +
                "    \"name\": \"Update BARU object Destia via TestNG\",\n" +
                "    \"data\": {\n" +
                "        \"year\": 2025,\n" +
                "        \"price\": 1849.99,\n" +
                "        \"cpu_model\": \"ADD Intel Core i9\",\n" +
                "        \"hard_disk_size\": \"1 TB\",\n" +
                "        \"capacity\": \"2 cpu\",\n" +
                "        \"screen_size\": \"14 Inch\",\n" +
                "        \"color\": \"red\"\n" +
                "    }\n" +
                "}";

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put("/webhook/37777abe-a5ef-4570-a383-c99b5f5f7906/api/objects/156");

        System.out.println("Update Object Response:\n" + response.getBody().asPrettyString());

        // Response validation
        assertEquals(response.getStatusCode(), 200, "Expected status code 200 OK");
    }
    @Test
    public void testDeleteObject() {
        RestAssured.baseURI = "https://whitesmokehouse.com";

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/webhook/d79a30ed-1066-48b6-83f5-556120afc46f/api/objects/157");

        System.out.println("Delete Object Response:\n" + response.getBody().asPrettyString());

        // Response validation
        assertEquals(response.getStatusCode(), 200, "Expected status code 200 OK");
    }
    @Test
    public void testUpdatePartialObject() {
        RestAssured.baseURI = "https://whitesmokehouse.com";

        String requestBody = "{\n" +
                "    \"name\": \"DESTIA Update Partial Object\",\n" +
                "    \"data\": {\n" +
                "        \"year\": 2025,\n" +
                "        \"price\": 1849.99\n" +
                "    }\n" +
                "}";

        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .patch("/webhook/39a0f904-b0f2-4428-80a3-391cea5d7d04/api/object/156");

        System.out.println("Update Partial Object Response:\n" + response.getBody().asPrettyString());

        // Response validation
        assertEquals(response.getStatusCode(), 200, "Expected status code 200 OK");
    }
}
