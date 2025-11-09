package com.cjs.qa.bts.pages;

import com.cjs.qa.core.Environment;
import com.cjs.qa.selenium.ISelenium;
import com.cjs.qa.selenium.Page;
import io.cucumber.datatable.DataTable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BI_BasicInfoPage extends Page {
  public BI_BasicInfoPage(WebDriver webDriver) {
    super(webDriver);
  }

  private final By labelPolicyNumber = By.xpath("//li[@ng-bind='policy.formattedPolicyNumber']");
  private final By editEffectiveDate = By.id("effectiveDate");
  private final By editExpirationDate = By.id("expirationDate");
  private final By editOriginalEffectiveDate = By.id("originalEffectiveDate");
  private final By DropdownRenewalTerm = By.id("renewalTerm");
  private final By editPriorPolicyNumber = By.id("priorPolicyNumber");
  private final By CheckboxRenewalPolicy = By.id("renewalPolicy");
  private final By editPriorCarrier = By.id("priorCarrier");
  private final By editGuaranteedOn = By.id("guaranteedOn");
  private final By editRatingCompany = By.id("ratingCompany");
  private final By editPolicyDescription = By.id("policyDescription");
  private final By editPrimaryState = By.id("primaryState");
  private final By editCombinedQuote1 = By.id("combinedQuote1");
  private final By editCombinedQuote2 = By.id("combinedQuote2");
  private final By editSubmissionNumber = By.id("submissionNumber");
  private final By editInternalDescription = By.id("policyDescription");
  private final By CheckboxMultiPolicy = By.id("multiPolicy");
  private final By editMultiPolicyDiscount = By.id("multiPolicyDiscount");
  private final By buttonFrame = By.id("basicInformation-title");
  public final String PAGE_TITLE = "BI_BasicInfoPage";

  public void verifyPage() {
    verifyTitle(PAGE_TITLE);
  }

  public String getBasicInformation() {
    // Wait for Processing Policy pop-up window to disappear
    while (objectExists(By.xpath(".//*[contains(text(),'Loading Basic Information')]"))) {
      // Wait for pop-up to disappear
    }
    final List<String> list = getElementsText(By.xpath(".//*[@id='basicInformation']//label"));
    list.addAll(getElementsText(By.xpath(".//*[@id='basicInformation']//input")));
    return list.toString();
  }

  public String getAgencyInformation() {
    final List<String> list = getElementsText(By.xpath(".//*[@id='agencyInformation']//label"));
    list.addAll(getElementsText(By.xpath(".//*[@id='agencyInformation']//select")));
    list.addAll(getElementsText(By.xpath(".//*[@id='agencyInformation']//input")));
    return list.toString();
  }

  public String getMiscellaneousInformation() {
    final List<String> list = getElementsText(By.xpath(".//*[@id='miscInformation']//label"));
    list.addAll(getElementsText(By.xpath(".//*[@id='miscInformation']//input")));
    list.addAll(getElementsText(By.xpath(".//*[@id='miscInformation']//select")));
    return list.toString();
  }

  public String getBillingInformation() {
    final List<String> list = getElementsText(By.xpath(".//*[@id='billingInformation']//label"));
    list.addAll(getElementsText(By.xpath(".//*[@id='billingInformation']//input")));
    list.addAll(getElementsText(By.xpath(".//*[@id='billingInformation']//select")));
    return list.toString();
  }

  public String getUnderwriting() {
    final List<String> list = getElementsText(By.xpath(".//*[@id='underwriting']//label"));
    list.addAll(getElementsText(By.xpath(".//*[@id='underwriting']//input")));
    list.addAll(getElementsText(By.xpath(".//*[@id='underwriting']//select")));
    return list.toString();
  }

  public String getAdvantage() {
    final List<String> list = getElementsText(By.xpath(".//*[@id='advantage']//label"));
    list.addAll(getElementsText(By.xpath(".//*[@id='advantage']//input")));
    list.addAll(getElementsText(By.xpath(".//*[@id='advantage']//select")));
    return list.toString();
  }

  public String getTerrorism() {
    final List<String> list = getElementsText(By.xpath(".//*[@id='terrorism']//label"));
    list.addAll(getElementsText(By.xpath(".//*[@id='terrorism']//select")));
    list.addAll(getElementsText(By.xpath(".//*[@id='terrorism']//input")));
    return list.toString();
  }

  public void verifyeditPriorPolicyNumber(String value) {
    Assert.assertEquals(editPriorPolicyNumber, value);
  }

  public String getPolicyNumber() {
    return getLabel(labelPolicyNumber);
  }

  public void toggleFrame() {
    clickObject(buttonFrame);
  }

  // METHODS GET
  public String getEditEffectiveDate() {
    return getEdit(editEffectiveDate);
  }

  public String getEditExpirationDate() {
    return getEdit(editExpirationDate);
  }

  public String getEditOriginalEffectiveDate() {
    return getEdit(editOriginalEffectiveDate);
  }

  public String getDropdownRenewalTerm() {
    return getDropdown(DropdownRenewalTerm);
  }

  public String getEditPriorPolicyNumber() {
    return getEdit(editPriorPolicyNumber);
  }

  public String getCheckboxRenewalPolicy() {
    return getCheckbox(CheckboxRenewalPolicy);
  }

  public String getEditPriorCarrier() {
    return getEdit(editPriorCarrier);
  }

  public String getEditGuaranteedOn() {
    return getEdit(editGuaranteedOn);
  }

  public String getEditRatingCompany() {
    return getEdit(editRatingCompany);
  }

  public String getEditPolicyDescription() {
    return getEdit(editPolicyDescription);
  }

  public String getEditPrimaryState() {
    return getEdit(editPrimaryState);
  }

  public String getEditCombinedQuote1() {
    return getEdit(editCombinedQuote1);
  }

  public String getEditCombinedQuote2() {
    return getEdit(editCombinedQuote2);
  }

  public String getEditSubmissionNumber() {
    return getEdit(editSubmissionNumber);
  }

  public String getEditInternalDescription() {
    return getEdit(editInternalDescription);
  }

  public String getCheckboxMultiPolicy() {
    return getCheckbox(CheckboxMultiPolicy);
  }

  public String getEditMultiPolicyDiscount() {
    return getEdit(editMultiPolicyDiscount);
  }

  // METHODS SET
  public void setEditEffectiveDate(String value) {
    setEdit(editEffectiveDate, value);
  }

  public void setEditExpirationDate(String value) {
    setEdit(editExpirationDate, value);
  }

  public void setEditOriginalEffectiveDate(String value) {
    setEdit(editOriginalEffectiveDate, value);
  }

  public void selectDropdownRenewalTerm(String value) {
    selectDropdown(DropdownRenewalTerm, value);
  }

  public void setEditPriorPolicyNumber(String value) {
    setEdit(editPriorPolicyNumber, value);
  }

  public void toggleCheckboxRenewalPolicy() {
    if (!getWebElement(CheckboxRenewalPolicy).isSelected()) {
      clickObject(CheckboxRenewalPolicy);
    }
  }

  public void setCheckboxRenewalPolicy(String value) {
    setCheckbox(CheckboxRenewalPolicy, value);
  }

  public void setEditPriorCarrier(String value) {
    setEdit(editPriorCarrier, value);
  }

  public void setEditGuaranteedOn(String value) {
    setEdit(editGuaranteedOn, value);
  }

  public void setEditRatingCompany(String value) {
    setEdit(editRatingCompany, value);
  }

  public void setEditPolicyDescription(String value) {
    setEdit(editPolicyDescription, value);
  }

  public void setEditPrimaryState(String value) {
    setEdit(editPrimaryState, value);
  }

  public void setEditCombinedQuote1(String value) {
    setEdit(editCombinedQuote1, value);
  }

  public void setEditCombinedQuote2(String value) {
    setEdit(editCombinedQuote2, value);
  }

  public void setEditSubmissionNumber(String value) {
    setEdit(editSubmissionNumber, value);
  }

  public void setEditInternalDescription(String value) {
    setEdit(editInternalDescription, value);
  }

  public void toggleCheckboxMultiPolicy() {
    if (!getWebElement(CheckboxMultiPolicy).isSelected()) {
      clickObject(CheckboxMultiPolicy);
    }
  }

  public void setCheckboxMultiPolicy(String value) {
    setCheckbox(CheckboxMultiPolicy, value);
  }

  public void setEditMultiPolicyDiscount(String value) {
    setEdit(editMultiPolicyDiscount, value);
  }

  // SWITCHES POPULATE
  public void populatePage(DataTable table) {
    final List<List<String>> data = table.asLists();
    for (final List<?> item : data) {
      final String field = (String) item.get(0);
      final String value = (String) item.get(1);
      if (!value.equals("")) {
        switch (field.toLowerCase()) {
          case "effective date":
            setEditEffectiveDate(value);
            break;
          case "expiration date":
            setEditExpirationDate(value);
            break;
          case "original effective date":
            setEditOriginalEffectiveDate(value);
            break;
          case "renewal term":
            selectDropdownRenewalTerm(value);
            break;
          case "prior policy number":
            setEditPriorPolicyNumber(value);
            break;
          case "renewal policy":
            setCheckboxRenewalPolicy(value);
            break;
          case "prior carrier":
            setEditPriorCarrier(value);
            break;
          case "guaranteed on":
            setEditGuaranteedOn(value);
            break;
          case "rating company":
            setEditRatingCompany(value);
            break;
          case "policy description":
            setEditPolicyDescription(value);
            break;
          case "primary state":
            setEditPrimaryState(value);
            break;
          case "combined quote1":
            setEditCombinedQuote1(value);
            break;
          case "combined quote2":
            setEditCombinedQuote2(value);
            break;
          case "submission number":
            setEditSubmissionNumber(value);
            break;
          case "internal description":
            setEditInternalDescription(value);
            break;
          case "multi policy":
            setCheckboxMultiPolicy(value);
            break;
          case "multi policy discount":
            setEditMultiPolicyDiscount(value);
            break;
          default:
            Environment.sysOut("[" + field + "]" + ISelenium.FIELD_NOT_CODED);
            break;
        }
      }
    }
  }

  // SWITCHES VALIDATE
  public void validatePage(DataTable table) {
    final Map<String, String> expected = new HashMap<>();
    final Map<String, String> actual = new HashMap<>();
    final List<List<String>> data = table.asLists();
    for (final List<?> item : data) {
      final String field = (String) item.get(0);
      String value = (String) item.get(1);
      if (!value.equals("")) {
        if (Environment.isLogAll()) {
          Environment.sysOut("({Field}" + field + ", {Value}" + value + ");");
        }
        expected.put(field, value);
        switch (field.toLowerCase()) {
          case "effective date":
            value = getEditEffectiveDate();
            break;
          case "expiration date":
            value = getEditExpirationDate();
            break;
          case "original effective date":
            value = getEditOriginalEffectiveDate();
            break;
          case "renewal term":
            value = getDropdownRenewalTerm();
            break;
          case "prior policy number":
            value = getEditPriorPolicyNumber();
            break;
          case "renewal policy":
            value = getCheckboxRenewalPolicy();
            break;
          case "prior carrier":
            value = getEditPriorCarrier();
            break;
          case "guaranteed on":
            value = getEditGuaranteedOn();
            break;
          case "rating company":
            value = getEditRatingCompany();
            break;
          case "policy description":
            value = getEditPolicyDescription();
            break;
          case "primary state":
            value = getEditPrimaryState();
            break;
          case "combined quote1":
            value = getEditCombinedQuote1();
            break;
          case "combined quote2":
            value = getEditCombinedQuote2();
            break;
          case "submission number":
            value = getEditSubmissionNumber();
            break;
          case "internal description":
            value = getEditInternalDescription();
            break;
          case "multi policy":
            value = getCheckboxMultiPolicy();
            break;
          case "multi policy discount":
            value = getEditMultiPolicyDiscount();
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
