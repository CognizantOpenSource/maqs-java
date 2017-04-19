/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.utilities.logging;

import com.magenic.jmaqs.utilities.helper.Config;
import com.magenic.jmaqs.utilities.helper.StringProcessor;

/**
 * Abstract logging interface base class.
 */
public abstract class Logger {
  /**
   * Default date format.
   */
  protected static final String DEFAULT_DATE_FORMAT = "uuuu-MM-dd HH:mm:ss.SSS";

  /**
   * Initializes a new instance of the class.
   */
  public Logger() {

  }

  /**
   * Write the formatted message (one line) to the console as a generic message.
   * 
   * @param messageType
   *          The type of message
   * @param message
   *          The message text
   * @param args
   *          String format arguments
   */
  public abstract void logMessage(MessageType messageType, String message, Object... args);

  /**
   * Write the formatted message (one line) to the console as a generic message.
   * 
   * @param message
   *          The message text
   * @param args
   *          String format arguments
   */
  public abstract void logMessage(String message, Object... args);

  protected String unknownMessageTypeMessage(MessageType type) {
    return StringProcessor.safeFormatter("Unknown MessageType: %s%s%s%s", type.name(),
        Config.NEW_LINE, "Message will be displayed with the MessageType of: ", type.name(),
        MessageType.GENERIC);
  }
}
