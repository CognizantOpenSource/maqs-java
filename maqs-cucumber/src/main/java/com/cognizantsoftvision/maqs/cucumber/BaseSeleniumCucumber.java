/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.cucumber;

import com.cognizantsoftvision.maqs.base.BaseTest;
import com.cognizantsoftvision.maqs.selenium.BaseSeleniumTest;

/**
 * The base Selenium cucumber object.
 */
public class BaseSeleniumCucumber extends BaseCucumberTestNG {

  /**
   * Create a test object.
   *
   * @return A Selenium base test object
   */
  @Override
  public BaseTest createSpecificBaseTest() {
    return new BaseSeleniumTest();
  }
}



