/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.logging;

/**
 * The Html File Logger interface class.
 */
public interface IHtmlFileLogger extends AutoCloseable {

  /**
   * Close the class and HTML file.
   */
  void close();
}
