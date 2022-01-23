/*
 * Copyright 2022 Cognizant, All rights Reserved
 */

package com.magenic.maqs.mongo;

import com.mongodb.MongoClientException;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Mongo database factory.
 */
public class MongoFactory {

  private MongoFactory() {
  }

  /**
   * Get the email client using connection information from the test run configuration.
   * @return The email connection
   */
  public static MongoCollection<Document> getDefaultCollection() {
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
  public static MongoCollection<Document> getCollection(
      String connectionString, String databaseString, String collectionString) {
    MongoClient mongoClient;
    MongoDatabase database;

    try {
      mongoClient = MongoClients.create(connectionString);
      database = mongoClient.getDatabase(databaseString);
    } catch (MongoClientException e) {
      throw new MongoClientException("connection was not created");
    } catch (MongoException e) {
      throw new MongoException("database does not exist");
    }
    return database.getCollection(collectionString);
  }

  /**
   * Get the email client using connection information from the test run configuration.
   * @param databaseString the database string
   * @param settings the mongoDB settings to be set
   * @param collectionString the collection string
   * @return The email connection
   */
  public static MongoCollection<Document> getCollection(String databaseString,
      String collectionString, MongoClientSettings settings) {
    MongoClient mongoClient;

    MongoDatabase database;

    try {
      mongoClient = MongoClients.create(settings);
      database = mongoClient.getDatabase(databaseString);
    } catch (Exception e) {
      throw new MongoException("connection was not created: " + e);
    }

    if (database.getName().isEmpty()) {
      throw new MongoException("connection was not created");
    }
    return database.getCollection(collectionString);
  }
}
