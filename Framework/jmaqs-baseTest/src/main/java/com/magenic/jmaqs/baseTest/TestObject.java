/* 
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.baseTest;

import com.magenic.jmaqs.utilities.logging.Logger;

/**
 * The TestObject class.
 */
public class TestObject {
  /**
   * The Logger object.
   */
  protected Logger logger;

  /**
   * The Fully Qualified Test Name.
   */
  protected String testName;

  /**
   * Initializes a new instance of the TestObject class.
   * 
   * @param fullyQualifiedTestName
   *          The Fully Qualified Test Name
   */
  public TestObject(String fullyQualifiedTestName) {
    this.testName = fullyQualifiedTestName;
  }

  /**
   * Initializes a new instance of the TestObject class.
   * 
   * @param fullyQualifiedTestName
   *          The Fully Qualified Test Name
   * @param logger
   *          The Logger object
   */
  public TestObject(String fullyQualifiedTestName, Logger logger) {
    this.testName = fullyQualifiedTestName;
    this.logger = logger;
  }

  /**
   * Get the Fully Qualified Test Name.
   * 
   * @return The fully qualified test name
   */
  public String getFullyQualifiedTestName() {
    return testName;
  }

  /**
   * Get the Logger.
   * 
   * @return A Logger object
   */
  public Logger getLogger() {
    return this.logger;
  }

  /**
   * Set the Logger for the TestObject.
   * 
   * @param log
   *          The Logger object
   */
  public void setLogger(Logger log) {
    this.logger = log;
  }
}
