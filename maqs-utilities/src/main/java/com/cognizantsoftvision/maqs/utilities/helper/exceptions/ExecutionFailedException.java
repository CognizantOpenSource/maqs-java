/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.helper.exceptions;

public class ExecutionFailedException extends RuntimeException {

  private static final long serialVersionUID = 1;

  /**
   * Instantiates the Execution Failed Exception. This exception is thrown when a
   * function that is invoked fails.
   *
   * @param message   The message for the exception
   * @param exception The exception that was thrown by the failed event
   */
  public ExecutionFailedException(String message, Exception exception) {
    super(message, exception);
  }
}
