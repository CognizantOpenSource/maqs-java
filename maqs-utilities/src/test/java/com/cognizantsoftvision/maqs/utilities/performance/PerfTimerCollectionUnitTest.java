/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.performance;

import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import com.cognizantsoftvision.maqs.utilities.logging.ConsoleLogger;
import com.cognizantsoftvision.maqs.utilities.logging.FileLogger;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Performance Timer Collection unit test class.
 */
public class PerfTimerCollectionUnitTest {

  /**
   * tests the get log functionality.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void testGetLog() {
    ConsoleLogger logger = new ConsoleLogger();
    PerfTimerCollection perfTimerCollection = new PerfTimerCollection(logger, "TestCase");
    Assert.assertNotNull(perfTimerCollection.getLog());
  }

  @Test(groups = TestCategories.UTILITIES)
  public void testSetLog() {
    ConsoleLogger logger = new ConsoleLogger();
    PerfTimerCollection perfTimerCollection = new PerfTimerCollection(logger, "TestCase");
    
    FileLogger fileLogger = new FileLogger();
    perfTimerCollection.setLog(fileLogger);
    Assert.assertTrue(perfTimerCollection.getLog() instanceof FileLogger);
  }

  /**
   * tests the get test name functionality.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void testGetTestName() {
    ConsoleLogger logger = new ConsoleLogger();
    PerfTimerCollection perfTimerCollection = new PerfTimerCollection(logger, "TestCase");
    Assert.assertEquals(perfTimerCollection.getTestName(), "TestCase");
  }

  /**
   * tests setting the test name functionality.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void testSetTestName() {
    ConsoleLogger logger = new ConsoleLogger();
    PerfTimerCollection perfTimerCollection = new PerfTimerCollection(logger, "TestCase");
    Assert.assertEquals(perfTimerCollection.getTestName(), "TestCase");
    perfTimerCollection.setTestName("NewTestCaseName");
    Assert.assertEquals(perfTimerCollection.getTestName(), "NewTestCaseName");

  }
}
