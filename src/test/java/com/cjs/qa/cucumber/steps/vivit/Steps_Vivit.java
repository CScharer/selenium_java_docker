package com.cjs.qa.cucumber.steps.vivit;

import com.cjs.qa.core.Environment;
import com.cjs.qa.core.QAException;
import com.cjs.qa.selenium.ISelenium;
import com.cjs.qa.selenium.Selenium;
import com.cjs.qa.utilities.Constants;
import com.cjs.qa.utilities.Convert;
import com.cjs.qa.utilities.DateHelpers;
import com.cjs.qa.utilities.JavaHelpers;
import com.cjs.qa.utilities.Reports;
import com.cjs.qa.vivit.Vivit;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class Steps_Vivit extends Environment {
  protected Scenario scenarioObject = null;
  protected Selenium selenium = null;
  protected WebDriver webDriver = null;
  protected final Map<String, List> mapListTest = new HashMap<>();
  protected final List<Map<String, String>> listMapTest = new ArrayList<>();
  protected Map<String, String> mapTest = Reports.getColumnsString(Reports.STRING_SUMMARY);
  protected String browser = ISelenium.BROWSER_DEFAULT;
  protected double timeStarted = 0;
  protected double timeCompleted = 0;
  protected Vivit vivit;
  protected String application = null;
  protected String document = null;
  protected String xml = null;

  // mvn clean install -Dtest=forkCount="5" -Dtags="@Vivit"
  @Before
  public void testSetup(Scenario scenario) throws Exception {
    mapTest.put(
        "Started",
        DateHelpers.getCurrentDateTime(DateHelpers.FORMAT_US_STANDARD_DATE_TIME + ".SSS"));
    timeStarted = System.currentTimeMillis();
    scenarioObject = scenario;
    mapTest.put("API", "true");
    mapTest.put("DB", "true");
    mapTest.put("GUI", "true");
    mapTest.put("Test Name", scenarioObject.getName());
    String sessionId = scenarioObject.getId();
    mapTest.put("Scenario ID", sessionId);
    mapTest.put("Session ID", sessionId);
    Environment.sysOut("User Name:[" + Constants.CURRENT_USER + "]");
    Environment.sysOut("mapTest:" + mapTest.toString() + " Starting...");
    if (Environment.environmentSet == false) {
      Environment.setEnvironmentVariableValues();
    }
    try {
      if (mapTest.get("GUI").equals("true")) {
        webDriver = ISelenium.browserProfiling(browser, scenarioObject);
        selenium = new Selenium(webDriver);
        selenium.getSessionInformation();
        sessionId = selenium.getSessionId().toString();
        mapTest.put("Session ID", sessionId);
      }
    } catch (final Exception e) {
      Environment.sysOut(e);
    }
    Environment.sysOut("mapTest:" + mapTest.toString());
  }

  @After
  public void testTeardown() throws QAException {
    Environment.sysOut("mapTest:" + mapTest.toString() + " Tearing Down...");
    if (scenarioObject.isFailed()) {
      Environment.sysOutFailure("mapTest:" + mapTest.toString() + "] FAILED");
    }
    mapTest.put("Status", scenarioObject.getStatus().toString());
    mapTest.put(
        "Completed",
        DateHelpers.getCurrentDateTime(DateHelpers.FORMAT_US_STANDARD_DATE_TIME + ".SSS"));
    timeCompleted = System.currentTimeMillis();
    mapTest.put(
        "Elapsed (seconds)",
        String.valueOf((timeCompleted - timeStarted) / Constants.MILLISECONDS));
    listMapTest.add(mapTest);
    mapListTest.put("Summary", listMapTest);
    if (scenarioObject.isFailed()) {
      if (mapTest.get("GUI").equals("true")) {
        selenium.embedScreenshot(scenarioObject, webDriver);
      }
    }
    selenium.killBrowser(webDriver);
    try {
      Reports.createReportExcelLock(scenarioObject, mapListTest);
    } catch (final Exception e) {
      Environment.sysOut(e.getMessage());
    }
    Environment.sysOut("mapTest:" + mapTest.toString() + "] Torn Down!!!");
    scenarioObject = null;
    selenium = null;
  }

  @Given("^Environment Setup Vivit$")
  public void environment_Setup_Vivit(DataTable table) throws Throwable {
    browser = "";
    final List<List<String>> data = table.asLists();
    for (final List<?> item : data) {
      final String field = (String) item.get(0);
      final String value = (String) item.get(1);
      if (!value.equals("")) {
        switch (field.toLowerCase()) {
          case "browser":
            browser = value.toUpperCase().trim();
            // browser = Environment.getBrowser();
            mapTest.put("Browser", browser);
            break;
          case "company":
            final String company = value.toUpperCase().trim();
            mapTest.put("Company", company);
            break;
          case "lob":
            final String lob = value.toUpperCase().trim();
            mapTest.put("LOB", lob);
            break;
          case "environment":
            final String environment = value.toUpperCase().trim();
            mapTest.put("Environment", environment);
            break;
        }
        Environment.sysOut(field + ":[" + value + "]");
      }
    }
    // if (browser.equals(""))
    // {
    // browser = Environment.getBrowser();
    // webDriver = selenium.browserProfiling(webDriver, browser,
    // Environment.isRunRemote());
    // sessionId = (((RemoteWebDriver)
    // webDriver).getSessionId()).toString();
    // Environment.sysOut("mapTest:" + mapTest.toString());
    // }
    vivit = new Vivit(webDriver);
  }

  // TODO ************************************************** GUI
  // *********************************************************
  @Given("^pGiven \"([^\"]*)\"$")
  public void pGiven(String scenarioName) throws Throwable {
    mapTest.put("Test Name", scenarioName);
    Environment.sysOut("pGiven:[" + scenarioName + "]");
    final String randomNumber = JavaHelpers.generateRandomInteger(1, 10, 2);
    if (randomNumber.equals("1")) {
      // throw new Exception(scenarioName);
      // Assert.fail(scenarioName);
      addScenarioError(
          "Random Error Generator. randomNumber:["
              + randomNumber
              + "]"
              + Constants.NEWLINE
              + mapTest.toString().replaceAll(", ", Constants.NEWLINE));
    }
    Assert.assertTrue(Environment.scenarioErrors.size() == 0);
    // webDriver.get(VivitEnvironment.URL_LOGIN);
  }

  @And("^pAnd$")
  public void pAnd() throws Throwable {
    Environment.sysOut("pAnd");
    // webDriver.get("http://www.vivit-worldwide.org/page/2017board");
  }

  @But("^pBut$")
  public void pBut() throws Throwable {
    Environment.sysOut("pBut");
    // webDriver.get("http://www.vivit-worldwide.org/staff/");
  }

  @When("^pWhen$")
  public void pWhen() throws Throwable {
    Environment.sysOut("pWhen");
    // webDriver.get("http://www.vivit-worldwide.org/?page=Local_Chapters");
  }

  @Then("^pThen$")
  public void pThen() throws Throwable {
    Environment.sysOut("pThen");
    // webDriver.get("http://www.vivit-worldwide.org/?page=LocalUserGroups");
    // webDriver.get("http://www.vivit-worldwide.org/?page=SIGS");
  }

  @Given("^Board of Directors Page \"([^\"]*)\" \"([^\"]*)\"$")
  public void board_of_Directors_Page(String scenarioName, String url) throws Throwable {
    mapTest.put("Scenario Name", scenarioName);
    Environment.sysOut(
        "board_of_Directors_Page-scenarioName;[" + scenarioName + "], url:[" + url + "]");
    webDriver.get(url);
  }

  @Then(
      "^Board Member \"([^\"]*)\" Exists \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\","
          + " \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
  public void board_Member_Exists(
      String name,
      String title,
      String eMail,
      String company,
      String address,
      String country,
      String duties)
      throws Throwable {
    Environment.sysOut(
        "board_Member_Exists-name:["
            + name
            + "], title:["
            + title
            + "], eMail:["
            + eMail
            + "], company:["
            + company
            + "], address:["
            + address
            + "], country:["
            + country
            + "], duties:["
            + duties
            + "]");
    final DataTable table =
        Convert.fromListToDataTable(
            Arrays.asList(
                Arrays.asList("Name", name),
                Arrays.asList("Title", title),
                Arrays.asList("eMail", eMail),
                Arrays.asList("Company", company),
                Arrays.asList("Address", address),
                Arrays.asList("Country", country),
                Arrays.asList("Duties", duties)));
    Environment.sysOut("table:[" + table.toString() + "]");
    // vivit.BoDPage.validatePage(table);
  }

  @Given("^Staff Page \"([^\"]*)\" \"([^\"]*)\"$")
  public void staff_Page(String scenarioName, String url) throws Throwable {
    Environment.sysOut("staff_Page-scenarioName;[" + scenarioName + "], url:[" + url + "]");
    mapTest.put("Scenario Name", scenarioName);
    webDriver.get(url);
  }

  @Then("^Staff Member \"([^\"]*)\" Exists \"([^\"]*)\", \"([^\"]*)\"$")
  public void staff_Member_Exists(String nameTitle, String eMail, String phone) throws Throwable {
    Environment.sysOut(
        "staff_Member_Exists-nameTitle:["
            + nameTitle
            + "], eMail:["
            + eMail
            + "], phone:["
            + phone
            + "]");
    final DataTable table =
        Convert.fromListToDataTable(
            Arrays.asList(
                Arrays.asList("Name-Title", nameTitle),
                Arrays.asList("eMail", eMail),
                Arrays.asList("Phone", phone)));
    Environment.sysOut("table:[" + table.toString() + "]");
    // vivit.StaffPage.validatePage(table);
  }

  // *********************************************************
  // TODO ************************************************ API REST
  // ******************************************************

  // ***************************************************** API REST
  // ******************************************************
  // TODO ************************************************ API SOAP
  // ******************************************************

  // ***************************************************** API SOAP
  // ******************************************************
  // TODO *********************************************** API SOAPUI
  // *****************************************************

  // **************************************************** API SOAPUI
  // *****************************************************
}
