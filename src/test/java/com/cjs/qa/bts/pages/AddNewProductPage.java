package com.cjs.qa.bts.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cjs.qa.core.Environment;
import com.cjs.qa.selenium.ISelenium;
import com.cjs.qa.selenium.Page;

import io.cucumber.datatable.DataTable;

public class AddNewProductPage extends Page
{
	public AddNewProductPage(WebDriver webDriver)
	{
		super(webDriver);
	}

	// DECLARATIONS
	private final By	DropdownProduct				= By.id("productId");
	private final By	DropdownProgram				= By.id("programId");
	private final By	DropdownRatingCompany		= By
			.id(".//*[@id='addProductForm']/div[3]/div[1]/div[2]/div[1]/div[4]/div[1]/div/select");
	private final By	DropdownState				= By.id("stateCode");
	private final By	buttonAdd					= By.id("");
	private final By	buttonAddProductCoverages	= By.id("");
	public final String	PAGE_TITLE					= "AddNewProductPage";

	public void verifyPage()
	{
		verifyTitle(PAGE_TITLE);
	}

	// METHODS GET
	public String getDropdownProduct()
	{
		return getDropdown(DropdownProduct);
	}

	public String getDropdownProgram()
	{
		return getDropdown(DropdownProgram);
	}

	public String getDropdownRatingCompany()
	{
		return getDropdown(DropdownRatingCompany);
	}

	public String getDropdownState()
	{
		return getDropdown(DropdownState);
	}

	// METHODS SET
	public void selectDropdownProduct(String value)
	{
		selectDropdown(DropdownProduct, value);
	}

	public void selectDropdownProgram(String value)
	{
		selectDropdown(DropdownProgram, value);
	}

	public void selectDropdownRatingCompany(String value)
	{
		selectDropdown(DropdownRatingCompany, value);
	}

	public void selectDropdownState(String value)
	{
		selectDropdown(DropdownState, value);
	}

	public void clickButtonAdd(String value)
	{
		clickObject(buttonAdd);
	}

	public void clickButtonAddProductCoverages(String value)
	{
		clickObject(buttonAddProductCoverages);
	}

	// SWITCHES POPULATE
	public void populatePage(DataTable table)
	{
		final List<List<String>> data = table.asLists();
		for (final List<?> item : data)
		{
			final String field = (String) item.get(0);
			final String value = (String) item.get(1);
			if (!value.equals(""))
			{
				if (Environment.isLogAll())
				{
					Environment.sysOut("({Field}" + field + ", {Value}" + value + ");");
				}
				switch (field.toLowerCase())
				{
					case "Product":
						selectDropdownProduct(value);
						break;
					case "Program":
						selectDropdownProgram(value);
						break;
					case "Rating Company":
						selectDropdownRatingCompany(value);
						break;
					case "State":
						selectDropdownState(value);
						break;
					default:
						Environment.sysOut("[" + field + "]" + ISelenium.FIELD_NOT_CODED);
						break;
				}
			}
		}
	}

	// SWITCHES VALIDATE
	public void validatePage(DataTable table)
	{
		final Map<String, String> expected = new HashMap<>();
		final Map<String, String> actual = new HashMap<>();
		final List<List<String>> data = table.asLists();
		for (final List<?> item : data)
		{
			final String field = (String) item.get(0);
			String value = (String) item.get(1);
			if (!value.equals(""))
			{
				expected.put(field, value);
				switch (field.toLowerCase())
				{
					case "Product":
						value = getDropdownProduct();
						break;
					case "Program":
						value = getDropdownProgram();
						break;
					case "Rating Company":
						value = getDropdownRatingCompany();
						break;
					case "State":
						value = getDropdownState();
						break;
					default:
						value = "[" + field + "]" + ISelenium.FIELD_NOT_CODED;
						Environment.sysOut(value);
						break;
				}
				actual.put(field, value);
			}
		}
		Assert.assertSame(PAGE_TITLE + " validatePage", expected, actual);
	}
}