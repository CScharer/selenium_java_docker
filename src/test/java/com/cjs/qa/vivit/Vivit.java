package com.cjs.qa.vivit;

import com.cjs.qa.vivit.pages.BlogsPage;
import com.cjs.qa.vivit.pages.BoDPage;
import com.cjs.qa.vivit.pages.CalendarsPage;
import com.cjs.qa.vivit.pages.ForumsPage;
import com.cjs.qa.vivit.pages.GroupPage;
import com.cjs.qa.vivit.pages.HomePage;
import com.cjs.qa.vivit.pages.MyMemberProfilePage;
import com.cjs.qa.vivit.pages.SearchPage;
import com.cjs.qa.vivit.pages.StaffPage;
import org.openqa.selenium.WebDriver;

public class Vivit {
  public BlogsPage BlogsPage;
  public BoDPage BoDPage;
  public CalendarsPage CalendarsPage;
  public ForumsPage ForumsPage;
  public GroupPage GroupPage;
  public HomePage HomePage;
  public MyMemberProfilePage MyMemberProfilePage;
  public SearchPage SearchPage;
  public StaffPage StaffPage;

  public Vivit(WebDriver webDriver) {
    BlogsPage = new BlogsPage(webDriver);
    BoDPage = new BoDPage(webDriver);
    CalendarsPage = new CalendarsPage(webDriver);
    ForumsPage = new com.cjs.qa.vivit.pages.ForumsPage(webDriver);
    GroupPage = new GroupPage(webDriver);
    HomePage = new HomePage(webDriver);
    MyMemberProfilePage = new MyMemberProfilePage(webDriver);
    SearchPage = new SearchPage(webDriver);
    StaffPage = new StaffPage(webDriver);
    webDriver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(120));
  }
}
