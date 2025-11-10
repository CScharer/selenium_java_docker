package com.cjs.qa.bts.pages;

import com.cjs.qa.selenium.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BiMiscellaneousInformationPage extends Page {
  public BiMiscellaneousInformationPage(WebDriver webDriver) {
    super(webDriver);
  }

  private final By checkboxPackageModification = By.id("packageModification");
  private final By checkboxAuditable = By.id("auditable");
  private final By dropdownLegalEntity = By.id("legalEntity");
  private final By dropdownAuditBasis = By.id("auditBasis");
  private final By dropdownTypeOfPolicy = By.id("typeOfPolicy");
  private final By dropdownExternalAuditIndicator = By.id("externalAuditIndicator");
  private final By dropdownPolicySymbol = By.id("policySymbol");
  private final By editNaicsCode = By.id("naicsCode");
  private final By editBusinessDescription = By.id("businessDescription");
  private final By editOwnersLegalName = By.id("ownersLegalName");
  private final By editFinancialReliabilityCode = By.id("financialReliabilityCode");
  private final By editNationalAccountIndicator = By.id("nationalAccountIndicator");
  private final By dropdownReportingCompany = By.id("reportingCompany");
  private final By buttonFrame = By.id("miscInformation-title");
  private final String pageTitle = "BI_MiscellaneousInformationPage";

  private String getPageTitle() {
    return pageTitle;
  }

  public void verifyPage() {
    verifyTitle(getPageTitle());
  }

  public void toggleCheckboxPackageModification() {
    if (!getWebElement(checkboxPackageModification).isSelected()) {
      clickObject(checkboxPackageModification);
    }
  }

  public void toggleCheckboxAuditable() {
    if (!getWebElement(checkboxAuditable).isSelected()) {
      clickObject(checkboxAuditable);
    }
  }

  public void selectDropdownLegalEntity(String value) {
    selectDropdown(dropdownLegalEntity, value);
  }

  public void selectDropdownAuditBasis(String value) {
    selectDropdown(dropdownAuditBasis, value);
  }

  public void selectDropdownTypeOfPolicy(String value) {
    selectDropdown(dropdownTypeOfPolicy, value);
  }

  public void selectDropdownExternalAuditIndicator(String value) {
    selectDropdown(dropdownExternalAuditIndicator, value);
  }

  public void selectDropdownPolicySymbol(String value) {
    selectDropdown(dropdownPolicySymbol, value);
  }

  public void enterEditNaicsCode(String value) {
    setEdit(editNaicsCode, value);
  }

  public void enterEditBusinessDescription(String value) {
    setEdit(editBusinessDescription, value);
  }

  public void enterEditOwnersLegalName(String value) {
    setEdit(editOwnersLegalName, value);
  }

  public void enterEditFinancialReliabilityCode(String value) {
    setEdit(editFinancialReliabilityCode, value);
  }

  public void enterEditNationalAccountIndicator(String value) {
    setEdit(editNationalAccountIndicator, value);
  }

  public void selectDropdownReportingCompanyl(String value) {
    selectDropdown(dropdownReportingCompany, value);
  }

  public void toggleFrame() {
    clickObject(buttonFrame);
  }
}
