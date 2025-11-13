package com.cjs.qa.selenium;

import com.cjs.qa.core.Environment;
import com.cjs.qa.utilities.Constants;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
// HtmlUnitDriverOptions not available in this version
import org.openqa.selenium.ie.InternetExplorerDriver;
// PhantomJS removed - use Chrome/Firefox headless instead
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.safari.SafariDriver;

public class Selenium extends Page implements ISelenium {
  private double screenshotCounter = 0;
  private Capabilities capabilities = null;
  private SessionId sessionId = null;

  /**
   * @param webDriver
   */
  public Selenium(WebDriver webDriver) {
    super(webDriver);
  }

  public void getSessionInformation() {
    if (getWebDriver() instanceof RemoteWebDriver) {
      setCapabilities(((RemoteWebDriver) getWebDriver()).getCapabilities());
      setSessionId(((RemoteWebDriver) getWebDriver()).getSessionId());
    } else if (getWebDriver() instanceof ChromeDriver) {
      setCapabilities(((ChromeDriver) getWebDriver()).getCapabilities());
      setSessionId(((ChromeDriver) getWebDriver()).getSessionId());
    } else if (getWebDriver() instanceof EdgeDriver) {
      setCapabilities(((EdgeDriver) getWebDriver()).getCapabilities());
      setSessionId(((EdgeDriver) getWebDriver()).getSessionId());
    } else if (getWebDriver() instanceof FirefoxDriver) {
      setCapabilities(((FirefoxDriver) getWebDriver()).getCapabilities());
      setSessionId(((FirefoxDriver) getWebDriver()).getSessionId());
    } else if (getWebDriver() instanceof InternetExplorerDriver) {
      setCapabilities(((InternetExplorerDriver) getWebDriver()).getCapabilities());
      setSessionId(((InternetExplorerDriver) getWebDriver()).getSessionId());
    // PhantomJS support removed - use Chrome/Firefox headless instead
    } else if (getWebDriver() instanceof HtmlUnitDriver) {
      setCapabilities(((RemoteWebDriver) getWebDriver()).getCapabilities());
      setSessionId(((RemoteWebDriver) getWebDriver()).getSessionId());
    } else if (getWebDriver() instanceof RemoteWebDriver) {
      setCapabilities(((RemoteWebDriver) getWebDriver()).getCapabilities());
      setSessionId(((RemoteWebDriver) getWebDriver()).getSessionId());
    } else if (getWebDriver() instanceof SafariDriver) {
      setCapabilities(((SafariDriver) getWebDriver()).getCapabilities());
      setSessionId(((SafariDriver) getWebDriver()).getSessionId());
    }
    // Note: OperaDriver support commented out
    // else if (getWebDriver() instanceof OperaDriver) {
    //   setCapabilities(((OperaDriver) webDriver).getCapabilities());
    //   setSessionId(((OperaDriver) webDriver).getSessionId());
    // }
    Environment.sysOut(toString());
  }

  public double getScreenshotCounter() {
    return screenshotCounter;
  }

  /**
   * @param screenshotCounter
   */
  public void setScreenshotCounter(double screenshotCounter) {
    this.screenshotCounter = screenshotCounter;
  }

  public Capabilities getCapabilities() {
    return capabilities;
  }

  /**
   * @param capabilities
   */
  public void setCapabilities(Capabilities capabilities) {
    this.capabilities = capabilities;
  }

  public SessionId getSessionId() {
    return sessionId;
  }

  /**
   * @param driver
   */
  public void setDriver(WebDriver driver) {
    setWebDriver(driver);
  }

  /**
   * @param sessionId
   */
  public void setSessionId(SessionId sessionId) {
    this.sessionId = sessionId;
  }

  @Override
  public String toString() {
    final StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(Constants.NEWLINE);
    stringBuilder.append("Selenium WebDriver Information:");
    stringBuilder.append(Constants.NEWLINE);
    stringBuilder.append("Session ID:[" + getSessionId() + "]");
    stringBuilder.append(Constants.NEWLINE);
    stringBuilder.append("Capabilities:");
    stringBuilder.append(Constants.NEWLINE);
    stringBuilder.append("Browser:[" + getCapabilities().getBrowserName() + "]");
    stringBuilder.append(Constants.NEWLINE);
    stringBuilder.append("Version:[" + getCapabilities().getBrowserVersion() + "]");
    stringBuilder.append(Constants.NEWLINE);
    stringBuilder.append("Platform:[" + getCapabilities().getPlatformName() + "]");
    stringBuilder.append(Constants.NEWLINE);
    stringBuilder.append("toString:[" + getCapabilities().toString() + "]");
    return stringBuilder.toString();
  }
}
