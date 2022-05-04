/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.cognizantsoftvision.maqs.base.DriverManager;
import com.cognizantsoftvision.maqs.base.ITestObject;
import com.cognizantsoftvision.maqs.utilities.helper.StringProcessor;
import com.cognizantsoftvision.maqs.utilities.logging.MessageType;
import java.util.function.Supplier;

/**
 * The Playwright Driver Manager class.
 */
public class PlaywrightDriverManager extends DriverManager<PageDriver> {

  private PageDriver pageDriver;

  /**
   * Instantiates a new Driver manager.
   *
   * @param getDriverFunction driver function supplier
   * @param baseTestObject    the base test object
   */
  public PlaywrightDriverManager(Supplier<PageDriver> getDriverFunction, ITestObject baseTestObject) {
    super(getDriverFunction, baseTestObject);
  }

  /**
   * Override the page.
   * @param overrideDriver The new page
   */
  public void overrideDriver(Supplier<PageDriver> overrideDriver) {
    this.setBaseDriver(overrideDriver.get());
  }

  /**
   * Override the page.
   * @param overridePage Function for getting a new page
   */
  public void overrideDriver(PageDriver overridePage) {
    this.overrideDriver(() -> overridePage);
  }

  /**
   * Get the page driver.
   * @return The page driver
   */
  public PageDriver getPageDriver() {
    if (this.pageDriver == null) {
      this.pageDriver = new PageDriver(getBase().getAsyncPage());

      // Log the setup
      this.loggingStartup(this.baseDriver);
    }

    return this.pageDriver;
  }

  /**
   * Get the page driver.
   * @return the page object
   */
  public Object getPage() {
    return this.getPageDriver();
  }

  /**
   * Closes the page driver manager.
   */
  @Override
  public void close() {
    getLogger().logMessage(MessageType.VERBOSE, "Start dispose driver");

    // If we never created the driver we don't have any cleanup to do
    if (!this.isDriverInitialized()) {
      return;
    }

    try {
      PageDriver driver = this.getPageDriver();
      driver.close();
    } catch (Exception e) {
      getLogger().logMessage(MessageType.ERROR,
          StringProcessor.safeFormatter("Failed to close web driver because: %s", e.getMessage()));
    }
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
    }
  }
}
