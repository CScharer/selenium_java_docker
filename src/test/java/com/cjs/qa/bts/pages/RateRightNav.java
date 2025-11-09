package com.cjs.qa.bts.pages;

import com.cjs.qa.selenium.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RateRightNav extends Page {
  public RateRightNav(WebDriver webDriver) {
    super(webDriver);
  }

  // DECLARATIONS
  private final String NODE_FORM1ADMIN = "form1:adminFunctions:";
  private final String NODE_POLICYISSUANCE = NODE_FORM1ADMIN + "policyIssuanceFunctions:";
  private final String NODE_NAVIGATIONMENU = ":navigationMenuItem:";
  private final String NODE_MENUCSUBMIT = "menuCommandSubmittable";
  private final By sidebarRight = By.xpath(".//*[@id='outer-east-open-btn']");
  private final By linkExitPolicySearch = By.xpath(".//*[@id='requestLinkExitPolicy']");
  private final By linkChangeDates = By.xpath(".//*[@id='requestLinkChangeDates']");
  private final By linkPolicyErrors = By.xpath(".//*[@id='policyErrorsLink']");
  private final By linkPolicyViews =
      By.xpath(".//*[@id='" + NODE_FORM1ADMIN + "panelGroupButtonForPolicyViews']/a");
  private final By linkEdit =
      By.xpath(
          ".//*[@id='" + NODE_POLICYISSUANCE + "0" + NODE_NAVIGATIONMENU + NODE_MENUCSUBMIT + "']");
  private final By linkValidate =
      By.xpath(
          ".//*[@id='" + NODE_POLICYISSUANCE + "1" + NODE_NAVIGATIONMENU + NODE_MENUCSUBMIT + "']");
  private final By linkRate =
      By.xpath(
          ".//*[@id='" + NODE_POLICYISSUANCE + "2" + NODE_NAVIGATIONMENU + NODE_MENUCSUBMIT + "']");
  private final By linkBind =
      By.xpath(
          ".//*[@id='" + NODE_POLICYISSUANCE + "3" + NODE_NAVIGATIONMENU + NODE_MENUCSUBMIT + "']");
  private final By linkIssue =
      By.xpath(
          ".//*[@id='" + NODE_POLICYISSUANCE + "4" + NODE_NAVIGATIONMENU + NODE_MENUCSUBMIT + "']");
  private final By linkRequestPrint =
      By.xpath(
          ".//*[@id='" + NODE_POLICYISSUANCE + "5" + NODE_NAVIGATIONMENU + "menuLinkSubmittable']");
  private final By linkTerritoryInformation =
      By.xpath(".//*[@id='" + NODE_FORM1ADMIN + "terrInfoLink']");
  private final By linkClientInformation =
      By.xpath(".//*[@id='" + NODE_FORM1ADMIN + "clientViewLink']");
  private final By linkPayorDetails =
      By.xpath(".//*[@id='" + NODE_FORM1ADMIN + "payorDetailsLink']");

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
