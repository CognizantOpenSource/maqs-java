/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.database;

import com.magenic.jmaqs.base.BaseExtendableTest;
import org.testng.ITestResult;

public class BaseDatabaseTest extends BaseExtendableTest<DatabaseTestObject> {

  public BaseDatabaseTest() {
  }

  public DatabaseDriver getDatabaseDriver() {
    return this.getTestObject().getDatabaseDriver();
  }

  public void setDatabaseDriver(DatabaseDriver driver) {
    this.getTestObject().setDatabaseDriver(driver);
  }

  public DatabaseDriver getConnection() {
    return ConnectionFactory.getOpenConnection();
  }

  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {

  }

  @Override
  protected void createNewTestObject() {
    this.setTestObject(new DatabaseTestObject(this.getConnection(), this.createLogger(),
        this.getFullyQualifiedTestClassName()));
  }

}
