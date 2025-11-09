package com.cjs.qa.core;

import com.cjs.qa.americanairlines.AmericanAirlinesEnvironment;
import com.cjs.qa.dropbox.DropboxEnvironment;
import com.cjs.qa.everyonesocial.EveryoneSocialEnvironment;
import com.cjs.qa.google.GoogleEnvironment;
import com.cjs.qa.hardees.HardeesEnvironment;
import com.cjs.qa.iadhs.IaDhsEnvironment;
import com.cjs.qa.linkedin.LinkedInEnvironment;
import com.cjs.qa.marlboro.MarlboroEnvironment;
import com.cjs.qa.microsoft.MicrosoftEnvironment;
import com.cjs.qa.microsoft.excel.IExcel;
import com.cjs.qa.microsoft.excel.xls.XLS;
import com.cjs.qa.polkcounty.PolkCountyEnvironment;
import com.cjs.qa.selenium.ISelenium;
import com.cjs.qa.united.UnitedEnvironment;
import com.cjs.qa.utilities.Constants;
import com.cjs.qa.utilities.DateHelpers;
import com.cjs.qa.utilities.FSO;
import com.cjs.qa.utilities.IExtension;
import com.cjs.qa.utilities.JavaHelpers;
import com.cjs.qa.utilities.SoftAssert;
import com.cjs.qa.utilities.XML;
import com.cjs.qa.vivit.VivitEnvironment;
import com.cjs.qa.wellmark.WellmarkEnvironment;
import io.cucumber.java.Scenario;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.BasicConfigurator;
import org.junit.Assert;

public class Environment {
  public static final String NOT_FOUND_USING_DEFAULT = " NOT FOUND.  USING DEFAULT-";
  public static final String COMPANY = "Core";
  public static final String EMAIL_SIGNATURE =
      FSO.fileReadAll(Constants.PATH_OUTLOOK_SIGNATURES + "MSN" + IExtension.HTM);
  public static boolean environmentSet = false;
  public static final String FILE_CONFIG =
      Constants.PATH_ROOT
          + "Configurations"
          + Constants.DELIMETER_PATH
          + "Environments"
          + IExtension.XML;
  public static String FOLDER_DATA = Constants.PATH_FILES_DATA + COMPANY + Constants.DELIMETER_PATH;
  public static String FILE_LOG = FOLDER_DATA + "Log_" + COMPANY + IExtension.LOG;
  public static List<String> classExlusionList = Arrays.asList("");
  public static Map<Integer, String> scenarioErrors = new HashMap<>();
  private static String gridHub = null;
  private static String gridPort = null;
  public static final String FILE_CONFIG_XML = FSO.fileReadAll(FILE_CONFIG);
  public static final String CURRENT_USER = Constants.CURRENT_USER;
  public static SoftAssert softAssert = new SoftAssert();
  private static boolean overrideUser = false;
  private static String browser = ISelenium.BROWSER_DEFAULT;
  private static String company = "CJS";
  private static String environment = "TST";
  private static int timeOutPage = 300;
  private static int timeOutElement = 30;
  private static int timeOutAlert = 5;
  private static boolean runRemote = true;
  private static boolean scrollToObjects = true;
  private static boolean highlightObjects = true;
  private static boolean flashObjects = true;
  private static boolean logAll = true;
  private static boolean logAPI = true;
  private static boolean logSQL = true;

  public Environment() {
    BasicConfigurator.configure();
    setEnvironmentVariableValues();
  }

  public void addScenarioError(String error) {
    Environment.scenarioErrors.put((Environment.scenarioErrors.size() + 1), error);
  }

  public static void clearScenarioErrors() {
    Environment.scenarioErrors = new HashMap<>();
  }

  public static String getSheetFailure(String filePathName, int defaultSheetCount, String policy)
      throws IOException, QAException {
    final XLS excel = new XLS(filePathName, IExcel.SHEET_SUMMARY);
    // int rowSummary = (excel.getRowCount(IExcel.SHEET_SUMMARY) + 1)
    int rowSummary = (excel.getSheetCount() - (defaultSheetCount - 1));
    excel.close();
    final String sheetNameFailure = JavaHelpers.formatNumber(rowSummary, "000");
    // + "_" + policy;
    return sheetNameFailure;
  }

