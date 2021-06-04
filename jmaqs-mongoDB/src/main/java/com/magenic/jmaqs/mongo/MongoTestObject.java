/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.mongo;

import com.magenic.jmaqs.base.BaseTestObject;
import com.magenic.jmaqs.utilities.logging.Logger;
import com.mongodb.client.MongoCollection;
import java.util.function.Supplier;
import org.bson.Document;

/**
 * Mongo test context data.
 */
public class MongoTestObject extends BaseTestObject {
  /**
   * Initializes a new instance of the MongoTestObject class.
   * @param connectionString Client connection string
   * @param databaseString Database connection string
   * @param collectionString Mongo collection string
   * @param logger The test's logger
   * @param fullyQualifiedTestName The test's fully qualified test name
   */
  public MongoTestObject(String connectionString, String databaseString, String collectionString, Logger logger,
      String fullyQualifiedTestName) {
    super(logger, fullyQualifiedTestName);
    this.getManagerStore().put((MongoDriverManager.class).getCanonicalName(),
            new MongoDriverManager(connectionString,databaseString,collectionString, this));
  }

  /**
   * Gets the Mongo driver manager.
   * @return the mongo driver manager
   */
  public MongoDriverManager getMongoDBManager() {
    return this.getManagerStore().getDriver(MongoDriverManager.class.getCanonicalName());
  }

  /**
   * Gets the Mongo driver.
   * @return the stored mongoDB driver.
   */
  public MongoDBDriver getMongoDBDriver() {
    return this.getMongoDBManager().getMongoDriver();
  }

  /**
   * Override the Mongo driver settings.
   * @param connectionString Client connection string
   * @param databaseString Database connection string
   * @param collectionString Mongo collection string
   */
  public void overrideMongoDBDriver(String connectionString, String databaseString, String collectionString) {
    this.getMongoDBManager().overrideDriver(connectionString, databaseString, collectionString);
  }

  /**
   * Override the Mongo driver settings.
   * @param driver New Mongo driver
   */
  public void overrideMongoDBDriver(MongoDBDriver driver) {
    this.getMongoDBManager().overrideDriver(driver);
  }

  /**
   * Override the Mongo driver a collection function.
   * @param overrideCollectionConnection The collection function
   */
  public void overrideMongoDBDriver(Supplier<MongoCollection<Document>> overrideCollectionConnection) {
    this.getMongoDBManager().overrideDriver(overrideCollectionConnection);
  }
}
