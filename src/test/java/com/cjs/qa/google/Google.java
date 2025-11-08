package com.cjs.qa.google;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.cjs.qa.google.pages.FlightsPage;
import com.cjs.qa.google.pages.SignInPage;

public class Google
{
	public FlightsPage	FlightsPage;
	public SignInPage	SignInPage;

	public Google(WebDriver webDriver)
	{
		FlightsPage = new FlightsPage(webDriver);
		SignInPage = new SignInPage(webDriver);
		webDriver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(120));
	}
}