/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.maqs.appium.baseAppiumTest;

import com.magenic.jmaqs.selenium.baseSeleniumTest.SeleniumWait;

import io.appium.java_client.AppiumDriver;

import java.util.concurrent.TimeUnit;

/**
 * Class AppiumWait ...
 *
 * @author jasonedstrom Created on 5/29/17
 */
public class AppiumWait extends SeleniumWait {

  /**
   * Constructor for SeleniumWait object.
   *
   * @param appiumDriver
   *          Appium Driver
   */
  public AppiumWait(AppiumDriver appiumDriver) {
    super(appiumDriver);
  }

  /**
   * Constructor for SeleniumWait object.
   *
   * @param appiumDriver
   *          Appium Driver
   * @param implicitWaitTimeout
   *          int value of seconds to wait before timing out
   */
  public AppiumWait(AppiumDriver appiumDriver, int implicitWaitTimeout) {
    super(appiumDriver, implicitWaitTimeout);
  }

  /**
   * Constructor for SeleniumWait object.
   *
   * @param appiumDriver
   *          Appium Driver
   * @param implicitWaitTimeout
   *          int value of seconds to wait before timing out
   * @param fluentRetryTime
   *          int value of seconds to wait before retry
   */
  public AppiumWait(AppiumDriver appiumDriver, int implicitWaitTimeout, int fluentRetryTime) {
    super(appiumDriver, implicitWaitTimeout, fluentRetryTime);
  }

  /**
   * Set the implicit wait time.
   *
   * @param wait
   *          int value of seconds to wait
   */
  @Override
  protected void setImplicitWait(int wait) {
    getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    getWebDriver().manage().timeouts().implicitlyWait(wait, TimeUnit.SECONDS);
  }
}
