package com.cjs.qa.bts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cjs.qa.selenium.Page;

public class RequestPrintRightNav extends Page
{
	public RequestPrintRightNav(WebDriver webDriver)
	{
		super(webDriver);
	}

	// DECLARATIONS
	private final By	sidebarRight				= By.xpath(".//*[@id='outer-east-open-btn']");
	private final By	linkExitPolicySearch		= By.xpath(".//*[@id='requestLinkExitPolicy']");
	private final By	linkChangeDates				= By.xpath(".//*[@id='requestLinkChangeDates']");
	private final By	linkPolicyErrors			= By.xpath(".//*[@id='policyErrorsLink']");
	private final By	linkPolicyViews				= By
			.xpath(".//*[@id='form1:adminFunctions:panelGroupButtonForPolicyViews']/a");
	private final By	linkEdit					= By.xpath(
			".//*[@id='form1:adminFunctions:policyIssuanceFunctions:0:navigationMenuItem:menuCommandSubmittable']");
	private final By	linkValidate				= By.xpath(
			".//*[@id='form1:adminFunctions:policyIssuanceFunctions:1:navigationMenuItem:menuCommandSubmittable']");
	private final By	linkRate					= By.xpath(
			".//*[@id='form1:adminFunctions:policyIssuanceFunctions:2:navigationMenuItem:menuCommandSubmittable']");
	private final By	linkBind					= By.xpath(
			".//*[@id='form1:adminFunctions:policyIssuanceFunctions:3:navigationMenuItem:menuCommandSubmittable']");
	private final By	linkIssue					= By.xpath(
			".//*[@id='form1:adminFunctions:policyIssuanceFunctions:4:navigationMenuItem:menuCommandSubmittable']");
	private final By	linkRequestPrint			= By
			.xpath(".//*[@id='form1:adminFunctions:policyIssuanceFunctions:5:navigationMenuItem:menuLinkSubmittable']");
	private final By	linkTerritoryInformation	= By.xpath(".//*[@id='form1:adminFunctions:terrInfoLink']");
	private final By	linkClientInformation		= By.xpath(".//*[@id='form1:adminFunctions:clientViewLink']");
	private final By	linkPayorDetails			= By.xpath(".//*[@id='form1:adminFunctions:payorDetailsLink']");

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