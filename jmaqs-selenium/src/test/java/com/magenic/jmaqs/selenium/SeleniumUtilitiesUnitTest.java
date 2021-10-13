/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.base.BaseGenericTest;
import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.utilities.helper.StringProcessor;
import com.magenic.jmaqs.utilities.helper.TestCategories;
import com.magenic.jmaqs.utilities.logging.ConsoleLogger;
import com.magenic.jmaqs.utilities.logging.FileLogger;
import java.io.File;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * The type Selenium utilities test.
 */
public class SeleniumUtilitiesUnitTest extends BaseGenericTest {

        /**
         * The web driver.
         */
        private WebDriver webDriver;

        /**
         * Sets up the web driver for the unit tests.
         */

        public void setUp() {
                webDriver = WebDriverFactory.getDefaultBrowser();
        }

        /**
         * Test capture screenshot no append.
         */
        @Test(groups = TestCategories.SELENIUM)
        public void testCaptureScreenshotNoAppend() {
                setUp();
                try {
                        FileLogger fileLogger = (FileLogger) this.getTestObject().getLogger();
                        SeleniumTestObject testObject = new SeleniumTestObject(webDriver, fileLogger,
                                        this.getTestObject().getFullyQualifiedTestName());
                        this.setTestObject(testObject);

                        // Open Google and take a screenshot
                        webDriver.navigate().to("http://www.google.com");
                        boolean isSuccess = SeleniumUtilities.captureScreenshot(webDriver, testObject);

                        // Assert screenshot was successful
                        Assert.assertTrue(isSuccess, "Expected Screenshot to be successful.");
                        String[] arrayOfAssociatedFiles = testObject.getArrayOfAssociatedFiles();
                        Assert.assertTrue(
                                        new File((arrayOfAssociatedFiles[arrayOfAssociatedFiles.length - 1])).exists(),
                                        "Checking that expected file path exists");
                } finally {
                        webDriver.quit();
                }
        }

        /**
         * Test capture screenshot append.
         */
        @Test(groups = TestCategories.SELENIUM)
        public void testCaptureScreenshotAppend() {
                setUp();
                try {
                        FileLogger fileLogger = (FileLogger) this.getTestObject().getLogger();
                        SeleniumTestObject testObject = new SeleniumTestObject(webDriver, fileLogger,
                                        this.getTestObject().getFullyQualifiedTestName());
                        this.setTestObject(testObject);

                        // Open Google and take a screenshot
                        webDriver.navigate().to("http://www.google.com");
                        final String testAppend = "testAppend";
                        boolean isSuccess = SeleniumUtilities.captureScreenshot(webDriver, testObject, testAppend);

                        // Assert screenshot was successful
                        Assert.assertTrue(isSuccess, "Expected Screenshot to be successful.");
                        String[] arrayOfAssociatedFiles = testObject.getArrayOfAssociatedFiles();
                        Assert.assertTrue(
                                        new File((arrayOfAssociatedFiles[arrayOfAssociatedFiles.length - 1])).exists(),
                                        "Checking that expected file path exists");
                        Assert.assertTrue(
                                        new File((arrayOfAssociatedFiles[arrayOfAssociatedFiles.length - 1])).getName()
                                                        .contains(testAppend),
                                        "Checking that appended value was added to file");
                } finally {
                        webDriver.quit();
                }
        }

        /**
         * Test capture screenshot console logger.
         */
        @Test(groups = TestCategories.SELENIUM)
        public void testCaptureScreenshotConsoleLogger() {
                setUp();
                try {
                        ConsoleLogger consoleLogger = new ConsoleLogger();
                        SeleniumTestObject testObject = new SeleniumTestObject(webDriver, consoleLogger,
                                        this.getTestObject().getFullyQualifiedTestName());
                        this.setTestObject(testObject);

                        // Open Google and take a screenshot
                        webDriver.navigate().to("http://www.google.com");
                        boolean isSuccess = SeleniumUtilities.captureScreenshot(webDriver, testObject, "testAppend");

                        // Assert screenshot was NOT successful
                        Assert.assertFalse(isSuccess, "Expected Screenshot to NOT be successful.");
                } finally {
                        webDriver.quit();
                }
        }

