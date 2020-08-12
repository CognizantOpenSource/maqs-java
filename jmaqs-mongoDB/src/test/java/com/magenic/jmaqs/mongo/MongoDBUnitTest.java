/*
 * Copyright 2020 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.mongo;

import com.magenic.jmaqs.utilities.helper.TestCategories;
import com.mongodb.QueryBuilder;
import com.mongodb.client.MongoCollection;
import org.bson.BsonDocument;
import org.bson.Document;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import java.util.List;

/**
 * Test basic mongo base test functionality
 */
public class MongoDBUnitTest extends BaseMongoTest {
  /**
   * Is the collection override respected.
   */
  @Test(groups = TestCategories.MONGO)
  public void OverrideCollectionFunction() {
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
  public void OverrideConnectionStrings() {
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
  public void OverrideDriver() {
    MongoDBDriver firstDriver = this.getMongoDBDriver();
    MongoDBDriver newDriver = new MongoDBDriver(MongoFactory.getDefaultCollection());
    this.getTestObject().overrideMongoDBDriver(newDriver);

    Assert.assertNotEquals(firstDriver, this.getMongoDBDriver());
    Assert.assertEquals(newDriver, this.getMongoDBDriver());
    Assert.assertFalse(this.getMongoDBDriver().isCollectionEmpty());
  }

  /**
   * Is the test over-ridable with a custom driver.
   */
  @Test(groups = TestCategories.MONGO)
  public void OverrideWithCustomDriver() {
    MongoDBDriver firstDriver = this.getMongoDBDriver();
    MongoDBDriver newDriver = new MongoDBDriver(MongoFactory.getCollection(MongoDBConfig.getConnectionString(),
        MongoDBConfig.getDatabaseString(), null, MongoDBConfig.getCollectionString()));
    this.getTestObject().overrideMongoDBDriver(newDriver);

    Assert.assertNotEquals(firstDriver, this.getMongoDBDriver());
    Assert.assertEquals(newDriver, this.getMongoDBDriver());
    Assert.assertFalse(this.getMongoDBDriver().isCollectionEmpty());
  }

  /**
   * Test the list all collection items helper function.
   */
  @Test(groups = TestCategories.MONGO)
  public void TestMongoListAllCollectionItems() {
    List<Document> collectionItems = this.getMongoDBDriver().listAllCollectionItems();
    for (Document bson : collectionItems){
      Assert.assertTrue(bson.containsKey("lid"));
    }

    Assert.assertEquals(4, collectionItems.size());
  }

  /**
   * Test the count all collection items helper function
   */
  @Test(groups = TestCategories.MONGO)
  public void TestMongoCountItemsInCollection() {
    Assert.assertEquals(4, this.getMongoDBDriver().countAllItemsInCollection());
  }


  ///**
  // * Test the collection works as expected.
  // */
  //@Test(groups = TestCategories.MONGO)
  //public void TestMongoGetLoginID() {
  //  var filter = Builders.Filter.Eq("lid", "test3");
//
  //  String value = this.getMongoDBDriver().getCollection().find(filter).ToList()[0]["lid"].ToString();
  //  Assert.assertEquals("test3", value);
  //}
//
  ///**
  // * Test the collection works as expected.
  // */
  //@Test(groups = TestCategories.MONGO)
  //public void TestMongoQueryAndReturnFirst() {
  //  var filter = Builders.Filter.Eq("lid", "test3");
  //  MongoCollection<Document> document = this.getMongoDBDriver().getCollection().Find(filter).ToList().First();
  //  Assert.assertEquals("test3", document["lid"].ToString());
  //}
//
  ///**
  // * Test the collection works as expected
  // */
  //@Test(groups = TestCategories.MONGO)
  //public void TestMongoFindListWithKey() {
  //  var filter = Builders<Document>.Filter.Exists("lid");
  //  List<Document> documentList = this.getMongoDBDriver().getCollection().find(filter);
  //  for (Document documents : documentList) {
  //    Assert.assertNotEquals(documents.get("lid").toString(), "");
  //  }
//
  //  Assert.assertEquals(4, documentList.size());
  //}
//
  ///**
  // * Test the collection works as expected.
  // */
  //@Test(groups = TestCategories.MONGO)
  //public void TestMongoLinqQuery() {
  //  QueryBuilder<BsonDocument> query =
  //      from e in this.getMongoDBDriver().getCollection().Collection.AsQueryable<BsonDocument>()
  //  where e["lid"] == "test1"
  //  select e;
  //  List<BsonDocument> retList = query.ToList<BsonDocument>();
  //  for (var value : retList) {
  //    Assert.assertEquals("test1", value["lid"]);
  //  }
  //}

  /**
   * Make sure the test objects map properly.
   */
  @Test(groups = TestCategories.MONGO)
  public void TestMongoDBTestObjectMapCorrectly() {
    Assert.assertEquals(this.getTestObject().getLogger(), this.getLogger(), "Logs don't match");
    Assert.assertEquals(this.getTestObject().getPerfTimerCollection(), this.getPerfTimerCollection(), "Soft asserts don't match");
    Assert.assertEquals(this.getTestObject().getMongoDBDriver(), this.getMongoDBDriver(), "Web service driver don't match");
  }

  /**
   * Steps to do before logging teardown results.
   *
   * @param resultType The test result
   */
  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {

  }

  @Override
  protected void createNewTestObject() {

  }
}
