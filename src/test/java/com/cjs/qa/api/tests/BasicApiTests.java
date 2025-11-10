package com.cjs.qa.api.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Basic API Tests using REST Assured
 *
 * <p>Demonstrates fundamental REST API testing: - GET requests - Status code validation - Response
 * time validation - Header verification - Content type checking
 *
 * <p>Using JSONPlaceholder (https://jsonplaceholder.typicode.com/) - Free fake REST API for testing
 */
@Epic("API Testing")
@Feature("Basic REST API Tests")
public class BasicApiTests {
  private static final Logger LOGGER = LogManager.getLogger(BasicApiTests.class);
  private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

  @BeforeClass
  public void setUp() {
    baseURI = BASE_URL;
    LOGGER.info("========================================");
    LOGGER.info("🌐 API TEST SETUP");
    LOGGER.info("Base URL: {}", BASE_URL);
    LOGGER.info("========================================");
  }

  @Test(priority = 1)
  @Story("GET Requests")
  @Severity(SeverityLevel.BLOCKER)
  @Description("Test basic GET request and status code validation")
  public void testGetRequest() {
    LOGGER.info("\n>>> Test: Basic GET Request");

    Allure.step("Send GET request to /posts/1");
    Response response =
        given()
            .log()
            .all()
            .when()
            .get("/posts/1")
            .then()
            .log()
            .all()
            .statusCode(200)
            .extract()
            .response();

    LOGGER.info("Status Code: {}", response.getStatusCode());
    LOGGER.info("Response Time: {}ms", response.getTime());

    Allure.step("Verify response");
    Assert.assertEquals(response.getStatusCode(), 200);

    LOGGER.info("✅ GET request successful");
  }

  @Test(priority = 2)
  @Story("Response Validation")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Test response body validation")
  public void testResponseBody() {
    LOGGER.info("\n>>> Test: Response Body Validation");

    Allure.step("Send GET request and validate body");
    given()
        .log()
        .uri()
        .when()
        .get("/posts/1")
        .then()
        .log()
        .body()
        .statusCode(200)
        .body("userId", equalTo(1))
        .body("id", equalTo(1))
        .body("title", notNullValue())
        .body("body", notNullValue());

    LOGGER.info("✅ Response body validated");
  }

  @Test(priority = 3)
  @Story("Response Validation")
  @Severity(SeverityLevel.NORMAL)
  @Description("Test response headers")
  public void testResponseHeaders() {
    LOGGER.info("\n>>> Test: Response Headers");

    Allure.step("Send GET request and check headers");
    Response response =
        given()
            .when()
            .get("/posts/1")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .header("Content-Type", containsString("application/json"))
            .extract()
            .response();

    LOGGER.info("Content-Type: {}", response.getContentType());
    LOGGER.info("Server: {}", response.getHeader("Server"));

    LOGGER.info("✅ Headers validated");
  }

  @Test(priority = 4)
  @Story("Performance")
  @Severity(SeverityLevel.NORMAL)
  @Description("Test API response time")
  public void testResponseTime() {
    LOGGER.info("\n>>> Test: Response Time");

    Allure.step("Send GET request and measure time");
    given()
        .when()
        .get("/posts/1")
        .then()
        .statusCode(200)
        .time(lessThan(2000L)); // Should respond within 2 seconds

    LOGGER.info("✅ Response time acceptable");
  }

  @Test(priority = 5)
  @Story("GET Requests")
  @Severity(SeverityLevel.NORMAL)
  @Description("Test GET request with query parameters")
  public void testGetWithQueryParams() {
    LOGGER.info("\n>>> Test: GET with Query Parameters");

    Allure.step("Send GET request with userId parameter");
    given()
        .queryParam("userId", 1)
        .log()
        .uri()
        .when()
        .get("/posts")
        .then()
        .log()
        .body()
        .statusCode(200)
        .body("$", hasSize(greaterThan(0))) // Should return array
        .body("[0].userId", equalTo(1)); // First item should have userId=1

    LOGGER.info("✅ Query parameters working");
  }

  @Test(priority = 6)
  @Story("GET Requests")
  @Severity(SeverityLevel.NORMAL)
  @Description("Test GET all resources")
  public void testGetAllPosts() {
    LOGGER.info("\n>>> Test: GET All Posts");

    Allure.step("Send GET request to retrieve all posts");
    Response response =
        given()
            .when()
            .get("/posts")
            .then()
            .statusCode(200)
            .body("$", hasSize(100)) // Should return 100 posts
            .extract()
            .response();

    LOGGER.info("Total posts retrieved: {}", response.jsonPath().getList("$").size());
    LOGGER.info("✅ Retrieved all posts");
  }

  @Test(priority = 7)
  @Story("Error Handling")
  @Severity(SeverityLevel.NORMAL)
  @Description("Test 404 error handling")
  public void testNotFound() {
    LOGGER.info("\n>>> Test: 404 Not Found");

    Allure.step("Send GET request to non-existent resource");
    given().when().get("/posts/999999").then().statusCode(404);

    LOGGER.info("✅ 404 handled correctly");
  }

  @AfterClass
  public void tearDown() {
    LOGGER.info("========================================");
    LOGGER.info("✅ API Tests Completed");
    LOGGER.info("========================================\n");
  }
}
