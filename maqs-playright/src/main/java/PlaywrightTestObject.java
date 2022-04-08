/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

import com.cognizantsoftvision.maqs.base.BaseTestObject;
import com.cognizantsoftvision.maqs.utilities.logging.Logger;
import com.microsoft.playwright.Page;
import java.util.function.Supplier;

/// <summary>
/// Playwright test context data
/// </summary>
public class PlaywrightTestObject extends BaseTestObject implements IPlaywrightTestObject {

  /// <summary>
  /// Initializes a new instance of the <see cref="PlaywrightTestObject" /> class
  /// </summary>
  /// <param name="PageDriver">The test's Playwright page</param>
  /// <param name="logger">The test's logger</param>
  /// <param name="fullyQualifiedTestName">The test's fully qualified test name</param>
  public PlaywrightTestObject(PageDriver PageDriver, Logger logger, String fullyQualifiedTestName) {
    super(logger, fullyQualifiedTestName);
    this.getManagerStore().put(instanceof(PlaywrightDriverManager).FullName, new PlaywrightDriverManager(() -> PageDriver, this));
//    this.SoftAssert = new PlaywrightSoftAssert(this);
  }

  /// <summary>
  /// Initializes a new instance of the <see cref="PlaywrightTestObject" /> class
  /// </summary>
  /// <param name="getDriver">Function for getting a Playwright page</param>
  /// <param name="logger">The test's logger</param>
  /// <param name="fullyQualifiedTestName">The test's fully qualified test name</param>
  public PlaywrightTestObject(Supplier<PageDriver> getDriver, Logger logger, String fullyQualifiedTestName) {
    super(logger, fullyQualifiedTestName);
    this.ManagerStore.Add(typeof(PlaywrightDriverManager).FullName, new PlaywrightDriverManager(getDriver, this));
//    this.getsSoftAssert = new PlaywrightSoftAssert(this);
  }

  /// <summary>
  /// Gets the Playwright driver manager
  /// </summary>
  public PageDriverManager pageManager;

  public PageDriverManager getPageManager() {
//    return this.ManagerStore.GetManager<PlaywrightDriverManager>(typeof(PlaywrightDriverManager).FullName);
    return this.getManagerStore().getOrDefault().getBaseDriver();
  }

  /// <summary>
  /// Gets the Playwright page
  /// </summary>
  private PageDriver PageDriver;

  @Override public PageDriver pageDriver() {
    return null;
  }

  @Override
  public PageDriver getPageDriver() {
    return this.getPageManager().getPageDriver();
  }

  @Override public PageDriverManager pageManager() {
    return null;
  }

  @Override
  public PageDriverManager getPageManager() {
    return null;
  }

  /// <summary>
  /// Override the the function for creating a new page
  /// </summary>
  /// <param name="getPage">New function for creating a page</param>
  @Override
  public void overridePageDriver(Supplier getPage) {
    this.getPageManager().overrideDriver(getPage.get());
  }

  /// <summary>
  /// Override the the old page with a new page
  /// </summary>
  /// <param name="page">The new page</param>
  @Override
  public void overridePageDriver(Page page) {
    this.getPageManager().overrideDriver(() -> page);
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
