/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.utilities.logging;

/**
 * The type of message.
 */
public enum MessageType {
  /**
   * Suspended message.
   */
  SUSPENDED,

  /**
   * Error message.
   */
  ERROR,

  /**
   * Warning message.
   */
  WARNING,

  /**
   * Success message.
   */
  SUCCESS,

  /**
   * Informational message - Our default message type.
   */
  INFORMATION,

  /**
   * Generic message.
   */
  GENERIC,

  /**
   * Verbose message.
   */
  VERBOSE
}
