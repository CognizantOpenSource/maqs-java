/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium.baseSeleniumTest;

import com.magenic.jmaqs.baseTest.BaseGenericTest;
import com.magenic.jmaqs.utilities.helper.StringProcessor;
import com.magenic.jmaqs.utilities.logging.MessageType;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

/**
 * Base Selenium Test class.
 */
public abstract class BaseSeleniumTest extends BaseGenericTest {

  /**
   * Initialize a new instance of the BaseSeleniumTest class.
   */
  public BaseSeleniumTest() {
  }

  /**
   * Thread local storage of SeleniumTestObject.
   */
  private ThreadLocal<SeleniumTestObject> seleniumTestObject = new ThreadLocal<SeleniumTestObject>();

  /**
   * Get WebDriver.
   * 
   * @return WebDriver
   */
  public WebDriver getWebDriver() {
    return this.seleniumTestObject.get().getWebDriver();
  }

  /**
   * Get SeleniumWait.
   * 
   * @return SeleniumWait
   */
  public SeleniumWait getSeleniumWait() {
    return this.seleniumTestObject.get().getSeleniumWait();
  }

  /**
   * Get the seleniumTestObject for this test.
   * 
   * @return The seleniumTestObject
   */
  public SeleniumTestObject getSeleniumTestObject() {
    return this.seleniumTestObject.get();
  }

  /**
   * Log info about the web driver setup.
   */
  @Override
  protected void postSetupLogging() {
    try {

      if (SeleniumConfig.getBrowserName().equalsIgnoreCase("Remote")) {
        this.getLogger().logMessage(MessageType.INFORMATION, "Remote driver: %s",
            SeleniumConfig.getRemoteBrowserName());
      } else {
        this.getLogger().logMessage(MessageType.INFORMATION, "Loaded driver: %s",
            SeleniumConfig.getBrowserName());
      }

      WebDriver driver = SeleniumConfig.browser();
      SeleniumWait wait = new SeleniumWait(driver);

      seleniumTestObject.set(new SeleniumTestObject(driver, wait, this.getLogger(),
          this.getFullyQualifiedTestClassName()));

      wait.setWaitDriver(driver, SeleniumConfig.getWaitDriver(driver));
      
    } catch (Exception e) {
      this.getLogger().logMessage(MessageType.ERROR, "Failed to start driver because: %s",
          e.getMessage());
      System.out.println(
          StringProcessor.safeFormatter("Browser type %s is not supported", e.getMessage()));
    }
  }

  /**
   * Take a screen shot if needed and tear down the web driver.
   * 
   * @param resultType
   *          The test result type
   */
  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {
    // Try to take a screen shot
    try {
      // TODO add screen capture once SeleniumUtilities has been created
      /*
       * if (this.Log is FileLogger && resultType != TestResultType.PASS &&
       * this.LoggingEnabledSetting != LoggingEnabled.NO) {
       * SeleniumUtilities.CaptureScreenshot(this.WebDriver, this.Log); }
       */
    } catch (Exception e) {
      this.tryToLog(MessageType.WARNING, "Failed to get screen shot because: %s", e.getMessage());
    }

    this.tryToLog(MessageType.INFORMATION, "Close");

    // tear down the web driver
    try {
      this.seleniumTestObject.get().webDriver.quit();
    } catch (Exception e) {
      this.tryToLog(MessageType.WARNING, "Failed to quit because: %s", e.getMessage());
    }
  }
}
