/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.base;

import com.cognizantsoftvision.maqs.utilities.logging.ILogger;
import java.util.function.Supplier;

/**
 * The type Driver manager.
 * @param <T>  Manager of type T
 */
public abstract class DriverManager<T> implements IDriverManager<T> {

  /**
   * Base Test Object.
   */
  private final ITestObject testObject;

  /**
   * The Base driver.
   */
  protected T baseDriver;

  /**
   * The Get driver.
   */
  protected Supplier<T> getDriverSupplier;

  /**
   * Instantiates a new Driver manager.
   *
   * @param getDriverFunction driver function supplier
   * @param testObject    the base test object
   */
  protected DriverManager(Supplier<T> getDriverFunction, ITestObject testObject) {
    this.testObject = testObject;
    this.getDriverSupplier = getDriverFunction;
  }

  /** {@inheritdoc} */
  public T getBaseDriver() {
    return baseDriver;
  }

  /** {@inheritdoc} */
  public void setBaseDriver(T baseDriver) {
    this.baseDriver = baseDriver;
  }

  /** {@inheritdoc} */
  public boolean isDriverInitialized() {
    return this.baseDriver != null;
  }

  /** {@inheritdoc} */
  public ILogger getLogger() {
    return this.testObject.getLogger();
  }

  /**
   * Get base object.
   *
   * @return the object
   */
  protected T getBase() {
    if (this.baseDriver == null) {
      this.baseDriver = this.getDriverSupplier.get();
    }

    return this.baseDriver;
  }

  /**
   * Gets test object.
   *
   * @return the test object
   */
  public ITestObject getTestObject() {
    return testObject;
  }
}
