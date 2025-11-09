package com.cjs.qa.bts.pages;

import com.cjs.qa.selenium.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BI_AgencyInformationPage extends Page {
  public BI_AgencyInformationPage(WebDriver webDriver) {
    super(webDriver);
  }

  private final By DropdownBranch = By.id("branch");
  private final By DropdownUnderwriter = By.id("underwriter");
  private final By editAgencyCode = By.id("agencyCode");
  private final By editAgencyName = By.id("agencyName");
  private final By buttonSearchAgencyName = By.id("agencyName-lookup");
  private final By editProducerCode = By.id("producerCode");
  private final By DropdownProducerName = By.id("producerName");
  private final By buttonFrame = By.id("agencyInformation-title");
  public final String PAGE_TITLE = "BI_AgencyInformationPage";

  public void verifyPage() {
    verifyTitle(PAGE_TITLE);
  }

  public void selectDropdownBranch(String value) {
    selectDropdown(DropdownBranch, value);
  }

  public void selectDropdownUnderwriter(String value) {
    selectDropdown(DropdownUnderwriter, value);
  }

  public void setEditAgencyCode(String value) {
    setEdit(editAgencyCode, value);
  }

  public void setEditAgencyName(String value) {
    setEdit(editAgencyName, value);
  }

  public void selectDropdownProducerName(String value) {
    selectDropdown(DropdownProducerName, value);
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
