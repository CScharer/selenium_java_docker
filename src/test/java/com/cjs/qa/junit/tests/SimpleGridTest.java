package com.cjs.qa.junit.tests;

import com.cjs.qa.utilities.AllureHelper;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.URL;

/**
 * Simple TestNG test to verify Grid connection and basic Selenium functionality
 */
@Epic("Selenium Grid Testing")
@Feature("Basic Grid Tests")
public class SimpleGridTest {

    private WebDriver driver;
    private String gridUrl;

    @BeforeMethod
    public void setUp() throws Exception {
        // Check if running in Docker (SELENIUM_REMOTE_URL environment variable)
        gridUrl = System.getenv("SELENIUM_REMOTE_URL");
        if (gridUrl == null || gridUrl.isEmpty()) {
            gridUrl = "http://localhost:4444/wd/hub";
        }

        System.out.println("========================================");
        System.out.println("Connecting to Grid at: " + gridUrl);
        System.out.println("========================================");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        driver = new RemoteWebDriver(new URL(gridUrl), options);
        System.out.println("✅ Successfully connected to Grid!");
    }

    @Test(priority = 1)
    @Story("Grid Connection")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that connection to Selenium Grid is successful and driver is initialized")
    public void testGridConnection() {
        System.out.println("\n>>> Running testGridConnection");
        Allure.step("Verify driver is not null");
        Assert.assertNotNull(driver, "Driver should be initialized");
        System.out.println("✅ Grid connection test PASSED\n");
    }

    @Test(priority = 2)
    @Story("Basic Navigation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify navigation to Google.com works correctly")
    public void testNavigateToGoogle() throws Exception {
        System.out.println("\n>>> Running testNavigateToGoogle");
        System.out.println("Navigating to Google...");
        Allure.step("Navigate to Google.com");
        driver.get("https://www.google.com");

        String title = driver.getTitle();
        System.out.println("Page title: " + title);

        Assert.assertTrue(title.contains("Google"), "Title should contain 'Google'");
        System.out.println("✅ Google navigation test PASSED\n");
    }

    @Test(priority = 3)
    @Story("Basic Navigation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify navigation to GitHub.com works correctly")
    public void testNavigateToGitHub() throws Exception {
        System.out.println("\n>>> Running testNavigateToGitHub");
        System.out.println("Navigating to GitHub...");
        Allure.step("Navigate to GitHub.com");
        driver.get("https://github.com");

        String title = driver.getTitle();
        System.out.println("Page title: " + title);

        Assert.assertTrue(title.contains("GitHub"), "Title should contain 'GitHub'");
        System.out.println("✅ GitHub navigation test PASSED\n");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            // Capture screenshot on failure
            if (result.getStatus() == ITestResult.FAILURE) {
                System.out.println("❌ Test failed - capturing screenshot...");
                AllureHelper.captureScreenshot(driver, "FAILURE-" + result.getName());
                AllureHelper.attachPageSource(driver);
                AllureHelper.logBrowserInfo(driver);
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                System.out.println("✅ Test passed - capturing success screenshot...");
                AllureHelper.captureScreenshot(driver, "SUCCESS-" + result.getName());
            }
            
            System.out.println("Closing browser...");
            driver.quit();
            System.out.println("Browser closed successfully");
            System.out.println("========================================\n");
        }
    }
}
