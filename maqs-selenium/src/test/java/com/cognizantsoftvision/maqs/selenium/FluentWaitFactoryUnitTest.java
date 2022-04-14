/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium;

import com.cognizantsoftvision.maqs.selenium.pageModel.AutomationPageModel;
import com.cognizantsoftvision.maqs.selenium.factories.FluentWaitFactory;
import com.cognizantsoftvision.maqs.selenium.factories.UIWaitFactory;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Fluent Wait Factory unit test class.
 */
public class FluentWaitFactoryUnitTest extends BaseSeleniumTest {

  /**
   * Test get fluent wait object.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void testGetFluentWaitObject() {
    int timeout = 1000;
    int polling = 500;

    AutomationPageModel pageModel = new AutomationPageModel(this.getTestObject());
    pageModel.open(pageModel.testSiteAutomationUrl);
    WebElement elementDriver = UIWaitFactory.getWaitDriver(pageModel.getWebDriver())
        .waitForClickableElement(pageModel.automationShowDialog1);

    FluentWait<WebElement> fluentWait = FluentWaitFactory
        .getNewElementFluentWait(elementDriver, timeout, polling);

    // Error string templates for assertion failures.
    String assertNotNullErrorTemplate = "The %s was null when it was expected to not be.";
    Assert.assertNotNull(fluentWait, String.format(assertNotNullErrorTemplate, "fluentWait"));
  }
}
