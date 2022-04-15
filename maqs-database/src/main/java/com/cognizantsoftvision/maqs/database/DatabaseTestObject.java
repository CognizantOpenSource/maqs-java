/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.database;

import com.cognizantsoftvision.maqs.base.BaseTestObject;
import com.cognizantsoftvision.maqs.utilities.logging.ILogger;
import java.util.function.Supplier;

/**
 * Class DatabaseTestObject ...
 */
public class DatabaseTestObject extends BaseTestObject {
  /**
   * Constructor DatabaseTestObject creates a new DatabaseTestObject instance.
   *
   * @param driver                 of type DatabaseDriver
   * @param logger                 of type Logger
   * @param fullyQualifiedTestName of type String
   */
  public DatabaseTestObject(DatabaseDriver driver, ILogger logger, String fullyQualifiedTestName) {
    this(() -> driver, logger, fullyQualifiedTestName);
  }

  /**
   * Constructor DatabaseTestObject creates a new DatabaseTestObject instance.
   *
   * @param getDriverSupplier      of type Supplier
   * @param logger                 of type Logger
   * @param fullyQualifiedTestName of type String
   */
  public DatabaseTestObject(Supplier<DatabaseDriver> getDriverSupplier, ILogger logger, String fullyQualifiedTestName) {
    super(logger, fullyQualifiedTestName);
    this.getManagerStore().put((DatabaseDriverManager.class).getCanonicalName(),
        new DatabaseDriverManager(getDriverSupplier, this));
  }

  /**
   * Method getDatabaseDriver returns the databaseDriver of this
   * DatabaseTestObject object.
   *
   * @return the databaseDriver (type DatabaseDriver) of this DatabaseTestObject
   *         object.
   */
  public DatabaseDriver getDatabaseDriver() {
    return this.getDatabaseManager().getDatabaseDriver();
  }

  /**
   * Method getDatabaseManager returns the databaseManager of this
   * DatabaseTestObject object.
   *
   * @return the databaseManager (type DatabaseDriverManager) of this
   *         DatabaseTestObject object.
   */
  public DatabaseDriverManager getDatabaseManager() {
    return (DatabaseDriverManager) this.getManagerStore().get(DatabaseDriverManager.class.getCanonicalName());
  }

  /**
   * Method setDatabaseDriver sets the databaseDriver of this DatabaseTestObject
   * object.
   *
   * @param driver the databaseDriver of this DatabaseTestObject object.
   */
  public void setDatabaseDriver(DatabaseDriver driver) {
    this.getManagerStore().put(DatabaseDriverManager.class.getCanonicalName(),
        new DatabaseDriverManager(() -> driver, this));
  }

  /**
   * Method setDatabaseManager sets the databaseManager of this DatabaseTestObject
   * object.
   *
   * @param manager the databaseManager of this DatabaseTestObject object.
   */
  public void setDatabaseManager(DatabaseDriverManager manager) {
    this.getManagerStore().put(DatabaseDriverManager.class.getCanonicalName(), manager);
  }
}
