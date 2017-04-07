/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium.baseSeleniumTest;

import com.magenic.jmaqs.baseTest.TestObject;
import com.magenic.jmaqs.utilities.logging.Logger;

import org.openqa.selenium.WebDriver;

/**
 * The SeleniumTestObject Class
 * 
 * @author ErikSo
 */
public class SeleniumTestObject extends TestObject {

  /**
   * The WebDriver Object
   */
  protected WebDriver webDriver;

  /**
   * The SeleniumWait Object
   */
  protected SeleniumWait seleniumWait;

  /**
   * Initializes a new instance of the SeleniunTestObject
   * 
   * @param driver
   *          The WebDriver Object
   * @param wait
   *          The SeleniumWait Object
   * @param logger
   *          The Logger Object
   */
  public SeleniumTestObject(WebDriver driver, SeleniumWait wait, Logger logger,
      String fullyQualifiedTestName) {
    super(fullyQualifiedTestName, logger);
    this.webDriver = driver;
    this.seleniumWait = wait;
  }

  /**
   * Get the SeleniumWait Object
   * 
   * @return A SeleniumWait Object
   */
  public SeleniumWait getSeleniumWait() {
    return this.seleniumWait;
  }

  /**
   * Set the SeleniumWait for the SeleniumTestObject
   * 
   * @param wait
   *          The SeleniumWait Object
   */
  public void setSeleniumWait(SeleniumWait wait) {
    this.seleniumWait = wait;
  }

  /**
   * Get the WebDriver Object
   * 
   * @return A WebDriver Object
   */
  public WebDriver getWebDriver() {
    return this.webDriver;
  }

  /**
   * Set the WebDriver for the SeleniumTestObject
   * 
   * @param driver
   *          The WebDriver Object
   */
  public void setWebDriver(WebDriver driver) {
    this.webDriver = driver;
  }
}
