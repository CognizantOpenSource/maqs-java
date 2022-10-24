/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.database;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.ClassTransformer;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;

public class DatabasePersistenceUnitInfo implements PersistenceUnitInfo {
  private final String persistenceUnitName;
  private static final PersistenceUnitTransactionType transactionType = PersistenceUnitTransactionType.RESOURCE_LOCAL;
  private final List<String> managedClassNames;
  private final List<String> mappingFileNames = new ArrayList<>();
  private final Properties properties;
  private DataSource jtaDataSource;
  private DataSource nonJtaDataSource;
  private final List<ClassTransformer> transformers = new ArrayList<>();

  public DatabasePersistenceUnitInfo(String persistenceUnitName, List<String> managedClassNames,
      Properties properties) {
    this.persistenceUnitName = persistenceUnitName;
    this.managedClassNames = managedClassNames;
    this.properties = properties;
  }

  @Override
  public String getPersistenceUnitName() {
    return persistenceUnitName;
  }

  @Override
  public String getPersistenceProviderClassName() {
    return "org.hibernate.jpa.HibernatePersistenceProvider";
  }

  @Override
  public PersistenceUnitTransactionType getTransactionType() {
    return transactionType;
  }

  @Override
  public DataSource getJtaDataSource() {
    return jtaDataSource;
  }

  @Override
  public DataSource getNonJtaDataSource() {
    return nonJtaDataSource;
  }

  @Override
  public List<String> getMappingFileNames() {
    return mappingFileNames;
  }

  @Override
  public List<URL> getJarFileUrls() {
    return new ArrayList<>();
  }

  @Override
  public URL getPersistenceUnitRootUrl() {
    return null;
  }

  @Override
  public List<String> getManagedClassNames() {
    return managedClassNames;
  }

  @Override
  public boolean excludeUnlistedClasses() {
    return false;
  }

  @Override
  public SharedCacheMode getSharedCacheMode() {
    return null;
  }

  @Override
  public ValidationMode getValidationMode() {
    return null;
  }

  @Override
  public Properties getProperties() {
    return properties;
  }

  @Override
  public String getPersistenceXMLSchemaVersion() {
    return null;
  }

  @Override
  public ClassLoader getClassLoader() {
    return null;
  }

  @Override
  public void addTransformer(ClassTransformer transformer) {
    transformers.add(transformer);
  }

  @Override
  public ClassLoader getNewTempClassLoader() {
    return null;
  }
}
