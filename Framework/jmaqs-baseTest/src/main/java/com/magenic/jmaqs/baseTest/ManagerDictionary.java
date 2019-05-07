/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.baseTest;

import java.util.*;

//TODO: recheck for parity.  Need more context to implement some of this interface

/**
 * Driver manager dictionary.
 */
public class ManagerDictionary extends HashMap<String, DriverManager> implements AutoCloseable {

  @Override public void close() throws Exception {
    this.clear();
  }

  @Override public void clear() {
    for (Map.Entry<String, DriverManager> entry : this.entrySet()) {
      try {
        entry.getValue().close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    super.clear();
  }

  //TODO: Check and temp
  @SuppressWarnings("unchecked") public <T extends Object> T getDriver(String key) {
    return (T) this.get(key);
  }

  public void put(DriverManager driverManager) {
    this.put(driverManager.getClass().getName(), driverManager);
  }

  public void putOrOverride(DriverManager driverManager) {
    this.putOrOverride(driverManager.getClass().getName(), driverManager);
  }

  public void putOrOverride(String key, DriverManager driverManager) {
    this.remove(key);
    this.put(key, driverManager);
  }

  public boolean Remove(String key) {
    if (this.containsKey(key)) {
      try {
        this.get(key).close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    super.remove(key);
    return this.containsKey(key);

  }
}

