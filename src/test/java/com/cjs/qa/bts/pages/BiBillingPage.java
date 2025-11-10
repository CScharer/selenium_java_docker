package com.cjs.qa.bts.pages;

import com.cjs.qa.selenium.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BiBillingPage extends Page {
  public BiBillingPage(WebDriver webDriver) {
    super(webDriver);
  }

  private final By NumberOfGroupMembers = By.id("NUM_GRP_MBR");
  private final By buttonFrame = By.id("billing-title");
  private final String pageTitle = "BI_BillingPage";

  private String getPageTitle() {
    return pageTitle;
  }

  public void verifyPage() {
    verifyTitle(getPageTitle());
  }

  public void enterNumberOfGroupMembers(String value) {
    setEdit(NumberOfGroupMembers, value);
  }

  public void toggleFrame() {
    clickObject(buttonFrame);
  }
}
