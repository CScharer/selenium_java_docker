package com.cjs.qa.gt.api.services;

import com.cjs.qa.core.Environment;
import com.cjs.qa.core.security.EAPIKeys;
import com.cjs.qa.rest.REST;
import com.cjs.qa.utilities.Constants;
import com.cjs.qa.utilities.IExtension;
import com.cjs.qa.utilities.IHTTP;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("PMD.ClassNamingConventions")
public class GTWebinarServiceTests {
  public static final String USER_ID = "jill.vivit@yahoo.com";
  // TODO: Migrate to Google Cloud Secret Manager (EPasswords enum)
  // This password is currently hardcoded and should be moved to Secret Manager
  // See: docs/issues/open/cleanup-hardcoded-passwords.md
  public static final String PASSWORD = "vivitrules1";

  // API credentials migrated to Google Cloud Secret Manager via EAPIKeys enum
  // See: docs/issues/open/hardcoded-api-keys-and-secrets.md
  public static String getApiConsumerKey() {
    return EAPIKeys.VIVIT_GT_WEBINAR_CONSUMER_KEY.getValue();
  }

  public static String getApiConsumerSecret() {
    return EAPIKeys.VIVIT_GT_WEBINAR_CONSUMER_SECRET.getValue();
  }

  // Deprecated: Use getApiConsumerKey() instead
  // Will be removed after migration to Secret Manager is complete
  @Deprecated public static final String API_CONSUMER_KEY = "WGhbDnxCGUwKNABGKeymjoII4gqalCa3";

  // Deprecated: Use getApiConsumerSecret() instead
  // Will be removed after migration to Secret Manager is complete
  @Deprecated public static final String API_CONSUMER_SECRET = "DdkRQTJGLq4VF20t";
  public static final String API_VERSION = "v2";
  public static final String URL_GT = "https://api.getgo" + IExtension.COM + "/";
  public static final String API_GT_AUTH = URL_GT + "/oauth/" + API_VERSION;
  public static final String API_GT_BASE = URL_GT + "/G2W/rest/" + API_VERSION;
  public static final String CONNECTED_TO = "Connection to [";
  private static String accountKey = null;
  private static String accessToken = null;
  private static boolean serviceActive = true; // false;

  // private static int callID = 0;

  @Test
  public void gtwWebinarServiceTest() {
    Environment.sysOut("Testing!");
  }

  public GTWebinarServiceTests() {
    if (!serviceActive) {
      try {
        final HttpURLConnection httpUrlConnection =
            (HttpURLConnection) new URL(URL_GT).openConnection();
        httpUrlConnection.setRequestMethod("HEAD");
        final int responseCode = httpUrlConnection.getResponseCode();
        if (!(responseCode >= HttpURLConnection.HTTP_OK
            && responseCode < HttpURLConnection.HTTP_BAD_REQUEST)) {
          Environment.sysOut(
              CONNECTED_TO
                  + URL_GT
                  + "] unsuccessful with response of ["
                  + responseCode
                  + ":"
                  + IHTTP.getResponseValue(responseCode)
                  + "].");
          // Environment.addScenarioError(CONNECTED_TO + URL_YM + "]
          // unsuccessful with response of [" + responseCode + ":" +
          // IHTTP.getResponseValue(responseCode) + "].")
        }
        Assert.assertTrue(
            CONNECTED_TO
                + URL_GT
                + "] unsuccessful with response of ["
                + responseCode
                + ":"
                + IHTTP.getResponseValue(responseCode)
                + "].",
            responseCode >= HttpURLConnection.HTTP_OK
                && responseCode < HttpURLConnection.HTTP_BAD_REQUEST);
        Environment.sysOut(
            CONNECTED_TO
                + URL_GT
                + "] successfull with response of ["
                + responseCode
                + ":"
                + IHTTP.getResponseValue(responseCode)
                + "].");
        serviceActive = true; // NOPMD - Constructor sets service availability state
      } catch (final Exception e) {
        Environment.sysOut(e);
      }
    }
  }

  public static Map<String, String> getAPIJSONResponse(
      String credentials, String requestMethod, String apiRequest, String url) throws Throwable {
    return REST.getAPIJSONResponse(credentials, requestMethod, apiRequest, url);
  }

  public static String getAPIKey() throws Throwable {
    return Constants.nlTab(1, 1) + "<ApiKey>" + getApiConsumerKey() + "</ApiKey>";
  }

  public static String getAccessToken() {
    return accessToken;
  }

  public static void setAccessToken(String accessToken) {
    GTWebinarServiceTests.accessToken = accessToken;
  }

  public static String getAccountKey() {
    return accountKey;
  }

  public static void setAccountKey(String accountKey) {
    GTWebinarServiceTests.accountKey = accountKey;
  }
}
