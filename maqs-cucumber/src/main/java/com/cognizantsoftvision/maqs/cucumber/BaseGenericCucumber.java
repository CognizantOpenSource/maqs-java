/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.cucumber;

import com.cognizantsoftvision.maqs.base.BaseGenericTest;
import com.cognizantsoftvision.maqs.base.BaseTest;

/**
 * The base generic cucumber object.
 */
public class BaseGenericCucumber extends BaseCucumberTestNG {

  /**
   * Create a test object.
   *
   * @return A generic base test object
   */
  @Override
  public BaseTest createSpecificBaseTest() {
    return new BaseGenericTest();
  }
}



