/*
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.maqs.appium.baseAppiumTest;

import org.testng.ITestResult;

import com.magenic.jmaqs.baseTest.BaseGenericTest;
import com.magenic.jmaqs.selenium.baseSeleniumTest.SeleniumWait;
import com.magenic.jmaqs.utilities.helper.StringProcessor;
import com.magenic.jmaqs.utilities.logging.MessageType;

import io.appium.java_client.AppiumDriver;

/**
 * Base Appium Test Class.
 *
 * @author JasonE
 */
public abstract class BaseAppiumTest extends BaseGenericTest {

  /** Thread local storage of AppiumTestObject. */
  private ThreadLocal<AppiumTestObject> appiumTestObject = new ThreadLocal<AppiumTestObject>();

  /**
   * Initialize a new instance of the BaseAppiumTest class.
   */
  public BaseAppiumTest() {

  }

  /**
   * Gets the appium driver.
   *
   * @return the appium driver
   */
  public AppiumDriver getAppiumDriver() {
    return this.appiumTestObject.get().getAppiumDriver();
  }

  /**
   * Gets the appium wait.
   *
   * @return the appium wait
   */
  public SeleniumWait getAppiumWait() {
    return this.appiumTestObject.get().getAppiumWait();
  }

  /**
   * Gets the appium test object.
   *
   * @return the appium test object
   */
  public AppiumTestObject getAppiumTestObject() {
    return this.appiumTestObject.get();
  }

  /*
   * (non-Javadoc)
   * 
   * @see magenic.maqs.utilities.BaseTest.BaseGenericTest#postSetupLogging()
   */
  protected void postSetupLogging() {
    try {
      // TODO: Compare to C# version
      /*
       * if (AppiumConfig.getMobileDeviceOs().equalsIgnoreCase("Remote")) {
       * this.getLogger().logMessage(MessageType.INFORMATION, "Remote driver: %s",
       * AppiumConfig.getRemoteMobileDevice()); } else {
       */
      AppiumDriver driver = AppiumConfig.mobileDevice();
      SeleniumWait wait = new SeleniumWait(driver);

      appiumTestObject.set(new AppiumTestObject(driver, wait, this.getFullyQualifiedTestClassName(),
          this.getLogger()));
      this.getLogger().logMessage(MessageType.INFORMATION, "Loaded driver: %s",
          AppiumConfig.getMobileDeviceOs());
      // }
    } catch (Exception e) {
      this.getLogger().logMessage(MessageType.ERROR, "Failed to start driver because: %s",
          e.getMessage());
      System.out.println(
          StringProcessor.safeFormatter("Browser type %s is not supported", e.getMessage()));
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * magenic.maqs.utilities.BaseTest.BaseGenericTest#beforeLoggingTeardown(org.testng.ITestResult)
   */
  protected void beforeLoggingTeardown(ITestResult resultType) {
    try {

    } catch (Exception e)

    {
      this.tryToLog(MessageType.WARNING, "Failed to get screen shot because: %s", e.getMessage());
    }
    this.tryToLog(MessageType.INFORMATION, "Close");

    try {
      this.appiumTestObject.get().appiumDriver.quit();
    } catch (Exception e) {
      this.tryToLog(MessageType.WARNING, "Failed to quit because: %s", e.getMessage());
    }
  }
}