  public static String getComputerName() {
    final Map<String, String> env = System.getenv();
    if (env.containsKey("COMPUTERNAME")) {
      return env.get("COMPUTERNAME").toUpperCase();
    } else if (env.containsKey("HOSTNAME")) {
      return env.get("HOSTNAME").toUpperCase();
    } else {
      return "Unknown Computer";
    }
  }

  public static void setScrollToObject(boolean scrollToObjects) {
    sysOut(
        "Environment Variable "
            + Constants.QUOTE_DOUBLE
            + "SCROLL_TO_OBJECTS"
            + Constants.QUOTE_DOUBLE
            + "["
            + scrollToObjects
            + "]");
    Environment.scrollToObjects = scrollToObjects;
  }

  public static void setEnvironmentVariableValues() {
    String config = Constants.CURRENT_USER;
    final String hostName = getComputerName();
    setOverrideUser("OVERRIDE_USER", hostName);
    if (overrideUser) {
      config = hostName;
    }
    sysOut("Using Configurations for [" + config + "]");
    setBrowser("BROWSER", config);
    setCompany("COMPANY", config);
    setEnvironment("ENVIRONMENT", config);
    setTimeOutPage("TIME_OUT_PAGE", config);
    setTimeOutElement("TIME_OUT_ELEMENT", config);
    setTimeOutAlert("TIME_OUT_ALERT", config);
    setrunRemote("RUN_REMOTE", config);
    setScrollToObjects("SCROLL_TO_OBJECTS", config);
    setHighlightObjects("HIGHLIGHT_OBJECTS", config);
    setFlashObjects("FLASH_OBJECTS", config);
    setLogAll("LOG_ALL", config);
    setLogAPI("LOG_API", config);
    setLogSQL("LOG_SQL", config);
    environmentSet = true;
  }

  public static void setEnvironmentFileStructure(String project) {
    sysOut("Setting Environment File Structure for the [" + project + "] Project");
    project = project.toLowerCase();
    switch (project) {
      case "americanairlines":
        FOLDER_DATA = AmericanAirlinesEnvironment.FOLDER_DATA;
        FILE_LOG = AmericanAirlinesEnvironment.FILE_LOG;
        break;
      case "core":
      case "jenkins":
      case "testclasssetup":
        break;
      case "dropbox":
        FOLDER_DATA = DropboxEnvironment.FOLDER_DATA;
        FILE_LOG = DropboxEnvironment.FILE_LOG;
        break;
      case "everyonesocial":
        FOLDER_DATA = EveryoneSocialEnvironment.FOLDER_DATA;
        FILE_LOG = EveryoneSocialEnvironment.FILE_LOG;
        break;
      case "google":
        FOLDER_DATA = GoogleEnvironment.FOLDER_DATA;
        FILE_LOG = GoogleEnvironment.FILE_LOG;
        break;
      case "hardees":
        FOLDER_DATA = HardeesEnvironment.FOLDER_DATA;
        FILE_LOG = HardeesEnvironment.FILE_LOG;
        break;
      case "iadhs":
        FOLDER_DATA = IaDhsEnvironment.FOLDER_DATA;
        FILE_LOG = IaDhsEnvironment.FILE_LOG;
        break;
      case "linkedin":
        FOLDER_DATA = LinkedInEnvironment.FOLDER_DATA;
        FILE_LOG = LinkedInEnvironment.FILE_LOG;
        break;
      case "marlboro":
        FOLDER_DATA = MarlboroEnvironment.FOLDER_DATA;
        FILE_LOG = MarlboroEnvironment.FILE_LOG;
        break;
      case "microsoft":
        FOLDER_DATA = MicrosoftEnvironment.FOLDER_DATA;
        FILE_LOG = MarlboroEnvironment.FILE_LOG;
        break;
      case "polkcounty":
        FOLDER_DATA = PolkCountyEnvironment.FOLDER_DATA;
        FILE_LOG = PolkCountyEnvironment.FILE_LOG;
        break;
      case "united":
        FOLDER_DATA = UnitedEnvironment.FOLDER_DATA;
        FILE_LOG = UnitedEnvironment.FILE_LOG;
        break;
      case "vivit":
        FOLDER_DATA = VivitEnvironment.FOLDER_DATA;
        FILE_LOG = VivitEnvironment.FILE_LOG;
        break;
      case "wellmark":
        FOLDER_DATA = WellmarkEnvironment.FOLDER_DATA;
        FILE_LOG = WellmarkEnvironment.FILE_LOG;
        break;
      default:
        Assert.fail(
            "The setEnvironmentFileStructure has not been defined for the ["
                + project
                + "] Project");
        break;
    }
  }

