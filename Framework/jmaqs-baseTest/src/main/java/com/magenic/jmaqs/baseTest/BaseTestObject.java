/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.baseTest;

import com.magenic.jmaqs.utilities.logging.Logger;
import com.magenic.jmaqs.utilities.logging.MessageType;
import com.magenic.jmaqs.utilities.performance.PerfTimerCollection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.Attributes;

/**
 * The BaseTestObject class.
 */
public class BaseTestObject implements AutoCloseable {
  /**
   * The Logger.
   */
  private Logger log;

  /**
   * The Performance Timer Collection.
   */
  private PerfTimerCollection perfTimerCollection;

  /**
   * The Soft Assert.
   */
  private SoftAssert softAssert;

  /**
   * Concurrent Hash Map of string key value pairs.
   */
  private ConcurrentHashMap<String, String> values;

  /**
   * Concurrent Hash Map of string key and object value pairs.
   */
  private ConcurrentHashMap<String, Object> objects;

  /**
   * Dictionary of String key and driver value pairs.
   */
  private ManagerDictionary managerStore;

  /**
   * Initializes a new instance of the BaseTestObject class.
   *
   * @param logger                 The test's logger
   * @param softAssert             The test's soft assert
   * @param fullyQualifiedTestName The test's fully qualified test name
   */
  public BaseTestObject(Logger logger, SoftAssert softAssert, String fullyQualifiedTestName) {
    this.log = logger;
    this.softAssert = softAssert;
    this.perfTimerCollection = new PerfTimerCollection(logger, fullyQualifiedTestName);
    this.values = new ConcurrentHashMap<String, String>();
    this.objects = new ConcurrentHashMap<String, Object>();
    this.managerStore = new ManagerDictionary();

    logger.logMessage(MessageType.INFORMATION, "Setup test object for " + fullyQualifiedTestName);
  }

  /**
   * Initializes a new instance of the BaseTestObject class.
   *
   * @param logger                 The test's logger
   * @param fullyQualifiedTestName The test's fully qualified test name
   */
  public BaseTestObject(Logger logger, String fullyQualifiedTestName) {
    this.log = logger;
    this.softAssert = new SoftAssert(this.log);
    this.perfTimerCollection = new PerfTimerCollection(logger, fullyQualifiedTestName);
    this.values = new ConcurrentHashMap<String, String>();
    this.objects = new ConcurrentHashMap<String, Object>();
    this.managerStore = new ManagerDictionary();

    logger.logMessage(MessageType.INFORMATION, "Setup test object for " + fullyQualifiedTestName);
  }

  /**
   * Initializes a new instance of the BaseTestObject class.
   *
   * @param baseTestObject An existing base test object
   */
  public BaseTestObject(BaseTestObject baseTestObject) {
    this.log = baseTestObject.getLog();
    this.softAssert = baseTestObject.getSoftAssert();
    this.perfTimerCollection = baseTestObject.getPerfTimerCollection();
    this.values = baseTestObject.getValues();
    this.objects = baseTestObject.getObjects();
    this.managerStore = baseTestObject.getManagerStore();

    baseTestObject.getLog().logMessage(MessageType.INFORMATION, "Setup test object");
  }

  /**
   * Sets a string value, will replace if the key already exists.
   *
   * @param key   The key
   * @param value The value to associate with the key
   */
  public void setValue(String key, String value) {
    if (this.values.containsKey(key)) {
      this.values.replace(key, value);
    } else {
      this.values.put(key, value);
    }
  }

  /**
   * Sets an object value, will replace if the key already exists.
   *
   * @param key   The key
   * @param value The value to associate with the key
   */
  public void setObject(String key, Object value) {
    if (this.objects.containsKey(key)) {
      this.objects.replace(key, value);
    } else {
      this.objects.put(key, value);
    }
  }

  /**
   * Gets the logger.
   *
   * @return The logger
   */
  public Logger getLog() {
    return this.log;
  }

  /**
   * Sets the logger.
   *
   * @param logger The logger to use
   */
  public void setLog(Logger logger) {
    this.log = logger;
  }

  /**
   * Gets the Performance Timer Collection.
   *
   * @return Performance Timer Collection
   */
  public PerfTimerCollection getPerfTimerCollection() {
    return this.perfTimerCollection;
  }

