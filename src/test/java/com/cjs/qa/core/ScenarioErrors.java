package com.cjs.qa.core;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;

public class ScenarioErrors {
  private static Map<Integer, String> errors = new HashMap<>();

  public static Map<Integer, String> getErrors() {
    return errors;
  }

  public void assertErrors(String message) {
    Assert.assertTrue(message, getErrors().size() == 0);
  }

  public void add(String error) {
    getErrors().put((Environment.scenarioErrors.size() + 1), error);
  }

  public static void clear() {
    errors = new HashMap<>();
  }
}
