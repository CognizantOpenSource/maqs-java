/*
 * Copyright 2020 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.mongo;

import com.magenic.jmaqs.base.BaseExtendableTest;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.testng.ITestResult;

import java.util.function.Supplier;

/// <summary>
/// Generic base MongoDB test class
/// </summary>
/// <typeparam name="T">The mongo collection type</typeparam>
public class BaseMongoTest extends BaseExtendableTest<MongoTestObject> {
  /// <summary>
  /// Initializes a new instance of the <see cref="BaseMongoTest{T}"/> class.
  /// Setup the database client for each test class
  /// </summary>
  public BaseMongoTest() { }

  /**
   * Steps to do before logging teardown results.
   *
   * @param resultType The test result
   */
  @Override protected void beforeLoggingTeardown(ITestResult resultType) {

  }

  @Override protected void createNewTestObject() {

  }

  /// <summary>
  /// Gets or sets the web service driver
  /// </summary>
  public MongoDBDriver getMongoDBDriver() {
    return this.getTestObject().getMongoDBDriver();
  }

  public void setMongoDBDriver(MongoDBDriver value) {
    this.getTestObject().overrideMongoDBDriver(value);
  }

  /// <summary>
  /// Override the Mongo driver - does not lazy load
  /// </summary>
  /// <param name="driver">New Mongo driver</param>
  public void overrideConnectionDriver(MongoDBDriver driver) {
    this.getTestObject().overrideMongoDBDriver(driver);
  }

  /// <summary>
  /// Override the Mongo driver - respects lazy loading
  /// </summary>
  /// <param name="overrideCollectionConnection">The new collection connection</param>
  public void overrideConnectionDriver(
      Supplier<MongoCollection<Document>> overrideCollectionConnection) {
    this.getTestObject().overrideMongoDBDriver(overrideCollectionConnection);
  }

  /// <summary>
  /// Override the Mongo driver  - respects lazy loading
  /// </summary>
  /// <param name="connectionString">Client connection string</param>
  /// <param name="databaseString">Database connection string</param>
  /// <param name="collectionString">Mongo collection string</param>
  public void overrideConnectionDriver(String connectionString, String databaseString, String collectionString) {
    this.getTestObject().overrideMongoDBDriver(connectionString, databaseString, collectionString);
  }

  /// <summary>
  /// Get the base web service url
  /// </summary>
  /// <returns>The base web service url</returns>
  protected String getBaseConnectionString() {
    return MongoDBConfig.getConnectionString();
  }

  /// <summary>
  /// Get the base web service url
  /// </summary>
  /// <returns>The base web service url</returns>
  protected String getBaseDatabaseString() {
    return MongoDBConfig.getDatabaseString();
  }

  /// <summary>
  /// Get the base web service url
  /// </summary>
  /// <returns>The base web service url</returns>
  protected String getBaseCollectionString() {
    return MongoDBConfig.getCollectionString();
  }
}
