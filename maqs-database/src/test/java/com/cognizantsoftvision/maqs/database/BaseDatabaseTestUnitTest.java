/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.database;

import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BaseDatabaseTestUnitTest extends BaseDatabaseTest {

  @Test(groups = TestCategories.DATABASE)
  public void testGetDatabaseDriver() {
    final DatabaseDriver databaseDriver = getDatabaseDriver();
    Assert.assertNotNull(databaseDriver);
  }

  @Test(groups = TestCategories.DATABASE)
  public void testSetDatabaseDriver() {
    final DatabaseDriver connection = getConnection();
    final int hashCode = getDatabaseDriver().hashCode();
    setDatabaseDriver(connection);
    final int hashCode1 = getDatabaseDriver().hashCode();
    Assert.assertNotEquals(hashCode, hashCode1);
  }

  @Test(groups = TestCategories.DATABASE)
  public void testGetConnection() {
    final DatabaseDriver connection = getConnection();
    Assert.assertNotNull(connection);
    Assert.assertNotEquals(connection.hashCode(), getDatabaseDriver().hashCode());
  }
}