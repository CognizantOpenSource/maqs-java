/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.logging;

/**
 * The Html File Logger interface class.
 */
public interface IHtmlFileLogger extends AutoCloseable {

  /**
   * Logs a message to the log file.
   *
   * @param message the string message to be logged
   * @param args the object in addition to the string message
   */
  void logMessage(String message, Object... args);

  /**
   * Logs a message to the log file.
   *
   * @param messageType the message type that is being logged
   * @param message the string message to be logged
   * @param args the object in addition to the string message
   */
  void logMessage(MessageType messageType, String message, Object... args);

  /**
   * Close the class and HTML file.
   */
  void close();
}
