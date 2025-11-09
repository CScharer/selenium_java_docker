package com.cjs.qa.vivit.pages;

import com.cjs.qa.core.Environment;
import com.cjs.qa.selenium.Page;
import com.cjs.qa.utilities.JavaHelpers;
import com.cjs.qa.vivit.VivitData;
import com.cjs.qa.vivit.objects.Forums;
import com.cjs.qa.vivit.objects.Groups;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForumsPage extends Page {
  public ForumsPage(WebDriver webDriver) {
    super(webDriver);
  }

  final By byTableForums =
      By.xpath(".//*[@id='SpContent_Container']/table/tbody/tr[@class!='header']");

  public void getForumData() throws Throwable {
    Environment.sysOut(
        "***ClassMethodDebug***:[" + JavaHelpers.getCurrentClassMethodDebugName() + "]");
    String statusName = "getForumData";
    if (VivitData.successFileExists(statusName)) {
      return;
    }
    // final JDBC jdbc = new JDBC("", "QAAuto");
    // final String sql = JDBCConstants.SELECT_ALL_FROM +
    // "[v_Vivit_GroupPage_Forum_URLs];";
    // final List<Map<String, String>> mapForums =
    // jdbc.queryResultsString(sql, false);
    // jdbc.close();
    // for (final Map<String, String> mapForum : mapForums)
    // {
    // getForums(mapForum.get("ForumsURL"), mapForum.get("Name"));
    // }
    for (final Groups groups : Groups.getGroupsList()) {
      if (JavaHelpers.hasValue(groups.getForumsURL())) {
        Environment.sysOut(
            "Retrieving Data:["
                + JavaHelpers.getCurrentClassMethodName()
                + "], ["
                + groups.getGroupName()
                + "], ["
                + groups.getForumsURL()
                + "]");
        getForums(groups.getForumsURL(), groups.getGroupName());
      } else {
        Environment.sysOut(
            "Retrieving Null:["
                + JavaHelpers.getCurrentClassMethodName()
                + "], ["
                + groups.getGroupName()
                + "], ["
                + groups.getForumsURL()
                + "]");
      }
    }
    List<StringBuilder> sqlStringBuilderList = new ArrayList<>();
    sqlStringBuilderList.add(new StringBuilder("Forums"));
    sqlStringBuilderList.add(Forums.appendRecords());
    VivitData.updateTableFromCurrentToPreviousAndInsert(sqlStringBuilderList);
    VivitData.successFileCreate(statusName);
  }

  private void getForums(String url, String forumPage) {
    webDriver.get(url);
    final List<WebElement> listForumRecords = webDriver.findElements(byTableForums);
    for (int record = 0; record < listForumRecords.size(); record++) {
      final WebElement elementRecord = listForumRecords.get(record);
      String xPath = "./td";
      final List<WebElement> listWebElements = elementRecord.findElements(By.xpath(xPath));
      String groupName = null;
      String forumID = null;
      String name = null;
      String topics = null;
      String posts = null;
      String lastActivity = null;
      String forumURL = null;
      for (int fieldIndex = 0; fieldIndex < listWebElements.size(); fieldIndex++) {
        final WebElement webElement = listWebElements.get(fieldIndex);
        highlightCurrentElement(webElement);
        final String fieldValue = webElement.getText();
        // final String fieldName = mapFields.get(fieldIndex);
        String href = "";
        String ForumID = "";
        if (fieldIndex == 0) {
          xPath = "./b/a";
          final WebElement hrefElement = webElement.findElement(By.xpath(xPath));
          href = hrefElement.getAttribute("href");
          ForumID = href.split("/")[4];
          // fieldName = mapFields.get(fieldIndex);
        }
        switch (fieldIndex) {
          case 1: // "Topics":
            topics = fieldValue;
            break;
          case 2: // "Posts":
            posts = fieldValue;
            break;
          case 3: // "Latest Activity":
            lastActivity = fieldValue;
            break;
          case 0: // default:
            forumID = ForumID;
            groupName = forumPage;
            forumURL = href;
            name = fieldValue;
            break;
          default:
            Environment.sysOut("Unexpected field index: " + fieldIndex + ". Skipping.");
            break;
        }
      }
      Forums.getForumsList()
          .add(
              new Forums(
                  groupName,
                  forumID,
                  name,
                  Integer.valueOf(topics),
                  Integer.valueOf(posts),
                  lastActivity,
                  forumURL));
    }
  }
}
