package com.cjs.qa.bts.pages;

import com.cjs.qa.selenium.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BiBillingInformationPage extends Page {
  public BiBillingInformationPage(WebDriver webDriver) {
    super(webDriver);
  }

  private final By editBillingAccountNumber = By.id("billingAccountNumber");
  private final By dropdownBillType = By.id("billType");
  private final By dropdownPayPlan = By.id("payPlan");
  private final By dropdownAmendmentDistribution = By.id("amendmentDistribution");
  private final By editDepositAmount = By.id("depositAmount");
  private final By dropdownDepositType = By.id("depositType");
  private final By editDepositPercent = By.id("depositPercent");
  private final By buttonFrame = By.id("billingInformation-title");
  private final String pageTitle = "BI_BillingInformationPage";

  private String getPageTitle() {
    return pageTitle;
  }

  public void verifyPage() {
    verifyTitle(getPageTitle());
  }

  public void setEditBillingAccountNumber(String value) {
    setEdit(editBillingAccountNumber, value);
  }

  public void selectDropdownBillType(String value) {
    selectDropdown(dropdownBillType, value);
  }

  public void selectDropdownPayPlan(String value) {
    selectDropdown(dropdownPayPlan, value);
  }

  public void selectDropdownAmendmentDistribution(String value) {
    selectDropdown(dropdownAmendmentDistribution, value);
  }

  public void setEditDepositAmount(String value) {
    setEdit(editDepositAmount, value);
  }

  public void selectDropdownDepositType(String value) {
    selectDropdown(dropdownDepositType, value);
  }

  public void setEditDepositPercent(String value) {
    setEdit(editDepositPercent, value);
  }

  public void toggleFrame() {
    clickObject(buttonFrame);
  }
}
