package com.cjs.qa.atlassian;

import java.util.Map;

import org.codehaus.plexus.util.FileUtils;

import com.cjs.qa.atlassian.bamboo.Bamboo;
import com.cjs.qa.atlassian.confluence.Confluence;
import com.cjs.qa.atlassian.crowd.Crowd;
import com.cjs.qa.atlassian.jira.Issue;
import com.cjs.qa.atlassian.jira.Jira;
import com.cjs.qa.atlassian.jira.Project;
import com.cjs.qa.atlassian.stash.Stash;
import com.cjs.qa.core.Environment;
import com.cjs.qa.utilities.CJSConstants;
import com.cjs.qa.utilities.CommandLine;
import com.cjs.qa.utilities.Constants;
import com.cjs.qa.utilities.DateHelpers;
import com.cjs.qa.utilities.FSO;
import com.cjs.qa.utilities.IExtension;
import com.cjs.qa.utilities.JavaHelpers;

public class Atlassian
{

	private static String	domain	= "-tst.cjsconsulting" + IExtension.COM;
	private Bamboo			bamboo;
	private Confluence		confluence;
	private Crowd			crowd;
	private Jira			jira;
	private Stash			stash;

	public Atlassian()
	{
		setBamboo(new Bamboo());
		setConfluence(new Confluence());
		setCrowd(new Crowd());
		final Project project = new Project("QSDT");
		final String dateTimeStamp = DateHelpers.getCurrentDateTime(DateHelpers.FORMAT_DATE_TIME_STAMP);
		final Issue issue = new Issue("TEST_" + dateTimeStamp,
				"Creating of an issue TEST_" + dateTimeStamp + " using the REST API", "Task");
		setJira(new Jira(issue, project));
		setStash(new Stash());
	}

	public static void main(String[] args)
	{
		for (int index = 1; index <= 1; index++)
		{
			final Atlassian atlassian = new Atlassian();
			if (index == 1)
			{
				Environment.sysOut("URLs:" + getString(atlassian));
			}
			final String json = atlassian.getJira().toString();
			// Environment.sysOut("json:" + json);
			// curl -D- -u fred:fred -X POST --data {see below} -H
			// "Content-Type: application/json"
			// http://localhost:8090/rest/api/2/issue/
			final String jsonPath = Constants.PATH_PROJECT + "JSON" + Constants.DELIMETER_PATH;
			FileUtils.mkdir(jsonPath);
			final String fileJSON = jsonPath + atlassian.getJira().getIssue().getSummary() + ".json";
			FSO.fileWrite(fileJSON, json, false);
			final StringBuilder stringBuilder = new StringBuilder("cmd /C " + Constants.QUOTE_DOUBLE + "");
			final String userID = CJSConstants.USERID_VIVIT;
			final String password = "********";
			stringBuilder.append("curl -D- -u ");
			stringBuilder.append(userID + ":" + password);
			stringBuilder.append(" -X POST -d ");
			stringBuilder.append("@" + Constants.QUOTE_DOUBLE + fileJSON + Constants.QUOTE_DOUBLE + " ");
			stringBuilder.append(
					"-H " + Constants.QUOTE_DOUBLE + "Content-Type: application/json" + Constants.QUOTE_DOUBLE + "");
			stringBuilder.append(" " + Constants.QUOTE_DOUBLE + atlassian.getJira().getUrl() + Constants.QUOTE_DOUBLE
					+ Constants.QUOTE_DOUBLE + "");
			Environment.sysOut("CURL Command:" + stringBuilder.toString());
			Map map = null;
			try
			{
				map = CommandLine.runProcess(stringBuilder.toString(), true);
			} catch (final Exception e)
			{
				e.printStackTrace();
			}
			if (map.get("status").equals("0"))
			{
				Environment.sysOut("PASS!!! map:" + map.toString());
			} else
			{
				Environment.sysOut("FAIL!!! map:" + map.toString());
			}
			JavaHelpers.sleep(0, 1);
		}
	}

	public Bamboo getBamboo()
	{
		return bamboo;
	}

	public Confluence getConfluence()
	{
		return confluence;
	}

	public Crowd getCrowd()
	{
		return crowd;
	}

	public Jira getJira()
	{
		return jira;
	}

	public Stash getStash()
	{
		return stash;
	}

	public static String getDomain()
	{
		return domain;
	}

	public void setBamboo(Bamboo bamboo)
	{
		this.bamboo = bamboo;
	}

	public void setConfluence(Confluence confluence)
	{
		this.confluence = confluence;
	}

	public void setCrowd(Crowd crowd)
	{
		this.crowd = crowd;
	}

	public void setJira(Jira jira)
	{
		this.jira = jira;
	}

	public void setStash(Stash stash)
	{
		this.stash = stash;
	}

	public void setDomain(String domain)
	{
		Atlassian.domain = domain;
	}

	public static String getString(Atlassian atlassian)
	{
		final StringBuilder stringBuilder = new StringBuilder();
		final Constants c = new Constants();
		c.setFormatPretty(true);
		int newLine = 0;
		if (c.isFormatPretty())
		{
			newLine = 1;
		}
		final int tab = 0;
		stringBuilder.append(c.nlTab(newLine, tab) + "Bamboo URL:[" + atlassian.getBamboo().getUrl() + "]");
		stringBuilder.append(c.nlTab(newLine, tab) + "Confluence URL:[" + atlassian.getConfluence().getUrl() + "]");
		stringBuilder.append(c.nlTab(newLine, tab) + "Crowd URL:[" + atlassian.getCrowd().getUrl() + "]");
		stringBuilder.append(c.nlTab(newLine, tab) + "Jira URL:[" + atlassian.getJira().getUrl() + "]");
		stringBuilder.append(c.nlTab(newLine, tab) + "Stash URL:[" + atlassian.getStash().getUrl() + "]");
		return stringBuilder.toString();
	}
}