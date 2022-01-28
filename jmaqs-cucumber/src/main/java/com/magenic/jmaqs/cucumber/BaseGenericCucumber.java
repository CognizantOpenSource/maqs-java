/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.cucumber;

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