        /**
         * Test capture screenshot custom directory file name.
         */
        @Test(groups = TestCategories.SELENIUM)
        public void testCaptureScreenshotCustomDirectoryFileName() {
                setUp();
                try {
                        FileLogger fileLogger = (FileLogger) this.getTestObject().getLogger();
                        SeleniumTestObject testObject = new SeleniumTestObject(webDriver, fileLogger,
                                        this.getTestObject().getFullyQualifiedTestName());
                        this.setTestObject(testObject);

                        // Open Google and take a screenshot
                        webDriver.navigate().to("http://www.google.com");
                        String dateTime = DateTimeFormatter.ofPattern("uuuu-MM-dd-HH-mm-ss-SSSS", Locale.getDefault())
                                        .format(LocalDateTime.now(Clock.systemUTC()));
                        String filePath = SeleniumUtilities.captureScreenshot(webDriver, testObject,
                                        fileLogger.getDirectory(),
                                        StringProcessor.safeFormatter("%s - %s", "TestCustomName", dateTime));

                        Assert.assertEquals(filePath,
                                        Paths.get(StringProcessor.safeFormatter("%s%s - %s%s",
                                            fileLogger.getDirectory(), File.separator + "TestCustomName", dateTime,
                                            ".png")).normalize().toString());
                        Assert.assertTrue(new File(filePath).exists(),
                                        "Checking that screenshot file exists at expected path.");
                } finally {
                        webDriver.quit();
                }
        }

        /**
         * Test save page source no append.
         */
        @Test(groups = TestCategories.SELENIUM)
        public void testSavePageSourceNoAppend() {
                setUp();
                try {
                        FileLogger fileLogger = (FileLogger) this.getTestObject().getLogger();
                        SeleniumTestObject testObject = new SeleniumTestObject(webDriver, fileLogger,
                                        this.getTestObject().getFullyQualifiedTestName());
                        this.setTestObject(testObject);

                        // Open Google and take a screenshot
                        webDriver.navigate().to("http://www.google.com");
                        boolean isSuccess = SeleniumUtilities.savePageSource(webDriver, testObject);

                        // Assert screenshot was successful
                        Assert.assertTrue(isSuccess, "Expected Screenshot to be successful.");
                        String[] arrayOfAssociatedFiles = testObject.getArrayOfAssociatedFiles();
                        Assert.assertTrue(
                                        new File((arrayOfAssociatedFiles[arrayOfAssociatedFiles.length - 1])).exists(),
                                        "Checking that expected file path exists");
                } finally {
                        webDriver.quit();
                }
        }

        /**
         * Test save page source append.
         */
        @Test(groups = TestCategories.SELENIUM)
        public void testSavePageSourceAppend() {
                setUp();
                try {
                        FileLogger fileLogger = (FileLogger) this.getTestObject().getLogger();
                        SeleniumTestObject testObject = new SeleniumTestObject(webDriver, fileLogger,
                                        this.getTestObject().getFullyQualifiedTestName());
                        this.setTestObject(testObject);

                        // Open Google and take a screenshot
                        webDriver.navigate().to("http://www.google.com");
                        final String testAppend = "testAppend";
                        boolean isSuccess = SeleniumUtilities.savePageSource(webDriver, testObject, testAppend);

                        // Assert screenshot was successful
                        Assert.assertTrue(isSuccess, "Expected Screenshot to be successful.");
                        String[] arrayOfAssociatedFiles = testObject.getArrayOfAssociatedFiles();
                        Assert.assertTrue(
                                        new File((arrayOfAssociatedFiles[arrayOfAssociatedFiles.length - 1])).exists(),
                                        "Checking that expected file path exists");
                        Assert.assertTrue(
                                        new File((arrayOfAssociatedFiles[arrayOfAssociatedFiles.length - 1])).getName()
                                                        .contains(testAppend),
                                        "Checking that appended value was added to file");
                } finally {
                        webDriver.quit();
                }
        }

