/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium;

import com.cognizantsoftvision.maqs.selenium.unittestpagemodel.AutomationPageModel;
import com.cognizantsoftvision.maqs.selenium.unittestpagemodel.PageElementsPageModel;
import com.cognizantsoftvision.maqs.utilities.helper.exceptions.TimeoutException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BaseSeleniumPageModelUnitTest extends BaseSeleniumTest {

  /**
   * The Automation Page Model.
   */
  AutomationPageModel automationPageModel;

  /**
   * Sets up the page models for the test.
   */
  private void setUp() {
    automationPageModel = new AutomationPageModel(this.getTestObject());
  }

  @Test
  public void testGetLogger() {
    setUp();
    Assert.assertNotNull(automationPageModel.getLogger());
  }

  @Test
  public void testGetTestObject() {
    setUp();
    Assert.assertNotNull(automationPageModel.getTestObject());
  }

  @Test
  public void testGetWebDriver() {
    setUp();
    Assert.assertNotNull(automationPageModel.getWebDriver());
  }

  @Test
  public void testGetPerfTimerCollection() {
    setUp();
    Assert.assertNotNull(automationPageModel.getPerfTimerCollection());
  }

  @Test
  public void testSetWebDriver() throws Exception {
    setUp();
    int hashCode = automationPageModel.getWebDriver().hashCode();
    WebDriver drive = this.getBrowser();

    automationPageModel.setWebDriver(drive);
    int hashCode1 = automationPageModel.getWebDriver().hashCode();
    Assert.assertNotEquals(hashCode, hashCode1);
    drive.quit();
  }

  @Test
  public void testIsPageLoaded() {
    setUp();
    automationPageModel.open();
    Assert.assertTrue(automationPageModel.isPageLoaded());
  }

  @Test
  public void testGetElementCalledTwiceReturnsTheSameElement() {
    setUp();
    automationPageModel.open();
    automationPageModel.waitForPageLoad();

    LazyWebElement initElem = automationPageModel.getLazyElement(automationPageModel.employeePageTitle);
    LazyWebElement cachedElem = automationPageModel.getLazyElement(automationPageModel.employeePageTitle);
    Assert.assertSame(initElem, cachedElem);
  }

  @Test
  public void testGetLazyElementWithBy() throws TimeoutException, InterruptedException {
    setUp();
    automationPageModel.open();
    automationPageModel.waitForPageLoad();
    LazyWebElement lazyElement = automationPageModel.getLazyElement(automationPageModel.employeePageTitle);
    LazyWebElement storedElement = automationPageModel.getLazyElementStore()
        .get(automationPageModel.employeePageTitle.toString());

    Assert.assertNotNull(lazyElement);
    Assert.assertNotNull(storedElement);
    Assert.assertEquals(lazyElement, storedElement);
    Assert.assertEquals(lazyElement.getText(), "Elements to be automated");
  }

  @Test
  public void testGetLazyElementWithByAndName() throws TimeoutException, InterruptedException {
    setUp();
    automationPageModel.open();
    automationPageModel.waitForPageLoad();
    LazyWebElement lazyElement = automationPageModel.getLazyElement(
        automationPageModel.employeePageTitle, "Page Title");
    LazyWebElement storedElement = automationPageModel.getLazyElementStore()
        .get(automationPageModel.employeePageTitle + "Page Title");

    Assert.assertNotNull(lazyElement);
    Assert.assertNotNull(storedElement);
    Assert.assertEquals(lazyElement, storedElement);
    Assert.assertEquals(lazyElement.getText(), "Elements to be automated");
    Assert.assertEquals(lazyElement.getUserFriendlyName(), "Page Title");
  }

  @Test
  public void testGetLazyElementWithParentElementAndBy() throws TimeoutException, InterruptedException {
    setUp();
    automationPageModel.open();
    automationPageModel.waitForPageLoad();
    LazyWebElement lazyElement = automationPageModel.getLazyElement(automationPageModel.body,
        automationPageModel.employeePageTitle);
    LazyWebElement storedElement = automationPageModel.getLazyElementStore()
        .get(automationPageModel.employeePageTitle.toString());

    Assert.assertNotNull(lazyElement);
    Assert.assertNotNull(storedElement);
    Assert.assertEquals(storedElement, lazyElement);
    Assert.assertEquals(lazyElement.getText(), "Elements to be automated");
  }

  @Test
  public void testGetLazyElementWithParentElementByAndName() throws TimeoutException, InterruptedException {
    setUp();
    automationPageModel.open();
    automationPageModel.waitForPageLoad();
    LazyWebElement lazyElement = automationPageModel.getLazyElement(
        automationPageModel.body, automationPageModel.employeePageTitle, "Page Title");
    LazyWebElement storedElement = automationPageModel.getLazyElementStore()
        .get(automationPageModel.employeePageTitle + "Page Title");

    Assert.assertNotNull(lazyElement);
    Assert.assertNotNull(storedElement);
    Assert.assertEquals(storedElement, lazyElement);
    Assert.assertEquals(lazyElement.getText(), "Elements to be automated");
    Assert.assertEquals(lazyElement.getUserFriendlyName(), "Page Title");
  }
}