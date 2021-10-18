/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.mongo;

import com.magenic.jmaqs.utilities.helper.TestCategories;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test the mongo test object functionality.
 */
public class MongoTestObjectUnitTest extends BaseMongoTest {

  /**
   * Is the collection override respected.
   */
  @Test(groups = TestCategories.MONGO)
  public void overrideCollectionFunction() {
    MongoCollection<Document> collection = this.getMongoDBDriver().getCollection();
    MongoCollection<Document> newCollection = MongoFactory.getDefaultCollection();

    this.getTestObject().overrideMongoDBDriver(() -> newCollection);
    Assert.assertNotEquals(collection, this.getMongoDBDriver().getCollection());
    Assert.assertEquals(newCollection, this.getMongoDBDriver().getCollection());
    Assert.assertFalse(this.getMongoDBDriver().isCollectionEmpty());
  }

  /**
   * Are the connection string overrides respected.
   */
  @Test(groups = TestCategories.MONGO)
  public void overrideConnectionStrings() {
    MongoCollection<Document> collection = this.getMongoDBDriver().getCollection();
    this.getTestObject().overrideMongoDBDriver(MongoDBConfig.getConnectionString(),
        MongoDBConfig.getDatabaseString(), MongoDBConfig.getCollectionString());

    Assert.assertNotEquals(collection, this.getMongoDBDriver().getCollection());
    Assert.assertFalse(this.getMongoDBDriver().isCollectionEmpty());
  }

  /**
   * Is the driver override respected.
   */
  @Test(groups = TestCategories.MONGO)
  public void overrideDriver() {
    MongoDBDriver firstDriver = this.getMongoDBDriver();
    MongoDBDriver newDriver = new MongoDBDriver(MongoFactory.getDefaultCollection());
    this.getTestObject().overrideMongoDBDriver(newDriver);

    Assert.assertNotEquals(firstDriver, this.getMongoDBDriver());
    Assert.assertEquals(newDriver, this.getMongoDBDriver());
    Assert.assertFalse(this.getMongoDBDriver().isCollectionEmpty());
  }

  /**
   * Is the test overridable with a custom driver.
   */
  @Test(groups = TestCategories.MONGO)
  public void overrideWithCustomDriver() {
    MongoDBDriver firstDriver = this.getMongoDBDriver();

    MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(new ConnectionString(MongoDBConfig.getConnectionString()))
        .build();

    MongoDBDriver newDriver = new MongoDBDriver(settings,
        MongoDBConfig.getDatabaseString(), MongoDBConfig.getCollectionString());
    this.getTestObject().overrideMongoDBDriver(newDriver);

    Assert.assertNotEquals(firstDriver, this.getMongoDBDriver());
    Assert.assertEquals(newDriver, this.getMongoDBDriver());
    Assert.assertFalse(this.getMongoDBDriver().isCollectionEmpty());
  }

  /// <summary>
  /// Make sure the test objects map properly
  /// </summary>
  @Test(groups = TestCategories.MONGO)
  public void TestMongoDBTestObjectMapCorrectly() {
    Assert.assertEquals(this.getTestObject().getLogger(), this.getLogger(), "Logs don't match");
    Assert.assertEquals(this.getTestObject().getPerfTimerCollection(), this.getPerfTimerCollection(), "Perf Timer Collections don't match");
    Assert.assertEquals(this.getTestObject().getMongoDBDriver(), this.getMongoDBDriver(), "Web service driver don't match");
  }
}
