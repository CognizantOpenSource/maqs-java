/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.nosql;

import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Mongo Database Config unit test class.
 */
public class MongoDBConfigUnitTest extends BaseMongoTest{

  /**
   * Test getting the connection string.
   */
  @Test(groups = TestCategories.MONGO)
  public void testGetMongoDBConnectionStringTest() {
    String connection = MongoDBConfig.getConnectionString();
    Assert.assertEquals(connection, "mongodb://localhost:27017", "connection strings do not match");
  }

  /**
   * Test getting the database string.
   */
  @Test(groups = TestCategories.MONGO)
  public void testGetMongoDBDatabaseStringTest() {
    String databaseString = MongoDBConfig.getDatabaseString();
    Assert.assertEquals(databaseString, "MongoDatabaseTest", "database string do not match");
  }

  /**
   * Test getting the connection string.
   */
  @Test(groups = TestCategories.MONGO)
  public void testGetMongoDBCollectionStringTest() {
    String collection = MongoDBConfig.getCollectionString();
    Assert.assertEquals(collection, "MongoTestCollection", "collection strings do not match");
  }

  /**
   * Test getting the timeout value.
   */
  @Test(groups = TestCategories.MONGO)
  public void testGetMongoDBQueryTimeout() {
    int databaseTimeout = MongoDBConfig.getQueryTimeout();
    Assert.assertEquals(databaseTimeout, 30, "Timeout is incorrect");
  }
}
