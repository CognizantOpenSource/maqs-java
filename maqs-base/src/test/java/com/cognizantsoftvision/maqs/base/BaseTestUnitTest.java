/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.base;

import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import com.cognizantsoftvision.maqs.utilities.logging.*;
import com.cognizantsoftvision.maqs.utilities.performance.PerfTimerCollection;
import java.util.ArrayList;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;

/**
 * The Base Test unit test class.
 */
@Test(groups = TestCategories.FRAMEWORK)
public class BaseTestUnitTest extends BaseTest {

  /**
   * Verify fully qualified test name.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void fullyQualifiedTestNameTest() {
    String testName = this.getFullyQualifiedTestClassName();

    Assert.assertEquals(testName,
        "com.cognizantsoftvision.maqs.base.BaseTestUnitTest.fullyQualifiedTestNameTest");
  }

  /**
   * Validate setting a new logger.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void fileLoggerTest() {
    this.setLogger(new FileLogger());

    if (!(this.getLogger() instanceof FileLogger)) {
      Assert.fail("FileLogger was not set.");
    }
  }

  /**
   * Validate Logging Verbose works.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void logVerboseTest() {
    this.logVerbose("This is a test to log verbose.");
    this.logVerbose("This is another test to log verbose");
  }

  /**
   * Validate that Try To Log is working.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void tryToLogTest() {
    this.tryToLog(MessageType.INFORMATION, "Try to log message.");
  }

  /**
   * Validate adding exceptions to the Logged Exception list adds the exceptions correctly.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void addLoggedExceptionsTest() {
    ArrayList<String> exceptions = new ArrayList<>();
    exceptions.add("First Exception.");
    exceptions.add("Second Exception.");
    exceptions.add("Third Exception.");
    this.setLoggedExceptions(exceptions);

    Assert.assertEquals(this.getLoggedExceptions().size(), 3,
        "Expect that 3 Logged exceptions are in this exception list.");
  }

  /**
   * Validate the Logging Enabled Setting is YES (set in Config).
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void loggingEnabledSettingTest() {
    Assert.assertEquals(this.getLoggingEnabled(), LoggingConfig.getLoggingEnabledSetting());
  }

  /**
   * Validate the Logging Enabled Settings.
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void setLoggingEnabledSettingTest() {
    this.setLoggingEnabled(LoggingEnabled.ONFAIL);
    Assert.assertEquals(this.getLoggingEnabled(), LoggingEnabled.ONFAIL);
    this.setLoggingEnabled(LoggingEnabled.YES);
    Assert.assertEquals(this.getLoggingEnabled(), LoggingEnabled.YES);
  }

  /**
   * Validate Setting the Test Object to a new Test Object (Console Logger instead of File Logger).
   */
  @Test(groups = TestCategories.FRAMEWORK)
  public void setTestObjectTest() {
    Logger logger = new ConsoleLogger();
    BaseTestObject baseTestObject = new BaseTestObject(logger,
        this.getFullyQualifiedTestClassName());
    this.setTestObject(baseTestObject);

    Assert.assertTrue(this.getTestObject().getLogger() instanceof ConsoleLogger,
        "Expected Test Object to be set to have a Console Logger.");
  }

  @Test(groups = TestCategories.FRAMEWORK)
  public void testSetPerformanceCollection() {
    PerfTimerCollection perfTimerCollection = new PerfTimerCollection(this.getLogger(),
        this.getFullyQualifiedTestClassName());
    this.setPerfTimerCollection(perfTimerCollection);
  }

  @Test(groups = TestCategories.FRAMEWORK)
  public void testGetPerformanceCollection() {
    PerfTimerCollection perfTimerCollection = new PerfTimerCollection(this.getLogger(),
        this.getFullyQualifiedTestClassName());
    this.setPerfTimerCollection(perfTimerCollection);
    Assert.assertNotNull(this.getPerfTimerCollection());
  }

  @Test(groups = TestCategories.FRAMEWORK)
  public void testGetTestContext() {
    Assert.assertNotNull(this.getTestContext());
  }

  @Test(groups = TestCategories.FRAMEWORK)
  public void testSetTestContext() {
    final ITestContext testContext = this.getTestContext();
    testContext.setAttribute("testName", "SetTestContext");
    this.setTestContext(testContext);
    Assert.assertNotNull(this.getTestContext());
    Assert.assertEquals(this.getTestContext().getAttribute("testName"), "SetTestContext");
  }

  @Test(groups = TestCategories.FRAMEWORK)
  public void testGetManagerStore() {
    Assert.assertNotNull(this.getManagerStore());
  }

  @Test(groups = TestCategories.FRAMEWORK)
  public void testCleanUpLogs() {
    this.setLogger(new FileLogger());
    this.setLoggingEnabled(LoggingEnabled.ONFAIL);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.cognizantsoftvision.maqs.utilities.BaseTest.BaseTest#beforeLoggingTeardown(org.testng.
   * ITestResult)
   */
  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {

  }

}
