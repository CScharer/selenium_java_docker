package com.cjs.qa.bts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cjs.qa.selenium.Page;

public class BI_BillingInformationPage extends Page
{
	public BI_BillingInformationPage(WebDriver webDriver)
	{
		super(webDriver);
	}

	private final By	editBillingAccountNumber		= By.id("billingAccountNumber");
	private final By	DropdownBillType				= By.id("billType");
	private final By	DropdownPayPlan					= By.id("payPlan");
	private final By	DropdownAmendmentDistribution	= By.id("amendmentDistribution");
	private final By	editDepositAmount				= By.id("depositAmount");
	private final By	DropdownDepositType				= By.id("depositType");
	private final By	editDepositPercent				= By.id("depositPercent");
	private final By	buttonFrame						= By.id("billingInformation-title");
	public final String	PAGE_TITLE						= "BI_BillingInformationPage";

	public void verifyPage()
	{
		verifyTitle(PAGE_TITLE);
	}

	public void setEditBillingAccountNumber(String value)
	{
		setEdit(editBillingAccountNumber, value);
	}

	public void selectDropdownBillType(String value)
	{
		selectDropdown(DropdownBillType, value);
	}

	public void selectDropdownPayPlan(String value)
	{
		selectDropdown(DropdownPayPlan, value);
	}

	public void selectDropdownAmendmentDistribution(String value)
	{
		selectDropdown(DropdownAmendmentDistribution, value);
	}

	public void setEditDepositAmount(String value)
	{
		setEdit(editDepositAmount, value);
	}

	public void selectDropdownDepositType(String value)
	{
		selectDropdown(DropdownDepositType, value);
	}

	public void setEditDepositPercent(String value)
	{
		setEdit(editDepositPercent, value);
	}

	public void toggleFrame()
	{
		clickObject(buttonFrame);
	}
}