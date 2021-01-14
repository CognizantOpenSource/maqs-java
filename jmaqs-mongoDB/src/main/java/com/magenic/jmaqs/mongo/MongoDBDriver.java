/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to wrap the MongoCollection and related helper functions
 */
public class MongoDBDriver implements AutoCloseable{
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
   * The Mongo DB client.
   */
  private MongoClient client;

  /**
   * Gets the client object.
   * @return the mongo client
   */
  public MongoClient getMongoClient() {
      return this.client;
  }

  /**
   * Sets the client object.
   * @param newClient the new mongo Client to be set.
   */
  public void setMongoClient(MongoClient newClient) {
    this.client = newClient;
  }

  /**
   * The MongoDB Database.
   */
  private MongoDatabase database;

  /**
   * Gets the database object.
   * @return the MongoDB database object
   */
  public MongoDatabase getDatabase() {
      return this.database;
  }

  /**
   * The MongoDB collection.
   */
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

  /**
   * List all of the items in the collection
   * @return List of the items in the collection
   */
  public List<Document> listAllCollectionItems() {
    return this.getCollection().find().into(new ArrayList<>());
  }

  /**
   * Checks if the collection contains any records.
   * @return True if the collection is empty, false otherwise
   */
  public boolean isCollectionEmpty() {
    return this.getCollection().find() == null;
  }

  /**
   * Counts all of the items in the collection.
   * @return Number of items in the collection
   */
  public int countAllItemsInCollection() {
    return (int) this.getCollection().count();
  }

  @Override public void close() throws Exception {

  }
}
