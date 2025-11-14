package com.cjs.qa.pluralsight.pages;

import com.cjs.qa.selenium.Page;
import com.cjs.qa.utilities.IExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Page {
  public LoginPage(WebDriver webDriver) {
    super(webDriver);
  }

  public static final String DEFAULT_URL = "https://app.pluralsight" + IExtension.COM;
  private static final By editUserName = By.id("Username");
  private static final By editPassword = By.id("Password");
  private static final By buttonSignIn = By.id("login");

  public void buttonSignInClick() {
    clickObject(buttonSignIn);
  }

  public void editUserNameSet(String value) {
    setEdit(editUserName, value);
  }

  public void editPasswordSet(String value) {
    setEditPassword(editPassword, value);
  }

  public void login() {
    getWebDriver().get(DEFAULT_URL);
    final String userName = "tgenzen";
    // DUMMY PASSWORD - For testing/training only, not a real credential
    // TODO: Migrate to Google Cloud Secret Manager if this becomes a production credential
    final String password = "C@Training";
    editUserNameSet(userName);
    editPasswordSet(password);
    buttonSignInClick();
  }
}
