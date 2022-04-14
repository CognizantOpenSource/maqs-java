/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.cognizantsoftvision.maqs.base.BaseTestObject;
import com.cognizantsoftvision.maqs.utilities.logging.ILogger;
import java.util.function.Supplier;

/**
 * The Playwright Test Object class.
 */
public class PlaywrightTestObject extends BaseTestObject implements IPlaywrightTestObject {

  /**
   * Initializes a new instance of the PlaywrightTestObject class.
   * @param pageDriver The test's Playwright page
   * @param logger The test's logger
   * @param fullyQualifiedTestName The test's fully qualified test name
   */
  public PlaywrightTestObject(PageDriver pageDriver, ILogger logger, String fullyQualifiedTestName) {
    super(logger, fullyQualifiedTestName);
    this.getManagerStore().put((PageDriverManager.class).getCanonicalName(),
        new PageDriverManager(() -> pageDriver, this));
  }

  /**
   * Initializes a new instance of the PlaywrightTestObject class.
   * @param getDriver Function for getting a Playwright page
   * @param logger The test's logger
   * @param fullyQualifiedTestName The test's fully qualified test name
   */
  public PlaywrightTestObject(Supplier<PageDriver> getDriver, ILogger logger, String fullyQualifiedTestName) {
    super(logger, fullyQualifiedTestName);
    this.getManagerStore().put((PageDriverManager.class).getCanonicalName(),
        new PageDriverManager(getDriver, this));
  }

  /**
   * Gets the page driver manager.
   * @return the page driver manager
   */
  public PageDriverManager getPageManager() {
    return (PageDriverManager) this.getManagerStore().get(PageDriverManager.class.getCanonicalName());
  }

  /**
   * the Playwright page driver.
   */
  protected PageDriver pageDriver;

  /**
   * Gets the Playwright page driver.
   *
   * @return the playwright page driver
   */
  public PageDriver getPageDriver() {
    return this.getPageManager().getPageDriver();
  }

  /**
   * Override the old page driver with a new page.
   *
   * @param pageDriver the new page driver
   */
  @Override
  public void overridePageDriver(PageDriver pageDriver) {
    this.getPageManager().overrideDriver(pageDriver);
  }
}
