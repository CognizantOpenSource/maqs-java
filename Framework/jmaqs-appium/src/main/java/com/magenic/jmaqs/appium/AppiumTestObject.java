/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.appium;

import com.magenic.jmaqs.base.BaseTestObject;
import com.magenic.jmaqs.selenium.SeleniumWait;
import com.magenic.jmaqs.utilities.logging.Logger;
import io.appium.java_client.AppiumDriver;

/**
 * Class AppiumTestObject.
 */
public class AppiumTestObject extends BaseTestObject {

  /** The appium driver. */
  protected AppiumDriver appiumDriver;

  /** The appium wait. */
  protected AppiumWait appiumWait;

  /**
   * Instantiates a new appium test object.
   *
   * @param appiumDriver
   *          the appium driver
   * @param wait
   *          the wait // * @param fullyQualifiedTestName the fully qualified test name
   * @param logger
   *          the logger
   */
  public AppiumTestObject(AppiumDriver appiumDriver, AppiumWait wait, String fullyQualifiedTestName,
      Logger logger) {
    super(logger, fullyQualifiedTestName);
    this.appiumDriver = appiumDriver;
    this.appiumWait = wait;
  }

  /**
   * Gets the appium wait.
   *
   * @return the appium wait
   */
  public SeleniumWait getAppiumWait() {
    return this.appiumWait;
  }

  /**
   * Sets the appium wait.
   *
   * @param wait
   *          the new appium wait
   */
  public void setAppiumWait(AppiumWait wait) {
    this.appiumWait = wait;
  }

  /**
   * Gets the appium driver.
   *
   * @return the appium driver
   */
  public AppiumDriver getAppiumDriver() {

    return this.appiumDriver;
  }

  /**
   * Sets the appium driver.
   *
   * @param driver
   *          the new appium driver
   */
  public void setAppiumDriver(AppiumDriver driver) {
    this.appiumDriver = driver;
  }
}
