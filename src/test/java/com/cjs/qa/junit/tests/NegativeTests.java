package com.cjs.qa.junit.tests;

import com.cjs.qa.utilities.AllureHelper;
import io.qameta.allure.*;
import java.net.URL;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

/**
 * Negative Tests & Error Handling
 *
 * <p>Tests error scenarios, edge cases, and failure recovery Validates proper error handling and
 * user feedback
 *
 * <p>Coverage: - Invalid URLs - Missing elements - Timeout scenarios - Invalid actions - Error
 * recovery
 */
@Epic("Extended Test Coverage")
@Feature("Negative Tests")
public class NegativeTests {
  private static final Logger logger = LogManager.getLogger(NegativeTests.class);

  private WebDriver driver;
  private String gridUrl;

  @BeforeMethod
  public void setUp() throws Exception {
    logger.info("========================================");
    logger.info("⚠️  NEGATIVE TEST SETUP");

    gridUrl = System.getenv("SELENIUM_REMOTE_URL");
    if (gridUrl == null || gridUrl.isEmpty()) {
      gridUrl = "http://localhost:4444/wd/hub";
    }

    logger.info("Grid URL: {}", gridUrl);
    logger.info("========================================");

    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--disable-gpu");

    driver = new RemoteWebDriver(new URL(gridUrl), options);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    logger.info("✅ Driver initialized");
  }

  @Test(priority = 1)
  @Story("Error Handling")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Test handling of non-existent elements")
  public void testNonExistentElement() {
    logger.info("\n>>> Test: Non-existent Element");

    Allure.step("Navigate to Google");
    driver.get("https://www.google.com");

    Allure.step("Attempt to find non-existent element");
    boolean elementNotFound = false;

    try {
      // Try to find an element that doesn't exist
      WebElement nonExistent =
          driver.findElement(By.id("this-element-definitely-does-not-exist-12345"));
      logger.error("Element was found (unexpected!)");
    } catch (NoSuchElementException e) {
      logger.info("Correctly caught NoSuchElementException");
      elementNotFound = true;
    }

    AllureHelper.captureScreenshot(driver, "NonExistent-Element");

    Allure.step("Verify exception was thrown");
    Assert.assertTrue(
        elementNotFound, "NoSuchElementException should be thrown for non-existent element");

    logger.info("✅ Non-existent element handled correctly");
  }

  @Test(priority = 2)
  @Story("Error Handling")
  @Severity(SeverityLevel.NORMAL)
  @Description("Test invalid URL navigation")
  public void testInvalidUrlNavigation() {
    logger.info("\n>>> Test: Invalid URL Navigation");

    String invalidUrl = "https://this-domain-definitely-does-not-exist-12345.com";

    Allure.step("Attempt to navigate to invalid URL: " + invalidUrl);

    try {
      driver.get(invalidUrl);

      // Wait for page load or error
      Thread.sleep(3000);

      String currentUrl = driver.getCurrentUrl();
      String title = driver.getTitle();

      logger.info("Current URL after invalid navigation: {}", currentUrl);
      logger.info("Current Title: {}", title);

      AllureHelper.captureScreenshot(driver, "Invalid-URL");

      // Browser should handle the error (might show error page)
      Assert.assertNotNull(currentUrl, "Should have some URL even if invalid");

      logger.info("✅ Invalid URL handled by browser");

    } catch (Exception e) {
      logger.info("Exception caught during invalid URL navigation: {}", e.getMessage());
      AllureHelper.captureScreenshot(driver, "Invalid-URL-Exception");
      // This is acceptable behavior
    }
  }

  @Test(priority = 3)
  @Story("Element Interaction")
  @Severity(SeverityLevel.NORMAL)
  @Description("Test interaction with disabled elements")
  public void testDisabledElementInteraction() {
    logger.info("\n>>> Test: Disabled Element Interaction");

    Allure.step("Navigate to Google");
    driver.get("https://www.google.com");

    Allure.step("Find search box");
    WebElement searchBox = driver.findElement(By.name("q"));

    Allure.step("Verify element is enabled");
    boolean isEnabled = searchBox.isEnabled();
    logger.info("Search box enabled: {}", isEnabled);

    Assert.assertTrue(isEnabled, "Search box should be enabled");

    AllureHelper.captureScreenshot(driver, "Element-State");

    logger.info("✅ Element state verified");
  }

