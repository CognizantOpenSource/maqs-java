/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium.baseSeleniumTest;

import com.magenic.jmaqs.utilities.logging.FileLogger;
import com.magenic.jmaqs.utilities.logging.Logger;
import com.magenic.jmaqs.utilities.logging.LoggingConfig;
import com.magenic.jmaqs.utilities.logging.MessageType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Static class for the selenium utilities, ported from MAQS.
 */
public class SeleniumUtilities {

  /**
   * To capture a screenshot during execution.
   * 
   * @param webDriver
   *          The WebDriver
   * @param log
   *          The logger being used
   * @param appendName
   *          Appends a name to the end of a filename
   * @return Boolean if the save of the image was successful
   */
  public static boolean captureScreenshot(WebDriver webDriver, Logger log, String appendName) {
    try {
      String path = "";

      // Check if we are using a file logger
      if (!(log instanceof FileLogger)) {
        // Since this is not a file logger we will need to use a generic
        // file name
        path = captureScreenshot(webDriver, LoggingConfig.getLogDirectory(),
            "ScreenCap" + appendName);
      } else {
        // Calculate the file name
        String fullpath = ((FileLogger) log).getFilePath();
        String directory = new File(fullpath).getParent();
        String fileNameWithoutExtension = new File(fullpath).getName();

        path = captureScreenshot(webDriver, directory, fileNameWithoutExtension);
      }

      log.logMessage(MessageType.INFORMATION, "Screenshot saved: " + path);
      return true;
    } catch (Exception exception) {
      log.logMessage(MessageType.ERROR, "Screenshot error: %s", exception.toString());
      return false;
    }
  }

  /**
   * To capture a screenshot during execution.
   * 
   * @param webDriver
   *          The WebDriver
   * @param directory
   *          The directory file path
   * @param fileNameWithoutExtension
   *          Filename without extension
   * @return Path to the log file
   * @throws IOException
   *           There was a problem creating the screen shot
   */
  public static String captureScreenshot(WebDriver webDriver, String directory,
      String fileNameWithoutExtension) throws IOException {
    File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);

    // Make sure the directory exists
    File folder = new File(directory);
    if (!folder.isDirectory()) {
      folder.mkdir();
    }

    // Calculate the file name
    String path = Paths.get(directory, fileNameWithoutExtension + ".png").toString();

    // Save the screenshot
    FileUtils.copyFile(scrFile, new File(path));

    return path;
  }

  /**
   * Get the web driver from a web element.
   * 
   * @param webElement
   *          The web element
   * @return The web driver
   */
  public static WebDriver webElementToWebDriver(WebElement webElement) {

    WebDriver driver = null;

    driver = ((WrapsDriver) webElement).getWrappedDriver();

    // If this an even firing wrapper get the base wrapper
    if (driver instanceof EventFiringWebDriver) {
      return ((EventFiringWebDriver) driver).getWrappedDriver();
    }

    return driver;
  }

}
