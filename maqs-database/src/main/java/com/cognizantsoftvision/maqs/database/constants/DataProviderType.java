/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.database.constants;

public enum DataProviderType {
  H2("H2", "org.hibernate.dialect.H2Dialect"),
  MYSQL("MySQL", "org.hibernate.dialect.MySQLDialect"),
  SQL("SQL", "org.hibernate.dialect.SQLServerDialect"),
  SQLITE("SQLite", "org.hibernate.dialect.SQLiteDialect");

  /**
   * Values of DataProviderEnum.  Calling values multiple times is poor performance so holding a local copy.
   */
  private static final DataProviderType[] dataProviderTypes = values();
  private final String name;
  private final String dialectString;

  DataProviderType(String name, String dialectString) {
    this.name = name;
    this.dialectString = dialectString;
  }

  public static DataProviderType find(String providerTypeString) {
    for (DataProviderType dataProviderType : dataProviderTypes) {
      if (dataProviderType.name.equalsIgnoreCase(providerTypeString)) {
        return dataProviderType;
      }
    }
    throw new IllegalArgumentException(
        "No constant with provider type string " + providerTypeString + " found");
  }

  public String getName() {
    return name;
  }

  public String getDialectString() {
    return dialectString;
  }

}
