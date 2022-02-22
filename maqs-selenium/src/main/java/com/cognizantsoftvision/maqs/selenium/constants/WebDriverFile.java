/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium.constants;

/**
 * Contains constants for web driver file names.
 */
public enum WebDriverFile {

  /**
   * The Chrome driver file.
   */
  CHROME("chromedriver"),
  /**
   * The Firefox driver file.
   */
  FIREFOX("geckodriver"),
  /**
   * The Internet Explorer driver file.
   */
  IE("IEDriverServer"),
  /**
   * The Edge driver file.
   */
  EDGE("MicrosoftWebDriver");

  /**
   * This driver file.
   */
  private final String fileName;

  WebDriverFile(String file) {
    this.fileName = file;
  }

  /**
   * Gets file name.
   *
   * @return the file name
   */
  public String getFileName() {
    String windowsFileExtension = ".exe";
    if (OperatingSystem.getOperatingSystem() == OperatingSystem.WINDOWS) {
      return fileName + windowsFileExtension;
    } else {
      return fileName;
    }
  }
}