        /**
         * Test save page source custom directory file name.
         */
        @Test(groups = TestCategories.SELENIUM)
        public void testSavePageSourceCustomDirectoryFileName() {
                setUp();
                try {
                        FileLogger fileLogger = (FileLogger) this.getTestObject().getLogger();
                        SeleniumTestObject testObject = new SeleniumTestObject(webDriver, fileLogger,
                                        this.getTestObject().getFullyQualifiedTestName());
                        this.setTestObject(testObject);

                        // Open Google and take a screenshot
                        webDriver.navigate().to("http://www.google.com");
                        String dateTime = DateTimeFormatter.ofPattern("uuuu-MM-dd-HH-mm-ss-SSSS", Locale.getDefault())
                                        .format(LocalDateTime.now(Clock.systemUTC()));
                        String filePath = SeleniumUtilities.savePageSource(webDriver, testObject,
                                        fileLogger.getDirectory(),
                                        StringProcessor.safeFormatter("%s - %s", "TestCustomName", dateTime));

                        // Assert File Path returned from Screenshot is the same as expected file path.
                        Assert.assertEquals(filePath,
                                        Paths.get(StringProcessor.safeFormatter("%s%s - %s%s",
                                            fileLogger.getDirectory(), File.separator + "TestCustomName", dateTime,
                                            ".txt")).normalize().toString());
                        Assert.assertTrue(new File(filePath).exists(),
                                        "Checking that page source file exists at expected path.");
                        String[] arrayOfAssociatedFiles = testObject.getArrayOfAssociatedFiles();
                        Assert.assertTrue(
                                        new File((arrayOfAssociatedFiles[arrayOfAssociatedFiles.length - 1])).exists(),
                                        "Checking that expected file path exists");
                } finally {
                        webDriver.quit();
                }
        }

        /**
         * Test save page source console logger.
         */
        @Test(groups = TestCategories.SELENIUM)
        public void testSavePageSourceConsoleLogger() {
                setUp();
                try {
                        ConsoleLogger consoleLogger = new ConsoleLogger();
                        SeleniumTestObject testObject = new SeleniumTestObject(webDriver, consoleLogger,
                                        this.getTestObject().getFullyQualifiedTestName());
                        this.setTestObject(testObject);

                        // Open Google and take a screenshot
                        webDriver.navigate().to("http://www.google.com");
                        boolean isSuccess = SeleniumUtilities.savePageSource(webDriver, testObject);

                        // Assert screenshot was successful
                        Assert.assertTrue(isSuccess, "Expected Screenshot to be successful.");
                        String[] arrayOfAssociatedFiles = testObject.getArrayOfAssociatedFiles();
                        Assert.assertTrue(
                                        new File((arrayOfAssociatedFiles[arrayOfAssociatedFiles.length - 1])).exists(),
                                        "Checking that expected file path exists");
                } finally {
                        webDriver.quit();
                }
        }

        /**
         * Test web element to web driver.
         */
        @Test(groups = TestCategories.SELENIUM)
        public void testWebElementToWebDriver() {
                setUp();
                try {
                        ConsoleLogger consoleLogger = new ConsoleLogger();
                        SeleniumTestObject testObject = new SeleniumTestObject(webDriver, consoleLogger,
                            this.getTestObject().getFullyQualifiedTestName());
                        this.setTestObject(testObject);

                        // Open Google and take a screenshot
                        webDriver.navigate().to("http://www.google.com");
                        UIWaitFactory.getWaitDriver(webDriver).waitForPageLoad();
                        WebElement input = webDriver.findElement(By.tagName("div"));
                        WebDriver webElementToWebDriver = SeleniumUtilities.webElementToWebDriver(input);
                        Assert.assertNotNull(webElementToWebDriver, "Expected extracted web driver to not be null");
                } finally {
                        webDriver.quit();
                }
        }

        /**
         * Test killing the driver.
         */
        @Test(groups = TestCategories.SELENIUM)
        public void testKillDriver() {
                setUp();
                try {
                        ConsoleLogger consoleLogger = new ConsoleLogger();
                        SeleniumTestObject testObject = new SeleniumTestObject(webDriver, consoleLogger,
                                        this.getTestObject().getFullyQualifiedTestName());
                        this.setTestObject(testObject);

                        // Open Google then Kill the Driver
                        webDriver.navigate().to("http://www.google.com");
                        SeleniumUtilities.killDriver(webDriver);

                        // Assert that the Session ID is null in the Selenium Driver
                        Assert.assertNull(((RemoteWebDriver) webDriver).getSessionId(),
                                        "Expected Selenium Driver Session ID to be null.");
                } finally {
                        webDriver.quit();
                }
        }
}