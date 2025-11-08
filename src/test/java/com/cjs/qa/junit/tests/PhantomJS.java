package com.cjs.qa.junit.tests;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cjs.qa.utilities.CommandLine;

public class PhantomJS
{
	public static final String	DRIVER_PATH				= "C:\\Selenium\\Grid2\\Drivers\\";
	public static final String	DRIVER_EXE_PHANTOMJS	= "phantomjs.exe";
	public static final String	DRIVER_PATH_PHANTOMJS	= DRIVER_PATH + DRIVER_EXE_PHANTOMJS;
	WebDriver					webDriver;
	WebElement					webElement;
	By							byObject;

	@Before
	public void beforeTest() throws Exception
	{
		if (!CommandLine.isProcessRunning(DRIVER_EXE_PHANTOMJS))
		{
			String command = "cmd \"PhantomJS\" cmd /C \"" + DRIVER_PATH_PHANTOMJS + "\"";
			CommandLine.runProcessNoWait(command);
		}
	}

	@After
	public void afterTest() throws Exception
	{
		if (CommandLine.isProcessRunning(DRIVER_EXE_PHANTOMJS))
		{
			CommandLine.killProcess(DRIVER_EXE_PHANTOMJS);
		}
	}

	@Test
	public void phantomJS() throws Exception
	{
		System.setProperty("phantomjs.binary.path", DRIVER_PATH_PHANTOMJS);
		// WebDriver webDriver = new PhantomJSDriver(BrowserVersion.CHROME)
		webDriver = new PhantomJSDriver();
		webDriver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
		webDriver.get("https://www.google.com");
		for (int counter = 1; counter <= 10; counter++)
		{
			String searchString = "Searching for " + counter;
			byObject = By.name("q");
			webElement = waitExists(byObject);
			webElement.clear();
			webElement.sendKeys(searchString);
			System.out.println("Search Text:[" + webElement.getAttribute("value") + "]");
			// byObject = By.xpath(".//input[@name='btnK']");
			// byObject = By.name("btnK");
			// webElement = waitExists(byObject);
			// // webElement.submit()
			// webElement.click();
			webElement.sendKeys(Keys.ENTER);
			webDriver.navigate().refresh();
		}
		byObject = By.id("gb_70");
		byObject = By.xpath(".//a[.='Sign in']");
		webElement = waitExists(byObject);
		webElement.click();
		String title = webDriver.getTitle();
		System.out.println("title:[" + title + "]");
	}

	protected WebElement waitExists(By by)
	{
		// May need to update as new webDriverWait returns boolean.
		final WebDriverWait webDriverWait = new WebDriverWait(webDriver, java.time.Duration.ofSeconds(15));
		return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
}