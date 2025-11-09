package com.cjs.qa.hardees;

import com.cjs.qa.hardees.pages.SurveyPage;
import org.openqa.selenium.WebDriver;

public class Hardees {
  public SurveyPage SurveyPage;

  public Hardees(WebDriver webDriver) {
    SurveyPage = new SurveyPage(webDriver);
    webDriver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(120));
  }
}
