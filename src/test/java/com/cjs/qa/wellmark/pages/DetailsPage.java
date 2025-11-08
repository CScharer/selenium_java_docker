package com.cjs.qa.wellmark.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cjs.qa.selenium.Page;
import com.cjs.qa.utilities.Constants;

public class DetailsPage extends Page
{
	public DetailsPage(WebDriver webDriver)
	{
		super(webDriver);
	}

	// List<String> sectionList = new Arrays.asList("PatientInfo",
	// "FinancialSum","ClaimSum","YourResp");
	List<String> listSection = new ArrayList<>();

	public void getData()
	{
		final StringBuilder stringBuilder = new StringBuilder();
		listSection.add("PatientInfo");
		listSection.add("FinancialSum");
		listSection.add("ClaimSum");
		listSection.add("YourResp");
		for (final String section : listSection)
		{
			final By labels = By.xpath(".//*[@id='" + section + "']/..//tr/th");
			final List<WebElement> elements = webDriver.findElements(labels);
			for (int index = 0; index < elements.size(); index++)
			{
				final WebElement elementLabel = elements.get(index);
				stringBuilder.append(elementLabel.getText());
				stringBuilder.append(Constants.DELIMETER_LIST);
				final WebElement elementValue = elementLabel.findElement(By.xpath("./../td/span"));
				stringBuilder.append(elementValue.getText());
			}
		}
	}
}