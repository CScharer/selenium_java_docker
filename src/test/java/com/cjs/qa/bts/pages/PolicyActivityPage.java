package com.cjs.qa.bts.pages;

import com.cjs.qa.core.Environment;
import com.cjs.qa.selenium.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PolicyActivityPage extends Page {
  public PolicyActivityPage(WebDriver webDriver) {
    super(webDriver);
  }

  private final String addRtnPrem = ".//*/tr[contains(.,'";
  private final By exitPolicySearch = By.xpath(".//*[@id='requestLinkSearch1']");
  public final String PAGE_TITLE = "Genesys - Policy Activity";

  public String getAddRtnPrem(String value) {
    // final By addRtnPremText = By.xpath(addRtnPrem + value + "')]/td[8]";)
    final String addRtnPremTextFromScreen =
        getWebElement(By.xpath(addRtnPrem + value + "')]/td[8]")).getText();
    Environment.sysOut(addRtnPremTextFromScreen);
    return addRtnPremTextFromScreen;
  }

  public void clickExitPolicy() {
    clickObject(exitPolicySearch);
  }
}
