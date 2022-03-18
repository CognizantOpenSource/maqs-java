/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.base.exceptions;

public class MAQSRuntimeException extends RuntimeException {

  public MAQSRuntimeException(String message, Exception exception) {
    super(message, exception);
  }

  public MAQSRuntimeException(String message) {
    super(message);
  }
}

