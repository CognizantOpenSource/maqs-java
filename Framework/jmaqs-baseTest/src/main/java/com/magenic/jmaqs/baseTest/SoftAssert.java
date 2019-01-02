package com.magenic.jmaqs.baseTest;

import com.magenic.jmaqs.utilities.logging.Logger;

/**
 * SoftAssert class.
 */
public class SoftAssert {
  /**
   * The logger.
   */
  private Logger log;
    
  /**
   * Gets the Logger.
   *
   * @return
   *          The Logger
   */
  protected Logger getLog() {
    return this.log;
  }

  /**
   * Sets the Logger.
   *
   * @param logger
   *                The logger to set.
   */
  private void setLog(Logger logger) {
    this.log = logger;
  }

  /**
   * Initializes a new instance of the SoftAssert class.
   * 
   * @param logger
   *                The logger to use
   */
  public SoftAssert(Logger logger) {
    this.log = logger;  
  }  
}
