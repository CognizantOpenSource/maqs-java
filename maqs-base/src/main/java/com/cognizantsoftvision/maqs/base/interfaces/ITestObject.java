/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.base.interfaces;

import com.cognizantsoftvision.maqs.base.DriverManager;
import com.cognizantsoftvision.maqs.base.ManagerDictionary;
import com.cognizantsoftvision.maqs.utilities.logging.Logger;
import com.cognizantsoftvision.maqs.utilities.performance.PerfTimerCollection;
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


  /// <summary>
  /// Gets the manager store
  /// </summary>
  ManagerDictionary getManagerStore();

  /// <summary>
  /// Gets a dictionary of string key and object value pairs
  /// </summary>
  ConcurrentMap<String, Object> getObjects();

  /// <summary>
  /// Gets or sets the performance timer collection
  /// </summary>
  PerfTimerCollection getPerfTimerCollection();

  void setPerfTimerCollection(PerfTimerCollection perfTimerCollection);

  /// <summary>
  /// Gets a dictionary of string key value pairs
  /// </summary>
  ConcurrentMap<String, String> getValues();

  /// <summary>
  /// Checks if the file exists and if so attempts to add it to the associated files set
  /// </summary>
  /// <param name="path">path of the file</param>
  /// <returns>True if the file exists and was successfully added, false if the file doesn't exist or was already added</returns>
  boolean addAssociatedFile(String path);

  /// <summary>
  /// Add a new driver
  /// </summary>
  /// <param name="key">Key for the new driver</param>
  /// <param name="manager">The new driver manager</param>
  void addDriverManager(String key, final DriverManager<?> driverManager);

  /// <summary>
  /// Add a new driver
  /// </summary>
  /// <typeparam name="T">The driver type</typeparam>
  /// <param name="manager">The new driver manager</param>
  /// <param name="overrideIfExists">Should we override if this driver exists.  If it exists and we don't override than an error will be thrown.</param>
  <T extends DriverManager<?>> void addDriverManager(final T driverManager, final boolean overrideIfExists);

  /// <summary>
  /// Returns an array of the file paths associated with the test object
  /// </summary>
  /// <param name="path">The file path to search for</param>
  /// <returns>Whether the exact file path is contained in the set</returns>
  boolean containsAssociatedFile(String path);

  /// <summary>
  /// Returns an array of the file paths associated with the test object
  /// </summary>
  /// <returns>An array of the associated files</returns>
  String[] getArrayOfAssociatedFiles();

  /// <summary>
  /// Override a specific driver
  /// </summary>
  /// <typeparam name="T">The driver type</typeparam>
  /// <param name="manager">The new driver manager</param>
  void overrideDriverManager(final String key, final DriverManager<?> driverManager);

  /// <summary>
  /// Removes the file path from the associated file set
  /// </summary>
  /// <param name="path">path of the file</param>
  /// <returns>True if the file path was successfully removed, false if the file wasn't in the set</returns>
  boolean removeAssociatedFile(String path);

  /// <summary>
  /// Sets an object value, will replace if the key already exists
  /// </summary>
  /// <param name="key">The key</param>
  /// <param name="value">The value to associate with the key</param>
  void setObject(String key, Object value);

  /// <summary>
  /// Sets a string value, will replace if the key already exists
  /// </summary>
  /// <param name="key">The key</param>
  /// <param name="value">The value to associate with the key</param>
  void setValue(String key, String value);

}
