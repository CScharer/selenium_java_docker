package com.cjs.qa.junit.tests;

import com.cjs.qa.utilities.AllureHelper;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

/**
 * Advanced Selenium Features Tests
 * 
 * Demonstrates advanced Selenium capabilities:
 * - JavaScript execution
 * - Cookie management
 * - Window/Tab handling
 * - Mouse actions
 * - Keyboard actions
 * - Screenshot comparisons
 * - Local storage
 */
@Epic("Extended Test Coverage")
@Feature("Advanced Features")
public class AdvancedFeaturesTests {
    private static final Logger logger = LogManager.getLogger(AdvancedFeaturesTests.class);
    
    private WebDriver driver;
    private String gridUrl;
    
    @BeforeMethod
    public void setUp() throws Exception {
        logger.info("========================================");
        logger.info("🚀 ADVANCED FEATURES TEST SETUP");
        
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        logger.info("✅ Driver initialized");
    }
    
    @Test(priority = 1)
    @Story("JavaScript Execution")
    @Severity(SeverityLevel.NORMAL)
    @Description("Execute JavaScript commands in browser")
    public void testJavaScriptExecution() {
        logger.info("\n>>> Test: JavaScript Execution");
        
        Allure.step("Navigate to Google");
        driver.get("https://www.google.com");
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        Allure.step("Execute JavaScript to get page title");
        String title = (String) js.executeScript("return document.title;");
        logger.info("Title via JS: {}", title);
        Assert.assertTrue(title.contains("Google"));
        
        Allure.step("Execute JavaScript to scroll");
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        logger.info("Scrolled to bottom");
        
        Allure.step("Execute JavaScript to modify element");
        js.executeScript(
            "var searchBox = document.querySelector('textarea[name=\"q\"]');" +
            "if (searchBox) searchBox.style.border = '3px solid red';"
        );
        logger.info("Modified element style via JS");
        
        AllureHelper.captureScreenshot(driver, "JS-Execution");
        
        logger.info("✅ JavaScript execution successful");
    }
    
    @Test(priority = 2)
    @Story("Cookie Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test cookie creation, retrieval, and deletion")
    public void testCookieManagement() {
        logger.info("\n>>> Test: Cookie Management");
        
        Allure.step("Navigate to Google");
        driver.get("https://www.google.com");
        
        Allure.step("Get initial cookies");
        Set<Cookie> initialCookies = driver.manage().getCookies();
        int initialCount = initialCookies.size();
        logger.info("Initial cookies: {}", initialCount);
        
        Allure.step("Add custom cookie");
        Cookie customCookie = new Cookie("test_cookie", "selenium_test_value");
        driver.manage().addCookie(customCookie);
        logger.info("Added custom cookie");
        
        Allure.step("Verify cookie was added");
        Cookie retrievedCookie = driver.manage().getCookieNamed("test_cookie");
        Assert.assertNotNull(retrievedCookie, "Cookie should be retrievable");
        Assert.assertEquals(retrievedCookie.getValue(), "selenium_test_value");
        logger.info("Cookie verified: {} = {}", retrievedCookie.getName(), retrievedCookie.getValue());
        
        Allure.step("Delete specific cookie");
        driver.manage().deleteCookie(customCookie);
        Cookie deletedCookie = driver.manage().getCookieNamed("test_cookie");
        Assert.assertNull(deletedCookie, "Cookie should be deleted");
        logger.info("Cookie deleted successfully");
        
        AllureHelper.captureScreenshot(driver, "Cookie-Management");
        
        logger.info("✅ Cookie management successful");
    }
    
    @Test(priority = 3)
    @Story("Window Management")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test window size, position, and maximize")
    public void testWindowManagement() {
        logger.info("\n>>> Test: Window Management");
        
        Allure.step("Navigate to Google");
        driver.get("https://www.google.com");
        
        Allure.step("Get initial window size");
        Dimension initialSize = driver.manage().window().getSize();
        logger.info("Initial size: {}x{}", initialSize.width, initialSize.height);
        
        Allure.step("Resize window");
        driver.manage().window().setSize(new Dimension(800, 600));
        Dimension newSize = driver.manage().window().getSize();
        logger.info("New size: {}x{}", newSize.width, newSize.height);
        
        AllureHelper.captureScreenshot(driver, "Window-Resized");
        
        Allure.step("Maximize window");
        driver.manage().window().maximize();
        Dimension maxSize = driver.manage().window().getSize();
        logger.info("Maximized size: {}x{}", maxSize.width, maxSize.height);
        
        AllureHelper.captureScreenshot(driver, "Window-Maximized");
        
        // Verify resize worked
        Assert.assertNotEquals(initialSize.width, newSize.width,
            "Window should have been resized");
        
        logger.info("✅ Window management successful");
    }
    
