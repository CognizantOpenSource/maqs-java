/*
 * Copyright 2022 Cognizant, All rights Reserved
 */

package com.cognizantsoftvision.maqs.nosql;

import com.cognizantsoftvision.maqs.base.BaseExtendableTest;
import com.mongodb.client.MongoCollection;
import java.util.function.Supplier;
import org.bson.Document;
import org.testng.ITestResult;

/**
 * The Base Mongo Test class.
 */
public class BaseMongoTest extends BaseExtendableTest<MongoTestObject> {

  /**
   * Initializes a new instance of the BaseMongoTest class,
   * Set up the database client for each test class.
   */
  public BaseMongoTest() {
    // Currently, not populated with any logic
  }

  /**
   * Gets the mongoDB driver.
   * @return the mongoDB driver
   */
  public MongoDBDriver getMongoDBDriver() {
    return this.getTestObject().getMongoDBDriver();
  }

  /**
   * Sets the MongoDB driver.
   * @param driver the MongoDB driver to be set.
   */
  public void setMongoDBDriver(MongoDBDriver driver) {
    this.getTestObject().overrideMongoDBDriver(driver);
  }

  /**
   * Override the Mongo driver - does not lazy load.
   * @param driver New Mongo driver
   */
  public void overrideConnectionDriver(MongoDBDriver driver) {
    this.getTestObject().overrideMongoDBDriver(driver);
  }

  /**
   * Override the Mongo driver  - respects lazy loading.
   * @param overrideCollectionConnection The collection function
   */
  public void overrideConnectionDriver(Supplier<MongoCollection<Document>> overrideCollectionConnection) {
    this.getTestObject().overrideMongoDBDriver(overrideCollectionConnection);
  }

  /**
   * Override the Mongo driver  - respects lazy loading.
   * @param connectionString Client connection string
   * @param databaseString Database connection string
   * @param collectionString Mongo collection string
   */
  public void overrideConnectionDriver(String connectionString, String databaseString, String collectionString) {
    this.getTestObject().overrideMongoDBDriver(connectionString, databaseString, collectionString);
  }

  /**
   * Get the base web service url.
   * @return The base web service url
   */
  protected String getBaseConnectionString() {
    return MongoDBConfig.getConnectionString();
  }

  /**
   * Get the base web service url.
   * @return The base web service url
   */
  protected String getBaseDatabaseString() {
    return MongoDBConfig.getDatabaseString();
  }

  /**
   * Get the base web service url.
   * @return The base web service url
   */
  protected String getBaseCollectionString() {
    return MongoDBConfig.getCollectionString();
  }

  @Override
  protected void createNewTestObject() {
    this.setTestObject(new MongoTestObject(this.getBaseConnectionString(), this.getBaseDatabaseString(),
        this.getBaseCollectionString(), this.createLogger(), this.getFullyQualifiedTestClassName()));
  }

  /**
   * Steps to take before logging teardown results.
   * @param resultType The test result
   */
  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {
    // Currently, not populated with any logic
  }
}
