/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.helper;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The String Processor unit test class.
 */
public class StringProcessorUnitTest {

  /**
   * tests the safe formatter functionality.
   */
  @Test
  public void testSafeFormatter() {
    final String successful = StringProcessor.safeFormatter("This is a %s message.", "successful");
    Assert.assertEquals(successful, "This is a successful message.");
  }

  /**
   * tests the safe formatter message functionality.
   */
  @Test
  public void testSafeFormatterMessage() {
    final String successful = StringProcessor.safeFormatter("This is a message.");
    Assert.assertEquals(successful, "This is a message.");
  }

  /**
   * tests the safe formatter message functionality when the message is null
   */
  @Test
  public void testSafeFormatterMessageNull() {
    final String s = StringProcessor.safeFormatter(null, "Message", "String", "Null");
    Assert.assertEquals(s, "Message: null Arguments: Message String Null ");
  }

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
