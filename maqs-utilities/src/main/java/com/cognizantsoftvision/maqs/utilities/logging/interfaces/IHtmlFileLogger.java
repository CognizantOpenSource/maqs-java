package com.cognizantsoftvision.maqs.utilities.logging.interfaces;

import com.cognizantsoftvision.maqs.utilities.logging.MessageType;

/**
 * The Html File Logger interface class.
 */
public interface IHtmlFileLogger extends AutoCloseable {

  /**
   * Logs a message.
   * @param message the string message to be logged
   * @param args the object in addition to the string message
   */
  void logMessage(String message, Object... args);

  /**
   * Logs a message.
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
