/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.base;

import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BaseGenericTestNGUnitTest extends BaseGenericTest {

  @Test(groups = TestCategories.FRAMEWORK)
  public void testCreateTestObject() {
    Assert.assertNotNull(this.getTestObject());
  }
}