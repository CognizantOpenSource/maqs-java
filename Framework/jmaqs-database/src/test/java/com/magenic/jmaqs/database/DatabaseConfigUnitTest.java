/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.database;

import com.magenic.jmaqs.base.BaseTest;
import com.magenic.jmaqs.database.constants.DataProviderType;
import com.magenic.jmaqs.database.providers.IDataSourceProvider;
import com.magenic.jmaqs.database.providers.SQLProvider;
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
    Assert.assertEquals(DatabaseConfig.getConnectionString(),
        "jdbc:sqlserver://qasqlserver.database.windows.net");
  }

  /**
   * Test get provider type string.
   */
  @Test(groups = TestCategories.Database)
  public void testGetProviderTypeString() {
    Assert.assertEquals(DatabaseConfig.getProviderTypeString(), "SQL");
  }

  /**
   * Test get entity directory string.
   */
  @Test(groups = TestCategories.Database)
  public void testGetEntityDirectoryString() {
    Assert.assertEquals(DatabaseConfig.getEntityDirectoryString(),
        "./src/test/java/com/magenic/jmaqs/database/entities/");
  }

  @Test
  public void testGetProviderType() {
    final DataProviderType providerType = DatabaseConfig.getProviderType();
    Assert.assertEquals(providerType, DataProviderType.SQL);
  }

  @Test
  public void testGetProvider() {
    final IDataSourceProvider provider = DatabaseConfig.getProvider();
    Assert.assertTrue(provider instanceof SQLProvider);
  }

  @Test
  public void testGetEntityPackageString() {
    Assert.assertEquals(DatabaseConfig.getEntityPackageString(),
        "com.magenic.jmaqs.database.entities");
  }

  @Test
  public void testGetName() {
    Assert.assertEquals(DatabaseConfig.getName(), "JMAQS");
  }

  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {

  }
}