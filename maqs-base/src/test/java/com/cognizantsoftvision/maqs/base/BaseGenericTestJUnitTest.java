/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BaseGenericTestJUnitTest extends BaseGenericTest {

  @Test
  public void testCreateTestObject() {
    Assertions.assertNotNull(this.getTestObject());
  }
}