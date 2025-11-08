package com.cjs.qa.bts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cjs.qa.selenium.Page;

public class SidbarAdminFunctionsPage extends Page
{
	public SidbarAdminFunctionsPage(WebDriver webDriver)
	{
		super(webDriver);
	}

	// DECLARATIONS
	private final By	linkEdit			= By.id("EditEdit");
	private final By	linkValidate		= By.id("ValidateValidate");
	private final By	linkRate			= By.id("RateRate");
	private final By	linkBind			= By.id("BindBind");
	private final By	linkIssue			= By.id("IssueIssue");
	private final By	linkRequestPrint	= By.id("requestPrintLink");

	// METHODS SET
	public void clickLinkEdit()
	{
		clickObject(linkEdit);
	}

	public void clickLinkValidate()
	{
		clickObject(linkValidate);
	}

	public void clickLinkRate()
	{
		clickObject(linkRate);
	}

	public void clickLinkBind()
	{
		clickObject(linkBind);
	}

	public void clickLinkIssue()
	{
		clickObject(linkIssue);
	}

	public void clickLinkRequestPrint()
	{
		clickObject(linkRequestPrint);
	}
}