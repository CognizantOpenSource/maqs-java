/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.appium.exceptions;

/**
 * The Appium Config Exception class.
 */
public class AppiumConfigException extends RuntimeException {

  /**
   * Throws an Appium Config Exception if an error occurs.
   * @param e the exception to be thrown
   */
  public AppiumConfigException(Exception e) {
    super(e);
  }
}
