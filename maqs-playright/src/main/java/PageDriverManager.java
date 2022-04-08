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

/// <summary>
/// Playwright driver store
/// </summary>
public class PageDriverManager extends DriverManager<PageDriver> {

  /// <summary>
  /// Initializes a new instance of the <see cref="PlaywrightDriverManager"/> class
  /// </summary>
  /// <param name="getDriver">Function for getting an Playwright page</param>
  /// <param name="testObject">The associated test object</param>
  public PageDriverManager(Supplier<PageDriver> getDriver, ITestObject testObject) {
    super(() -> new PageDriver(getDriver.get().getAsyncPage()), testObject);
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
    this.overrideDriverGet(() -> overrideDriver);
  }

  /// <summary>
  /// Override the page
  /// </summary>
  /// <param name="overrideDriver">Function for getting a new page</param>
  public void overrideDriver(Supplier<Page> overrideDriver) {
    this.overrideDriverGet(overrideDriver);
  }

  /// <summary>
  /// Override the page
  /// </summary>
  /// <param name="overridePage">Function for getting a new page</param>
  public void overrideDriver(Supplier<Page> overridePage) {
    this.overrideDriverGet(() -> new PageDriver(overridePage.get()));
  }

  /// <summary>
  /// Get the page
  /// </summary>
  /// <returns>The page</returns>
  public PageDriver getPageDriver() {
    PageDriver tempDriver;

    if (!this.isDriverInitialized() && LoggingConfig.getLoggingEnabledSetting() != LoggingEnabled.NO) {
      tempDriver = getDriver() as PageDriver;
      this.getBaseDriver() = tempDriver;

      // Log the setup
      this.LoggingStartup(tempDriver);
    }

    tempDriver = getBase() as PageDriver;

    if(tempDriver == null) {
      throw new NullPointerException("Base driver is null");
    }

    return tempDriver;
  }

  /// <summary>
  /// Get the page
  /// </summary>
  /// <returns>The page</returns>
  public Object getPage() {
    return this.getPageDriver();
  }

  /// <summary>
  /// Log a verbose message and include the automation specific call stack data
  /// </summary>
  /// <param name="message">The message text</param>
  /// <param name="args">String format arguments</param>
  protected void logVerbose(String message, Object[] args) {
    StringBuilder messages = new StringBuilder();
    messages.append(StringProcessor.safeFormatter(message, args));

    var methodInfo = MethodBase.GetCurrentMethod();
    var fullName = $"{methodInfo.DeclaringType.FullName}.{methodInfo.Name}";

    for (String stackLevel : Environment.StackTrace.Split(new String[] { Environment.NewLine }, StringSplitOptions.None)) {
      String trimmed = stackLevel.trim();
      if (!trimmed.startsWith("at Microsoft.") && !trimmed.startsWith("at System.")
          && !trimmed.startsWith("at NUnit.") && !trimmed.startsWith("at " + fullName)) {
        messages.append(stackLevel);
      }
    }

    this.getLogger().logMessage(MessageType.VERBOSE, messages.toString());
  }

  /// <summary>
  /// Have the driver cleanup after itself
  /// </summary>
  protected void driverDispose() {
    this.getLogger().logMessage(MessageType.VERBOSE, "Start dispose driver");

    // If we never created the driver we don't have any cleanup to do
    if (!this.isDriverInitialized()) {
      return;
    }

    try {
      PageDriver driver = this.getPageDriver();
//      driver.AsyncPage.close().Wait();
      driver.getAsyncPage().close();
    } catch (Exception e) {
      this.getLogger().logMessage(MessageType.ERROR, "Failed to close page because: " + e.getMessage());
    }
    this.setBaseDriver(null);
    this.getLogger().logMessage(MessageType.VERBOSE, "End dispose driver");
  }

  /// <summary>
  /// Log that the page setup
  /// </summary>
  /// <param name="pageDriver">The page</param>
  private void LoggingStartup(PageDriver pageDriver) {
    try {
      this.getLogger().logMessage(MessageType.INFORMATION, "Driver: " + pageDriver.getParentBrowser());
    } catch (Exception e) {
      this.getLogger().logMessage(MessageType.ERROR, "Failed to start driver because: " + e.getMessage());
      System.out.print("Failed to start driver because: " + e.getMessage());
    }
  }

  @Override public void close() throws Exception {

  }
}
