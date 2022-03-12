/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.appium;

import com.cognizantsoftvision.maqs.base.interfaces.ITestObject;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

/**
 * The Appium Test Object interface class.
 */
public interface IAppiumTestObject extends ITestObject {

  /**
   * Gets the appium driver.
   *
   * @return the appium driver
   */
  AppiumDriver<WebElement> getAppiumDriver();

  /**
   * Gets appium manager.
   *
   * @return the appium manager
   */
  MobileDriverManager getAppiumManager();

  /**
   * Sets appium driver.
   *
   * @param appiumDriver the appium driver
   */
  void setAppiumDriver(AppiumDriver<WebElement> appiumDriver);

  /**
   * Sets appium driver.
   *
   * @param appiumDriverSupplier the appium driver supplier
   */
  void setAppiumDriver(Supplier<AppiumDriver<WebElement>> appiumDriverSupplier);
}
