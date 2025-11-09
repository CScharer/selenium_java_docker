package com.cjs.qa.bts.pages;

import com.cjs.qa.selenium.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IssuePage extends Page {
  public IssuePage(WebDriver webDriver) {
    super(webDriver);
  }

  // DECLERATIONS
  private final String NODE_Form1PrintPolicy = "form1:printPolicyId:";
  private final String NODE_Form1ProcessedResults = "form1:cplProcessedResults:";
  private final By CheckboxPullPrint = By.id(NODE_Form1PrintPolicy + "pullPrint");
  private final By CheckboxPrintWorksheets = By.id(NODE_Form1PrintPolicy + "printWorksheet");
  private final By DropdownPrinter = By.id(NODE_Form1PrintPolicy + "printer");
  private final By buttonIssueNow = By.xpath(".//input[@value='Issue Now']");
  // id(NODE_Form1ProcessedResults + "flashScreenRateButton");
  private final By buttonIssueLater = By.id(NODE_Form1ProcessedResults + "buttonLater");
  public final String PAGE_TITLE = "IssuePage";

  // METHODS SET
  public void toggleCheckboxPullPrint() {
    clickObject(CheckboxPullPrint);
  }

  public void toggleCheckboxPrintWorksheets() {
    clickObject(CheckboxPrintWorksheets);
  }

  public String getDropdownPrinter() {
    return getDropdown(DropdownPrinter);
  }

  public void clickButtonIssueNow() {
    clickObject(buttonIssueNow);
    // Wait for Processing Policy pop-up window to disappear
    while (objectExists(By.xpath("//span[contains(text(), 'Processing Policy')]"))) {
      // Wait for pop-up to disappear
    }
  }

  public void clickButtonIssueLater() {
    clickObject(buttonIssueLater);
  }
}
