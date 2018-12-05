/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.utilities.unitTests;

import com.magenic.jmaqs.utilities.helper.StringProcessor;
import com.magenic.jmaqs.utilities.logging.FileLogger;
import com.magenic.jmaqs.utilities.logging.Logger;
import com.magenic.jmaqs.utilities.logging.LoggingConfig;
import com.magenic.jmaqs.utilities.logging.MessageType;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Unit test class for FileLogger.java
 */
public class FileLoggerUnitTest {
  @DataProvider(name = "logLevels")
  public static Object[][] data() {
    return new Object[][] {
      { "VERBOSE", new HashMap<String, Boolean>() {{
          put("VERBOSE", true); put("INFORMATION", true); put("GENERIC", true);
          put("SUCCESS", true); put("WARNING", true); put("ERROR", true);
        }}
      },
      { "INFORMATION", new HashMap<String, Boolean>() {{
          put("VERBOSE", false); put("INFORMATION", true); put("GENERIC", true);
          put("SUCCESS", true); put("WARNING", true); put("ERROR", true);
        }}
      },
      { "GENERIC", new HashMap<String, Boolean>() {{
          put("VERBOSE", false); put("INFORMATION", false); put("GENERIC", true);
          put("SUCCESS", true); put("WARNING", true); put("ERROR", true);
        }}
      },
      { "SUCCESS", new HashMap<String, Boolean>() {{
          put("VERBOSE", false); put("INFORMATION", false); put("GENERIC", false);
          put("SUCCESS", true); put("WARNING", true); put("ERROR", true);
        }}
      },
      { "WARNING", new HashMap<String, Boolean>() {{
          put("VERBOSE", false); put("INFORMATION", false); put("GENERIC", false);
          put("SUCCESS", false); put("WARNING", true); put("ERROR", true);
        }}
      },
      { "ERROR", new HashMap<String, Boolean>() {{
          put("VERBOSE", false); put("INFORMATION", false); put("GENERIC", false);
          put("SUCCESS", false); put("WARNING", false); put("ERROR", true);
        }}
      },
      { "SUSPENDED", new HashMap<String, Boolean>() {{
          put("VERBOSE", false); put("INFORMATION", false); put("GENERIC", false);
          put("SUCCESS", false); put("WARNING", false); put("ERROR", false);
        }}
      }
    };
  }

  @Test(dataProvider = "logLevels")
  public void testHierarchicalTxtFileLogger(String logLevel, HashMap<String, Boolean> levels) {
    FileLogger logger = new FileLogger(true, LoggingConfig.getLogDirectory(), this.getFileName("TestHierarchicalTxtFileLogger" + logLevel, "txt"), MessageType.GENERIC);
    this.testHierarchicalLogging(logger, logger.getFilePath(), logLevel, levels);

    File file = new File(logger.getFilePath());
    file.delete();
  }

  /**
   * Test logging to a new file.
   */
  @Test
  public void fileLoggerNoAppendTest() {
    FileLogger logger = new FileLogger(false, "", "WriteToFileLogger");
    logger.logMessage(MessageType.WARNING, "Hello, this is a test.");
    File file1 = new File(logger.getFilePath());
    file1.delete();
  }

  /**
   * Test logging to an existing file.
   */
  @Test
  public void fileLoggerAppendFileTest() {
    FileLogger logger = new FileLogger(true, "", "WriteToExistingFileLogger");
    logger.logMessage(MessageType.WARNING, "This is a test to write to an existing file.");
    File file1 = new File(logger.getFilePath());
    file1.delete();
  }

  /**
   * Verify hierarchical logging is respected
   *
   * @param logger
   *          The logger we are checking
   * @param filePath
   *          Where the log output can be found
   * @param logLevelText
   *          The type of logging
   * @param levels
   *          What should appear for each level
   */
  private void testHierarchicalLogging(Logger logger, String filePath, String logLevelText, HashMap<String, Boolean> levels) {
    // Create a soft assert
    SoftAssert softAssert = new SoftAssert();

    // Get the log level
    MessageType logLevel = MessageType.valueOf(logLevelText);
    logger.setLoggingLevel(logLevel);

    // Set the logger options to set the log level and add log entries to the file
    logger.logMessage(logLevel, "\nThe Loglevel is set to " + logLevel);

    // Message template
    String logLine = "Test Log item %s";

    // Log the test messages
    logger.logMessage(MessageType.VERBOSE, logLine, MessageType.VERBOSE);
    logger.logMessage(MessageType.INFORMATION, logLine, MessageType.INFORMATION);
    logger.logMessage(MessageType.GENERIC, logLine, MessageType.GENERIC);
    logger.logMessage(MessageType.SUCCESS, logLine, MessageType.SUCCESS);
    logger.logMessage(MessageType.WARNING, logLine, MessageType.WARNING);
    logger.logMessage(MessageType.ERROR, logLine, MessageType.ERROR);

    // Give the write time
    try {
      Thread.sleep(250);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Get the file content
    String logContents = this.readTextFile(filePath);

    // Verify that only the logged messages at the log level or below are logged
    for(HashMap.Entry<String, Boolean> level : levels.entrySet()) {
      if ((level.getKey() != "Row") && (level.getKey() != "LogLevel")) {
        // Verify if the Message Type is found
        boolean logMessageFound = logContents.contains(String.format(logLine, level.getKey()));
        softAssert.assertEquals(Boolean.toString(logMessageFound), level.getValue().toString(), "Looking for " + level.getKey());
      }
    }

    // Set the log level so that the soft asserts log
    logger.setLoggingLevel(MessageType.VERBOSE);

    // Fail test if any soft asserts failed
    softAssert.assertAll();
  }

  /**
   * Read a file and return it as a string
   *
   * @param filePath
   *          The file path to read
   * @return
   *          The contents of the file
   */
  private String readTextFile(String filePath) {
    String text = "";
    try {
      text = new String(Files.readAllBytes(Paths.get(filePath)));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return text;
  }

  /**
   * Get a unique file name
   *
   * @param testName
   *          Prepend text
   * @param extension
   *          The file extension
   * @return
   *          A unique file name
   */
  private String getFileName(String testName, String extension) {
    return StringProcessor.safeFormatter("UtilitiesUnitTesting.{0}-{1}.{2}", testName, UUID.randomUUID(), extension);
  }
}
