/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.selenium.factories.UIFindFactory;
import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.selenium.unittestpagemodel.PageElementsPageModel;
import com.magenic.jmaqs.utilities.helper.TestCategories;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class UIFindFactoryUnitTest extends BaseSeleniumTest {

	/** Url for the site. */
	private static String siteUrl = SeleniumConfig.getWebSiteBase();

	/** Automation site url. */
	private static String siteAutomationUrl = siteUrl + "Automation/";
	
	/** Error string templates for assertion failures */
	private static String assertNotNullErrorTemplate = "The %s was null when it was expected to not be.";

  @Test(groups = TestCategories.Selenium)
	public void testGetUIFindWithElement() {
		PageElementsPageModel pageModel = new PageElementsPageModel(this.getTestObject());
		pageModel.open(siteAutomationUrl);
		WebElement elementDriver = UIWaitFactory.getWaitDriver(pageModel.getSeleniumTestObject().getWebDriver())
				.waitForClickableElement(pageModel.showDialog1ButtonLocator);
		
		UIFind findWithElement = UIFindFactory.getFind(elementDriver);
		
		assertNotNull(findWithElement, String.format(assertNotNullErrorTemplate, "findWithElement"));
	}

  @Test(groups = TestCategories.Selenium)
	public void testGetUIFindWithDriver() {
		UIFind findWithWebDriver = UIFindFactory.getFind(this.getWebDriver());
		
		assertNotNull(findWithWebDriver, String.format(assertNotNullErrorTemplate, "findWithWebDriver"));
	}

	@Override
	protected void postSetupLogging() throws Exception {

	}
}
