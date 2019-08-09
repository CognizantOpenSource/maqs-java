/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.base;

import com.magenic.jmaqs.base.BaseGenericTest;
import com.magenic.jmaqs.utilities.logging.FileLogger;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

/**
 * Unit test class for BaseGenericTest class.
 */
@Test
public class BaseGenericTestUnitTest extends BaseGenericTest {
  /**
   * Verify fully qualified test name.
   */
  @Test
  public void fullyQualifiedTestNameTest() {
    String testName = this.getFullyQualifiedTestClassName();

    Assert.assertEquals(testName,
        "BaseGenericTestUnitTest.fullyQualifiedTestNameTest");
  }

  /**
   * Validate setting a new logger.
   */
  @Test
  public void fileLoggerTest() {
    this.setLogger(new FileLogger());

    if (!(this.getLogger() instanceof FileLogger)) {
      Assert.fail("FileLogger was not set.");
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.magenic.jmaqs.utilities.BaseTest.BaseGenericTest#postSetupLogging()
   */
  @Override
  protected void postSetupLogging() {

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.magenic.jmaqs.utilities.BaseTest.BaseGenericTest#beforeLoggingTeardown(org.testng.
   * ITestResult)
   */
  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {

  }

}
