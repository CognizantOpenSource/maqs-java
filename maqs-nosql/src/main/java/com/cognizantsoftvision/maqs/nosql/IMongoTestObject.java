/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.nosql;

import com.cognizantsoftvision.maqs.base.ITestObject;
import com.mongodb.client.MongoCollection;
import java.util.function.Supplier;
import org.bson.Document;

/**
 * The Mongo Test Object interface.
 */
public interface IMongoTestObject extends ITestObject {

  /**
   * Gets the Mongo driver.
   * @return the mongo database driver
   */
  MongoDBDriver getMongoDBDriver();

  /**
   * Gets the Mongo driver manager.
   * @return the mongo database manager
   */
  MongoDriverManager getMongoDBManager();

  /**
   * Override the Mongo driver a collection function.
    * @param overrideCollectionConnection The collection function
   */
  void overrideMongoDBDriver(Supplier<MongoCollection<Document>> overrideCollectionConnection);

  /**
   * Override the Mongo driver settings.
   * @param driver New Mongo driver
   */
  void overrideMongoDBDriver(MongoDBDriver driver);

  /**
   * Override the Mongo driver settings.
   * @param connectionString Client connection string
   * @param databaseString Database connection string
   * @param collectionString Mongo collection string
   */
  void overrideMongoDBDriver(String connectionString, String databaseString, String collectionString);
}
