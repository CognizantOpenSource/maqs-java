/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

import com.cognizantsoftvision.maqs.utilities.logging.Logger;
import com.cognizantsoftvision.maqs.utilities.performance.IPerfTimerCollection;

/// <summary>
/// Base Playwright page model
/// </summary>
public abstract class BasePlaywrightPageModel {

  /// <summary>
  /// Initializes a new instance of the <see cref="BasePlaywrightPageModel"/> class.
  /// </summary>
  /// <param name="testObject">The Playwright test object</param>
  protected BasePlaywrightPageModel(IPlaywrightTestObject testObject) {
    this.setTestObject(testObject);
    this.setPageDriver(testObject.getPageDriver());
  }

  /// <summary>
  /// Initializes a new instance of the <see cref="BasePlaywrightPageModel"/> class.
  /// </summary>
  /// <param name="testObject">The Playwright test object</param>
  /// <param name="customDriver">Driver to use instead of the default test object related driver</param>
  protected BasePlaywrightPageModel(IPlaywrightTestObject testObject, PageDriver customDriver) {
    this.setTestObject(testObject);
    this.setPageDriver(customDriver);
  }

  /// <summary>
  /// Gets the PageDriver from the test object
  /// </summary>
  protected PageDriver pageDriver;

  public PageDriver getPageDriver() {
    return this.pageDriver;
  }

  private void setPageDriver(PageDriver driver) {
    this.pageDriver = driver;
  }

  /// <summary>
  /// Gets the log from the test object
  /// </summary>
  protected Logger log;

  public Logger getLogger() {
    return this.log;
  }

  /// <summary>
  /// Gets the performance timer collection from the test object
  /// </summary>
  protected IPerfTimerCollection perfTimerCollection;

  public IPerfTimerCollection getPerfTimerCollection() {
    return this.perfTimerCollection;
  }

  /// <summary>
  /// Gets or sets the Playwright test object
  /// </summary>
  protected IPlaywrightTestObject testObject;

  public IPlaywrightTestObject getTestObject() {
    return this.testObject;
  }

  public void setTestObject(IPlaywrightTestObject testObject) {
    this.testObject = testObject;
  }

  /// <summary>
  /// Override the PageDriver
  /// This allows you to use something other than the default tests object PageDriver.
  /// </summary>
  /// <param name="PageDriver">The override PageDriver</param>
  public void overridePageDriver(PageDriver pageDriver) {
    // Override driver
    this.pageDriver = pageDriver;
  }

  /// <summary>
  /// Check if the page has been loaded
  /// </summary>
  /// <returns>True if the page was loaded</returns>
  public abstract boolean isPageLoaded();
}
