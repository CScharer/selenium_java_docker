package com.cjs.qa.bts.pages;

import com.cjs.qa.core.Environment;
import com.cjs.qa.selenium.ISelenium;
import com.cjs.qa.selenium.Page;
import io.cucumber.datatable.DataTable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RequestPrintPage extends Page {
  public RequestPrintPage(WebDriver webDriver) {
    super(webDriver);
  }

  private final String NODE_REQUEST_PRINT = "form1:requestPrint:";
  private final By DropdownReports = By.id(NODE_REQUEST_PRINT + "reportId");
  private final By DropdownEffectiveDate = By.id(NODE_REQUEST_PRINT + "effectiveDtId");
  private final By DropdownProcessedDate = By.id(NODE_REQUEST_PRINT + "processedDt");
  private final By CheckboxPullPrint = By.id(NODE_REQUEST_PRINT + "printPolicyId:pullPrint");
  private final By CheckboxPrintWorkSheets =
      By.id(NODE_REQUEST_PRINT + "printPolicyId:printWorksheet");
  private final By DropdownPrinter = By.id(NODE_REQUEST_PRINT + "printPolicyId:printer");
  private final By CheckboxSPLPrint = By.id(NODE_REQUEST_PRINT + "printPolicyId:splPrint");
  private final By buttonPrint = By.id(NODE_REQUEST_PRINT + "buttonOk");
  private final By buttonCancel = By.id(NODE_REQUEST_PRINT + "cancelBtn");
  private final By buttonMinimizeTransactionInformation =
      By.id(NODE_REQUEST_PRINT + "heading3form1:requestPrint:heading3");
  private final By buttonMinimizePrintOptions =
      By.id(NODE_REQUEST_PRINT + "printPolicyId:printheading");
  private final String pageTitle = "RequestPrintPage";

  private String getPageTitle() {
    return pageTitle;
  }

  public void verifyPage() {
    verifyTitle(getPageTitle());
  }

  // METHODS GET
  public String getDropdownReports() {
    return getDropdown(DropdownReports);
  }

  public String getDropdownEffectiveDate() {
    return getDropdown(DropdownEffectiveDate);
  }

  public String getDropdownProcessedDate() {
    return getDropdown(DropdownProcessedDate);
  }

  public String getCheckboxPullPrint() {
    return getCheckbox(CheckboxPullPrint);
  }

  public String getCheckboxPrintWorksheets() {
    return getCheckbox(CheckboxPrintWorkSheets);
  }

  public String getDropdownPrinter() {
    return getDropdown(DropdownPrinter);
  }

  public String getCheckboxSPLPrint() {
    return getCheckbox(CheckboxSPLPrint);
  }

  // METHODS SET
  public void selectDropdownReports(String value) {
    selectDropdown(DropdownReports, value);
  }

  public void selectDropdownEffectiveDate(String value) {
    sleep(200);
    selectDropdownWithPartialText(DropdownEffectiveDate, value);
  }

  public void selectDropdownProcessedDate(String value) {
    sleep(200);
    selectDropdownWithPartialText(DropdownProcessedDate, value);
  }

  public void toggleCheckboxPullPrint() {
    clickObject(CheckboxPullPrint);
  }

  public void setCheckboxPullPrint(String value) {
    setCheckbox(CheckboxPullPrint, value);
  }

  public void toggleCheckboxPrintWorkSheets() {
    clickObject(CheckboxPrintWorkSheets);
  }

  public void setCheckboxPrintWorksheets(String value) {
    setCheckbox(CheckboxPrintWorkSheets, value);
  }

  public void selectDropdownPrinter(String value) {
    selectDropdown(DropdownPrinter, value);
  }

  public void toggleCheckboxSPLPrint() {
    clickObject(CheckboxSPLPrint);
  }

  public void setCheckboxSPLPrint(String value) {
    setCheckbox(CheckboxSPLPrint, value);
  }

  // BUTTONS
  public void clickButtonPrint() {
    clickObject(buttonPrint);
  }

  public void clickButtonCancel() {
    clickObject(buttonCancel);
  }

  public void clickButtonMinimizeTransactionInformation() {
    clickObject(buttonMinimizeTransactionInformation);
  }

  public void clickButtonMinimizePrintOptions() {
    clickObject(buttonMinimizePrintOptions);
  }

  // SWITCHES POPULATE
  public void populatePage(DataTable table) {
    final List<List<String>> data = table.asLists();
    for (final List<?> item : data) {
      final String field = (String) item.get(0);
      String value = (String) item.get(1);
      if (!value.equals("")) {
        if (Environment.isLogAll()) {
          Environment.sysOut("({Field}" + field + ", {Value}" + value + ");");
        }
        switch (field.toLowerCase()) {
          case "reports":
            selectDropdownReports(value);
            break;
          case "effective date":
            selectDropdownEffectiveDate(value);
            break;
          case "processed date":
            selectDropdownProcessedDate(value);
            break;
          case "pull print":
            setCheckboxPullPrint(value);
            break;
          case "print worksheets":
            setCheckboxPrintWorksheets(value);
            break;
          case "printer":
            selectDropdownPrinter(value);
            break;
          case "spl print":
            setCheckboxSPLPrint(value);
            break;
          default:
            value = "[" + field + "]" + ISelenium.FIELD_NOT_CODED;
            Environment.sysOut(value);
            break;
        }
      }
    }
  }

  // SWITCHES VALIDATE
  public void validatePage(DataTable table) {
    FormsPage formsPage = new FormsPage(getWebDriver());
    formsPage.getUserName();
    final Map<String, String> expected = new HashMap<>();
    final Map<String, String> actual = new HashMap<>();
    final List<List<String>> data = table.asLists();
    for (final List<?> item : data) {
      final String field = (String) item.get(0);
      String value = (String) item.get(1);
      if (!value.equals("")) {
        if (Environment.isLogAll()) {
          Environment.sysOut("({Field}" + field + ", {Value}" + value + ");");
        }
        expected.put(field, value);
        switch (field.toLowerCase()) {
          case "reports":
            value = getDropdownReports();
            break;
          case "effective date":
            value = getDropdownEffectiveDate();
            break;
          case "processed date":
            value = getDropdownProcessedDate();
            value = expected.get(field);
            break;
          case "pull print":
            value = getCheckboxPullPrint();
            break;
          case "print worksheets":
            value = getCheckboxPrintWorksheets();
            break;
          case "printer":
            value = getDropdownPrinter();
            break;
          case "spl print":
            value = getCheckboxSPLPrint();
            break;
          default:
            value = "[" + field + "]" + ISelenium.FIELD_NOT_CODED;
            Environment.sysOut(value);
            break;
        }
        actual.put(field, value);
      }
    }
    Assert.assertSame(getPageTitle() + " validatePage", expected, actual);
  }
}
