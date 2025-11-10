package com.cjs.qa.bts.pages;

import com.cjs.qa.selenium.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BI_TerrorismPage extends Page {
  public BI_TerrorismPage(WebDriver webDriver) {
    super(webDriver);
  }

  private final By DropdownDoesTerrorismApply = By.id("TRSM_IND");
  private final By DropdownDoesTerrorismApplyToCrime = By.id("TRSM_CRIM_IND1");
  private final By editCertifiedActsOfTerrorismPremium = By.id("TRSM_PREM");
  private final By editTerrorismFireFollowingPremium = By.id("TRSM_FF_PREM");
  private final By editOtherActsOfTerrorism = By.id("TRSM_OTH_PREM");
  private final By buttonFrame = By.id("terrorism-title");
  private final String pageTitle = "BI_TerrorismPage";

  private String getPageTitle() {
    return pageTitle;
  }

  public void verifyPage() {
    verifyTitle(getPageTitle());
  }

  public void selectDropdownDoesTerrorismApply(String value) {
    selectDropdown(DropdownDoesTerrorismApply, value);
  }

  public void selectDropdownDoesTerrorismApplyToCrime(String value) {
    selectDropdown(DropdownDoesTerrorismApplyToCrime, value);
  }

  public void setEditCertifiedActsOfTerrorismPremium(String value) {
    setEdit(editCertifiedActsOfTerrorismPremium, value);
  }

  public void setEditTerrorismFireFollowingPremium(String value) {
    setEdit(editTerrorismFireFollowingPremium, value);
  }

  public void setEditOtherActsOfTerrorism(String value) {
    setEdit(editOtherActsOfTerrorism, value);
  }

  public void toggleFrame() {
    clickObject(buttonFrame);
  }
}
