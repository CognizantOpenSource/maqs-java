/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.mongo;

import com.magenic.jmaqs.utilities.helper.TestCategories;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class MongoDBFunctionalUnitTest extends BaseMongoTest{

  /**
   * Test the collection works as expected.
   */
  @Test(groups = TestCategories.MONGO)
  public void testMongoGetLoginID() {
    Bson filter = Filters.eq("lid", "test3");
    //String value = this.getMongoDBDriver().getCollection().find(filter).ToList()[0]["lid"].ToString();
    List<Document> value = this.getMongoDBDriver().getCollection().find(filter).into(new ArrayList<>());
    Assert.assertEquals(value.get(0).toString(), "test3");
  }

  /**
   * Test the collection works as expected.
   */
  @Test(groups = TestCategories.MONGO)
  public void testMongoQueryAndReturnFirst() {
    Bson filter = Filters.eq("lid", "test3");
    //MongoCollection<Document> document = this.getMongoDBDriver().getCollection().find(filter).ToList().First();
    Document document = this.getMongoDBDriver().getCollection().find(filter).first();
    Assert.assertEquals(document.get("lid").toString(), "test3");
  }

  /**
   * Test the collection works as expected
   */
  @Test(groups = TestCategories.MONGO)
  public void testMongoFindListWithKey() {
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
  public void testMongoLinqQuery() {
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
}
