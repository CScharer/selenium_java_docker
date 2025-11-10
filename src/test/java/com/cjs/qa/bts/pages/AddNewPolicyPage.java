package com.cjs.qa.bts.pages;

import com.cjs.qa.selenium.Page;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddNewPolicyPage extends Page {
  public AddNewPolicyPage(WebDriver webDriver) {
    super(webDriver);
  }

  private final String nodePolicyDetails = "form1:policy:";
  // @FindBy(how = How.ID, using = "usernameT") private WebElement
  // editUsername;
  // @FindBy(id = nodePolicyDetails + "renewalPolicyBI") private By
  // checkboxRenewalPolicy;
  private final By checkboxRenewalPolicy = By.id(nodePolicyDetails + "renewalPolicyBI");
  private final By editEffectiveDate = By.id(nodePolicyDetails + "effectiveDtBI");
  private final By dropdownTerm = By.id(nodePolicyDetails + "termBI");
  private final By dropdownRenewalTerm = By.id(nodePolicyDetails + "renewalTermBI");
  private final By editExpirationDate = By.id(nodePolicyDetails + "expirationDtBI");
  private final By dropdownRatingCompany = By.id(nodePolicyDetails + "policyCompanyBI");
  private final By dropdownIssuingCompany = By.id(nodePolicyDetails + "policyProductBI");
  private final By dropdownProgram = By.id(nodePolicyDetails + "policyProgramBI");
  private final By dropdownPrimaryState = By.id(nodePolicyDetails + "policyStateBI");
  private final By editReleaseDate = By.id(nodePolicyDetails + "releaseDtBI");
  private final By checkboxUserEnteredPolicyNum = By.id(nodePolicyDetails + "userEnteredPolicyBI");
  private final By editPolicyNum = By.id(nodePolicyDetails + "policyNbrBI");
  private final By buttonSave = By.id(nodePolicyDetails + "button1");
  private final By buttonCancel = By.id(nodePolicyDetails + "button2");
  private final By clickOffDate = By.id(nodePolicyDetails + "buildPolicyGrp");
  private final By editPolicyNumSeq = By.id(nodePolicyDetails + "policyNbrSeqBI");
  private final String pageTitle = "AddNewPolicyPage";

  private String getPageTitle() {
    return pageTitle;
  }

  public void verifyPage() {
    verifyTitle(getPageTitle());
  }

  public void toggleCheckboxRenewalPolicy() {
    clickObject(checkboxRenewalPolicy);
    clickObject(clickOffDate);
  }

  public void setEditEffectiveDate(String value) {
    setEdit(editEffectiveDate, value);
    clickObject(clickOffDate);
  }

  public void selectTerm(String value) {
    selectDropdown(dropdownTerm, value);
    clickObject(clickOffDate);
  }

  public void selectRenewalTerm(String value) {
    selectDropdown(dropdownRenewalTerm, value);
    clickObject(clickOffDate);
  }

  public void setEditExpirationDate(String value) {
    setEdit(editExpirationDate, value);
    clickObject(clickOffDate);
  }

  public void selectRatingCompany(String value) {
    selectDropdown(dropdownRatingCompany, value);
    clickObject(clickOffDate);
  }

  public void selectIssuingCompany(String value) {
    selectDropdown(dropdownIssuingCompany, value);
    clickObject(clickOffDate);
  }

  public void selectProgram(String value) {
    selectDropdown(dropdownProgram, value);
    clickObject(clickOffDate);
  }

  public void selectPrimaryState(String value) {
    selectDropdown(dropdownPrimaryState, value);
    clickObject(clickOffDate);
  }

  public void setReleaseDate(String value) {
    setEdit(editReleaseDate, value);
    clickObject(clickOffDate);
  }

  public void toggleCheckboxUserEnteredPolicyNum() {
    clickObject(checkboxUserEnteredPolicyNum);
    clickObject(clickOffDate);
  }

  public void enterPolicyNum(String policyNum, String policyNumSeq) {
    if ("checked".equals(getCheckbox(checkboxUserEnteredPolicyNum))) {
      setEdit(editPolicyNum, policyNum);
      clickObject(clickOffDate);
      setEdit(editPolicyNumSeq, policyNumSeq);
      clickObject(clickOffDate);
    }
  }

  public void verifyPolicyNum(String policyNum, String policyNumSeq) {
    final String shownPolicyNum = getEdit(editPolicyNum);
    final String shownPolicyNumSeq = getEdit(editPolicyNumSeq);
    Assert.assertEquals(shownPolicyNumSeq, policyNumSeq);
    Assert.assertEquals(shownPolicyNum, policyNum);
    clickObject(clickOffDate);
  }

  public void clickButtonSave() {
    clickObject(buttonSave);
  }

  public void clickButtonCancel() {
    clickObject(buttonCancel);
  }
}
