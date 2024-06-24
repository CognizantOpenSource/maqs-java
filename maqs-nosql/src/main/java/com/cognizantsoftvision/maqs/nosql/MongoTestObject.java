/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.nosql;

import com.cognizantsoftvision.maqs.base.BaseTestObject;
import com.cognizantsoftvision.maqs.utilities.logging.Logger;
import com.mongodb.client.MongoCollection;
import java.util.function.Supplier;
import org.bson.Document;

/**
 * The Mongo Test Object class.
 */
public class MongoTestObject extends BaseTestObject implements IMongoTestObject {

  /**
   * Initializes a new instance of the MongoTestObject class.
   * @param connectionString Client connection string
   * @param databaseString Database connection string
   * @param collectionString Mongo collection string
   * @param logger The test's logger
   * @param fullyQualifiedTestName The test's fully qualified test name
   */
  public MongoTestObject(String connectionString, String databaseString, String collectionString,
      Logger logger, String fullyQualifiedTestName) {
    super(logger, fullyQualifiedTestName);
    this.getManagerStore().put((MongoDriverManager.class).getCanonicalName(),
            new MongoDriverManager(connectionString,databaseString,collectionString, this));
  }

  /**
   * {@inheritDoc}
   */
  public MongoDriverManager getMongoDBManager() {
    return this.getManagerStore().getDriver(MongoDriverManager.class.getCanonicalName());
  }

  /**
   * {@inheritDoc}
   */
  public MongoDBDriver getMongoDBDriver() {
    return this.getMongoDBManager().getMongoDriver();
  }

  /**
   * {@inheritDoc}
   */
  public void overrideMongoDBDriver(String connectionString, String databaseString, String collectionString) {
    this.getMongoDBManager().overrideDriver(connectionString, databaseString, collectionString);
  }

  /**
   * {@inheritDoc}
   */
  public void overrideMongoDBDriver(MongoDBDriver driver) {
    this.getManagerStore().put(MongoDriverManager.class.getCanonicalName(),
        new MongoDriverManager(() -> driver, this));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void overrideMongoDBDriver(Supplier<MongoCollection<Document>> overrideCollectionConnection) {
    this.getMongoDBManager().overrideDriver(overrideCollectionConnection);
  }
}
