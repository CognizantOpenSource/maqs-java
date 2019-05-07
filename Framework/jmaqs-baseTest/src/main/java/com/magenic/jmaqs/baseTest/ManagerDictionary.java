/*
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.baseTest;

import java.util.*;

//TODO: recheck for parity.  Need more context to implement some of this interface

/**
 * Driver manager dictionary.
 */
public class ManagerDictionary extends Dictionary<String, DriverManager> implements AutoCloseable {
  @Override public void close() throws Exception {
    this.clear();
  }

  @Override public int size() {
    return 0;
  }

  @Override public boolean isEmpty() {
    return false;
  }

  @Override public Enumeration<String> keys() {
    return null;
  }

  @Override public Enumeration<DriverManager> elements() {
    return null;
  }

  @Override public DriverManager get(Object key) {
    return null;
  }

 /* @Override public DriverManager get(Object object) {
    String key = object.getClass().getName();

    if (this.containsKey(key))
    {
      try {
        this.get(key).close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }*/

  private boolean containsKey(String key) {

    for (Iterator<String> it = this.keys().asIterator(); it.hasNext(); ) {
      String keyValue = it.next();
      if (keyValue.equals(key)) {
        return true;
      }
    }
    return false;
  }

  @Override public DriverManager put(String key, DriverManager value) {
    return null;
  }

  @Override public DriverManager remove(Object key) {
    return null;
  }

  public void clear() {
    for (AbstractMap.SimpleEntry<String, DriverManager> driverManagerPair : this.getPairs()) {
      try {
        driverManagerPair.getValue().close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private List<AbstractMap.SimpleEntry<String, DriverManager>> getPairs() {
    ArrayList<AbstractMap.SimpleEntry<String, DriverManager>> pairList = new ArrayList<>();
    for (Iterator<String> it = this.keys().asIterator(); it.hasNext(); ) {
      String key = it.next();
      DriverManager driverManager = this.get(key);
      pairList.add(new AbstractMap.SimpleEntry<>(key, driverManager));
    }
    return pairList;
  }
}

