package com.cjs.qa.microsoft;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.cjs.qa.microsoft.pages.BingPage;
import com.cjs.qa.microsoft.pages.RewardsPage;
import com.cjs.qa.microsoft.pages.SignInPage;

public class Microsoft
{
	public BingPage		BingPage;
	public RewardsPage	RewardsPage;
	public SignInPage	SignInPage;

	public Microsoft(WebDriver webDriver)
	{
		BingPage = new BingPage(webDriver);
		RewardsPage = new RewardsPage(webDriver);
		SignInPage = new SignInPage(webDriver);
		webDriver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(120));
	}
}