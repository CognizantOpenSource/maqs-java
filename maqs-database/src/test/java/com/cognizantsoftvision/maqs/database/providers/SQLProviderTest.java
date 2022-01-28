/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.database.providers;

import com.cognizantsoftvision.maqs.base.BaseGenericTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SQLProviderTest extends BaseGenericTest {

  @Test
  public void testGetDataSource() {
    SQLProvider sqlProvider = new SQLProvider("http://127.0.0.1");
    Assert.assertNotNull(sqlProvider.getDataSource());
  }

  @Test
  public void testGetDialect() {
    SQLProvider sqlProvider = new SQLProvider("http://127.0.0.1");
    Assert.assertEquals(sqlProvider.getDialect(), "org.hibernate.dialect.SQLServerDialect");
  }
}