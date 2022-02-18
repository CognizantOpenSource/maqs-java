/*
 * Copyright 2022 Cognizant, All rights Reserved
 */

package com.cognizantsoftvision.maqs.nosql;

import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

/**
 * Test mongo base test functionality
 */
public class MongoDBDriverUnitTest extends BaseMongoTest {

  @Test(groups = TestCategories.MONGO)
  public void testMongoDBDriver() {
    MongoCollection<Document> collection = MongoFactory.getDefaultCollection();
    MongoDBDriver driver = new MongoDBDriver(collection);
    Assert.assertNotNull(driver);

    driver = new MongoDBDriver(MongoDBConfig.getConnectionString(),
        MongoDBConfig.getDatabaseString(), MongoDBConfig.getConnectionString());
    Assert.assertNotNull(driver);

    driver = new MongoDBDriver(MongoDBConfig.getCollectionString());
    Assert.assertNotNull(driver);
  }

  @Test(groups = TestCategories.MONGO)
  public void testGetMongoClient() {
    this.setMongoDBDriver(new MongoDBDriver());
    MongoClient client = this.getMongoDBDriver().getMongoClient();
    Assert.assertNotNull(client);
  }

  @Test(groups = TestCategories.MONGO)
  public void testSetMongoClient() {
    this.setMongoDBDriver(new MongoDBDriver());
    this.getMongoDBDriver().setMongoClient(MongoDBConfig.getConnectionString());
    Assert.assertNotNull(this.getMongoDBDriver().getMongoClient());
  }

  /**
   * Test the list all collection items helper function.
   */
  @Test(groups = TestCategories.MONGO)
  public void testListAllCollectionItems() {
    this.setMongoDBDriver(new MongoDBDriver());
    List<Document> collectionItems = this.getMongoDBDriver().listAllCollectionItems();
    for (Document bson : collectionItems){
      Assert.assertTrue(bson.containsKey("lid"));
    }

    Assert.assertEquals(collectionItems.size(), 4);
  }

  @Test(groups = TestCategories.MONGO)
  public void testIsCollectionEmpty() {
    boolean collection = this.getMongoDBDriver().isCollectionEmpty();
    Assert.assertTrue(collection);
  }

  /**
   * Test the count all collection items helper function
   */
  @Test(groups = TestCategories.MONGO)
  public void testCountAllItemsInCollection() {
    Assert.assertEquals(this.getMongoDBDriver().countAllItemsInCollection(), 4);
  }
}
