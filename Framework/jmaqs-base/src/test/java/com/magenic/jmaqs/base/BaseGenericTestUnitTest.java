/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.base;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BaseGenericTestUnitTest extends BaseGenericTest {

  @Test
  public void testCreateTestObject() {
    Assert.assertNotNull(this.getTestObject());
  }
}