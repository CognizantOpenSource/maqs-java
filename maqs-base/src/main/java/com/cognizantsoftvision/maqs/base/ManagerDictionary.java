/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.base;

import com.cognizantsoftvision.maqs.base.exceptions.ManagerDisposalException;
import java.util.HashMap;
import java.util.Map;

/**
 * Driver manager dictionary.
 */
public class ManagerDictionary extends HashMap<String, DriverManager<?>> implements AutoCloseable {

  @Override
  public void close() {
    this.clear();
  }

  @Override
  public void clear() {
    for (Map.Entry<String, DriverManager<?>> entry : this.entrySet()) {
      try {
        entry.getValue().close();
      } catch (Exception e) {
        throw new ManagerDisposalException(e);
      }
    }
    super.clear();
  }

  /**
   * Gets driver.
   *
   * @param <T> the type parameter
   * @param key the key
   * @return the driver
   */
  @SuppressWarnings("unchecked")
  public <T extends Object> T getDriver(String key) {
    return (T) this.get(key);
  }

  /**
   * Put.
   *
   * @param driverManager the driver manager
   */
  public void put(DriverManager<?> driverManager) {
    this.put(driverManager.getClass().getName(), driverManager);
  }

  /**
   * Put or override.
   *
   * @param driverManager the driver manager
   */
  public void putOrOverride(DriverManager<?> driverManager) {
    this.putOrOverride(driverManager.getClass().getName(), driverManager);
  }

  /**
   * Put or override.
   *
   * @param key           the key
   * @param driverManager the driver manager
   */
  public void putOrOverride(String key, DriverManager<?> driverManager) {
    this.remove(key);
    this.put(key, driverManager);
  }

  /**
   * Remove boolean.
   *
   * @param key the key
   * @return the boolean
   */
  public boolean remove(String key) {
    if (this.containsKey(key)) {
      try {
        this.get(key).close();
      } catch (Exception e) {
        throw new ManagerDisposalException(e);
      }
    }

    super.remove(key);
    return !this.containsKey(key);

  }
}
