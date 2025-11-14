package com.cjs.qa.junit.tests;

import com.cjs.qa.utilities.RetryAnalyzer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class to demonstrate and verify RetryAnalyzer functionality.
 *
 * <p>This class includes examples of:
 *
 * <ul>
 *   <li>Using RetryAnalyzer with @Test annotation
 *   <li>Configuring retry count programmatically
 *   <li>Testing retry behavior
 * </ul>
 */
public class RetryAnalyzerTest {
  private static final Logger LOGGER = LogManager.getLogger(RetryAnalyzerTest.class);
  private static int testCounter = 0;

  @BeforeMethod
  public void setUp() {
    testCounter = 0;
  }

  @Test(retryAnalyzer = RetryAnalyzer.class)
  public void testRetryAnalyzerWithPassingTest() {
    LOGGER.info("This test should pass on first attempt");
    Assert.assertTrue(true, "Test should pass");
  }

  @Test(retryAnalyzer = RetryAnalyzer.class)
  public void testRetryAnalyzerWithFailingTest() {
    // This test will fail and should be retried
    testCounter++;
    LOGGER.info("Test attempt: {}", testCounter);
    // Fail first 2 times, pass on 3rd (if max retry is 3)
    if (testCounter < 3) {
      Assert.fail("Intentionally failing test (attempt " + testCounter + ")");
    }
    Assert.assertTrue(true, "Test passes on retry");
  }

  @Test
  public void testRetryAnalyzerConfiguration() {
    int originalCount = RetryAnalyzer.getMaxRetryCount();
    LOGGER.info("Original retry count: {}", originalCount);

    // Test setting retry count
    RetryAnalyzer.setMaxRetryCount(5);
    Assert.assertEquals(RetryAnalyzer.getMaxRetryCount(), 5, "Retry count should be 5");

    // Test reset to default
    RetryAnalyzer.resetToDefault();
    Assert.assertEquals(
        RetryAnalyzer.getMaxRetryCount(), originalCount, "Retry count should be reset to original");

    // Test negative value handling
    RetryAnalyzer.setMaxRetryCount(-1);
    Assert.assertEquals(
        RetryAnalyzer.getMaxRetryCount(), 0, "Negative retry count should become 0");
  }

  @Test(retryAnalyzer = RetryAnalyzer.class)
  public void testRetryAnalyzerWithZeroRetries() {
    // Disable retries for this test
    RetryAnalyzer.setMaxRetryCount(0);
    LOGGER.info("This test has retries disabled");
    Assert.assertTrue(true, "Test should pass");
  }
}
