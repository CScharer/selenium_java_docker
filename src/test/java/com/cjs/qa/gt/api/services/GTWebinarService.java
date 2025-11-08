package com.cjs.qa.gt.api.services;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.cjs.qa.core.Environment;
import com.cjs.qa.rest.REST;
import com.cjs.qa.utilities.Constants;
import com.cjs.qa.utilities.IExtension;
import com.cjs.qa.utilities.IHTTP;

public class GTWebinarService
{
	public static final String	USER_ID				= "jill.vivit@yahoo.com";
	public static final String	PASSWORD			= "vivitrules1";
	public static final String	API_CONSUMER_KEY	= "WGhbDnxCGUwKNABGKeymjoII4gqalCa3";
	public static final String	API_CONSUMER_SECRET	= "DdkRQTJGLq4VF20t";
	public static final String	API_VERSION			= "v2";
	public static final String	URL_GT				= "https://api.getgo" + IExtension.COM + "/";
	public static final String	API_GT_AUTH			= URL_GT + "/oauth/" + API_VERSION;
	public static final String	API_GT_BASE			= URL_GT + "/G2W/rest/" + API_VERSION;
	public static final String	CONNECTED_TO		= "Connection to [";
	public static String		accountKey			= null;
	public static String		accessToken			= null;
	private static boolean		serviceActive		= true;											// false;
	// private static int callID = 0;

	@Test
	public void gtwWebinarServiceTest()
	{
		Environment.sysOut("Testing!");
	}

	public GTWebinarService()
	{
		if (!serviceActive)
		{
			try
			{
				final HttpURLConnection httpUrlConnection = (HttpURLConnection) new URL(URL_GT).openConnection();
				httpUrlConnection.setRequestMethod("HEAD");
				final int responseCode = httpUrlConnection.getResponseCode();
				if (!((responseCode >= HttpURLConnection.HTTP_OK)
						&& (responseCode < HttpURLConnection.HTTP_BAD_REQUEST)))
				{
					Environment.sysOut(CONNECTED_TO + URL_GT + "] unsuccessful with response of [" + responseCode + ":"
							+ IHTTP.getResponseValue(responseCode) + "].");
					// Environment.addScenarioError(CONNECTED_TO + URL_YM + "]
					// unsuccessful with response of [" + responseCode + ":" +
					// IHTTP.getResponseValue(responseCode) + "].")
				}
				Assert.assertTrue(
						CONNECTED_TO + URL_GT + "] unsuccessful with response of [" + responseCode + ":"
								+ IHTTP.getResponseValue(responseCode) + "].",
						((responseCode >= HttpURLConnection.HTTP_OK)
								&& (responseCode < HttpURLConnection.HTTP_BAD_REQUEST)));
				Environment.sysOut(CONNECTED_TO + URL_GT + "] successfull with response of [" + responseCode + ":"
						+ IHTTP.getResponseValue(responseCode) + "].");
				serviceActive = true;
			} catch (final Exception e)
			{
				Environment.sysOut(e);
			}
		}
	}

	public static Map<String, String> getAPIJSONResponse(String credentials, String requestMethod, String apiRequest,
			String url) throws Throwable
	{
		return REST.getAPIJSONResponse(credentials, requestMethod, apiRequest, url);
	}

	public static String getAPIKey() throws Throwable
	{
		return Constants.nlTab(1, 1) + "<ApiKey>" + API_CONSUMER_KEY + "</ApiKey>";
	}

	public static String getAccessToken()
	{
		return accessToken;
	}

	public static void setAccessToken(String accessToken)
	{
		GTWebinarService.accessToken = accessToken;
	}

	public static String getAccountKey()
	{
		return accountKey;
	}

	public static void setAccountKey(String accountKey)
	{
		GTWebinarService.accountKey = accountKey;
	}
}