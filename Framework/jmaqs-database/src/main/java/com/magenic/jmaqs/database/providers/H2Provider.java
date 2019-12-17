package com.magenic.jmaqs.database.providers;

import javax.sql.DataSource;

public class H2Provider implements IDataSourceProvider {
  public H2Provider(String connectionString) {

  }

  @Override
  public DataSource getDataSource() {
    return null;
  }

  @Override
  public String getDialect() {
    return null;
  }
}
