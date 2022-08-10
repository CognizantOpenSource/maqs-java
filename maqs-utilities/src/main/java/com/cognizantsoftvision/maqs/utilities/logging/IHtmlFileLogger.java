/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.logging;

public interface IHtmlFileLogger extends IFileLogger {

  /**
   * Embed a base 64 image.
   * @param base64String Base 64 image string
   */
  void embedImage(String base64String);
}
