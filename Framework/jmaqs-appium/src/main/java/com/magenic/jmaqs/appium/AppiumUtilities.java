/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.appium;

import com.google.common.io.Files;
import com.magenic.jmaqs.utilities.logging.FileLogger;
import com.magenic.jmaqs.utilities.logging.MessageType;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class AppiumUtilities {

  private AppiumUtilities() {

  }

    /**
     * To capture a screenshot during execution.
     * Default parameter appendName of empty string.
     *
     * @param appiumDriver
     *            The Appium Driver
     * @param testObject
     *            The Test Object to associate the screenshot.
     * @return    True if the image was saved successfully, otherwise false.
     */
    public static boolean captureScreenshot(AppiumDriver<WebElement> appiumDriver, AppiumTestObject testObject) {
        return captureScreenshot(appiumDriver, testObject, "");
    }

  /**
   * To capture a screenshot during execution.
   *
   * @param appiumDriver
   *            The Appium Driver
   * @param testObject
   *            The Test Object to associate the screenshot.
   * @param appendName
   *            The Name to append
   * @return    True if the image was saved successfully, otherwise false.
   */
  public static boolean captureScreenshot(AppiumDriver<WebElement> appiumDriver, AppiumTestObject testObject, String appendName) {
    try {
      if (!(testObject.getLog() instanceof FileLogger)) {
        return false;
      }

      String fullPath = ((FileLogger)testObject.getLog()).getFilePath();
      String directory = Paths.get(fullPath).normalize().toString();
      String fileNameWithoutExtension = Files.getNameWithoutExtension(fullPath) + appendName;
      captureScreenshot(appiumDriver, testObject, directory, fileNameWithoutExtension);

      testObject.getLog().logMessage(MessageType.INFORMATION, "Screenshot saved.");
      return true;
    } catch (Exception exception) {
      testObject.getLog().logMessage(MessageType.ERROR, String.format("Screenshot error: %s", exception.getMessage()));
      return false;
    }
  }

  /**
   * To capture a screenshot during execution.
   *
   * @param appiumDriver
   *            The Appium Driver
   * @param testObject
   *            The Test Object to associate the screenshot.
   * @param directory
   *            The directory file path
   * @param fileNameWithoutExtension
   *            File Name Without Extension
   */
  public static void captureScreenshot(AppiumDriver<WebElement> appiumDriver, AppiumTestObject testObject, String directory, String fileNameWithoutExtension) {
    File tempFile = appiumDriver.getScreenshotAs(OutputType.FILE);
    String path = Paths.get(directory, fileNameWithoutExtension + ".png").normalize().toString();

    // Try to copy the temporary file to desired file path
    try {
      Files.copy(tempFile, new File(path));
    } catch (IOException exception) {
      testObject.getLog().logMessage(MessageType.ERROR, String.format("Screenshot error: %s", exception.getMessage()));
    }
  }
}
