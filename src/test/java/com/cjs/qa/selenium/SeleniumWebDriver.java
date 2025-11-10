package com.cjs.qa.selenium;

import com.cjs.qa.core.Environment;
import com.cjs.qa.core.QAException;
import com.cjs.qa.utilities.CommandLine;
import com.cjs.qa.utilities.Constants;
import com.cjs.qa.utilities.FSO;
import com.cjs.qa.utilities.IExtension;
import com.cjs.qa.utilities.JavaHelpers;
import com.cjs.qa.utilities.Processes;
import com.google.gson.JsonObject;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.velocity.texen.util.FileUtil;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
// HtmlUnitDriverOptions not available in this version
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class SeleniumWebDriver {
  public static final String OS_NAME = "os.name";
  public static final String PATH_SCREENSHOTS = Constants.PATH_SCREENSHOTS;
  public static final String FORMAT_SCREENSHOT = "###000";
  private String browser = Browser.CHROME;
  private Capabilities capabilities = null;
  private String gridHubName = "CSCHARER-LAPTOP";
  private int gridHubPort = 4444;
  private String gridHub = "http://" + getGridHubName() + ":" + getGridHubPort() + "/wd/hub";
  private String operatingSystem = System.getProperty(OS_NAME);
  // "Windows 10"
  private Page page = null;
  private boolean remote = true;
  private Scenario scenario = null;
  private int screenshots = 0;
  private SessionId sessionId = null;
  private WebDriver webDriver = null;
  private String vendorURL = null;
  private String version = null;

  /**
   * @param browser
   * @param remote
   * @param vendorURL
   * @param operatingSystem
   * @param version
   * @throws Throwable
   */
  public SeleniumWebDriver(
      String browser, boolean remote, String vendorURL, String operatingSystem, String version)
      throws Throwable {
    killBrowser();
    if (browser != null) {
      setBrowser(browser);
    }
    setRemote(remote);
    setVendorURL(vendorURL);
    if (operatingSystem == null) {
      operatingSystem = System.getProperty(OS_NAME);
      setOperatingSystem(operatingSystem);
    }
    setVersion(version);
    initializeWebDriver();
  }

  public void captureDesktopImage() {
    try {
      final BufferedImage image =
          new Robot()
              .createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
      ImageIO.write(image, "png", new File(getScreenshotFilePathName("Desktop")));
    } catch (final Exception e) {
      e.printStackTrace();
      Environment.sysOut(e.getMessage());
    }
  }

  /**
   * @param scrollAmount
   */
  public void captureScreenshot(int scrollAmount) {
    try {
      if (getWebDriver() != null) {
        String title = getWebDriver().getTitle();
        Environment.sysOut("title:[" + title + "]");
        title = FSO.fileValidateName(title);
        Environment.sysOut("title:[" + title + "]");
        final int heightScroll = getPage().getScrollHeight();
        final String pathFileName = getScreenshotFilePathName(title);
        if (heightScroll == 0) {
          final File screenshot =
              ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
          FileUtils.copyFile(screenshot, new File(pathFileName));
        } else {
          getPage().maximizeWindow();
          getPage().scrollToTop();
          final int heightWindow = getPage().getHeight();
          int heightScrolled = 0;
          Environment.sysOut(
              "windowHeight:[" + heightWindow + "], scrollHeight:[" + heightScroll + "]");
          final String pathTemp = PATH_SCREENSHOTS + getSessionId() + Constants.DELIMETER_PATH;
          FileUtil.mkdir(pathTemp);
          int fileNumber = 1;
          do {
            final File screenshot =
                ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(pathTemp);
            stringBuilder.append(title);
            stringBuilder.append("_");
            stringBuilder.append(JavaHelpers.formatNumber(fileNumber, FORMAT_SCREENSHOT));
            stringBuilder.append(".png");
            final String fileName = stringBuilder.toString();
            FileUtils.copyFile(screenshot, new File(fileName));
            getPage().scrollDown(scrollAmount);
            heightScrolled += scrollAmount;
            fileNumber++;
          } while (heightScrolled < heightScroll);
          try {
            final String pathFileNameNew = combineALLImages(pathTemp);
            FileUtils.copyFile(new File(pathFileNameNew), new File(pathFileName));
            FileUtils.deleteDirectory(new File(pathTemp));
          } catch (final Exception e) {
            e.printStackTrace();
          }
        }
      }
    } catch (final IOException e) {
      e.printStackTrace();
      Environment.sysOut(e.getMessage());
    }
  }

  /**
   * @param pathTemp
   * @return
   * @throws IOException
   * @throws InterruptedException
   */
  private String combineALLImages(String pathTemp) throws IOException {
    final List<String> listFiles = FSO.filesList(pathTemp, ".png");
    final List<File> listFile = new ArrayList<>();
    final int columns = 1;
    final int rows = listFiles.size();
    for (String filePathName : listFiles) {
      listFile.add(new File(filePathName));
    }
    final BufferedImage bufferedImageSample = ImageIO.read(listFile.get(0));
    // Initializing the final image
    final BufferedImage bufferedImageFinal =
        new BufferedImage(
            bufferedImageSample.getWidth() * columns,
            bufferedImageSample.getHeight() * rows,
            bufferedImageSample.getType());
    int index = 0;
    for (int indexRow = 0; indexRow < rows; indexRow++) {
      for (int indexColumn = 0; indexColumn < columns; indexColumn++) {
        final String filePathName = listFiles.get(indexRow);
        final String fileName =
            filePathName.substring(
                (filePathName.lastIndexOf(Constants.DELIMETER_PATH) + 1), filePathName.length());
        Environment.sysOut("Adding Image File:[" + index + "], [" + fileName + "]");
        final BufferedImage bufferedImageTemp = ImageIO.read(listFile.get(index));
        bufferedImageFinal
            .createGraphics()
            .drawImage(
                bufferedImageTemp,
                bufferedImageSample.getWidth() * indexColumn,
                bufferedImageSample.getHeight() * indexRow,
                null);
        index++;
      }
    }
    // final String newFile = pathTemp + "FinalImage" + IExtension.JPG
    final String newFile = pathTemp + "FinalImage" + IExtension.PNG;
    boolean success = ImageIO.write(bufferedImageFinal, "png", new File(newFile));
    Environment.sysOut("success:[" + success + "]");
    return newFile;
  }

  public String getBrowser() {
    return browser;
  }

  public Capabilities getCapabilities() {
    return capabilities;
  }

  public String getExecutableVersion(Capabilities capabilities) {
    String executableVersion = "";
    // (String)capabilities.getCapability("version")
    switch (getBrowser().toLowerCase(Locale.ENGLISH)) {
      case Browser.EDGE:
        executableVersion = (String) capabilities.getCapability("browserVersion");
        break;
      case Browser.CHROME:
      case Browser.FIREFOX:
      case Browser.IE:
      case Browser.INTERNET_EXPLORER:
      default:
        executableVersion = (String) capabilities.getCapability("version");
        break;
    }
    return executableVersion;
  }

  public String getGridHub() {
    return gridHub;
  }

  public String getGridHubName() {
    return gridHubName;
  }

  public int getGridHubPort() {
    return gridHubPort;
  }

  public String getOperatingSystem() {
    return operatingSystem;
  }

  public Page getPage() {
    return page;
  }

  public Scenario getScenario() {
    return scenario;
  }

  /**
   * @param title
   * @return
   */
  private String getScreenshotFilePathName(String title) {
    setScreenshots(getScreenshots() + 1);
    final StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(PATH_SCREENSHOTS);
    FileUtil.mkdir(stringBuilder.toString());
    stringBuilder.append(getBrowser().toLowerCase(Locale.ENGLISH));
    stringBuilder.append("_");
    stringBuilder.append(getSessionId());
    stringBuilder.append("_");
    stringBuilder.append(title);
    stringBuilder.append("_");
    stringBuilder.append(JavaHelpers.formatNumber(getScreenshots(), FORMAT_SCREENSHOT));
    stringBuilder.append(".png");
    return stringBuilder.toString();
  }

  public int getScreenshots() {
    return screenshots;
  }

  public SessionId getSessionId() {
    return sessionId;
  }

  public void getSessionInformation() {
    if (webDriver instanceof RemoteWebDriver || webDriver instanceof RemoteWebDriver) {
      setCapabilities(((RemoteWebDriver) webDriver).getCapabilities());
      setSessionId(((RemoteWebDriver) webDriver).getSessionId());
    } else if (webDriver instanceof ChromeDriver) {
      setCapabilities(((ChromeDriver) webDriver).getCapabilities());
      setSessionId(((ChromeDriver) webDriver).getSessionId());
    } else if (webDriver instanceof EdgeDriver) {
      setCapabilities(((EdgeDriver) webDriver).getCapabilities());
      setSessionId(((EdgeDriver) webDriver).getSessionId());
    } else if (webDriver instanceof FirefoxDriver) {
      setCapabilities(((FirefoxDriver) webDriver).getCapabilities());
      setSessionId(((FirefoxDriver) webDriver).getSessionId());
    } else if (webDriver instanceof InternetExplorerDriver) {
      setCapabilities(((InternetExplorerDriver) webDriver).getCapabilities());
      setSessionId(((InternetExplorerDriver) webDriver).getSessionId());
    } else if (webDriver instanceof PhantomJSDriver) {
      setCapabilities(((PhantomJSDriver) webDriver).getCapabilities());
      setSessionId(((PhantomJSDriver) webDriver).getSessionId());
    } else if (webDriver instanceof HtmlUnitDriver) {
      setCapabilities(((HtmlUnitDriver) webDriver).getCapabilities());
      // setSessionId(((HtmlUnitDriver) webDriver).getSessionId())
    } else if (webDriver instanceof SafariDriver) {
      setCapabilities(((SafariDriver) webDriver).getCapabilities());
      setSessionId(((SafariDriver) webDriver).getSessionId());
    } // Opera driver deprecated in Selenium 4
    // // else if (webDriver instanceof OperaDriver)
    // {
    //  // setCapabilities(((OperaDriver) webDriver).getCapabilities());
    //  // setSessionId(((OperaDriver) webDriver).getSessionId());
    // }
    Environment.sysOut("Selenium WebDriver Information:" + toString());
  }

  public String getVendorURL() {
    return vendorURL;
  }

  public String getVersion() {
    return version;
  }

  public WebDriver getWebDriver() {
    return webDriver;
  }

  public Capabilities getWebDriverCapabilities() {
    Capabilities capabilities = null;
    if (isRemote()) {
      capabilities = ((RemoteWebDriver) getWebDriver()).getCapabilities();
    } else {
      switch (getBrowser().toLowerCase(Locale.ENGLISH)) {
        case Browser.EDGE:
          capabilities = ((EdgeDriver) getWebDriver()).getCapabilities();
          break;
        case Browser.FIREFOX:
          capabilities = ((FirefoxDriver) getWebDriver()).getCapabilities();
          break;
        case Browser.IE:
        case Browser.INTERNET_EXPLORER:
          capabilities = ((InternetExplorerDriver) getWebDriver()).getCapabilities();
          break;
        case Browser.CHROME:
        default:
          capabilities = ((ChromeDriver) getWebDriver()).getCapabilities();
          break;
      }
    }
    return capabilities;
  }

  public void getWebDriverInfo() {
    try {
      if (getWebDriver() != null) {
        String browser = getBrowser();
        Environment.sysOut("browser:[" + browser + "]");
        final EDriverProperties eDriverProperties =
            EDriverProperties.fromString(browser.toUpperCase(Locale.ENGLISH));
        String webDriver = eDriverProperties.getPathDriver();
        Environment.sysOut("webDriver:[" + webDriver + "]");
        String executable = eDriverProperties.getPathBinary();
        Environment.sysOut("executable:[" + executable + "]");
        String webDriverVersion = getWebDriverVersion(webDriver);
        Environment.sysOut("webDriverVersion:[" + webDriverVersion + "]");
        getWebDriverProcesses(webDriver);
        Capabilities capabilities = getWebDriverCapabilities();
        Environment.sysOut("capabilities:[" + capabilities.toString() + "]");
        String executableVersion = getExecutableVersion(capabilities);
        Environment.sysOut("executableVersion:[" + executableVersion + "]");
        if (getBrowser().equalsIgnoreCase(Browser.CHROME)) {
          Map<String, Object> chromeCapabilities =
              (Map<String, Object>) capabilities.getCapability(Browser.CHROME);
          // for (Entry entry : chromeCapabilities.entrySet())
          // {
          // String key = (String) entry.getKey();
          // Object value = entry.getValue();
          // Environment.sysOut("key:[" + key + "]");
          // Environment.sysOut("class:[" +
          // value.getClass().toString() + "], value:[" + value +
          // "]");
          // }
          webDriverVersion = (String) chromeCapabilities.get("chromedriverVersion");
          Environment.sysOut("webDriverVersion:[" + webDriverVersion + "]");
          String[] webDriverVersionArray = webDriverVersion.split(" ");
          webDriverVersion = webDriverVersionArray[0];
          Environment.sysOut("webDriverVersion:[" + webDriverVersion + "]");
        }
      }
    } catch (Throwable throwable) {
      Environment.sysOut("throwable:[" + throwable.toString() + "]");
    }
  }

  public void getWebDriverProcesses(String webDriver) throws Throwable {
    File file = new File(webDriver);
    webDriver = file.getName();
    // webDdriver = webDdriver.replaceAll(Constants.DELIMETER_PATH, "/");
    // Environment.sysOut("webDriver:[" + webDdriver + "]");
    // String[] webDriverArray = webDriver.split("/");
    // webDriver = webDriverArray[(driverArray.length - 1)];
    Environment.sysOut("webDriver:[" + webDriver + "]");
    String command = CommandLine.TASKLIST + " /fo:csv /nh /fi \"imagename eq " + webDriver + "\"";
    Environment.sysOut("command:[" + command + "]");
    Processes processes = new Processes(command);
    // Environment.sysOut(processes.toString());
    Environment.sysOut("DataTable");
    Environment.sysOut(processes.toDataTable().toString());
  }

  public String getWebDriverVersion(String webDriver) throws Exception {
    String webDriverVersion = "";
    if (!getBrowser().equalsIgnoreCase(Browser.EDGE)) {
      String webDriverCommand = "\"" + webDriver + "\" --version";
      Environment.sysOut("webDriverCommand:[" + webDriverCommand + "]");
      Map<String, String> webDriverMap = CommandLine.runProcess(webDriverCommand, true);
      Environment.sysOut("webDriverMap:[" + webDriverMap.toString() + "]");
      String[] webDriverArray = webDriverMap.get("lines").split(" ");
      webDriverVersion = webDriverArray[1];
    }
    return webDriverVersion;
  }

  public void initializeWebDriver() throws Throwable {
    final int MAX_INSTANCIATION_ATTEMPTS = 100;
    final DesiredCapabilities desiredCapabilities =
        setDesiredCapabilities(getOperatingSystem(), getBrowser());
    final EDriverProperties eDriverProperties =
        EDriverProperties.fromString(getBrowser().toUpperCase(Locale.ENGLISH));
    try {
      Environment.sysOut(OS_NAME + ":[" + System.getProperty(OS_NAME) + "]");
      setLocalExecutables();
      Environment.sysOut("desiredCapabilities:[" + desiredCapabilities.toString() + "]");
      if (isRemote()) {
        // setProxy(getBrowser(), isRemote(), desiredCapabilities);
        if (getVendorURL() == null) {
          int attempt = 0;
          boolean driverInstanciated = false;
          do {
            attempt++;
            try {
              setWebDriver(new RemoteWebDriver(new URL(getGridHub()), desiredCapabilities));
              if (getWebDriver() != null) {
                driverInstanciated = true;
                Environment.sysOut("Remote Web Driver instanciated on attemp [" + attempt + "]");
              }
            } catch (final Exception e) {
              if (attempt % 10 == 0) {
                // e.printStackTrace()
                Environment.sysOut(
                    "Remote Web Driver could not be instanciated on attemp [" + attempt + "]");
              } else {
                System.out.print(".");
              }
            }
          } while (!driverInstanciated && attempt < MAX_INSTANCIATION_ATTEMPTS);
          if (!driverInstanciated) {
            throw new QAException("Remote Web Driver could not be instanciated");
          }
          Environment.sysOut("Grid Hub:[" + getGridHub() + "]");
        } else {
          setWebDriver(new RemoteWebDriver(new URL(getVendorURL()), desiredCapabilities));
        }
      } else {

        switch (getBrowser().toLowerCase(Locale.ENGLISH)) {
          case Browser.EDGE:
            // WebDriverManager automatically downloads and sets up Edge driver
            WebDriverManager.edgedriver().setup();
            EdgeOptions edgeOpts = new EdgeOptions();
            edgeOpts.merge(desiredCapabilities);
            setWebDriver(new EdgeDriver(edgeOpts));
            break;
          case Browser.FIREFOX:
            // WebDriverManager automatically downloads and sets up Gecko driver
            WebDriverManager.firefoxdriver().setup();
            final FirefoxOptions firefoxOptions = new FirefoxOptions();
            Environment.sysOut(
                "System.getProperty("
                    + Constants.QUOTE_DOUBLE
                    + eDriverProperties.getWebDriverType()
                    + Constants.QUOTE_DOUBLE
                    + "):]"
                    + System.getProperty(eDriverProperties.getWebDriverType())
                    + "]");
            firefoxOptions.setBinary(eDriverProperties.getPathBinary());
            // setWebDriver(new MarionetteDriver());
            setWebDriver(new FirefoxDriver(firefoxOptions));
            //
            GeckoDriverService service =
                new GeckoDriverService.Builder()
                    .usingDriverExecutable(new File("path to geckodriver"))
                    .usingAnyFreePort()
                    .usingAnyFreePort()
                    .build();
            service.start();
            // GeckoDriver needs the Proxy set in
            // RequiredCapabilities
            FirefoxOptions ffOpts = new FirefoxOptions();
            ffOpts.merge(desiredCapabilities);
            setWebDriver(new FirefoxDriver(service, ffOpts));
            break;
          case Browser.HTML_UNIT:
            // setWebDriver(new
            // HtmlUnitDriver(desiredCapabilities));
            setWebDriver(new HtmlUnitDriver(desiredCapabilities));
            ((HtmlUnitDriver) webDriver).setJavascriptEnabled(true);
            // final WebClient webClient = new WebClient();
            // try
            // {
            // final HtmlPage htmlPage =
            // webClient.getPage("http://stackoverflow" +
            // IExtension.COM + "/");
            // Environment.sysOut(htmlPage.asNormalizedText());
            // } catch (final Exception e)
            // {
            // Environment.sysOut(e);
            // }
            break;
          case Browser.IE: // (very slow)
          case Browser.INTERNET_EXPLORER:
            // WebDriverManager automatically downloads and sets up IE driver
            WebDriverManager.iedriver().setup();
            InternetExplorerOptions ieOpts = new InternetExplorerOptions();
            ieOpts.merge(desiredCapabilities);
            setWebDriver(new InternetExplorerDriver(ieOpts));
            break;
          case Browser.SAFARI:
            // Safari driver is bundled with Safari - no WebDriverManager needed
            setWebDriver(new SafariDriver());
            break;
          default:
          case Browser.CHROME:
            // WebDriverManager automatically downloads and sets up Chrome driver
            WebDriverManager.chromedriver().setup();
            // final ChromeOptions chromeOptions =
            // setChromeOptions(eDriverProperty.getWebDriverType(),
            // eDriverProperty.getPathBinary());
            final ChromeOptions chromeOptions = setChromeOptions(null, null);
            setWebDriver(new ChromeDriver(chromeOptions));
            // setWebDriver(new ChromeDriver(desiredCapabilities));
            break;
        }
      }
      getSessionInformation();
      // Move browser to last monitor.
      int lastMonitor = (JavaHelpers.getMonitorCount() - 1);
      if (lastMonitor != 0) {
        int position = (lastMonitor * 2000);
        Point point = new Point(position, 0);
        getWebDriver().manage().window().setPosition(point);
        // Resize browser.
        int pointX = (JavaHelpers.getMonitorWidth(lastMonitor));
        int pointY = (JavaHelpers.getMonitorHeight(lastMonitor));
        Dimension dimension = new Dimension(pointX, pointY);
        getWebDriver().manage().window().setSize(dimension);
      }
      getWebDriver().manage().window().maximize();
      setPage(new Page(getWebDriver()));
      getWebDriverInfo();
    } catch (final Exception e) {
      throw new QAException(JavaHelpers.getCurrentMethodName(), e);
    }
  }

  public boolean isRemote() {
    return remote;
  }

  public void killBrowser() {
    try {
      if (getWebDriver() != null) {
        getWebDriver().close();
      }
    } catch (final Exception e) {
      // Intentionally empty - browser may already be closed
      if (Environment.isLogAll()) {
        Environment.sysOut("Browser close failed (expected if already closed)");
      }
    }
    try {
      if (getWebDriver() != null) {
        getWebDriver().quit();
      }
    } catch (final Exception e) {
      // Intentionally empty - browser may already be terminated
      if (Environment.isLogAll()) {
        Environment.sysOut("Browser quit failed (expected if already terminated)");
      }
    }
    setWebDriver(null);
  }

  /**
   * @param browser
   */
  public void setBrowser(String browser) {
    this.browser = browser;
  }

  /**
   * @param capabilities
   */
  public void setCapabilities(Capabilities capabilities) {
    this.capabilities = capabilities;
  }

  private ChromeOptions setChromeOptions(String pathFileExecutable, String pathFileBinary) {
    final ChromeOptions chromeOptions = new ChromeOptions();
    final ChromeOptions desiredCapabilities = new ChromeOptions();
    final Map<String, Object> mapPeferences = new HashMap<>();
    final List<String> arguments =
        Arrays.asList(
            "disable-popup-blocking",
            "test-type",
            "start-maximized",
            "--disable-popup-blocking",
            "--start-maximized");
    // chromeOptions.addArguments("chrome.switches", "--disable-extensions
    // --disable-extensions-file-access-check
    // --disable-extensions-http-throttling --disable-infobars
    // --enable-automation --start-maximized");
    chromeOptions.addArguments(arguments);
    // chromeOptions.AddUserProfilePreference("credentials_enable_service",
    // false);
    // chromeOptions.AddUserProfilePreference("profile.password_manager_enabled",
    // false);
    mapPeferences.put("credentials_enable_service", false);
    mapPeferences.put("profile.password_manager_enabled", false);
    chromeOptions.setExperimentalOption("prefs", mapPeferences);
    if (pathFileExecutable != null) {
      System.setProperty("webdriver.chrome.driver", pathFileExecutable);
    }
    if (pathFileBinary != null) {
      chromeOptions.setBinary(pathFileBinary);
    }
    desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
    return chromeOptions;
  }

  /**
   * @param operatingSystem
   * @param browser
   * @return
   */
  private DesiredCapabilities setDesiredCapabilities(String operatingSystem, String browser) {
    final EDriverProperties eDriverProperties = EDriverProperties.fromString(browser.toUpperCase(Locale.ENGLISH));
    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    switch (browser.toLowerCase(Locale.ENGLISH)) {
      case "android":
        desiredCapabilities = new DesiredCapabilities();
        break;
      case Browser.CHROME:
        final ChromeOptions chromeOptions = setChromeOptions(null, null);
        desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.merge(chromeOptions);
        break;
      case Browser.EDGE:
        EdgeOptions edgeOpts2 = new EdgeOptions();
        desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.merge(edgeOpts2);
        break;
      case Browser.FIREFOX:
        final FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(eDriverProperties.getPathBinary());
        desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.merge(firefoxOptions);
        break;
      case Browser.HTML_UNIT:
        desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName(Browser.HTML_UNIT);
        break;
      case Browser.HTML_UNIT_WITH_JS:
        desiredCapabilities = new DesiredCapabilities();
        break;
      case Browser.IE: // (very slow)
      case Browser.INTERNET_EXPLORER:
        InternetExplorerOptions ieOpts2 = new InternetExplorerOptions();
        ieOpts2.introduceFlakinessByIgnoringSecurityDomains();
        ieOpts2.ignoreZoomSettings();
        ieOpts2.requireWindowFocus();
        desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.merge(ieOpts2);
        desiredCapabilities.setCapability(
            eDriverProperties.getWebDriverType(), eDriverProperties.getPathDriver());
        break;
      case "ipad":
        desiredCapabilities = new DesiredCapabilities();
        break;
      case "iphone":
        desiredCapabilities = new DesiredCapabilities();
        break;
      case "phantomjs":
        desiredCapabilities = new DesiredCapabilities();
        break;
      case Browser.SAFARI:
        SafariOptions safariOpts = new SafariOptions();
        desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.merge(safariOpts);
        break;
      default:
        Environment.sysOut("Unknown browser: " + browser + ". Using default capabilities.");
        desiredCapabilities = new DesiredCapabilities();
        break;
    }
    switch (operatingSystem.toLowerCase(Locale.ENGLISH)) {
      case OS.ANDROID:
        desiredCapabilities.setPlatform(Platform.ANDROID);
        break;
      case OS.ANY:
        desiredCapabilities.setPlatform(Platform.ANY);
        break;
      case OS.EL_CAPITAN:
        desiredCapabilities.setPlatform(Platform.EL_CAPITAN);
        break;
      case OS.LINUX:
        desiredCapabilities.setPlatform(Platform.LINUX);
        break;
      case OS.MAC:
        desiredCapabilities.setPlatform(Platform.MAC);
        break;
      case OS.MAVERICKS:
        desiredCapabilities.setPlatform(Platform.MAVERICKS);
        break;
      case OS.MOINTAIN_LION:
        desiredCapabilities.setPlatform(Platform.MOUNTAIN_LION);
        break;
      case OS.SNOW_LEOPARD:
        desiredCapabilities.setPlatform(Platform.SNOW_LEOPARD);
        break;
      case OS.UNIX:
        desiredCapabilities.setPlatform(Platform.UNIX);
        break;
      case OS.VISTA:
        desiredCapabilities.setPlatform(Platform.VISTA);
        break;
      case OS.WIN8:
        desiredCapabilities.setPlatform(Platform.WIN8);
        break;
      case OS.WIN8_1:
        desiredCapabilities.setPlatform(Platform.WIN8_1);
        break;
      case OS.WINDOWS:
        desiredCapabilities.setPlatform(Platform.WINDOWS);
        break;
      case OS.WINDOWS_10:
        desiredCapabilities.setPlatform(Platform.WIN10);
        break;
      case OS.XP:
        desiredCapabilities.setPlatform(Platform.XP);
        break;
      case OS.YOSEMITE:
        desiredCapabilities.setPlatform(Platform.YOSEMITE);
        break;
      default:
        Environment.sysOut("Unknown operating system: " + operatingSystem + ". Using ANY platform.");
        desiredCapabilities.setPlatform(Platform.ANY);
        break;
    }
    return desiredCapabilities;
  }

  /**
   * @param driver
   */
  public void setDriver(WebDriver driver) {
    setWebDriver(driver);
  }

  /**
   * @param gridHub
   */
  public void setGridHub(String gridHub) {
    this.gridHub = gridHub;
  }

  /**
   * @param gridHubName
   */
  public void setGridHubName(String gridHubName) {
    this.gridHubName = gridHubName;
  }

  /**
   * @param gridHubPort
   */
  public void setGridHubPort(int gridHubPort) {
    this.gridHubPort = gridHubPort;
  }

  private void setLocalExecutables() {
    final EDriverProperties eDriverProperties =
        EDriverProperties.fromString(getBrowser().toUpperCase(Locale.ENGLISH));
    switch (getBrowser().toLowerCase(Locale.ENGLISH)) {
      case Browser.SAFARI:
        // If you wish for safari to forget session everytime
        // SafariOptions.setUseCleanSession(true); // Removed in Selenium 4
        setWebDriver(new SafariDriver());
        break;
      case Browser.CHROME:
      case Browser.EDGE:
      case Browser.FIREFOX:
      case Browser.IE: // (very slow)
      case Browser.INTERNET_EXPLORER: // (very slow)
      default:
        System.setProperty(eDriverProperties.getWebDriverType(), eDriverProperties.getPathDriver());
        break;
    }
    Environment.sysOut(
        JavaHelpers.getCurrentMethodName()
            + "-webDriverType:["
            + eDriverProperties.getWebDriverType()
            + "], PathDriver:["
            + eDriverProperties.getPathDriver()
            + "], , PathBinary:["
            + eDriverProperties.getPathBinary()
            + "]");
  }

  /**
   * @param operatingSystem
   */
  public void setOperatingSystem(String operatingSystem) {
    this.operatingSystem = operatingSystem;
  }

  /**
   * @param page
   */
  public void setPage(Page page) {
    this.page = page;
  }

  public void setProxy(String browser, boolean isRemote, DesiredCapabilities desiredCapabilities)
      throws Exception {
    if (!isRemote) {
      setGridHubName("localhost");
      setGridHubPort(8080);
      setGridHub("http://" + getGridHubName() + ":" + getGridHubPort() + "/wd/hub");
    }
    String sProxy = getGridHubName() + ":" + getGridHubPort();
    switch (browser) {
      case Browser.FIREFOX:
        JsonObject json = new JsonObject();
        json.addProperty("proxyType", "MANUAL");
        json.addProperty("httpProxy", getGridHub());
        json.addProperty("httpProxyPort", getGridHubPort());
        json.addProperty("sslProxy", getGridHub());
        json.addProperty("sslProxyPort", getGridHubPort());
        desiredCapabilities.setCapability("proxy", json);
        break;
      case Browser.CHROME:
      default:
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(sProxy).setFtpProxy(sProxy).setSslProxy(sProxy);
        desiredCapabilities.setCapability(CapabilityType.PROXY, proxy);
        break;
    }
  }

  /**
   * @param remote
   */
  public void setRemote(boolean remote) {
    this.remote = remote;
  }

  /**
   * @param scenario
   */
  public void setScenario(Scenario scenario) {
    this.scenario = scenario;
  }

  /**
   * @param screenshots
   */
  public void setScreenshots(int screenshots) {
    this.screenshots = screenshots;
  }

  /**
   * @param sessionId
   */
  public void setSessionId(SessionId sessionId) {
    this.sessionId = sessionId;
  }

  /**
   * @param vendorURL
   */
  public void setVendorURL(String vendorURL) {
    this.vendorURL = vendorURL;
  }

  /**
   * @param version
   */
  public void setVersion(String version) {
    this.version = version;
  }

  /**
   * @param webDriver
   */
  public void setWebDriver(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  @Override
  public String toString() {
    final StringBuilder stringBuilder = new StringBuilder();
    final Constants c = new Constants();
    c.setFormatPretty(true);
    int newLine = 0;
    if (c.isFormatPretty()) {
      newLine = 1;
    }
    int tab = 0;
    // stringBuilder.append(c.nlTab(newLine, tab) + "{");
    // tab = c.tabIncriment(tab, (newLine * 1));
    // stringBuilder.append(c.nlTab(newLine, tab) + Constants.QUOTE_DOUBLE +
    // "Selenium WebDriver
    // Information" + Constants.QUOTE_DOUBLE + ":");
    stringBuilder.append(c.nlTab(newLine, tab) + "{");
    tab = c.tabIncriment(tab, (newLine * 1));
    stringBuilder.append(
        c.nlTab(newLine, tab)
            + Constants.QUOTE_DOUBLE
            + "Grid Hub Name"
            + Constants.QUOTE_DOUBLE
            + ": "
            + Constants.QUOTE_DOUBLE
            + getGridHubName()
            + Constants.QUOTE_DOUBLE
            + ",");
    stringBuilder.append(
        c.nlTab(newLine, tab)
            + Constants.QUOTE_DOUBLE
            + "Grid Hub Port"
            + Constants.QUOTE_DOUBLE
            + ": "
            + Constants.QUOTE_DOUBLE
            + getGridHubPort()
            + Constants.QUOTE_DOUBLE
            + ",");
    stringBuilder.append(
        c.nlTab(newLine, tab)
            + Constants.QUOTE_DOUBLE
            + "Grid Hub"
            + Constants.QUOTE_DOUBLE
            + ": "
            + Constants.QUOTE_DOUBLE
            + getGridHub()
            + Constants.QUOTE_DOUBLE
            + ",");
    stringBuilder.append(
        c.nlTab(newLine, tab)
            + Constants.QUOTE_DOUBLE
            + "Operating System"
            + Constants.QUOTE_DOUBLE
            + ": "
            + Constants.QUOTE_DOUBLE
            + getOperatingSystem()
            + Constants.QUOTE_DOUBLE
            + ",");
    stringBuilder.append(
        c.nlTab(newLine, tab)
            + Constants.QUOTE_DOUBLE
            + "Remote"
            + Constants.QUOTE_DOUBLE
            + ": "
            + isRemote()
            + ",");
    final Scenario scenario = getScenario();
    if (scenario == null) {
      stringBuilder.append(
          c.nlTab(newLine, tab)
              + Constants.QUOTE_DOUBLE
              + "Scenario"
              + Constants.QUOTE_DOUBLE
              + ": "
              + Constants.QUOTE_DOUBLE
              + scenario
              + Constants.QUOTE_DOUBLE
              + ",");
    } else {
      stringBuilder.append(
          c.nlTab(newLine, tab)
              + Constants.QUOTE_DOUBLE
              + "Scenario"
              + Constants.QUOTE_DOUBLE
              + ": "
              + Constants.QUOTE_DOUBLE
              + getScenario().toString()
              + Constants.QUOTE_DOUBLE
              + ",");
    }
    stringBuilder.append(
        c.nlTab(newLine, tab)
            + Constants.QUOTE_DOUBLE
            + "Screenshot Count"
            + Constants.QUOTE_DOUBLE
            + ": "
            + getScreenshots()
            + ",");
    stringBuilder.append(
        c.nlTab(newLine, tab)
            + Constants.QUOTE_DOUBLE
            + "Session ID"
            + Constants.QUOTE_DOUBLE
            + ": "
            + Constants.QUOTE_DOUBLE
            + getSessionId()
            + Constants.QUOTE_DOUBLE
            + ",");
    final WebDriver webDriver = getWebDriver();
    if (webDriver == null) {
      stringBuilder.append(
          c.nlTab(newLine, tab)
              + Constants.QUOTE_DOUBLE
              + "Web Driver"
              + Constants.QUOTE_DOUBLE
              + ": "
              + Constants.QUOTE_DOUBLE
              + webDriver
              + Constants.QUOTE_DOUBLE
              + ",");
    } else {
      stringBuilder.append(
          c.nlTab(newLine, tab)
              + Constants.QUOTE_DOUBLE
              + "Web Driver"
              + Constants.QUOTE_DOUBLE
              + ": "
              + Constants.QUOTE_DOUBLE
              + getWebDriver().toString()
              + Constants.QUOTE_DOUBLE
              + ",");
    }
    stringBuilder.append(
        c.nlTab(newLine, tab)
            + Constants.QUOTE_DOUBLE
            + "Vendor URL"
            + Constants.QUOTE_DOUBLE
            + ": "
            + Constants.QUOTE_DOUBLE
            + getVendorURL()
            + Constants.QUOTE_DOUBLE
            + ",");
    stringBuilder.append(
        c.nlTab(newLine, tab)
            + Constants.QUOTE_DOUBLE
            + "Version"
            + Constants.QUOTE_DOUBLE
            + ": "
            + Constants.QUOTE_DOUBLE
            + getVersion()
            + Constants.QUOTE_DOUBLE
            + ",");
    stringBuilder.append(
        c.nlTab(newLine, tab)
            + Constants.QUOTE_DOUBLE
            + "Capabilities"
            + Constants.QUOTE_DOUBLE
            + ":");
    stringBuilder.append(c.nlTab(newLine, tab) + "{");
    tab = c.tabIncriment(tab, (newLine * 1));
    stringBuilder.append(
        c.nlTab(newLine, tab)
            + Constants.QUOTE_DOUBLE
            + "Browser"
            + Constants.QUOTE_DOUBLE
            + ": "
            + Constants.QUOTE_DOUBLE
            + getCapabilities().getBrowserName()
            + Constants.QUOTE_DOUBLE
            + ",");
    stringBuilder.append(
        c.nlTab(newLine, tab)
            + Constants.QUOTE_DOUBLE
            + "Platform"
            + Constants.QUOTE_DOUBLE
            + ": "
            + Constants.QUOTE_DOUBLE
            + getCapabilities().getPlatformName()
            + Constants.QUOTE_DOUBLE
            + ",");
    stringBuilder.append(
        c.nlTab(newLine, tab)
            + Constants.QUOTE_DOUBLE
            + "Version"
            + Constants.QUOTE_DOUBLE
            + ": "
            + Constants.QUOTE_DOUBLE
            + getCapabilities().getBrowserVersion()
            + Constants.QUOTE_DOUBLE
            + "");
    tab = c.tabIncriment(tab, (newLine * -1));
    stringBuilder.append(c.nlTab(newLine, tab) + "}");
    tab = c.tabIncriment(tab, (newLine * -1));
    stringBuilder.append(c.nlTab(newLine, tab) + "}");
    // tab = c.tabIncriment(tab, (newLine * -1));
    // stringBuilder.append(c.nlTab(newLine, tab) + "}");
    return stringBuilder.toString();
  }
}
