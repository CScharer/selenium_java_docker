package com.cjs.qa.core;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

public class ScenarioErrors
{
	public static Map<Integer, String> errors = new HashMap<>();

	public void assertErrors(String message)
	{
		Assert.assertTrue(message, errors.size() == 0);
	}

	public void add(String error)
	{
		errors.put((Environment.scenarioErrors.size() + 1), error);
	}

	public static void clear()
	{
		errors = new HashMap<>();
	}
}