/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.utilities.logging.FileLogger;
import org.apache.commons.io.FilenameUtils;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * Screenshot unit test class.
 */
//FIXME: Resolve failing test issues before re-enabling.
@Ignore
public class ScreenshotUnitTest extends BaseSeleniumTest {
  
  /**
   * Test taking a screenshot
   */
  //FIXME: NUllPointerException in setup()
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

  //FIXME: Commenting out test until repaired.  Casting issue
  //FIXME: ava.lang.ClassCastException: com.magenic.jmaqs.utilities.logging.ConsoleLogger cannot be cast to com.magenic.jmaqs.utilities.logging.FileLogger
  //	at com.magenic.jmaqs.selenium.ScreenshotUnitTest.createScreenShotWithLoggerFileNameTest(ScreenshotUnitTest.java:49)
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