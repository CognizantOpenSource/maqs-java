/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.mongo;

import com.magenic.jmaqs.utilities.helper.TestCategories;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MongoDriverManagerUnitTest extends BaseMongoTest{

  @Test(groups = TestCategories.MONGO)
  public void testMongoDriverManager() {
  MongoDriverManager manager = new MongoDriverManager(MongoDBConfig.getConnectionString(),
      MongoDBConfig.getDatabaseString(),MongoDBConfig.getCollectionString(), this.getTestObject());
  Assert.assertNotNull(manager);
  }

  /// <summary>
  /// Override driver with collection string
  /// </summary>
  @Test(groups = TestCategories.MONGO)
  public void respectCollectionDriverOverride() {
    //MongoDBConfig.GetConnectionString(), MongoDBConfig.GetDatabaseString(), collectionString
    MongoDBDriver mongoDriver = new MongoDBDriver(MongoDBConfig.getCollectionString());
    this.getTestObject().getMongoDBManager().overrideDriver(mongoDriver);
    Assert.assertEquals(mongoDriver.getCollection(), this.getMongoDBDriver().getCollection());
  }

  /// <summary>
  /// Override drive with all 3 connection strings
  /// </summary>
  @Test(groups = TestCategories.MONGO)
  public void respectDriverConnectionsOverride() {
    MongoDBDriver mongoDriver = new MongoDBDriver(MongoDBConfig.getConnectionString(),
        MongoDBConfig.getDatabaseString(), MongoDBConfig.getCollectionString());
    this.getTestObject().getMongoDBManager().overrideDriver(mongoDriver);
    Assert.assertEquals(mongoDriver.getCollection(), this.getMongoDBDriver().getCollection());
  }

  /// <summary>
  /// Override driver directly
  /// </summary>
  @Test(groups = TestCategories.MONGO)
  public void respectDirectDriverOverride() {
    MongoDBDriver mongoDriver = new MongoDBDriver(MongoDBConfig.getCollectionString());
    this.setMongoDBDriver(mongoDriver);
    Assert.assertEquals(mongoDriver.getCollection(), this.getMongoDBDriver().getCollection());
  }

  /// <summary>
  /// Override driver with new driver
  /// </summary>
  @Test(groups = TestCategories.MONGO)
  public void respectNewDriverOverride() {
    MongoDBDriver mongoDriver = new MongoDBDriver(MongoDBConfig.getCollectionString());
    this.getTestObject().overrideMongoDBDriver(mongoDriver);
    Assert.assertEquals(mongoDriver.getCollection(), this.getMongoDBDriver().getCollection());
  }

  /// <summary>
  /// Override drive with collection function
  /// </summary>
  @Test(groups = TestCategories.MONGO)
  public void respectCollectionOverride() {
    MongoCollection<Document> collection = MongoFactory.getDefaultCollection();
    this.getTestObject().overrideMongoDBDriver(() -> collection);
    Assert.assertEquals(collection, this.getMongoDBDriver().getCollection());
  }

  /// <summary>
  /// Override drive with all 3 connection strings
  /// </summary>
  @Test(groups = TestCategories.MONGO)
  public void respectDriverConnectionStingsOverride() {
    MongoCollection<Document> collection = this.getMongoDBDriver().getCollection();
    this.getTestObject().overrideMongoDBDriver(MongoDBConfig.getConnectionString(),
        MongoDBConfig.getDatabaseString(), MongoDBConfig.getCollectionString());
    Assert.assertNotEquals(collection, this.getMongoDBDriver().getCollection());
  }

  /// <summary>
  /// Override in base with collection function
  /// </summary>
  @Test(groups = TestCategories.MONGO)
  public void respectCollectionOverrideInBase() {
    MongoCollection<Document> collection = MongoFactory.getDefaultCollection();
    this.overrideConnectionDriver(new MongoDBDriver(collection));
    Assert.assertEquals(collection, this.getMongoDBDriver().getCollection());
  }

  /// <summary>
  /// Override in base with new driver
  /// </summary>
  @Test(groups = TestCategories.MONGO)
  public void respectDriverOverrideInBase() {
    MongoCollection<Document> collection = MongoFactory.getDefaultCollection();
    this.overrideConnectionDriver(new MongoDBDriver(collection));
    Assert.assertEquals(collection, this.getMongoDBDriver().getCollection());
  }

  /// <summary>
  /// Override drive with strings in base
  /// </summary>
  @Test(groups = TestCategories.MONGO)
  public void testRespectConnectionStingsOverrideInBase() {
    MongoCollection<Document> collection = this.getMongoDBDriver().getCollection();
    this.overrideConnectionDriver(MongoDBConfig.getConnectionString(),
        MongoDBConfig.getDatabaseString(), MongoDBConfig.getCollectionString());
    Assert.assertNotEquals(collection, this.getMongoDBDriver().getCollection());
  }

  /**
   * Override with default driver
   */
  @Test(groups = TestCategories.MONGO)
  public void respectDefaultDriverOverride() {
    MongoDBDriver mongoDriver = new MongoDBDriver();
    this.getTestObject().getMongoDBManager().overrideDriver(mongoDriver);

    Assert.assertEquals(mongoDriver.getCollection(), this.getMongoDBDriver().getCollection());
    Assert.assertEquals(mongoDriver.getDatabase(), this.getMongoDBDriver().getDatabase());
    Assert.assertEquals(mongoDriver.getMongoClient(), this.getMongoDBDriver().getMongoClient());
  }
}
