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
  void put(IDriverManager<?> driverManager);

  /**
   * Put or override.
   *
   * @param driverManager the driver manager
   */
  void putOrOverride(IDriverManager<?> driverManager);

  /**
   * Put or override.
   *
   * @param key           the key
   * @param driverManager the driver manager
   */
  void putOrOverride(String key, IDriverManager<?> driverManager);

  /**
   * Put or override.
   * @param key the key.
   * @return the driver manager interface
   */
  IDriverManager<?> getManager(String key);

  /**
   * Remove boolean.
   *
   * @param key the key
   * @return the boolean
   */
  boolean remove(String key);
}
