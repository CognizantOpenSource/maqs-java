/*
 * Copyright 2022 Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.nosql;


import com.cognizantsoftvision.maqs.base.BaseTestObject;
import com.cognizantsoftvision.maqs.base.DriverManager;
import com.mongodb.client.MongoCollection;
import java.util.function.Supplier;
import org.bson.Document;

/**
 * The Mongo Driver Manager class.
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
  public MongoDriverManager(String connectionString, String databaseString,
      String collectionString, BaseTestObject testObject) {
    super(() -> new MongoDBDriver(
        MongoFactory.getCollection(connectionString, databaseString, collectionString)), testObject);
  }

  /** TODO: is this needed?
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
    driver = overrideDriver;
    this.setBaseDriver(new MongoDBDriver(overrideDriver.getCollection()));
  }

  /**
   * Override the Mongo driver - respects lazy loading.
   * @param connectionString Connection string of mongo DB
   * @param databaseString Database string to use
   * @param collectionString Collection string to use
   */
  public void overrideDriver(String connectionString, String databaseString, String collectionString) {
    driver = null;
    this.overrideDriverGet(() -> MongoFactory.getCollection(connectionString, databaseString, collectionString));
    this.overrideDriverGet(() -> MongoFactory.getCollection(connectionString, databaseString, collectionString));
  }

  /**
   * Override the Mongo driver - respects lazy loading.
   * @param overrideCollectionConnection The new collection connection
   */
  public void overrideDriver(Supplier<MongoCollection<Document>> overrideCollectionConnection) {
    driver = null;
    this.overrideDriverGet(overrideCollectionConnection);
  }

  /**
   * Get the Mongo driver.
   * @return The Mongo driver
   */
  public MongoDBDriver getMongoDriver() {
    return this.getBase();
  }

  protected void overrideDriverGet(Supplier<MongoCollection<Document>> driverGet) {
    this.setBaseDriver(new MongoDBDriver(driverGet.get()));
  }

  public void close() {
    if (!this.isDriverInitialized()) {
      return;
    }
    MongoDBDriver mongoDBDriver = this.getMongoDriver();
    mongoDBDriver.getMongoClient().close();
    this.baseDriver = null;
  }
}
