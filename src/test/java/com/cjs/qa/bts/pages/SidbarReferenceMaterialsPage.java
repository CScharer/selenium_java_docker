package com.cjs.qa.bts.pages;

import com.cjs.qa.selenium.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SidbarReferenceMaterialsPage extends Page {
  public SidbarReferenceMaterialsPage(WebDriver webDriver) {
    super(webDriver);
  }

  // DECLARATIONS
  private final By linkTerritoryInformation = By.id("terrInfoLink");
  private final By linkClientInformation = By.id("clientViewLink");
  private final By linkPayorDetails = By.id("payorViewLink");

  // METHODS SET
  public void clickLinkTerritoryInformation() {
    clickObject(linkTerritoryInformation);
  }

  public void clickLinkClientInformation() {
    clickObject(linkClientInformation);
  }

  public void clickLinkPayorDetails() {
    clickObject(linkPayorDetails);
  }
}
