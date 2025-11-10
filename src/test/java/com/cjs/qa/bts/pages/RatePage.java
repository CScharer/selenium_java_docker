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

public class RatePage extends Page {
  public RatePage(WebDriver webDriver) {
    super(webDriver);
  }

  private final String pageTitle = "RatePage";

  private String getPageTitle() {
    return pageTitle;
  }
  private final String nodeProcessedErrs = ".//*[@id='form1:cplProcessedResults:cplProcessedErrs:";
  private final By labelCustomStatusMsg = By.xpath(nodeProcessedErrs + "customStatusMsg']");
  private final By labelPrintStatus = By.xpath(nodeProcessedErrs + "processTransactionStatus']");
  private final By toggleResults =
      By.xpath(nodeProcessedErrs + "cplProcessedErrorsTable']/div[1]/span[1]/button");
  private final By labelPolicyTotal = By.xpath(nodeProcessedErrs + "overviewTotM']");
  private final By labelCommercialAuto =
      By.xpath(nodeProcessedErrs + "productsTableEx1:0:overviewTotM1']");
  private final By labelAgriculturalOutput =
      By.xpath(nodeProcessedErrs + "productsTableEx1:1:overviewTotM1']");
  private final By labelGeneralLiability =
      By.xpath(nodeProcessedErrs + "productsTableEx1:2:overviewTotM1']");
  private final By labelPolicywideCoverages =
      By.xpath(nodeProcessedErrs + "productsTableEx1:3:overviewTotM1']");
  private final By buttonRateNow =
      By.xpath(".//*[@id='form1:cplProcessedResults:flashScreenRateButton']");

  public void verifyCustomStatusMsg(String value) {
    verifyResultData(value, labelCustomStatusMsg);
  }

  public void verifyPrintStatus(String value) {
    verifyResultData(value, labelPrintStatus);
  }

  public void toggleResults() {
    clickObject(toggleResults);
  }

  public void verifyResultPolicyTotal(String value) {
    verifyResultData(value, labelPolicyTotal);
  }

  public void verifyResultCommercialAuto(String value) {
    verifyResultData(value, labelCommercialAuto);
  }

  public void verifyResultAgriculturalOutput(String value) {
    verifyResultData(value, labelAgriculturalOutput);
  }

  public void verifyResultGeneralLiability(String value) {
    verifyResultData(value, labelGeneralLiability);
  }

  public void verifyResultPolicywideCoverages(String value) {
    verifyResultData(value, labelPolicywideCoverages);
  }

  public String getLabelPolicyTotal() {
    return getLabel(labelPolicyTotal);
  }

  public String getLabelCommercialAuto() {
    return getLabel(labelCommercialAuto);
  }

  public String getLabelAgriculturalOutput() {
    return getLabel(labelAgriculturalOutput);
  }

  public String getLabelGeneralLiability() {
    return getLabel(labelGeneralLiability);
  }

  public String getLabelPolicyCoverages() {
    return getLabel(labelPolicywideCoverages);
  }

  public void clickButtonRateNow() {
    clickObject(buttonRateNow);
  }

  // public void populatePage(DataTable table) {
  // List<List<String>> data = table.asLists();
  // for (List<?> item : data) {
  // String field = (String) item.get(0);
  // String value = (String) item.get(1);
  // if (!value.equals("")) {
  // sysOut("({Field}" + field + ", {Value}" + value + ");");
  // switch (field.toLowerCase()) {
  // case "field1":
  // setEdit1(value);
  // break;
  // case "field2":
  // setEdit2(value);
  // break;
  // case "fieldN":
  // setEditN(value);
  // break;
  // default:
  // sysOut("[" + field + "]" + ISelenium.FIELD_NOT_CODED);
  // break;
  // }
  // }
  // }
  // }
  public void validatePage(DataTable table) {
    final Map<String, String> expected = new HashMap<>();
    final Map<String, String> actual = new HashMap<>();
    final List<List<String>> data = table.asLists();
    for (final List<?> item : data) {
      final String field = (String) item.get(0);
      final String value = (String) item.get(1);
      if (!value.equals("")) {
        expected.put(field, value);
        switch (field.toLowerCase()) {
          case "policy total":
            actual.put(field, getLabelPolicyTotal());
            break;
          case "commercial auto":
            actual.put(field, getLabelCommercialAuto());
            break;
          case "agricultural output":
            actual.put(field, getLabelAgriculturalOutput());
            break;
          case "general liability":
            actual.put(field, getLabelGeneralLiability());
            break;
          case "policywide coverages":
            actual.put(field, getLabelGeneralLiability());
            break;
          default:
            Environment.sysOut("[" + field + "]" + ISelenium.FIELD_NOT_CODED);
            break;
        }
      }
    }
    Assert.assertSame(getPageTitle() + " validatePage", expected, actual);
  }
}
