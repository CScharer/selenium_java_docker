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

public class NamedInsuredsPage extends Page
{
	public NamedInsuredsPage(WebDriver webDriver)
	{
		super(webDriver);
	}

	// DECLARATIONS
	private final String	NODE_FORM1				= "form1:";
	private final By		buttonAdd				= By.id(NODE_FORM1 + "addInsIntBtn");
	private final By		buttonDelete			= By.id(NODE_FORM1 + "deleteNamedInsBtn");
	private final By		buttonCreateNewNameSeq	= By.id(NODE_FORM1 + "crteNameSeqButton");
	private final By		buttonAddExistNameSeq	= By.id(NODE_FORM1 + "addExistNSeqButton");
	public final String		PAGE_TITLE				= "NamedInsuredsPage";

	public void verifyPage()
	{
		verifyTitle(PAGE_TITLE);
	}

	// METHODS SET
	public void clickButtonAdd(String value)
	{
		clickObject(buttonAdd);
	}

	public void clickButtonDelete(String value)
	{
		clickObject(buttonDelete);
	}

	public void clickButtonCreateNewNameSeq(String value)
	{
		clickObject(buttonCreateNewNameSeq);
	}

	public void clickButtonAddExistNameSeq(String value)
	{
		clickObject(buttonAddExistNameSeq);
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
					case "Add":
						clickButtonAdd(value);
						break;
					case "Delete":
						clickButtonDelete(value);
						break;
					case "Create New Name Seq":
						clickButtonCreateNewNameSeq(value);
						break;
					case "Add Exist Name Seq":
						clickButtonAddExistNameSeq(value);
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
					case "":
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