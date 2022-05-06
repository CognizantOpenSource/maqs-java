/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.helper.exceptions;

/**
 * The Custom Function Exception class.
 * UThe built-in one does not allow for throwing a generic exception.
 */
public class FunctionException extends RuntimeException {
  private static final long serialVersionUID = 1;

  /**
   * Initializes an instance of the FunctionException class.
   *
   * @param errorMessage The desired custom error message
   * @param err          An inner exception
   */
  public FunctionException(String errorMessage, Throwable err) {
    super(errorMessage, err);
  }
}
