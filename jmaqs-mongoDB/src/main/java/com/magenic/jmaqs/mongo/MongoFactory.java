/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Mongo database factory.
 */
public class MongoFactory {

  private MongoFactory() {}

  /**
   * Get the email client using connection information from the test run configuration.
   * @return The email connection
   */
  public static MongoCollection<Document> getDefaultCollection(){
    return getCollection(MongoDBConfig.getConnectionString(),
        MongoDBConfig.getDatabaseString(), MongoDBConfig.getCollectionString());
  }

  /**
   * Get the email client using connection information from the test run configuration.
   * @param connectionString the connection string
   * @param databaseString the database string
   * @param collectionString the collection string
   * @return The email connection
   */
  public static MongoCollection<Document> getCollection(String connectionString, String databaseString, String collectionString) {
    MongoDatabase database;
    try (MongoClient connection = new MongoClient(new MongoClientURI(connectionString))) {
      database = connection.getDatabase(databaseString);
    } catch (Exception e) {
    throw new MongoException("connection was not created");
  }
    return database.getCollection(collectionString);
  }

  /**
   * Get the email client using connection information from the test run configuration.
   * @param connectionString the connection string
   * @param databaseString the database string
   * @param settings the mongoDB settings to be set
   * @param collectionString the collection string
   * @return The email connection
   */
  public static MongoCollection<Document> getCollection(String connectionString, String databaseString,
      MongoClientOptions settings, String collectionString) {
    MongoDatabase database;
    try (MongoClient connection = new MongoClient(connectionString, settings)) {
      database = connection.getDatabase(databaseString);
    } catch (Exception e) {
      throw new MongoException("connection was not created");
    }
      return database.getCollection(collectionString);
  }
}
