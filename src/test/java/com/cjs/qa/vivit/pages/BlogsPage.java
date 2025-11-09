package com.cjs.qa.vivit.pages;

import com.cjs.qa.core.Environment;
import com.cjs.qa.selenium.Page;
import com.cjs.qa.utilities.JavaHelpers;
import com.cjs.qa.vivit.VivitData;
import com.cjs.qa.vivit.objects.Blogs;
import com.cjs.qa.vivit.objects.Groups;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BlogsPage extends Page {
  public BlogsPage(WebDriver webDriver) {
    super(webDriver);
  }

  public String BLOGS_URL_COMMUNITY = "https://www.vivit-worldwide.org/members/blogs.asp";
  private final By byTableBlogs = By.xpath(".//*[@id='SpContent_Container']/form/table/tbody/tr");

  public void getBlogData() throws Throwable {
    Environment.sysOut(
        "***ClassMethodDebug***:[" + JavaHelpers.getCurrentClassMethodDebugName() + "]");
    String statusName = "getBlogData";
    if (VivitData.successFileExists(statusName)) {
      return;
    }
    for (final Groups groups : Groups.getGroupsList()) {
      if (JavaHelpers.hasValue(groups.getBlogsURL())) {
        Environment.sysOut(
            "Retrieving Data:["
                + JavaHelpers.getCurrentClassMethodName()
                + "], ["
                + groups.getGroupName()
                + "], ["
                + groups.getBlogsURL()
                + "]");
        getBlogs(groups.getBlogsURL(), groups.getGroupName());
      } else {
        Environment.sysOut(
            "Retrieving Null:["
                + JavaHelpers.getCurrentClassMethodName()
                + "], ["
                + groups.getGroupName()
                + "], ["
                + groups.getBlogsURL()
                + "]");
      }
    }
    getBlogs(BLOGS_URL_COMMUNITY, "Community");
    List<StringBuilder> sqlStringBuilderList = new ArrayList<>();
    sqlStringBuilderList.add(new StringBuilder("Blogs"));
    sqlStringBuilderList.add(Blogs.appendRecords());
    VivitData.updateTableFromCurrentToPreviousAndInsert(sqlStringBuilderList);
    VivitData.successFileCreate(statusName);
  }

  private void getBlogs(String url, String blogPage) {
    webDriver.get(url);
    String urlActual = webDriver.getCurrentUrl();
    if (!url.equalsIgnoreCase(urlActual)) {
      Environment.sysOut(
          "blogPage:[" + blogPage + "], [" + url + "] DOES NOT EQUAL [" + urlActual + "]");
    }
    final List<WebElement> listBlogRecords = webDriver.findElements(byTableBlogs);
    final Map<Integer, String> mapFields = new HashMap<>();
    for (int record = 0; record < listBlogRecords.size(); record++) {
      final WebElement elementRecord = listBlogRecords.get(record);
      String xPath = "./td";
      if (record > 0) {
        xPath += "/a";
      }
      final List<WebElement> listWebElements = elementRecord.findElements(By.xpath(xPath));
      final Map<String, String> mapBlogs = new HashMap<>();
      String groupName = null;
      String blogID = null;
      String name = null;
      String posts = null;
      String lastActivity = null;
      String blogURL = null;
      for (int fieldIndex = 0; fieldIndex < listWebElements.size(); fieldIndex++) {
        if (record == 0) {
          final WebElement webElement = listWebElements.get(fieldIndex);
          highlightCurrentElement(webElement);
          final String fieldValue = webElement.getText();
          mapFields.put(fieldIndex, fieldValue);
        } else {
          final WebElement webElement = listWebElements.get(fieldIndex);
          highlightCurrentElement(webElement);
          final String fieldValue = webElement.getText();
          final String href = webElement.getAttribute("href");
          blogID = href.split("/")[4];
          String fieldName = mapFields.get(fieldIndex);
          // Account for Sort/Options
          if (mapFields.size() == 4) {
            fieldName = mapFields.get((fieldIndex + 1));
          }
          switch (fieldName) {
            case "Blog Name / Description":
              mapBlogs.put("BlogID", blogID);
              mapBlogs.put("GroupName", blogPage);
              groupName = blogPage;
              mapBlogs.put("BlogURL", href);
              blogURL = href;
              name = fieldValue;
              mapBlogs.put("Name", fieldValue);
              name = fieldValue;
              break;
            case "No. of Posts":
              mapBlogs.put("Posts", fieldValue);
              posts = fieldValue;
              break;
            case "Last Activity":
              mapBlogs.put("LastActivity", fieldValue);
              lastActivity = fieldValue;
              break;
            default:
              Environment.sysOut("Unknown field name: " + fieldName + ". Skipping.");
              break;
          }
        }
      }
      if (record > 0) {
        int numberOfPosts = 0;
        if (JavaHelpers.hasValue(posts)) {
          numberOfPosts = Integer.valueOf(posts);
        }
        Blogs.getBlogsList()
            .add(
                new Blogs(
                    groupName,
                    blogID,
                    name,
                    Integer.valueOf(numberOfPosts),
                    lastActivity,
                    blogURL));
      }
    }
  }
}
