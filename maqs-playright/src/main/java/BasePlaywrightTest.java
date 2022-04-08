/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

import com.cognizantsoftvision.maqs.base.BaseExtendableTest;
import com.cognizantsoftvision.maqs.utilities.logging.*;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import org.testng.ITestResult;
import java.io.File;
import java.nio.file.Path;

/// <summary>
/// Generic base Playwright test class
/// </summary>
public class BasePlaywrightTest implements BaseExtendableTest<IPlaywrightTestObject> {

  /// <summary>
  /// Initializes a new instance of the <see cref="BasePlaywrightTest"/> class
  /// Set up the page for each test class
  /// </summary>
  public BasePlaywrightTest() {
  }

  /// <summary>
  /// Gets or sets the PageDriver
  /// </summary>
  public PageDriver pageDriver;

  public PageDriver getPageDriver() {
    return this.pageDriver;
  }

  public void setPageDriver(PageDriver pageDriver) {
    this.getTestObject().overridePageDriver(pageDriver);
  }

  /// <summary>
  /// The default get page function
  /// </summary>
  /// <returns>The page</returns>
  protected PageDriver getBrowser() {
    return PageDriverFactory.getDefaultPageDriver();
  }

  /// <summary>
  /// Create a test object
  /// </summary>
  /// <param name="log">Associated logger</param>
  /// <returns>The Playwright test object</returns>
  //@Override
  protected IPlaywrightTestObject createSpecificTestObject(Logger log) {
    return new PlaywrightTestObject(this::getBrowser, log, this.getFullyQualifiedTestClassName());
  }

  /// <summary>
  /// Attach or delete Playwright testing artifacts
  /// </summary>
  /// <param name="resultType">The test result</param>
//  @Override
  protected void beforeCleanup(TestResultType resultType) {
    // Try to take a screenshot
    try {
      // Just stop if we are not logging or the driver was not initalized or there is no browser
      if (this.getLoggingEnabledSetting() == LoggingEnabled.NO
          || !this.getTestObject().getPageManager().isDriverInitialized()
          || this.getPageDriver().getParentBrowser() == null) {
        return;
      }

      // The test did not pass or we want it logged regardless
      if (this.getLoggingEnabledSetting() == LoggingEnabled.YES || resultType != TestResultType.PASS) {
        String fullpath = ((IFileLogger)this.getLogger()).getFilePath();
        String fileNameWithoutExtension = Path.combine(Path.GetDirectoryName(fullpath), Path.GetFileNameWithoutExtension(fullpath));

        attachTestFiles(this.getPageDriver().getParentBrowser(), fileNameWithoutExtension);
        return;
      }

      // We are not logging these results so delete the recordings
      deleteTestFiles(this.getPageDriver().getParentBrowser());
    }
    catch (Exception e)
    {
      this.tryToLog(MessageType.WARNING, "Failed to attach (or cleanup) Playwright test files: " + e.getMessage());
    }
  }


  /// <summary>
  /// Attach Playwright related traces and video
  /// </summary>
  /// <param name="browser">Current test browser</param>
  /// <param name="baseName">Fully qualified log file without extension</param>
  private void attachTestFiles(Browser browser, String baseName) {
    int append = 0;
    for (var context : browser.contexts()) {
      String traceFilePath = baseName + "_" + append++ + ".zip";

      context.tracing().stop(new Tracing.StopOptions().setPath(new File(traceFilePath).toPath()));
//      {
//        Path = $"{traceFilePath}",
//      }).Wait();

      for (Page page : context.pages()) {
        if (page.video() != null) {
          this.getTestObject().AddAssociatedFile(page.video().path());
        }

        this.getTestObject().AddAssociatedFile(traceFilePath);
      }
    }
  }

  /// <summary>
  /// Delete Playwright related video
  /// </summary>
  /// <param name="browser">Current test browser</param>
  private void deleteTestFiles(Browser browser) {
    for (BrowserContext context : browser.contexts()) {
      for (Page page : context.pages()) {
        if (page.video() != null) {
          page.video().delete();
        }
      }
    }
  }

  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {

  }

  @Override
  protected void createNewTestObject() {

  }
}
