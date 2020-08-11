/*
 * Copyright 2020 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.mongo;

import com.magenic.jmaqs.base.BaseTestObject;
import com.magenic.jmaqs.base.DriverManager;
import com.magenic.jmaqs.utilities.logging.LoggingConfig;
import com.magenic.jmaqs.utilities.logging.LoggingEnabled;
import com.magenic.jmaqs.utilities.logging.MessageType;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.function.Supplier;

/**
 * Mongo database driver.
 */
public class MongoDriverManager extends DriverManager<MongoDBDriver> {
  /**
   * Cached copy of the connection driver.
   */
  private MongoDBDriver driver;

  /**
   * Initializes a new instance of the MongoDriverManager class.
   * @param connectionString Client connection string
   * @param databaseString Database connection string
   * @param collectionString Mongo collection string
   * @param testObject Test object this driver is getting added to
   */
  public MongoDriverManager(String connectionString, String databaseString, String collectionString, BaseTestObject testObject) {
    super(() -> (MongoDBDriver) MongoFactory.getCollection(connectionString, databaseString, collectionString), testObject);
  }

  /**
   * Initializes a new instance of the MongoDriverManager class.
   * @param getCollection Function for getting a Mongo collection connection
   * @param testObject Test object this driver is getting added to
   */
  public MongoDriverManager(Supplier<MongoDBDriver> getCollection, BaseTestObject testObject) {
    super(getCollection, testObject);
  }

  /**
   * Override the Mongo driver.
   * @param overrideDriver The new Mongo driver
   */
  public void overrideDriver(MongoDBDriver overrideDriver) {
    this.driver = overrideDriver;
    this.setBaseDriver((MongoDBDriver) overrideDriver.getCollection());
  }

  /**
   * Override the Mongo driver - respects lazy loading.
   * @param connectionString Connection string of mongo DB
   * @param databaseString Database string to use
   * @param collectionString Collection string to use
   */
  public void overrideDriver(String connectionString, String databaseString, String collectionString) {
    this.driver = null;
    this.overrideDriver(() -> MongoFactory.getCollection(connectionString, databaseString, collectionString));
  }

  /**
   * Override the Mongo driver - respects lazy loading.
   * @param overrideCollectionConnection The new collection connection
   */
  public void overrideDriver(Supplier<MongoCollection<Document>> overrideCollectionConnection) {
    this.driver = null;
    this.overrideDriver(overrideCollectionConnection);
  }

  /**
   * Get the Mongo driver
   * @return The Mongo driver
   */
  public MongoDBDriver getMongoDriver() {
    if (this.driver == null) {
      MongoCollection<Document> temp = (MongoCollection<Document>)getBase();

      if (LoggingConfig.getLoggingEnabledSetting() == LoggingEnabled.NO) {
        this.getLogger().logMessage(MessageType.INFORMATION, "Getting Mongo driver");
        this.driver = new MongoDBDriver(temp);
      } else {
        this.getLogger().logMessage(MessageType.INFORMATION, "Getting event firing Mongo driver");
      }
    }

    return this.driver;
  }

  @Override
  public void close() throws Exception { }
}
