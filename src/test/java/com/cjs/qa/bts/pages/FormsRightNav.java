package com.cjs.qa.bts.pages;

import com.cjs.qa.selenium.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormsRightNav extends Page {
  public FormsRightNav(WebDriver webDriver) {
    super(webDriver);
  }

  // DECLARATIONS
  private final String nodeForm1Admin = "form1:adminFunctions:";
  private final String nodeNavigationMenu = ":navigationMenuItem:";
  private final By sidebarRightOpen = By.xpath(".//*[@id='outer-east-open-btn']");
  private final By sidebarRightClose = By.xpath(".//*[@'outer-east-close-btn']");
  private final By linkExitPolicySearch = By.xpath(".//*[@id='requestLinkExitPolicy']");
  private final By linkPolicyErrors = By.xpath(".//*[@id='policyErrorsLink']");
  private final By linkPolicyViews =
      By.xpath(".//*[@id='" + nodeForm1Admin + "panelGroupButtonForPolicyViews']/a");
  private final By linkRequestPrint =
      By.xpath(
          ".//*[@id='" + nodeForm1Admin + "0" + nodeNavigationMenu + "menuLinkSubmittable']");
  private final By linkTerritoryInformation =
      By.xpath(".//*[@id='" + nodeForm1Admin + "terrInfoLink']");
  private final By linkClientInformation =
      By.xpath(".//*[@id='" + nodeForm1Admin + "clientViewLink']");
  private final By linkPayorDetails =
      By.xpath(".//*[@id='" + nodeForm1Admin + "payorDetailsLink']");
  private final By linkRate =
      By.xpath(
          ".//*[@id='"
              + nodeForm1Admin
              + "policyIssuanceFunctions:2:navigationMenuItem:menuCommandSubmittable']");
  private boolean sidebarIsOpen = false;

  // METHODS SET
  public boolean isSidebarOpen() {
    sidebarIsOpen = getWebElement(sidebarRightClose) != null;
    return sidebarIsOpen;
  }

  public void toggleSidebarRight() {
    // sidebarIsOpen = isSidebarOpen();
    // if (sidebarIsOpen)
    // {
    // clickObject(sidebarRightClose);
    // } else
    // {
    clickObject(sidebarRightOpen);
    // }
  }

  public void clickLinkExitPolicySearch() {
    clickObject(linkExitPolicySearch);
  }

  public void clickLinkPolicyErrors() {
    clickObject(linkPolicyErrors);
  }

  public void clickLinkPolicyViews() {
    clickObject(linkPolicyViews);
  }

  public void clickLinkRequestPrint() {
    clickObject(linkRequestPrint);
  }

  public void clickLinkRate() {
    clickObject(linkRate);
  }

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
