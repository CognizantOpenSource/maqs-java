/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.base.exceptions;

public class JMAQSRuntimeException extends RuntimeException {

  public JMAQSRuntimeException(String message, Exception exception) {
    super(message, exception);
  }

  public JMAQSRuntimeException(String message) {
    super(message);
  }
}

