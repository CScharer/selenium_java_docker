package com.cjs.qa.bts.pages;

import com.cjs.qa.selenium.Page;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddNewPolicyPage extends Page {
  public AddNewPolicyPage(WebDriver webDriver) {
    super(webDriver);
  }

  private final String NODE_POLICY = "form1:policy:";
  // @FindBy(how = How.ID, using = "usernameT") private WebElement
  // editUsername;
  // @FindBy(id = NODE_POLICY + "renewalPolicyBI") private By
  // CheckboxRenewalPolicy;
  private final By CheckboxRenewalPolicy = By.id(NODE_POLICY + "renewalPolicyBI");
  private final By editEffectiveDate = By.id(NODE_POLICY + "effectiveDtBI");
  private final By DropdownTerm = By.id(NODE_POLICY + "termBI");
  private final By DropdownRenewalTerm = By.id(NODE_POLICY + "renewalTermBI");
  private final By editExpirationDate = By.id(NODE_POLICY + "expirationDtBI");
  private final By DropdownRatingCompany = By.id(NODE_POLICY + "policyCompanyBI");
  private final By DropdownIssuingCompany = By.id(NODE_POLICY + "policyProductBI");
  private final By DropdownProgram = By.id(NODE_POLICY + "policyProgramBI");
  private final By DropdownPrimaryState = By.id(NODE_POLICY + "policyStateBI");
  private final By editReleaseDate = By.id(NODE_POLICY + "releaseDtBI");
  private final By CheckboxUserEnteredPolicyNum = By.id(NODE_POLICY + "userEnteredPolicyBI");
  private final By editPolicyNum = By.id(NODE_POLICY + "policyNbrBI");
  private final By buttonSave = By.id(NODE_POLICY + "button1");
  private final By buttonCancel = By.id(NODE_POLICY + "button2");
  private final By clickOffDate = By.id(NODE_POLICY + "buildPolicyGrp");
  private final By editPolicyNumSeq = By.id(NODE_POLICY + "policyNbrSeqBI");
  private final String pageTitle = "AddNewPolicyPage";

  private String getPageTitle() {
    return pageTitle;
  }

  public void verifyPage() {
    verifyTitle(getPageTitle());
  }

  public void toggleCheckboxRenewalPolicy() {
    clickObject(CheckboxRenewalPolicy);
    clickObject(clickOffDate);
  }

  public void setEditEffectiveDate(String value) {
    setEdit(editEffectiveDate, value);
    clickObject(clickOffDate);
  }

  public void selectTerm(String value) {
    selectDropdown(DropdownTerm, value);
    clickObject(clickOffDate);
  }

  public void selectRenewalTerm(String value) {
    selectDropdown(DropdownRenewalTerm, value);
    clickObject(clickOffDate);
  }

  public void setEditExpirationDate(String value) {
    setEdit(editExpirationDate, value);
    clickObject(clickOffDate);
  }

  public void selectRatingCompany(String value) {
    selectDropdown(DropdownRatingCompany, value);
    clickObject(clickOffDate);
  }

  public void selectIssuingCompany(String value) {
    selectDropdown(DropdownIssuingCompany, value);
    clickObject(clickOffDate);
  }

  public void selectProgram(String value) {
    selectDropdown(DropdownProgram, value);
    clickObject(clickOffDate);
  }

  public void selectPrimaryState(String value) {
    selectDropdown(DropdownPrimaryState, value);
    clickObject(clickOffDate);
  }

  public void setReleaseDate(String value) {
    setEdit(editReleaseDate, value);
    clickObject(clickOffDate);
  }

  public void toggleCheckboxUserEnteredPolicyNum() {
    clickObject(CheckboxUserEnteredPolicyNum);
    clickObject(clickOffDate);
  }

  public void EnterPolicyNum(String policyNum, String policyNumSeq) {
    if (getCheckbox(CheckboxUserEnteredPolicyNum).equals("checked")) {
      setEdit(editPolicyNum, policyNum);
      clickObject(clickOffDate);
      setEdit(editPolicyNumSeq, policyNumSeq);
      clickObject(clickOffDate);
    }
  }

  public void VerifyPolicyNum(String policyNum, String policyNumSeq) {
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
