package com.cjs.qa.iadhs;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.cjs.qa.iadhs.pages.CasePaymentsPage;
import com.cjs.qa.iadhs.pages.SignInPage;

public class IaDhs
{
	public CasePaymentsPage	CasePaymentsPage;
	public SignInPage		SignInPage;

	public IaDhs(WebDriver webDriver)
	{
		CasePaymentsPage = new CasePaymentsPage(webDriver);
		SignInPage = new SignInPage(webDriver);
		webDriver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(120));
	}
}