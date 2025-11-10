package com.cjs.qa.bts.pages;

import com.cjs.qa.selenium.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BI_ButtonsPage extends Page {
  public BI_ButtonsPage(WebDriver webDriver) {
    super(webDriver);
  }

  private final By buttonSave = By.id("save");
  private final By buttonSaveProducts = By.id("saveProducts");
  private final By buttonCancel = By.id("cancel");
  private final By buttonQuickScroll = By.id("quickScroll");
  private final By buttonOpenCloseLeftNav =
      By.xpath("html/body/div[1]/nav/div/div[1]/div[1]/button");
  private final String pageTitle = "BI_ButtonsPage";

  private String getPageTitle() {
    return pageTitle;
  }

  public void verifyPage() {
    verifyTitle(getPageTitle());
  }

  public void clickButtonSave() {
    clickObject(buttonSave);
  }

  public void clickButtonSaveProducts() {
    clickObject(buttonSaveProducts);
  }

  public void clickButtonCancel() {
    clickObject(buttonCancel);
  }

  public void clickButtonQuickScroll() {
    clickObject(buttonQuickScroll);
  }

  public void clickButtonOpenCloseLeftNav() {
    clickObject(buttonOpenCloseLeftNav);
  }
}
