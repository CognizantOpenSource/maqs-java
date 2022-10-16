/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.logging;

import com.cognizantsoftvision.maqs.utilities.helper.Config;
import com.cognizantsoftvision.maqs.utilities.helper.exceptions.MaqsLoggingConfigException;

/**
 * The Logger Factory class.
 */
public class LoggerFactory {

  private LoggerFactory() {

  }

   /**
   * Get a new console logger which respects current logging level.
   * @return A console logger
   */
    public static ILogger getConsoleLogger() {
      return new ConsoleLogger(LoggingConfig.getLoggingLevelSetting());
    }

  /**
   * Get a logger.
   * @param logName Log name - gets added as console message or file name
   * @return A logger
   * @throws MaqsLoggingConfigException if an error occurs
   */
    public static ILogger getLogger(String logName) throws MaqsLoggingConfigException {
      String console = "CONSOLE";

      if (LoggingConfig.getLoggingEnabledSetting() == LoggingEnabled.NO) {
        return getLogger(logName, console, MessageType.SUSPENDED);
      }

      return getLogger(logName, Config.getGeneralValue("LogType", console), LoggingConfig.getLoggingLevelSetting());
    }

  /**
   * Get a logger.
   * @param logName Log name - gets added as console message or file name
   * @param logType Type of log - Console, Text or HTML
   * @param loggingLevel Logging level
   * @return A logger
   * @throws MaqsLoggingConfigException if an exception occurs
   */
   public static ILogger getLogger(String logName, String logType, MessageType loggingLevel)
       throws MaqsLoggingConfigException {
     String logDirectory = LoggingConfig.getLogDirectory();

     switch (logType.toUpperCase()) {
       case "CONSOLE":
         return new ConsoleLogger(loggingLevel);
       case "TXT":
       case "TEXT":
         return new FileLogger(logDirectory, logName, loggingLevel);
       case "HTML":
       case "HTM":
         return new HtmlFileLogger(logDirectory, logName, loggingLevel);
       default:
         throw new MaqsLoggingConfigException("Log type '" + logType + "' is not a valid option");
      }
    }
}
