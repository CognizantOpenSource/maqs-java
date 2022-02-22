/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.database;

import com.cognizantsoftvision.maqs.base.BaseExtendableTest;
import org.testng.ITestResult;

/**
 * Base Database Test Class.
 */
public class BaseDatabaseTest extends BaseExtendableTest<DatabaseTestObject> {

  /**
   * Instantiates a new Base database test.
   */
  public BaseDatabaseTest() {
    // Currently not populated with any logic
  }

  /**
   * Get Database Driver from TestObject.
   *
   * @return the databaseDriver (type DatabaseDriver) of this BaseDatabaseTest object.
   */
  public DatabaseDriver getDatabaseDriver() {
    return this.getTestObject().getDatabaseDriver();
  }

  /**
   * Method setDatabaseDriver sets the databaseDriver of the test object.
   *
   * @param driver the databaseDriver of this BaseDatabaseTest object.
   */
  public void setDatabaseDriver(DatabaseDriver driver) {
    this.getTestObject().setDatabaseDriver(driver);
  }

  /**
   * Get a new DatabaseDriver from the ConnectionFactory.
   *
   * @return the connection (type DatabaseDriver) of this BaseDatabaseTest object.
   */
  public DatabaseDriver getConnection() {
    return ConnectionFactory.getOpenConnection();
  }

  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {
    // Currently not populated with any logic
  }

  @Override
  protected void createNewTestObject() {
    this.setTestObject(new DatabaseTestObject(this.getConnection(), this.createLogger(),
        this.getFullyQualifiedTestClassName()));
  }

}
