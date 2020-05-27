/*
 * Copyright 2020 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.base;

import com.magenic.jmaqs.base.watcher.JunitTestWatcher;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.testng.ITestResult;

/**
 * Base Generic Test class for Unit Tests and Other testing scenarios.
 */
public class BaseGenericTest extends BaseExtendableTest<BaseTestObject> {

  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {
    //No before logging steps needed in this scenario
  }

  @Override
  protected void createNewTestObject() {
    this.setTestObject(
            new BaseTestObject(this.createLogger(), this.getFullyQualifiedTestClassName()));
  }
}