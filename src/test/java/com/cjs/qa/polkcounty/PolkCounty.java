package com.cjs.qa.polkcounty;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.cjs.qa.polkcounty.pages.Details;
import com.cjs.qa.polkcounty.pages.Main;

public class PolkCounty
{
	public Main		Main;
	public Details	Details;

	public PolkCounty(WebDriver webDriver)
	{
		Main = new Main(webDriver);
		Details = new Details(webDriver);
		webDriver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(120));
	}
}