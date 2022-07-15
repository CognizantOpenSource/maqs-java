/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.logging;

/**
 * The Message Type enum class.
 */
public enum MessageType {

  /**
   * Suspended message.
   */
  SUSPENDED(-1),

  /**
   * Error message.
   */
  ERROR(0),

  /**
   * Warning message.
   */
  WARNING(1),

  /**
   * Success message.
   */
  SUCCESS(2),

  /**
   * Generic message.
   */
  GENERIC(3),

  /**
   * Step Message test engineer would insert.
   */
  STEP(4),

  /**
   * Action Message reflects actions a user would take.
   */
  ACTION(5),

  /**
   * Informational message - Our default message type.
   */
  INFORMATION(6),

  /**
   * Verbose message.
   */
  VERBOSE(7);

  /**
   * The value of current Message Type.
   */
  private final int value;

  /**
   * Initializes a new instance of Message Type.
   *
   * @param value
   *          The value.
   */
  MessageType(int value) {
    this.value = value;
  }

  /**
   * Gets the value of the current Message Type.
   *
   * @return
   *        Value of current Message Type.
   */
  public int getValue() {
    return this.value;
  }
}