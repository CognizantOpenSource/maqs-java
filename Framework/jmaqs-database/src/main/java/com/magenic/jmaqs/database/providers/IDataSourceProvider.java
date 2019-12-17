/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.database.providers;

import javax.sql.DataSource;

public interface IDataSourceProvider {

  DataSource getDataSource();

  String getDialect();
}
