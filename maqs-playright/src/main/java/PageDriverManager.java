/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

import com.cognizantsoftvision.maqs.base.BaseTestObject;
import com.cognizantsoftvision.maqs.base.DriverManager;
import com.cognizantsoftvision.maqs.base.ITestObject;
import com.cognizantsoftvision.maqs.utilities.helper.StringProcessor;
import com.cognizantsoftvision.maqs.utilities.logging.LoggingConfig;
import com.cognizantsoftvision.maqs.utilities.logging.LoggingEnabled;
import com.cognizantsoftvision.maqs.utilities.logging.MessageType;
import com.microsoft.playwright.Page;
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
  protected PageDriverManager(Supplier<PageDriver> getDriverFunction, ITestObject baseTestObject) {
    super(getDriverFunction, baseTestObject);
  }

  /// <summary>
  /// Override the page
  /// </summary>
  /// <param name="overrideDriver">The new page</param>
  public void overrideDriver(PageDriver overrideDriver) {
    this.overrideDriver(() -> overrideDriver);
  }

  /// <summary>
  /// Override the page
  /// </summary>
  /// <param name="overrideDriver">Function for getting a new page</param>
  public void overrideDriver(PageDriver overrideDriver) {
    this.overrideDriver(overrideDriver);
  }

  /// <summary>
  /// Override the page
  /// </summary>
  /// <param name="overridePage">Function for getting a new page</param>
  public void overrideDriver(Supplier<Page> overridePage) {
    this.overrideDriverGet(() -> new PageDriver(overridePage.get()));
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
   * Log a verbose message and include the automation specific call stack data.
   * @param message The message text
   * @param args String format arguments
   */
  protected void logVerbose(String message, Object[] args) {
    StringBuilder messages = new StringBuilder();
    messages.append(StringProcessor.safeFormatter(message, args));
    String fullTestName = this.getTestObject().getFullyQualifiedTestName();

    Thread thread = Thread.currentThread();
    for (StackTraceElement stackTraceElement : thread.getStackTrace()) {
      String trim = stackTraceElement.toString().trim();
      if (!trim.startsWith(fullTestName)) {
        messages.append(stackTraceElement);
      }
    }

    this.getLogger().logMessage(MessageType.VERBOSE, messages.toString());
  }

  /**
   * Have the driver cleanup after itself.
   */
  protected void driverDispose() {
    this.getLogger().logMessage(MessageType.VERBOSE, "Start dispose driver");

    // If we never created the driver we don't have any cleanup to do
    if (!this.isDriverInitialized()) {
      return;
    }

    try {
      PageDriver driver = this.getPageDriver();
      driver.getAsyncPage().close();
    } catch (Exception e) {
      this.getLogger().logMessage(MessageType.ERROR, "Failed to close page because: " + e.getMessage());
    }
    this.setBaseDriver(null);
    this.getLogger().logMessage(MessageType.VERBOSE, "End dispose driver");
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
}
