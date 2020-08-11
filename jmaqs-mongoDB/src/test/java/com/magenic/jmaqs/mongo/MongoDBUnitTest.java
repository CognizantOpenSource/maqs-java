/*
 * Copyright 2020 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.mongo;

import com.magenic.jmaqs.utilities.helper.TestCategories;
import com.mongodb.client.MongoCollection;
import org.bson.BsonDocument;
import org.bson.Document;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.util.List;
/// <summary>
/// Test basic mongo base test functionality
/// </summary>
public class MongoDBUnitTest extends BaseMongoTest {
  /// <summary>
  /// Is the collection override respected
  /// </summary>
  @Test(groups = TestCategories.MONGO)
  public void OverrideCollectionFunction() {
    MongoCollection<Document> collection = this.getMongoDBDriver().getCollection();
    MongoCollection<Document> newCollection = MongoFactory.getDefaultCollection();
    this.getTestObject().overrideMongoDBDriver(() -> newCollection);

    Assert.assertNotEquals(collection, this.getMongoDBDriver().getCollection());
    Assert.assertEquals(newCollection, this.getMongoDBDriver().getCollection());
    Assert.assertFalse(this.getMongoDBDriver().isCollectionEmpty());
  }

  /// <summary>
  /// Are the connection string overrides respected
  /// </summary>
  @Test(groups = TestCategories.MONGO)
  public void OverrideConnectionStrings() {
    MongoCollection<Document> collection = this.getMongoDBDriver().getCollection();
    this.getTestObject().overrideMongoDBDriver(MongoDBConfig.getConnectionString(),
        MongoDBConfig.getDatabaseString(), MongoDBConfig.getCollectionString());

    Assert.assertNotEquals(collection, this.getMongoDBDriver().getCollection());
    Assert.assertFalse(this.getMongoDBDriver().isCollectionEmpty());
  }

  /// <summary>
  /// Is the driver override respected
  /// </summary>
  @Test(groups = TestCategories.MONGO)
  public void OverrideDriver() {
    MongoDBDriver firstDriver = this.getMongoDBDriver();
    MongoDBDriver newDriver = new MongoDBDriver(MongoFactory.getDefaultCollection());
    this.getTestObject().overrideMongoDBDriver(newDriver);

    Assert.assertNotEquals(firstDriver, this.getMongoDBDriver());
    Assert.assertEquals(newDriver, this.getMongoDBDriver());
    Assert.assertFalse(this.getMongoDBDriver().isCollectionEmpty());
  }

  /// <summary>
  /// Is the test over-ridable with a custom driver
  /// </summary>
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

  /// <summary>
  /// Test the list all collection items helper function
  /// </summary>
  @Test(groups = TestCategories.MONGO)
  public void TestMongoListAllCollectionItems() {
    List<Document> collectionItems = this.getMongoDBDriver().listAllCollectionItems();
    for (Document bson : collectionItems){
      Assert.assertTrue(bson.containsKey("lid"));
    }

    Assert.assertEquals(4, collectionItems.size());
  }

  /// <summary>
  /// Test the count all collection items helper function
  /// </summary>
  @Test(groups = TestCategories.MONGO)
  public void TestMongoCountItemsInCollection() {
    Assert.assertEquals(4, this.getMongoDBDriver().countAllItemsInCollection());
  }

  /// <summary>
  /// Test the collection works as expected
  /// </summary>
  @Test(groups = TestCategories.MONGO)
  public void TestMongoGetLoginID() {
    var filter = Builders.Filter.Eq("lid", "test3");

    String value = this.getMongoDBDriver().getCollection().find(filter).ToList()[0]["lid"].ToString();
    Assert.assertEquals("test3", value);
  }

  /// <summary>
  /// Test the collection works as expected
  /// </summary>
  @Test(groups = TestCategories.MONGO)
  public void TestMongoQueryAndReturnFirst() {
    var filter = Builders.Filter.Eq("lid", "test3");
    MongoCollection<Document> document = this.getMongoDBDriver().getCollection().Find(filter).ToList().First();
    Assert.assertEquals("test3", document["lid"].ToString());
  }

  /// <summary>
  /// Test the collection works as expected
  /// </summary>
  @Test(groups = TestCategories.MONGO)
  public void TestMongoFindListWithKey() {
    var filter = Builders<BsonDocument>.Filter.Exists("lid");
    List<BsonDocument> documentList = this.MongoDBDriver.Collection.Find(filter).ToList();
    foreach (BsonDocument documents in documentList)
    {
      Assert.AreNotEqual(documents["lid"].ToString(), string.Empty);
    }

    Assert.AreEqual(4, documentList.Count);
  }

  /// <summary>
  /// Test the collection works as expected
  /// </summary>
  @Test(groups = TestCategories.MONGO)
  public void TestMongoLinqQuery() {
    MongoQueryable<BsonDocument> query =
        from e in this.MongoDBDriver.Collection.AsQueryable<BsonDocument>()
    where e["lid"] == "test1"
    select e;
    List<BsonDocument> retList = query.ToList<BsonDocument>();
    foreach (var value in retList)
    {
      Assert.AreEqual("test1", value["lid"]);
    }
  }

  /// <summary>
  /// Make sure the test objects map properly
  /// </summary>
  @Test(groups = TestCategories.MONGO)
  public void TestMongoDBTestObjectMapCorrectly()
  {
    Assert.AreEqual(this.TestObject.Log, this.Log, "Logs don't match");
    Assert.AreEqual(this.TestObject.SoftAssert, this.SoftAssert, "Soft asserts don't match");
    Assert.AreEqual(this.TestObject.PerfTimerCollection, this.PerfTimerCollection, "Soft asserts don't match");
    Assert.AreEqual(this.TestObject.MongoDBDriver, this.MongoDBDriver, "Web service driver don't match");
  }

  /**
   * Steps to do before logging teardown results.
   *
   * @param resultType The test result
   */
  @Override protected void beforeLoggingTeardown(ITestResult resultType) {

  }

  @Override protected void createNewTestObject() {

  }
}
