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
   * Initializes a new instance of the SeleniunTestObject.
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
   * Set the WebDriver for the SeleniumTestObject.
   * 
   * @param driver
   *          The WebDriver Object
   */
  public void setWebDriver(WebDriver driver) {
    this.webDriver = driver;
  }
}
