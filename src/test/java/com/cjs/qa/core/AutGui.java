package com.cjs.qa.core;

import com.cjs.qa.utilities.SoftAssert;
import org.openqa.selenium.WebDriver;

public class AutGui {
  public SoftAssert SoftAssert;

  public AutGui(WebDriver webDriver) {
    SoftAssert = new SoftAssert();
    webDriver
        .manage()
        .timeouts()
        .pageLoadTimeout(java.time.Duration.ofSeconds(Environment.getTimeOutPage()));
  }

  public static String updateSQL(String sql) {
    return sql;
  }
}
