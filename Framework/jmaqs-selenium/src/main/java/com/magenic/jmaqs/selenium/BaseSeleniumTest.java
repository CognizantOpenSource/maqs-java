/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.base.BaseExtendableTest;
import com.magenic.jmaqs.utilities.helper.StringProcessor;
import com.magenic.jmaqs.utilities.logging.Logger;
import com.magenic.jmaqs.utilities.logging.LoggingEnabled;
import com.magenic.jmaqs.utilities.logging.MessageType;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

/**
 * Base Selenium Test class.
 */
public abstract class BaseSeleniumTest extends BaseExtendableTest<SeleniumTestObject> {

  /**
   * Initialize a new instance of the BaseSeleniumTest class.
   */
  public BaseSeleniumTest() {
  }

  /**
   * Thread local storage of SeleniumTestObject.
   *
   * @deprecated methodology no longer used
   */
  @Deprecated
  private ThreadLocal<SeleniumTestObject> seleniumTestObject = new ThreadLocal<SeleniumTestObject>();

  /**
   * Get WebDriver.
   *
   * @return WebDriver web driver
   */
  public WebDriver getWebDriver() {
    return this.getTestObject().getWebDriver();
  }

  /**
   * Sets web driver.
   *
   * @param webDriver the web driver
   */
  public void setWebDriver(WebDriver webDriver) {
    this.getTestObject().setWebDriver(webDriver);
  }

  /**
   * Get SeleniumWait.
   *
   * @return SeleniumWait selenium wait
   * @deprecated {@link com.magenic.jmaqs.selenium.SeleniumWait} has been deprecated
   */
  @Deprecated
  public SeleniumWait getSeleniumWait() {
    return this.seleniumTestObject.get().getSeleniumWait();
  }

  /**
   * Get the seleniumTestObject for this test.
   *
   * @return The seleniumTestObject
   * @deprecated methodology no longer used
   */
  @Deprecated
  public SeleniumTestObject getSeleniumTestObject() {
    return this.seleniumTestObject.get();
  }

  /**
   * Log info about the web driver setup.
   *
   * @deprecated methodology no longer used.
   */
  @Deprecated
  protected void postSetupLogging() {
    try {

      if (SeleniumConfig.getBrowserName().equalsIgnoreCase("Remote")) {
        this.getLogger().logMessage(MessageType.INFORMATION, "Remote driver: %s",
            SeleniumConfig.getRemoteBrowserName());
      } else {
        this.getLogger().logMessage(MessageType.INFORMATION, "Loaded driver: %s",
            SeleniumConfig.getBrowserName());
      }

      /*WebDriver driver = SeleniumConfig.browser();
      SeleniumWait wait = new SeleniumWait(driver);

      seleniumTestObject.set(new SeleniumTestObject(driver, wait, this.getLogger(),
          this.getFullyQualifiedTestClassName()));  */
    } catch (Exception e) {
      this.getLogger()
          .logMessage(MessageType.ERROR, "Failed to start driver because: %s", e.getMessage());
      System.out.println(
          StringProcessor.safeFormatter("Browser type %s is not supported", e.getMessage()));
    }
  }

  /**
   * Take a screen shot if needed and tear down the web driver.
   *
   * @param resultType The test result type
   */
  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {
    // Try to take a screen shot
    try {
      if (this.getWebDriver() != null && resultType.getStatus() != ITestResult.SUCCESS
          && this.getLoggingEnabledSetting() != LoggingEnabled.NO) {

        captureScreenShot(this.getWebDriver(), this.getLogger(), "");
      }
    } catch (Exception e) {
      this.tryToLog(MessageType.WARNING, "Failed to get screen shot because: %s", e.getMessage());
    }
  }

  /**
   * Capture Screenshot.
   *
   * @param driver   the driver
   * @param log      the log
   * @param fileName the file name
   * @return Path to screenshot.
   * @deprecated removing method unless compelling reason not to.
   */
  @Deprecated
  protected String captureScreenShot(WebDriver driver, Logger log, String fileName) {
    return SeleniumUtilities.captureScreenshot(driver, log, fileName);
  }

  /**
   * Get the current browser.
   *
   * @return Current browser Web Driver
   * @throws Exception Throws exception
   */
  protected WebDriver getBrowser() throws Exception {
    // Returns the web driver
    return WebDriverFactory.getDefaultBrowser();
  }

  @Override
  protected void createNewTestObject() {
    try {
      this.setTestObject(new SeleniumTestObject(this.getBrowser(), this.createLogger(),
          this.getFullyQualifiedTestClassName()));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}