  /**
   * Sets the Performance Timer Collection.
   *
   * @param perfTimerCollection Performance Timer Collection
   */
  public void setPerfTimerCollection(PerfTimerCollection perfTimerCollection) {
    this.perfTimerCollection = perfTimerCollection;
  }

  /**
   * Gets the Soft Assert.
   *
   * @return The Soft Assert
   */
  public SoftAssert getSoftAssert() {
    return this.softAssert;
  }

  /**
   * Sets the Soft Assert.
   *
   * @param softAssert The Soft Assert to use
   */
  public void setSoftAssert(SoftAssert softAssert) {
    this.softAssert = softAssert;
  }

  /**
   * Gets the Concurrent Hash Map of string key value pairs.
   *
   * @return Concurrent Hash Map of string key value pairs
   */
  public ConcurrentHashMap<String, String> getValues() {
    return this.values;
  }

  /**
   * Sets the Concurrent Hash Map of string key and object value pairs.
   *
   * @param values Concurrent Hash Map of string key value pairs to use
   */
  private void setValues(ConcurrentHashMap<String, String> values) {
    this.values = values;
  }

  /**
   * Gets the Concurrent Hash Map of string key and object value pairs.
   *
   * @return Concurrent Hash Map of string key and object value pairs
   */
  public ConcurrentHashMap<String, Object> getObjects() {
    return this.objects;
  }

  /**
   * Sets the Concurrent Hash Map of string key and object value pairs.
   *
   * @param objects Concurrent Hash Map of string key and object value pairs to use
   */
  public void setObjects(ConcurrentHashMap<String, Object> objects) {
    this.objects = objects;
  }

  /**
   * Gets the Concurrent Hash Map of string key and driver value pairs.
   *
   * @return Concurrent Hash Map of string key and driver value pairs
   */
  public ManagerDictionary getManagerStore() {
    return this.managerStore;
  }

  /**
   * Sets the Concurrent Hash Map of string key and driver value pairs.
   *
   * @param managerStore Concurrent Hash Map of string key and driver value pairs to use.
   */
  public void setManagerStore(ManagerDictionary managerStore) {
    this.managerStore = managerStore;
  }

  /**
   * Add driver manager.
   *
   * @param <T>           the type parameter
   * @param driverManager the driver manager
   */
  public <T extends DriverManager> void addDriverManager(T driverManager) {
    this.addDriverManager(driverManager, false);
  }

  /**
   * Add driver manager.
   *
   * @param <T>              the type parameter
   * @param driverManager    the driver manager
   * @param overrideIfExists the override if exists
   */
  public <T extends DriverManager> void addDriverManager(T driverManager,
      boolean overrideIfExists) {
    if (overrideIfExists) {
      //TODO: GENERIC T STRING
      this.overrideDriverManager("", driverManager);
    } else {
      //TODO: GENERIC T STRING
      this.addDriverManager("", driverManager);
    }
  }

  public void overrideDriverManager(String key, DriverManager driverManager) {
    if (this.managerStore.containsKey(key)) {
      this.managerStore.putOrOverride(key, driverManager);
    } else {
      this.managerStore.put(key, driverManager);
    }
  }

  /**
   * Add driver manager.
   *
   * @param key           the key
   * @param driverManager the driver manager
   */
  public void addDriverManager(String key, DriverManager driverManager) {
    this.managerStore.put(key, driverManager);
  }

  /**
   * Dispose of the driver store.
   * **NEEDS IMPLEMENTATION
   */
  @Override
  public void close() {
    if (this.managerStore == null) {
      return;
    }

    this.log.logMessage(MessageType.VERBOSE, "Start dispose");

    for (DriverManager singleDriver : this.managerStore.values()) {
      if (singleDriver != null) {
        try {
          singleDriver.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      this.managerStore = null;
      this.log.logMessage(MessageType.VERBOSE, "End dispose");
    }

  }

  /**
   * Dispose of the driver store.
   *
   * @param closing the closing
   */
  //In C#, but might not be necessary
  public void close(boolean closing) {
    if (!closing) {
      this.close();
    }
  }
}
