package com.cjs.qa.pluralsight.pages;

import com.cjs.qa.selenium.Page;
import com.cjs.qa.utilities.Constants;
import com.cjs.qa.utilities.JavaHelpers;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TableOfContentsPage extends Page {
  public TableOfContentsPage(WebDriver webDriver) {
    super(webDriver);
  }

  private final By bySection =
      By.xpath("//div[@id='tab-table-of-contents']/section[contains(@class,'module')]");
  private final By byHeading2 = By.xpath(".//div[contains(@class,'side-menu-module-title')]/h2");
  private final By byHeading3 = By.xpath(".//h3");

  public void expandTableOfContentNodes() {
    List<WebElement> webElements;
    do {
      webElements = webDriver.findElements(By.xpath("//*[contains(@class,'icon-drop-down')]"));
      if (!webElements.isEmpty()) {
        final int index = (webElements.size() - 1);
        final WebElement webElement = webElements.get(index);
        webElement.click();
      }
    } while (!webElements.isEmpty());
  }

  public String getTableOfContents(String url) {
    // url += "/table-of-contents";
    // webDriver.get(url);
    final List<WebElement> webElements = webDriver.findElements(By.xpath(".//h3/a"));
    url = webElements.get(0).getAttribute("href");
    webDriver.get(url);
    JavaHelpers.sleep(3);
    expandTableOfContentNodes();
    final StringBuilder stringBuilder = new StringBuilder(Constants.NEWLINE);
    final List<WebElement> listSections = webDriver.findElements(bySection);
    for (int indexSection = 0; indexSection < listSections.size(); indexSection++) {
      final WebElement webElementSection = listSections.get(indexSection);
      final List<WebElement> listHeadings2 = webElementSection.findElements(byHeading2);
      for (int indexHeadings2 = 0; indexHeadings2 < listHeadings2.size(); indexHeadings2++) {
        final WebElement webElement2 = listHeadings2.get(indexHeadings2);
        final String heading2 = webElement2.getText();
        // stringBuilder.append(Constants.TAB);
        stringBuilder.append(heading2);
        stringBuilder.append(Constants.NEWLINE);
        final List<WebElement> listHeadings3 = webElementSection.findElements(byHeading3);
        for (int indexHeadings3 = 0; indexHeadings3 < listHeadings3.size(); indexHeadings3++) {
          final WebElement webElement3 = listHeadings3.get(indexHeadings3);
          final String heading3 = webElement3.getText();
          stringBuilder.append(Constants.TAB);
          stringBuilder.append(heading3);
          if (indexHeadings3 < (listHeadings3.size() - 1)) {
            stringBuilder.append(Constants.NEWLINE);
          }
        }
        if (indexSection < (listSections.size() - 1)) {
          stringBuilder.append(Constants.NEWLINE);
        }
      }
    }
    return "Course (Table of Contents)" + stringBuilder.toString();
  }
}
