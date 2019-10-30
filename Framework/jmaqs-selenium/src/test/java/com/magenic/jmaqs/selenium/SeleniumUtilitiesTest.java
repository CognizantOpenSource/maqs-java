/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.base.BaseGenericTest;
import com.magenic.jmaqs.base.BaseTest;
import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.utilities.helper.StringProcessor;
import com.magenic.jmaqs.utilities.helper.TestCategories;
import com.magenic.jmaqs.utilities.logging.ConsoleLogger;
import com.magenic.jmaqs.utilities.logging.FileLogger;
import java.nio.file.Paths;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * The type Selenium utilities test.
 */
public class SeleniumUtilitiesTest extends BaseTest {

  /**
   * Test capture screenshot no append.
   */
  @Test(groups = TestCategories.Selenium)
  public void testCaptureScreenshotNoAppend() {
    WebDriver webDriver = null;
    try {
      webDriver = WebDriverFactory.getDefaultBrowser();
    } catch (Exception e) {
      e.printStackTrace();
    }
    FileLogger fileLogger = (FileLogger) this.getTestObject().getLog();
    SeleniumTestObject testObject = new SeleniumTestObject(webDriver, fileLogger,
        this.getTestObject().getFullyQualifiedTestName());
    this.setTestObject(testObject);

    // Open Google and take a screenshot
    webDriver.navigate().to("http://www.google.com");
    boolean isSuccess = SeleniumUtilities.captureScreenshot(webDriver, testObject);

    // Assert screenshot was successful
    Assert.assertTrue(isSuccess, "Expected Screenshot to be successful.");
  }

  /**
   * Test capture screenshot append.
   */
  @Test(groups = TestCategories.Selenium)
  public void testCaptureScreenshotAppend() {
    WebDriver webDriver = null;
    try {
      webDriver = WebDriverFactory.getDefaultBrowser();
    } catch (Exception e) {
      e.printStackTrace();
    }
    FileLogger fileLogger = (FileLogger) this.getTestObject().getLog();
    SeleniumTestObject testObject = new SeleniumTestObject(webDriver, fileLogger,
        this.getTestObject().getFullyQualifiedTestName());
    this.setTestObject(testObject);

    // Open Google and take a screenshot
    webDriver.navigate().to("http://www.google.com");
    boolean isSuccess = SeleniumUtilities.captureScreenshot(webDriver, testObject, "testAppend");

    // Assert screenshot was successful
    Assert.assertTrue(isSuccess, "Expected Screenshot to be successful.");
  }

  /**
   * Test capture screenshot console logger.
   */
  @Test(groups = TestCategories.Selenium)
  public void testCaptureScreenshotConsoleLogger() {
    WebDriver webDriver = null;
    try {
      webDriver = WebDriverFactory.getDefaultBrowser();
    } catch (Exception e) {
      e.printStackTrace();
    }
    ConsoleLogger consoleLogger = new ConsoleLogger();
    SeleniumTestObject testObject = new SeleniumTestObject(webDriver, consoleLogger,
        this.getTestObject().getFullyQualifiedTestName());
    this.setTestObject(testObject);

    // Open Google and take a screenshot
    webDriver.navigate().to("http://www.google.com");
    boolean isSuccess = SeleniumUtilities.captureScreenshot(webDriver, testObject, "testAppend");

    // Assert screenshot was NOT successful
    Assert.assertFalse(isSuccess, "Expected Screenshot to NOT be successful.");
  }

  /**
   * Test capture screenshot custom directory file name.
   */
  @Test(groups = TestCategories.Selenium)
  public void testCaptureScreenshotCustomDirectoryFileName() {
    WebDriver webDriver = null;
    try {
      webDriver = WebDriverFactory.getDefaultBrowser();
    } catch (Exception e) {
      e.printStackTrace();
    }
    FileLogger fileLogger = (FileLogger) this.getTestObject().getLog();
    SeleniumTestObject testObject = new SeleniumTestObject(webDriver, fileLogger,
        this.getTestObject().getFullyQualifiedTestName());
    this.setTestObject(testObject);

    // Open Google and take a screenshot
    webDriver.navigate().to("http://www.google.com");
    String dateTime = DateTimeFormatter.ofPattern("uuuu-MM-dd-HH-mm-ss-SSSS", Locale.getDefault())
        .format(LocalDateTime.now(Clock.systemUTC()));
    String filePath = SeleniumUtilities
        .captureScreenshot(webDriver, testObject, fileLogger.getDirectory(),
            StringProcessor.safeFormatter("%s - %s", "TestCustomName", dateTime));

    Assert.assertEquals(filePath, Paths.get(StringProcessor
        .safeFormatter("%s%s - %s%s", fileLogger.getDirectory(), "\\TestCustomName", dateTime,
            ".png")).normalize().toString());
  }

  /**
   * Test save page source no append.
   */
  @Test(groups = TestCategories.Selenium)
  public void testSavePageSourceNoAppend() {
    WebDriver webDriver = null;
    try {
      webDriver = WebDriverFactory.getDefaultBrowser();
    } catch (Exception e) {
      e.printStackTrace();
    }
    FileLogger fileLogger = (FileLogger) this.getTestObject().getLog();
    SeleniumTestObject testObject = new SeleniumTestObject(webDriver, fileLogger,
        this.getTestObject().getFullyQualifiedTestName());
    this.setTestObject(testObject);

    // Open Google and take a screenshot
    webDriver.navigate().to("http://www.google.com");
    boolean isSuccess = SeleniumUtilities.savePageSource(webDriver, testObject);

    // Assert screenshot was successful
    Assert.assertTrue(isSuccess, "Expected Screenshot to be successful.");
  }

