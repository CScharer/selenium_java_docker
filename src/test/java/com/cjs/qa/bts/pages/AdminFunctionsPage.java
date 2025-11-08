package com.cjs.qa.bts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cjs.qa.selenium.Page;

public class AdminFunctionsPage extends Page
{
	public AdminFunctionsPage(WebDriver webDriver)
	{
		super(webDriver);
	}

	private final By	DropdownTestStatus		= By.id("modeBI");
	private final By	linkExitPolicy			= By.id("requestLinkSearch1");
	private final By	linkScheduledProcesses	= By.id("requestLinkScheduleProcess");
	private final By	linkWebServices			= By.id("requestLinkForWebServices");
	private final By	linkMassNotes			= By.id("requestLinkForMassNotes");
	private final By	linkViewExportRequests	= By.id("requestLinkExportRequest");
	private final By	linkQueriesTools		= By.id("requestLinkQueriesTools");
	public final String	PAGE_TITLE				= "AdminFunctionsPage";

	public void verifyPage()
	{
		verifyTitle(PAGE_TITLE);
	}

	public void clickLinkExitPolicy()
	{
		clickObject(linkExitPolicy);
	}

	public void clickLinkScheduledProcesses()
	{
		clickObject(linkScheduledProcesses);
	}

	public void clickLinkWebServices()
	{
		clickObject(linkWebServices);
	}

	public void clickLinkMassNotes()
	{
		clickObject(linkMassNotes);
	}

	public void clickLinkViewExportRequests()
	{
		clickObject(linkViewExportRequests);
	}

	public void clickLinkQueriesTools()
	{
		clickObject(linkQueriesTools);
	}

	public void selectDropdownTestStatus(String value)
	{
		selectDropdown(DropdownTestStatus, value);
	}
}