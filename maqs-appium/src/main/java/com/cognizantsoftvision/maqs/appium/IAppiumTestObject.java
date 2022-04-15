/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.appium;

import com.cognizantsoftvision.maqs.base.ITestObject;
import io.appium.java_client.AppiumDriver;
import java.util.function.Supplier;
import org.openqa.selenium.WebElement;

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

  /**
   * Gets appium manager.
   *
   * @return the appium manager
   */
  MobileDriverManager getAppiumManager();

}
