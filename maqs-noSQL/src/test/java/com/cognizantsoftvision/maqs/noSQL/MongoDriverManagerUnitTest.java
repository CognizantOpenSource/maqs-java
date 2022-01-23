/*
 * Copyright 2022 Cognizant, All rights Reserved
 */

package com.cognizantsoftvision.maqs.noSQL;

import com.magenic.jmaqs.utilities.helper.TestCategories;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test the mongo driver manager functionality.
 */
public class MongoDriverManagerUnitTest extends BaseMongoTest{

  /**
   * Override with default driver.
   */
  @Test(groups = TestCategories.MONGO)
  public void respectDefaultDriverOverride() {
    MongoDBDriver mongoDriver = new MongoDBDriver();
    this.getTestObject().getMongoDBManager().overrideDriver(mongoDriver);
    Assert.assertEquals(mongoDriver.getCollection(), this.getMongoDBDriver().getCollection());
    Assert.assertEquals(mongoDriver.getDatabase(), this.getMongoDBDriver().getDatabase());
    Assert.assertEquals(mongoDriver.getMongoClient(), this.getMongoDBDriver().getMongoClient());
  }

  /**
   * Override driver with collection string.
   */
  @Test(groups = TestCategories.MONGO)
  public void respectCollectionDriverOverride() {
    // MongoDBConfig.GetConnectionString(), MongoDBConfig.GetDatabaseString(), collectionString
    MongoDBDriver mongoDriver = new MongoDBDriver(MongoDBConfig.getCollectionString());
    this.getTestObject().getMongoDBManager().overrideDriver(mongoDriver);
    Assert.assertEquals(mongoDriver.getCollection(), this.getMongoDBDriver().getCollection());
  }

  /**
   * Override drive with all 3 connection strings.
   */
  @Test(groups = TestCategories.MONGO)
  public void RespectDriverConnectionsOverride() {
    MongoDBDriver mongoDriver = new MongoDBDriver(MongoDBConfig.getConnectionString(),
        MongoDBConfig.getDatabaseString(), MongoDBConfig.getCollectionString());
    this.getTestObject().getMongoDBManager().overrideDriver(mongoDriver);
    Assert.assertEquals(mongoDriver.getCollection(), this.getMongoDBDriver().getCollection());
  }

  /**
   * Override driver directly.
   */
  @Test(groups = TestCategories.MONGO)
  public void RespectDirectDriverOverride() {
    MongoDBDriver mongoDriver = new MongoDBDriver(MongoDBConfig.getCollectionString());
    this.setMongoDBDriver(mongoDriver);
    Assert.assertEquals(mongoDriver.getCollection(), this.getMongoDBDriver().getCollection());
  }

  /**
   * Override driver with new driver.
   */
  @Test(groups = TestCategories.MONGO)
  public void RespectNewDriverOverride() {
    MongoDBDriver mongoDriver = new MongoDBDriver(MongoDBConfig.getCollectionString());
    this.getTestObject().overrideMongoDBDriver(mongoDriver);
    Assert.assertEquals(mongoDriver.getCollection(), this.getMongoDBDriver().getCollection());
  }

  /**
   * Override drive with collection function.
   */
  @Test(groups = TestCategories.MONGO)
  public void RespectCollectionOverride() {
    MongoCollection<Document> collection = MongoFactory.getDefaultCollection();
    this.getTestObject().overrideMongoDBDriver(() -> collection);
    Assert.assertEquals(collection, this.getMongoDBDriver().getCollection());
  }

  /**
   * Override drive with all 3 connection strings.
   */
  @Test(groups = TestCategories.MONGO)
  public void RespectDriverConnectionStingsOverride() {
    MongoCollection<Document> collection = this.getMongoDBDriver().getCollection();
    this.getTestObject().overrideMongoDBDriver(MongoDBConfig.getConnectionString(),
        MongoDBConfig.getDatabaseString(), MongoDBConfig.getCollectionString());
    Assert.assertNotEquals(collection, this.getMongoDBDriver().getCollection());
  }

  /**
   * Override in base with collection function.
   */
  @Test(groups = TestCategories.MONGO)
  public void RespectCollectionOverrideInBase() {
    MongoCollection<Document> collection = MongoFactory.getDefaultCollection();
    this.overrideConnectionDriver(() -> collection);
    Assert.assertEquals(collection, this.getMongoDBDriver().getCollection());
  }

  /**
   * Override in base with new driver.
   */
  @Test(groups = TestCategories.MONGO)
  public void RespectDriverOverrideInBase() {
    MongoCollection<Document> collection = MongoFactory.getDefaultCollection();
    this.overrideConnectionDriver(new MongoDBDriver(collection));
    Assert.assertEquals(collection, this.getMongoDBDriver().getCollection());
  }

  /**
   * Override drive with strings in base.
   */
  @Test(groups = TestCategories.MONGO)
  public void RespectConnectionStingsOverrideInBase() {
    MongoCollection<Document> collection = this.getMongoDBDriver().getCollection();
    this.overrideConnectionDriver(MongoDBConfig.getConnectionString(),
        MongoDBConfig.getDatabaseString(), MongoDBConfig.getCollectionString());
    Assert.assertNotEquals(collection, this.getMongoDBDriver().getCollection());
  }
}
