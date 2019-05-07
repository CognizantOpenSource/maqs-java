/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium.baseSeleniumTest;

import com.magenic.jmaqs.baseTest.BaseGenericTest;
import com.magenic.jmaqs.utilities.helper.StringProcessor;
import com.magenic.jmaqs.utilities.logging.Logger;
import com.magenic.jmaqs.utilities.logging.LoggingEnabled;
import com.magenic.jmaqs.utilities.logging.MessageType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.EventListener;

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

  //public WebDriver webDrive = getWebDriver();

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
        this.getLog().logMessage(MessageType.INFORMATION, "Remote driver: %s",
            SeleniumConfig.getRemoteBrowserName());
      } else {
        this.getLog().logMessage(MessageType.INFORMATION, "Loaded driver: %s",
            SeleniumConfig.getBrowserName());
      }

      WebDriver driver = SeleniumConfig.browser();
      SeleniumWait wait = new SeleniumWait(driver);

      seleniumTestObject.set(new SeleniumTestObject(driver, wait, this.getLog(),
          this.getFullyQualifiedTestClassName()));      
    } catch (Exception e) {
      this.getLog().logMessage(MessageType.ERROR, "Failed to start driver because: %s",
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
      if (this.getWebDriver() != null && resultType.getStatus() != ITestResult.SUCCESS 
          && this.getLoggingEnabledSetting() != LoggingEnabled.NO) {

        captureScreenShot(this.getWebDriver(), this.getLog(), "");
      }       
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

  /**
   * Capture Screenshot.
   * @return Path to screenshot.
   */
  protected String captureScreenShot(WebDriver driver, Logger log, String fileName) {
    return SeleniumUtilities.captureScreenshot(driver, log, fileName);
  }

  // The default get web Driver function
  protected  WebDriver getBrowser() throws Exception {
      // Returns the web driver
      return SeleniumConfig.browser();
  }

  // Setup the event firing web driver
  protected void SetupEventFireingTester() throws Exception {
    //this.seleniumTestObject = this.getBrowser();
    try {
        this.seleniumTestObject.get().webDriver = new EventFiringWebDriver(this.seleniumTestObject.get().webDriver);
      this.mapEvents((EventFiringWebDriver)this.seleniumTestObject.get().webDriver);
    }
    catch (Exception e) {
      this.getLog().logMessage(MessageType.WARNING, "Cannot cast driver: {0} as an event firing driver", this.seleniumTestObject.get().webDriver);
    }
  }

  // Setup the normal web driver - the none event firing implementation
  protected  void SetupNoneEventFiringTester() throws Exception {
    this.seleniumTestObject.get().webDriver = this.getBrowser();
  }

  // Map selenium events to log events
  private void mapEvents(EventFiringWebDriver eventFiringDriver)
  {
    if (this.getLoggingEnabledSetting() == LoggingEnabled.YES || getLoggingEnabledSetting() == LoggingEnabled.ONFAIL)
    {
      EventHandler eventHandler = new EventHandler(this.seleniumTestObject.get().webDriver, getLog());



      /*
      eventHandler.beforeChangeValueOf();
      eventHandler.afterChangeValueOf();
      eventHandler.beforeClickOn();
      eventHandler.afterClickOn();
      eventHandler.beforeChangeValueOf();
      eventHandler.beforeChangeValueOf();
      eventHandler.afterChangeValueOf();
      eventHandler.afterChangeValueOf();
      eventHandler.beforeFindBy();
      eventHandler.afterFindBy();
      eventHandler.beforeNavigateBack();
      eventHandler.afterNavigateBack();
      eventHandler.beforeNavigateForward();
      eventHandler.afterNavigateForward();
      eventHandler.beforeNavigateRefresh();
      eventHandler.afterNavigateRefresh();

      eventHandler.beforeNavigateTo();
      eventHandler.afterNavigateTo();
      eventHandler.beforeScript();
      eventHandler.afterScript();
      eventHandler.beforeSwitchToWindow();
      eventHandler.afterSwitchToWindow();
      eventHandler.beforeAlertAccept();
      eventHandler.afterAlertAccept();
      eventHandler.beforeAlertDismiss();
      eventHandler.afterAlertDismiss();
      eventHandler.onException();
      eventHandler.beforeGetScreenshotAs();
      eventHandler.afterGetScreenshotAs();
      eventHandler.beforeGetText();
      eventHandler.afterGetText();
      */
    }
  }
}