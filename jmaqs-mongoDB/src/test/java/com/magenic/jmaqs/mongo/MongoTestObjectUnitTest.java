/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.mongo;

import com.magenic.jmaqs.utilities.helper.TestCategories;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MongoTestObjectUnitTest extends BaseMongoTest{
  private final MongoCollection<Document> collection = this.getMongoDBDriver().getCollection();
  private final MongoCollection<Document> newCollection = MongoFactory.getDefaultCollection();

  /**
   * Is the collection override respected.
   */
  @Test(groups = TestCategories.MONGO)
  public void overrideCollectionFunction() {
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
    MongoDBDriver newDriver = new MongoDBDriver(MongoFactory.getCollection(MongoDBConfig.getConnectionString(),
        MongoDBConfig.getDatabaseString(), null, MongoDBConfig.getCollectionString()));
    this.getTestObject().overrideMongoDBDriver(newDriver);

    Assert.assertNotEquals(firstDriver, this.getMongoDBDriver());
    Assert.assertEquals(newDriver, this.getMongoDBDriver());
    Assert.assertFalse(this.getMongoDBDriver().isCollectionEmpty());
  }
}
