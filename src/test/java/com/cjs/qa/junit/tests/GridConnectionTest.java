package com.cjs.qa.junit.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static org.junit.Assert.*;

/**
 * Simple test to verify Grid connection and basic Selenium functionality
 */
public class GridConnectionTest {

    private WebDriver driver;
    private String gridUrl;

    @Before
    public void setUp() throws Exception {
        // Check if running in Docker (SELENIUM_REMOTE_URL environment variable)
        gridUrl = System.getenv("SELENIUM_REMOTE_URL");
        if (gridUrl == null || gridUrl.isEmpty()) {
            gridUrl = "http://localhost:4444/wd/hub";
        }

        System.out.println("Connecting to Grid at: " + gridUrl);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        driver = new RemoteWebDriver(new URL(gridUrl), options);
        System.out.println("Successfully connected to Grid!");
    }

    @Test
    public void testGridConnection() {
        assertNotNull("Driver should be initialized", driver);
        System.out.println("✅ Grid connection test PASSED");
    }

    @Test
    public void testNavigateToGoogle() throws Exception {
        System.out.println("Navigating to Google...");
        driver.get("https://www.google.com");

        String title = driver.getTitle();
        System.out.println("Page title: " + title);

        assertTrue("Title should contain 'Google'", title.contains("Google"));
        System.out.println("✅ Google navigation test PASSED");
    }

    @Test
    public void testNavigateToGitHub() throws Exception {
        System.out.println("Navigating to GitHub...");
        driver.get("https://github.com");

        String title = driver.getTitle();
        System.out.println("Page title: " + title);

        assertTrue("Title should contain 'GitHub'", title.contains("GitHub"));
        System.out.println("✅ GitHub navigation test PASSED");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            System.out.println("Closing browser...");
            driver.quit();
            System.out.println("Browser closed successfully");
        }
    }
}
