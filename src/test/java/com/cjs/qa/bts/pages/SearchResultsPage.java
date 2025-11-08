package com.cjs.qa.bts.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cjs.qa.selenium.Page;

public class SearchResultsPage extends Page
{
	public SearchResultsPage(WebDriver webDriver)
	{
		super(webDriver);
	}

	private final By	editTerm				= By.xpath("//label[.='Term']/../p");
	private final By	editStatus				= By.xpath("//label[.='Status']/../p");
	// private By buttonClient =
	// By.xpath("//a[@ng-click='populateClientActions(client)']//i[contains(@class,'fa-caret-down')]");
	private final By	buttonClient			= By.xpath("//a[@ng-click='populateClientActions(client)']");
	// private By buttonPolicy =
	// By.xpath("//a[@ng-click='populatePolicyActions(policy)']//i[contains(@class,'fa-caret-down')]");
	// private By buttonPolicy =
	// By.xpath("//a[@ng-click='populatePolicyActions(policy)']");
	private final By	buttonPolicy			= By
			.xpath("//a[@ng-click='populatePolicyActions(policy)']/div[contains(@class,'ng-binding')]");
	// private final By buttonRequestPrint =
	// By.xpath("html/body/div[4]/div[2]/div/div[1]/div[2]/div[1]/div[2]/ul/li/div/div[1]/ul/li[10]/a/div[2]")
	private final By	buttonInquireOnPrompt	= By.xpath(".//*[@id='btn-ok']");
	// private final By PolicyToPolicy = By.linkText("Policy To Policy");
	public final String	PAGE_TITLE				= "SearchResultsPage";
	private final By	releaseLock				= By
			.xpath("html/body/div[4]/div[2]/div/div[1]/div[2]/div[1]/div[2]/ul/li/div/div[3]/a[1]/i[2]");

	public void verifyPage()
	{
		verifyTitle(PAGE_TITLE);
		//// h2[@ng-show='loadingResults']
	}

	public String getEditTerm()
	{
		return getEdit(editTerm);
	}

	public String getEditStatus()
	{
		return getEdit(editStatus);
	}

	public void clickButtonClient()
	{
		clickObject(buttonClient);
	}

	public void clickButtonClient(String policy, String value)
	{
		//// a[@ng-click='populateClientActions(client)']/div[.='5082204-11']
		clickObject(By.xpath("//a[@ng-click='populateClientActions(client)']/div[.='" + policy + "']/../..//div[.='"
				+ value + "']"));
	}

	public void unlockPolicy()
	{
		clickObject(releaseLock);
	}

	public void clickButtonPolicy()
	{
		clickObject(buttonPolicy);
	}

	public void clickButtonPolicy(String policy, String value)
	{
		// This should be changed to look for the actual button first and if it
		// can not find it then click on the down arrow and try again.
		String xPath = "html/body//div[.='" + policy + "']/..//i[@class='fa fa-caret-down']";
		if (objectExists(By.xpath(xPath)))
		{
			clickObject(By.xpath(xPath));
		}
		xPath = null;
		switch (value)
		{
			case "Cancel":
			case "Cancel Pending Audit":
			case "Cancel Rewrite":
				xPath = "html/body//div[.='" + policy + "']/../..//div[.='Cancel']";
				break;
			case "Policy To Policy":
			case "Policy To Quote":
			case "Policy To Quote Renewal":
			case "Policy To Renewal":
				xPath = "html/body//div[.='" + policy + "']/../..//div[.='Copy Policy']";
				break;
		}
		sleep(1);
		if (xPath != null)
		{
			if (objectExists(By.xpath(xPath)))
			{
				// clickObject(By.xpath(xPath));
				hoverObject(By.xpath(xPath));
			}
		}
		xPath = "html/body//div[.='" + policy + "']/../..//div[.='" + value + "']";
		clickObject(By.xpath(xPath));
	}

	public void clickButtonInquireOnPrompt()
	{
		clickObject(buttonInquireOnPrompt);
	}
}