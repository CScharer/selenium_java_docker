package com.cjs.qa.oracle.api.services;

import com.cjs.qa.core.api.WebService;

public class Oracle extends WebService
{
	public String				browser;
	public WebService			APIPage;
	public OracleDynamicVariables	OracleDynamicVariables;

	public Oracle()
	{
		// API = new API(Environment, driver);
		OracleDynamicVariables = new OracleDynamicVariables();
	}
}