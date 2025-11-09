package com.cjs.qa.united;

import com.cjs.qa.united.pages.BookTravelPage;
import com.cjs.qa.united.pages.HomePage;
import com.cjs.qa.united.pages.LoginPage;
import com.cjs.qa.united.pages.MemberAccountUpdatePage;
import com.cjs.qa.united.pages.SecurityPage;
import org.openqa.selenium.WebDriver;

public class United {
  public BookTravelPage BookTravelPage;
  public HomePage HomePage;
  public LoginPage LoginPage;
  public MemberAccountUpdatePage MemberAccountUpdatePage;
  public SecurityPage SecurityPage;

  public United(WebDriver webDriver) {
    BookTravelPage = new BookTravelPage(webDriver);
    HomePage = new HomePage(webDriver);
    LoginPage = new LoginPage(webDriver);
    MemberAccountUpdatePage = new MemberAccountUpdatePage(webDriver);
    SecurityPage = new SecurityPage(webDriver);
    webDriver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(120));
  }
}
