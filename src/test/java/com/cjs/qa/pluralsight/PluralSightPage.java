package com.cjs.qa.pluralsight;

import com.cjs.qa.pluralsight.pages.LoginPage;
import com.cjs.qa.pluralsight.pages.SessionPage;
import com.cjs.qa.pluralsight.pages.TableOfContentsPage;
import org.openqa.selenium.WebDriver;

public class PluralSightPage {
  public LoginPage LoginPage;
  public SessionPage SessionPage;
  public TableOfContentsPage TableOfContentsPage;

  public PluralSightPage(WebDriver webDriver) {
    LoginPage = new LoginPage(webDriver);
    SessionPage = new SessionPage(webDriver);
    TableOfContentsPage = new TableOfContentsPage(webDriver);
    webDriver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(120));
  }
}
