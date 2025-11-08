package com.cjs.qa.linkedin;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.cjs.qa.core.Environment;
import com.cjs.qa.core.security.EPasswords;
import com.cjs.qa.linkedin.data.Data;
import com.cjs.qa.linkedin.pages.ConnectionsPage;
import com.cjs.qa.linkedin.pages.ContactInfoPage;
import com.cjs.qa.linkedin.pages.HomePage;
import com.cjs.qa.linkedin.pages.LoginAlternatePage;
import com.cjs.qa.linkedin.pages.LoginPage;
import com.cjs.qa.utilities.CJSConstants;
import com.cjs.qa.utilities.Constants;
import com.cjs.qa.utilities.JavaHelpers;

public class LinkedIn
{
	public static final String	LINKEDIN_URL	= LinkedInEnvironment.URL_LOGIN.toLowerCase() + "in/";
	public ConnectionsPage		ConnectionsPage;
	public ContactInfoPage		ContactInfoPage;
	public HomePage				HomePage;
	public LoginAlternatePage	LoginAlternatePage;
	public LoginPage			LoginPage;

	public LinkedIn(WebDriver webDriver)
	{
		ConnectionsPage = new ConnectionsPage(webDriver);
		ContactInfoPage = new ContactInfoPage(webDriver);
		HomePage = new HomePage(webDriver);
		LoginAlternatePage = new LoginAlternatePage(webDriver);
		LoginPage = new LoginPage(webDriver);
		webDriver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(120));
	}

	public void getConnectionContactInfo(WebDriver webDriver, boolean run) throws Throwable
	{
		Environment.sysOut(Constants.CLASS_METHOD_DEBUG + JavaHelpers.getCurrentClassMethodDebugName() + "]");
		String methodName = JavaHelpers.getCurrentMethodName();
		if (!run)
		{
			Environment.sysOut(methodName + ":run:[" + run + "]");
			return;
		}
		List<Map<String, String>> linkedInMapList = Data.getJdbc().queryResultsString(Data.getQueryLinkedInURL(),
				false);
		if (linkedInMapList.isEmpty())
		{
			Environment.sysOut(methodName + ":linkedInMapList.size():[" + linkedInMapList.size() + "]");
			return;
		}
		ContactInfoPage.getContactInfoPageData(linkedInMapList);
		Data.update();
	}

	public void getConnectionURLS(WebDriver webDriver, boolean run) throws Throwable
	{
		Environment.sysOut(Constants.CLASS_METHOD_DEBUG + JavaHelpers.getCurrentClassMethodDebugName() + "]");
		String methodName = JavaHelpers.getCurrentMethodName();
		if (!run)
		{
			Environment.sysOut(methodName + ":run:[" + run + "]");
			return;
		}
		List<Map<String, String>> linkedInMapList = Data.getJdbc().queryResultsString(Data.getQueryNoLinkedInURL(),
				false);
		if (linkedInMapList.isEmpty())
		{
			Environment.sysOut(methodName + ":linkedInMapList.size():[" + linkedInMapList.size() + "]");
			return;
		}
		HomePage.clickButtonMyNetwork();
		sleepRandom(2, 4, 250, 750);
		String urlConnections = LinkedInEnvironment.URL_LOGIN + "mynetwork/invite-connect/connections/";
		webDriver.get(urlConnections);
		StringBuilder sqlStringBuilder = new StringBuilder();
		for (int mapIndex = 0; mapIndex < linkedInMapList.size(); mapIndex++)
		{
			Map<String, String> map = linkedInMapList.get(mapIndex);
			if (JavaHelpers.hasValue(map.get("First Name")) && JavaHelpers.hasValue(map.get("Last Name")))
			{
				ConnectionsPage.setEditSearchbyname(map.get("First Name").trim() + " " + map.get("Last Name").trim());
				sleepRandom(2, 4, 250, 750);
				String href = ConnectionsPage.getUserHREF();
				if (JavaHelpers.hasValue(href))
				{
					href = href.toLowerCase();
					if (href.contains(LINKEDIN_URL))
					{
						// href = href.substring(href.indexOf(LINKEDIN_URL))
						href = href.replaceAll(LINKEDIN_URL, "");
					}
					href = href.replaceAll("/", "");
					map.put(Data.FIELD_LINKEDIN_URL, href);
					sqlStringBuilder = Data.appendRecordURL(sqlStringBuilder, map);
				}
			}
		}
		Data.updateRecords(sqlStringBuilder);
		Data.getJdbc().executeUpdate(Data.getQueryUpdateNotFound(), false);
		Data.getJdbc().executeUpdate(Data.getQueryUpdateLinkedInURL(), false);
	}

	public void run(WebDriver webDriver) throws Throwable
	{
		LoginPage.login(LoginAlternatePage, CJSConstants.EMAIL_ADDRESS_MSN, EPasswords.LINKEDIN.getValue());
		getConnectionURLS(webDriver, true);
		getConnectionContactInfo(webDriver, true);
	}

	public static void sleepRandom(int secondsMin, int secondsMax, int millisecondsMin, int millisecondsMax)
	{
		Environment.sysOut(Constants.CLASS_METHOD_DEBUG + JavaHelpers.getCurrentClassMethodDebugName() + "]");
		String methodName = JavaHelpers.getCurrentMethodName();
		int seconds = JavaHelpers.generateRandomInteger(secondsMin, secondsMax);
		int milliseconds = JavaHelpers.generateRandomInteger(millisecondsMin, millisecondsMax);
		Environment.sysOut(
				methodName + "-randomNumberSeconds:[" + seconds + "], randomNumberMilliSeconds:[" + milliseconds + "]");
		JavaHelpers.sleep(seconds, milliseconds);
	}
}