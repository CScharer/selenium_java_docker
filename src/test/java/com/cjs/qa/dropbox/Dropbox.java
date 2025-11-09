package com.cjs.qa.dropbox;

import com.cjs.qa.dropbox.pages.SignInPage;
import org.openqa.selenium.WebDriver;

public class Dropbox {
  public SignInPage SignInPage;

  public Dropbox(WebDriver webDriver) {
    SignInPage = new SignInPage(webDriver);
    webDriver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(120));
  }
}
