package com.cjs.qa.dropbox;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.cjs.qa.dropbox.pages.SignInPage;

public class Dropbox
{
	public SignInPage SignInPage;

	public Dropbox(WebDriver webDriver)
	{
		SignInPage = new SignInPage(webDriver);
		webDriver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(120));
	}
}