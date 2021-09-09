/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.selenium.pageModels.AutomationPageModel;
import com.magenic.jmaqs.utilities.helper.exceptions.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BaseSeleniumPageModelUnitTest extends BaseSeleniumTest {

  /**
   * the Automation page model.
   */
  private AutomationPageModel automationPageModel;

  @BeforeMethod
  public void setUp() {
    automationPageModel = new AutomationPageModel(this.getTestObject());
  }

  @Test
  public void testGetLogger() {
    Assert.assertNotNull(automationPageModel.getLogger());
  }

  @Test
  public void testGetTestObject() {
    Assert.assertNotNull(automationPageModel.getTestObject());
  }

  @Test
  public void testGetWebDriver() {
    Assert.assertNotNull(automationPageModel.getWebDriver());
  }

  @Test
  public void testGetPerfTimerCollection() {
    Assert.assertNotNull(automationPageModel.getPerfTimerCollection());
  }

  @Test
  public void testSetWebDriver() {
    int hashCode = automationPageModel.getWebDriver().hashCode();
    WebDriver drive = this.getBrowser();

    automationPageModel.setWebDriver(drive);
    int hashCode1 = automationPageModel.getWebDriver().hashCode();
    Assert.assertNotEquals(hashCode, hashCode1);
    drive.quit();
  }

  @Test
  public void testIsPageLoaded() {
    automationPageModel.open();
    Assert.assertTrue(automationPageModel.isPageLoaded());
  }

  @Test
  public void testGetElementCalledTwiceReturnsTheSameElement() {
    automationPageModel.open();
    automationPageModel.waitForPageLoad();

    LazyWebElement initElem = automationPageModel.getLazyElement(automationPageModel.employeePageTitle);
    LazyWebElement cachedElem = automationPageModel.getLazyElement(automationPageModel.employeePageTitle);

    Assert.assertSame(initElem, cachedElem);
  }

  @Test
  public void testGetLazyElementWithBy() throws TimeoutException, InterruptedException {
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
    automationPageModel.open();
    automationPageModel.waitForPageLoad();
    LazyWebElement lazyElement = automationPageModel.getLazyElement(automationPageModel.employeePageTitle,
        "Page Title");
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
    automationPageModel.open();
    automationPageModel.waitForPageLoad();
    LazyWebElement lazyElement = automationPageModel.getLazyElement(automationPageModel.body,
        automationPageModel.employeePageTitle, "Page Title");
    LazyWebElement storedElement = automationPageModel.getLazyElementStore()
        .get(automationPageModel.employeePageTitle + "Page Title");

    Assert.assertNotNull(lazyElement);
    Assert.assertNotNull(storedElement);
    Assert.assertEquals(storedElement, lazyElement);
    Assert.assertEquals(lazyElement.getText(), "Elements to be automated");
    Assert.assertEquals(lazyElement.getUserFriendlyName(), "Page Title");
  }
}