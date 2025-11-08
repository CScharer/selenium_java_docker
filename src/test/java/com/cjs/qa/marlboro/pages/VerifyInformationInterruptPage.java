package com.cjs.qa.marlboro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cjs.qa.marlboro.MarlboroEnvironment;
import com.cjs.qa.selenium.Page;

public class VerifyInformationInterruptPage extends Page
{
	public VerifyInformationInterruptPage(WebDriver webDriver)
	{
		super(webDriver);
	}

	final String	URL_VERIFYINFORMATION			= MarlboroEnvironment.URL_BASE
			+ "/marlboro/Security/VerifyInformationInterrupt";
	final By		CheckboxCigarettes				= By.xpath(".//span[.='Cigarettes']/../div/span");
	final By		DropdownRegularBrand			= By.xpath(
			"//div[@data-orgtext][contains(text(),'What is your regular brand of cigarettes, that is, the brand you buy most often?')]/../span/select");
	By				optionRegularBrand				= By
			.xpath(".//div[.='Please tell us about your regular brand.']/..//label[contains(text(),'Non-Menthol')]/..");
	By				DropdownRegularBrandNonMenthol	= By.xpath(
			"//div[@data-orgtext][contains(text(),'What Non-Menthol pack do you buy most often?')]/../span/select");
	final By		buttonNext						= By.xpath(".//*[@id='verifyinfoInterruptnxtBtn']");

	public void CheckboxCigarettesSet(String value)
	{
		setCheckbox(CheckboxCigarettes, value);
	}

	public void DropdownRegularBrandSelect(String value)
	{
		selectDropdown(DropdownRegularBrand, value);
	}

	public void optionRegularBrandSelect(String value)
	{
		final String xPath = ".//div[.='Please tell us about your regular brand.']/..//label[contains(text(),'" + value
				+ "')]/..";
		final By optionRegularBrand = By.xpath(xPath);
		clickObject(optionRegularBrand);
	}

	public void DropdownRegularBrandNonMentholSelect(String value)
	{
		selectDropdown(DropdownRegularBrandNonMenthol, value);
	}

	public void buttonNextClick()
	{
		clickObject(buttonNext);
	}

	public void PopulatePage()
	{
		if (!webDriver.getCurrentUrl().toLowerCase().equals(URL_VERIFYINFORMATION.toLowerCase()))
		{
			return;
		}
		// CheckboxCigarettesSet("checked");
		DropdownRegularBrandSelect("Marlboro");
		optionRegularBrandSelect("Non-Menthol");
		DropdownRegularBrandNonMentholSelect("Marlboro Gold Pack");
		buttonNextClick();
	}
}
