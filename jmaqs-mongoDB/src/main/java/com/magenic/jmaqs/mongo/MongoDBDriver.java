/*
 * Copyright 2020 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

/**
 * Class to wrap the MongoCollection and related helper functions
 */
public class MongoDBDriver {
  /**
   * Initializes a new instance of the MongoDBDriver class
   * @param collection The collection object
   */
  public MongoDBDriver(MongoCollection<Document> collection) {
    setCollection(collection);
  }

  /**
   * Initializes a new instance of the MongoDBDriver class
   * @param connectionString Server address
   * @param databaseString Name of the database
   * @param collectionString Name of the collection
   */
  public MongoDBDriver(String connectionString, String databaseString, String collectionString) {
    setCollection(MongoFactory.getCollection(connectionString, databaseString, collectionString));
  }

  /**
   * Initializes a new instance of the MongoDBDriver class.
   * @param collectionString Name of the collection
   */
  public MongoDBDriver(String collectionString) {
    setCollection(MongoFactory.getCollection(MongoDBConfig.getConnectionString(),
        MongoDBConfig.getDatabaseString(), collectionString));
  }

  /**
   * Initializes a new instance of the MongoDBDriver class.
   */
  public MongoDBDriver() {
    setCollection(MongoFactory.getDefaultCollection());
  }

  /**
   * Gets the client object.
   * @return the mongo client
   */
  public MongoClient getMongoClient() {
      return this.Database.Client;
  }

  private MongoDatabase database;

  /// <summary>
  /// Gets the database object
  /// </summary>
  public MongoDatabase getDatabase() {
      return this.collection.Database;
  }

  private MongoCollection<Document> collection;

  /**
   * Gets the collection object.
   * @return a mongo collection
   */
  public MongoCollection<Document> getCollection() { return collection; }

  /**
   * Sets the Mongo Collection object.
   * @param newCollection the collection to be set
   */
  private void setCollection(MongoCollection<Document> newCollection) {
    this.collection = newCollection;
  }

  /// <summary>
  /// List all of the items in the collection
  /// </summary>
  /// <returns>List of the items in the collection</returns>
  public List<Document> listAllCollectionItems() {
    return this.getCollection().Find<T>(_ => true).ToList();
  }

  /// <summary>
  /// Checks if the collection contains any records
  /// </summary>
  /// <returns>True if the collection is empty, false otherwise</returns>
  public boolean isCollectionEmpty() {
    return !this.getCollection().Find<T>(_ => true).Any();
  }

  /// <summary>
  /// Counts all of the items in the collection
  /// </summary>
  /// <returns>Number of items in the collection</returns>
  public int countAllItemsInCollection() {
    return Integer.parseInt(this.getCollection().CountDocuments(_ => true).ToString());
  }
}
