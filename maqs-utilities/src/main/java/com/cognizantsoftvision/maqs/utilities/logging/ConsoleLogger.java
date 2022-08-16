/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.logging;

import com.cognizantsoftvision.maqs.utilities.helper.StringProcessor;

/**
 * The Console Logger class.
 * Helper class for logging to the console.
 */
public class ConsoleLogger extends Logger {
  /**
   * Initializes a new instance of the ConsoleLogger class.
   */
  public ConsoleLogger() {
    super(MessageType.INFORMATION);
  }

  /**
   * Initializes a new instance of the ConsoleLogger class.
   *
   * @param level
   *          The logging level.
   */
  public ConsoleLogger(MessageType level) {
    super(level);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MessageType getMessageType() {
    return this.getLoggingLevel();
  }

  /**
   * Write the formatted message (one line) to the console as a generic message.
   * 
   * @param message
   *          The message text
   * @param args
   *          String format arguments
   */
  @Override
  public void logMessage(String message, Object... args) {
    this.writeLine(message, args);
  }

  /**
   * Write the formatted message (one line) to the console as the specified type.
   * 
   * @param messageType
   *          The type of message
   * @param message
   *          The message text
   * @param args
   *          String format arguments
   * 
   */
  @Override
  public void logMessage(MessageType messageType, String message, Object... args) {
    this.writeLine(messageType, message, args);
  }

  /**
   * Write the formatted message to the console as a generic message.
   * 
   * @param message
   *          The message text
   * @param args
   *          String format arguments
   */
  public void write(String message, Object... args) {
    this.writeToConsole(MessageType.INFORMATION, false, message, args);
  }

  /**
   * Write the formatted message to the console as the given message type.
   * 
   * @param type
   *          The type of message
   * @param message
   *          The message text
   * @param args
   *          Message string format arguments
   */
  public void write(MessageType type, String message, Object... args) {
    this.writeToConsole(type, false, message, args);
  }

  /**
   * Write the formatted message followed by a line break to the console as a generic message.
   * 
   * @param message
   *          The message text
   * @param args
   *          String format arguments
   */
  public void writeLine(String message, Object... args) {
    this.writeToConsole(MessageType.INFORMATION, true, message, args);
  }

  /**
   * Write the formatted message followed by a line break to the console as the given message type.
   * 
   * @param type
   *          The type of message
   * @param message
   *          The message text
   * @param args
   *          Message string format arguments
   */
  public void writeLine(MessageType type, String message, Object... args) {
    this.writeToConsole(type, true, message, args);
  }

  /**
   * write the message to the console.
   * 
   * 
   * @param type
   *          The type of message
   * @param line
   *          Is this a write-line command, else it is just to write
   * @param message
   *          The log message
   * @param args
   *          Message string format arguments
   */
  private void writeToConsole(MessageType type, boolean line, String message, Object... args) {
    // Just return if there is no message
    if (message == null || message.isEmpty() || !this.shouldMessageBeLogged(type)) {
      return;
    }

    String result = StringProcessor.safeFormatter(message, args);
    try {
      // If this a write-line command
      if (line) {
        System.out.println(result);
      } else {
        System.out.print(result);
      }
    } catch (Exception e) {
      System.out.println(StringProcessor.safeFormatter("Failed to write to the console because: %s",
          e.getMessage()));
    }
  }
}
