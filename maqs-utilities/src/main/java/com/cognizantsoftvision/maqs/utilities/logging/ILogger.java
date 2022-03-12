/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.logging;

/**
 * The Logging interface class.
 */
public interface ILogger {

  /**
   * Gets the logging level.
   * @return the Message Type
   */
  MessageType getLoggingLevel();

  /**
   * Set the logging level.
   *
   * @param level The logging level.
   */
  void setLoggingLevel(MessageType level);

  /**
   * Suspends logging.
   */
  void suspendLogging();

  /**
   * Continue logging after it was suspended.
   */
  void continueLogging();

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
  void logMessage(MessageType messageType, String message, Object... args);

  /**
   * Write the formatted message (one line) to the console as a generic message.
   *
   * @param message
   *          The message text
   * @param args
   *          String format arguments
   */
  void logMessage(String message, Object... args);
}
