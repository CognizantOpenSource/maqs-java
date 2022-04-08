/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

import com.cognizantsoftvision.maqs.base.BaseTestObject;
import com.cognizantsoftvision.maqs.utilities.logging.Logger;
import com.microsoft.playwright.Page;
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
  public PlaywrightTestObject(PageDriver pageDriver, Logger logger, String fullyQualifiedTestName) {
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
  public PlaywrightTestObject(Supplier<PageDriver> getDriver, Logger logger, String fullyQualifiedTestName) {
    super(logger, fullyQualifiedTestName);
    this.getManagerStore().put((PageDriverManager.class).getCanonicalName(),
        new PageDriverManager(getDriver, this));
  }

  /**
   * the page driver manager.
   */
  private PageDriverManager pageManager;

  public PageDriverManager getPageManager() {
//    return this.ManagerStore.GetManager<PlaywrightDriverManager>(typeof(PlaywrightDriverManager).FullName);
    return (PageDriverManager) this.getManagerStore().get(PageDriverManager.class.getCanonicalName());
  }

  /// <summary>
  /// Gets the Playwright page
  /// </summary>
  private PageDriver pageDriver;

  @Override
  public PageDriver getPageDriver() {
    return this.pageDriver;
  }

  /// <summary>
  /// Override the the old page with a new page
  /// </summary>
  /// <param name="page">The new page</param>
  @Override
  public void overridePageDriver(Page page) {
    this.getPageManager().overrideDriver(page);
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
