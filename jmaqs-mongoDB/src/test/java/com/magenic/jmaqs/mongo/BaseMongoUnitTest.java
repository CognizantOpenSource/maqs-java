/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.mongo;

import com.magenic.jmaqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BaseMongoUnitTest extends BaseMongoTest {
  @Test(groups = TestCategories.MONGO)
  public void testGetMongoDBDriver() {
    MongoDBDriver mongoDBDriver = this.getMongoDBDriver();
    Assert.assertNotNull(mongoDBDriver,
        "Checking that MongoDB Driver is not null through BaseMongoTest");
  }

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

  @Test(groups = TestCategories.MONGO)
  public void testGetMongoTestObject() {
    Assert.assertNotNull(this.getTestObject(),
        "Checking that Selenium Test Object is not null through BaseSeleniumTest");
  }

  @Test(groups = TestCategories.MONGO)
  public void testOverrideConnectionDriver() {
    overrideConnectionDriver(this.getMongoDBDriver());
    overrideConnectionDriver(this.getBaseConnectionString(), this.getBaseDatabaseString(), this.getBaseCollectionString());
    Assert.assertNotNull(getMongoDBDriver());
  }

  /**
   * Gets the connection string.
   */
  @Test(groups = TestCategories.MONGO)
  public void getDatabaseConnectionStringTest() {
    String connection = this.getBaseConnectionString();
    Assert.assertEquals(connection, "mongodb://localhost:27017", "connection strings do not match");
  }

  /**
   * Gets the connection string.
   */
  @Test(groups = TestCategories.MONGO)
  public void getDatabaseCollectionStringTest() {
    String collection = this.getBaseCollectionString();
    Assert.assertEquals(collection, "MongoTestCollection", "collection strings do not match");
  }

  /**
   * Gets the database string.
   */
  @Test(groups = TestCategories.MONGO)
  public void getDatabaseStringTest() {
    String databaseString = this.getBaseDatabaseString();
    Assert.assertEquals(databaseString, "MongoDatabaseTest", "database string do not match");
  }
}
