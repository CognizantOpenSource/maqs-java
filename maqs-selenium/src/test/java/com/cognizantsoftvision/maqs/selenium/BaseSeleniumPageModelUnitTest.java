/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium;

import com.cognizantsoftvision.maqs.selenium.pageModel.AutomationPageModel;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import com.cognizantsoftvision.maqs.utilities.helper.exceptions.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * The Base Selenium Page Model unit test class.
 */
public class BaseSeleniumPageModelUnitTest extends BaseSeleniumTest {

  /**
   * Test getting the logger.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void testGetLogger() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    Assert.assertNotNull(automationPageModel.getLogger());


  }

  /**
   * Test getting the test object.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void testGetTestObject() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    Assert.assertNotNull(automationPageModel.getTestObject());
  }

  /**
   * Test getting the web driver.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void testGetWebDriver() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    Assert.assertNotNull(automationPageModel.getWebDriver());
  }

  /**
   * Test getting the performance timer collection.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void testGetPerfTimerCollection() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    Assert.assertNotNull(automationPageModel.getPerfTimerCollection());
  }

  /**
   * Test setting the web driver.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void testSetWebDriver() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    int hashCode = automationPageModel.getWebDriver().hashCode();
    WebDriver drive = this.getBrowser();

    automationPageModel.setWebDriver(drive);
    int hashCode1 = automationPageModel.getWebDriver().hashCode();
    Assert.assertNotEquals(hashCode, hashCode1);
    drive.quit();
  }

  /**
   * Test if the page is loaded.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void testIsPageLoaded() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    automationPageModel.open();
    Assert.assertTrue(automationPageModel.isPageLoaded());
  }

  /**
   * Test getting the same element twice and returns the same element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void testGetSameElementTwiceReturnsTheSameElement() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    automationPageModel.open();
    automationPageModel.waitForPageLoad();

    LazyWebElement initElem = automationPageModel.getLazyElement(automationPageModel.automationPageHeader);
    LazyWebElement cachedElem = automationPageModel.getLazyElement(automationPageModel.automationPageHeader);
    Assert.assertSame(initElem, cachedElem);
  }

  /**
   * Test getting the lazy element with a By.
   * @throws TimeoutException if a timeout exception is thrown
   * @throws InterruptedException if an interrupted exception is thrown
   */
  @Test(groups = TestCategories.SELENIUM)
  public void testGetLazyElementWithBy() throws TimeoutException, InterruptedException {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    automationPageModel.open();
    automationPageModel.waitForPageLoad();
    LazyWebElement lazyElement = automationPageModel.getLazyElement(automationPageModel.automationPageHeader);
    LazyWebElement storedElement = automationPageModel.getLazyElementStore()
        .get(automationPageModel.automationPageHeader.toString());

    Assert.assertNotNull(lazyElement);
    Assert.assertNotNull(storedElement);
    Assert.assertEquals(lazyElement, storedElement);
    Assert.assertEquals(lazyElement.getText(), "Elements to be automated");
  }

  /**
   * Test getting the lazy element with By selector and its Name.
   * @throws TimeoutException if a timeout exception occurs
   * @throws InterruptedException if an interrupted exception occurs
   */
  @Test(groups = TestCategories.SELENIUM)
  public void testGetLazyElementWithByAndName() throws TimeoutException, InterruptedException {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    automationPageModel.open();
    automationPageModel.waitForPageLoad();
    LazyWebElement lazyElement = automationPageModel.getLazyElement(
        automationPageModel.automationPageHeader, "Page Title");
    LazyWebElement storedElement = automationPageModel.getLazyElementStore()
        .get(automationPageModel.automationPageHeader + "Page Title");

    Assert.assertNotNull(lazyElement);
    Assert.assertNotNull(storedElement);
    Assert.assertEquals(lazyElement, storedElement);
    Assert.assertEquals(lazyElement.getText(), "Elements to be automated");
    Assert.assertEquals(lazyElement.getUserFriendlyName(), "Page Title");
  }

  /**
   * Test getting the lazy element with parent element and a By.
   * @throws TimeoutException if a timeout exception is thrown
   * @throws InterruptedException if an interrupted exception is thrown
   */
  @Test(groups = TestCategories.SELENIUM)
  public void testGetLazyElementWithParentElementAndBy() throws TimeoutException, InterruptedException {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    automationPageModel.open();
    automationPageModel.waitForPageLoad();
    LazyWebElement lazyElement = automationPageModel.getLazyElement(
        automationPageModel.body, automationPageModel.automationPageHeader);
    LazyWebElement storedElement = automationPageModel.getLazyElementStore()
        .get(automationPageModel.automationPageHeader.toString());

    Assert.assertNotNull(lazyElement);
    Assert.assertNotNull(storedElement);
    Assert.assertEquals(storedElement, lazyElement);
    Assert.assertEquals(lazyElement.getText(), "Elements to be automated");
  }

  /**
   * Test getting the lazy element with parent element By and its Name.
   * @throws TimeoutException if a timeout exception is thrown
   * @throws InterruptedException if an interrupted exception is thrown
   */
  @Test(groups = TestCategories.SELENIUM)
  public void testGetLazyElementWithParentElementByAndName() throws TimeoutException, InterruptedException {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    automationPageModel.open();
    automationPageModel.waitForPageLoad();
    LazyWebElement lazyElement = automationPageModel.getLazyElement(
        automationPageModel.body, automationPageModel.automationPageHeader, "Page Title");
    LazyWebElement storedElement = automationPageModel.getLazyElementStore()
        .get(automationPageModel.automationPageHeader + "Page Title");

    Assert.assertNotNull(lazyElement);
    Assert.assertNotNull(storedElement);
    Assert.assertEquals(storedElement, lazyElement);
    Assert.assertEquals(lazyElement.getText(), "Elements to be automated");
    Assert.assertEquals(lazyElement.getUserFriendlyName(), "Page Title");
  }
}