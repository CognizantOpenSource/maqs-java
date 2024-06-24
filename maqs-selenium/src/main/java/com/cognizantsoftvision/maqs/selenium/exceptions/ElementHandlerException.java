/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium.exceptions;

import com.cognizantsoftvision.maqs.base.exceptions.MAQSRuntimeException;

/**
 * The type Element handler exception.
 */
public class ElementHandlerException extends MAQSRuntimeException {

  private static final long serialVersionUID = 1;

  /**
   * Instantiates a new Element handler exception.
   *
   * @param message the message
   */
  public ElementHandlerException(String message) {
    super(message);
  }

  /**
   * Instantiates a new Element handler exception.
   *
   * @param message the message
   * @param exception the exception
   */
  public ElementHandlerException(String message, Exception exception) {
    super(message, exception);
  }
}
