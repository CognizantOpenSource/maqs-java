/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.database.providers;

import javax.sql.DataSource;

public class MySQLProvider implements IDataSourceProvider {
  public MySQLProvider(String connectionString) {

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
