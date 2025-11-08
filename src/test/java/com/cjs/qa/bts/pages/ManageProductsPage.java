package com.cjs.qa.bts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cjs.qa.selenium.Page;

public class ManageProductsPage extends Page
{
	public ManageProductsPage(WebDriver webDriver)
	{
		super(webDriver);
	}

	private final By	buttonDeleteProductList					= By.id("deleteButton");
	private final By	buttonAddNewProductAdd					= By.id("addProductBtn");
	private final By	buttonAddNewProductAddProductCoveragegs	= By.id("addProductCvgBtn");
	private final By	DropdownProduct							= By.id("productId");
	private final By	DropdownProgram							= By.id("programId");
	private final By	DropdownRatingCompany					= By.id("addProductForm");
	private final By	DropdownState							= By.id("stateCode");
	public final String	PAGE_TITLE								= "ManageProductsPage";

	public void verifyPage()
	{
		verifyTitle(PAGE_TITLE);
	}

	public void clickButtonDeleteProductList()
	{
		clickObject(buttonDeleteProductList);
	}

	public void clickButtonAddNewProductAdd()
	{
		clickObject(buttonAddNewProductAdd);
	}

	public void clickButtonAddNewProductAddProductCoveragegs()
	{
		clickObject(buttonAddNewProductAddProductCoveragegs);
	}

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
}