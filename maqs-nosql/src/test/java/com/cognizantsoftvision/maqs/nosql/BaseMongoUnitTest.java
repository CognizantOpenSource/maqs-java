/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.nosql;

import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Base Mongo unit test class.
 */
public class BaseMongoUnitTest extends BaseMongoTest {

  /**
   * Test the get mongo db driver.
   */
  @Test(groups = TestCategories.MONGO)
  public void testGetMongoDBDriver() {
    this.setMongoDBDriver(new MongoDBDriver(
            MongoDBConfig.getConnectionString(), MongoDBConfig.getDatabaseString(), MongoDBConfig.getCollectionString()));
    MongoDBDriver mongoDBDriver = this.getMongoDBDriver();
    Assert.assertNotNull(mongoDBDriver,
        "Checking that MongoDB Driver is not null through BaseMongoTest");
  }

  /**
   * Test the set mongo db driver.
   */
  @Test(groups = TestCategories.MONGO)
  public void testSetMongoDBDriver() {
    int hashCode = this.getMongoDBDriver().hashCode();
    try {
      this.setMongoDBDriver(new MongoDBDriver());
    } catch (Exception e) {
      e.printStackTrace();
    }
    int hashCode1 = this.getMongoDBDriver().hashCode();
    Assert.assertNotEquals(hashCode, hashCode1);
  }

  /**
   * Test the override connection driver with the mongo db driver.
   */
  @Test(groups = TestCategories.MONGO)
  public void testOverrideConnectionDriverWithMongoDBDriver() {
    overrideConnectionDriver(this.getMongoDBDriver());
    Assert.assertNotNull(getMongoDBDriver());
    overrideConnectionDriver(this.getBaseConnectionString(),
        this.getBaseDatabaseString(), this.getBaseCollectionString());
    Assert.assertNotNull(getMongoDBDriver());
  }

  /**
   * Test getting the connection string.
   */
  @Test(groups = TestCategories.MONGO)
  public void testGetBaseMongoConnectionStringTest() {
    String connection = this.getBaseConnectionString();
    Assert.assertEquals(connection, "mongodb://localhost:27017", "connection strings do not match");
  }

  /**
   * Test getting the database string.
   */
  @Test(groups = TestCategories.MONGO)
  public void testGetBaseMongoStringTest() {
    String databaseString = this.getBaseDatabaseString();
    Assert.assertEquals(databaseString, "MongoDatabaseTest", "database string do not match");
  }

  /**
   * Test getting the connection string.
   */
  @Test(groups = TestCategories.MONGO)
  public void testGetBaseMongoCollectionStringTest() {
    String collection = this.getBaseCollectionString();
    Assert.assertEquals(collection, "MongoTestCollection", "collection strings do not match");
  }
}
