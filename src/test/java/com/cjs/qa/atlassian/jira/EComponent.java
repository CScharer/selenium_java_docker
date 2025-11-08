package com.cjs.qa.atlassian.jira;

public enum EComponent
{
	QATOOLS("QA Team"), BATF("Berkley Automated Testing Framework (BATF)");
	private String value;

	private EComponent(String value)
	{
		this.value = value;
	}

	public String getValue()
	{
		return value;
	}
}