/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.nosql;

import static com.mongodb.client.MongoClients.create;

import com.mongodb.MongoClientException;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * The Mongo Factory Class.
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
    MongoDatabase database;

    try (MongoClient mongoClient = create(connectionString)) {
      database = mongoClient.getDatabase(databaseString);
      return database.getCollection(collectionString);
    } catch (MongoClientException e) {
      throw new MongoClientException("connection was not created");
    } catch (MongoException e) {
      throw new MongoException("database does not exist");
    }

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
    MongoDatabase database;

    try (MongoClient mongoClient = create(settings)) {
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
