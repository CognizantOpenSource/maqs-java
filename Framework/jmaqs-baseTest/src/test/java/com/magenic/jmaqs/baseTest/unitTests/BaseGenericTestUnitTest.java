/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.baseTest.unitTests;

import com.magenic.jmaqs.baseTest.BaseTest;
import com.magenic.jmaqs.utilities.logging.FileLogger;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

/**
 * Unit test class for BaseTest class.
 */
@Test
public class BaseGenericTestUnitTest extends BaseTest {
  /**
   * Verify fully qualified test name.
   */
  @Test
  public void fullyQualifiedTestNameTest() {
    String testName = this.getFullyQualifiedTestClassName();

    Assert.assertEquals(testName,
        "com.magenic.jmaqs.baseTest.unitTests.BaseGenericTestUnitTest.fullyQualifiedTestNameTest");
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
   * @see com.magenic.jmaqs.utilities.BaseTest.BaseTest#postSetupLogging()
   */
  @Override
  protected void postSetupLogging() {

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.magenic.jmaqs.utilities.BaseTest.BaseTest#beforeLoggingTeardown(org.testng.
   * ITestResult)
   */
  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {

  }

}
