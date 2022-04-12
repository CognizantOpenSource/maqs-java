/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.cognizantsoftvision.maqs.utilities.logging.ILogger;
import com.cognizantsoftvision.maqs.utilities.performance.IPerfTimerCollection;

/**
 * The Base Playwright page model class.
 */
public abstract class BasePlaywrightPageModel {

  /**
   * Initializes a new instance of the BasePlaywrightPageModel class.
   * @param testObject The Playwright test object
   */
  protected BasePlaywrightPageModel(IPlaywrightTestObject testObject) {
    this.setTestObject(testObject);
    this.setPageDriver(testObject.getPageDriver());
  }

  /**
   * Initializes a new instance of the BasePlaywrightPageModel class.
   * @param testObject The Playwright test object
   * @param customDriver Driver to use instead of the default test object related driver
   */
  protected BasePlaywrightPageModel(IPlaywrightTestObject testObject, PageDriver customDriver) {
    this.setTestObject(testObject);
    this.setPageDriver(customDriver);
  }

  /**
   * the com.cognizantsoftvision.maqs.playwright.PageDriver from the test object.
   */
  protected PageDriver pageDriver;

  /**
   * Gets the com.cognizantsoftvision.maqs.playwright.PageDriver from the test object.
   * @return the page driver
   */
  public PageDriver getPageDriver() {
    return this.pageDriver;
  }

  /**
   * Sets the com.cognizantsoftvision.maqs.playwright.PageDriver from the test object.
   * @param driver the page driver to be set
   */
  private void setPageDriver(PageDriver driver) {
    this.pageDriver = driver;
  }

  /**
   * The log from the test object.
   */
  protected ILogger log;

  /**
   * Gets the log from the test object.
   * @return the logger
   */
  public ILogger getLogger() {
    return this.getTestObject().getLogger();
  }

  /**
   * The performance timer collection from the test object.
   */
  protected IPerfTimerCollection perfTimerCollection;

  /**
   * Gets the performance timer collection from the test object.
   * @return the perf timer collection
   */
  public IPerfTimerCollection getPerfTimerCollection() {
    return this.getTestObject().getPerfTimerCollection();
  }

  /**
   * the Playwright test object.
   */
  protected IPlaywrightTestObject testObject;

  /**
   * Gets the Playwright test object.
   * @return the playwright test object interface
   */
  public IPlaywrightTestObject getTestObject() {
    return this.testObject;
  }

  /**
   * Sets the Playwright test object.
   * @param testObject the playwright test object
   */
  protected void setTestObject(IPlaywrightTestObject testObject) {
    this.testObject = testObject;
  }

  /**
   * Override the com.cognizantsoftvision.maqs.playwright.PageDriver.
   * This allows you to use something other than the default tests
   * object com.cognizantsoftvision.maqs.playwright.PageDriver.
   *
   * @param pageDriver the new page driver
   */
  public void overridePageDriver(PageDriver pageDriver) {
    // Override driver
    this.pageDriver = pageDriver;
  }

  /**
   * Check if the page has been loaded.
   * @return True if the page was loaded
   */
  public abstract boolean isPageLoaded();
}
