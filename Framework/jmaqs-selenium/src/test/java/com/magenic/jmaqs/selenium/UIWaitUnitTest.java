/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.utilities.helper.TestCategories;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The UI wait class functionality unit test.
 */
public class UIWaitUnitTest extends BaseSeleniumTest {
  /**
   * Unit testing site URL - Login page.
   */
  private String testSiteUrl = SeleniumConfig.getWebSiteBase();

  /**
   * Unit testing site URL - IFrame page.
   */
  private String testSiteIFrameUrl = testSiteUrl + "Automation/iFramePage";

  /**
   * Unit testing site URL - Async page.
   */
  private String testSiteAsyncUrl = testSiteUrl + "Automation/AsyncPage";

  /**
   * Unit testing site URL - Automation page.
   */
  private String testSiteAutomationUrl = testSiteUrl + "Automation/";

  /**
   * Home button css selector.
   */
  private By homeButtonCssSelector = By.cssSelector("#homeButton > a");

  /**
   * Home button css selector.
   */
  private By dropdownToggleClassSelector = By.className("dropdown-toggle");

  /**
   * Dropdown selector.
   */
  private By asyncDropdownCssSelector = By.cssSelector("#Selector");

  /**
   * Dropdown label.
   */
  private By asyncOptionsLabel = By.cssSelector("#Label");

  /**
   * Dropdown label - hidden once dropdown loads.
   */
  private By asyncLoadingLabel = By.cssSelector("#LoadingLabel");

  /**
   * Asynchronous div that loads after a delay on Async Testing Page.
   */
  private By asyncLoadingTextDiv = By.cssSelector("#loading-div-text");

  /**
   * Names label.
   */
  private By automationNamesLabel = By.cssSelector("#Dropdown > p > strong > label");

  /**
   * Selector that is not in page.
   */
  private By notInPage = By.cssSelector("NOTINPAGE");

  /**
   * First dialog button.
   */
  private By automationShowDialog1 = By.cssSelector("#showDialog1");

  /**
   * Flower table title.
   */
  private By flowerTableTitle = By.cssSelector("#FlowerTable > caption > strong");

  /**
   * Flower table.
   */
  private By flowerTable = By.cssSelector("#FlowerTable TD");

  /**
   * Food table.
   */
  private By foodTable = By.cssSelector("#FoodTable");

  /**
   * Gets the disabled item.
   */
  private By disabledField = By.cssSelector("#disabledField > INPUT");

  /**
   * the IFrame element with the source.
   */
  private By magenicIFrameLocator = By.className("#mageniciFrame");

