package com.cjs.qa.bts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cjs.qa.selenium.Page;

public class BI_LeftNav extends Page
{
	public BI_LeftNav(WebDriver webDriver)
	{
		super(webDriver);
	}

	private final By	buttonBasicInformation					= By.id("basicinfonavitem");
	private final By	buttonNamedInsureds						= By.id("NamedInsuredsNamedInsureds");
	private final By	buttonAddresses							= By.id("viewAddressId");
	private final By	buttonScheduleOfLocationsAndVehicles	= By
			.id("ScheduleofLocationsandVehiclesScheduleofLocationsandVehicles");
	private final By	buttonInsurableInterestSummary			= By
			.id("InsurableInterestSummaryInsurableInterestSummary");
	// ***UPDATE***
	private final By	buttonManageProducts					= By
			.xpath(".//*[@id='left-sidebar']/div/div[2]/div/ul/li[6]");
	// .id("manageProductsId");
	// private By CWG(){}
	private final By	buttonBusinessowners					= By.id("Businessowners500914");
	private final By	buttonForms								= By.id("FormsForms");
	private final By	buttonCommissions						= By.id("defaultCommissionsId");
	private final By	buttonPremiumModifiers					= By.id("PremiumModifiersPremiumModifiers");
	private final By	buttonPremiumSummary					= By.id("premiumSummaryId");
	private final By	buttonPolicyActivity					= By.id("policyActivityId");
	private final By	buttonNotes								= By.id("notesId");
	private final By	buttonPolicyDocuments					= By.id("policyDocumentsId");
	private final By	buttonExportExcel						= By.xpath(".//*[@id='viewport']/div[2]/div/a/span");
	public final String	PAGE_TITLE								= "BI_LeftNav";

	public void verifyPage()
	{
		verifyTitle(PAGE_TITLE);
	}

	public void clickButtonBasicInformation()
	{
		clickObject(buttonBasicInformation);
	}

	public void clickButtonNamedInsureds()
	{
		clickObject(buttonNamedInsureds);
	}

	public void clickButtonAddresses()
	{
		clickObject(buttonAddresses);
	}

	public void clickButtonExportExcel()
	{
		clickObject(buttonExportExcel);
	}

	public void clickButtonScheduleOfLocationsAndVehicles()
	{
		clickObject(buttonScheduleOfLocationsAndVehicles);
	}

	public void clickButtonInsurableInterestSummary()
	{
		clickObject(buttonInsurableInterestSummary);
	}

	public void clickButtonManageProducts()
	{
		refresh();
		clickObject(buttonManageProducts);
	}

	public void clickButtonBusinessowners()
	{
		clickObject(buttonBusinessowners);
	}

	public void clickButtonForms()
	{
		clickObject(buttonForms);
	}

	public void clickButtonCommissions()
	{
		clickObject(buttonCommissions);
	}

	public void clickButtonPremiumModifiers()
	{
		clickObject(buttonPremiumModifiers);
	}

	public void clickButtonPremiumSummary()
	{
		clickObject(buttonPremiumSummary);
	}

	public void clickButtonPolicyActivity()
	{
		clickObject(buttonPolicyActivity);
	}

	public void clickButtonNotes()
	{
		clickObject(buttonNotes);
	}

	public void clickButtonPolicyDocuments()
	{
		clickObject(buttonPolicyDocuments);
	}
}