package com.cjs.qa.bts.pages;

import com.cjs.qa.selenium.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RateLeftNavPage extends Page {
  public RateLeftNavPage(WebDriver webDriver) {
    super(webDriver);
  }

  private final String NODE_PROCESSEDERRS =
      ".//*[@id='form1:viewNavigationMenu:navigationMenuLevel1:";
  private final By linkManageProducts =
      By.xpath(NODE_PROCESSEDERRS + "5:navigationMenuItem:menuLinkSubmittable']/span");

  // private final By linkInusrableInterestSummary =
  // By.xpath(NODE_PROCESSEDERRS +
  // "4:navigationMenuItem:menuCommandSubmittable']/span")

  public void clickLinkManageProducts() {
    clickObject(linkManageProducts);
  }
}
