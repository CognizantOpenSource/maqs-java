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
public class ManagerStore extends HashMap<String, IDriverManager<?>> implements IManagerStore {

  @Override
  public void close() {
    this.clear();
  }

  @Override
  public void clear() {
    for (Map.Entry<String, IDriverManager<?>> entry : this.entrySet()) {
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
  public <T> T getDriver(String key) {
    return (T) this.get(key);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void put(IDriverManager<?> driverManager) {
    this.put(driverManager.getClass().getName(), driverManager);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void putOrOverride(IDriverManager<?> driverManager) {
    this.putOrOverride(driverManager.getClass().getName(), driverManager);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void putOrOverride(String key, IDriverManager<?> driverManager) {
    this.remove(key);
    this.put(key, driverManager);
  }

  @Override
  public IDriverManager<?> getManager(String key) {
    return this.get(key);
  }

  /**
   * {@inheritDoc}
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
