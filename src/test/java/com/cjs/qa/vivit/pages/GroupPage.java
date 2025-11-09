package com.cjs.qa.vivit.pages;

import com.cjs.qa.core.Environment;
import com.cjs.qa.selenium.Page;
import com.cjs.qa.utilities.Constants;
import com.cjs.qa.utilities.FSO;
import com.cjs.qa.utilities.IExtension;
import com.cjs.qa.utilities.JavaHelpers;
import com.cjs.qa.vivit.VivitData;
import com.cjs.qa.vivit.VivitEnvironment;
import com.cjs.qa.vivit.objects.GroupLeaderLink;
import com.cjs.qa.vivit.objects.Groups;
import com.cjs.qa.vivit.objects.Leaders;
import com.google.common.base.Stopwatch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GroupPage extends Page {
  public GroupPage(WebDriver webDriver) {
    super(webDriver);
  }

  final List<String> listGroupLeaders = new ArrayList<>();
  final By byTitleBar = By.xpath(".//*[@id='SpTitleBar']");
  final By byLinksGroups = By.xpath("//*[@id='CustomPageBody']//a[text()!='contact us today!']");

  public void getGroupPageData() throws Throwable {
    Environment.sysOut(
        "***ClassMethodDebug***:[" + JavaHelpers.getCurrentClassMethodDebugName() + "]");
    String statusName = "getGroupPageData";
    if (VivitData.successFileExists(statusName)) {
      return;
    }
    Groups.setGroupPage(true);
    List<String> groupTypeList = Arrays.asList("LUGS", "SIGS");
    for (String groupType : groupTypeList) {
      Environment.sysOut(
          "Retrieving Data:[" + JavaHelpers.getCurrentClassMethodName() + "], [" + groupType + "]");
      getGroupData(groupType);
    }
    final String tables = "Groups;Leaders;GroupLeaderLink";
    String[] tableArray = tables.split(Constants.DELIMETER_LIST);
    for (String table : tableArray) {
      List<StringBuilder> sqlStringBuilderList = new ArrayList<>();
      sqlStringBuilderList.add(new StringBuilder(table));
      switch (table) {
        case "Groups":
          sqlStringBuilderList.add(Groups.appendRecords());
          break;
        case "Leaders":
          sqlStringBuilderList.add(Leaders.appendRecords());
          break;
        case "GroupLeaderLink":
          sqlStringBuilderList.add(GroupLeaderLink.appendRecords());
          break;
        default:
          break;
      }
      VivitData.updateTableFromCurrentToPreviousAndInsert(sqlStringBuilderList);
    }
    Groups.setGroupPage(false);
    VivitData.successFileCreate(statusName);
  }

  private void getGroupData(String group) throws Throwable {
    Environment.sysOut(
        "***ClassMethodDebug***:[" + JavaHelpers.getCurrentClassMethodDebugName() + "]");
    final String SYMBOL_ID = "id=";
    try {
      final By groupTitle = By.xpath(".//*[@id='ctl00_PageContent_lblPageSummaryTitle']");
      String GROUP = "";
      final String groupType = group.toUpperCase();
      String expectedTitle = "";
      switch (groupType) {
        case "LUGS":
          GROUP = VivitEnvironment.URL_LOGIN + "?page=LocalUserGroups";
          expectedTitle = "Vivit Local User Groups";
          break;
        case "SIGS":
          GROUP = VivitEnvironment.URL_LOGIN + "?page=SIGS";
          expectedTitle = "Special Interest Groups";
          break;
        default:
          return;
      }
      webDriver.get(GROUP);
      final String title = webDriver.findElement(byTitleBar).getText();
      Assert.assertEquals(title, expectedTitle, title);
      sleep(1, 0);
      final List<WebElement> links = webDriver.findElements(byLinksGroups);
      final List<String> chapterURLList = new ArrayList<>();
      for (int index = 0; index < links.size(); index++) {
        final WebElement element = links.get(index);
        final String urlPage = element.getAttribute("href");
        chapterURLList.add(urlPage);
      }
      // Print formatted URL list.
      StringBuilder stringBuilder = new StringBuilder();
      for (int indexChapterURL = 0; indexChapterURL < chapterURLList.size(); indexChapterURL++) {
        final String chapterURL = chapterURLList.get(indexChapterURL);
        if (indexChapterURL < (chapterURLList.size() - 1)) {
          stringBuilder.append("\n");
        }
        stringBuilder.append(chapterURL);
      }
      Environment.sysOut("urls:[" + stringBuilder.toString() + "]");
      for (int indexChapterURL = 0; indexChapterURL < chapterURLList.size(); indexChapterURL++) {
        final String chapterURL = chapterURLList.get(indexChapterURL);
        final String pageID = chapterURL;
        VivitEnvironment.sysOut("get:[" + chapterURL + "]");
        final Stopwatch stopwatch = Stopwatch.createStarted();
        webDriver.get(chapterURL);
        stopwatch.stop();
        final long lElapsedTime = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        String elapsedTime = String.valueOf(lElapsedTime) + "ms";
        Environment.sysOut(
            "ElapsedTime:["
                + elapsedTime
                + "], ElapsedTime:["
                + stopwatch.toString().replaceAll(" ", "")
                + "]");
        stopwatch.reset();
        final List<WebElement> wallPostElements =
            webDriver.findElements(By.xpath("//div[@class='WallPost']"));
        String wallPosts = String.valueOf(wallPostElements.size());
        String groupName = "";
        // final String currentURL = webDriver.getCurrentUrl();
        // if(currentURL.equalsIgnoreCase(chapterURL))
        if (objectExists(groupTitle, Environment.getTimeOutElement())) {
          final WebElement webElementGroup = webDriver.findElement(groupTitle);
          highlightCurrentElement(webElementGroup);
          groupName = webElementGroup.getText();
          switch (groupType) {
            case "LUGS":
              groupName = groupName.replace("Local User Group", groupType);
              break;
            case "SIGS":
              groupName = groupName.replace("Special Interest Group", groupType);
              break;
          }
          final Map<String, By> mapByGroupURLsBy = getGroupURLs();
          final Map<String, String> mapGroupURLs = new HashMap<>();
          for (final Entry entry : mapByGroupURLsBy.entrySet()) {
            final String keyBy = (String) entry.getKey();
            final By byElement = mapByGroupURLsBy.get(keyBy);
            String objectURL = null;
            if (objectExists(byElement, 1)) {
              final WebElement webElement = webDriver.findElement(byElement);
              highlightCurrentElement(webElement);
              objectURL = webElement.getAttribute("href");
              // "https://www.vivit-worldwide.org" + objectURL;
            }
            mapGroupURLs.put(keyBy, objectURL);
          }
          Groups.getGroupsList()
              .add(new Groups(groupType, pageID, groupName, elapsedTime, wallPosts, chapterURL));
          Groups.getGroupsList().get((Groups.getGroupsList().size() - 1)).updateURLs(mapGroupURLs);
        } else { // Broken Group URL.
          Environment.sysOut("Missing chapterURL:" + chapterURL);
          // groupType = groupType;
          // pageID = pageID;
          groupName =
              groupType + ": " + pageID.substring((pageID.lastIndexOf("/") + 1), pageID.length());
          elapsedTime = "N/A";
          wallPosts = "N/A";
          // chapterURL = chapterURL;
          Groups.getGroupsList()
              .add(new Groups(groupType, pageID, groupName, elapsedTime, wallPosts, chapterURL));
        }
        Environment.sysOut("groupName:[" + groupName + "]");
        // Get the labels/hrefs links of the Groups.
        final By hrefLinks = By.xpath(".//*[@id='ctl00_PageContent_tdZone2']/table/tbody/tr/td/b");
        final List<WebElement> webElements = webDriver.findElements(hrefLinks);
        for (WebElement webElement : webElements) {
          // Need to verify this so there's no unhandled error.
          highlightCurrentElement(webElement);
          final String leaderType = webElement.getText();
          webElement = webElement.findElement(By.xpath("./../a"));
          highlightCurrentElement(webElement);
          final String leaderName = webElement.getText();
          final String href = webElement.getAttribute("href");
          String webSiteMemberID = href.replace(Constants.NEWLINE, "");
          if (webSiteMemberID.indexOf(SYMBOL_ID) != -1) {
            webSiteMemberID =
                webSiteMemberID.substring(
                    (webSiteMemberID.indexOf(SYMBOL_ID) + SYMBOL_ID.length()),
                    webSiteMemberID.length());
          }
          Environment.sysOut(
              "Leader Type:["
                  + leaderType
                  + "], Leader:["
                  + leaderName
                  + "], Href:["
                  + href
                  + "], webSiteMemberID:["
                  + webSiteMemberID
                  + "]");
          if (!Leaders.getLeadersWebSiteMemberIDList().contains(webSiteMemberID)) {
            Leaders.getLeadersList().add(new Leaders(webSiteMemberID, leaderName, href));
          }
          // if
          // (!GroupLeaderLink.getGroupLeaderLinkWebSiteMemberIDList().contains(webSiteMemberID))
          // {
          GroupLeaderLink.getGroupLeaderLinkList()
              .add(new GroupLeaderLink(groupType, pageID, webSiteMemberID, leaderType));
          // }
        }
      }
    } catch (final Exception e) {
      Environment.sysOut(e);
    }
  }

  private Map<String, By> getGroupURLs() {
    final String ROOT_NODE_PRE = ".//div[@id='pnlNavBarFormat']/table//a/text()[contains(.,'";
    final String ROOT_NODE_POST = "')]/..";
    final Map<String, By> mapByGroupURLsBy = new HashMap<>();
    mapByGroupURLsBy.put(
        "GroupDirectoryURL", By.xpath(ROOT_NODE_PRE + "Group Directory" + ROOT_NODE_POST));
    mapByGroupURLsBy.put("CalendarURL", By.xpath(ROOT_NODE_PRE + "Calendar" + ROOT_NODE_POST));
    mapByGroupURLsBy.put("BlogsURL", By.xpath(ROOT_NODE_PRE + "Blogs" + ROOT_NODE_POST));
    mapByGroupURLsBy.put("ForumsURL", By.xpath(ROOT_NODE_PRE + "Forums" + ROOT_NODE_POST));
    mapByGroupURLsBy.put(
        "PhotoGalleryURL", By.xpath(ROOT_NODE_PRE + "Photo Gallery" + ROOT_NODE_POST));
    return mapByGroupURLsBy;
  }

  public void searchSites() {
    try {
      StringBuilder stringBuilderCSV = new StringBuilder();
      final String fileName = Environment.FOLDER_DATA + "searchVivitSites" + IExtension.CSV;
      final String LIGS = VivitEnvironment.URL_LOGIN + "?page=LocalUserGroups";
      final String SIGS = VivitEnvironment.URL_LOGIN + "?page=SIGS";
      final By ligLinks = By.xpath(".//*[@id='CustomPageBody']//a[text()!='contact us today!']");
      final By sigLinks = By.xpath(".//*[@id='CustomPageBody']//a[text()!='contact us today!']");
      final By hrefLinks = By.xpath(".//*[@id='ctl00_PageContent_tdZone2']/table/tbody/tr/td/a");
      // final List<String> urls =
      // Arrays.asList("https://vivitworldwide.site-ym" + IExtension.COM +
      // "/", "https://vivitworldwide.site-ym" + IExtension.COM +
      // "/?page=board", "https://vivitworldwide.site-ym" + IExtension.COM
      // + "/staff/", "https://vivitworldwide.site-ym" + IExtension.COM +
      // "/?page=Volunteer", "https://c.ymcdn" + IExtension.COM +
      // "/sites/www.vivit-worldwide.org/resource/resmgr/docs/2017_VivitMemberBrochure"
      // + IExtension.PDF, "https://vivitworldwide.site-ym" +
      // IExtension.COM + "/?page=HallofFame");
      // for (final String url : urls) {
      // VivitEnvironment.sysOut("Searching:[" + url + "]");
      // webDriver.get(url);
      // }
      webDriver.get(LIGS);
      sleep(1, 0);
      List<WebElement> links = webDriver.findElements(ligLinks);
      for (int index = 0; index < links.size(); index++) {
        final WebElement element = links.get(index);
        final String url = element.getAttribute("href");
        VivitEnvironment.sysOut("Clicking:[" + url + "]");
        clickObject(element);
        final List<WebElement> webElements = webDriver.findElements(hrefLinks);
        for (WebElement webElement : webElements) {
          final String leaderName = webElement.getText();
          final String href = webElement.getAttribute("href");
          webElement = webElement.findElement(By.xpath("./../b"));
          final String leaderType = webElement.getText();
          Environment.sysOut(
              "Leader Type:[" + leaderType + "], Leader:[" + leaderName + "], Href:[" + href + "]");
        }
        if (index < links.size()) {
          webDriver.get(LIGS);
          sleep(1, 0);
          links = webDriver.findElements(ligLinks);
        }
      }
      webDriver.get(SIGS);
      sleep(1, 0);
      links = webDriver.findElements(sigLinks);
      for (int index = 0; index < links.size(); index++) {
        final WebElement element = links.get(index);
        final String url = element.getAttribute("href");
        VivitEnvironment.sysOut("Clicking:[" + url + "]");
        clickObject(element);
        final List<WebElement> webElements = webDriver.findElements(hrefLinks);
        for (WebElement webElement : webElements) {
          final String leaderName = webElement.getText();
          final String href = webElement.getAttribute("href");
          webElement = webElement.findElement(By.xpath("./../b"));
          final String leaderType = webElement.getText();
          Environment.sysOut(
              "Leader Type:[" + leaderType + "], Leader:[" + leaderName + "], Href:[" + href + "]");
        }
        if (index < links.size()) {
          webDriver.get(SIGS);
          sleep(1, 0);
          webDriver.findElements(sigLinks);
        }
      }
      FSO.fileWrite(fileName, stringBuilderCSV.toString(), false);
      stringBuilderCSV = null;
      // https://www.vivit-worldwide.org/?page=LocalUserGroups
      // minimizeWindow();
    } catch (final Exception e) {
      Environment.sysOut(e);
    }
  }

  public void validateGroups() {
    validateGroup("LUGS");
    validateGroup("SIGS");
  }

  public void validateGroup(String group) {
    // Default
    final String GROUP = group.toUpperCase();
    String url = VivitEnvironment.URL_LOGIN + "?page=LocalUserGroups";
    switch (GROUP) {
      case "LUGS":
        url = VivitEnvironment.URL_LOGIN + "?page=LocalUserGroups";
        break;
      case "SIGS":
        url = VivitEnvironment.URL_LOGIN + "?page=SIGS";
        break;
    }
    webDriver.get(url);
    final List<WebElement> webElements = webDriver.findElements(byLinksGroups);
    Environment.sysOut("Total " + GROUP + ":[" + webElements.size() + "]");
    StringBuilder stringBuilder = new StringBuilder();
    for (int index = 0; index < webElements.size(); index++) {
      final WebElement webElement = webElements.get(index);
      highlightCurrentElement(webElement);
      final String item = webElement.getText();
      if (!item.trim().equals("")) {
        stringBuilder.append(item + Constants.NEWLINE);
        Environment.sysOut(index + ":[" + item + "]");
      }
    }
    final String fileData = Environment.FOLDER_DATA + group + "-List" + IExtension.TXT;
    String expected = FSO.fileReadAll(fileData);
    expected = expected.replace("CÃ´te d'Ivoire", "Côte d'Ivoire");
    final String actual = stringBuilder.toString();
    Environment.softAssert.assertEquals(expected, actual, group);
  }
}