  /**
   * Tests the functionality that waits until the IFrame to load.
   */
  @Test(groups = TestCategories.Selenium)
  // TODO: iFrame on test page is out of date and will need to be updated.
  public void waitUntilIFrameToLoad() {
    this.getWebDriver().navigate().to(testSiteIFrameUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    this.getWebDriver().switchTo().frame(0);
    Assert.assertTrue(wait.waitUntilIframeToLoad(magenicIFrameLocator));
  }

  /**
   * Tests the functionality that waits for the IFrame to load.
   */
  @Test(groups = TestCategories.Selenium)
  // TODO: iFrame on test page is out of date and will need to be updated.
  public void waitForIFrameToLoad() {
    this.getWebDriver().navigate().to(testSiteIFrameUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    wait.waitForIframeToLoad(magenicIFrameLocator);
  }

  /**
   * Tests the functionality that waits until the attribute texts equals.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilAttributeTextEquals() {
    this.getWebDriver().navigate().to(testSiteAsyncUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilAttributeTextEquals(asyncLoadingLabel, "style", "display: none;"));
  }

  /**
   * Tests the functionality that waits for the attribute texts equals.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitForAttributeTextEqualsFound() {
    this.getWebDriver().navigate().to(testSiteAsyncUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    WebElement element = wait.waitForAttributeTextEquals(asyncLoadingTextDiv, "style", "display: block;");
    Assert.assertNotNull(element);
    Assert.assertEquals(element.getText(), "Loaded");
  }

  /**
   * Tests the functionality that waits until an attribute contains text.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilAttributeTextContainsFound() {
    this.getWebDriver().navigate().to(testSiteAsyncUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilAttributeTextContains(asyncDropdownCssSelector, "id", ""));
  }

  /**
   * Verify that the WaitUntilAttributeTextContains method returns false
   * for objects that don't have this text inside attribute value within timeout.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilAttributeTextContainsFalse() {
    this.getWebDriver().navigate().to(testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertFalse(wait.waitUntilAttributeTextContains(asyncDropdownCssSelector, "nottherightid", "id"));
  }

  /**
   * Verify that the WaitUntilAttributeTextContains method returns false
   * for objects that don't have this attribute value within timeout.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilAttributeTextEqualsFalse() {
    getWebDriver().navigate().to(testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertFalse(wait.waitUntilAttributeTextEquals(asyncLoadingLabel, "display:", "style"));
  }

  /**
   * Tests the functionality that waits for a clickable element scrolls into view.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitForClickableElementAndScrollIntoView() {
    this.getWebDriver().navigate().to(testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.waitForClickableElementAndScrollIntoView(automationShowDialog1));
  }

  /**
   * Tests the functionality that waits until a clickable element scrolls into view.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilClickableElementAndScrollIntoView() {
    this.getWebDriver().navigate().to(testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilClickableElementAndScrollIntoView(automationShowDialog1));
  }

  /**
   * Tests the functionality that waits for the present element.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitForPresentElement() {
    this.getWebDriver().navigate().to(testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.waitForPresentElement(flowerTableTitle));
  }

  /**
   * Tests the functionality that waits for elements.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitForElements() {
    this.getWebDriver().navigate().to(testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertEquals(wait.waitForElements(flowerTable).size(), 20);
  }

  /**
   * Tests the functionality that waits for an enabled element.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitForEnabledElement() {
    this.getWebDriver().navigate().to(testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.waitForEnabledElement(flowerTableTitle));
  }

  /**
   * Tests the functionality that waits until an enabled element.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilEnabledElement() {
    this.getWebDriver().navigate().to(testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilEnabledElement(flowerTableTitle));
  }

  /**
   * Tests the functionality that waits until a disabled element.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilDisabledElement() {
    this.getWebDriver().navigate().to(testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilDisabledElement(disabledField));
  }

  /**
   * Test the functionality that resets the wait driver.
   */
  @Test(groups = TestCategories.Selenium)
  public void resetWaitDriver() {
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.resetWaitDriver());
  }

  /**
   * Test the functionality that gets a new wait driver.
   */
  @Test(groups = TestCategories.Selenium)
  public void getNewWaitDriver() {
    this.getWebDriver().navigate().to(testSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.getNewWaitDriver());
    Assert.assertNotNull(wait.getNewWaitDriver(this.getTestObject().getWebDriver()));
  }

  /**
   * Verify WaitForClickableElement wait works.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitForClickableElement() {
    this.getWebDriver().navigate().to(testSiteUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    WebElement element = wait.waitForClickableElement(homeButtonCssSelector);
    Assert.assertNotNull(element, "Null element was returned");
  }

  /**
   * Verify WaitForVisibleElement wait works.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitForVisibleElement() {
    this.getWebDriver().navigate().to(testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    WebElement element = wait.waitForVisibleElement(asyncDropdownCssSelector);
    Assert.assertNotNull(element, "Null element was returned");
  }

  /**
   * Verify WaitForExactText wait works.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitForExactText() {
    this.getWebDriver().navigate().to(testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    WebElement element = wait.waitForExactText(asyncOptionsLabel, "Options");
    Assert.assertNotNull(element, "Null element was returned");
  }

  /**
   * Verify WaitForContainsText wait works.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitForContainsText() {
    this.getWebDriver().navigate().to(testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    WebElement element = wait.waitForContainsText(automationNamesLabel, "Name");
    Assert.assertNotNull(element, "Null element was returned");
  }

  /**
   * Verify WaitForAbsentElement wait works.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitForAbsentElement() {
    this.getWebDriver().navigate().to(testSiteUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    wait.waitForAbsentElement(notInPage);
  }

  /**
   *  Verify WaitForAbsentElement wait fails.
   */
  @Test(groups = TestCategories.Selenium, expectedExceptions = TimeoutException.class)
  public void waitForAbsentElementFail() {
    this.getWebDriver().navigate().to(testSiteUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    wait.waitForPageLoad();
    wait.waitForAbsentElement(homeButtonCssSelector);
  }

  /**
   * Verify WaitForPageLoad wait works.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitForPageLoad() {
    getWebDriver().navigate().to(testSiteUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    wait.waitForPageLoad();
  }

  /**
   * Verify WaitUntilPageLoad wait works.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilPageLoad() {
    getWebDriver().navigate().to(testSiteUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilPageLoad(), "Page failed to load");
  }

  /**
   * Verify WaitUntilClickableElement wait works.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilClickableElement() {
    this.getWebDriver().navigate().to(testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilClickableElement(automationShowDialog1), "Failed to find element");
  }

  /**
   * Verify WaitUntilVisibleElement wait works.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilVisibleElement() {
    this.getWebDriver().navigate().to(testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilVisibleElement(automationShowDialog1), "Failed to find element");
  }

  /**
   * Verify WaitUntilExactText wait works.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilExactText() {
    this.getWebDriver().navigate().to(testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilExactText(automationShowDialog1, "Show dialog"), "Failed to find element");
  }

  /**
   * Verify WaitUntilContainsText wait works.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilContainsText() {
    this.getWebDriver().navigate().to(testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilContainsText(automationShowDialog1, "dialog"),
        "Failed to find element");
  }

  /**
   * Test for the wait until absent.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilAbsentElement() {
    this.getWebDriver().navigate().to(testSiteUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilAbsentElement(notInPage));
  }

  /**
   * Verify that WaitForAttributeTextEquals throws an exception
   * for instances where the attribute is not found.
   * An attribute check that should have failed to
   * find the given string equal to an elements attribute passed
   */
  @Test(groups = TestCategories.Selenium, expectedExceptions = NotFoundException.class)
  public void waitForAttributeEqualsDoesNotFind() {
    this.getWebDriver().navigate().to(testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    wait.waitForAttributeTextEquals(foodTable, "Flower Table", "Summary");
  }

  /**
   * Verify that WaitForAttributeTextEquals can find an attribute value after waiting.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitForAttributeEqualsFound() {
    this.getWebDriver().navigate().to(testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.waitForAttributeTextEquals(asyncLoadingTextDiv, "style", "display: block;"));
  }

  /**
   * Verify that the WaitUntilAttributeTextEquals works with async objects.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilAttributeEquals() {
    this.getWebDriver().navigate().to(testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilAttributeTextEquals(asyncLoadingLabel, "style","display: none;"));
  }

  /**
   * Verify that the WaitUntilAttributeTextContains works with async objects.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilAttributeContains() {
    this.getWebDriver().navigate().to(testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilAttributeTextContains(asyncLoadingLabel, "style", "none;"));
  }
}