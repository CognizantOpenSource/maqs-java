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
   * Url for the site.
   */
  private static String siteUrl = SeleniumConfig.getWebSiteBase();

  /**
   * Automation site url.
   */
  private static String siteAutomationUrl = siteUrl + "Automation/";

  /**
   * Error string templates for assertion failures.
   */
  private static String assertNotNullErrorTemplate = "The %s was null when it was expected to not be.";

  /**
   * Test get ui find with element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void testGetUIFindWithElement() {
    PageElementsPageModel pageModel = new PageElementsPageModel(this.getTestObject());
    pageModel.open(siteAutomationUrl);
    WebElement elementDriver = UIWaitFactory
        .getWaitDriver(pageModel.getWebDriver())
        .waitForClickableElement(pageModel.showDialog1ButtonLocator);

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
