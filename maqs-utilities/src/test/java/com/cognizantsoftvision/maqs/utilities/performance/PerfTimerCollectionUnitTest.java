/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.performance;

import com.cognizantsoftvision.maqs.utilities.logging.ConsoleLogger;
import com.cognizantsoftvision.maqs.utilities.logging.MessageType;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

/**
 * The Performance Timer Collection unit test class.
 */
public class PerfTimerCollectionUnitTest {

  /**
   * Tests the get and set log functionality.
   */
  @Test
  public void testGetAndSetLog() {
    ConsoleLogger logger = new ConsoleLogger();
    logger.setLoggingLevel(MessageType.SUCCESS);
    PerfTimerCollection perfTimerCollection = new PerfTimerCollection(logger, "TestCase");
    perfTimerCollection.setLog(logger);
    Assert.assertEquals(perfTimerCollection.getLog().getLoggingLevel(), MessageType.SUCCESS);
  }

  /**
   * tests the get test name functionality.
   */
  @Test
  public void testGetTestName() {
    ConsoleLogger logger = new ConsoleLogger();
    PerfTimerCollection perfTimerCollection = new PerfTimerCollection(logger, "TestCase");
    Assert.assertEquals(perfTimerCollection.getTestName(), "TestCase");
  }

  /**
   * tests setting the test name functionality.
   */
  @Test
  public void testSetTestName() {
    ConsoleLogger logger = new ConsoleLogger();
    PerfTimerCollection perfTimerCollection = new PerfTimerCollection(logger, "TestCase");
    Assert.assertEquals(perfTimerCollection.getTestName(), "TestCase");
    perfTimerCollection.setTestName("NewTestCaseName");
    Assert.assertEquals(perfTimerCollection.getTestName(), "NewTestCaseName");
  }
}
