package com.cjs.qa.atlassian.confluence;

import com.cjs.qa.atlassian.Atlassian;

public class Confluence
{

	private String url = "confluence" + Atlassian.getDomain();

	public Confluence()
	{
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}
}