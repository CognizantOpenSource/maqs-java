/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.utilities.logging.ConsoleLogger;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * The type Selenium test object test.
 */
public class SeleniumTestObjectTest {

  private String testName;
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
  @Test
  public void testSeleniumTestObjectCreationWithDriver() {

    WebDriver defaultBrowser = null;
    try {
      defaultBrowser = WebDriverFactory.getDefaultBrowser();
    } catch (Exception e) {
      e.printStackTrace();
      Assert.fail("Driver creation failed. ");
    }
    SeleniumTestObject testObject = new SeleniumTestObject(defaultBrowser, consoleLogger, testName);
    Assert.assertNotNull(testObject, "Checking that selenium test object via driver is not null");
  }

  /**
   * Test appium test object creation with supplier.
   */
  @Test
  public void testSeleniumTestObjectCreationWithSupplier() {

    try (SeleniumTestObject testObject = new SeleniumTestObject(() -> {
      try {
        return WebDriverFactory.getDefaultBrowser();
      } catch (Exception e) {
        Assert.fail("Driver creation failed.");
      }
      return null;
    }, consoleLogger, testName)) {
      Assert.assertNotNull(testObject, "Checking that appium test object via supplier is not null");
    }
  }

  @Test
  public void testGetWebDriver() {
    throw new UnsupportedOperationException("Test method not implemented yet.");
  }

  @Test
  public void testGetWebManager() {
    throw new UnsupportedOperationException("Test method not implemented yet.");
  }

  @Test
  public void testSetWebDriver() {
    throw new UnsupportedOperationException("Test method not implemented yet.");
  }

  @Test
  public void testSetWebDriverSupplier() {
    throw new UnsupportedOperationException("Test method not implemented yet.");
  }
}