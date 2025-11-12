package com.cjs.qa.bts.pages;

import com.cjs.qa.selenium.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RateRightNav extends Page {
  public RateRightNav(WebDriver webDriver) {
    super(webDriver);
  }

  // DECLARATIONS
  private static final String nodeForm1Admin = "form1:adminFunctions:";
  private final String nodePolicyIssuance = nodeForm1Admin + "policyIssuanceFunctions:";
  private static final String nodeNavigationMenu = ":navigationMenuItem:";
  private static final String nodeMenuCsubmit = "menuCommandSubmittable";
  private final By sidebarRight = By.xpath(".//*[@id='outer-east-open-btn']");
  private final By linkExitPolicySearch = By.xpath(".//*[@id='requestLinkExitPolicy']");
  private final By linkChangeDates = By.xpath(".//*[@id='requestLinkChangeDates']");
  private final By linkPolicyErrors = By.xpath(".//*[@id='policyErrorsLink']");
  private final By linkPolicyViews =
      By.xpath(".//*[@id='" + nodeForm1Admin + "panelGroupButtonForPolicyViews']/a");
  private final By linkEdit =
      By.xpath(
          ".//*[@id='" + nodePolicyIssuance + "0" + nodeNavigationMenu + nodeMenuCsubmit + "']");
  private final By linkValidate =
      By.xpath(
          ".//*[@id='" + nodePolicyIssuance + "1" + nodeNavigationMenu + nodeMenuCsubmit + "']");
  private final By linkRate =
      By.xpath(
          ".//*[@id='" + nodePolicyIssuance + "2" + nodeNavigationMenu + nodeMenuCsubmit + "']");
  private final By linkBind =
      By.xpath(
          ".//*[@id='" + nodePolicyIssuance + "3" + nodeNavigationMenu + nodeMenuCsubmit + "']");
  private final By linkIssue =
      By.xpath(
          ".//*[@id='" + nodePolicyIssuance + "4" + nodeNavigationMenu + nodeMenuCsubmit + "']");
  private final By linkRequestPrint =
      By.xpath(
          ".//*[@id='" + nodePolicyIssuance + "5" + nodeNavigationMenu + "menuLinkSubmittable']");
  private final By linkTerritoryInformation =
      By.xpath(".//*[@id='" + nodeForm1Admin + "terrInfoLink']");
  private final By linkClientInformation =
      By.xpath(".//*[@id='" + nodeForm1Admin + "clientViewLink']");
  private final By linkPayorDetails =
      By.xpath(".//*[@id='" + nodeForm1Admin + "payorDetailsLink']");

  // METHODS SET
  public void toggleSidebarRight() {
    clickObject(sidebarRight);
  }

  public void clickLinkExitPolicySearch() {
    clickObject(linkExitPolicySearch);
  }

  public void clickLinkChangeDates() {
    clickObject(linkChangeDates);
  }

  public void clickLinkPolicyErrors() {
    clickObject(linkPolicyErrors);
  }

  public void clickLinkPolicyViews() {
    clickObject(linkPolicyViews);
  }

  public void clickLinkEdit() {
    clickObject(linkEdit);
  }

  public void clickLinkValidate() {
    clickObject(linkValidate);
  }

  public void clickLinkRate() {
    clickObject(linkRate);
  }

  public void clickLinkBind() {
    clickObject(linkBind);
  }

  public void clickLinkIssue() {
    clickObject(linkIssue);
  }

  public void clickLinkRequestPrint() {
    clickObject(linkRequestPrint);
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
