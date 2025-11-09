package com.cjs.qa.dropbox.pages;

import com.cjs.qa.core.Environment;
import com.cjs.qa.core.QAException;
import com.cjs.qa.core.security.EPasswords;
import com.cjs.qa.dropbox.DropboxEnvironment;
import com.cjs.qa.selenium.Page;
import com.cjs.qa.utilities.CJSConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage extends Page {
  public SignInPage(WebDriver webDriver) {
    super(webDriver);
  }

  By checkboxRememberMeInput = By.xpath(".//*[@name='remember_me']");
  final By checkboxRememberMe = By.xpath(".//*[@name='remember_me']/..");
  final By editEmail = By.xpath(".//*[@name='login_email']");
  final By editPassword = By.xpath(".//*[@name='login_password']");
  final By buttonNext = By.xpath(".//input[@value='Next']");
  final By buttonSignIn = By.xpath(".//button[@id='sign-up-in']");
  final By buttonSignInRegular =
      By.xpath(".//button[contains(@class,'signin-button')][@type='submit']");
  final By buttonSignInWithGoogle =
      By.xpath(".//button[contains(@class,'auth-google')][@type='button']");

  public void buttonNextClick() throws QAException {
    if (objectExists(buttonNext)) {
      clickObject(buttonNext);
    }
  }

  public void buttonSignInClick() throws QAException {
    if (objectExists(buttonSignIn)) {
      clickObject(buttonSignIn);
    }
  }

  public void buttonSignInRegularClick() throws QAException {
    if (objectExists(buttonSignInRegular)) {
      clickObject(buttonSignInRegular);
    }
  }

  public void buttonSignInWithGoogleClick() throws QAException {
    if (objectExists(buttonSignInWithGoogle)) {
      clickObject(buttonSignInWithGoogle);
    }
  }

  public void CheckboxRememberMeSet(String value) throws QAException {
    if (objectExists(checkboxRememberMe)) {
      setCheckbox(checkboxRememberMeInput, value);
    }
  }

  @Override
  protected void setCheckbox(By by, String value) {
    if (Environment.isLogAll()) {
      Environment.sysOut("({Field}" + checkboxRememberMe.toString() + ", {Value}" + value + ");");
    }
    switch (value.toLowerCase()) {
      case "checked":
        if (getCheckbox(checkboxRememberMeInput) != value) {
          getWebDriver().findElement(checkboxRememberMe).click();
        }
        break;
      case "unchecked":
        if (getCheckbox(checkboxRememberMeInput) != value) {
          getWebDriver().findElement(checkboxRememberMe).click();
        }
        break;
      default:
        Environment.sysOut("The value [" + value + "] is not supported for a Checkbox.");
        break;
    }
    if (getCheckbox(checkboxRememberMeInput) != value) {
      Environment.sysOut(
          "The value ["
              + value
              + "] was not selected for Checkbox ["
              + checkboxRememberMe.toString()
              + "].");
    }
  }

  @Override
  protected String getCheckbox(By by) {
    String value;
    value = getWebDriver().findElement(by).getAttribute("aria-checked");
    if (value.equals("false")) {
      value = "unchecked";
    } else {
      value = "checked";
    }
    if (Environment.isLogAll()) {
      Environment.sysOut("({Field}" + by.toString() + ", {Value}" + value + ");");
    }
    return value;
  }

  public void editEmailSet(String value) throws QAException {
    if (objectExists(editEmail)) {
      setEdit(editEmail, value);
    }
  }

  public void editPasswordSet(String value) throws QAException {
    if (objectExists(editPassword)) {
      setEdit(editPassword, value);
    }
  }

  public void load() throws QAException {
    maximizeWindow();
    Environment.sysOut("Loading:[" + DropboxEnvironment.URL_LOGIN + "]");
    webDriver.get(DropboxEnvironment.URL_LOGIN);
    // int waits = 0;
    // do
    // {
    // waits++;
    // } while (objectExists(labelLoading));
    // Environment.sysOut("Waited For Load:[" + waits + "] times.");
  }

  public void signIn(String value) throws QAException {
    editEmailSet(value);
    buttonNextClick();
  }

  public void signInToVerifyActiveAccount(String eMail) throws QAException {
    load();
    buttonSignInClick();
    editEmailSet(eMail);
    editPasswordSet(EPasswords.DROPBOX.getValue());
    if (eMail.equalsIgnoreCase(CJSConstants.EMAIL_ADDRESS_MSN)) {
      CheckboxRememberMeSet("checked");
    } else {
      CheckboxRememberMeSet("unchecked");
    }
    // buttonSignInRegularClick();
  }

  public void solveCaptcha() throws QAException {
    // .//*[@id='rc-imageselect-target']/table/tbody/tr/td
    // .//*[@id='recaptcha-verify-button']
    // .//*[@id='recaptcha-verify-button']
    // .//*[@id='rc-imageselect']//div[@class='rc-imageselect-desc-no-canonical']/strong
    // .//*[@id='rc-imageselect-target']/table/tbody/tr/td//img
    // getAttribute("src");
    // https://www.google" + IExtension.COM +
    // "/recaptcha/api2/payload?c=03ACgFB9uW1rxx2Q6BKRXCllRqsmDEDXyWHmImGjmRfxGQci69our4XnsQFXxCGOHDcKWgrKjuAKW_WFOVs9rAF-_a-yTffQZ9_ccuyJPSO83j3zeSsPDDHDcdudDtGsylkmKVmsDwaly-_bVmOrfAV6h9cua6mhPl2_vWcKpi5f_Lt-xiXnPYclwup9GZcDg4fjqyUeL-cEpt-8duPm84o7ecYTKKomAZhkX-qcWn7wDXNxhhVWTnGj7b8CMBRHmZvtgsrGLGbVpiLTb0-U0iX8JeLMskNclpms-ZJqSToHOVmuRWpuXfnXf4yZ7f5xYY2RKqG5f5Zato0ZdfZUEYv4puXOI8wPLRRk-ku1YiXjRVCu4hbDuzwB7hlVVxuusaN3iGDyVLtR9JV9nvVmQ-5q03Ob-Q5kkm_LoqlZ0djO8QxRUv7d7r4ydTjx92ZPJzwKOt-RGdf18s2HcIdwOpPQst4n8U0sGP3UaXoeEzMu8KNju0cJnhEY2uJNLcHCQuQP2ZI4R7A5Z4l-Fv5BLBqa0UcnYjuAzu3NTk7vTkt1rFx7RipMpXyQO2rQ63eMdTJ7Ya0NpVLB_sgAZovzkc0NVoh3izZsA0nE21KtqLpDBNKwvK0XETNKxenf88IuIq-dKXS7NrphQHZ63r2n0QKzFgYdmxPNwKU9rdo4ctIG-_Q3Tp_fvImTN4EGE0NyulUDZCDTG-8cVFlp_bZve78sq0GKvK6AUOSbBWUyjobueCSRnVf65fbPLQ4H-6n2HBKGimiYkm8OXCctYa9rYCGGoa9Xib39UvNCILuLWC5OJnY5KZtbp4j5r9nxePyjTEINwZusKMD8ErEvt6WGWugdoC6w5BR8IjYGxGgYQMVqeCsekQOCNj1QpYlGZM9DdS0-9_ONkZbqwqFW6uPtZe54zXeHOKazQdwN8UjM5vNlzC6-MCItysxQlPS0BgjOehcdxP9GXO94xd7-k4iKrDDlKXO0k7E-PzQjMc3YPoFWyL0l3SOlrt_6Cly0uaEJkRgbqzjsbORXORuZsU2BI-i_wuPeuRywnNaTcaAxrk9Kq__s-hCAhogXCMrKaH7xhnJrGr19f0orCpnhcU0B9NSidzSonYUOOctEF6G4K-1tktRC7RxxSiPQ2QvDr0BvigmUkBflafdyUlsiDhw2kkgUSFvmPpz0mMMgBNxPWwan-ZQ1YJQCPUQECQYccyrazyDSr-L7oMiNJLjJcgUmuehH1yI28OIFAelCOAKvA-e4cP_UoGASX1JDSOUZqah9jwOfqZJhIrgIARzD32nn5tnm-Du0HH5C11bh5lPnqPT5oGeP-6FJY852M6cmwMJbVs76S6OxHf3TF2OoJvv0StbH9JZx6T6zPUgbrZk9TCwyTs75KIyTKL0_vGVJI3Wayb-WhA9J5NvVmhlFmMDEDvHQLjQrZeMulxYiG6fwhY0eq4AKEoCSSqZY8OFV5HputUCi8g1zp5xALuwLxGk5Bo-5rmlKFMWedsUMBE3erivlPCtQm8-gd0M_CXq0IIqUPguus6MYz3JB5S7vVw4EMTSqI4R0SQePBH2tykJEUoQNqacjBzTA1ZAZ_yx8Uc3hdl9nMpT_vgk0USC0P2NsIZsWU7F59S1HsKYflIXOf18-gpXItiYUWlBhFpAZPBbT9E50eP4P43nQVV5HaZBEd_WzqrE8iob43Csla4vdOI7W67lDwc7KfL32pzZr6UVNlbnKVmZHU7okpYrLyhQbZHDpElgvgYc0U_kYgOQyLymnMouQS9wZXFiv9RhxMKBNjT11IhVV4YmujCZWeyspDfTvXK1zIxhakPEMrIliWxAdOAUN_mjiEbIoC65ZmUMLBCqbfrWbZ35jz87e93F4_LtwNjvPXJIYmIaUnZLjnnFQBKNXwrNzYQJegrb4tUn4B-REehsOZrTuw2tpHgO14vi3GwkwY1v80dgpdeBG-hzKO5UAqYlJWqNYsEW-M94Gh5FqiKc2qim9ReoGsspN2OtA9a8h63OKeOyHxEIkhuUJ5jAdgasvbN5msB4eC4WJGr3aOCt0ZP8MaVFVSKhaabscW3cCbGQsI6VZgrCWO5s-POjQjxbUlA2tyCCZIfFr02RnPrYdm8n1Q_rjagTNKUfDAbKYbXJnpfv1uiHXG-rKMwfqQlJZ15b26eZtH9SSIn-lddqgAncxi9&k=6LdnLyIUAAAAAOiGPtddh-g3KiJRoDGGPD-6dqXo&id=8a5620dcbbd2c583
    // xhtml:html/xhtml:body/xhtml:img
    // https://www.google" + IExtension.COM +
    // "/search?tbs=sbi:AMhZZiu4OZ2ogWFh0jJZFAtfcBHUZ4hrKayvjuMuONxuI9U0L7ntAU4pYqDJl1Jg936KkRyLR4FVxqR0BQMadjKEwvlwHPnqE5IvPqE6_1wr7prZrE195P-EZ9mjxrCjgcDNqXrzI9PWRSzm8zA-k3Wf5ontYIZ39Dk-9LsXl_1XzbzAbPfQxv6WznfYRnTfC9_1Gcyd1NvcPvl7qBBAioWlfy4vqGSiuhk_1cNvhEJsdr1o3sXBhZRfDlfYv1KHUylol037C109AM7cyimU5XnniJiJDDjlNe5hUUNioYfqcFWJDis6bVPBQ857n4Rma1bJ4wtypBD6f9pWrmrhLwpp67jKLF0ORCIWtQ
  }
}
