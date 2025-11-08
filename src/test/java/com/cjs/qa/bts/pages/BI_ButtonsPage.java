package com.cjs.qa.bts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cjs.qa.selenium.Page;

public class BI_ButtonsPage extends Page
{
	public BI_ButtonsPage(WebDriver webDriver)
	{
		super(webDriver);
	}

	private final By	buttonSave				= By.id("save");
	private final By	buttonSaveProducts		= By.id("saveProducts");
	private final By	buttonCancel			= By.id("cancel");
	private final By	buttonQuickScroll		= By.id("quickScroll");
	private final By	buttonOpenCloseLeftNav	= By.xpath("html/body/div[1]/nav/div/div[1]/div[1]/button");
	public final String	PAGE_TITLE				= "BI_ButtonsPage";

	public void verifyPage()
	{
		verifyTitle(PAGE_TITLE);
	}

	public void clickButtonSave()
	{
		clickObject(buttonSave);
	}

	public void clickButtonSaveProducts()
	{
		clickObject(buttonSaveProducts);
	}

	public void clickButtonCancel()
	{
		clickObject(buttonCancel);
	}

	public void clickButtonQuickScroll()
	{
		clickObject(buttonQuickScroll);
	}

	public void clickButtonOpenCloseLeftNav()
	{
		clickObject(buttonOpenCloseLeftNav);
	}
}