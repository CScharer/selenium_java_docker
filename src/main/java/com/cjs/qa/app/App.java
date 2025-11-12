package com.cjs.qa.app;

import org.apache.log4j.Logger;

public final class App {
  private App() {
    // Utility class - prevent instantiation
  }

  public static void main(String[] args) {
    Logger.getRootLogger().debug("Hello World!");
  }
}
