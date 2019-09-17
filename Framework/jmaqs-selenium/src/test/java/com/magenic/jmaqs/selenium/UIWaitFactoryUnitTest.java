/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */
package com.magenic.jmaqs.selenium;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.magenic.jmaqs.selenium.factories.UIWait;
import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.selenium.unittestpagemodel.PageElementsPageModel;

public class UIWaitFactoryUnitTest extends BaseSeleniumTest {
	
	/** Url for the site. */
	private static String siteUrl = SeleniumConfig.getWebSiteBase();

	/** Automation site url. */
	private static String siteAutomationUrl = siteUrl + "Automation/";
	
	/** Error string templates for assertion failures */
	private static String assertNotNullErrorTemplate = "The %s was null when it was expected to not be.";
	private static String assertEqualErrorTemplate = "%s was not equal to %s when they were expected to be.";
	private static String assertNotEqualErrorTemplate = "%s was equal to %s when they were expected not to be.";
	
	@Test
	public void getWaitDriverTest() {
		UIWait waitDriver = UIWaitFactory.getWaitDriver(this.getWebDriver());
		
		Assert.assertNotNull(waitDriver, String.format(assertNotNullErrorTemplate, "waitDriver"));
	}
	
	@Test
	public void getWaitDriverWhenOneExists() {
		UIWait firstWaitDriver = UIWaitFactory.getWaitDriver(this.getWebDriver());
		UIWait secondWaitDriver = UIWaitFactory.getWaitDriver(this.getWebDriver());
		
		Assert.assertEquals(
				firstWaitDriver, 
				secondWaitDriver, 
				String.format(assertEqualErrorTemplate, "firstWaitDriver", "secondWaitDriver"));
	}
	
	@Test
	public void setWaitDriverWhenDriverKeyDoesNotExist() {
		UIWait waitDriver = new UIWait(this.getWebDriver());
		UIWaitFactory.setWaitDriver(this.getWebDriver(), waitDriver);
		
		UIWait existingDriver = UIWaitFactory.getWaitDriver(this.getWebDriver());
		
		Assert.assertEquals(
				waitDriver, 
				existingDriver, 
				String.format(assertEqualErrorTemplate, "waitDriver", "existingDriver"));
	}
	
	@Test
	public void setWaitDriverWhenDriverKeyDoesExist() {
		UIWait oldWaitDriver = UIWaitFactory.getWaitDriver(this.getWebDriver());
		UIWait newWaitDriver = new UIWait(this.getWebDriver());
		
		UIWaitFactory.setWaitDriver(this.getWebDriver(), newWaitDriver);
		UIWait overridenWaitDriver = UIWaitFactory.getWaitDriver(this.getWebDriver());
		
		Assert.assertEquals(newWaitDriver, overridenWaitDriver, String.format(assertEqualErrorTemplate, "newWaitDriver", "overridenWaitDriver"));
		Assert.assertNotEquals(oldWaitDriver, overridenWaitDriver, String.format(assertNotEqualErrorTemplate, "oldWaitDriver", "overridentWaitDriver"));
	}
	
	@Test
	public void getWaitDriverWithWebElement() {
		PageElementsPageModel pageModel = new PageElementsPageModel(this.getSeleniumTestObject());
		pageModel.open(siteAutomationUrl);
		WebElement elementDriver = UIWaitFactory.getWaitDriver(pageModel.getSeleniumTestObject().getWebDriver())
				.waitForClickableElement(pageModel.showDialog1ButtonLocator);
		
		UIWait waitDriver = UIWaitFactory.getWaitDriver(elementDriver);
		
		Assert.assertNotNull(waitDriver);
	}
	
	@Test
	public void removeWaitDriver() {
		UIWait waitDriverToBeRemoved = UIWaitFactory.getWaitDriver(this.getWebDriver());
		UIWaitFactory.removeWaitDriver(this.getWebDriver());
		
		UIWait newWaitDriver = UIWaitFactory.getWaitDriver(this.getWebDriver());
		
		Assert.assertNotEquals(
				waitDriverToBeRemoved, 
				newWaitDriver, 
				String.format(assertNotEqualErrorTemplate, "waitDriverToBeRemoved", "newWaitDriver"));
	}
}
