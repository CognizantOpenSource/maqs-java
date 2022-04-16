/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.base;

import com.cognizantsoftvision.maqs.utilities.logging.ILogger;
import com.cognizantsoftvision.maqs.utilities.performance.IPerfTimerCollection;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * The Test Object interface class.
 */
public interface ITestObject extends AutoCloseable {

  /**
   * Gets the logger.
   *
   * @return The logger
   */
  ILogger getLogger();

  /**
   * Sets the logger.
   *
   * @param newLogger The logger to use
   */
  void setLogger(final ILogger newLogger);

  /**
   * Gets the manager store.
   *
   * @return the manager Dictionary
   */
  ManagerStore getManagerStore();

  /**
   * Gets or sets the performance timer collection.
   *
   * @return the performance timer collection
   */
  IPerfTimerCollection getPerfTimerCollection();

  /**
   * Sets the performance timer collection.
   *
   * @param perfTimerCollection the performance time collection to be set
   */
  void setPerfTimerCollection(IPerfTimerCollection perfTimerCollection);

  /**
   * Gets associated files, by file path.
   * @return list of associated files
   */
  List<String> getAssociatedFiles();

  /**
   * Checks if the file exists and if so attempts to add it to the associated files set.
   *
   * @param path path of the file
   * @return boolean value if the associated file is added
   */
  boolean addAssociatedFile(String path);

  /**
   * Adds a driver manager.
   * @param driverManager the driver manager to be added
   * @param <T> the object
   */
  <T extends IDriverManager<?>> void addDriverManager(T driverManager);

  /**
   * Adds a driver manager.
   * @param driverManager the driver manager to be added
   * @param overrideIfExists override if the manager exists
   * @param <T> the object
   */
  <T extends IDriverManager<?>> void addDriverManager(T driverManager, boolean overrideIfExists);

  /**
   * Adds a driver manager.
   *
   * @param key the key
   * @param driverManager the driver manager to be added
   */
  void addDriverManager(String key, IDriverManager<?> driverManager);

  /**
   * Adds a driver manager.
   *
   * @param key the key
   * @param driverManager the driver manager to be added
   */
  void addDriverManager(String key, DriverManager<?> driverManager);

  /**
   * Returns an array of the file paths associated with the test object.
   *
   * @param path The file path to search for
   * @return Whether the exact file path is contained in the set
   */
  boolean containsAssociatedFile(String path);

  /**
   * Returns an array of the file paths associated with the test object.
   *
   * @return An array of the associated files
   */
  String[] getArrayOfAssociatedFiles();

  void overrideDriverManager(String key, DriverManager<?> driverManager);

  /**
   * Override a specific driver.
   *
   * @param key the key to be used to search for the driver in the driver manager
   * @param driverManager The new driver manager
   */
  void overrideDriverManager(final String key, final IDriverManager<?> driverManager);

  /**
   * Removes the file path from the associated file set.
   *
   * @param path path of the file
   * @return True if the file path was successfully removed, false if the file wasn't in the set
   */
  boolean removeAssociatedFile(String path);

  /**
   * Gets a dictionary of string key and object value pairs.
   *
   * @return the objects in the map
   */
  ConcurrentMap<String, Object> getObjects();

  /**
   * Sets an object value, will replace if the key already exists.
   *
   * @param key The key
   * @param value The value to associate with the key
   */
  void setObject(String key, Object value);

  /**
   * Gets a dictionary of string key value pairs.
   *
   * @return the values
   */
  ConcurrentMap<String, String> getValues();

  /**
   * Sets a string value, will replace if the key already exists.
   *
   * @param key The key
   * @param value The value to associate with the key
   */
  void setValue(String key, String value);

  /**
   * gets the fully qualified test name.
   * @return returns the test name
   */
  String getFullyQualifiedTestName();

  /**
   * gets if the test object is closed.
   * @return boolean if the test object is closed
   */
  boolean getIsClosed();
}
