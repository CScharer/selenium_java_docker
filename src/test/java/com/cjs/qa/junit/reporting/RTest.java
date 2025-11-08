package com.cjs.qa.junit.reporting;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Description;

public class RTest extends RStep
{
	private String		testName;
	private String		testStatus;
	private List<RStep>	RStep	= new ArrayList<>();

	public RTest(String stepName, String stepStatus)
	{
		super(stepName, stepStatus);
		setTestName(testName);
		setTestStatus(testStatus);
	}

	public void addStep(Description description, String status)
	{
		final String name = description.toString();
		getRStep().add(new RStep(name, status));
	}

	public RStep cRStep()
	{
		return getRStep().get(getRStep().size() - 1);
	}

	public List<RStep> getRStep()
	{
		return RStep;
	}

	public String getTestName()
	{
		return testName;
	}

	public String getTestStatus()
	{
		return testStatus;
	}

	public void setRStep(List<RStep> rStep)
	{
		RStep = rStep;
	}

	public void setTestName(String testName)
	{
		this.testName = testName;
	}

	public void setTestStatus(String testStatus)
	{
		this.testStatus = testStatus;
	}
}