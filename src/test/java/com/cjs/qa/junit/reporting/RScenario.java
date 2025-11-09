package com.cjs.qa.junit.reporting;

import java.util.ArrayList;
import java.util.List;

public class RScenario extends RTest {
  private String scenarioName;
  private String scenarioStatus;
  private List<RTest> RTest = new ArrayList<>();

  public RScenario(String stepName, String stepStatus) {
    super(stepName, stepStatus);
    setScenarioName(scenarioName);
    setScenarioStatus(scenarioStatus);
  }

  public void addTest(String testName, String testStatus) {
    getRTest().add(new RTest(testName, testStatus));
  }

  public RTest cRTest() {
    return getRTest().get(getRTest().size() - 1);
  }

  public List<RTest> getRTest() {
    return RTest;
  }

  public String getScenarioName() {
    return scenarioName;
  }

  public String getScenarioStatus() {
    return scenarioStatus;
  }

  public void setRTest(List<RTest> rTest) {
    RTest = rTest;
  }

  public void setScenarioName(String scenarioName) {
    this.scenarioName = scenarioName;
  }

  public void setScenarioStatus(String scenarioStatus) {
    this.scenarioStatus = scenarioStatus;
  }
}
