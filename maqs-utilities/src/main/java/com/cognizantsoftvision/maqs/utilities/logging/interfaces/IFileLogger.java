package com.cognizantsoftvision.maqs.utilities.logging.interfaces;

import com.cognizantsoftvision.maqs.utilities.logging.MessageType;

/**
 * The File Logger interface class
 */
public interface IFileLogger extends ILogger{

  /**
   * Gets the FilePath value.
   *
   * @return returns the file path
   */
  String getFilePath();

  /**
   * Gets the Message Type value.
   *
   * @return The Message Type.
   */
  MessageType getMessageType();

  /**
   * Gets the Directory Path.
   *
   * @return Returns the Directory
   */
  String getDirectory();

  /**
   * Sets the FilePath value.
   *
   * @param path sets the file path
   */
  void setFilePath(String path);

  /**
   * Gets the File Name value.
   *
   * @return Returns the File Name.
   */
  String getFileName();

  /**
   * Logs the message.
   * @param message The message text.
   * @param args String format arguments
   */
  void logMessage(String message, Object... args);

  /**
   * Logs the message.
   * @param messageType The type of message
   * @param message The message text
   * @param args String format arguments
   */
  void logMessage(MessageType messageType, String message, Object... args);
  }
