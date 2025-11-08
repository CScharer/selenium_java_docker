package com.cjs.qa.junit.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;

/**
 * Simple TestNG test to verify Grid connection and basic Selenium functionality
 */
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
    public void testGridConnection() {
        System.out.println("\n>>> Running testGridConnection");
        Assert.assertNotNull(driver, "Driver should be initialized");
        System.out.println("✅ Grid connection test PASSED\n");
    }

    @Test(priority = 2)
    public void testNavigateToGoogle() throws Exception {
        System.out.println("\n>>> Running testNavigateToGoogle");
        System.out.println("Navigating to Google...");
        driver.get("https://www.google.com");

        String title = driver.getTitle();
        System.out.println("Page title: " + title);

        Assert.assertTrue(title.contains("Google"), "Title should contain 'Google'");
        System.out.println("✅ Google navigation test PASSED\n");
    }

    @Test(priority = 3)
    public void testNavigateToGitHub() throws Exception {
        System.out.println("\n>>> Running testNavigateToGitHub");
        System.out.println("Navigating to GitHub...");
        driver.get("https://github.com");

        String title = driver.getTitle();
        System.out.println("Page title: " + title);

        Assert.assertTrue(title.contains("GitHub"), "Title should contain 'GitHub'");
        System.out.println("✅ GitHub navigation test PASSED\n");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            System.out.println("Closing browser...");
            driver.quit();
            System.out.println("Browser closed successfully");
            System.out.println("========================================\n");
        }
    }
}
