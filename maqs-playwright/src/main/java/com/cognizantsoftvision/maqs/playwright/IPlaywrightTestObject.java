/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.cognizantsoftvision.maqs.base.ITestObject;

/**
 * The Playwright Test Object interface.
 */
public interface IPlaywrightTestObject extends ITestObject {

  /**
   * Gets the page driver.
   * @return the page driver
   */
  PageDriver getPageDriver();

  /**
   * Gets the Playwright page manager.
   * @return the playwright driver manager
   */
  PageDriverManager getPageManager();

  /**
   * Override the Playwright page driver.
   * @param pageDriver the new page driver
   */
  void overridePageDriver(PageDriver pageDriver);
}
