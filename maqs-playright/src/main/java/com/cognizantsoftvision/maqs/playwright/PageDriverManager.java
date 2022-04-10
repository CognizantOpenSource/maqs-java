/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.cognizantsoftvision.maqs.base.DriverManager;
import com.cognizantsoftvision.maqs.base.ITestObject;
import com.cognizantsoftvision.maqs.utilities.logging.LoggingConfig;
import com.cognizantsoftvision.maqs.utilities.logging.LoggingEnabled;
import com.cognizantsoftvision.maqs.utilities.logging.MessageType;
import java.util.function.Supplier;

/**
 * The Playwright Driver Manager class.
 */
public class PageDriverManager extends DriverManager<PageDriver> {

  /**
   * Initializes a new instance of the PlaywrightDriverManager class.
   *
   * @param getDriver Function for getting a Playwright page
   * @param testObject The associated test object
   */
  public PageDriverManager(PageDriver getDriver, ITestObject testObject) {
    super(() -> new PageDriver(getDriver.getAsyncPage()), testObject);
  }

  /**
   * Instantiates a new Driver manager.
   *
   * @param getDriverFunction driver function supplier
   * @param baseTestObject    the base test object
   */
  public PageDriverManager(Supplier<PageDriver> getDriverFunction, ITestObject baseTestObject) {
    super(getDriverFunction, baseTestObject);
  }

  /// <summary>
  /// Override the page
  /// </summary>
  /// <param name="overrideDriver">The new page</param>
  public void overrideDriver(Supplier<PageDriver> overrideDriver) {
    this.setBaseDriver(overrideDriver.get());
  }

  /// <summary>
  /// Override the page
  /// </summary>
  /// <param name="overridePage">Function for getting a new page</param>
  public void overrideDriver(PageDriver overridePage) {
    this.overrideDriver(() -> overridePage);
  }



  /**
   * Get the page driver.
   * @return The page driver
   */
  public PageDriver getPageDriver() {
    PageDriver tempDriver;

    if (!this.isDriverInitialized() && LoggingConfig.getLoggingEnabledSetting() != LoggingEnabled.NO) {
      tempDriver = getPageDriver();
      this.setBaseDriver(tempDriver);

      // Log the setup
      this.loggingStartup(tempDriver);
    }

    tempDriver = getBase();

    if (tempDriver == null) {
      throw new NullPointerException("Base driver is null");
    }

    return tempDriver;
  }

  /**
   * Get the page driver.
   * @return the page object
   */
  public Object getPage() {
    return this.getPageDriver();
  }

  /**
   * Log that the page setup.
   * @param pageDriver the new page
   */
  private void loggingStartup(PageDriver pageDriver) {
    try {
      this.getLogger().logMessage(MessageType.INFORMATION, "Driver: " + pageDriver.getParentBrowser());
    } catch (Exception e) {
      this.getLogger().logMessage(MessageType.ERROR, "Failed to start driver because: " + e.getMessage());
      System.out.print("Failed to start driver because: " + e.getMessage());
    }
  }

  @Override
  public void close() {
    this.baseDriver.close();
  }
}
