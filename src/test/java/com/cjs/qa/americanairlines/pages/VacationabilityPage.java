package com.cjs.qa.americanairlines.pages;

import com.cjs.qa.americanairlines.AmericanAirlinesEnvironment;
import com.cjs.qa.core.Environment;
import com.cjs.qa.core.QAException;
import com.cjs.qa.selenium.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VacationabilityPage extends Page {
  public VacationabilityPage(WebDriver webDriver) {
    super(webDriver);
  }

  public int searches = 180;
  final By labelLoading = By.xpath(".//*[@id='cphBody_divLoader']//div[@class='loader-text']");
  final By editFirstName = By.xpath(".//*[@id='txtFirst']");
  final By editLastName = By.xpath(".//*[@id='txtLast']");
  final By editEmail = By.xpath(".//*[@id='txtEmail']");
  final By editAANumber = By.xpath(".//*[@id='txtAANumber']");
  final By CheckboxAgree = By.xpath(".//*[@id='divRegistration']//label[@for='chkAgree']/span");
  final By buttonRegister = By.xpath(".//*[@id='btnRegister']");
  final By labelThankYou =
      By.xpath(".//*[@id='cphBody_divStaticPage']//div[@class='box-title-copy']");

  public void editFirstNameSet(String value) throws QAException {
    setEdit(editFirstName, value);
  }

  public void editLastNameSet(String value) throws QAException {
    setEdit(editLastName, value);
  }

  public void editEmailSet(String value) throws QAException {
    setEdit(editEmail, value);
  }

  public void editAANumberSet(String value) throws QAException {
    setEdit(editAANumber, value);
  }

  public void CheckboxAgreeSet(String value) throws QAException {
    setCheckbox(CheckboxAgree, value);
  }

  public void buttonRegisterClick() throws QAException {
    clickObject(buttonRegister);
    waitExists(labelThankYou);
    searches++;
    Environment.sysOut("Registered:[" + searches + "] times.");
  }

  public void load() throws QAException {
    maximizeWindow();
    Environment.sysOut("Loading:[" + AmericanAirlinesEnvironment.URL_LOGIN + "]");
    webDriver.get(AmericanAirlinesEnvironment.URL_LOGIN);
    int waits = 0;
    do {
      waits++;
    } while (objectExists(labelLoading));
    Environment.sysOut("Waited For Load:[" + waits + "] times.");
  }

  public void populate() throws QAException {
    load();
    // editFirstNameSet("Christopher");
    // editLastNameSet("Scharer");
    // editEmailSet(CJSConstants.EMAIL_ADDRESS_MSN);
    // editAANumber("7L86P34");
    // CheckboxAgreeSet("checked");
    // buttonRegisterClick();
  }
}
