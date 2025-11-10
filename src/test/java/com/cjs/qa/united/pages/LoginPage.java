package com.cjs.qa.united.pages;

import com.cjs.qa.core.QAException;
import com.cjs.qa.selenium.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Page {
  public LoginPage(WebDriver webDriver) {
    super(webDriver);
  }

  private final By editMileagePlusNumber = By.xpath(".//*[@id='loginFormModel.login']");
  private final By editPassword = By.xpath(".//*[@id='loginFormModel.password']");
  private final By checkboxRememberMe =
      By.xpath(".//*[@id='loginFormModel']//input[@id='saveCredentials']");
  // loginFormModel.rememberMe
  private final By buttonSignIn = By.xpath(".//*[@id='loginFormModel']/button");

  public void editMileagePlusNumberSet(String value) throws QAException {
    setEdit(editMileagePlusNumber, value);
  }

  public void editPasswordSet(String value) throws QAException {
    setEditPassword(editPassword, value);
  }

  public void checkboxRememberMeSet(String value) throws QAException {
    setCheckbox(checkboxRememberMe, value);
  }

  public void buttonSignInClick() throws QAException {
    clickObject(buttonSignIn);
  }

  public void login(String mileagePlusNumber, String password, String rememberMe)
      throws QAException {
    editMileagePlusNumberSet(mileagePlusNumber);
    editPasswordSet(password);
    // CheckboxRememberMeSet(rememberMe);
    buttonSignInClick();
  }
}
