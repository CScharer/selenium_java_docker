package com.cjs.qa.bts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cjs.qa.selenium.Page;

public class ReferenceMaterialsPage extends Page
{
	public ReferenceMaterialsPage(WebDriver webDriver)
	{
		super(webDriver);
	}

	private final By	linkTerritoryInformation	= By.id("terrInfoLink");
	private final By	linkClientInformation		= By.id("clientViewLink");
	private final By	linkPayorDetails			= By.id("payorViewLink");
	public final String	PAGE_TITLE					= "ReferenceMaterialsPage";

	public void verifyPage()
	{
		verifyTitle(PAGE_TITLE);
	}

	public void clickLinkTerritoryInformation()
	{
		clickObject(linkTerritoryInformation);
	}

	public void clickLinkClientInformation()
	{
		clickObject(linkClientInformation);
	}

	public void clickLinkPayorDetails()
	{
		clickObject(linkPayorDetails);
	}
}