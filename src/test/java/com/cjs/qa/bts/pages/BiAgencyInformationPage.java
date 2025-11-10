package com.cjs.qa.bts.pages;

import com.cjs.qa.selenium.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BiAgencyInformationPage extends Page {
  public BiAgencyInformationPage(WebDriver webDriver) {
    super(webDriver);
  }

  private final By dropdownBranch = By.id("branch");
  private final By dropdownUnderwriter = By.id("underwriter");
  private final By editAgencyCode = By.id("agencyCode");
  private final By editAgencyName = By.id("agencyName");
  private final By buttonSearchAgencyName = By.id("agencyName-lookup");
  private final By editProducerCode = By.id("producerCode");
  private final By dropdownProducerName = By.id("producerName");
  private final By buttonFrame = By.id("agencyInformation-title");
  private final String pageTitle = "BI_AgencyInformationPage";

  private String getPageTitle() {
    return pageTitle;
  }

  public void verifyPage() {
    verifyTitle(getPageTitle());
  }

  public void selectDropdownBranch(String value) {
    selectDropdown(dropdownBranch, value);
  }

  public void selectDropdownUnderwriter(String value) {
    selectDropdown(dropdownUnderwriter, value);
  }

  public void setEditAgencyCode(String value) {
    setEdit(editAgencyCode, value);
  }

  public void setEditAgencyName(String value) {
    setEdit(editAgencyName, value);
  }

  public void selectDropdownProducerName(String value) {
    selectDropdown(dropdownProducerName, value);
  }

  public void clickButtonSearchAgencyNameButton() {
    clickObject(buttonSearchAgencyName);
  }

  public void setEditProducerCode(String value) {
    // N/A Read-Only
    setEdit(editProducerCode, value);
  }

  public void toggleFrame() {
    clickObject(buttonFrame);
  }
}
