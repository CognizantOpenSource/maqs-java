/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.utilities.unitTests;

import com.magenic.jmaqs.utilities.logging.FileLogger;
import com.magenic.jmaqs.utilities.logging.MessageType;

import java.io.File;

import org.testng.annotations.Test;

/**
 * Unit test class for FileLogger.java
 * 
 * @author BrentS
 *
 */
public class FileLoggerUnitTest {
  /**
   * Test logging to a new file
   */
  @Test
  public void fileLoggerNoAppendTest() {
    FileLogger logger = new FileLogger(false, "", "WriteToFileLogger");
    logger.logMessage(MessageType.WARNING, "Hello, this is a test.");
    File file1 = new File(logger.getFilePath());
    file1.delete();
  }

  /**
   * Test logging to an existing file
   */
  @Test
  public void fileLoggerAppendFileTest() {
    FileLogger logger = new FileLogger(true, "", "WriteToExistingFileLogger");
    logger.logMessage(MessageType.WARNING, "This is a test to write to an existing file.");
    File file1 = new File(logger.getFilePath());
    file1.delete();
  }
}
