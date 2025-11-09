package com.cjs.qa.bts.pages;

import com.cjs.qa.selenium.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IssuePageLeftNav extends Page {
  public IssuePageLeftNav(WebDriver webDriver) {
    super(webDriver);
  }

  // private final String NODE_PROCESSEDERRS =
  // ".//*[@id='form1:viewNavigationMenu:navigationMenuLevel1:"
  private final By linkPremiumSummary = By.linkText("Premium Summary");
  private final By linkPolicyActivity = By.linkText("Policy Activity");

  // .xpath(NODE_PROCESSEDERRS +
  // "11:navigationMenuItem:menuLinkSubmittable']/span");
  public void clickLinkPremiumSummary() {
    while (!(isDisplayed(linkPremiumSummary))) {}
    clickObject(linkPremiumSummary);
  }

  public void clickLinkPolicyActivity() {
    while (!(isDisplayed(linkPolicyActivity))) {}
    clickObject(linkPolicyActivity);
  }
}
