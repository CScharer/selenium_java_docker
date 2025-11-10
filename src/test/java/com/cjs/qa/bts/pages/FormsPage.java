package com.cjs.qa.bts.pages;

import com.cjs.qa.selenium.Page;
import com.cjs.qa.utilities.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormsPage extends Page {
  public FormsPage(WebDriver webDriver) {
    super(webDriver);
  }

  private final String formsListBoxBegin = ".//*/tr[contains(.," + Constants.QUOTE_SINGLE;
  private final String formsListBoxEnd = Constants.QUOTE_SINGLE + ")]/td/input";
  private final String NODE_Forms1 = ".//*[@id='form1:forms:";
  private final By buttonOptionalForms = By.xpath(NODE_Forms1 + "manualFormsBtn']");
  private final By buttonUserDefinedForms = By.xpath(NODE_Forms1 + "userDefinedFormsBtn']");
  private final By buttonViewExcludedForms = By.xpath(NODE_Forms1 + "forms:excludedFormsBtn']");
  private final By buttonQuickScroll =
      By.xpath(NODE_Forms1 + "forms:variableDataBtnGroup']/div[6]/input");
  private final By buttonDelete = By.xpath(NODE_Forms1 + "forms:deleteBtn']");
  private final By buttonUpdateReprint = By.xpath(".//*[@id='form1:forms:printBtn']");
  private final String pageTitle = "FormsPage";

  private String getPageTitle() {
    return pageTitle;
  }

  // METHODS
  public void clickButtonOptionalForms() {
    clickObject(buttonOptionalForms);
  }

  public void clickButtonUpdateReprint() {
    clickObject(buttonUpdateReprint);
  }

  public void clickButtonUserDefinedForms() {
    clickObject(buttonUserDefinedForms);
  }

  public void clickButtonViewExcludedForms() {
    clickObject(buttonViewExcludedForms);
  }

  public void clickButtonQuickScroll() {
    clickObject(buttonQuickScroll);
  }

  public void clickButtonDelete() {
    clickObject(buttonDelete);
  }

  public void clickFormsBox(String value) {
    final String form = value.substring(0, value.indexOf(';'));
    final String edition = value.substring(value.indexOf(';') + 1, value.length());
    final By formToClick =
        By.xpath(
            formsListBoxBegin
                + form
                + Constants.QUOTE_SINGLE
                + ")][contains(.,"
                + Constants.QUOTE_SINGLE
                + edition
                + formsListBoxEnd);
    clickObject(formToClick);
  }
  // 4211585-10
  // NEED TO STILL FIGURE OUT HOW TO GET DATA FROM FORMS LIST
  // TO MATCH ONLY WHAT WE HAVE ON THE INPUT SHEET
}
