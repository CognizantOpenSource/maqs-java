/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium.exceptions;

import com.cognizantsoftvision.maqs.base.exceptions.JMAQSRuntimeException;

/**
 * The type Web driver factory exception.
 */
public class WebDriverFactoryException extends JMAQSRuntimeException {

  /**
   * Instantiates a new Web driver factory exception.
   *
   * @param message   the message
   * @param exception the exception
   */
  public WebDriverFactoryException(String message, Exception exception) {
    super(message, exception);
  }
}
