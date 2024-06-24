/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.base.junit;

import com.cognizantsoftvision.maqs.base.BaseGenericTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;

class JUnitBaseGenericTestUnitTest extends BaseGenericTest {

  @Test
  void testCreateTestObject() {
    Assertions.assertNotNull(this.getTestObject());
  }
}