/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium;

import com.cognizantsoftvision.maqs.selenium.unittestpagemodel.AutomationPageModel;
import com.cognizantsoftvision.maqs.selenium.factories.UIWaitFactory;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The type Ui wait factory unit test.
 */
public class UIWaitFactoryUnitTest extends BaseSeleniumTest {

  /**
   * The Automation Page Model.
   */
  AutomationPageModel automationPageModel;

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
   * The constant assertEqualErrorTemplate.
   */
  private static String assertEqualErrorTemplate = "%s was not equal to %s when they were expected to be.";
  /**
   * The constant assertNotEqualErrorTemplate.
   */
  private static String assertNotEqualErrorTemplate = "%s was equal to %s when they were expected not to be.";

  /**
   * Sets up the page models for the test.
   */
  public void setUp() {
    automationPageModel = new AutomationPageModel(this.getTestObject());
  }

  /**
   * Gets wait driver test.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void getWaitDriverTest() {
    setUp();
    UIWait waitDriver = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(waitDriver, String.format(assertNotNullErrorTemplate, "waitDriver"));
  }

  /**
   * Gets wait driver when one exists.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void getWaitDriverWhenOneExists() {
    setUp();
    UIWait firstWaitDriver = UIWaitFactory.getWaitDriver(this.getWebDriver());
    UIWait secondWaitDriver = UIWaitFactory.getWaitDriver(this.getWebDriver());

    Assert.assertEquals(firstWaitDriver.getWaitDriver(), secondWaitDriver.getWaitDriver(),
        String.format(assertEqualErrorTemplate, "firstWaitDriver", "secondWaitDriver"));
  }

  /**
   * Sets wait driver when driver key does not exist.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void setWaitDriverWhenDriverKeyDoesNotExist() {
    UIWait waitDriver = new UIWait(this.getWebDriver());
    UIWaitFactory.setWaitDriver(this.getWebDriver(), waitDriver.getWaitDriver());

    UIWait existingDriver = UIWaitFactory.getWaitDriver(this.getWebDriver());

    Assert.assertEquals(waitDriver.getWaitDriver(), existingDriver.getWaitDriver(),
        String.format(assertEqualErrorTemplate, "waitDriver", "existingDriver"));
  }

  /**
   * Sets wait driver when driver key does exist.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void setWaitDriverWhenDriverKeyDoesExist() {
    UIWait oldWaitDriver = UIWaitFactory.getWaitDriver(this.getWebDriver());
    UIWait newWaitDriver = new UIWait(this.getWebDriver());

    UIWaitFactory.setWaitDriver(this.getWebDriver(), newWaitDriver.getWaitDriver());
    UIWait overriddenWaitDriver = UIWaitFactory.getWaitDriver(this.getWebDriver());

    Assert.assertEquals(newWaitDriver.getWaitDriver(), overriddenWaitDriver.getWaitDriver(),
        String.format(assertEqualErrorTemplate, "newWaitDriver", "overriddenWaitDriver"));
    Assert.assertNotEquals(oldWaitDriver.getWaitDriver(), overriddenWaitDriver.getWaitDriver(),
        String.format(assertNotEqualErrorTemplate, "oldWaitDriver", "overriddentWaitDriver"));
  }

  /**
   * Gets wait driver with web element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void getWaitDriverWithWebElement() {
    setUp();
    automationPageModel.open(automationPageModel.testSiteAutomationUrl);
    WebElement elementDriver = UIWaitFactory.getWaitDriver(automationPageModel.getWebDriver())
        .waitForClickableElement(automationPageModel.automationShowDialog1);

    UIWait waitDriver = UIWaitFactory.getWaitDriver(elementDriver);
    Assert.assertNotNull(waitDriver);
  }

  /**
   * Remove wait driver.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void removeWaitDriver() {
    UIWait waitDriverToBeRemoved = UIWaitFactory.getWaitDriver(this.getWebDriver());
    UIWaitFactory.removeWaitDriver(this.getWebDriver());
    UIWait newWaitDriver = UIWaitFactory.getWaitDriver(this.getWebDriver());

    Assert.assertNotEquals(waitDriverToBeRemoved, newWaitDriver,
        String.format(assertNotEqualErrorTemplate, "waitDriverToBeRemoved", "newWaitDriver"));
  }
}
