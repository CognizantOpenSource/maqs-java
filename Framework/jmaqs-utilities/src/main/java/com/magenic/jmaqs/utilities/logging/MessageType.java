/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.utilities.logging;

/**
 * The type of message.
 */
public enum MessageType {
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
   * Informational message.
   */
  INFORMATION,

  /**
   * Generic message - Our default message type.
   */
  GENERIC;
}
