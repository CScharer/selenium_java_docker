package com.cjs.qa.api.tests;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * CRUD API Tests
 * 
 * Demonstrates full CRUD operations:
 * - CREATE (POST)
 * - READ (GET)
 * - UPDATE (PUT/PATCH)
 * - DELETE
 * 
 * Using JSONPlaceholder fake API
 */
@Epic("API Testing")
@Feature("CRUD Operations")
public class CrudApiTests {
    private static final Logger logger = LogManager.getLogger(CrudApiTests.class);
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        logger.info("========================================");
        logger.info("🔧 CRUD API TEST SETUP");
        logger.info("Base URL: {}", BASE_URL);
        logger.info("========================================");
    }
    
    @Test(priority = 1)
    @Story("CREATE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test POST request to create new resource")
    public void testCreatePost() {
        logger.info("\n>>> Test: POST - Create Resource");
        
        String requestBody = "{\n" +
            "  \"title\": \"Test Post\",\n" +
            "  \"body\": \"This is a test post created by automation\",\n" +
            "  \"userId\": 1\n" +
            "}";
        
        Allure.step("Send POST request with JSON body");
        Response response = 
            given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .log().all()
            .when()
                .post("/posts")
            .then()
                .log().all()
                .statusCode(201)  // Created
                .body("title", equalTo("Test Post"))
                .body("userId", equalTo(1))
                .body("id", notNullValue())
                .extract().response();
        
        int createdId = response.jsonPath().getInt("id");
        logger.info("Created resource with ID: {}", createdId);
        logger.info("✅ POST request successful");
    }
    
    @Test(priority = 2)
    @Story("READ")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test GET request to retrieve specific resource")
    public void testReadPost() {
        logger.info("\n>>> Test: GET - Read Resource");
        
        Allure.step("Send GET request for specific post");
        Response response = 
            given()
            .when()
                .get("/posts/1")
            .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", equalTo(1))
                .extract().response();
        
        String title = response.jsonPath().getString("title");
        logger.info("Post title: {}", title);
        logger.info("✅ GET request successful");
    }
    
    @Test(priority = 3)
    @Story("UPDATE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test PUT request to update resource")
    public void testUpdatePost() {
        logger.info("\n>>> Test: PUT - Update Resource");
        
        String updateBody = "{\n" +
            "  \"id\": 1,\n" +
            "  \"title\": \"Updated Title\",\n" +
            "  \"body\": \"Updated body content\",\n" +
            "  \"userId\": 1\n" +
            "}";
        
        Allure.step("Send PUT request to update post");
        given()
            .header("Content-Type", "application/json")
            .body(updateBody)
            .log().all()
        .when()
            .put("/posts/1")
        .then()
            .log().all()
            .statusCode(200)
            .body("title", equalTo("Updated Title"))
            .body("id", equalTo(1));
        
        logger.info("✅ PUT request successful");
    }
    
    @Test(priority = 4)
    @Story("UPDATE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test PATCH request for partial update")
    public void testPatchPost() {
        logger.info("\n>>> Test: PATCH - Partial Update");
        
        String patchBody = "{\n" +
            "  \"title\": \"Patched Title\"\n" +
            "}";
        
        Allure.step("Send PATCH request to partially update post");
        given()
            .header("Content-Type", "application/json")
            .body(patchBody)
            .log().all()
        .when()
            .patch("/posts/1")
        .then()
            .log().all()
            .statusCode(200)
            .body("title", equalTo("Patched Title"))
            .body("id", equalTo(1));
        
        logger.info("✅ PATCH request successful");
    }
    
    @Test(priority = 5)
    @Story("DELETE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test DELETE request to remove resource")
    public void testDeletePost() {
        logger.info("\n>>> Test: DELETE - Remove Resource");
        
        Allure.step("Send DELETE request");
        given()
            .log().all()
        .when()
            .delete("/posts/1")
        .then()
            .log().all()
            .statusCode(200);
        
        logger.info("✅ DELETE request successful");
    }
    
    @Test(priority = 6)
    @Story("READ")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test GET nested resources")
    public void testNestedResources() {
        logger.info("\n>>> Test: GET Nested Resources");
        
        Allure.step("Get comments for specific post");
        Response response = 
            given()
            .when()
                .get("/posts/1/comments")
            .then()
                .statusCode(200)
                .body("$", hasSize(greaterThan(0)))
                .body("[0].postId", equalTo(1))
                .body("[0].email", notNullValue())
                .extract().response();
        
        int commentCount = response.jsonPath().getList("$").size();
        logger.info("Comments found: {}", commentCount);
        logger.info("✅ Nested resources retrieved");
    }
    
    @Test(priority = 7)
    @Story("Filtering")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test filtering with query parameters")
    public void testFiltering() {
        logger.info("\n>>> Test: Filtering Resources");
        
        Allure.step("Filter posts by userId");
        given()
            .queryParam("userId", 1)
        .when()
            .get("/posts")
        .then()
            .statusCode(200)
            .body("$", hasSize(10))  // User 1 has 10 posts
            .body("userId", everyItem(equalTo(1)));
        
        logger.info("✅ Filtering working correctly");
    }
    
    @AfterClass
    public void tearDown() {
        logger.info("========================================");
        logger.info("✅ Basic API Tests Completed");
        logger.info("========================================\n");
    }
}

