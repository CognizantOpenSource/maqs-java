/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.base.BaseTestObject;
import com.magenic.jmaqs.utilities.logging.Logger;

import org.openqa.selenium.WebDriver;

/**
 * The SeleniumTestObject Class.
 */
public class SeleniumTestObject extends BaseTestObject {

  /**
   * The WebDriver Object.
   */
  protected WebDriver webDriver;

  /**
   * The SeleniumWait Object.
   */
  protected SeleniumWait seleniumWait;

  /**
   * Initializes a new instance of the SeleniumTestObject.
   * 
   * @param driver
   *          The WebDriver Object
   * @param wait
   *          The SeleniumWait Object
   * @param logger
   *          The Logger Object
   * @param fullyQualifiedTestName
   *          The fully qualified test name
   */
  public SeleniumTestObject(WebDriver driver, SeleniumWait wait, Logger logger, String fullyQualifiedTestName) {
    super(logger, fullyQualifiedTestName);
    this.webDriver = driver;
    this.seleniumWait = wait;
  }

  /**
   *
   * @param getDriver
   * @param logger
   * @param fullyQualifiedTestName+
   */
  // TODO: Review with Jason and wait for SeleniumDriverManager and softAssert completion
  // public SeleniumTestObject(Func<IWebDriver> getDriver, Logger logger, string fullyQualifiedTestName) : base(logger, fullyQualifiedTestName)
  public SeleniumTestObject(WebDriver getDriver, Logger logger, String fullyQualifiedTestName) {
    super(logger, fullyQualifiedTestName);
    this.managerStore.Add(typeof(SeleniumDriverManager).FullName, new SeleniumDriverManager(getDriver, this));
    this.softAssert = new SeleniumSoftAssert(this);
  }

  /**
   * Get the SeleniumWait Object.
   * 
   * @return A SeleniumWait Object
   */
  public SeleniumWait getSeleniumWait() {
    return this.seleniumWait;
  }

  /**
   * Set the SeleniumWait for the SeleniumTestObject.
   * 
   * @param wait
   *          The SeleniumWait Object
   */
  public void setSeleniumWait(SeleniumWait wait) {
    this.seleniumWait = wait;
  }

  /**
   * Get the WebDriver Object.
   * 
   * @return A WebDriver Object
   */
  public WebDriver getWebDriver() {
    return this.webDriver;
  }


  /**
   * Gets the Selenium driver manager
   */
  public SeleniumDriverManager getWebManager
  {
      return this.ManagerStore[typeof(SeleniumDriverManager).FullName] instanceof SeleniumDriverManager;
  }
  /**
   * Set the WebDriver for the SeleniumTestObject.
   * 
   * @param driver
   *          The WebDriver Object
   */
  public void setWebDriver(WebDriver driver) {
    this.webDriver = driver;
  }

  /**
   * Override the Selenium web driver
   * @param webDriver New web driver
   */
  public void OverrideWebDriver(WebDriver webDriver)
  {
    this.OverrideDriverManager(typeof(SeleniumDriverManager).FullName, new SeleniumDriverManager(() => webDriver, this));
  }

  /**
   * Override the function for creating a Selenium web driver
   * @param getDriver Function for creating a web driver
   */
  // public void OverrideWebDriver(Func<WebDriver> getDriver)
  public void OverrideWebDriver(WebDriver getDriver)
  {
    this.OverrideDriverManager(typeof(SeleniumDriverManager).FullName, new SeleniumDriverManager(getDriver, this));
  }

}
