/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.performance;

import com.cognizantsoftvision.maqs.utilities.logging.ConsoleLogger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PerfTimerCollectionUnitTest {

  @Test
  public void testGetLog() {
    ConsoleLogger logger = new ConsoleLogger();
    PerfTimerCollection perfTimerCollection = new PerfTimerCollection(logger, "TestCase");
    Assert.assertNotNull(perfTimerCollection.getLog());
  }

  @Test
  public void testGetTestName() {
    ConsoleLogger logger = new ConsoleLogger();
    PerfTimerCollection perfTimerCollection = new PerfTimerCollection(logger, "TestCase");
    Assert.assertEquals(perfTimerCollection.getTestName(), "TestCase");
  }

  @Test
  public void testSetTestName() {
    ConsoleLogger logger = new ConsoleLogger();
    PerfTimerCollection perfTimerCollection = new PerfTimerCollection(logger, "TestCase");
    Assert.assertEquals(perfTimerCollection.getTestName(), "TestCase");
    perfTimerCollection.setTestName("NewTestCaseName");
    Assert.assertEquals(perfTimerCollection.getTestName(), "NewTestCaseName");

  }
}