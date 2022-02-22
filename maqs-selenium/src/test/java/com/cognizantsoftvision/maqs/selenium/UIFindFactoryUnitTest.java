/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium;

import com.cognizantsoftvision.maqs.selenium.unittestpagemodel.PageElementsPageModel;
import com.cognizantsoftvision.maqs.selenium.factories.UIFindFactory;
import com.cognizantsoftvision.maqs.selenium.factories.UIWaitFactory;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The type Ui find factory unit test.
 */
public class UIFindFactoryUnitTest extends BaseSeleniumTest {

  /**
   * Error string templates for assertion failures.
   */
  private static final String assertNotNullErrorTemplate = "The %s was null when it was expected to not be.";

  /**
   * Test get ui find with element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void testGetUIFindWithElement() {
    AutomationPageModel automationUrl = new AutomationPageModel(this.getTestObject());
    automationUrl.open(automationUrl.testSiteAutomationUrl);
    WebElement elementDriver = UIWaitFactory
        .getWaitDriver(automationUrl.getWebDriver())
        .waitForClickableElement(automationUrl.automationShowDialog1);

    UIFind findWithElement = UIFindFactory.getFind(elementDriver);

    Assert.assertNotNull(findWithElement,
        String.format(assertNotNullErrorTemplate, "findWithElement"));
  }

  /**
   * Test get ui find with driver.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void testGetUIFindWithDriver() {
    UIFind findWithWebDriver = UIFindFactory.getFind(this.getWebDriver());

    Assert.assertNotNull(findWithWebDriver,
        String.format(assertNotNullErrorTemplate, "findWithWebDriver"));
  }
}