/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.appium;

import com.cognizantsoftvision.maqs.base.BaseTestObject;
import com.cognizantsoftvision.maqs.utilities.logging.ILogger;
import io.appium.java_client.AppiumDriver;
import java.util.function.Supplier;
import org.openqa.selenium.WebElement;

/**
 * The Appium Test Object class.
 */
public class AppiumTestObject extends BaseTestObject implements IAppiumTestObject {

  /**
   * Instantiates a new Appium test object.
   *
   * @param appiumDriver           the appium driver
   * @param logger                 the logger
   * @param fullyQualifiedTestName the fully qualified test name
   */
  public AppiumTestObject(AppiumDriver<WebElement> appiumDriver, ILogger logger,
      String fullyQualifiedTestName) {
    this(() -> appiumDriver, logger, fullyQualifiedTestName);
  }

  /**
   * Instantiates a new Appium test object.
   *
   * @param appiumDriverSupplier   the appium driver supplier
   * @param logger                 the logger
   * @param fullyQualifiedTestName the fully qualified test name
   */
  public AppiumTestObject(Supplier<AppiumDriver<WebElement>> appiumDriverSupplier, ILogger logger,
      String fullyQualifiedTestName) {
    super(logger, fullyQualifiedTestName);
    this.getManagerStore().put(MobileDriverManager.class.getCanonicalName(),
        new MobileDriverManager(appiumDriverSupplier, this));
  }

  /**
   * {@inheritDoc}
   */
  public AppiumDriver<WebElement> getAppiumDriver() {
    return this.getAppiumManager().getMobileDriver();
  }

  /**
   * {@inheritDoc}
   */
  public MobileDriverManager getAppiumManager() {
    return (MobileDriverManager) this.getManagerStore()
        .get(MobileDriverManager.class.getCanonicalName());
  }

  /**
   * {@inheritDoc}
   */
  public void setAppiumDriver(AppiumDriver<WebElement> appiumDriver) {
    this.getManagerStore().put(MobileDriverManager.class.getCanonicalName(),
        new MobileDriverManager((() -> appiumDriver), this));
  }

  /**
   * {@inheritDoc}
   */
  public void setAppiumDriver(Supplier<AppiumDriver<WebElement>> appiumDriverSupplier) {
    this.getManagerStore().put(MobileDriverManager.class.getCanonicalName(),
        new MobileDriverManager(appiumDriverSupplier, this));
  }
}
