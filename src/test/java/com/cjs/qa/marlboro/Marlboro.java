package com.cjs.qa.marlboro;

import com.cjs.qa.marlboro.pages.EarnPointsPage;
import com.cjs.qa.marlboro.pages.OffersAndActivityPage;
import com.cjs.qa.marlboro.pages.SecurityPage;
import com.cjs.qa.marlboro.pages.SignInPage;
import com.cjs.qa.marlboro.pages.VerifyInformationInterruptPage;
import com.cjs.qa.utilities.IExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Marlboro {
  public static final String URL_OFFERS =
      MarlboroEnvironment.URL_BASE + "/pages/offers" + IExtension.HTML;
  public EarnPointsPage EarnPointsPage;
  public OffersAndActivityPage OffersAndActivityPage;
  public SecurityPage SecurityPage;
  public SignInPage SignInPage;
  public VerifyInformationInterruptPage VerifyInformationInterruptPage;
  By imageAccount = By.xpath("//i[contains(@class,'icon-account')]");

  public Marlboro(WebDriver webDriver) {
    EarnPointsPage = new EarnPointsPage(webDriver);
    OffersAndActivityPage = new OffersAndActivityPage(webDriver);
    SecurityPage = new com.cjs.qa.marlboro.pages.SecurityPage(webDriver);
    SignInPage = new SignInPage(webDriver);
    VerifyInformationInterruptPage = new VerifyInformationInterruptPage(webDriver);
    webDriver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(120));
  }
}
