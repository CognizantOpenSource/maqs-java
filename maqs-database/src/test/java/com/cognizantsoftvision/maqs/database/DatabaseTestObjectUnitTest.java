/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.database;

import com.cognizantsoftvision.maqs.base.BaseGenericTest;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DatabaseTestObjectUnitTest extends BaseGenericTest {

  @Test(groups = TestCategories.DATABASE)
  public void testDatabaseTestObject() {
    DatabaseDriver databaseDriver = ConnectionFactory.getOpenConnection();

    DatabaseTestObject testObject = new DatabaseTestObject(databaseDriver, this.getLogger(),
        this.getFullyQualifiedTestClassName());
    Assert.assertNotNull(testObject, "Checking that database test object via driver is not null");
  }

  @Test(groups = TestCategories.DATABASE)
  public void testGetDatabaseDriver() {
    DatabaseDriver databaseDriver = ConnectionFactory.getOpenConnection();

    try (DatabaseTestObject testObject = new DatabaseTestObject(databaseDriver, this.getLogger(),
        this.getFullyQualifiedTestClassName())) {
      Assert.assertNotNull(testObject.getDatabaseDriver(),
          "Checking that database driver can be retrieved from test object");
    }
  }

  @Test(groups = TestCategories.DATABASE)
  public void testGetDatabaseManager() {
    DatabaseDriver databaseDriver = ConnectionFactory.getOpenConnection();

    try (DatabaseTestObject testObject = new DatabaseTestObject(databaseDriver, this.getLogger(),
        this.getFullyQualifiedTestClassName())) {
      Assert.assertNotNull(testObject.getDatabaseManager(),
          "Checking that database driver manager can be retrieved from test object");
    }
  }

  @Test(groups = TestCategories.DATABASE)
  public void testSetDatabaseDriver() {
    DatabaseDriver databaseDriver = ConnectionFactory.getOpenConnection();

    try (DatabaseTestObject testObject = new DatabaseTestObject(databaseDriver, this.getLogger(),
        this.getFullyQualifiedTestClassName())) {
      final int hashCode = testObject.getDatabaseDriver().hashCode();
      DatabaseDriver openConnection = ConnectionFactory.getOpenConnection();
      testObject.setDatabaseDriver(openConnection);
      final int hashCode1 = testObject.getDatabaseDriver().hashCode();
      Assert.assertNotEquals(hashCode, hashCode1,
          String.format("Checking that the database driver is not the same as previous version.  First: %d, Second: %d",
              hashCode, hashCode1));
    }
  }

  @Test
  public void testSetDatabaseManager() {
    DatabaseDriver databaseDriver = ConnectionFactory.getOpenConnection();

    DatabaseTestObject testObject = new DatabaseTestObject(databaseDriver, this.getLogger(),
        this.getFullyQualifiedTestClassName());
    final int hashCode = testObject.getDatabaseManager().hashCode();
    DatabaseDriverManager databaseDriverManager = new DatabaseDriverManager(ConnectionFactory::getOpenConnection,
        testObject);
    testObject.setDatabaseManager(databaseDriverManager);
    final int hashCode1 = testObject.getDatabaseManager().hashCode();
    Assert.assertNotEquals(hashCode, hashCode1);
  }

  @Test
  public void testSetDatabaseManagerWithDriver() {
    DatabaseDriver databaseDriver = ConnectionFactory.getOpenConnection();

    DatabaseTestObject testObject = new DatabaseTestObject(databaseDriver, this.getLogger(),
        this.getFullyQualifiedTestClassName());
    final int hashCode = testObject.getDatabaseManager().hashCode();
    DatabaseDriverManager databaseDriverManager = new DatabaseDriverManager(ConnectionFactory.getOpenConnection(),
        testObject);
    testObject.setDatabaseManager(databaseDriverManager);
    final int hashCode1 = testObject.getDatabaseManager().hashCode();
    Assert.assertNotEquals(hashCode, hashCode1);
  }
}