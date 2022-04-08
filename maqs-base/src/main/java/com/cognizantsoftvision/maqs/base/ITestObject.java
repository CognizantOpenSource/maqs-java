/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.base;

import com.cognizantsoftvision.maqs.utilities.logging.Logger;
import com.cognizantsoftvision.maqs.utilities.performance.PerfTimerCollection;
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
  Logger getLogger();

  /**
   * Sets the logger.
   *
   * @param newLogger The logger to use
   */
  void setLogger(final Logger newLogger);

  /**
   * Gets the manager store.
   *
   * @return the manager Dictionary
   */
  ManagerStore getManagerStore();

  /**
   * Gets a dictionary of string key and object value pairs.
   *
   * @return the objects in the map
   */
  ConcurrentMap<String, Object> getObjects();

  /**
   * Gets or sets the performance timer collection.
   *
   * @return the performance timer collection
   */
  PerfTimerCollection getPerfTimerCollection();

  /**
   * Sets the performance timer collection.
   *
   * @param perfTimerCollection the performance time collection to be set
   */
  void setPerfTimerCollection(PerfTimerCollection perfTimerCollection);

  /**
   * Gets a dictionary of string key value pairs.
   *
   * @return the values
   */
  ConcurrentMap<String, String> getValues();

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
   * Add a new driver manager.
   *
   * @param key Key for the new driver
   * @param driverManager The new driver manager
   */
  void addDriverManager(String key, final IDriverManager<?> driverManager);

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
   * Sets an object value, will replace if the key already exists.
   *
   * @param key The key
   * @param value The value to associate with the key
   */
  void setObject(String key, Object value);

  /**
   * Sets a string value, will replace if the key already exists.
   *
   * @param key The key
   * @param value The value to associate with the key
   */
  void setValue(String key, String value);

  String getFullyQualifiedTestName();

  boolean getIsClosed();
}