  private static String getStackInfo() {
    String stack = null;
    final StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
    for (final StackTraceElement stackTraceElement : stackTraceElements) {
      final String className = stackTraceElement.getClassName();
      if (!classExlusionList.contains(className) && className.contains(".Pages")) {
        stack =
            stackTraceElement.getFileName()
                + Constants.DELIMETER_LIST
                + stackTraceElement.getMethodName()
                + Constants.DELIMETER_LIST
                + "Line ("
                + stackTraceElement.getLineNumber()
                + ")";
        break;
      }
    }
    return stack;
  }

  private static String getSysOutHeader() {
    return DateHelpers.getCurrentDateAndTime()
        + Constants.DELIMETER_LIST
        + Constants.CURRENT_USER
        + Constants.DELIMETER_LIST
        + getComputerName()
        + Constants.DELIMETER_LIST;
  }

  public static void sysOut(String value) {
    if (isLogAll()) {
      StringBuilder stringBuilder = new StringBuilder();
      // stringBuilder.append(getSysOutHeader())
      // stringBuilder.append(getStackInfo())
      // stringBuilder.append(Constants.DELIMETER_LIST)
      stringBuilder.append(value);
      System.out.println(stringBuilder.toString());
      // stringBuilder.append(Constants.NEWLINE)
      // FSO.fileWrite(FILE_LOG, stringBuilder.toString(), true)
    }
  }

  public static void sysOut(int value) {
    sysOut(String.valueOf(value));
  }

  public static void sysOut(Scenario senario, String value) {
    sysOut(value);
    senario.log(value);
  }

  public static void sysOut(Exception e) {
    sysOut(e.getMessage());
    e.printStackTrace();
    Assert.fail();
  }

  public static void sysOut(Throwable e) {
    sysOut(e.getMessage());
    e.printStackTrace();
  }

  public static void sysOut(String[] value) {
    sysOut(Arrays.toString(value));
  }

  public static void sysOut(List<String> value) {
    sysOut(value.toString());
  }

  public static void sysOut(Map<String, String> value) {
    sysOut(value.toString());
  }

  public static void sysOut(StackTraceElement[] value) {
    sysOut(Arrays.toString(value));
  }

  public static void sysOut(boolean value) {
    sysOut(String.valueOf(value));
  }

  public static String getGridHub() {
    return gridHub;
  }

  public static String getGridPort() {
    return gridPort;
  }

  public static void sysOutFailure(String value) {
    sysOut(value);
    final String messageOut = getSysOutHeader() + value;
    if (FILE_LOG == null) {
      sysOut("FILE_LOG:[" + FILE_LOG + "]" + Constants.NEWLINE + value);
    } else {
      FSO.fileWrite(FILE_LOG, messageOut + Constants.TAB, true);
      FSO.fileWrite(FILE_LOG, "FAILED", true);
      FSO.fileWrite(FILE_LOG, Constants.NL, true);
    }
  }

  private static void setOverrideUser(String node, String config) {
    try {
      final StringBuilder stringBuilder = new StringBuilder();
      final String value = XML.getNode(FILE_CONFIG_XML, config, node);
      if (value != null) {
        overrideUser = Boolean.valueOf(value);
      } else {
        stringBuilder.append(node + NOT_FOUND_USING_DEFAULT);
      }
      stringBuilder.append(
          node
              + ":["
              + overrideUser
              + "], Computer/Host Name:["
              + config
              + "], UserID:["
              + Constants.CURRENT_USER
              + "]");
      sysOut(stringBuilder.toString());
    } catch (final Exception e) {
      sysOut(e);
    }
  }

