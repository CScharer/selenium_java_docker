package com.cjs.qa.bts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cjs.qa.selenium.Page;

public class PremiumSummaryRightNav extends Page
{
	public PremiumSummaryRightNav(WebDriver webDriver)
	{
		super(webDriver);
	}

	// DECLARATIONS
	// private final String NODE_FORM1ADMIN = "form1:adminFunctions:";
	// private final String NODE_POLICYISSUANCE = NODE_FORM1ADMIN +
	// "policyIssuanceFunctions:";
	// private final String NODE_NAVIGATIONMENU = ":navigationMenuItem:";
	// private final String NODE_MENUCSUBMIT = "menuCommandSubmittable";
	private final By	sidebarRightOpen			= By.xpath(".//*[@id='outer-east-open-btn']");
	private final By	sidebarRightClose			= By.xpath(".//*[@'outer-east-close-btn']");
	private final By	linkExitPolicySearch		= By.xpath(".//*[@id='requestLinkSearch1']");
	private final By	linkChangeDates				= By.xpath(".//*[@id='changeDateLink']");
	private final By	linkPolicyErrors			= By.xpath(".//*[@id='policyErrorsLink']");
	private final By	linkPolicyViews				= By.xpath(".//*[@id='policyViewsLink']");
	private final By	linkRequestPrint			= By.xpath(".//*[@id='requestPrintLink']");
	private final By	linkTerritoryInformation	= By.xpath(".//*[@id='terrInfoLink']");
	private final By	linkClientInformation		= By.xpath(".//*[@id='clientViewLink']");
	private final By	linkPayorDetails			= By.xpath(".//*[@id='payorViewLink']");

	// METHODS
	public boolean isSidebarOpen()
	{
		try
		{
			final WebElement element = getWebElement(sidebarRightClose);
			return true;
		} catch (final Exception e)
		{
			return false;
		}
	}

	public void toggleSidebarRight()
	{
		if (isSidebarOpen())
		{
			clickObject(sidebarRightClose);
		} else
		{
			clickObject(sidebarRightOpen);
		}
	}

	public void clickLinkExitPolicySearch()
	{
		clickObject(linkExitPolicySearch);
	}

	public void clickLinkChangeDates()
	{
		clickObject(linkChangeDates);
	}

	public void clickLinkPolicyErrors()
	{
		clickObject(linkPolicyErrors);
	}

	public void clickLinkPolicyViews()
	{
		clickObject(linkPolicyViews);
	}

	public void clickLinkRequestPrint()
	{
		clickObject(linkRequestPrint);
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
