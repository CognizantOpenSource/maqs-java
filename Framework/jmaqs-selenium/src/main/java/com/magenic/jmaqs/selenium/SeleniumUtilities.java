/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.utilities.helper.StringProcessor;
import com.magenic.jmaqs.utilities.logging.FileLogger;
import com.magenic.jmaqs.utilities.logging.Logger;
import com.magenic.jmaqs.utilities.logging.LoggingConfig;
import com.magenic.jmaqs.utilities.logging.MessageType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.MessageFormat;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Static class for the selenium utilities, ported from MAQS.
 */
public class SeleniumUtilities {

  /**
   * Default Date Time Format for appending to files.
   */
  private static final String DEFAULT_DATE_TIME_FORMAT = "uuuu-MM-dd-HH-mm-ss-SSSS";

  private SeleniumUtilities() {
  }

  /**
   * Capture screenshot boolean.
   *
   * @param webDriver  the web driver
   * @param testObject the test object
   * @return the boolean
   */
  public static boolean captureScreenshot(WebDriver webDriver, SeleniumTestObject testObject) {
    return captureScreenshot(webDriver, testObject, "");
  }

  /**
   * To capture a screenshot during execution.
   *
   * @param webDriver  The WebDriver
   * @param testObject the test object
   * @param appendName Appends a name to the end of a filename
   * @return Boolean if the save of the image was successful
   */
  public static boolean captureScreenshot(WebDriver webDriver, SeleniumTestObject testObject,
      String appendName) {
    try {
      // Check if we are using a file logger. If not, return false.
      if (!(testObject.getLog() instanceof FileLogger)) {
        return false;
      }

      // Calculate the file name with Date Time Stamp
      String directory = ((FileLogger) testObject.getLog()).getDirectory();
      String fileNameWithoutExtension = StringProcessor
          .safeFormatter("%s - %s%s", testObject.getFullyQualifiedTestName(),
              DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT, Locale.getDefault())
                  .format(LocalDateTime.now(Clock.systemUTC())), appendName);
      captureScreenshot(webDriver, testObject, directory, fileNameWithoutExtension);

      testObject.getLog().logMessage(MessageType.INFORMATION, "Screenshot saved.");
      return true;
    } catch (Exception exception) {
      testObject.getLog().logMessage(MessageType.ERROR,
          String.format("Screenshot error: %s", exception.getMessage()));
      return false;
    }
  }

  /**
   * To capture a screenshot during execution.
   *
   * @param webDriver                The WebDriver
   * @param testObject               the test object
   * @param directory                The directory file path
   * @param fileNameWithoutExtension Filename without extension
   * @return Path to the log file
   * @throws IOException There was a problem creating the screen shot
   */
  public static String captureScreenshot(WebDriver webDriver, SeleniumTestObject testObject,
      String directory, String fileNameWithoutExtension) {
    File tempFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
    String path = Paths.get(directory, fileNameWithoutExtension + ".png").normalize().toString();

    // Make sure the directory exists
    try {
      Path tempPath = new File(directory).toPath();
      if (!tempPath.toFile().isDirectory()) {
        Files.createDirectories(tempPath);
      }
    } catch (IOException exception) {
      testObject.getLog()
          .logMessage(MessageType.ERROR, "Failed to create directories: " + exception.getMessage());
    }

    // Try to copy the temporary file to desired file path
    try {
      Files.copy(tempFile.toPath(), new File(path).toPath(), StandardCopyOption.COPY_ATTRIBUTES);
    } catch (IOException exception) {
      testObject.getLog().logMessage(MessageType.ERROR,
          String.format("Screenshot error: %s", exception.getMessage()));
    }

    testObject.addAssociatedFile(path);
    return path;
  }

  /**
   * To capture a page source during execution.
   * Default parameter appendName of empty string.
   *
   * @param webDriver  the web driver
   * @param testObject The Appium Test Object
   * @return True if saving page source is successful, otherwise false
   */
  public static boolean savePageSource(WebDriver webDriver, SeleniumTestObject testObject) {
    return savePageSource(webDriver, testObject, "");
  }

  /**
   * To capture a page source during execution.
   *
   * @param webDriver  the web driver
   * @param testObject The Appium Test Object
   * @param appendName Appends a name to the end of a filename
   * @return True if saving page source is successful, otherwise false
   */
  public static boolean savePageSource(WebDriver webDriver, SeleniumTestObject testObject,
      String appendName) {
    try {
      String path = "";

      // Check if we are using a file logger.
      if (!(testObject.getLog() instanceof FileLogger)) {
        // Since this is not a file logger we will need to use a generic file name
        path = savePageSource(webDriver, testObject, LoggingConfig.getLogDirectory(),
            StringProcessor.safeFormatter("%s - %s%s", "PageSource",
                DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT, Locale.getDefault())
                    .format(LocalDateTime.now(Clock.systemUTC())), appendName));
      } else {
        // Calculate the file name
        String directory = ((FileLogger) testObject.getLog()).getDirectory();
        String fileNameWithoutExtension = StringProcessor
            .safeFormatter("%s - %s%s", testObject.getFullyQualifiedTestName() + "_PS",
                DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT, Locale.getDefault())
                    .format(LocalDateTime.now(Clock.systemUTC())), appendName);

        path = savePageSource(webDriver, testObject, directory, fileNameWithoutExtension);
      }

      testObject.getLog().logMessage(MessageType.INFORMATION, "Page Source saved: " + path);
      return true;
    } catch (Exception exception) {
      testObject.getLog().logMessage(MessageType.ERROR,
          String.format("Page Source error: %s", exception.getMessage()));
      return false;
    }
  }

  /**
   * To capture Page Source during execution.
   *
   * @param webDriver                the web driver
   * @param testObject               The Appium Test Object
   * @param directory                The directory file path
   * @param fileNameWithoutExtension File Name Without Extension
   * @return Path to the log file
   */
  public static String savePageSource(WebDriver webDriver, SeleniumTestObject testObject,
      String directory, String fileNameWithoutExtension) {
    // Save the current page source into a string
    String pageSource = webDriver.getPageSource();

    // Make sure the directory exists
    try {
      Path path = new File(directory).toPath();
      if (!path.toFile().isDirectory()) {
        Files.createDirectories(path);
      }
    } catch (IOException exception) {
      testObject.getLog()
          .logMessage(MessageType.ERROR, "Failed to create directories: " + exception.getMessage());
    }

    // Calculate the file name
    String path = Paths.get(directory, fileNameWithoutExtension + ".txt").normalize().toString();

    try (FileWriter writer = new FileWriter(path, false)) {
      writer.write(pageSource);

      // Flush File Writer
      writer.flush();
    } catch (IOException exception) {
      testObject.getLog().logMessage(MessageType.ERROR, "Failed to start File Writer.");
    }

    testObject.addAssociatedFile(path);
    return path;
  }

  /**
   * Get the web driver from a web element.
   *
   * @param webElement The web element
   * @return The web driver
   */
  public static WebDriver webElementToWebDriver(WebElement webElement) {
    WebDriver driver;
    driver = ((RemoteWebElement) webElement).getWrappedDriver();

    // If this an even firing wrapper get the base wrapper
    if (driver instanceof EventFiringWebDriver) {
      return ((EventFiringWebDriver) driver).getWrappedDriver();
    }

    return driver;
  }

  /**
   * Checks if directory exists, creating one if not.
   *
   * @param directory The directory path
   */
  private static void checkDirectory(String directory) {
    // Make sure the directory exists
    File folder = new File(directory);
    if (!folder.isDirectory()) {
      folder.mkdir();
    }
  }

  /**
   * Make sure the driver is shut down.
   *
   * @param webDriver the web driver
   */
  public static void killDriver(WebDriver webDriver) {
    try {
      webDriver.close();
    } finally {
      webDriver.quit();
    }
  }
}
