/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.logger;

import com.cognizantsoftvision.maqs.utilities.helper.Config;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import com.cognizantsoftvision.maqs.utilities.helper.exceptions.MaqsLoggingConfigException;
import com.cognizantsoftvision.maqs.utilities.logging.ConsoleLogger;
import com.cognizantsoftvision.maqs.utilities.logging.FileLogger;
import com.cognizantsoftvision.maqs.utilities.logging.HtmlFileLogger;
import com.cognizantsoftvision.maqs.utilities.logging.ILogger;
import com.cognizantsoftvision.maqs.utilities.logging.LoggerFactory;
import com.cognizantsoftvision.maqs.utilities.logging.MessageType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Logger Factory unit test class.
 */
public class LoggerFactoryUnitTest {

  /**
   * Test getting the logger.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void testGetLogger() {
    Assert.assertNotNull(LoggerFactory.getLogger("Test Name"));

    HashMap<String, String> newValueMap = new HashMap<>();
    newValueMap.put("Log", "NO");
    newValueMap.put("LogType", "TXT");
    Config.addGeneralTestSettingValues(newValueMap, true);
    Assert.assertNotNull(LoggerFactory.getLogger("Test Name"));
  }

  /**
   * Test getting the console logger.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void testGetConsoleLogger() {
    Assert.assertNotNull(LoggerFactory.getConsoleLogger());
    ILogger logger = LoggerFactory.getLogger("logName", "CONSOLE", MessageType.WARNING);
    Assert.assertTrue(logger instanceof ConsoleLogger);
  }

  /**
   * Tests getting the file logger.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void testGetFileLogger() {
    List<String> logType = new ArrayList<>();
    logType.add("TXT");
    logType.add("TEXT");

    for (String log : logType) {
      ILogger logger = LoggerFactory.getLogger("logName", log, MessageType.ACTION);
      Assert.assertTrue(logger instanceof FileLogger);
    }
  }

  /**
   * Test getting the HTML file logger.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void testGetHtmlFileLogger() {
    List<String> logType = new ArrayList<>();
    logType.add("HTML");
    logType.add("HTM");

    for (String log : logType) {
      ILogger logger = LoggerFactory.getLogger("logName", log, MessageType.ACTION);
      Assert.assertTrue(logger instanceof HtmlFileLogger);
    }
  }

  /**
   * Test throwing an exception with an unsupported log type.
   */
  @Test(groups = TestCategories.UTILITIES, expectedExceptions = MaqsLoggingConfigException.class)
  public void testGetInvalidLogger() {
    LoggerFactory.getLogger("logName", "TEST", MessageType.ACTION);
  }
}
