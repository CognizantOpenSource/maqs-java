/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.database;

import com.cognizantsoftvision.maqs.base.BaseGenericTest;
import com.cognizantsoftvision.maqs.database.constants.DataProviderType;
import com.cognizantsoftvision.maqs.database.providers.IDataSourceProvider;
import com.cognizantsoftvision.maqs.database.providers.SQLProvider;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The type Database config unit test.
 */
public class DatabaseConfigUnitTest extends BaseGenericTest {

  /**
   * Test get provider type string.
   */
  @Test(groups = TestCategories.DATABASE)
  public void testGetProviderTypeString() {
    Assert.assertEquals(DatabaseConfig.getProviderTypeString(), "SQL");
  }
}
