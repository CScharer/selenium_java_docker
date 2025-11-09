package com.cjs.qa.wellmark;

import com.cjs.qa.wellmark.pages.ClaimsAndSpendingPage;
import com.cjs.qa.wellmark.pages.DetailsPage;
import com.cjs.qa.wellmark.pages.HomePage;
import com.cjs.qa.wellmark.pages.LogInPage;
import com.cjs.qa.wellmark.pages.PopUpPage;
import com.cjs.qa.wellmark.pages.TempPage;
import org.openqa.selenium.WebDriver;

public class Wellmark {
  public ClaimsAndSpendingPage ClaimsAndSpendingPage;
  public DetailsPage DetailsPage;
  public HomePage HomePage;
  public LogInPage LogInPage;
  public PopUpPage PopUpPage;
  public TempPage TempPage;

  public Wellmark(WebDriver webDriver) {
    ClaimsAndSpendingPage = new ClaimsAndSpendingPage(webDriver);
    DetailsPage = new com.cjs.qa.wellmark.pages.DetailsPage(webDriver);
    HomePage = new HomePage(webDriver);
    LogInPage = new LogInPage(webDriver);
    PopUpPage = new PopUpPage(webDriver);
    TempPage = new TempPage(webDriver);
    webDriver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(120));
  }
}
