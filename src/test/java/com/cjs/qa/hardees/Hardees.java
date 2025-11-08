package com.cjs.qa.hardees;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.cjs.qa.hardees.pages.SurveyPage;

public class Hardees
{
	public SurveyPage SurveyPage;

	public Hardees(WebDriver webDriver)
	{
		SurveyPage = new SurveyPage(webDriver);
		webDriver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(120));
	}
}