/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.cognizantsoftvision.maqs.base.BaseGenericTest;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import com.microsoft.playwright.Page;
import org.testng.Assert;
import org.testng.annotations.Test;

/// <summary>
/// Test driver manager
/// </summary>
public class PlaywrightDriverManagerUnitTest extends BaseGenericTest {

  /// <summary>
  /// Make we can update the store with a new manager using an IPage
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void respectsNewPageViaManager() {
    Page newPage = getNewPage();
    this.getManagerStore().putOrOverride(new PageDriverManager(() -> new PageDriver(newPage) , this.getTestObject()));
    PageDriverManager manager = (PageDriverManager) this.getManagerStore().getManager(PageDriverManager.class.getName());
    Assert.assertEquals(newPage, manager.getPageDriver().getAsyncPage());
  }

  /// <summary>
  /// Make we can update the drive with a IPage object
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void respectsNewPageViaOverride() {
    Page newPage = getNewPage();
    this.getTestObject().overrideDriverManager(
        "playwrightDriver", new PageDriverManager(() -> new PageDriver(newPage) , this.getTestObject()));
    PageDriverManager manager = (PageDriverManager) this.getManagerStore().getManager("playwrightDriver");
    Assert.assertEquals(newPage, manager.getPageDriver().getAsyncPage());
  }

  /// <summary>
  /// Make we can update the drive with a IPage function
  /// </summary>
//  @Test(groups = TestCategories.PLAYWRIGHT)
//  public void respectsNewPageViaOverrideFunc() {
//    Page newPage = getNewPage();
//    this.getTestObject().overrideDriverManager(() -> newPage);
//    Assert.assertEquals(newPage, this.getPageDriver().getAsyncPage());
//  }

  /// <summary>
  /// Get a new IPage
  /// </summary>
  /// <returns>A new IPAge</returns>
  private static Page getNewPage() {
    return PageDriverFactory.getPageDriverForBrowserWithDefaults(PlaywrightBrowser.WEBKIT).getAsyncPage();
  }
}
