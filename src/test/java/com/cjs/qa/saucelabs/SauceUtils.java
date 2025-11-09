package com.cjs.qa.saucelabs;

import com.cjs.qa.core.Environment;
import com.cjs.qa.utilities.IExtension;
import com.cjs.qa.utilities.JavaHelpers;
import com.saucelabs.saucerest.SauceREST;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;

public final class SauceUtils {
  public static final String DEFAULT_HOST = "sauceconnect.berkley-bts" + IExtension.COM;
  public static final String DEFAULT_PORT = "4445";
  public static final String DEFAULT_TUNNEL_IDENTIFIER = "prodSLTunnel";
  public static final String DEFAULT_PARENT_TUNNEL = "berkley-technology-services";
  public static final String DEFAULT_USER_NAME = "btsqatest";
  public static final String DEFAULT_ACCESS_KEY = "ff17d4b9-b11d-487c-826d-81b856993668";
  private static SauceREST sauceRESTClient;

  private SauceUtils() { }

  private static SauceREST getSauceRestClient(String userName, String accessKey) {
    if (sauceRESTClient == null) {
      sauceRESTClient = new SauceREST(userName, accessKey);
    }
    return sauceRESTClient;
  }

  public static void updateResults(
      String userName, String accessKey, boolean testResults, String sessionId) {
    final SauceREST sauceREST = getSauceRestClient(userName, accessKey);
    final Map<String, Object> updates = new HashMap<>();
    addBuildNumberToUpdate(updates);
    updates.put("passed", testResults);
    sauceREST.updateJobInfo(sessionId, updates);
  }

  /**
   * Populates the <code>updates</code> map with the value of the system property/environment
   * variable with the following name:
   *
   * <ol>
   *   <li>SAUCE_BAMBOO_BUILDNUMBER
   *   <li>JENKINS_BUILD_NUMBER
   *   <li>BUILD_TAG
   *   <li>BUILD_NUMBER
   *   <li>TRAVIS_BUILD_NUMBER
   *   <li>CIRCLE_BUILD_NUM
   * </ol>
   *
   * @param updates String,Object pair containing job updates
   */
  private static void addBuildNumberToUpdate(Map<String, Object> updates) {
    for (Entry entry : getBuildNumerKeyValues().entrySet()) {
      String key = (String) entry.getKey();
      String value = (String) entry.getValue();
      String buildNumber = JavaHelpers.readPropertyOrEnv(value, null);
      Environment.sysOut(
          "Key:[" + key + "], Value:[" + value + "], buildNumber:[" + buildNumber + "]");
      if (JavaHelpers.hasValue(buildNumber)) {
        updates.put("build", buildNumber);
        break;
      }
    }
  }

  private static Map<String, String> getBuildNumerKeyValues() {
    // Defaults
    final Map<String, String> map = new HashMap<>();
    map.put("Bamboo", "SAUCE_BAMBOO_BUILDNUMBER");
    map.put("Jenkins", "JENKINS_BUILD_NUMBER");
    map.put("BUILD_TAG", "BUILD_TAG");
    map.put("BUILD_NUMBER", "BUILD_NUMBER");
    map.put("TRAVIS_BUILD_NUMBER", "TRAVIS_BUILD_NUMBER");
    map.put("CIRCLE_BUILD_NUM", "CIRCLE_BUILD_NUM");
    return map;
  }

  private static Map<String, Object> getDesiredCapabilitiesDefaults() {
    // Defaults
    final Map<String, Object> map = new HashMap<>();
    map.put("browser", "chrome");
    map.put("forkCount", 1);
    // Selenium-Specific Options
    map.put("browserName", "chrome");
    map.put("version", "latest");
    map.put("platform", "WINDOWS");
    // Other Selenium Options
    // map.put("seleniumVersion", "")
    // map.put("chromedriverVersion", "")
    // map.put("iedriverVersion", "")
    // General Options
    // Alerts
    map.put("autoAcceptAlerts", true);
    // Test Annotation
    map.put("name", "");
    map.put("build", "");
    map.put("tags", "");
    // map.put("passed", "")
    // map.put("custom-data", "{\"release\": \"1.0\", \"commit\":
    // \"0k392a9dkjr\", \"staging\": true,\"execution_number\":
    // 5,\"server\": \"server.com\"}")
    // Timeouts
    map.put("maxDuration", 10800);
    map.put("commandTimeout", 300);
    map.put("idleTimeout", 1000);
    // Sauce Testing Options
    map.put("host", DEFAULT_HOST);
    map.put("port", DEFAULT_PORT);
    map.put("userName", DEFAULT_USER_NAME);
    map.put("accessKey", DEFAULT_ACCESS_KEY);
    map.put("tunnelIdentifier", DEFAULT_TUNNEL_IDENTIFIER);
    map.put("parentTunnel", DEFAULT_PARENT_TUNNEL);
    map.put("screenResolution", "2560x1600"); // 1280x960
    map.put("timeZone", "Chicago");
    // map.put("avoidProxy", true)
    // Optional Sauce Testing Features
    // Improves Performance
    map.put("recordVideo", false);
    map.put("videoUploadOnPass", false);
    map.put("recordScreenshots", false);
    map.put("recordLogs", false);
    map.put("webdriver.remote.quietExceptions", false);
    map.put("extendedDebugging", false);
    return map;
  }

  public static DesiredCapabilities setDesiredCapabilitiesAutoIT(
      DesiredCapabilities desiredCapabilities, String executable, boolean background) {
    executable = "authenticate.exe";
    background = true;
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("executable", executable);
    jsonObject.put("background", String.valueOf(background));
    desiredCapabilities.setCapability("prerun", jsonObject);
    return desiredCapabilities;
  }

  public static DesiredCapabilities setDesiredCapabilitiesDefaults(
      DesiredCapabilities desiredCapabilities) {
    final Map<String, Object> propertiesDefault = getDesiredCapabilitiesDefaults();
    final Map<String, Object> propertiesDefined = new HashMap<>();
    final Map<String, Object> propertiesUndefined = new HashMap<>();
    Object propertyDefault = null;
    for (final Entry entry : propertiesDefault.entrySet()) {
      final String setting = (String) entry.getKey();
      final Object propertyDefined = System.getProperty(setting);
      if (propertyDefined != null) {
        propertiesDefined.put(setting, propertyDefined);
      } else {
        propertiesUndefined.put(setting, entry.getValue());
      }
    }
    Environment.sysOut("Defined Properties (" + propertiesDefined.size() + ")");
    for (final Entry entry : propertiesDefined.entrySet()) {
      final String setting = (String) entry.getKey();
      final Object propertyDefined = System.getProperty(setting);
      Environment.sysOut("***Defined***-" + setting + ":[" + propertyDefined + "]");
      desiredCapabilities.setCapability(setting, propertyDefined);
    }
    Environment.sysOut("Undefined Properties (" + propertiesUndefined.size() + ")");
    for (final Entry entry : propertiesUndefined.entrySet()) {
      final String setting = (String) entry.getKey();
      propertyDefault = propertiesDefault.get(setting);
      Environment.sysOut("***Undefined***-" + setting + ":[" + propertyDefault + "]");
      System.setProperty(setting, String.valueOf(propertyDefault));
      desiredCapabilities.setCapability(setting, propertyDefault);
    }
    return desiredCapabilities;
  }
}
