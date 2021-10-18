/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.mongo;

import com.magenic.jmaqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for database configurations.
 */
public class MongoDBConfigUnitTest extends BaseMongoTest{

  /**
   * Gets the connection string.
   */
  @Test(groups = TestCategories.MONGO)
  public void testGetMongoDBConnectionStringTest() {
    String connection = MongoDBConfig.getConnectionString();
    Assert.assertEquals(connection, "mongodb://localhost:27017", "connection strings do not match");
  }

  /**
   * Gets the database string.
   */
  @Test(groups = TestCategories.MONGO)
  public void testGetMongoDBDatabaseStringTest() {
    String databaseString = MongoDBConfig.getDatabaseString();
    Assert.assertEquals(databaseString, "MongoDatabaseTest", "database string do not match");
  }

  /**
   * Gets the connection string.
   */
  @Test(groups = TestCategories.MONGO)
  public void testGetMongoDBCollectionStringTest() {
    String collection = MongoDBConfig.getCollectionString();
    Assert.assertEquals(collection, "MongoTestCollection", "collection strings do not match");
  }

  /**
   * Gets the timeout value.
   */
  @Test(groups = TestCategories.MONGO)
  public void testGetMongoDBQueryTimeout() {
    int databaseTimeout = MongoDBConfig.getQueryTimeout();
    Assert.assertEquals(databaseTimeout, 30, "Timeout is incorrect");
  }
}
