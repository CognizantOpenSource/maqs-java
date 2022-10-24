/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.nosql;

import static com.mongodb.client.MongoClients.create;

import com.mongodb.MongoClientException;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
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
    try (MongoClient mongoClient = create(connectionString)) {
      MongoDatabase database = mongoClient.getDatabase(databaseString);
      return database.getCollection(collectionString);
    } catch (MongoClientException e) {
      throw new MongoClientException("connection was not created");
    } catch (MongoException e) {
      throw new MongoException("database does not exist");
    }
  }
}
