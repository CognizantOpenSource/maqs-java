/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.appium;

import com.cognizantsoftvision.maqs.base.DriverManager;
import com.cognizantsoftvision.maqs.base.ITestObject;
import com.cognizantsoftvision.maqs.utilities.helper.StringProcessor;
import com.cognizantsoftvision.maqs.utilities.logging.MessageType;
import io.appium.java_client.AppiumDriver;
import java.util.function.Supplier;

/**
 * The Mobile Driver Manager class.
 */
public class MobileDriverManager extends DriverManager<AppiumDriver> {
  /**
   * Instantiates a new Mobile Driver Manager.
   *
   * @param getDriverFunction Function that specifies how to get the driver.
   * @param baseTestObject    The Base Test Object.
   */
  public MobileDriverManager(Supplier<AppiumDriver> getDriverFunction, ITestObject baseTestObject) {
    super(getDriverFunction, baseTestObject);
  }

  /**
   * Instantiates a new Appium Driver Manager.
   *
   * @param driver         Appium Driver
   * @param baseTestObject The Base Test Object.
   */
  public MobileDriverManager(AppiumDriver driver, ITestObject baseTestObject) {
    super(() -> driver, baseTestObject);
    this.baseDriver = driver;
  }

  /**
   * Get the Appium driver.
   *
   * @return The Appium Driver
   */
  public AppiumDriver getMobileDriver() {
    return getBase();
  }

  /**
   * Cleanup the Appium Driver.
   */
  public void close() {
    // If we never created the driver we don't have any cleanup to do
    if (!this.isDriverInitialized()) {
      return;
    }

    try {
      AppiumDriver driver = this.getMobileDriver();
      driver.quit();
    } catch (Exception e) {
      this.getLogger().logMessage(MessageType.ERROR,
          StringProcessor.safeFormatter("Failed to close mobile driver because: %s", e.getMessage()));
    }

    this.baseDriver = null;
  }
}
