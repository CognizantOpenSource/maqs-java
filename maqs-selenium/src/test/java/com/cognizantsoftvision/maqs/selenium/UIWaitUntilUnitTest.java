/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium;

import com.cognizantsoftvision.maqs.selenium.factories.UIWaitFactory;
import com.cognizantsoftvision.maqs.selenium.unittestpagemodel.AsyncPageModel;
import com.cognizantsoftvision.maqs.selenium.unittestpagemodel.AutomationPageModel;
import com.cognizantsoftvision.maqs.selenium.unittestpagemodel.IFramePageModel;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

/**
 * The UI wait class functionality unit test.
 */
public class UIWaitUntilUnitTest extends BaseSeleniumTest {

  /**
   * The Automation Page Model.
   */
  AutomationPageModel automationPageModel;

  /**
   * The IFrame Page Model.
   */
  IFramePageModel iFramePageModel;

  /**
   * The Asynchronous Page Model.
   */
  AsyncPageModel asyncPageModel;

  /**
   * Sets up the page models for the test.
   */
  public void setUp() {
    automationPageModel = new AutomationPageModel(this.getTestObject());
    iFramePageModel = new IFramePageModel(this.getTestObject());
    asyncPageModel = new AsyncPageModel(this.getTestObject());
  }

  /**
   * Tests the functionality that waits until the IFrame to load.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilIFrameToLoad() {
    setUp();
    this.getWebDriver().navigate().to(iFramePageModel.testSiteIFrameUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    wait.waitUntilPageLoad();
    WebDriverFactory.setBrowserSize(this.getWebDriver(), "Maximize");
    Assert.assertTrue(wait.waitUntilIframeToLoad(iFramePageModel.iframeLocator));
  }

  /**
   * Tests the functionality that waits for the IFrame to load.
   */
  @Ignore
  @Test(groups = TestCategories.SELENIUM)
  public void waitForIFrameToLoad() {
    setUp();
    this.getWebDriver().navigate().to(iFramePageModel.testSiteIFrameUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    wait.waitForPageLoad();
    WebDriverFactory.setBrowserSize(this.getWebDriver(), "Maximize");
    wait.waitForIframeToLoad(iFramePageModel.iframeLocator);
  }

  /**
   * Tests the functionality that waits until the attribute texts equals.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAttributeTextEquals() {
    setUp();
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilAttributeTextEquals(asyncPageModel.asyncLoadingLabel, "style", "display: none;"));
    Assert.assertTrue(wait.waitUntilAttributeTextEquals(asyncPageModel.asyncLoadingLabel, "style", "display: none;", 10000, 2000));
  }

  /**
   * Tests the functionality that waits for the attribute texts equals.
   */
  @Ignore
  @Test(groups = TestCategories.SELENIUM)
  public void waitForAttributeTextEqualsFound() {
    setUp();
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    WebElement element = wait.waitForAttributeTextEquals(asyncPageModel.asyncLoadingTextDiv, "style", "display: block;");
    Assert.assertNotNull(element);
    Assert.assertEquals(element.getText(), "Loaded");
  }

  /**
   * Tests the functionality that waits until an attribute contains text.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAttributeTextContainsFound() {
    setUp();
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
    setUp();
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertFalse(wait.waitUntilAttributeTextContains(
        asyncPageModel.asyncDropdownCssSelector, "nottherightid", "id"));
    Assert.assertFalse(wait.waitUntilAttributeTextContains(
        asyncPageModel.asyncDropdownCssSelector, "nottherightid", "id", 10000, 1000));
  }

  /**
   * Verify that the WaitUntilAttributeTextContains method returns false for
   * objects that don't have this attribute value within timeout.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAttributeTextEqualsFalse() {
    setUp();
    getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertFalse(wait.waitUntilAttributeTextEquals(asyncPageModel.asyncLoadingLabel, "display:", "style"));
    Assert.assertFalse(wait.waitUntilAttributeTextEquals(asyncPageModel.asyncLoadingLabel, "display:", "style", 10000, 1000));
  }

  /**
   * Tests the functionality that waits for a clickable element scrolls into view.
   */
  @Ignore
  @Test(groups = TestCategories.SELENIUM)
  public void waitForClickableElementAndScrollIntoView() {
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.waitForClickableElementAndScrollIntoView(
        automationPageModel.automationShowDialog1));
    Assert.assertNotNull(wait.waitForClickableElementAndScrollIntoView(
        automationPageModel.automationShowDialog1, 10000, 1000));
  }

