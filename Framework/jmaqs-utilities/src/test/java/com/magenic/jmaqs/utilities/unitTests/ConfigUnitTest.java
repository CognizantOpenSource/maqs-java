/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.utilities.unitTests;

import com.magenic.jmaqs.utilities.helper.Config;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Configuration unit test class.
 */
public class ConfigUnitTest {
  /**
   * Gets a value from a string.
   */
  @Test
  public void getValueWithString() {
    String value = Config.getValue("WaitTime");
    Assert.assertEquals(value, "100");
  }

  /**
   * Gets a value with a string or default.
   */
  @Test
  public void getValueWithStringAndDefault() {

    String value = Config.getValue("DoesNotExist", "Default");

    Assert.assertEquals(value, "Default");
  }
}
