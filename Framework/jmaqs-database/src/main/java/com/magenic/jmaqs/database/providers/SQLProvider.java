/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.database.providers;

import com.magenic.jmaqs.database.constants.DataProviderType;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import javax.sql.DataSource;

public class SQLProvider implements IDataSourceProvider {

  private static final DataProviderType dataProviderType = DataProviderType.SQL;

  public SQLProvider(String connectionString) {

  }

  @Override
  public DataSource getDataSource() {
    SQLServerDataSource dataSource = new SQLServerDataSource();
    //dataSource.
    return null;
  }

  @Override
  public String getDialect() {
    return dataProviderType.getDialectString();
  }
}
