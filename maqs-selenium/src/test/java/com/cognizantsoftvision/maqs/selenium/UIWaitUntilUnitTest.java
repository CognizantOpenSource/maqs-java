/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium;

import com.cognizantsoftvision.maqs.selenium.factories.UIWaitFactory;
import com.cognizantsoftvision.maqs.selenium.unittestpagemodel.AsyncPageModel;
import com.cognizantsoftvision.maqs.selenium.unittestpagemodel.AutomationPageModel;
import com.cognizantsoftvision.maqs.selenium.unittestpagemodel.IFramePageModel;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The UI Wait Until unit test class.
 */
public class UIWaitUntilUnitTest extends BaseSeleniumTest {

  /**
   * Tests the functionality that waits until the IFrame to load.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilIFrameToLoad() {
    IFramePageModel iFramePageModel = new IFramePageModel(this.getTestObject());
    this.getWebDriver().navigate().to(iFramePageModel.testSiteIFrameUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    wait.waitUntilPageLoad();
    WebDriverFactory.setBrowserSize(this.getWebDriver(), "Maximize");
    Assert.assertTrue(wait.waitUntilIframeToLoad(iFramePageModel.iframeLocator));
  }

  /**
   * Tests the functionality that waits until the attribute texts equals.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAttributeTextEquals() {
    AsyncPageModel asyncPageModel = new AsyncPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilAttributeTextEquals(asyncPageModel.asyncLoadingLabel, "style", "display: none;"));
    Assert.assertTrue(wait.waitUntilAttributeTextEquals(asyncPageModel.asyncLoadingLabel, "style", "display: none;", 10000, 2000));
  }

  /**
   * Tests the functionality that waits until an attribute contains text.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAttributeTextContainsFound() {
    AsyncPageModel asyncPageModel = new AsyncPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilAttributeTextContains(
        asyncPageModel.asyncDropdownCssSelector, "id", ""));
    Assert.assertTrue(wait.waitUntilAttributeTextContains(
        asyncPageModel.asyncDropdownCssSelector, "id", "", 10000, 1000));
  }

  /**
   * Verify that the WaitUntilAttributeTextContains method returns false for
   * objects that don't have this text inside attribute value within timeout.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAttributeTextContainsFalse() {
    AsyncPageModel asyncPageModel = new AsyncPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertFalse(wait.waitUntilAttributeTextContains(
        asyncPageModel.asyncDropdownCssSelector, "notTheRightEd", "id"));
    Assert.assertFalse(wait.waitUntilAttributeTextContains(
        asyncPageModel.asyncDropdownCssSelector, "notTheRightId", "id", 10000, 1000));
  }

  /**
   * Verify that the WaitUntilAttributeTextContains method returns false for
   * objects that don't have this attribute value within timeout.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAttributeTextEqualsFalse() {
    AsyncPageModel asyncPageModel = new AsyncPageModel(this.getTestObject());
    getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertFalse(wait.waitUntilAttributeTextEquals(asyncPageModel.asyncLoadingLabel, "display:", "style"));
    Assert.assertFalse(wait.waitUntilAttributeTextEquals(asyncPageModel.asyncLoadingLabel, "display:", "style", 10000, 1000));
  }

  /**
   * Tests the functionality that waits until a clickable element scrolls into
   * view.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilClickableElementAndScrollIntoView() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilClickableElementAndScrollIntoView(
        automationPageModel.automationShowDialog1));
    Assert.assertTrue(wait.waitUntilClickableElementAndScrollIntoView(
        automationPageModel.automationShowDialog1, 10000, 1000));
  }

  /**
   * Tests the functionality that waits until an enabled element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilEnabledElement() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilEnabledElement(automationPageModel.flowerTableTitle));
    Assert.assertTrue(wait.waitUntilEnabledElement(automationPageModel.flowerTableTitle, 10000, 1000));
  }

  /**
   * Tests the functionality that waits until a disabled element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilDisabledElement() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilDisabledElement(automationPageModel.disabledField));
    Assert.assertTrue(wait.waitUntilDisabledElement(automationPageModel.disabledField, 10000, 1000));
  }

  /**
   * Test the functionality that resets the wait driver.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void resetWaitDriver() {
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.resetWaitDriver());
  }

  /**
   * Test the functionality that gets a new wait driver.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void getNewWaitDriver() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(automationPageModel.testSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.getNewWaitDriver());
    Assert.assertNotNull(wait.getNewWaitDriver(10000));
    Assert.assertNotNull(wait.getNewWaitDriver(this.getTestObject().getWebDriver()));
    Assert.assertNotNull(wait.getNewWaitDriver(this.getTestObject().getWebDriver(), 10000, 1000));
  }

  /**
   * Verify WaitUntilPageLoad wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilPageLoad() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    getWebDriver().navigate().to(automationPageModel.testSiteUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilPageLoad(), "Page failed to load");
  }

  /**
   * Verify WaitUntilClickableElement wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilClickableElement() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilClickableElement(
        automationPageModel.automationShowDialog1), "Failed to find element");
    Assert.assertTrue(wait.waitUntilClickableElement(
        automationPageModel.automationShowDialog1, 10000, 1000), "Failed to find element");
  }

  /**
   * Verify WaitUntilVisibleElement wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilVisibleElement() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilVisibleElement(
        automationPageModel.automationShowDialog1), "Failed to find element");
    Assert.assertTrue(wait.waitUntilVisibleElement(
        automationPageModel.automationShowDialog1, 10000, 1000), "Failed to find element");
  }

  /**
   * Verify WaitUntilExactText wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilExactText() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilExactText(
        automationPageModel.automationShowDialog1, "Show dialog"), "Failed to find element");
    Assert.assertTrue(wait.waitUntilExactText(automationPageModel.automationShowDialog1,
        "Show dialog", 10000, 1000), "Failed to find element");
  }

  /**
   * Verify WaitUntilContainsText wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilContainsText() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilContainsText(
        automationPageModel.automationShowDialog1, "dialog"), "Failed to find element");
    Assert.assertTrue(wait.waitUntilContainsText(automationPageModel.automationShowDialog1,
            "dialog", 10000, 1000), "Failed to find element");
  }

  /**
   * Test for the wait until absent.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAbsentElement() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(automationPageModel.testSiteUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilAbsentElement(automationPageModel.notInPage));
    Assert.assertTrue(wait.waitUntilAbsentElement(
        automationPageModel.notInPage, 10000, 1000));
  }

  /**
   * Verify that the WaitUntilAttributeTextEquals works with async objects.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAttributeEquals() {
    AsyncPageModel asyncPageModel = new AsyncPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilAttributeTextEquals(
        asyncPageModel.asyncLoadingLabel, "style", "display: none;"));
  }

  /**
   * Verify that the WaitUntilAttributeTextContains works with async objects.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAttributeContains() {
    AsyncPageModel asyncPageModel = new AsyncPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilAttributeTextContains(
        asyncPageModel.asyncLoadingLabel, "style", "none;"));
  }
}