package com.cjs.qa.api.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Data-Driven API Tests
 *
 * <p>Demonstrates parameterized API testing: - Multiple endpoints - Various status codes -
 * Different HTTP methods - Batch validation
 */
@Epic("API Testing")
@Feature("Data-Driven API Tests")
public class ApiDataDrivenTests {
  private static final Logger LOGGER = LogManager.getLogger(ApiDataDrivenTests.class);
  private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

  @BeforeClass
  public void setUp() {
    baseURI = BASE_URL;
    LOGGER.info("========================================");
    LOGGER.info("📊 DATA-DRIVEN API TEST SETUP");
    LOGGER.info("Base URL: {}", BASE_URL);
    LOGGER.info("========================================");
  }

  @DataProvider(name = "endpoints")
  public Object[][] endpointsProvider() {
    return new Object[][] {
      {"/posts", 100},
      {"/comments", 500},
      {"/albums", 100},
      {"/photos", 5000},
      {"/todos", 200},
      {"/users", 10}
    };
  }

  @Test(dataProvider = "endpoints")
  @Story("Endpoints")
  @Severity(SeverityLevel.NORMAL)
  @Description("Test multiple endpoints return expected data")
  public void testEndpoints(String endpoint, int expectedCount) {
    LOGGER.info("\n>>> Test: Endpoint {}", endpoint);

    Allure.step("GET " + endpoint);
    Response response = given().when().get(endpoint).then().statusCode(200).extract().response();

    int actualCount = response.jsonPath().getList("$").size();
    LOGGER.info("Expected: {}, Actual: {}", expectedCount, actualCount);

    Allure.step("Verify count");
    Assert.assertEquals(
        actualCount, expectedCount, endpoint + " should return " + expectedCount + " items");

    LOGGER.info("✅ {} validated", endpoint);
  }

  @DataProvider(name = "postIds")
  public Object[][] postIdsProvider() {
    return new Object[][] {{1}, {5}, {10}, {25}, {50}, {75}, {100}};
  }

  @Test(dataProvider = "postIds")
  @Story("ID Validation")
  @Severity(SeverityLevel.NORMAL)
  @Description("Test retrieving posts by various IDs")
  public void testPostById(int postId) {
    LOGGER.info("\n>>> Test: Get Post ID {}", postId);

    Allure.step("GET /posts/" + postId);
    given().when().get("/posts/" + postId).then().statusCode(200).body("id", equalTo(postId));

    LOGGER.info("✅ Post {} retrieved", postId);
  }

  @DataProvider(name = "invalidEndpoints")
  public Object[][] invalidEndpointsProvider() {
    return new Object[][] {
      {"/nonexistent", 404},
      {"/posts/999999", 404},
      {"/users/999999", 404}
    };
  }

  @Test(dataProvider = "invalidEndpoints")
  @Story("Error Handling")
  @Severity(SeverityLevel.NORMAL)
  @Description("Test invalid endpoints return correct error codes")
  public void testInvalidEndpoints(String endpoint, int expectedStatus) {
    LOGGER.info("\n>>> Test: Invalid Endpoint {}", endpoint);

    Allure.step("GET " + endpoint + " (expect " + expectedStatus + ")");
    given().when().get(endpoint).then().statusCode(expectedStatus);

    LOGGER.info("✅ Error code {} returned correctly", expectedStatus);
  }

  @DataProvider(name = "contentTypes")
  public Object[][] contentTypesProvider() {
    return new Object[][] {
      {"/posts/1", "application/json"},
      {"/users/1", "application/json"},
      {"/albums/1", "application/json"}
    };
  }

  @Test(dataProvider = "contentTypes")
  @Story("Content Negotiation")
  @Severity(SeverityLevel.MINOR)
  @Description("Test content type headers")
  public void testContentTypes(String endpoint, String expectedType) {
    LOGGER.info("\n>>> Test: Content Type for {}", endpoint);

    Allure.step("Verify Content-Type header");
    Response response =
        given()
            .when()
            .get(endpoint)
            .then()
            .statusCode(200)
            .header("Content-Type", containsString(expectedType))
            .extract()
            .response();

    LOGGER.info("Content-Type: {}", response.getContentType());
    LOGGER.info("✅ Content type validated");
  }

  @AfterClass
  public void tearDown() {
    LOGGER.info("========================================");
    LOGGER.info("✅ Data-Driven API Tests Completed");
    LOGGER.info("========================================\n");
  }
}
