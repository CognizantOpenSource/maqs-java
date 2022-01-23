/*
 * Copyright 2022 Cognizant, All rights Reserved
 */

package com.cognizantsoftvision.maqs.noSQL;

import com.magenic.jmaqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test the Base Mongo Test functionality.
 */
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
  public void testOverrideConnectionDriverWithMongoDBDriver() {
    overrideConnectionDriver(this.getMongoDBDriver());
    Assert.assertNotNull(getMongoDBDriver());
    overrideConnectionDriver(this.getBaseConnectionString(), this.getBaseDatabaseString(), this.getBaseCollectionString());
    Assert.assertNotNull(getMongoDBDriver());
  }

  /**
   * Gets the connection string.
   */
  @Test(groups = TestCategories.MONGO)
  public void testGetBaseMongoConnectionStringTest() {
    String connection = this.getBaseConnectionString();
    Assert.assertEquals(connection, "mongodb://localhost:27017", "connection strings do not match");
  }

  /**
   * Gets the database string.
   */
  @Test(groups = TestCategories.MONGO)
  public void testGetBaseMongoStringTest() {
    String databaseString = this.getBaseDatabaseString();
    Assert.assertEquals(databaseString, "MongoDatabaseTest", "database string do not match");
  }

  /**
   * Gets the connection string.
   */
  @Test(groups = TestCategories.MONGO)
  public void testGetBaseMongoCollectionStringTest() {
    String collection = this.getBaseCollectionString();
    Assert.assertEquals(collection, "MongoTestCollection", "collection strings do not match");
  }
}
