/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */
package com.magenic.jmaqs.baseTest;

import com.magenic.jmaqs.utilities.logging.Logger;

import java.util.function.Supplier;

/**
 * The type Driver manager.
 */
public abstract class DriverManager implements AutoCloseable {

  private final BaseTestObject baseTestObject;

  /**
   * The Base driver.
   */
  protected Object baseDriver;

  /**
   * The Get driver.
   */
  protected Supplier<Object> getDriver;

  /**
   * Instantiates a new Driver manager.
   *
   * @param baseTestObject the base test object
   */
  public DriverManager(Supplier<Object> getDriverFunction, BaseTestObject baseTestObject) {
    this.baseTestObject = baseTestObject;
    this.getDriver = getDriverFunction;
  }

  /**
   * Gets base driver.
   *
   * @return the base driver
   */
  public Object getBaseDriver() {
    return baseDriver;
  }

  /**
   * Sets base driver.
   *
   * @param baseDriver the base driver
   */
  public void setBaseDriver(Object baseDriver) {
    this.baseDriver = baseDriver;
  }

  /**
   * Is driver initialized boolean.
   *
   * @return the boolean
   */
  public boolean isDriverInitialized() {
    return this.baseDriver != null;
  }

  /**
   * Gets logger.
   *
   * @return the logger
   */
  public Logger getLogger() {
    return this.baseTestObject.getLog();
  }

  /**
   * Get base object.
   *
   * @return the object
   */
  protected Object getBase() {
    if (this.baseDriver == null) {
      this.baseDriver = this.getDriver.get();
    }

    return this.baseDriver;
  }

}
