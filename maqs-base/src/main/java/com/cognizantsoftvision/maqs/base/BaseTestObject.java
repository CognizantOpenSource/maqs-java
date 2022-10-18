/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.base;

import com.cognizantsoftvision.maqs.base.exceptions.DriverDisposalException;
import com.cognizantsoftvision.maqs.utilities.helper.StringProcessor;
import com.cognizantsoftvision.maqs.utilities.logging.ILogger;
import com.cognizantsoftvision.maqs.utilities.logging.MessageType;
import com.cognizantsoftvision.maqs.utilities.performance.IPerfTimerCollection;
import com.cognizantsoftvision.maqs.utilities.performance.PerfTimerCollection;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * The Base Test Object class.
 */
public class BaseTestObject implements ITestObject {

  /**
   * The Logger.
   */
  private ILogger logger;

  /**
   * The Performance Timer Collection.
   */
  private IPerfTimerCollection perfTimerCollection;

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
  private ManagerStore managerStore;

  /**
   * ArrayList of Strings for associated files.
   */
  private final ArrayList<String> associatedFiles;

  /**
   * The Fully Qualified Test Name.
   */
  private final String fullyQualifiedTestName;

  /**
   * Was the object closed.
   */
  private boolean isClosed = false;

  /**
   * Initializes a new instance of the BaseTestObject class.
   *
   * @param logger                 The test's logger
   * @param fullyQualifiedTestName The test's fully qualified test name
   */
  public BaseTestObject(final ILogger logger, final String fullyQualifiedTestName) {
    this.logger = logger;
    this.perfTimerCollection = new PerfTimerCollection(logger, fullyQualifiedTestName);
    this.values = new ConcurrentHashMap<>();
    this.objects = new ConcurrentHashMap<>();
    this.managerStore = new ManagerStore();
    this.associatedFiles = new ArrayList<>();
    this.fullyQualifiedTestName = fullyQualifiedTestName;

    logger.logMessage(MessageType.INFORMATION, "Setup test object for " + fullyQualifiedTestName);
  }

  /**
   * Initializes a new instance of the BaseTestObject class.
   *
   * @param baseTestObject An existing base test object
   */
  public BaseTestObject(final ITestObject baseTestObject) {
    this.logger = baseTestObject.getLogger();
    this.perfTimerCollection = baseTestObject.getPerfTimerCollection();
    this.values = (ConcurrentHashMap<String, String>) baseTestObject.getValues();
    this.objects = (ConcurrentHashMap<String, Object>) baseTestObject.getObjects();
    this.managerStore = baseTestObject.getManagerStore();
    this.associatedFiles = new ArrayList<>();
    this.fullyQualifiedTestName = baseTestObject.getFullyQualifiedTestName();
    this.isClosed = baseTestObject.getIsClosed();

    baseTestObject.getLogger().logMessage(MessageType.INFORMATION, "Setup test object");
  }

  /**
   * {@inheritDoc} 
   */
  public ILogger getLogger() {
    return this.logger;
  }

  /**
   * {@inheritDoc} 
   */
  public void setLogger(final ILogger logger) {
    this.logger = logger;
  }

  /**
   * {@inheritDoc} 
   */
  public IPerfTimerCollection getPerfTimerCollection() {
    return this.perfTimerCollection;
  }

  /**
   * {@inheritDoc} 
   */
  public void setPerfTimerCollection(final IPerfTimerCollection perfTimerCollection) {
    this.perfTimerCollection = perfTimerCollection;
  }

  /**
   * {@inheritDoc} 
   */
  public String getFullyQualifiedTestName() {
    return this.fullyQualifiedTestName;
  }

  /**
   * {@inheritDoc} 
   */
  public ConcurrentMap<String, String> getValues() {
    return this.values;
  }

  /**
   * {@inheritDoc} 
   */
  @Override
  public void setValues(final ConcurrentHashMap<String, String> values) {
    this.values = values;
  }

  /**
   * {@inheritDoc} 
   */
  public ConcurrentMap<String, Object> getObjects() {
    return this.objects;
  }

  /**
   * {@inheritDoc} 
   */
  @Override
  public void setObjects(final ConcurrentHashMap<String, Object> objects) {
    this.objects = objects;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ManagerStore getManagerStore() {
    return this.managerStore;
  }

  /**
   *  {@inheritDoc}
   */
  @Override
  public void setValue(final String key, final String value) {
    if (this.values.containsKey(key)) {
      this.values.replace(key, value);
    } else {
      this.values.put(key, value);
    }
  }

  /**
   * {@inheritDoc} 
   */
  @Override
  public void setObject(final String key, final Object value) {
    if (this.objects.containsKey(key)) {
      this.objects.replace(key, value);
    } else {
      this.objects.put(key, value);
    }
  }

  /**
   * {@inheritDoc} 
   */
  @Override
  public <T extends IDriverManager<?>> void addDriverManager(final T driverManager) {
    this.addDriverManager(driverManager, false);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <T extends IDriverManager<?>> void addDriverManager(final T driverManager, final boolean overrideIfExists) {
    if (overrideIfExists) {
      this.overrideDriverManager(driverManager.getClass().getTypeName(), driverManager);
    } else {
      this.addDriverManager(driverManager.getClass().getTypeName(), driverManager);
    }
  }

  /**
   * {@inheritDoc} 
   */
  @Override
  public void addDriverManager(String key, IDriverManager<?> driverManager) {
    this.managerStore.put(key, driverManager);
  }

  /**
   * {@inheritDoc} 
   */
  @Override
  public void addDriverManager(final String key, final DriverManager<?> driverManager) {
    this.managerStore.put(key, driverManager);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void overrideDriverManager(final String key, final DriverManager<?> driverManager) {
    if (this.managerStore.containsKey(key)) {
      this.managerStore.putOrOverride(key, driverManager);
    } else {
      this.managerStore.put(key, driverManager);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void overrideDriverManager(String key, IDriverManager<?> driverManager) {
    this.managerStore.putOrOverride(key, driverManager);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean addAssociatedFile(final String path) {
    if (new File(path).exists()) {
      return this.associatedFiles.add(path);
    }
    return false;
  }

  /**
   * Dispose of the driver store.
   */
  @Override
  public void close() {
    if (this.managerStore == null) {
      return;
    }

    this.logger.logMessage(MessageType.VERBOSE, "Start dispose");

    for (final IDriverManager<?> singleDriver : this.managerStore.values()) {
      if (singleDriver != null) {
        try {
          singleDriver.close();
        } catch (final Exception e) {
          throw new DriverDisposalException(StringProcessor.safeFormatter("Unable to properly dispose of driver"), e);
        }
      }
      this.managerStore = null;
      this.logger.logMessage(MessageType.VERBOSE, "End dispose");
    }

    isClosed = true;
  }

  /**
   * {@inheritDoc} 
   */
  @Override
  public boolean removeAssociatedFile(final String path) {
    return this.associatedFiles.remove(path);
  }

  /**
   * {@inheritDoc} 
   */
  @Override
  public String[] getArrayOfAssociatedFiles() {
    return this.associatedFiles.toArray(new String[0]);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsAssociatedFile(final String path) {
    return this.associatedFiles.contains(path);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean getIsClosed() {
    return this.isClosed;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<String> getAssociatedFiles() {
    return this.associatedFiles;
  }
}
