//--------------------------------------------------
// <copyright file="LoggingConfig.java" company="Magenic">
//  Copyright 2016 Magenic, All rights Reserved
// </copyright>
// <summary>Logging related configuration</summary>
//--------------------------------------------------

package com.magenic.jmaqs.utilities.Logging;

import java.io.File;

import com.magenic.jmaqs.utilities.Helper.Config;
import com.magenic.jmaqs.utilities.Helper.StringProcessor;

public class LoggingConfig
{
	/**
	 * Get our logging state - Yes, no or on failure
	 * @return The log enabled state
	 */
    public static LoggingEnabled getLoggingEnabledSetting()
    {
    	switch (Config.getValue("Log", "NO").toUpperCase())
        {
    		case "YES":
    			return LoggingEnabled.YES;
            case "ONFAIL":
                return LoggingEnabled.ONFAIL;
            case "NO":
                return LoggingEnabled.NO;
            default:
                throw new IllegalArgumentException(StringProcessor.safeFormatter("Log value %s is not a valid option", Config.getValue("Log", "NO")));
        }
     }

    /**
     * Get the logger
     * @param fileName 		File name to use for the log
     * @return The logger
     */
     public static Logger getLogger(String fileName)
     {
         /**
          * Disable logging means we just send any logged messages to the console
          */
    	 if (getLoggingEnabledSetting() == LoggingEnabled.NO)
         {
        	 return new ConsoleLogger();
         }

         String logDirectory = getLogDirectory();

         switch (Config.getValue("LogType", "CONSOLE").toUpperCase())
         {
         	case "CONSOLE":
         		return new ConsoleLogger();
            case "TXT":
                return new FileLogger(false, logDirectory, fileName);
            default:
                throw new IllegalArgumentException(StringProcessor.safeFormatter("Log type % is not a valid option", Config.getValue("LogType", "CONSOLE")));
         }
      }

     /**
      * Gets the File Directory to store log files
      * @return String of file path
      */
      public static String getLogDirectory()
      {
    	  String path = new File("").getAbsolutePath().concat("\\Logs");
    	  return Config.getValue("FileLoggerPath", path);
      }
}