package com.cjs.qa.wellmark.pages;

import com.cjs.qa.selenium.Page;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TempPage extends Page {
  public TempPage(WebDriver webDriver) {
    super(webDriver);
  }

  private final By linksULR = By.xpath(".//*[@id='post-18078']//li/a[@href]");
  List<String> listLinks = new ArrayList<>();
}
