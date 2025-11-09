package com.cjs.qa.marlboro.pages;

import com.cjs.qa.core.Environment;
import com.cjs.qa.core.QAException;
import com.cjs.qa.marlboro.MarlboroEnvironment;
import com.cjs.qa.selenium.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OffersAndActivityPage extends Page {
  public OffersAndActivityPage(WebDriver webDriver) {
    super(webDriver);
  }

  final String URL_OFFERS = MarlboroEnvironment.URL_BASE + "/marlboro/my-marlboro/offers";
  final By button3DollarsOffACarton = By.xpath(".//span[contains(text(),'$3 Off a Carton')]");
  // final By buttonClaimYours =
  // By.xpath(".//*[@id='container']//span[.='CLAIM YOURS']");
  final By buttonGetYoursByMail =
      By.xpath(
          ".//div[@class='image-cta-link']//div[@class='inner-content']/button"
              + "/i[contains(@class,'btn-ctaIcon')]/../..");
  final By buttonCOUPONS =
      By.xpath(".//li[contains(@class,'menu-list-desk')]/a[@data-dtmtext='COUPONS']");
  final By buttonSubmit = By.xpath(".//*[@id='cpn-submit']");
  final By buttonLogOut = By.xpath(".//*[@id='logout']/span");
  final By labelMessage = By.xpath(".//div[@class='coupon-heading']/h6");
  final By labelThanks =
      By.xpath(".//div[@class='coupon-heading']/h6[.='THANK YOU FOR YOUR REQUEST.']");
  final By labelCouponInTheMail =
      By.xpath(
          ".//div[@class='coupon-heading']/h6[.='LOOKS LIKE YOUR MONTHLY COUPON IS"
              + " ALREADY IN THE MAIL.']");

  public void button3DollarsOffACartonClick() throws QAException {
    scrollToTop();
    scrollElementIntoView(getWebElement(button3DollarsOffACarton));
    scrollUp(250);
    Environment.setScrollToObject(false);
    clickObject(button3DollarsOffACarton);
    Environment.setScrollToObject(true);
  }

  public void buttonGetYoursByMailClick() throws QAException {
    clickObject(buttonGetYoursByMail);
    boolean waitingVideo = true;
    do {
      if (objectExists(button3DollarsOffACarton)) {
        waitingVideo = false;
      }

    } while (waitingVideo);
  }

  public void buttonCOUPONSClick() throws QAException {
    clickObject(buttonCOUPONS);
  }

  public void buttonSubmitClick() throws QAException {
    scrollToTop();
    scrollElementIntoView(getWebElement(button3DollarsOffACarton));
    scrollUp(250);
    Environment.setScrollToObject(false);
    clickObject(buttonSubmit);
    Environment.setScrollToObject(true);
  }

  public void buttonLogOutClick() throws QAException {
    clickObject(buttonLogOut);
  }

  public void collectCoupons() throws QAException {
    // buttonSeeAvailableCouponsClick();
    // buttonCOUPONSClick();
    //
    // buttonClaimYoursClick();
    // buttonGetYoursByMailClick();
    button3DollarsOffACartonClick();
    buttonSubmitClick();
  }

  public String getlabelMessage() throws QAException {
    String message = "";
    WebElement webElement = null;
    if (objectExists(labelMessage, 3)) {
      webElement = getWebElement(labelMessage);
      message = webElement.getText();
    }
    final By labelMessage = By.xpath(".//div[@class='error-page']/p[2]/span");
    if (objectExists(labelMessage, 3)) {
      webElement = getWebElement(labelMessage);
      message = webElement.getText();
    }
    return message;
  }

  public boolean labelThanksValidate() throws QAException {
    return objectExists(labelThanks, 3);
  }

  public boolean labelCouponInTheMailValidate() throws QAException {
    boolean collected = false;
    if (objectExists(labelCouponInTheMail, 3)) {
      final WebElement webElement = getWebElement(labelCouponInTheMail);
      collected = webElement.isDisplayed();
    }
    return collected;
  }
}
