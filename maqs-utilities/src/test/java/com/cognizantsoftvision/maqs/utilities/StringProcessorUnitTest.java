/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities;

import com.cognizantsoftvision.maqs.utilities.helper.StringProcessor;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The String Processor unit test class.
 */
public class StringProcessorUnitTest {

  /**
   * Test method for checking strings that contain brackets.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void stringFormatterCheckForBrackets() {
    String message = StringProcessor.safeFormatter("{This is a test for JSON}");
    Assert.assertEquals(message, "{This is a test for JSON}");
  }

  /**
   * Test method for checking string format.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void stringFormatterCheckForStringFormat() {
    String message = StringProcessor.safeFormatter("This %s should return %s", "Test", "Test");
    Assert.assertEquals( message, "This Test should return Test");
  }

  /**
   * Verify that StringProcessor.safeFormatter handles errors in the message as expected.
   */
  @Test(groups = TestCategories.UTILITIES)
  public void stringFormatterThrowException() {
    String message = StringProcessor
        .safeFormatter("This {0} should return {5}", "Test", "Test", "Test");
    Assert.assertTrue(message.contains("This {0} should return {5}"));
  }
}