  private static void setBrowser(String node, String config) {
    try {
      final StringBuilder stringBuilder = new StringBuilder();
      final String value = XML.getNode(FILE_CONFIG_XML, config, node);
      if (value != null) {
        browser = value;
      } else {
        stringBuilder.append(node + NOT_FOUND_USING_DEFAULT);
      }
      stringBuilder.append(node + ":[" + browser + "]");
      sysOut(stringBuilder.toString());
    } catch (final Exception e) {
      sysOut(e);
    }
  }

  private static void setCompany(String node, String config) {
    try {
      final StringBuilder stringBuilder = new StringBuilder();
      final String value = XML.getNode(FILE_CONFIG_XML, config, node);
      if (value != null) {
        company = value;
      } else {
        stringBuilder.append(node + NOT_FOUND_USING_DEFAULT);
      }
      stringBuilder.append(node + ":[" + company + "]");
      sysOut(stringBuilder.toString());
    } catch (final Exception e) {
      sysOut(e);
    }
  }

  private static void setEnvironment(String node, String config) {
    try {
      final StringBuilder stringBuilder = new StringBuilder();
      final String value = XML.getNode(FILE_CONFIG_XML, config, node);
      if (value != null) {
        environment = value;
      } else {
        stringBuilder.append(node + NOT_FOUND_USING_DEFAULT);
      }
      stringBuilder.append(node + ":[" + environment + "]");
      sysOut(stringBuilder.toString());
    } catch (final Exception e) {
      sysOut(e);
    }
  }

  private static void setTimeOutPage(String node, String config) {
    try {
      final StringBuilder stringBuilder = new StringBuilder();
      final String value = XML.getNode(FILE_CONFIG_XML, config, node);
      if (value != null) {
        timeOutPage = Integer.valueOf(value);
      } else {
        stringBuilder.append(node + NOT_FOUND_USING_DEFAULT);
      }
      stringBuilder.append(node + ":[" + timeOutPage + "]");
      sysOut(stringBuilder.toString());
    } catch (final Exception e) {
      sysOut(e);
    }
  }

  private static void setTimeOutElement(String node, String config) {
    try {
      final StringBuilder stringBuilder = new StringBuilder();
      final String value = XML.getNode(FILE_CONFIG_XML, config, node);
      if (value != null) {
        timeOutElement = Integer.valueOf(value);
      } else {
        stringBuilder.append(node + NOT_FOUND_USING_DEFAULT);
      }
      stringBuilder.append(node + ":[" + timeOutElement + "]");
      sysOut(stringBuilder.toString());
    } catch (final Exception e) {
      sysOut(e);
    }
  }

  private static void setTimeOutAlert(String node, String config) {
    try {
      final StringBuilder stringBuilder = new StringBuilder();
      final String value = XML.getNode(FILE_CONFIG_XML, config, node);
      if (value != null) {
        timeOutAlert = Integer.valueOf(value);
      } else {
        stringBuilder.append(node + NOT_FOUND_USING_DEFAULT);
      }
      stringBuilder.append(node + ":[" + timeOutAlert + "]");
      sysOut(stringBuilder.toString());
    } catch (final Exception e) {
      sysOut(e);
    }
  }

  private static void setrunRemote(String node, String config) {
    try {
      final StringBuilder stringBuilder = new StringBuilder();
      final String value = XML.getNode(FILE_CONFIG_XML, config, node);
      if (value != null) {
        runRemote = Boolean.valueOf(value);
      } else {
        stringBuilder.append(node + NOT_FOUND_USING_DEFAULT);
      }
      stringBuilder.append(node + ":[" + runRemote + "]");
      sysOut(stringBuilder.toString());
    } catch (final Exception e) {
      sysOut(e);
    }
  }

  private static void setScrollToObjects(String node, String config) {
    try {
      final StringBuilder stringBuilder = new StringBuilder();
      final String value = XML.getNode(FILE_CONFIG_XML, config, node);
      if (value != null) {
        scrollToObjects = Boolean.valueOf(value);
      } else {
        stringBuilder.append(node + NOT_FOUND_USING_DEFAULT);
      }
      stringBuilder.append(node + ":[" + scrollToObjects + "]");
      sysOut(stringBuilder.toString());
    } catch (final Exception e) {
      sysOut(e);
    }
  }

