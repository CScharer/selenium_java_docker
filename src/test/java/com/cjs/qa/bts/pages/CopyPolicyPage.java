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
import org.openqa.selenium.WebElement;

public class CopyPolicyPage extends Page {
  public CopyPolicyPage(WebDriver webDriver) {
    super(webDriver);
  }

  private final String NODE_COPYPOLICY = "form1:copyPolicy:";
  private final By editEffectiveDate = By.id(NODE_COPYPOLICY + "effectiveDtBI");
  // private By DropdownEffectiveDate = By.id(NODE_COPYPOLICY + "termBI");
  private final By DropdownTerm = By.id(NODE_COPYPOLICY + "termBI");
  private final By DropdownRenewalTerm = By.id(NODE_COPYPOLICY + "renewalTermBI");
  private final By editExpirationDate = By.id(NODE_COPYPOLICY + "expirationDtBI");
  private final By editReleaseDate = By.id(NODE_COPYPOLICY + "releaseDtBI");
  private final By DropdownCorporation = By.id(NODE_COPYPOLICY + "corporationBI");
  private final By DropdownCompany = By.id(NODE_COPYPOLICY + "policyCompanyBI");
  private final By DropdownPolicyType = By.id(NODE_COPYPOLICY + "policyProductBI");
  private final By DropdownProgram = By.id(NODE_COPYPOLICY + "policyProgramBI");
  private final By DropdownPolicyState = By.id(NODE_COPYPOLICY + "policyStateBI");
  private final By CheckboxRenewalPolicy = By.id(NODE_COPYPOLICY + "targetRenewalPolicy");
  private final By CheckboxUseCurrentRateRules = By.id(NODE_COPYPOLICY + "currentRules");
  private final By CheckboxRetainBaseNumber = By.id(NODE_COPYPOLICY + "retainBaseNbrBI");
  private final By CheckboxUserEnteredPolicyNumber = By.id(NODE_COPYPOLICY + "userEnteredPolNbrBI");
  private final By editCopyFromPolicyNumber = By.id(NODE_COPYPOLICY + "sourcePolicyNbrId");
  private final By editCopyToPolicyNumber = By.id(NODE_COPYPOLICY + "targetPolicyNbrId");
  // private By ExistingProductsInformationCheckbox(){}
  private final By buttonDelete = By.id(NODE_COPYPOLICY + "DelBtn");
  private final By buttonCopy = By.id(NODE_COPYPOLICY + "copyBtn");
  private final By buttonCancel = By.id(NODE_COPYPOLICY + "cancelBtn");
  private final By buttonCopySuccess = By.xpath("*//button[@type='button']//span[.='OK']");
  private final By buttonPendingChangesOK = By.xpath(".//*/span[.='OK']");
  public final String PAGE_TITLE = "CopyPolicyPage";

  public void verifyPage() {
    verifyTitle(PAGE_TITLE);
  }

  // METHODS GET
  public String getEditEffectiveDate() {
    return getEdit(editEffectiveDate);
  }

  public String getDropdownTerm() {
    return getDropdown(DropdownTerm);
  }

  public String getDropdownRenewalTerm() {
    return getDropdown(DropdownRenewalTerm);
  }

  public String getEditExpirationDate() {
    return getEdit(editExpirationDate);
  }

  public String getEditReleaseDate() {
    return getEdit(editReleaseDate);
  }

  public String getDropdownCorporation() {
    return getDropdown(DropdownCorporation);
  }

  public String getDropdownCompany() {
    return getDropdown(DropdownCompany);
  }

  public String getDropdownPolicyType() {
    return getDropdown(DropdownPolicyType);
  }

  public String getDropdownProgram() {
    return getDropdown(DropdownProgram);
  }

  public String getDropdownPolicyState() {
    return getDropdown(DropdownPolicyState);
  }

  public String getCheckboxRenewalPolicy() {
    return getCheckbox(CheckboxRenewalPolicy);
  }

  public String getCheckboxUseCurrentRateRules() {
    return getCheckbox(CheckboxUseCurrentRateRules);
  }

  public String getCheckboxRetainBaseNumber() {
    return getCheckbox(CheckboxRetainBaseNumber);
  }

  public String getCheckboxUserEnteredPolicyNumber() {
    return getCheckbox(CheckboxUserEnteredPolicyNumber);
  }

  public String getEditCopyFromPolicyNumber() {
    return getEdit(editCopyFromPolicyNumber);
  }

  public String getEditCopyToPolicyNumber() {
    return getEdit(editCopyToPolicyNumber);
  }

  // METHODS SET
  public void setEditEffectiveDate(String value) {
    setEdit(editEffectiveDate, value);
  }

  public void selectDropdownTerm(String value) {
    selectDropdown(DropdownTerm, value);
  }

  public void selectDropdownRenewalTerm(String value) {
    selectDropdown(DropdownRenewalTerm, value);
  }

  public void setEditExpirationDate(String value) {
    setEdit(editExpirationDate, value);
  }

  public void setEditReleaseDate(String value) {
    setEdit(editReleaseDate, value);
  }

  public void selectDropdownCorporation(String value) {
    selectDropdown(DropdownCorporation, value);
  }

  public void selectDropdownCompany(String value) {
    selectDropdown(DropdownCompany, value);
  }

  public void selectDropdownPolicyType(String value) {
    selectDropdown(DropdownPolicyType, value);
  }

  public void selectDropdownProgram(String value) {
    selectDropdown(DropdownProgram, value);
  }

  public void selectDropdownPolicyState(String value) {
    selectDropdown(DropdownPolicyState, value);
  }

