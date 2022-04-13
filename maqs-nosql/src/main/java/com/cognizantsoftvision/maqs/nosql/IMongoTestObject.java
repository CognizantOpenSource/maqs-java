/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.nosql;

import com.cognizantsoftvision.maqs.base.ITestObject;
import com.mongodb.client.MongoCollection;
import java.util.function.Supplier;
import org.bson.Document;

/// <summary>
/// Mongo test object interface
/// </summary>
public interface IMongoTestObject extends ITestObject {

  /// <summary>
  /// Gets the Mongo driver
  /// </summary>
  MongoDBDriver getMongoDBDriver();

  /// <summary>
  /// Gets the Mongo driver manager
  /// </summary>
  MongoDriverManager getMongoDBManager();

  /// <summary>
  /// Override the Mongo driver a collection function
  /// </summary>
  /// <param name="overrideCollectionConnection">The collection function</param>
  void overrideMongoDBDriver(Supplier<MongoCollection<Document>> overrideCollectionConnection);

  /// <summary>
  /// Override the Mongo driver settings
  /// </summary>
  /// <param name="driver">New Mongo driver</param>
  void overrideMongoDBDriver(MongoDBDriver driver);

  /// <summary>
  /// Override the Mongo driver settings
  /// </summary>
  /// <param name="connectionString">Client connection string</param>
  /// <param name="databaseString">Database connection string</param>
  /// <param name="collectionString">Mongo collection string</param>
  void overrideMongoDBDriver(String connectionString, String databaseString, String collectionString);
}
