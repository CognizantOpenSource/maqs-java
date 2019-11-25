/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.database;

import com.magenic.jmaqs.utilities.helper.Config;
import com.magenic.jmaqs.utilities.helper.ConfigSection;

/**
 * The type Database config.
 */
public class DatabaseConfig {

  private DatabaseConfig() {
  }

  /**
   * The Database section.
   */
  private static final ConfigSection DATABASE_SECTION = ConfigSection.DatabaseMaqs;

  /**
   * Gets connection string.
   *
   * @return the connection string
   */
  public static String getConnectionString() {
    return Config.getValueForSection(DATABASE_SECTION, "DatabaseConnectionString");
  }

  /**
   * Gets provider type string.
   *
   * @return the provider type string
   */
  public static String getProviderTypeString() {
    return Config.getValueForSection(DATABASE_SECTION, "DatabaseProviderType");
  }

}
