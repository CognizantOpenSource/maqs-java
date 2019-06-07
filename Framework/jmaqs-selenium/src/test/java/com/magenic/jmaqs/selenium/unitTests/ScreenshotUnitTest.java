/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium.unitTests;

import com.magenic.jmaqs.selenium.baseSeleniumTest.BaseSeleniumTest;
import com.magenic.jmaqs.selenium.baseSeleniumTest.SeleniumUtilities;
import com.magenic.jmaqs.utilities.logging.FileLogger;
import org.apache.commons.io.FilenameUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * Screenshot unit test class.
 */
public class ScreenshotUnitTest extends BaseSeleniumTest {
  
  /**
   * Test taking a screenshot
   */
  @Test
  public void createScreenShotTest() throws IOException {
    String path = SeleniumUtilities.captureScreenshot(this.getWebDriver(), ".", "screenshotTest");
    File ss = new File(path);
    Assert.assertTrue(ss.exists() && ss.isFile());
    ss.delete();
  }

  /**
   * Test taking a screenshot
   */
  @Test
  public void createScreenShotWithLoggerTest() throws IOException {
    String path = SeleniumUtilities.captureScreenshot(this.getWebDriver(), this.getLogger(), "");
    File ss = new File(path);
    Assert.assertTrue(ss.exists() && ss.isFile());
    ss.delete();
  }

  /**
   * Test taking a screenshot, check file name
   */
  @Test
  public void createScreenShotWithLoggerFileNameTest() throws IOException {
    String path = SeleniumUtilities.captureScreenshot(this.getWebDriver(), this.getLogger(), "");
    String screenshotNameWithoutExtension = FilenameUtils.getBaseName(path);
    String loggerNameWithoutExtension = FilenameUtils.getBaseName(((FileLogger) this.getLogger()).getFilePath());   
    Assert.assertEquals(screenshotNameWithoutExtension, loggerNameWithoutExtension);    
  }
}
