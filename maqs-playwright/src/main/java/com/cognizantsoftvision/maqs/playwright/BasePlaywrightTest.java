/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.cognizantsoftvision.maqs.base.BaseExtendableTest;
import com.cognizantsoftvision.maqs.utilities.helper.StringProcessor;
import com.cognizantsoftvision.maqs.utilities.logging.IFileLogger;
import com.cognizantsoftvision.maqs.utilities.logging.ILogger;
import com.cognizantsoftvision.maqs.utilities.logging.LoggingConfig;
import com.cognizantsoftvision.maqs.utilities.logging.LoggingEnabled;
import com.cognizantsoftvision.maqs.utilities.logging.MessageType;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.testng.ITestResult;

/**
 * The Base Playwright Test class.
 */
public class BasePlaywrightTest extends BaseExtendableTest<IPlaywrightTestObject> {

  /**
   * Initializes a new instance of the BasePlaywrightTest class.
   * Set up the page for each test class
   */
  public BasePlaywrightTest() {
    // Empty class setup
  }

  /**
   * Gets the PageDriver.
   * @return the page driver
   */
  public PageDriver getPageDriver() {
    return this.getTestObject().getPageDriver();
  }

  /**
   * Gets a new PageDriver.
   * @return the page driver
   */
  protected PageDriver getPage() {
    return PageDriverFactory.getDefaultPageDriver();
  }

  /**
   * Sets the PageDriver.
   * @param pageDriver the new page driver to be set
   */
  public void setPageDriver(PageDriver pageDriver) {
    this.getTestObject().setPageDriver(pageDriver);
  }

  /**
   * create a new test object.
   * @param log the logger to be set in the new test object
   * @return the playwright test object interface
   */
  protected IPlaywrightTestObject createNewTestObject(ILogger log) {
    return new PlaywrightTestObject(this::getPageDriver, log, this.getFullyQualifiedTestClassName());
  }

  /**
   * Create a new test object.
   */
  @Override
  protected void createNewTestObject() {
    try {
      this.setTestObject(
          new PlaywrightTestObject(() -> {
            try {
              return getPage();
            } catch (Exception e) {
              getLogger().logMessage(StringProcessor.safeFormatter("Failed setup driver: %s", e.toString()));
              throw e;
            }
          }, this.createLogger(), this.getFullyQualifiedTestClassName()));
    } catch (Exception e) {
      getLogger().logMessage(StringProcessor.safeFormatter("Test Object could not be created: %s", e.getMessage()));
      throw e;
    }
  }

  /**
   * Attach or delete Playwright testing artifacts.
   * @param resultType The test result
   */
  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {
    // Try to take a screenshot.
    try {
      // Just stop if we are not logging or the driver was not initialized or there is no browser
      if (this.getLoggingEnabled() == LoggingEnabled.NO
          || !this.getTestObject().getPageManager().isDriverInitialized()
          || this.getPageDriver().getParentBrowser() == null) {
        return;
      }

      // The test did not pass, or we want it logged regardless
      if (this.getLoggingEnabled() == LoggingEnabled.YES || !resultType.isSuccess()) {
        String fullPath = ((IFileLogger)this.getLogger()).getFilePath();
        String fileNameWithoutExtension = Path.of(fullPath).getFileName().toString();
        attachTestFiles(this.getPageDriver().getParentBrowser(), fileNameWithoutExtension);
        return;
      }

      // We are not logging these results so delete the recordings
      deleteTestFiles(this.getPageDriver().getParentBrowser());
    } catch (Exception e) {
      this.tryToLog(MessageType.WARNING,
          "Failed to attach (or cleanup) Playwright test files: " + e.getMessage());
    }
  }

  /**
   * Attach Playwright related traces and video.
   * @param browser Current test browser
   * @param baseName Fully qualified log file without extension
   */
  private void attachTestFiles(Browser browser, String baseName) {
    int append = 0;

    for (var context : browser.contexts()) {
      String traceFilePath = Paths.get(LoggingConfig.getLogDirectory(),  baseName + "_" + append++ + ".zip").toString();
      context.tracing().stop(new Tracing.StopOptions().setPath(new File(traceFilePath).toPath()));

      for (Page page : context.pages()) {
        if (page.video() != null) {
          this.getTestObject().addAssociatedFile(page.video().path().toString());
        }

        this.getTestObject().addAssociatedFile(traceFilePath);
      }
    }
  }

  /**
   * Delete Playwright related video.
   * @param browser Current test browser
   */
  private static void deleteTestFiles(Browser browser) {
    for (BrowserContext context : browser.contexts()) {
      for (Page page : context.pages()) {
        if (page.video() != null) {
          page.video().delete();
        }
      }
    }
  }
}
