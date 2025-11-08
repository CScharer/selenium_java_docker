package com.cjs.qa.pluralsight.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cjs.qa.core.QAException;
import com.cjs.qa.selenium.Page;
import com.cjs.qa.utilities.Constants;
import com.cjs.qa.utilities.JavaHelpers;

public class SessionPage extends Page
{
	public SessionPage(WebDriver webDriver)
	{
		super(webDriver);
	}

	private final By	bySessionTitle		= By.xpath("//h1[@class='course-hero__title']");
	private final By	bySessionInstructor	= By.xpath(".//*[@id='ps-main']//p[@class='course-hero__byline']");
	private final By	bySessionSynopsis	= By.xpath(".//*[@id='ps-main']//p[@class='course-hero__excerpt']");

	public String getSessionInstructor() throws QAException
	{
		return webDriver.findElement(bySessionInstructor).getText();
	}

	public String getSessionTitle() throws QAException
	{
		return webDriver.findElement(bySessionTitle).getText();
	}

	public String getSessionSynopsis() throws QAException
	{
		return webDriver.findElement(bySessionSynopsis).getText();
	}

	public String getSessionInformation(String sessionURL) throws QAException
	{
		webDriver.get(sessionURL);
		JavaHelpers.sleep(3);
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(getSessionTitle());
		stringBuilder.append(Constants.NEWLINE);
		stringBuilder.append("By " + getSessionInstructor());
		stringBuilder.append(Constants.NEWLINE);
		stringBuilder.append(getSessionSynopsis());
		stringBuilder.append(Constants.NEWLINE);
		return stringBuilder.toString();
	}
}