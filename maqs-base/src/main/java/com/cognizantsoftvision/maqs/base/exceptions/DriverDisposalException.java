/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.base.exceptions;

/**
 * The type Driver disposal exception.
 */
public class DriverDisposalException extends RuntimeException {

  /**
   * Instantiates a new Driver disposal exception.
   *
   * @param message   the message
   * @param exception the exception
   */
  public DriverDisposalException(String message, Exception exception) {
    super(message, exception);
  }
}