  /**
   * Tests the functionality that waits until a clickable element scrolls into
   * view.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilClickableElementAndScrollIntoView() {
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilClickableElementAndScrollIntoView(
        automationPageModel.automationShowDialog1));
    Assert.assertTrue(wait.waitUntilClickableElementAndScrollIntoView(
        automationPageModel.automationShowDialog1, 10000, 1000));
  }

  /**
   * Tests the functionality that waits for the present element.
   */
  @Ignore
  @Test(groups = TestCategories.SELENIUM)
  public void waitForPresentElement() {
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.waitForPresentElement(automationPageModel.flowerTableTitle));
    Assert.assertNotNull(wait.waitForPresentElement(automationPageModel.flowerTableTitle, 10000, 1000));
  }

  /**
   * Tests the functionality that waits for elements.
   */
  @Ignore
  @Test(groups = TestCategories.SELENIUM)
  public void waitForElements() {
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertEquals(wait.waitForElements(automationPageModel.flowerTable).size(), 20);
    Assert.assertEquals(wait.waitForElements(automationPageModel.flowerTable, 10000, 1000).size(), 20);
  }

  /**
   * Tests the functionality that waits for an enabled element.
   */
  @Ignore
  @Test(groups = TestCategories.SELENIUM)
  public void waitForEnabledElement() {
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.waitForEnabledElement(automationPageModel.flowerTableTitle));
    Assert.assertNotNull(wait.waitForEnabledElement(automationPageModel.flowerTableTitle, 10000, 1000));
  }

  /**
   * Tests the functionality that waits until an enabled element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilEnabledElement() {
    setUp();
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
    setUp();
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
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.getNewWaitDriver());
    Assert.assertNotNull(wait.getNewWaitDriver(10000));
    Assert.assertNotNull(wait.getNewWaitDriver(this.getTestObject().getWebDriver()));
    Assert.assertNotNull(wait.getNewWaitDriver(this.getTestObject().getWebDriver(), 10000, 1000));
  }

  /**
   * Verify WaitForClickableElement wait works.
   */
  @Ignore
  @Test(groups = TestCategories.SELENIUM)
  public void waitForClickableElement() {
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    WebElement element = wait.waitForClickableElement(automationPageModel.homeButton);
    Assert.assertNotNull(element, "Null element was returned");
    element = wait.waitForClickableElement(automationPageModel.homeButton, 10000, 1000);
    Assert.assertNotNull(element, "Null element was returned");
  }

  /**
   * Verify WaitForVisibleElement wait works.
   */
  @Ignore
  @Test(groups = TestCategories.SELENIUM)
  public void waitForVisibleElement() {
    setUp();
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    WebElement element = wait.waitForVisibleElement(asyncPageModel.asyncDropdownCssSelector);
    Assert.assertNotNull(element, "Null element was returned");
    element = wait.waitForVisibleElement(asyncPageModel.asyncDropdownCssSelector, 10000, 1000);
    Assert.assertNotNull(element, "Null element was returned");
  }

  /**
   * Verify WaitForExactText wait works.
   */
  @Ignore
  @Test(groups = TestCategories.SELENIUM)
  public void waitForExactText() {
    setUp();
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    WebElement element = wait.waitForExactText(asyncPageModel.asyncOptionsLabel, "Options");
    Assert.assertNotNull(element, "Null element was returned");
  }

  /**
   * Verify WaitForContainsText wait works.
   */
  @Ignore
  @Test(groups = TestCategories.SELENIUM)
  public void waitForContainsText() {
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    WebElement element = wait.waitForContainsText(automationPageModel.automationNamesLabel, "Name");
    Assert.assertNotNull(element, "Null element was returned");
  }

  /**
   * Verify WaitForAbsentElement wait works.
   */
  @Ignore
  @Test(groups = TestCategories.SELENIUM)
  public void waitForAbsentElement() {
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    wait.waitForAbsentElement(automationPageModel.notInPage);
  }

  /**
   * Verify WaitForAbsentElement wait fails.
   */
  @Ignore
  @Test(groups = TestCategories.SELENIUM, expectedExceptions = TimeoutException.class)
  public void waitForAbsentElementFail() {
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    wait.waitForPageLoad();
    wait.waitForAbsentElement(automationPageModel.homeButton);
  }

  /**
   * Verify WaitForPageLoad wait works.
   */
  @Ignore
  @Test(groups = TestCategories.SELENIUM)
  public void waitForPageLoad() {
    setUp();
    getWebDriver().navigate().to(automationPageModel.testSiteUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    wait.waitForPageLoad();
  }

  /**
   * Verify WaitUntilPageLoad wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilPageLoad() {
    setUp();
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
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilClickableElement(automationPageModel.automationShowDialog1), "Failed to find element");
    Assert.assertTrue(wait.waitUntilClickableElement(automationPageModel.automationShowDialog1, 10000, 1000), "Failed to find element");
  }

  /**
   * Verify WaitUntilVisibleElement wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilVisibleElement() {
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilVisibleElement(automationPageModel.automationShowDialog1), "Failed to find element");
    Assert.assertTrue(wait.waitUntilVisibleElement(automationPageModel.automationShowDialog1, 10000, 1000), "Failed to find element");
  }

  /**
   * Verify WaitUntilExactText wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilExactText() {
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilExactText(automationPageModel.automationShowDialog1, "Show dialog"), "Failed to find element");
    Assert.assertTrue(wait.waitUntilExactText(automationPageModel.automationShowDialog1, "Show dialog", 10000, 1000),
        "Failed to find element");
  }

  /**
   * Verify WaitUntilContainsText wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilContainsText() {
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(
        wait.waitUntilContainsText(automationPageModel.automationShowDialog1, "dialog"),
        "Failed to find element");
    Assert.assertTrue(
        wait.waitUntilContainsText(automationPageModel.automationShowDialog1, "dialog", 10000, 1000),
        "Failed to find element");
  }

  /**
   * Test for the wait until absent.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAbsentElement() {
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilAbsentElement(automationPageModel.notInPage));
    Assert.assertTrue(wait.waitUntilAbsentElement(automationPageModel.notInPage, 10000, 1000));
  }

  /**
   * Verify that WaitForAttributeTextEquals throws an exception for instances
   * where the attribute is not found. An attribute check that should have failed
   * to find the given string equal to an elements attribute passed
   */
  @Ignore
  @Test(groups = TestCategories.SELENIUM, expectedExceptions = NotFoundException.class)
  public void waitForAttributeEqualsDoesNotFind() {
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    WebElement element = wait.waitForAttributeTextEquals(automationPageModel.foodTable, "Flower Table", "Summary");
    Assert.assertEquals(element.getAttribute("Text"), "Flower Table");
  }

  /**
   * Verify that WaitForAttributeTextEquals can find an attribute value after
   * waiting.
   */
  @Ignore
  @Test(groups = TestCategories.SELENIUM)
  public void waitForAttributeEqualsFound() {
    setUp();
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.waitForAttributeTextEquals(asyncPageModel.asyncLoadingTextDiv, "style", "display: block;"));
    Assert.assertNotNull(wait.waitForAttributeTextEquals(asyncPageModel.asyncLoadingTextDiv, "style", "display: block;", 10000, 1000));
  }

  /**
   * Verify that the WaitUntilAttributeTextEquals works with async objects.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAttributeEquals() {
    setUp();
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilAttributeTextEquals(asyncPageModel.asyncLoadingLabel, "style", "display: none;"));
  }

  /**
   * Verify that the WaitUntilAttributeTextContains works with async objects.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAttributeContains() {
    setUp();
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilAttributeTextContains(asyncPageModel.asyncLoadingLabel, "style", "none;"));
  }
}