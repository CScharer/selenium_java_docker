package com.cjs.qa.americanairlines;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.cjs.qa.americanairlines.pages.VacationabilityPage;

public class AmericanAirlines
{
	public VacationabilityPage VacationabilityPage;

	public AmericanAirlines(WebDriver webDriver)
	{
		VacationabilityPage = new VacationabilityPage(webDriver);
		webDriver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(120));
	}
}