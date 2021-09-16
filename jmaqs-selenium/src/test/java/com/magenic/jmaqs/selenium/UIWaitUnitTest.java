/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.selenium.pageModels.AsyncPageModel;
import com.magenic.jmaqs.selenium.pageModels.AutomationPageModel;
import com.magenic.jmaqs.selenium.pageModels.IFramePageModel;
import com.magenic.jmaqs.utilities.helper.TestCategories;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * The UI wait class functionality unit test.
 */
public class UIWaitUnitTest extends BaseSeleniumTest {

  /**
   * the Automation page model.
   */
  private AutomationPageModel automationPageModel;

  /**
   * the Async page model.
   */
  private AsyncPageModel asyncPageModel;

  /**
   * the IFrame page model.
   */
  private IFramePageModel iframePageModel;

  /**
   * Sets up the page models for the unit tests.
   */
  @BeforeMethod
  public void setUp() {
    automationPageModel = new AutomationPageModel(this.getTestObject());
    asyncPageModel = new AsyncPageModel(this.getTestObject());
    iframePageModel = new IFramePageModel(this.getTestObject());
  }

  /**
   * Tests the functionality that waits until the IFrame to load.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilIFrameToLoad() {
    UIWait wait = navigateToTestSite(iframePageModel.testSiteIFrameUrl);
    Assert.assertTrue(wait.waitUntilIframeToLoad(iframePageModel.iframeLocator));
  }

  /**
   * Tests the functionality that waits until the IFrame to load.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilIFrameToLoadException() {
    UIWait wait = navigateToTestSite(iframePageModel.testSiteIFrameUrl);
    Assert.assertFalse(wait.waitUntilIframeToLoad(iframePageModel.notInPage));
  }

  /**
   * Tests the functionality that waits until the IFrame to load.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilIFrameToLoadWithTimeOut() {
    UIWait wait = navigateToTestSite(iframePageModel.testSiteIFrameUrl);
    Assert.assertTrue(wait.waitUntilIframeToLoad(iframePageModel.iframeLocator, 10000, 500));
  }

  /**
   * Tests the functionality that waits for the IFrame to load.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForIFrameToLoad() {
    UIWait wait = navigateToTestSite(iframePageModel.testSiteIFrameUrl);
    wait.waitForIframeToLoad(iframePageModel.iframeLocator);
  }

  /**
   * Tests the functionality that waits for the IFrame to load.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForIFrameToLoadWithTimeOut() {
    UIWait wait = navigateToTestSite(iframePageModel.testSiteIFrameUrl);
    wait.waitForIframeToLoad(iframePageModel.iframeLocator, 10000, 500);
  }

  /**
   * Tests the functionality that waits until the attribute texts equals.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAttributeTextEquals() {
    UIWait wait = navigateToTestSite(asyncPageModel.testSiteAsyncUrl);
    Assert.assertTrue(wait.waitUntilAttributeTextEquals(asyncPageModel.asyncLoadingLabel, "style", "display: none;"));
    Assert.assertTrue(wait.waitUntilAttributeTextEquals(asyncPageModel.asyncLoadingLabel, "style", "display: none;", 10000, 2000));
  }

  /**
   * Tests the functionality that waits for the attribute texts equals.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForAttributeTextEqualsFound() {
    UIWait wait = navigateToTestSite(asyncPageModel.testSiteAsyncUrl);
    WebElement element = wait.waitForAttributeTextEquals(asyncPageModel.asyncLoadingTextDiv, "style", "display: block;");
    Assert.assertNotNull(element);
    Assert.assertEquals(element.getText(), "Loaded");
  }

  /**
   * Tests the functionality that waits until an attribute contains text.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAttributeTextContainsFound() {
    UIWait wait = navigateToTestSite(asyncPageModel.testSiteAsyncUrl);
    Assert.assertTrue(wait.waitUntilAttributeTextContains(asyncPageModel.asyncDropdownCssSelector, "id", ""));
    Assert.assertTrue(wait.waitUntilAttributeTextContains(asyncPageModel.asyncDropdownCssSelector, "id", "", 10000, 1000));
  }

  /**
   * Verify that the WaitUntilAttributeTextContains method returns false for
   * objects that don't have this text inside attribute value within timeout.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAttributeTextContainsFalse() {
    UIWait wait = navigateToTestSite(asyncPageModel.testSiteAsyncUrl);
    Assert.assertFalse(wait.waitUntilAttributeTextContains(asyncPageModel.asyncDropdownCssSelector, "notTheRightId", "id"));
    Assert
        .assertFalse(wait.waitUntilAttributeTextContains(asyncPageModel.asyncDropdownCssSelector, "notTheRightId", "id", 10000, 1000));
  }

  /**
   * Verify that the WaitUntilAttributeTextContains method returns false for
   * objects that don't have this attribute value within timeout.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAttributeTextEqualsFalse() {
    UIWait wait = navigateToTestSite(asyncPageModel.testSiteAsyncUrl);
    Assert.assertFalse(wait.waitUntilAttributeTextEquals(asyncPageModel.asyncLoadingLabel, "display:", "style"));
    Assert.assertFalse(wait.waitUntilAttributeTextEquals(asyncPageModel.asyncLoadingLabel, "display:", "style", 10000, 1000));
  }

  /**
   * Tests the functionality that waits for a clickable element scrolls into view.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForClickableElementAndScrollIntoView() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteAutomationUrl);
    Assert.assertNotNull(wait.waitForClickableElementAndScrollIntoView(automationPageModel.automationShowDialog1));
    Assert.assertNotNull(wait.waitForClickableElementAndScrollIntoView(automationPageModel.automationShowDialog1, 10000, 1000));
  }

  /**
   * Tests the functionality that waits until a clickable element scrolls into
   * view.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilClickableElementAndScrollIntoView() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteAutomationUrl);
    Assert.assertTrue(wait.waitUntilClickableElementAndScrollIntoView(automationPageModel.automationShowDialog1));
    Assert.assertTrue(wait.waitUntilClickableElementAndScrollIntoView(automationPageModel.automationShowDialog1, 10000, 1000));
  }

  /**
   * Tests the functionality that waits for the present element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForPresentElement() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteAutomationUrl);
    Assert.assertNotNull(wait.waitForPresentElement(automationPageModel.flowerTableTitle));
    Assert.assertNotNull(wait.waitForPresentElement(automationPageModel.flowerTableTitle, 10000, 1000));
  }

  /**
   * Tests the functionality that waits for elements.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForElements() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteAutomationUrl);
    Assert.assertEquals(wait.waitForElements(automationPageModel.flowerTable).size(), 20);
    Assert.assertEquals(wait.waitForElements(automationPageModel.flowerTable, 10000, 1000).size(), 20);
  }

  /**
   * Tests the functionality that waits for an enabled element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForEnabledElement() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteAutomationUrl);
    Assert.assertNotNull(wait.waitForEnabledElement(automationPageModel.flowerTableTitle));
    Assert.assertNotNull(wait.waitForEnabledElement(automationPageModel.flowerTableTitle, 10000, 1000));
  }

  /**
   * Tests the functionality that waits until an enabled element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilEnabledElement() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteAutomationUrl);
    Assert.assertTrue(wait.waitUntilEnabledElement(automationPageModel.flowerTableTitle));
    Assert.assertTrue(wait.waitUntilEnabledElement(automationPageModel.flowerTableTitle, 10000, 1000));
  }

  /**
   * Tests the functionality that waits until a disabled element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilDisabledElement() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteAutomationUrl);
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
    UIWait wait = navigateToTestSite(automationPageModel.testSiteUrl);
    Assert.assertNotNull(wait.getNewWaitDriver());
    Assert.assertNotNull(wait.getNewWaitDriver(10000));
    Assert.assertNotNull(wait.getNewWaitDriver(this.getTestObject().getWebDriver()));
    Assert.assertNotNull(wait.getNewWaitDriver(this.getTestObject().getWebDriver(), 10000, 1000));
  }

  /**
   * Verify WaitForClickableElement wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForClickableElement() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteUrl);
    WebElement element = wait.waitForClickableElement(automationPageModel.homeButton);
    Assert.assertNotNull(element, "Null element was returned");
    element = wait.waitForClickableElement(automationPageModel.homeButton, 10000, 1000);
    Assert.assertNotNull(element, "Null element was returned");
  }

  /**
   * Verify WaitForVisibleElement wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForVisibleElement() {
    UIWait wait = navigateToTestSite(asyncPageModel.testSiteAsyncUrl);
    WebElement element = wait.waitForVisibleElement(asyncPageModel.asyncDropdownCssSelector);
    Assert.assertNotNull(element, "Null element was returned");
    element = wait.waitForVisibleElement(asyncPageModel.asyncDropdownCssSelector, 10000, 1000);
    Assert.assertNotNull(element, "Null element was returned");
  }

  /**
   * Verify WaitForExactText wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForExactText() {
    UIWait wait = navigateToTestSite(asyncPageModel.testSiteAsyncUrl);
    WebElement element = wait.waitForExactText(asyncPageModel.asyncOptionsLabel, "Options");
    Assert.assertNotNull(element, "Null element was returned");
  }

  /**
   * Verify WaitForContainsText wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForContainsText() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteAutomationUrl);
    WebElement element = wait.waitForContainsText(automationPageModel.automationNamesLabel, "Name");
    Assert.assertNotNull(element, "Null element was returned");
  }

  /**
   * Verify WaitForContainsText wait does not work.
   */
  @Test(groups = TestCategories.SELENIUM, expectedExceptions = NotFoundException.class)
  public void waitForContainsTextException() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteAutomationUrl);
    wait.waitForContainsText(automationPageModel.notInPage, "Name");
  }

  /**
   * Verify WaitForAbsentElement wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForAbsentElement() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteUrl);
    wait.waitForAbsentElement(automationPageModel.notInPage);
  }

  /**
   * Verify WaitForAbsentElement wait fails.
   */
  @Test(groups = TestCategories.SELENIUM, expectedExceptions = TimeoutException.class)
  public void waitForAbsentElementFail() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteUrl);
    wait.waitForAbsentElement(automationPageModel.homeButton);
  }

  /**
   * Verify WaitForPageLoad wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForPageLoad() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteUrl);
    wait.waitForPageLoad();
  }

  /**
   * Verify WaitUntilPageLoad wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilPageLoad() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteUrl);
    Assert.assertTrue(wait.waitUntilPageLoad(), "Page failed to load");
  }

  /**
   * Verify WaitUntilClickableElement wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilClickableElement() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteAutomationUrl);
    Assert.assertTrue(wait.waitUntilClickableElement(automationPageModel.automationShowDialog1), "Failed to find element");
    Assert.assertTrue(wait.waitUntilClickableElement(automationPageModel.automationShowDialog1, 10000, 1000), "Failed to find element");
  }

  /**
   * Verify WaitUntilVisibleElement wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilVisibleElement() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteAutomationUrl);
    Assert.assertTrue(wait.waitUntilVisibleElement(automationPageModel.automationShowDialog1), "Failed to find element");
    Assert.assertTrue(wait.waitUntilVisibleElement(automationPageModel.automationShowDialog1, 10000, 1000), "Failed to find element");
  }

  /**
   * Verify WaitUntilExactText wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilExactText() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteAutomationUrl);
    Assert.assertTrue(wait.waitUntilExactText(automationPageModel.automationShowDialog1, "Show dialog"), "Failed to find element");
    Assert.assertTrue(wait.waitUntilExactText(automationPageModel.automationShowDialog1, "Show dialog", 10000, 1000),
        "Failed to find element");
  }

  /**
   * Verify WaitUntilExactText text does not match.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilExactTextDoNotMatch() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteAutomationUrl);
    Assert.assertFalse(wait.waitUntilExactText(automationPageModel.automationShowDialog1, "Text"), "Failed to find element");
  }

  /**
   * Verify WaitUntilExactText text does not match.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilExactTextNullElement() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteAutomationUrl);
    Assert.assertFalse(wait.waitUntilExactText(null, "Text"), "Failed to find element");
  }

  /**
   * Verify WaitUntilContainsText wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilContainsText() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteAutomationUrl);
    Assert.assertTrue(wait.waitUntilContainsText(automationPageModel.automationShowDialog1, "dialog"), "Failed to find element");
    Assert.assertTrue(wait.waitUntilContainsText(automationPageModel.automationShowDialog1, "dialog", 10000, 1000),
        "Failed to find element");
  }

  /**
   * Verify WaitUntilContainsText does not match.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilContainsTextNotMatch() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteAutomationUrl);
    Assert.assertFalse(wait.waitUntilContainsText(automationPageModel.automationShowDialog1, "text"), "Failed to find element");
  }

  /**
   * Verify WaitUntilContainsText with null element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilContainsTextNullElement() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteAutomationUrl);
    Assert.assertFalse(wait.waitUntilContainsText(null, "text"), "Failed to find element");
  }

  /**
   * Test for the wait until absent.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAbsentElement() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteUrl);
    Assert.assertTrue(wait.waitUntilAbsentElement(automationPageModel.notInPage));
    Assert.assertTrue(wait.waitUntilAbsentElement(automationPageModel.notInPage, 10000, 1000));
  }

  /**
   * Verify that WaitForAttributeTextEquals throws an exception for instances
   * where the attribute is not found. An attribute check that should have failed
   * to find the given string equal to an elements attribute passed
   */
  @Test(groups = TestCategories.SELENIUM, expectedExceptions = NotFoundException.class)
  public void waitForAttributeEqualsNotFound() {
    UIWait wait = navigateToTestSite(automationPageModel.testSiteAutomationUrl);
    WebElement element = wait.waitForAttributeTextEquals(automationPageModel.foodTable, "Flower Table", "Summary");
    Assert.assertEquals(element.getAttribute("Text"), "Flower Table");
  }

  /**
   * Verify that WaitForAttributeTextEquals can find an attribute value after
   * waiting.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForAttributeEqualsFound() {
    UIWait wait = navigateToTestSite(asyncPageModel.testSiteAsyncUrl);
    Assert.assertNotNull(wait.waitForAttributeTextEquals(asyncPageModel.asyncLoadingTextDiv, "style", "display: block;"));
    Assert.assertNotNull(wait.waitForAttributeTextEquals(asyncPageModel.asyncLoadingTextDiv, "style", "display: block;", 10000, 1000));
  }

  /**
   * Verify that the WaitUntilAttributeTextEquals works with async objects.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAttributeEquals() {
    UIWait wait = navigateToTestSite(asyncPageModel.testSiteAsyncUrl);
    Assert.assertTrue(wait.waitUntilAttributeTextEquals(asyncPageModel.asyncLoadingLabel, "style", "display: none;"));
  }

  /**
   * Verify that the WaitUntilAttributeTextContains works with async objects.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitUntilAttributeContains() {
    UIWait wait = navigateToTestSite(asyncPageModel.testSiteAsyncUrl);
    Assert.assertTrue(wait.waitUntilAttributeTextContains(asyncPageModel.asyncLoadingLabel, "style", "none;"));
  }

  /**
   * Navigates to the specified test site.
   * Because many tests use different urls, this is not a setup method
   * @param url the url to be navigated to
   * @return a UIWait class for wait functionality
   */
  private UIWait navigateToTestSite(String url) {
    WebDriverFactory.setBrowserSize(this.getWebDriver(), "Maximize");
    this.getWebDriver().navigate().to(url);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();
    return UIWaitFactory.getWaitDriver(this.getWebDriver());
  }
}