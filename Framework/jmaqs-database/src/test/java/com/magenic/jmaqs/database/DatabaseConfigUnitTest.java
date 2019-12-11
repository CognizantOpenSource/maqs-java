/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.database;

import com.magenic.jmaqs.base.BaseGenericTest;
import com.magenic.jmaqs.base.BaseTest;
import com.magenic.jmaqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

/**
 * The type Database config unit test.
 */
public class DatabaseConfigUnitTest extends BaseTest {

  /**
   * Test get connection string.
   */
  @Test(groups = TestCategories.Database)
  public void testGetConnectionString() {
    Assert.assertEquals(DatabaseConfig.getConnectionString(), "jdbc:sqlite:./src/test/resources/MyDatabase.sqlite");
  }

  /**
   * Test get provider type string.
   */
  @Test(groups = TestCategories.Database)
  public void testGetProviderTypeString() {
    Assert.assertEquals(DatabaseConfig.getProviderTypeString(), "SQLITE");
  }

  /**
   * Test get entity directory string.
   */
  @Test
  public void testGetEntityDirectoryString() {
    Assert.assertEquals(DatabaseConfig.getEntityDirectoryString(), "./src/test/java/entities");
  }

  @Override
  protected void postSetupLogging() throws Exception {

  }

  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {

  }
}