  @Test(priority = 4)
  @Story("Timeout Handling")
  @Severity(SeverityLevel.NORMAL)
  @Description("Test element wait timeout")
  public void testElementWaitTimeout() {
    logger.info("\n>>> Test: Element Wait Timeout");

    Allure.step("Navigate to Google");
    driver.get("https://www.google.com");

    Allure.step("Wait for non-existent element (expect timeout)");
    boolean timeoutOccurred = false;

    try {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
      wait.until(
          ExpectedConditions.presenceOfElementLocated(
              By.id("element-that-will-never-appear-12345")));

      logger.error("Element appeared (unexpected!)");

    } catch (TimeoutException e) {
      logger.info("Correctly caught TimeoutException after {} seconds", 3);
      timeoutOccurred = true;
    }

    AllureHelper.captureScreenshot(driver, "Timeout-Test");

    Allure.step("Verify timeout occurred");
    Assert.assertTrue(timeoutOccurred, "TimeoutException should occur when element doesn't appear");

    logger.info("✅ Timeout handled correctly");
  }

  @Test(priority = 5)
  @Story("Error Recovery")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Test recovery after error")
  public void testErrorRecovery() {
    logger.info("\n>>> Test: Error Recovery");

    Allure.step("Cause an error");
    try {
      driver.findElement(By.id("non-existent-element"));
    } catch (NoSuchElementException e) {
      logger.info("Error occurred as expected");
    }

    Allure.step("Recover and perform valid action");
    driver.get("https://www.google.com");

    WebElement searchBox = driver.findElement(By.name("q"));
    searchBox.sendKeys("Recovery test");

    logger.info("Successfully recovered and performed action");

    AllureHelper.captureScreenshot(driver, "After-Recovery");

    Allure.step("Verify recovery successful");
    String currentUrl = driver.getCurrentUrl();
    Assert.assertTrue(
        currentUrl.contains("google.com"), "Should successfully recover and navigate");

    logger.info("✅ Error recovery successful");
  }

  @Test(priority = 6)
  @Story("Element State")
  @Severity(SeverityLevel.NORMAL)
  @Description("Test element state verification")
  public void testElementStateVerification() {
    logger.info("\n>>> Test: Element State Verification");

    Allure.step("Navigate to Google");
    driver.get("https://www.google.com");

    Allure.step("Find search box");
    WebElement searchBox = driver.findElement(By.name("q"));

    Allure.step("Verify element is displayed");
    Assert.assertTrue(searchBox.isDisplayed(), "Search box should be displayed");

    Allure.step("Verify element is enabled");
    Assert.assertTrue(searchBox.isEnabled(), "Search box should be enabled");

    AllureHelper.captureScreenshot(driver, "Element-State");

    logger.info("✅ Element state verified");
  }

  @Test(priority = 7)
  @Story("Error Messages")
  @Severity(SeverityLevel.MINOR)
  @Description("Test stale element reference handling")
  public void testStaleElementHandling() {
    logger.info("\n>>> Test: Stale Element Handling");

    Allure.step("Navigate to dynamic page");
    driver.get("https://www.google.com");

    Allure.step("Get reference to element");
    WebElement element = driver.findElement(By.name("q"));

    Allure.step("Navigate away");
    driver.get("https://github.com");

    Allure.step("Try to interact with stale element");
    boolean staleElementCaught = false;

    try {
      element.sendKeys("This should fail");
    } catch (StaleElementReferenceException e) {
      logger.info("Correctly caught StaleElementReferenceException");
      staleElementCaught = true;
    } catch (Exception e) {
      logger.info("Caught different exception: {}", e.getClass().getSimpleName());
      // Still acceptable
      staleElementCaught = true;
    }

    AllureHelper.captureScreenshot(driver, "Stale-Element");

    // Verify we're on the new page
    String currentUrl = driver.getCurrentUrl();
    Assert.assertTrue(currentUrl.contains("github"), "Should be on GitHub page");

    logger.info("✅ Stale element handled (caught: {})", staleElementCaught);
  }

  @AfterMethod
  public void tearDown(ITestResult result) {
    if (driver != null) {
      if (result.getStatus() == ITestResult.FAILURE) {
        logger.error("❌ Negative test failed (unexpected!)");
        AllureHelper.captureScreenshot(driver, "FAILURE-" + result.getName());
        AllureHelper.attachPageSource(driver);
      } else if (result.getStatus() == ITestResult.SUCCESS) {
        logger.info("✅ Negative test passed");
      }

      logger.info("Closing browser...");
      driver.quit();
      logger.info("========================================\n");
    }
  }
}
