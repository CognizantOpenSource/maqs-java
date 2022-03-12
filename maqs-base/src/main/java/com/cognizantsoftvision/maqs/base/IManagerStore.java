/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.base;

/**
 * The Manager Dictionary interface class.
 */
public interface IManagerStore extends AutoCloseable {

  /**
   * Put.
   *
   * @param driverManager the driver manager
   */
  void put(DriverManager<?> driverManager);

  /**
   * Put or override.
   *
   * @param driverManager the driver manager
   */
  void putOrOverride(DriverManager<?> driverManager);

  /**
   * Put or override.
   *
   * @param key           the key
   * @param driverManager the driver manager
   */
  void putOrOverride(String key, DriverManager<?> driverManager);

  /**
   * Remove boolean.
   *
   * @param key the key
   * @return the boolean
   */
  boolean remove(String key);

  /**
   * Check if the managers contains the keyed manager.
   *
   * @param key the Key name
   * @return True if the store contains the key
   */
  boolean managerContains(String key);
  }
