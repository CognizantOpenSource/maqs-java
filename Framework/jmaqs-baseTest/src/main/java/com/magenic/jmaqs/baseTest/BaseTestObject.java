/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.baseTest;

import com.magenic.jmaqs.utilities.logging.Logger;

/**
 * The TestObject class.
 */
public class BaseTestObject {
  /**
   * The Logger object.
   */
  protected Logger log;

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
  public BaseTestObject(String fullyQualifiedTestName) {
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
  public BaseTestObject(String fullyQualifiedTestName, Logger logger) {
    this.testName = fullyQualifiedTestName;
    this.log = logger;
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
  public Logger getLog() {
    return this.log;
  }

  /**
   * Set the Logger for the TestObject.
   * 
   * @param logger
   *          The Logger object
   */
  public void setLog(Logger logger) {
    this.log = logger;
  }
}