/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.appium;

import com.magenic.jmaqs.base.BaseTest;
import com.magenic.jmaqs.utilities.helper.StringProcessor;
import com.magenic.jmaqs.utilities.logging.ConsoleLogger;
import com.magenic.jmaqs.utilities.logging.FileLogger;
import io.appium.java_client.AppiumDriver;

import java.nio.file.Paths;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

/**
 * Appium Utilities Unit Test class.
 */
public class AppiumUtilitiesUnitTest extends BaseTest {
  @Test
  public void testCaptureScreenshotNoAppend() {
    AppiumDriver<WebElement> appiumDriver = AppiumDriverFactory.getDefaultMobileDriver();
    FileLogger fileLogger = (FileLogger)this.getTestObject().getLog();
    AppiumTestObject testObject = new AppiumTestObject(
        appiumDriver,
        fileLogger,
        this.getTestObject().getFullyQualifiedTestName());
    this.setTestObject(testObject);

    // Open Google and take a screenshot
    appiumDriver.navigate().to("http://www.google.com");
    boolean isSuccess = AppiumUtilities.captureScreenshot(appiumDriver, testObject);

    // Assert screenshot was successful
    Assert.assertTrue(isSuccess, "Expected Screenshot to be successful.");
  }

  @Test
  public void testCaptureScreenshotAppend() {
    AppiumDriver<WebElement> appiumDriver = AppiumDriverFactory.getDefaultMobileDriver();
    FileLogger fileLogger = (FileLogger)this.getTestObject().getLog();
    AppiumTestObject testObject = new AppiumTestObject(
        appiumDriver,
        fileLogger,
        this.getTestObject().getFullyQualifiedTestName());
    this.setTestObject(testObject);

    // Open Google and take a screenshot
    appiumDriver.navigate().to("http://www.google.com");
    boolean isSuccess = AppiumUtilities.captureScreenshot(appiumDriver, testObject, "testAppend");

    // Assert screenshot was successful
    Assert.assertTrue(isSuccess, "Expected Screenshot to be successful.");
  }

  @Test
  public void testCaptureScreenshotConsoleLogger() {
    AppiumDriver<WebElement> appiumDriver = AppiumDriverFactory.getDefaultMobileDriver();
    ConsoleLogger consoleLogger = new ConsoleLogger();
    AppiumTestObject testObject = new AppiumTestObject(
        appiumDriver,
        consoleLogger,
        this.getTestObject().getFullyQualifiedTestName());
    this.setTestObject(testObject);

    // Open Google and take a screenshot
    appiumDriver.navigate().to("http://www.google.com");
    boolean isSuccess = AppiumUtilities.captureScreenshot(appiumDriver, testObject, "testAppend");

    // Assert screenshot was NOT successful
    Assert.assertFalse(isSuccess, "Expected Screenshot to NOT be successful.");
  }

  @Test
  public void testCaptureScreenshotCustomDirectoryFileName() {
    AppiumDriver<WebElement> appiumDriver = AppiumDriverFactory.getDefaultMobileDriver();
    FileLogger fileLogger = (FileLogger)this.getTestObject().getLog();
    AppiumTestObject testObject = new AppiumTestObject(
        appiumDriver,
        fileLogger,
        this.getTestObject().getFullyQualifiedTestName());
    this.setTestObject(testObject);

    // Open Google and take a screenshot with a custom name and date/time appended
    appiumDriver.navigate().to("http://www.google.com");
    String dateTime = DateTimeFormatter.ofPattern(
        "uuuu-MM-dd-HH-mm-ss-SSSS",
        Locale.getDefault()).format(LocalDateTime.now(Clock.systemUTC()));
    String filePath = AppiumUtilities.captureScreenshot(
          appiumDriver,
          testObject,
          fileLogger.getDirectory(),
          StringProcessor.safeFormatter("%s - %s","TestCustomName", dateTime));

    // Assert File Path returned from Screenshot is the same as expected file path.
    Assert.assertEquals(
        filePath,
        Paths.get(StringProcessor.safeFormatter(
            "%s%s - %s%s",fileLogger.getDirectory(), "\\TestCustomName", dateTime, ".png")).normalize().toString());
  }

  @Override protected void postSetupLogging() throws Exception {

  }

  @Override protected void beforeLoggingTeardown(ITestResult resultType) {

  }
}
