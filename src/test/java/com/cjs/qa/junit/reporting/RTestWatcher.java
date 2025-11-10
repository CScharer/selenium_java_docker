package com.cjs.qa.junit.reporting;

import com.cjs.qa.core.Environment;
import org.junit.AssumptionViolatedException;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class RTestWatcher implements TestRule {
  private final RTestRun rTestRun = new RTestRun(null, null);

  @Rule
  public TestWatcher testWatcher =
      new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
          rTestRun.cRTestSet().cRScenario().cRTest().addStep(description, "Failed");
          Environment.sysOut(description.toString());
        }

        @Override
        protected void finished(Description description) {
          rTestRun.cRTestSet().cRScenario().cRTest().addStep(description, "Finished");
          Environment.sysOut(description.toString());
        }

        @Override
        protected void skipped(AssumptionViolatedException e, Description description) {
          rTestRun.cRTestSet().cRScenario().cRTest().addStep(description, "Skipped");
          Environment.sysOut(description.toString());
        }

        @Override
        protected void starting(Description description) {
          rTestRun.cRTestSet().cRScenario().cRTest().addStep(description, "Starting");
          Environment.sysOut(description.toString());
        }

        @Override
        protected void succeeded(Description description) {
          rTestRun.cRTestSet().cRScenario().cRTest().addStep(description, "Succeeded");
          Environment.sysOut(description.toString());
        }
      };

  @Override
  public Statement apply(Statement base, Description description) {
    return base;
  }
}
