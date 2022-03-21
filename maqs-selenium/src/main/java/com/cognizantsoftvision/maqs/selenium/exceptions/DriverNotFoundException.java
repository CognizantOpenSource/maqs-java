/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium.exceptions;

import com.cognizantsoftvision.maqs.base.exceptions.MAQSRuntimeException;

/**
 * The type Driver not found exception.
 */
public class DriverNotFoundException extends MAQSRuntimeException {
  /**
   * Instantiates a new Driver not found exception.
   *
   * @param message the message
   */
  public DriverNotFoundException(String message, Exception exception) {
    super(message, exception);
  }

  /**
   * Instantiates a new Driver not found exception.
   *
   * @param message the message
   */
  public DriverNotFoundException(String message) {
    super(message);
  }
}
