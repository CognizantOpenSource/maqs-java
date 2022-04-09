/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.cognizantsoftvision.maqs.base.BaseTestObject;
import com.cognizantsoftvision.maqs.utilities.logging.ILogger;
import com.cognizantsoftvision.maqs.utilities.logging.Logger;
import com.microsoft.playwright.Page;
import java.util.function.Supplier;

/**
 * The Playwright Test Object class.
 */
public class PlaywrightTestObject extends BaseTestObject implements IPlaywrightTestObject {

  /**
   * Initializes a new instance of the com.cognizantsoftvision.maqs.playwright.PlaywrightTestObject class.
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
   * Initializes a new instance of the com.cognizantsoftvision.maqs.playwright.PlaywrightTestObject class.
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
   * the page driver manager.
   */
  protected PageDriverManager pageManager;

  public PageDriverManager getPageManager() {
//    return this.ManagerStore.GetManager<PlaywrightDriverManager>(typeof(PlaywrightDriverManager).FullName);
    return (PageDriverManager) this.getManagerStore().get(PageDriverManager.class.getCanonicalName());
  }

  /// <summary>
  /// Gets the Playwright page
  /// </summary>
  protected PageDriver pageDriver;

  public PageDriver getPageDriver() {
    return this.getPageManager().getPageDriver();
  }

  /// <summary>
  /// Override the the old page driver with a new page
  /// </summary>
  /// <param name="pageDriver">The new page drive</param>
  @Override
  public void overridePageDriver(PageDriver pageDriver) {
    this.getPageManager().overrideDriver(pageDriver);
  }
}
