/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.selenium.unittestpagemodel.PageElementsPageModel;
import com.magenic.jmaqs.utilities.helper.TestCategories;
import org.openqa.selenium.By;
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
   * Gets the disabled item.
   */
  private By disabledField = By.cssSelector("#disabledField INPUT");

  /**
   * the IFrame element with the source.
   */
  private By magenicIFrameLocator = By.cssSelector("#mageniciFrame");

  /**
   * the sleep duration in milliseconds.
   */
  private int sleep = 1000;

  /**
   * the Time out duration in milliseconds.
   */
  private int timeOut = 5000;

  /**
   * goes to the Magenic test page.
   */
  private void goToTestPage() {
    PageElementsPageModel pageModel = new PageElementsPageModel(this.getTestObject());
    pageModel.open(testSiteUrl);
  }

  /**
   * waits for the clickable element.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitForClickableElement() {
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.waitForClickableElement(automationShowDialog1, timeOut, sleep));
  }

  /**
   * Tests the functionality that waits until the IFrame to load.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilIFrameToLoad() {
    this.getWebDriver().navigate().to(testSiteIFrameUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilIframeToLoad(magenicIFrameLocator));
    Assert.assertTrue(wait.waitUntilIframeToLoad(magenicIFrameLocator, timeOut, sleep));
  }

  /**
   * Tests the functionality that waits for the IFrame to load.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitForIFrameToLoad() {
    this.getWebDriver().navigate().to(testSiteIFrameUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    wait.waitForIframeToLoad(magenicIFrameLocator);
    wait.waitForIframeToLoad(magenicIFrameLocator, timeOut, sleep);
  }

  /**
   * Tests the functionality that waits until the exact text.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilExactText() {
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilExactText(flowerTable, "Flower Table", timeOut, sleep));
    Assert.assertTrue(wait.waitUntilExactText(flowerTable, "Flower Table"));
  }

  /**
   * Tests the functionality that waits until the contains text.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilContainsText() {
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilContainsText(flowerTable, "Flower Table", timeOut, sleep));
    Assert.assertTrue(wait.waitUntilContainsText(flowerTable, "Flower Table"));
  }

  /**
   * Tests the functionality that waits until the attribute texts equals.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilAttributeTextEquals() {
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilAttributeTextEquals(flowerTable, "class", "Flower Table"));
    Assert.assertTrue(wait.waitUntilAttributeTextEquals(flowerTable, "class", "Flower Table", timeOut, sleep));
  }

  /**
   * Tests the functionality that waits for the attribute texts equals.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitForAttributeTextEquals() {
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.waitForAttributeTextEquals(flowerTableTitle, "class", "Flower Table"));
    Assert.assertNotNull(wait.waitForAttributeTextEquals(flowerTableTitle, "class", "Flower Table", timeOut, sleep));
  }

  /**
   * Tests the functionality that waits until an attribute contains text.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilAttributeTextContains() {
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilAttributeTextContains(flowerTableTitle, "class", "Flower Table"));
    Assert.assertTrue(wait.waitUntilAttributeTextContains(flowerTableTitle, "class", "Flower Table", timeOut, sleep));
  }

  /**
   * Tests the functionality that waits for a clickable element scrolls into view.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitForClickableElementAndScrollIntoView() {
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.waitForClickableElementAndScrollIntoView(automationShowDialog1));
    Assert.assertNotNull(wait.waitForClickableElementAndScrollIntoView(automationShowDialog1, timeOut, sleep));
  }

  /**
   * Tests the functionality that waits until a clickable element scrolls into view.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilClickableElementAndScrollIntoView() {
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilClickableElementAndScrollIntoView(automationShowDialog1));
    Assert.assertTrue(wait.waitUntilClickableElementAndScrollIntoView(automationShowDialog1, timeOut, sleep));
  }

  /**
   * Tests the functionality that waits until a clickable element.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilClickableElement() {
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilClickableElement(automationShowDialog1));
    Assert.assertTrue(wait.waitUntilClickableElement(automationShowDialog1, timeOut, sleep));
  }

  /**
   * Tests the functionality that waits for the present element.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitForPresentElement() {
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.waitForPresentElement(flowerTableTitle));
    Assert.assertNotNull(wait.waitForPresentElement(flowerTableTitle, timeOut, sleep));
  }

  /**
   * Tests the functionality that waits until a visible element.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilVisibleElement() {
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilVisibleElement(flowerTableTitle));
    Assert.assertTrue(wait.waitUntilVisibleElement(flowerTableTitle, timeOut, sleep));
  }

  /**
   * Tests the functionality that waits for elements.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitForElements() {
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertEquals(wait.waitForElements(flowerTable).size(), 5);
    Assert.assertEquals(wait.waitForElements(flowerTable, timeOut, sleep).size(), 5);
  }

  /**
   * Tests the functionality that waits for an enabled element.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitForEnabledElement() {
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.waitForEnabledElement(flowerTableTitle));
  }

  /**
   * Tests the functionality that waits until an enabled element.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilEnabledElement() {
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilEnabledElement(flowerTableTitle));
  }

  /**
   * Tests the functionality that waits until a disabled element.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilDisabledElement() {
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilDisabledElement(disabledField));
  }

  /**
   * Test the functionality that waits until the element is absent.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitUntilAbsentElement() {
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertTrue(wait.waitUntilAbsentElement(notInPage));
  }

  /**
   * Test the functionality that waits for the exact text.
   */
  @Test(groups = TestCategories.Selenium)
  public void waitForExactText() {
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    WebElement element = wait.waitForExactText(flowerTableTitle, "Flower Table");
    Assert.assertEquals(element.getText(), "FlowerTable");
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
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.getNewWaitDriver());
    Assert.assertNotNull(wait.getNewWaitDriver(this.getTestObject().getWebDriver()));
    Assert.assertNotNull(wait.getNewWaitDriver(timeOut));
  }
}