  /**
   * Test save page source append.
   */
  @Test(groups = TestCategories.Selenium)
  public void testSavePageSourceAppend() {
    WebDriver webDriver = null;
    try {
      webDriver = WebDriverFactory.getDefaultBrowser();
    } catch (Exception e) {
      e.printStackTrace();
    }
    FileLogger fileLogger = (FileLogger) this.getTestObject().getLog();
    SeleniumTestObject testObject = new SeleniumTestObject(webDriver, fileLogger,
        this.getTestObject().getFullyQualifiedTestName());
    this.setTestObject(testObject);

    // Open Google and take a screenshot
    webDriver.navigate().to("http://www.google.com");
    boolean isSuccess = SeleniumUtilities.savePageSource(webDriver, testObject, "testAppend");

    // Assert screenshot was successful
    Assert.assertTrue(isSuccess, "Expected Screenshot to be successful.");
  }

  /**
   * Test save page source custom directory file name.
   */
  @Test(groups = TestCategories.Selenium)
  public void testSavePageSourceCustomDirectoryFileName() {
    WebDriver webDriver = null;
    try {
      webDriver = WebDriverFactory.getDefaultBrowser();
    } catch (Exception e) {
      e.printStackTrace();
    }
    FileLogger fileLogger = (FileLogger) this.getTestObject().getLog();
    SeleniumTestObject testObject = new SeleniumTestObject(webDriver, fileLogger,
        this.getTestObject().getFullyQualifiedTestName());
    this.setTestObject(testObject);

    // Open Google and take a screenshot
    webDriver.navigate().to("http://www.google.com");
    String dateTime = DateTimeFormatter.ofPattern("uuuu-MM-dd-HH-mm-ss-SSSS", Locale.getDefault())
        .format(LocalDateTime.now(Clock.systemUTC()));
    String filePath = SeleniumUtilities
        .savePageSource(webDriver, testObject, fileLogger.getDirectory(),
            StringProcessor.safeFormatter("%s - %s", "TestCustomName", dateTime));

    // Assert File Path returned from Screenshot is the same as expected file path.
    Assert.assertEquals(filePath, Paths.get(StringProcessor
        .safeFormatter("%s%s - %s%s", fileLogger.getDirectory(), "\\TestCustomName", dateTime,
            ".txt")).normalize().toString());

  }

  /**
   * Test save page source console logger.
   */
  @Test(groups = TestCategories.Selenium)
  public void testSavePageSourceConsoleLogger() {
    WebDriver webDriver = null;
    try {
      webDriver = WebDriverFactory.getDefaultBrowser();
    } catch (Exception e) {
      e.printStackTrace();
    }
    ConsoleLogger consoleLogger = new ConsoleLogger();
    SeleniumTestObject testObject = new SeleniumTestObject(webDriver, consoleLogger,
        this.getTestObject().getFullyQualifiedTestName());
    this.setTestObject(testObject);

    // Open Google and take a screenshot
    webDriver.navigate().to("http://www.google.com");
    boolean isSuccess = SeleniumUtilities.savePageSource(webDriver, testObject);

    // Assert screenshot was successful
    Assert.assertTrue(isSuccess, "Expected Screenshot to be successful.");
  }

  /**
   * Test web element to web driver.
   */
  @Test(groups = TestCategories.Selenium)
  public void testWebElementToWebDriver() {
    WebDriver webDriver = null;
    try {
      webDriver = WebDriverFactory.getDefaultBrowser();
    } catch (Exception e) {
      e.printStackTrace();
    }
    ConsoleLogger consoleLogger = new ConsoleLogger();
    SeleniumTestObject testObject = new SeleniumTestObject(webDriver, consoleLogger,
        this.getTestObject().getFullyQualifiedTestName());
    this.setTestObject(testObject);

    // Open Google and take a screenshot
    webDriver.navigate().to("http://www.google.com");
    WebElement input = UIWaitFactory.getWaitDriver(webDriver)
        .waitForVisibleElement(By.tagName("div"));
    WebDriver webElementToWebDriver = SeleniumUtilities.webElementToWebDriver(input);
    Assert.assertNotNull(webElementToWebDriver, "Expected extracted webdriver to not be null");
  }

  /**
   * Test kill driver.
   */
  @Test(groups = TestCategories.Selenium)
  public void testKillDriver() {
    WebDriver webDriver = null;
    try {
      webDriver = WebDriverFactory.getDefaultBrowser();
    } catch (Exception e) {
      e.printStackTrace();
    }
    ConsoleLogger consoleLogger = new ConsoleLogger();
    SeleniumTestObject testObject = new SeleniumTestObject(webDriver, consoleLogger,
        this.getTestObject().getFullyQualifiedTestName());
    this.setTestObject(testObject);

    // Open Google then Kill the Driver
    webDriver.navigate().to("http://www.google.com");
    SeleniumUtilities.killDriver(webDriver);

    // Assert that the Session ID is null in the Appium Driver
    Assert.assertNull(((RemoteWebDriver) webDriver).getSessionId(),
        "Expected Appium Driver Session ID to be null.");
  }

  @Override
  protected void postSetupLogging() throws Exception {

  }

  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {

  }
}