/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Logging class.
 */
public abstract class Logger implements ILogger {

  /**
   * Default date format.
   */
  protected static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

  /**
   * Log Level value area.
   */
  private MessageType logLevel;

  /**
   *  Log Level value save area.
   */
  private MessageType logLevelSaved = MessageType.SUSPENDED;

  /**
   * Initializes a new instance of the Logger class.
   */
  protected Logger() {
    this(MessageType.INFORMATION);
  }

  /**
    * Initializes a new instance of the Logger class.
   *
   * @param level
   *          The logging level.
   */
  protected Logger(MessageType level) {
    this.logLevel = level;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MessageType getLoggingLevel() {
    return this.logLevel;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setLoggingLevel(MessageType level) {
    this.logLevel = level;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void suspendLogging() {
    if (this.logLevel != MessageType.SUSPENDED) {
      this.logLevelSaved = this.logLevel;
      this.logLevel = MessageType.SUSPENDED;
      this.logMessage(MessageType.VERBOSE, "Suspending Logging..");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void continueLogging() {
    // Check if the logging was suspended
    if (this.logLevelSaved != MessageType.SUSPENDED) {
      // Return to the log level at the suspension of logging
      this.logLevel = this.logLevelSaved;
    }

    this.logLevelSaved = MessageType.SUSPENDED;
    this.logMessage(MessageType.VERBOSE, "Logging Continued..");
  }

  /**
   * Determine if the message should be logged.
   * The message should be logged if it's level is greater than or equal to the current logging level.
   *
   * @param messageType The type of message being logged.
   * @return True if the message should be logged.
   */
  protected boolean shouldMessageBeLogged(MessageType messageType) {
    // The message should be logged if it's level is less than or equal to the current logging level
    return messageType.getValue() <= this.logLevel.getValue();
  }

  /**
   * {@inheritDoc}
   */
  public abstract MessageType getMessageType();

  /**
   * Get current date time for logging purposes.
   * @return Current data time in UTC format
   */
  public String currentDateTime() {
    SimpleDateFormat format = new SimpleDateFormat(Logger.DEFAULT_DATE_FORMAT);
    return format.format(new Date());
  }
}
