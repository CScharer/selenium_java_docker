package com.cjs.qa.bts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cjs.qa.selenium.Page;

public class BI_MiscellaneousInformationPage extends Page
{
	public BI_MiscellaneousInformationPage(WebDriver webDriver)
	{
		super(webDriver);
	}

	private final By	CheckboxPackageModification		= By.id("packageModification");
	private final By	CheckboxAuditable				= By.id("auditable");
	private final By	DropdownLegalEntity				= By.id("legalEntity");
	private final By	DropdownAuditBasis				= By.id("auditBasis");
	private final By	DropdownTypeOfPolicy			= By.id("typeOfPolicy");
	private final By	DropdownExternalAuditIndicator	= By.id("externalAuditIndicator");
	private final By	DropdownPolicySymbol			= By.id("policySymbol");
	private final By	editNaicsCode					= By.id("naicsCode");
	private final By	editBusinessDescription			= By.id("businessDescription");
	private final By	editOwnersLegalName				= By.id("ownersLegalName");
	private final By	editFinancialReliabilityCode	= By.id("financialReliabilityCode");
	private final By	editNationalAccountIndicator	= By.id("nationalAccountIndicator");
	private final By	DropdownReportingCompany		= By.id("reportingCompany");
	private final By	buttonFrame						= By.id("miscInformation-title");
	public final String	PAGE_TITLE						= "BI_MiscellaneousInformationPage";

	public void verifyPage()
	{
		verifyTitle(PAGE_TITLE);
	}

	public void toggleCheckboxPackageModification()
	{
		if (!getWebElement(CheckboxPackageModification).isSelected())
		{
			clickObject(CheckboxPackageModification);
		}
	}

	public void toggleCheckboxAuditable()
	{
		if (!getWebElement(CheckboxAuditable).isSelected())
		{
			clickObject(CheckboxAuditable);
		}
	}

	public void selectDropdownLegalEntity(String value)
	{
		selectDropdown(DropdownLegalEntity, value);
	}

	public void selectDropdownAuditBasis(String value)
	{
		selectDropdown(DropdownAuditBasis, value);
	}

	public void selectDropdownTypeOfPolicy(String value)
	{
		selectDropdown(DropdownTypeOfPolicy, value);
	}

	public void selectDropdownExternalAuditIndicator(String value)
	{
		selectDropdown(DropdownExternalAuditIndicator, value);
	}

	public void selectDropdownPolicySymbol(String value)
	{
		selectDropdown(DropdownPolicySymbol, value);
	}

	public void EntereditNaicsCode(String value)
	{
		setEdit(editNaicsCode, value);
	}

	public void EntereditBusinessDescription(String value)
	{
		setEdit(editBusinessDescription, value);
	}

	public void EntereditOwnersLegalName(String value)
	{
		setEdit(editOwnersLegalName, value);
	}

	public void EntereditFinancialReliabilityCode(String value)
	{
		setEdit(editFinancialReliabilityCode, value);
	}

	public void EntereditNationalAccountIndicator(String value)
	{
		setEdit(editNationalAccountIndicator, value);
	}

	public void selectDropdownReportingCompanyl(String value)
	{
		selectDropdown(DropdownReportingCompany, value);
	}

	public void toggleFrame()
	{
		clickObject(buttonFrame);
	}
}