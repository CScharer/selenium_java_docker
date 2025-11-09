package com.cjs.qa.everyonesocial;

import com.cjs.qa.everyonesocial.pages.CompanyStreamPage;
import com.cjs.qa.everyonesocial.pages.CulturePage;
import com.cjs.qa.everyonesocial.pages.CustomerStoriesCaseStudiesPage;
import com.cjs.qa.everyonesocial.pages.EventsPage;
import com.cjs.qa.everyonesocial.pages.MicroFocusPage;
import com.cjs.qa.everyonesocial.pages.NavigationPage;
import com.cjs.qa.everyonesocial.pages.ProductLaunchesPage;
import com.cjs.qa.everyonesocial.pages.SignInPage;
import com.cjs.qa.everyonesocial.pages.TechBeaconPage;
import com.cjs.qa.utilities.JavaHelpers;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EveryoneSocial {
  public CompanyStreamPage CompanyStreamPage;
  public CulturePage CulturePage;
  public CustomerStoriesCaseStudiesPage CustomerStoriesCaseStudiesPage;
  public EventsPage EventsPage;
  public MicroFocusPage MicroFocusPage;
  public NavigationPage NavigationPage;
  public ProductLaunchesPage ProductLaunchesPage;
  public SignInPage SignInPage;
  public TechBeaconPage TechBeaconPage;
  private WebDriver webDriver = null;

  public EveryoneSocial() {}

  public EveryoneSocial(WebDriver webDriver) {
    setWebDriver(webDriver);
    CompanyStreamPage = new CompanyStreamPage(webDriver);
    CulturePage = new CulturePage(webDriver);
    CustomerStoriesCaseStudiesPage = new CustomerStoriesCaseStudiesPage(webDriver);
    EventsPage = new EventsPage(webDriver);
    MicroFocusPage = new MicroFocusPage(webDriver);
    NavigationPage = new NavigationPage(webDriver);
    ProductLaunchesPage = new ProductLaunchesPage(webDriver);
    SignInPage = new SignInPage(webDriver);
    TechBeaconPage = new TechBeaconPage(webDriver);
    webDriver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(120));
  }

  private WebDriver getWebDriver() {
    return webDriver;
  }

  private void setWebDriver(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public List<WebElement> webElementListShare() {
    final String BUTTONS_SHARE = ".//button/span[contains(text(),'Share')]";
    List<WebElement> webElementListShared = webElementListShared();
    List<WebElement> webElementListShare =
        (List<WebElement>) getWebDriver().findElement(By.xpath(BUTTONS_SHARE));
    webElementListShare.removeAll(webElementListShared);
    return webElementListShare;
  }

  public List<WebElement> webElementListShared() {
    final String BUTTONS_SHARED = ".//button/span[contains(text(),'Shared')]";
    return (List<WebElement>) getWebDriver().findElement(By.xpath(BUTTONS_SHARED));
  }

  public void clickButtonCancel() {
    By byCancel = By.xpath(".//*[@id='sharePanel']//button[.='Cancel']");
    CompanyStreamPage.getWebDriver().findElement(byCancel).click();
  }

  public void clickButtonFinish() {
    By byFinish = By.xpath(".//*[@id='sharePanel']//button[.='Finish']");
    CompanyStreamPage.getWebDriver().findElement(byFinish).click();
  }

  public boolean sharePosts() {
    List<WebElement> webElementListShared = webElementListShare();
    for (WebElement webElement : webElementListShared) {
      CompanyStreamPage.scrollToElement(webElement);
      CompanyStreamPage.flashCurrentElement(webElement, 20);
    }
    List<WebElement> webElementListShare = webElementListShare();
    for (WebElement webElement : webElementListShare) {
      // Only do this for 3 items.
      // webElement.click();
      // JavaHelpers.sleep(3);
      // clickButtonFinish();
      // JavaHelpers.sleep(3);
      CompanyStreamPage.scrollToElement(webElement);
      CompanyStreamPage.flashCurrentElement(webElement, 20);
    }

    JavaHelpers.sleep(3);
    return true;
  }
}
