/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.logging;

/**
 * The File Logger interface class.
 */
public interface IFileLogger extends ILogger {

  /**
   * Gets the FilePath value.
   *
   * @return returns the file path
   */
  String getFilePath();

  /**
   * Gets the Directory Path.
   *
   * @return Returns the Directory
   */
  String getDirectory();

  /**
   * Sets the FilePath value.
   *
   * @param path sets the file path
   */
  void setFilePath(String path);

  /**
   * Gets the File Name value.
   *
   * @return Returns the File Name.
   */
  String getFileName();
}
