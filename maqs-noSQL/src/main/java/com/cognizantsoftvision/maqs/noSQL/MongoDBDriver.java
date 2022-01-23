/*
 * Copyright 2022 Cognizant, All rights Reserved
 */

package com.cognizantsoftvision.maqs.noSQL;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 * Class to wrap the MongoCollection and related helper functions.
 */
public class MongoDBDriver implements AutoCloseable {

  /**
   * Initializes a new instance of the MongoDBDriver class.
   * @param collection The collection object
   */
  public MongoDBDriver(MongoCollection<Document> collection) {
    setCollection(collection);
  }

  /**
   * Initializes a new instance of the MongoDBDriver class.
   * @param connectionString Server address
   * @param databaseString Name of the database
   * @param collectionString Name of the collection
   */
  public MongoDBDriver(String connectionString, String databaseString, String collectionString) {
    setMongoClient(connectionString);
    setDatabase(this.getMongoClient(), databaseString);
    setCollection(this.getDatabase(), collectionString);
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
   * @param collectionString Name of the collection
   */
  public MongoDBDriver(MongoClientSettings clientSettings, String databaseString, String collectionString) {
    this.setMongoClient(clientSettings);
    setCollection(this.getMongoClient().getDatabase(databaseString).getCollection(collectionString));
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
   * @param connectionString the new mongo Client to be set.
   */
  public void setMongoClient(String connectionString) {
    this.client = MongoClients.create(connectionString);
  }

  public void setMongoClient(MongoClientSettings mongoClientSettings) {
    this.client = MongoClients.create(mongoClientSettings);
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
   * Sets the database object.
   * @param mongoClient the mongo DB client of the database
   * @param mongoDatabase the name of the mongo database
   */
  public void setDatabase(MongoClient mongoClient, String mongoDatabase) {
    this.database = mongoClient.getDatabase(mongoDatabase);
  }

  /**
   * Sets the database object.
   * @param mongoDatabase the name of the mongo database
   */
  public void setDatabase(String mongoDatabase) {
    this.database = this.getMongoClient().getDatabase(mongoDatabase);
  }

  /**
   * The MongoDB collection.
   */
  private MongoCollection<Document> collection;

  /**
   * Gets the collection object.
   * @return a mongo collection
   */
  public MongoCollection<Document> getCollection() {
    return collection;
  }

  /**
   * Sets the Mongo Collection object.
   * @param newCollection the collection to be set
   */
  private void setCollection(MongoCollection<Document> newCollection) {
    this.collection = newCollection;
  }

  /**
   * Sets the Mongo Collection object.
   * @param database the mongo DB database of the collection
   * @param collection the string value of the collection
   */
  private void setCollection(MongoDatabase database, String collection) {
    this.collection = database.getCollection(collection);
  }

  /**
   * List all the items in the collection.
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
    return this.getCollection().countDocuments() == 0;
  }

  /**
   * Counts all the items in the collection.
   * @return Number of items in the collection
   */
  public int countAllItemsInCollection() {
    return (int) this.getCollection().countDocuments();
  }

  @Override
  public void close() {
  }
}
