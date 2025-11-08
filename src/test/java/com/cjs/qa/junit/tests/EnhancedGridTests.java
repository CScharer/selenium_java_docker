package com.cjs.qa.junit.tests;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;
import java.time.Duration;

/**
 * Enhanced Grid tests demonstrating multi-browser support and advanced scenarios
 */
@Epic("Selenium Grid Testing")
@Feature("Enhanced Grid Tests")
public class EnhancedGridTests {

    private WebDriver driver;
    private String gridUrl;
    private String currentBrowser;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) throws Exception {
        currentBrowser = browser;
        gridUrl = System.getenv("SELENIUM_REMOTE_URL");
        if (gridUrl == null || gridUrl.isEmpty()) {
            gridUrl = "http://localhost:4444/wd/hub";
        }

        System.out.println("\n========================================");
        System.out.println("Test Browser: " + browser.toUpperCase());
        System.out.println("Grid URL: " + gridUrl);
        System.out.println("========================================");

        if ("firefox".equalsIgnoreCase(browser)) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            driver = new RemoteWebDriver(new URL(gridUrl), options);
        } else {
            // Default to Chrome
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            driver = new RemoteWebDriver(new URL(gridUrl), options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        System.out.println("✅ " + currentBrowser.toUpperCase() + " browser initialized\n");
    }

    @Test(priority = 1, description = "Verify Google homepage loads correctly")
    @Story("Homepage Navigation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that Google homepage loads successfully and contains expected elements")
    public void testGoogleHomepage() {
        System.out.println(">>> Test: Google Homepage");
        Allure.step("Navigate to Google homepage");
        driver.get("https://www.google.com");

        String title = driver.getTitle();
        System.out.println("Page title: " + title);

        Assert.assertTrue(title.contains("Google"), "Title should contain 'Google'");
        Assert.assertTrue(driver.getCurrentUrl().contains("google.com"), "URL should contain google.com");

        System.out.println("✅ Google homepage loaded successfully");
    }

    @Test(priority = 2, description = "Perform a Google search and verify results")
    @Story("Search Functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify Google search functionality with proper waits and URL verification")
    public void testGoogleSearch() {
        System.out.println(">>> Test: Google Search");
        Allure.step("Navigate to Google homepage");
        driver.get("https://www.google.com");

        String initialUrl = driver.getCurrentUrl();

        // Find search box and enter search term
        WebElement searchBox = driver.findElement(By.name("q"));
        String searchTerm = "Selenium";
        Allure.step("Enter search term: " + searchTerm);
        searchBox.sendKeys(searchTerm);
        System.out.println("Entered search term: " + searchTerm);

        // Submit search
        Allure.step("Submit search");
        searchBox.sendKeys(Keys.RETURN);

        // Wait for URL to change (indicates search was performed)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(initialUrl)));

        // Verify URL changed
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Results URL: " + currentUrl);

        Assert.assertNotEquals(currentUrl, initialUrl, "URL should have changed after search");
        Assert.assertTrue(currentUrl.contains("google.com"), "Should still be on Google");

        System.out.println("✅ Search completed successfully");
    }

    @Test(priority = 3, description = "Verify GitHub homepage and search")
    @Story("Homepage Navigation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify GitHub homepage loads and logo is present")
    public void testGitHubHomepage() {
        System.out.println(">>> Test: GitHub Homepage");
        Allure.step("Navigate to GitHub homepage");
        driver.get("https://github.com");

        String title = driver.getTitle();
        System.out.println("Page title: " + title);

        Assert.assertTrue(title.contains("GitHub"), "Title should contain 'GitHub'");

        // Verify GitHub logo is present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logo = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("[aria-label*='Homepage'], .octicon-mark-github, [data-testid='github-logo']")));

        Assert.assertNotNull(logo, "GitHub logo should be present");
        System.out.println("✅ GitHub homepage verified");
    }

    @Test(priority = 4, description = "Test navigation between multiple sites")
    @Story("Multi-Site Navigation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify ability to navigate between multiple websites in same session")
    public void testMultiSiteNavigation() {
        System.out.println(">>> Test: Multi-Site Navigation");

        // Navigate to multiple sites
        String[] sites = {
            "https://www.google.com",
            "https://github.com",
            "https://www.wikipedia.org"
        };

        for (String site : sites) {
            driver.get(site);
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Navigated to: " + currentUrl);

            Assert.assertTrue(currentUrl.contains(site.replace("https://www.", "").replace("https://", "")),
                "URL should match expected site");
        }

        System.out.println("✅ Multi-site navigation successful");
    }

    @Test(priority = 5, description = "Verify page load performance")
    @Story("Performance Testing")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify page loads within acceptable time limits (<10 seconds)")
    public void testPageLoadPerformance() {
        System.out.println(">>> Test: Page Load Performance");
        Allure.step("Measure page load time");

        long startTime = System.currentTimeMillis();
        driver.get("https://www.google.com");
        long endTime = System.currentTimeMillis();

        long loadTime = endTime - startTime;
        System.out.println("Page load time: " + loadTime + "ms");

        Assert.assertTrue(loadTime < 10000, "Page should load within 10 seconds");
        System.out.println("✅ Page load performance acceptable");
    }

    @Test(priority = 6, description = "Test browser capabilities")
    @Story("Browser Features")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify browser capabilities including JavaScript, cookies, and window management")
    public void testBrowserCapabilities() {
        System.out.println(">>> Test: Browser Capabilities");

        driver.get("https://www.google.com");

        // Verify JavaScript is enabled (search box requires JS)
        WebElement searchBox = driver.findElement(By.name("q"));
        Assert.assertTrue(searchBox.isDisplayed(), "Search box should be displayed (JS enabled)");

        // Verify cookies can be retrieved
        var cookies = driver.manage().getCookies();
        System.out.println("Cookies found: " + cookies.size());
        Assert.assertTrue(cookies.size() > 0, "Should have at least one cookie");

        // Verify window can be resized
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1024, 768));
        org.openqa.selenium.Dimension size = driver.manage().window().getSize();
        System.out.println("Window size: " + size.width + "x" + size.height);

        System.out.println("✅ Browser capabilities verified");
    }

    @Test(priority = 7, description = "Test form interaction")
    @Story("Form Interactions")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify form field interactions including input, clear, and attribute validation")
    public void testFormInteraction() {
        System.out.println(">>> Test: Form Interaction");

        driver.get("https://www.google.com");

        // Interact with search form
        WebElement searchBox = driver.findElement(By.name("q"));

        // Test various input methods
        searchBox.clear();
        searchBox.sendKeys("Selenium");
        Assert.assertEquals(searchBox.getAttribute("value"), "Selenium");

        searchBox.clear();
        searchBox.sendKeys("Grid Testing");
        Assert.assertEquals(searchBox.getAttribute("value"), "Grid Testing");

        System.out.println("✅ Form interaction successful");
    }

    @Test(priority = 8, description = "Verify responsive design detection")
    @Story("Responsive Design")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify website works correctly at different viewport sizes")
    public void testResponsiveDesign() {
        System.out.println(">>> Test: Responsive Design");

        driver.get("https://www.google.com");

        // Test different viewport sizes
        int[][] viewports = {{1920, 1080}, {1024, 768}, {768, 1024}};

        for (int[] viewport : viewports) {
            driver.manage().window().setSize(new org.openqa.selenium.Dimension(viewport[0], viewport[1]));
            System.out.println("Testing viewport: " + viewport[0] + "x" + viewport[1]);

            WebElement searchBox = driver.findElement(By.name("q"));
            Assert.assertTrue(searchBox.isDisplayed(), "Search box should be visible at " + viewport[0] + "x" + viewport[1]);
        }

        System.out.println("✅ Responsive design verified");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            System.out.println("\nClosing " + currentBrowser.toUpperCase() + " browser...");
            driver.quit();
            System.out.println("Browser closed successfully");
            System.out.println("========================================\n");
        }
    }
}

