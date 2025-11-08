package com.cjs.qa.bts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cjs.qa.selenium.Page;

public class BI_RightNav extends Page
{
	public BI_RightNav(WebDriver webDriver)
	{
		super(webDriver);
	}

	// DECLARATIONS
	private final By	sidebarRight				= By.xpath("html/body/div[1]/nav/div/div[1]/div[4]/button");
	private final By	linkExitPolicySearch		= By.xpath(".//*[@id='requestLinkSearch1']");
	private final By	linkChangeDates				= By.xpath(".//*[@id='changeDateLink']");
	private final By	linkPolicyErrors			= By.xpath(".//*[@id='policyErrorsLink']");
	private final By	linkPolicyViews				= By.xpath(".//*[@id='policyViewsLink']");
	private final By	linkEdit					= By.xpath(".//*[@id='EditEdit']");
	private final By	linkValidate				= By.xpath(".//*[@id='ValidateValidate']");
	private final By	linkRate					= By.xpath(".//*[@id='RateRate']");
	private final By	linkBind					= By.xpath(".//*[@id='BindBind']");
	private final By	linkIssue					= By.xpath(".//*[@id='IssueIssue']");
	private final By	linkRequestPrint			= By.xpath(".//*[@id='requestPrintLink']");
	private final By	linkTerritoryInformation	= By.xpath(".//*[@id='terrInfoLink']");
	private final By	linkClientInformation		= By.xpath(".//*[@id='clientViewLink']");
	private final By	linkPayorDetails			= By.xpath(".//*[@id='payorViewLink']");

	// METHODS SET
	public void toggleSidebarRight()
	{
		clickObject(sidebarRight);
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
