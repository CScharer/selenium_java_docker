package com.cjs.qa.bts.pages;

import com.cjs.qa.selenium.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManageProductsPage extends Page {
  public ManageProductsPage(WebDriver webDriver) {
    super(webDriver);
  }

  private final By buttonDeleteProductList = By.id("deleteButton");
  private final By buttonAddNewProductAdd = By.id("addProductBtn");
  private final By buttonAddNewProductAddProductCoveragegs = By.id("addProductCvgBtn");
  private final By dropdownProduct = By.id("productId");
  private final By dropdownProgram = By.id("programId");
  private final By dropdownRatingCompany = By.id("addProductForm");
  private final By dropdownState = By.id("stateCode");
  private static final String pageTitle = "ManageProductsPage";

  private String getPageTitle() {
    return pageTitle;
  }

  public void verifyPage() {
    verifyTitle(getPageTitle());
  }

  public void clickButtonDeleteProductList() {
    clickObject(buttonDeleteProductList);
  }

  public void clickButtonAddNewProductAdd() {
    clickObject(buttonAddNewProductAdd);
  }

  public void clickButtonAddNewProductAddProductCoveragegs() {
    clickObject(buttonAddNewProductAddProductCoveragegs);
  }

  public void selectDropdownProduct(String value) {
    selectDropdown(dropdownProduct, value);
  }

  public void selectDropdownProgram(String value) {
    selectDropdown(dropdownProgram, value);
  }

  public void selectDropdownRatingCompany(String value) {
    selectDropdown(dropdownRatingCompany, value);
  }

  public void selectDropdownState(String value) {
    selectDropdown(dropdownState, value);
  }
}