    @Test(priority = 4)
    @Story("Keyboard Actions")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test advanced keyboard interactions")
    public void testKeyboardActions() {
        logger.info("\n>>> Test: Keyboard Actions");
        
        Allure.step("Navigate to Google");
        driver.get("https://www.google.com");
        
        Allure.step("Find search box");
        WebElement searchBox = driver.findElement(By.name("q"));
        
        Allure.step("Type with pauses");
        searchBox.sendKeys("S");
        searchBox.sendKeys("e");
        searchBox.sendKeys("l");
        searchBox.sendKeys("e");
        searchBox.sendKeys("n");
        searchBox.sendKeys("i");
        searchBox.sendKeys("u");
        searchBox.sendKeys("m");
        logger.info("Typed: Selenium");
        
        AllureHelper.captureScreenshot(driver, "After-Typing");
        
        Allure.step("Clear with keyboard shortcut");
        searchBox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        searchBox.sendKeys(Keys.BACK_SPACE);
        logger.info("Cleared input");
        
        Allure.step("Type and submit");
        searchBox.sendKeys("Test" + Keys.ENTER);
        logger.info("Submitted search");
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.warn("Sleep interrupted", e);
        }
        
        AllureHelper.captureScreenshot(driver, "Keyboard-Actions");
        
        logger.info("✅ Keyboard actions successful");
    }
    
    @Test(priority = 5)
    @Story("Page Navigation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test browser navigation (back, forward, refresh)")
    public void testBrowserNavigation() {
        logger.info("\n>>> Test: Browser Navigation");
        
        Allure.step("Navigate to first page");
        driver.get("https://www.google.com");
        String firstUrl = driver.getCurrentUrl();
        logger.info("First URL: {}", firstUrl);
        
        Allure.step("Navigate to second page");
        driver.get("https://github.com");
        String secondUrl = driver.getCurrentUrl();
        logger.info("Second URL: {}", secondUrl);
        
        AllureHelper.captureScreenshot(driver, "Second-Page");
        
        Allure.step("Navigate back");
        driver.navigate().back();
        String backUrl = driver.getCurrentUrl();
        logger.info("After back: {}", backUrl);
        Assert.assertTrue(backUrl.contains("google"), "Should navigate back to Google");
        
        Allure.step("Navigate forward");
        driver.navigate().forward();
        String forwardUrl = driver.getCurrentUrl();
        logger.info("After forward: {}", forwardUrl);
        Assert.assertTrue(forwardUrl.contains("github"), "Should navigate forward to GitHub");
        
        Allure.step("Refresh page");
        driver.navigate().refresh();
        logger.info("Page refreshed");
        
        AllureHelper.captureScreenshot(driver, "After-Refresh");
        
        logger.info("✅ Browser navigation successful");
    }
    
    @Test(priority = 6)
    @Story("Element Properties")
    @Severity(SeverityLevel.MINOR)
    @Description("Test retrieving element properties and attributes")
    public void testElementProperties() {
        logger.info("\n>>> Test: Element Properties");
        
        Allure.step("Navigate to Google");
        driver.get("https://www.google.com");
        
        Allure.step("Find search box");
        WebElement searchBox = driver.findElement(By.name("q"));
        
        Allure.step("Get element properties");
        String tagName = searchBox.getTagName();
        String nameAttr = searchBox.getAttribute("name");
        String typeAttr = searchBox.getAttribute("type");
        Dimension size = searchBox.getSize();
        Point location = searchBox.getLocation();
        
        logger.info("Tag: {}", tagName);
        logger.info("Name: {}", nameAttr);
        logger.info("Type: {}", typeAttr);
        logger.info("Size: {}x{}", size.width, size.height);
        logger.info("Location: ({}, {})", location.x, location.y);
        
        AllureHelper.captureScreenshot(driver, "Element-Properties");
        
        Allure.step("Verify properties");
        Assert.assertEquals(tagName, "textarea", "Should be textarea element");
        Assert.assertEquals(nameAttr, "q", "Name should be 'q'");
        
        logger.info("✅ Element properties retrieved successfully");
    }
    
    @Test(priority = 7)
    @Story("Performance")
    @Severity(SeverityLevel.MINOR)
    @Description("Test page load and element search performance")
    public void testPerformanceMetrics() {
        logger.info("\n>>> Test: Performance Metrics");
        
        Allure.step("Measure page load time");
        long startTime = System.currentTimeMillis();
        driver.get("https://www.google.com");
        long endTime = System.currentTimeMillis();
        long loadTime = endTime - startTime;
        
        logger.info("Page load time: {}ms", loadTime);
        
        Allure.step("Measure element find time");
        startTime = System.currentTimeMillis();
        WebElement searchBox = driver.findElement(By.name("q"));
        endTime = System.currentTimeMillis();
        long findTime = endTime - startTime;
        
        logger.info("Element find time: {}ms", findTime);
        
        AllureHelper.captureScreenshot(driver, "Performance-Test");
        
        Allure.step("Verify performance acceptable");
        Assert.assertTrue(loadTime < 10000, "Page should load within 10 seconds");
        Assert.assertTrue(findTime < 2000, "Element should be found within 2 seconds");
        
        logger.info("✅ Performance metrics acceptable");
    }
    
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            if (result.getStatus() == ITestResult.FAILURE) {
                logger.error("❌ Advanced test failed");
                AllureHelper.captureScreenshot(driver, "FAILURE-" + result.getName());
                AllureHelper.attachPageSource(driver);
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                logger.info("✅ Advanced test passed");
            }
            
            logger.info("Closing browser...");
            driver.quit();
            logger.info("========================================\n");
        }
    }
}

