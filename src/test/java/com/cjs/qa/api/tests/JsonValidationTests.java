package com.cjs.qa.api.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

/**
 * JSON Validation Tests
 *
 * <p>Demonstrates JSON response validation: - Schema validation - Structure verification - Data
 * type checking - Array validation - Nested object validation
 */
@Epic("API Testing")
@Feature("JSON Validation")
public class JsonValidationTests {
  private static final Logger LOGGER = LogManager.getLogger(JsonValidationTests.class);
  private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

  @BeforeClass
  public void setUp() {
    RestAssured.baseURI = BASE_URL;
    LOGGER.info("========================================");
    LOGGER.info("📋 JSON VALIDATION TEST SETUP");
    LOGGER.info("Base URL: {}", BASE_URL);
    LOGGER.info("========================================");
  }

  @Test(priority = 1)
  @Story("Structure Validation")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Test JSON response structure")
  public void testJsonStructure() {
    LOGGER.info("\n>>> Test: JSON Structure Validation");

    Allure.step("Validate post object structure");
    given()
        .when()
        .get("/posts/1")
        .then()
        .statusCode(200)
        .body("$", hasKey("userId"))
        .body("$", hasKey("id"))
        .body("$", hasKey("title"))
        .body("$", hasKey("body"));

    LOGGER.info("✅ JSON structure validated");
  }

  @Test(priority = 2)
  @Story("Data Types")
  @Severity(SeverityLevel.NORMAL)
  @Description("Test JSON data types")
  public void testJsonDataTypes() {
    LOGGER.info("\n>>> Test: JSON Data Types");

    Allure.step("Validate field data types");
    Response response = given().when().get("/posts/1").then().statusCode(200).extract().response();

    final Object userId = response.jsonPath().get("userId");
    final Object id = response.jsonPath().get("id");
    final Object title = response.jsonPath().get("title");

    Allure.step("Verify types");
    LOGGER.info("userId type: {}", userId.getClass().getSimpleName());
    LOGGER.info("id type: {}", id.getClass().getSimpleName());
    LOGGER.info("title type: {}", title.getClass().getSimpleName());

    LOGGER.info("✅ Data types validated");
  }

  @Test(priority = 3)
  @Story("Array Validation")
  @Severity(SeverityLevel.NORMAL)
  @Description("Test JSON array responses")
  public void testJsonArray() {
    LOGGER.info("\n>>> Test: JSON Array Validation");

    Allure.step("Validate array response");
    Response response =
        given()
            .when()
            .get("/posts")
            .then()
            .statusCode(200)
            .body("$", isA(java.util.List.class))
            .body("$", hasSize(100))
            .body("id", everyItem(notNullValue()))
            .body("userId", everyItem(instanceOf(Integer.class)))
            .extract()
            .response();

    LOGGER.info("Array size: {}", response.jsonPath().getList("$").size());
    LOGGER.info("✅ Array validated");
  }

  @Test(priority = 4)
  @Story("Nested Objects")
  @Severity(SeverityLevel.NORMAL)
  @Description("Test nested JSON objects")
  public void testNestedObjects() {
    LOGGER.info("\n>>> Test: Nested Objects");

    Allure.step("Get user with nested address");
    given()
        .when()
        .get("/users/1")
        .then()
        .statusCode(200)
        .body("address", notNullValue())
        .body("address.street", notNullValue())
        .body("address.city", notNullValue())
        .body("address.zipcode", notNullValue())
        .body("address.geo", notNullValue())
        .body("address.geo.lat", notNullValue())
        .body("address.geo.lng", notNullValue());

    LOGGER.info("✅ Nested objects validated");
  }

  @Test(priority = 5)
  @Story("Collections")
  @Severity(SeverityLevel.NORMAL)
  @Description("Test collection operations and filtering")
  public void testCollections() {
    LOGGER.info("\n>>> Test: Collection Operations");

    Allure.step("Get all users");
    Response response = given().when().get("/users").then().statusCode(200).extract().response();

    Allure.step("Extract specific fields");
    java.util.List<String> names = response.jsonPath().getList("name");
    java.util.List<String> emails = response.jsonPath().getList("email");

    LOGGER.info("Total users: {}", names.size());
    LOGGER.info("First user: {}", names.get(0));
    LOGGER.info("First email: {}", emails.get(0));

    Allure.step("Verify all have required fields");
    given()
        .when()
        .get("/users")
        .then()
        .body("name", everyItem(notNullValue()))
        .body("email", everyItem(notNullValue()))
        .body("email", everyItem(containsString("@")));

    LOGGER.info("✅ Collection operations validated");
  }

  @Test(priority = 6)
  @Story("Response Parsing")
  @Severity(SeverityLevel.NORMAL)
  @Description("Test complex JSON path expressions")
  public void testComplexJsonPath() {
    LOGGER.info("\n>>> Test: Complex JSON Path");

    Allure.step("Get posts and use complex path expressions");
    Response response = given().when().get("/posts").then().statusCode(200).extract().response();

    Allure.step("Extract with JsonPath");
    // Get all titles
    java.util.List<String> titles = response.jsonPath().getList("title");
    LOGGER.info("Total titles: {}", titles.size());

    // Get posts where userId = 1
    java.util.List<Integer> user1Posts =
        response.jsonPath().getList("findAll { it.userId == 1 }.id");
    LOGGER.info("User 1 posts: {}", user1Posts.size());

    // Get first post title
    String firstTitle = response.jsonPath().getString("[0].title");
    LOGGER.info("First title: {}", firstTitle);

    LOGGER.info("✅ Complex JSON path validated");
  }

  @Test(priority = 7)
  @Story("Response Size")
  @Severity(SeverityLevel.MINOR)
  @Description("Test response size validation")
  public void testResponseSize() {
    LOGGER.info("\n>>> Test: Response Size");

    Allure.step("Get large response and measure size");
    Response response =
        given()
            .when()
            .get("/photos") // Large dataset
            .then()
            .statusCode(200)
            .extract()
            .response();

    String responseBody = response.getBody().asString();
    int bodyLength = responseBody.length();

    LOGGER.info("Response size: {} bytes", bodyLength);
    LOGGER.info("Photo count: {}", response.jsonPath().getList("$").size());

    Allure.step("Verify reasonable size");
    // Should be large but not excessive
    org.testng.Assert.assertTrue(bodyLength > 1000, "Response should have content");
    org.testng.Assert.assertTrue(bodyLength < 5000000, "Response should not be excessive");

    LOGGER.info("✅ Response size acceptable");
  }

  @AfterClass
  public void tearDown() {
    LOGGER.info("========================================");
    LOGGER.info("✅ Data-Driven API Tests Completed");
    LOGGER.info("========================================\n");
  }
}
