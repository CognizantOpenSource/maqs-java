/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.appium;

import com.magenic.jmaqs.utilities.helper.TestCategories;
import com.magenic.jmaqs.utilities.logging.ConsoleLogger;
import io.appium.java_client.AppiumDriver;
import java.lang.reflect.Method;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * The type Appium test object test.
 */
public class AppiumTestObjectUnitTest {

  /**
   * The Test name.
   */
  private String testName;
  /**
   * The Console logger.
   */
  private ConsoleLogger consoleLogger;

  /**
   * Sets up.
   *
   * @param method the method
   */
  @BeforeMethod
  public void setUp(Method method) {
    testName = method.getName();
    consoleLogger = new ConsoleLogger();

  }

  /**
   * Test appium test object creation with driver.
   */
  @Test(groups = TestCategories.Appium)
  public void testAppiumTestObjectCreationWithDriver() {
    AppiumDriver<WebElement> defaultMobileDriver = AppiumDriverFactory.getDefaultMobileDriver();
    AppiumTestObject appiumTestObject = new AppiumTestObject(defaultMobileDriver, consoleLogger,
        testName);
    Assert
        .assertNotNull(appiumTestObject, "Checking that appium test object via driver is not null");
  }

  /**
   * Test appium test object creation with supplier.
   */
  @Test(groups = TestCategories.Appium)
  public void testAppiumTestObjectCreationWithSupplier() {
    AppiumTestObject appiumTestObject = new AppiumTestObject(
        AppiumDriverFactory::getDefaultMobileDriver, consoleLogger, testName);
    Assert.assertNotNull(appiumTestObject,
        "Checking that appium test object via supplier is not null");
  }

  /**
   * Test get appium driver.
   */
  @Test(groups = TestCategories.Appium)
  public void testGetAppiumDriver() {
    AppiumDriver<WebElement> defaultMobileDriver = AppiumDriverFactory.getDefaultMobileDriver();
    AppiumTestObject appiumTestObject = new AppiumTestObject(defaultMobileDriver, consoleLogger,
        testName);
    AppiumDriver appiumDriver = appiumTestObject.getAppiumDriver();
    Assert.assertNotNull(appiumDriver,
        "Checking that appium driver can be retrieved from test object");
  }

  /**
   * Test get appium manager.
   */
  @Test(groups = TestCategories.Appium)
  public void testGetAppiumManager() {
    AppiumDriver<WebElement> defaultMobileDriver = AppiumDriverFactory.getDefaultMobileDriver();
    AppiumTestObject appiumTestObject = new AppiumTestObject(defaultMobileDriver, consoleLogger,
        testName);
    MobileDriverManager appiumManager = appiumTestObject.getAppiumManager();
    Assert.assertNotNull(appiumManager,
        "Checking that appium manager can be retrieved from test object");
  }

  /**
   * Test set appium driver.
   */
  @Test(groups = TestCategories.Appium)
  public void testSetAppiumDriverWithDriver() {
    AppiumDriver<WebElement> defaultMobileDriver = AppiumDriverFactory.getDefaultMobileDriver();
    AppiumTestObject appiumTestObject = new AppiumTestObject(defaultMobileDriver, consoleLogger,
        testName);
    int hashCode = appiumTestObject.getAppiumDriver().hashCode();
    appiumTestObject.getAppiumDriver().quit();
    appiumTestObject.setAppiumDriver(AppiumDriverFactory.getDefaultMobileDriver());
    int hashCode1 = appiumTestObject.getAppiumDriver().hashCode();
    Assert.assertNotEquals(hashCode, hashCode1, String.format(
        "Checking that the appium driver is not the same as previous version.  First: %d, Second: %d",
        hashCode, hashCode1));
  }

  /**
   * Test test set appium driver.
   */
  @Test(groups = TestCategories.Appium)
  public void testSetAppiumDriverWithSupplier() {
    AppiumDriver<WebElement> defaultMobileDriver = AppiumDriverFactory.getDefaultMobileDriver();
    AppiumTestObject appiumTestObject = new AppiumTestObject(defaultMobileDriver, consoleLogger,
        testName);
    int hashCode = appiumTestObject.getAppiumDriver().hashCode();
    appiumTestObject.getAppiumDriver().quit();
    appiumTestObject.setAppiumDriver(AppiumDriverFactory::getDefaultMobileDriver);
    int hashCode1 = appiumTestObject.getAppiumDriver().hashCode();
    Assert.assertNotEquals(hashCode, hashCode1, String.format(
        "Checking that the appium driver is not the same as previous version.  First: %d, Second: %d",
        hashCode, hashCode1));

  }
}