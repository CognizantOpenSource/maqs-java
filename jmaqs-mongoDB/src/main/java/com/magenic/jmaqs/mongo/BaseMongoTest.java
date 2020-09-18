/*
 * Copyright 2020 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.mongo;

import com.magenic.jmaqs.base.BaseExtendableTest;
import org.testng.ITestResult;

/**
 * Generic base MongoDB test class.
 */
public class BaseMongoTest extends BaseExtendableTest<MongoTestObject> {
  /**
   * Initializes a new instance of the BaseMongoTest class,
   * Setup the database client for each test class.
   */
  public BaseMongoTest() {
    // Currently not populated with any logic
  }

  /**
   * Gets the mongoDB driver.
   * @return the mongoDB driver
   */
  public MongoDBDriver getMongoDBDriver() {
    return this.getTestObject().getMongoDBDriver();
  }

  /**
   * Sets the MongoDB driver
   * @param value the MongoDB driver to be set.
   */
  public void setMongoDBDriver(MongoDBDriver value) {
    this.getTestObject().overrideMongoDBDriver(value);
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

  /**
   * Steps to do before logging teardown results.
   * @param resultType The test result
   */
  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {
    // Currently not populated with any logic
      }

  @Override
  protected void createNewTestObject() {
    this.setTestObject(new MongoTestObject(this.getBaseConnectionString(), this.getBaseDatabaseString(),
        this.getBaseCollectionString(), this.createLogger(), this.getFullyQualifiedTestClassName()));
  }
}
