package com.cjs.qa.google;

import com.cjs.qa.google.pages.FlightsPage;
import com.cjs.qa.google.pages.SignInPage;
import org.openqa.selenium.WebDriver;

public class Google {
  public FlightsPage FlightsPage;
  public SignInPage SignInPage;

  public Google(WebDriver webDriver) {
    FlightsPage = new FlightsPage(webDriver);
    SignInPage = new SignInPage(webDriver);
    webDriver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(120));
  }
}
