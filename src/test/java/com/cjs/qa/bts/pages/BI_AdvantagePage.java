package com.cjs.qa.bts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cjs.qa.selenium.Page;

public class BI_AdvantagePage extends Page
{
	public BI_AdvantagePage(WebDriver webDriver)
	{
		super(webDriver);
	}

	private final By	editYearBusinessStarted				= By.id("YR_BUS_STRT");
	private final By	editYearAgencyObtainClient			= By.id("YR_CLT_OBTND");
	private final By	CheckboxCurrentAutoCoverage			= By.id("INSD_AUTO_CVG");
	private final By	CheckboxCurrentWorkCompCoverage		= By.id("INSD_WC_CVG");
	private final By	CheckboxCurrentUmbrellaCoverage		= By.id("INSD_UMB_CVG");
	private final By	CheckboxCurrentOtherCoverage		= By.id("INSD_OTH_CVG");
	private final By	editDescribeOtherCoverage			= By.id("DESC_OTH_CVG");
	private final By	DropdownApplicantPartOfFranchise	= By.id("APL_FRN_IND");
	private final By	DropdownFormalSafetyProgramInPlace	= By.id("FRML_SFTY_PGM_IND");
	private final By	editNumberOfLossesGL				= By.id("NUM_LOSE_GL");
	private final By	editNumberOfLossesProperty			= By.id("NUM_LOSE_PROP");
	private final By	editNumberOfEmployees				= By.id("NUM_EMP");
	private final By	buttonFrame							= By.id("advantage-title");
	public final String	PAGE_TITLE							= "BI_AdvantagePage";

	public void verifyPage()
	{
		verifyTitle(PAGE_TITLE);
	}

	public void setEditYearBusinessStarted(String value)
	{
		setEdit(editYearBusinessStarted, value);
	}

	public void setEditYearAgencyObtainClient(String value)
	{
		setEdit(editYearAgencyObtainClient, value);
	}

	public void toggleCheckboxCurrentAutoCoverage()
	{
		clickObject(CheckboxCurrentAutoCoverage);
	}

	public void toggleCheckboxCurrentWorkCompCoverage()
	{
		clickObject(CheckboxCurrentWorkCompCoverage);
	}

	public void toggleCheckboxCurrentUmbrellaCoverage()
	{
		clickObject(CheckboxCurrentUmbrellaCoverage);
	}

	public void toggleCheckboxCurrentOtherCoverage()
	{
		clickObject(CheckboxCurrentOtherCoverage);
	}

	public void setEditDescribeOtherCoverage(String value)
	{
		setEdit(editDescribeOtherCoverage, value);
	}

	public void selectDropdownApplicantPartOfFranchise(String value)
	{
		selectDropdown(DropdownApplicantPartOfFranchise, value);
	}

	public void selectDropdownFormalSafetyProgramInPlace(String value)
	{
		selectDropdown(DropdownFormalSafetyProgramInPlace, value);
	}

	public void setEditNumberOfLossesGL(String value)
	{
		setEdit(editNumberOfLossesGL, value);
	}

	public void setEditNumberOfLossesProperty(String value)
	{
		setEdit(editNumberOfLossesProperty, value);
	}

	public void setEditNumberOfEmployees(String value)
	{
		setEdit(editNumberOfEmployees, value);
	}

	public void toggleFrame()
	{
		clickObject(buttonFrame);
	}
}