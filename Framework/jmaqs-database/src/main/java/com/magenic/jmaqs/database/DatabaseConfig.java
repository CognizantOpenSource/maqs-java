/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.database;

import com.magenic.jmaqs.database.constants.DataProviderType;
import com.magenic.jmaqs.database.providers.IDataSourceProvider;
import com.magenic.jmaqs.database.providers.SQLProvider;
import com.magenic.jmaqs.database.providers.SQLiteProvider;
import com.magenic.jmaqs.utilities.helper.Config;
import com.magenic.jmaqs.utilities.helper.ConfigSection;
import java.util.HashMap;
import java.util.Map;

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
   * Field DATABASE_CAPS_SECTION
   */
  private static final ConfigSection DATABASE_CAPS_SECTION = ConfigSection.DatabaseCapsMaqs;

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

  public static String getEntityDirectoryString() {
    return Config.getValueForSection(DATABASE_SECTION, "EntityDirectory");
  }

  /**
   * Method getProviderType returns the providerType of this DatabaseConfig object.
   *
   * @return the providerType (type DataProviderType) of this DatabaseConfig object.
   */
  public static DataProviderType getProviderType() {
    return getProviderType(getProviderTypeString());
  }

  /**
   * Method getProviderType ...
   *
   * @param providerTypeString of type String
   * @return DataProviderType
   */
  public static DataProviderType getProviderType(String providerTypeString) {
    return DataProviderType.find(providerTypeString);
  }

  /**
   * Method getProvider returns the provider of this DatabaseConfig object.
   *
   * @return the provider (type IDataSourceProvider) of this DatabaseConfig object.
   */
  public static IDataSourceProvider getProvider() {
    return getProvider(getProviderType());
  }

  /**
   * Method getProvider ...
   *
   * @param dataProviderType of type DataProviderType
   * @return IDataSourceProvider
   */
  public static IDataSourceProvider getProvider(DataProviderType dataProviderType) {

    IDataSourceProvider dataSourceProvider;

    switch (dataProviderType) {
      case SQL:
        dataSourceProvider = new SQLProvider(getConnectionString());
        break;
      case SQLITE:
        dataSourceProvider = new SQLiteProvider(getConnectionString());
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + getProviderType());
    }

    return dataSourceProvider;
  }

  /**
   * Method getDatabaseCapabilitiesAsObjects returns the databaseCapabilitiesAsObjects of this DatabaseConfig object.
   *
   * @return the databaseCapabilitiesAsObjects (type Map of String keys and Object values) of this DatabaseConfig object.
   */
  public static Map<String, Object> getDatabaseCapabilitiesAsObjects() {
    return new HashMap<>(getDatabaseCapabilitiesAsStrings());
  }

  /**
   * Method getDatabaseCapabilitiesAsStrings returns the databaseCapabilitiesAsStrings of this DatabaseConfig object.
   *
   * @return the databaseCapabilitiesAsStrings (type Map of String keys and String values) of this DatabaseConfig object.
   */
  public static Map<String, String> getDatabaseCapabilitiesAsStrings() {
    return Config.getSection(DATABASE_CAPS_SECTION);
  }

  /**
   * Method getEntityPackageString returns the entityPackageString of this DatabaseConfig object.
   *
   * @return the entityPackageString (type String) of this DatabaseConfig object.
   */
  public static String getEntityPackageString() {
    return Config.getValueForSection(DATABASE_SECTION, "EntityPackage");
  }

  public static String getName() {
    return Config.getValueForSection(DATABASE_SECTION, "Name", "JMAQS");
  }
}
