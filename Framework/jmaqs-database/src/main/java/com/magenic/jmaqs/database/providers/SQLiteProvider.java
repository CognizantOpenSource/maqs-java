/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.database.providers;

import com.magenic.jmaqs.database.constants.DataProviderType;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;

public class SQLiteProvider implements IDataSourceProvider {

  private String dbUrl;
  private static final DataProviderType dataProviderType = DataProviderType.SQLITE;

  public SQLiteProvider(String dbUrl) {
    this.dbUrl = dbUrl;
  }

  @Override
  public DataSource getDataSource() {
    SQLiteDataSource dataSource = new SQLiteDataSource();
    dataSource.setUrl(dbUrl);
    return dataSource;
  }

  @Override
  public String getDialect() {
    return dataProviderType.getDialectString();
  }
}
