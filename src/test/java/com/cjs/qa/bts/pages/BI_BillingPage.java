package com.cjs.qa.bts.pages;

import com.cjs.qa.selenium.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BI_BillingPage extends Page {
  public BI_BillingPage(WebDriver webDriver) {
    super(webDriver);
  }

  private final By NumberOfGroupMembers = By.id("NUM_GRP_MBR");
  private final By buttonFrame = By.id("billing-title");
  public final String PAGE_TITLE = "BI_BillingPage";

  public void verifyPage() {
    verifyTitle(PAGE_TITLE);
  }

  public void EnterNumberOfGroupMembers(String value) {
    setEdit(NumberOfGroupMembers, value);
  }

  public void toggleFrame() {
    clickObject(buttonFrame);
  }
}