  public void toggleCheckboxRenewalPolicy() {
    toggleCheckbox(CheckboxRenewalPolicy);
  }

  public void setCheckboxRenewalPolicy(String value) {
    setCheckbox(CheckboxRenewalPolicy, value);
  }

  public void toggleCheckboxUseCurrentRateRules() {
    toggleCheckbox(CheckboxUseCurrentRateRules);
  }

  public void setCheckboxUseCurrentRateRules(String value) {
    setCheckbox(CheckboxUseCurrentRateRules, value);
  }

  public void toggleCheckboxRetainBaseNumber() {
    toggleCheckbox(CheckboxRetainBaseNumber);
  }

  public void setCheckboxRetainBaseNumber(String value) {
    setCheckbox(CheckboxRetainBaseNumber, value);
  }

  public void toggleCheckboxUserEnteredPolicyNumber() {
    toggleCheckbox(CheckboxUserEnteredPolicyNumber);
  }

  public void setCheckboxUserEnteredPolicyNumber(String value) {
    setCheckbox(CheckboxUserEnteredPolicyNumber, value);
  }

  public void setEditCopyFromPolicyNumber(String value) {
    setEdit(editCopyFromPolicyNumber, value);
  }

  public void setEditCopyToPolicyNumber(String value) {
    setEdit(editCopyToPolicyNumber, value);
  }

  public void verifyFromPolicyAndToPolicyAreCorrect() {
    final String copyFromPolicyNum = getEdit(editCopyFromPolicyNumber);
    final String copyToPolicyNum = getEdit(editCopyToPolicyNumber);
    if (getWebElement(CheckboxRetainBaseNumber).isSelected()) {
      Assert.assertEquals(copyFromPolicyNum, copyToPolicyNum);
    } else {
      Assert.assertNotSame(copyFromPolicyNum, copyToPolicyNum);
    }
  }

  public void setCheckboxesExistingProducts() {
    // final List<WebElement> elements =
    // getWebElements(By.xpath(".//input[@type='Checkbox' and
    // starts-with(@id,'form1:copyPolicy:table1')]"));
    final List<WebElement> elements =
        getWebElements(
            By.xpath(".//input[@type='Checkbox'][starts-with(@id,'form1:copyPolicy:table1')]"));
    setCheckboxes(elements, "checked");
  }

  public void clickButtonDelete() {
    clickObject(buttonDelete);
  }

  public void clickButtonCopy() {
    clickObject(buttonCopy);
  }

  public void clickButtonCopySuccess() {
    /// html/body/div[contains(@class='ui-dialog-buttons')]
    clickObjectPopup(buttonCopySuccess);
    clickObjectPopup(buttonPendingChangesOK);
  }

  public void clickButtonCancel() {
    clickObject(buttonCancel);
  }

  // SWITCHES POPULATE
  public void populatePage(DataTable table) {
    final List<List<String>> data = table.asLists();
    for (final List<?> item : data) {
      final String field = (String) item.get(0);
      final String value = (String) item.get(1);
      if (!value.equals("")) {
        if (Environment.isLogAll()) {
          Environment.sysOut("({Field}" + field + ", {Value}" + value + ");");
        }
        switch (field.toLowerCase()) {
          case "effective date":
            setEditEffectiveDate(value);
            break;
          case "term":
            selectDropdownTerm(value);
            break;
          case "renewal term":
            selectDropdownRenewalTerm(value);
            break;
          case "expiration date":
            setEditExpirationDate(value);
            break;
          case "release date":
            setEditReleaseDate(value);
            break;
          case "corporation":
            selectDropdownCorporation(value);
            break;
          case "company":
            selectDropdownCompany(value);
            break;
          case "policy type":
            selectDropdownPolicyType(value);
            break;
          case "program":
            selectDropdownProgram(value);
            break;
          case "policy state":
            selectDropdownPolicyState(value);
            break;
          case "renewal policy":
            setCheckboxRenewalPolicy(value);
            break;
          case "use current rate/rules":
            setCheckboxUseCurrentRateRules(value);
            break;
          case "retain base number":
            setCheckboxRetainBaseNumber(value);
            break;
          case "user entered policy#":
            setCheckboxUserEnteredPolicyNumber(value);
            break;
          case "copy from policy#":
            setEditCopyFromPolicyNumber(value);
            break;
          case "copy to policy#":
            setEditCopyToPolicyNumber(value);
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
        expected.put(field, value);
        switch (field.toLowerCase()) {
          case "effective date":
            value = getEditEffectiveDate();
            break;
          case "term":
            value = getDropdownTerm();
            break;
          case "renewal term":
            value = getDropdownRenewalTerm();
            break;
          case "expiration date":
            value = getEditExpirationDate();
            break;
          case "release date":
            value = getEditReleaseDate();
            break;
          case "corporation":
            value = getDropdownCorporation();
            break;
          case "company":
            value = getDropdownCompany();
            break;
          case "policy type":
            value = getDropdownPolicyType();
            break;
          case "program":
            value = getDropdownProgram();
            break;
          case "policy state":
            value = getDropdownPolicyState();
            break;
          case "renewal policy":
            value = getCheckboxRenewalPolicy();
            break;
          case "use current rate/rules":
            value = getCheckboxUseCurrentRateRules();
            break;
          case "retain base number":
            value = getCheckboxRetainBaseNumber();
            break;
          case "user entered policy#":
            value = getCheckboxUserEnteredPolicyNumber();
            break;
          case "copy from policy#":
            value = getEditCopyFromPolicyNumber();
            break;
          case "copy to policy#":
            value = getEditCopyToPolicyNumber();
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
