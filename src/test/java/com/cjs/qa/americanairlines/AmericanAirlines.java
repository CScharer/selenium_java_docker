package com.cjs.qa.americanairlines;

import com.cjs.qa.americanairlines.pages.VacationabilityPage;
import org.openqa.selenium.WebDriver;

public class AmericanAirlines {
  public VacationabilityPage VacationabilityPage;

  public AmericanAirlines(WebDriver webDriver) {
    VacationabilityPage = new VacationabilityPage(webDriver);
    webDriver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(120));
  }
}