  private static void setHighlightObjects(String node, String config) {
    try {
      final StringBuilder stringBuilder = new StringBuilder();
      final String value = XML.getNode(FILE_CONFIG_XML, config, node);
      if (value != null) {
        highlightObjects = Boolean.valueOf(value);
      } else {
        stringBuilder.append(node + NOT_FOUND_USING_DEFAULT);
      }
      stringBuilder.append(node + ":[" + highlightObjects + "]");
      sysOut(stringBuilder.toString());
    } catch (final Exception e) {
      sysOut(e);
    }
  }

  private static void setFlashObjects(String node, String config) {
    try {
      final StringBuilder stringBuilder = new StringBuilder();
      final String value = XML.getNode(FILE_CONFIG_XML, config, node);
      if (value != null) {
        flashObjects = Boolean.valueOf(value);
      } else {
        stringBuilder.append(node + NOT_FOUND_USING_DEFAULT);
      }
      stringBuilder.append(node + ":[" + flashObjects + "]");
      sysOut(stringBuilder.toString());
    } catch (final Exception e) {
      sysOut(e);
    }
  }

  private static void setLogAll(String node, String config) {
    try {
      final StringBuilder stringBuilder = new StringBuilder();
      final String value = XML.getNode(FILE_CONFIG_XML, config, node);
      if (value != null) {
        logAll = Boolean.valueOf(value);
      } else {
        stringBuilder.append(node + NOT_FOUND_USING_DEFAULT);
      }
      stringBuilder.append(node + ":[" + logAll + "]");
      sysOut(stringBuilder.toString());
    } catch (final Exception e) {
      sysOut(e);
    }
  }

  private static void setLogAPI(String node, String config) {
    try {
      final StringBuilder stringBuilder = new StringBuilder();
      final String value = XML.getNode(FILE_CONFIG_XML, config, node);
      if (value != null) {
        logAPI = Boolean.valueOf(value);
      } else {
        stringBuilder.append(node + NOT_FOUND_USING_DEFAULT);
      }
      stringBuilder.append(node + ":[" + logAPI + "]");
      sysOut(stringBuilder.toString());
    } catch (final Exception e) {
      sysOut(e);
    }
  }

  private static void setLogSQL(String node, String config) {
    try {
      final StringBuilder stringBuilder = new StringBuilder();
      final String value = XML.getNode(FILE_CONFIG_XML, config, node);
      if (value != null) {
        logSQL = Boolean.valueOf(value);
      } else {
        stringBuilder.append(node + NOT_FOUND_USING_DEFAULT);
      }
      stringBuilder.append(node + ":[" + logSQL + "]");
      sysOut(stringBuilder.toString());
    } catch (final Exception e) {
      sysOut(e);
    }
  }

  public static boolean isEnvironmentSet() {
    return environmentSet;
  }

  public static String getConfigFile() {
    return FILE_CONFIG;
  }

  public static String getCurrentUser() {
    return Constants.CURRENT_USER;
  }

  public static boolean isOverrideUser() {
    return overrideUser;
  }

  public static String getBrowser() {
    return browser;
  }

  public static String getCompany() {
    return company;
  }

  public static String getEnvironment() {
    return environment;
  }

  public static int getTimeOutPage() {
    return timeOutPage;
  }

  public static int getTimeOutElement() {
    return timeOutElement;
  }

  public static int getTimeOutAlert() {
    return timeOutAlert;
  }

  public static boolean isRunRemote() {
    return runRemote;
  }

  public static boolean isScrollToObjects() {
    return scrollToObjects;
  }

  public static boolean isHighlightObjects() {
    return highlightObjects;
  }

  public static boolean isFlashObjects() {
    return flashObjects;
  }

  public static boolean isLogAll() {
    return logAll;
  }

  public static boolean isLogAPI() {
    return logAPI;
  }

  public static boolean isLogSQL() {
    return logSQL;
  }
}
