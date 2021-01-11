/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.mongo;

import com.magenic.jmaqs.utilities.helper.TestCategories;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * Test basic mongo base test functionality
 */
public class MongoDBDriverUnitTest extends BaseMongoTest {
  /**
   * Test the list all collection items helper function.
   */
  @Test(groups = TestCategories.MONGO)
  public void testMongoListAllCollectionItems() {
    List<Document> collectionItems = this.getMongoDBDriver().listAllCollectionItems();
    for (Document bson : collectionItems){
      Assert.assertTrue(bson.containsKey("lid"));
    }

    Assert.assertEquals(collectionItems.size(), 4);
  }

  /**
   * Test the count all collection items helper function
   */
  @Test(groups = TestCategories.MONGO)
  public void testMongoCountItemsInCollection() {
    Assert.assertEquals(this.getMongoDBDriver().countAllItemsInCollection(), 4);
  }


  /**
   * Test the collection works as expected.
   */
  @Test(groups = TestCategories.MONGO)
  public void TestMongoGetLoginID() {
    Bson filter = Filters.eq("lid", "test3");
    //String value = this.getMongoDBDriver().getCollection().find(filter).ToList()[0]["lid"].ToString();
    List<Document> value = this.getMongoDBDriver().getCollection().find(filter).into(new ArrayList<>());
    Assert.assertEquals(value.get(0).toString(), "test3");
  }

  /**
   * Test the collection works as expected.
   */
  @Test(groups = TestCategories.MONGO)
  public void TestMongoQueryAndReturnFirst() {
    Bson filter = Filters.eq("lid", "test3");
    //MongoCollection<Document> document = this.getMongoDBDriver().getCollection().find(filter).ToList().First();
    Document document = this.getMongoDBDriver().getCollection().find(filter).first();
    Assert.assertEquals(document.get("lid").toString(), "test3");
  }

  /**
   * Test the collection works as expected
   */
  @Test(groups = TestCategories.MONGO)
  public void TestMongoFindListWithKey() {
    //var filter = Builders<Document>.Filter.Exists("lid");
    Bson filter = Filters.exists("lid");
    List<Document> documentList = this.getMongoDBDriver().getCollection().find(filter).into(new ArrayList<>());

    for (Document documents : documentList) {
      Assert.assertNotEquals(documents.get("lid").toString(), "");
    }
    Assert.assertEquals(documentList.size(), 4);
  }

  /**
   * Test the collection works as expected.
   */
  @Test(groups = TestCategories.MONGO)
  public void TestMongoLinqQuery() {
    /*
    QueryBuilder querys =
        from e in this.getMongoDBDriver().getCollection()
    where e["lid"] == "test1"
    select e;
     */

    Bson filter = Filters.eq("lid", "test1");
    List<Document> retList = this.getMongoDBDriver().getCollection().find(filter).into(new ArrayList<>());

    for (Document value : retList) {
     Assert.assertEquals(value.get("lid"), "test1");
    }
  }

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
