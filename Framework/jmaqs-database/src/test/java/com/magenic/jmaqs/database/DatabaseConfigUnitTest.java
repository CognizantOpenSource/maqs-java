/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.database;

import com.magenic.jmaqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The type Database config unit test.
 */
public class DatabaseConfigUnitTest {

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
}