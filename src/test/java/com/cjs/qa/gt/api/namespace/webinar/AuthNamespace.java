package com.cjs.qa.gt.api.namespace.webinar;

import java.util.Map;

import org.json.JSONObject;

import com.cjs.qa.core.Environment;
import com.cjs.qa.gt.api.services.GTWebinarService;
import com.cjs.qa.utilities.CommandLine;
import com.cjs.qa.utilities.Constants;
import com.cjs.qa.utilities.JSON;

public class AuthNamespace extends GTWebinarService
{
	public AuthNamespace() throws Throwable
	{
		setHeader(null);
	}

	private String header = null;

	public Map<String, String> authenticate(String credentials) throws Throwable
	{
		String url = API_GT_BASE + "/";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Constants.nlTab(0, 0) + "{");
		stringBuilder.append(Constants.nlTab(1, 0) + "}");
		return getAPIJSONResponse(credentials, "POST", stringBuilder.toString(), url);
	}

	public Map<String, String> oauth(String credentials) throws Throwable
	{
		String url = URL_GT + "oauth/access_token";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Constants.nlTab(0, 0) + "{");
		stringBuilder.append(Constants.nlTab(1, 1) + "\"grant_type\":\"" + "password" + "\",");
		stringBuilder.append(Constants.nlTab(1, 1) + "\"user_id\":\"" + USER_ID + "\",");
		stringBuilder.append(Constants.nlTab(1, 1) + "\"password\":\"" + PASSWORD + "\",");
		stringBuilder.append(Constants.nlTab(1, 1) + "\"client_id\":\"" + API_CONSUMER_KEY + "\"");
		stringBuilder.append(Constants.nlTab(1, 0) + "}");
		Map<String, String> mapResponse;// = getAPIJSONResponse(credentials,
										// "POST", stringBuilder.toString(),
										// url);
		StringBuilder curlStringBuilder = new StringBuilder();
		curlStringBuilder.append("curl ");
		curlStringBuilder.append("-X POST ");
		curlStringBuilder.append("-H \"Accept:application/json\" ");
		curlStringBuilder.append("-H \"Content-Type: application/x-www-form-urlencoded\" ");
		curlStringBuilder.append("\"https://api.getgo.com/oauth/access_token\" ");
		curlStringBuilder.append("-d \"grant_type=\"password\"");
		curlStringBuilder.append("&user_id=\"" + USER_ID + "\"");
		curlStringBuilder.append("&password=\"" + PASSWORD + "\"");
		curlStringBuilder.append("&client_id=\"" + API_CONSUMER_KEY + "\"");
		String command = "cmd /C " + curlStringBuilder.toString();
		Environment.sysOut("command:[" + command + "]");
		mapResponse = CommandLine.runProcess(command, true);
		String json = mapResponse.get("lines");
		json = JSON.formatPretty(json, 4);
		Environment.sysOut("json Response:[" + Constants.NEWLINE + json + "]");
		final JSONObject jsonObject = new JSONObject(json);
		final String accessToken = jsonObject.getString("access_token");
		final String refreshToken = jsonObject.getString("refresh_token");
		final String organizerKey = jsonObject.getString("organizer_key");
		final String accountKey = jsonObject.getString("account_key");
		final String accountType = jsonObject.getString("account_type");
		Environment.sysOut("accessToken:[" + accessToken + "], refreshToken:[" + refreshToken + "], organizerKey:["
				+ organizerKey + "], accountKey:[" + accountKey + "], accountType:[" + accountType + "], ");
		GTWebinarService.setAccessToken(accessToken);
		GTWebinarService.setAccountKey(accountKey);
		return mapResponse;
	}

	public String getHeader()
	{
		return this.header;
	}

	public void setHeader(String header)
	{
		this.header = header;
	}
}