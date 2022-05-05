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
}
