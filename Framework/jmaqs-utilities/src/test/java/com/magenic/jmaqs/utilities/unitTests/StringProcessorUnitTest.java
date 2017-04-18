/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.utilities.unitTests;

import com.magenic.jmaqs.utilities.helper.StringProcessor;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit tests for the StringProcessor class.
 */
public class StringProcessorUnitTest {
  /**
   * Test method for checking string format.
   */
  @Test
  public void stringFormatterCheckForStringFormat() {
    String message = StringProcessor.safeFormatter("This %s should return %s", "Test", "Test");
    Assert.assertEquals("This Test should return Test", message);
  }
}
