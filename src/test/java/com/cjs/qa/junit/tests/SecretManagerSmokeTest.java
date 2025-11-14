package com.cjs.qa.junit.tests;

import com.cjs.qa.core.security.EAPIKeys;
import com.cjs.qa.core.security.EPasswords;
import com.cjs.qa.utilities.SecureConfig;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Smoke Test for Google Cloud Secret Manager Integration
 *
 * <p>Purpose: Quick verification that secrets can be retrieved from Google Cloud Secret Manager
 * Target: < 5 seconds execution time Coverage: One password + one API key retrieval
 *
 * <p>This test verifies: 1. SecureConfig can connect to Google Cloud 2. Secrets exist and are
 * accessible 3. EPasswords and EAPIKeys enums work correctly
 *
 * <p>Run with: ./mvnw test -Dtest=SecretManagerSmokeTest Or: Included in smoke test suite
 */
@Epic("Smoke Tests")
@Feature("Secret Manager Integration")
public class SecretManagerSmokeTest {
  private static final Logger LOGGER = LogManager.getLogger(SecretManagerSmokeTest.class);

  @Test(priority = 1, groups = "smoke")
  @Story("Secret Manager Connectivity")
  @Severity(SeverityLevel.BLOCKER)
  @Description("Verify that SecureConfig can retrieve a password from Google Cloud Secret Manager")
  public void smokeTestPasswordRetrieval() {
    LOGGER.info("\n========================================");
    LOGGER.info("🔐 SMOKE TEST - Password Retrieval");
    LOGGER.info("========================================");

    Allure.step("Retrieve password using EPasswords enum");
    String password = EPasswords.BTSQA.getValue();

    Allure.step("Verify password is not null");
    Assert.assertNotNull(password, "Password should not be null");

    Allure.step("Verify password is not empty");
    Assert.assertFalse(password.isEmpty(), "Password should not be empty");

    Allure.step("Verify password has minimum length");
    Assert.assertTrue(
        password.length() >= 8, "Password should have minimum length of 8 characters");

    LOGGER.info("✅ Password retrieved successfully (length: {})", password.length());
    LOGGER.info("========================================\n");
  }

  @Test(priority = 2, groups = "smoke")
  @Story("API Key Retrieval")
  @Severity(SeverityLevel.CRITICAL)
  @Description("Verify that EAPIKeys enum can retrieve an API key from Google Cloud Secret Manager")
  public void smokeTestApiKeyRetrieval() {
    LOGGER.info("\n========================================");
    LOGGER.info("🔑 SMOKE TEST - API Key Retrieval");
    LOGGER.info("========================================");

    Allure.step("Retrieve API key using EAPIKeys enum");
    String apiKey = EAPIKeys.VIVIT_GT_WEBINAR_CONSUMER_KEY.getValue();

    Allure.step("Verify API key is not null");
    Assert.assertNotNull(apiKey, "API key should not be null");

    Allure.step("Verify API key is not empty");
    Assert.assertFalse(apiKey.isEmpty(), "API key should not be empty");

    Allure.step("Verify API key has reasonable length");
    Assert.assertTrue(apiKey.length() >= 10, "API key should have minimum length of 10 characters");

    LOGGER.info("✅ API key retrieved successfully (length: {})", apiKey.length());
    LOGGER.info("========================================\n");
  }

  @Test(priority = 3, groups = "smoke")
  @Story("Secret Manager Direct Access")
  @Severity(SeverityLevel.NORMAL)
  @Description("Verify that SecureConfig can directly retrieve secrets by name")
  public void smokeTestDirectSecretRetrieval() {
    LOGGER.info("\n========================================");
    LOGGER.info("🔓 SMOKE TEST - Direct Secret Retrieval");
    LOGGER.info("========================================");

    Allure.step("Retrieve secret directly using SecureConfig");
    String secret = SecureConfig.getPassword("AUTO_BTSQA_PASSWORD");

    Allure.step("Verify secret is not null");
    Assert.assertNotNull(secret, "Secret should not be null");

    Allure.step("Verify secret is not empty");
    Assert.assertFalse(secret.isEmpty(), "Secret should not be empty");

    LOGGER.info("✅ Direct secret retrieval successful");
    LOGGER.info("========================================\n");
  }
}
