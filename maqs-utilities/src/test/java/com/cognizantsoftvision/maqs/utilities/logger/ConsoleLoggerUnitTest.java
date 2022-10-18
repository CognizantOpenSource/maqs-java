/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.logger;

import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import com.cognizantsoftvision.maqs.utilities.logging.ConsoleLogger;
import com.cognizantsoftvision.maqs.utilities.logging.MessageType;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Console Logger unit test class.
 */
public class ConsoleLoggerUnitTest {

  /**
   * Log message to a new console logger
   */
  @Test(groups = TestCategories.UTILITIES)
  public void consoleLoggerLogMessage() {
    ConsoleLogger console = new ConsoleLogger();
    console.logMessage("Test String %s %s", "args1", "args2");
  }

  /**
   * Log message to a new console logger using defined message type
   */
  @Test(groups = TestCategories.UTILITIES)
  public void consoleLoggerLogMessageSelectType() {
    ConsoleLogger console = new ConsoleLogger();
    console.logMessage(MessageType.GENERIC, "Test String %s", "args1");
  }

  /**
   * Log message to a new console logger using defined message type
   */
  @Test
  public void consoleLoggerGetMessageType() {
    ConsoleLogger console = new ConsoleLogger();
    Assert.assertEquals(console.getMessageType(), MessageType.INFORMATION);
  }

  /**
   * Write message to new console logger
   */
  @Test(groups = TestCategories.UTILITIES)
  public void consoleLoggerWriteMessage() {
    ConsoleLogger console = new ConsoleLogger();
    console.write("Test String %s %s", "args1", "args2");
  }

  /**
   * Write message to new console logger using defined message type
   */
  @Test(groups = TestCategories.UTILITIES)
  public void consoleLoggerWriteMessageSelectType() {
    ConsoleLogger console = new ConsoleLogger();
    console.write(MessageType.GENERIC, "TestString %s", "args1");
  }

    /**
     * Write message with new line to new console logger
     */
    @Test(groups = TestCategories.UTILITIES)
    public void consoleLoggerWriteLineMessage() {
        ConsoleLogger console = new ConsoleLogger();
        console.write("Test String %s %s", "args1", "args2");
    }

    /**
     * Write message with new line to new console logger using defined message type
     */
    @Test(groups = TestCategories.UTILITIES)
    public void consoleLoggerWriteMessageLineSelectType() {
        ConsoleLogger console = new ConsoleLogger();
        console.write(MessageType.GENERIC, "TestString %s", "args1");
    }

  @Test(groups = TestCategories.UTILITIES)
  public void consoleLoggerGetLoggingLevel() {
    ConsoleLogger console = new ConsoleLogger();
    Assert.assertEquals(console.getLoggingLevel(), MessageType.INFORMATION);
  }
}
