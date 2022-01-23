/*
 * Copyright 2022 Cognizant, All rights Reserved
 */

package com.magenic.maqs.mongo;

import com.magenic.jmaqs.utilities.helper.Config;
import com.magenic.jmaqs.utilities.helper.ConfigSection;

/**
 * MongoDB config class.
 */
public class MongoDBConfig {

  private MongoDBConfig() {
  }

  /**
   * The MongoDB configuration section.
   */
  private static final ConfigSection MONGO_SECTION = ConfigSection.MONGO_MAQS;

  /**
   * Get the client connection string.
   * @return The connection type
   */
  public static String getConnectionString() {
    return Config.getValueForSection(MONGO_SECTION, "MongoConnectionString");
  }

  /**
   * Get the database connection string.
   * @return The database name
   */
  public static String getDatabaseString() {
    return Config.getValueForSection(MONGO_SECTION, "MongoDatabase");
  }

  /**
   * Get the mongo collection string.
   * @return The mongo collection string
   */
  public static String getCollectionString() {
    return Config.getValueForSection(MONGO_SECTION, "MongoCollection");
  }

  /**
   * Get the database timeout in seconds.
   * @return The timeout in seconds from the config file or default
   *     of 30 seconds when no config.xml key is found
   */
  public static int getQueryTimeout() {
    return Integer.parseInt(Config.getValueForSection(MONGO_SECTION, "MongoTimeout", "30"));
  }
}
