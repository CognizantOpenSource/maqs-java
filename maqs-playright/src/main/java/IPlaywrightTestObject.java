/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

import com.cognizantsoftvision.maqs.base.ITestObject;
import com.microsoft.playwright.Page;

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
   * Override the Playwright page.
   * @param page the new page
   */
  void overridePageDriver(Page page);

  /**
   * Override the Playwright page driver.
   * @param pageDriver the new page driver
   */
  void overridePageDriver(PageDriver pageDriver);
}
