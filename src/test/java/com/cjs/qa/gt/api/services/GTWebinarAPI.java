package com.cjs.qa.gt.api.services;

import com.cjs.qa.gt.api.namespace.webinar.AuthNamespace;
import com.cjs.qa.gt.api.namespace.webinar.WebinarsNamespace;
import com.cjs.qa.gt.api.namespace.webinar.RegistrantsNamespace;

public class GTWebinarAPI
{
	public GTWebinarAPI() throws Throwable
	{
		AuthNamespace = new AuthNamespace();
		WebinarsNamespace = new WebinarsNamespace();
		RegistrantsNamespace = new RegistrantsNamespace();
	}

	public AuthNamespace	AuthNamespace;
	public WebinarsNamespace	WebinarsNamespace;
	public RegistrantsNamespace	RegistrantsNamespace;
}