/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.cognizantsoftvision.maqs.base.BaseGenericTest;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import com.microsoft.playwright.Page;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

/**
 * Test driver manager.
 */
public class PlaywrightDriverManagerUnitTest extends BaseGenericTest {

  /**
   * Make we can update the store with a new manager using a Page.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void respectsNewPageViaManager() {
    Page newPage = getNewPage();
    this.getManagerStore().putOrOverride(new PlaywrightDriverManager(() -> new PageDriver(newPage) , this.getTestObject()));
    PlaywrightDriverManager manager = (PlaywrightDriverManager) this.getManagerStore().getManager(
        PlaywrightDriverManager.class.getName());
    Assert.assertEquals(newPage, manager.getPageDriver().getAsyncPage());
  }

  /**
   * Make we can update the drive with a Page object.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void respectsNewPageViaOverride() {
    Page newPage = getNewPage();
    this.getTestObject().overrideDriverManager("playwrightDriver",
        new PlaywrightDriverManager(() -> new PageDriver(newPage) , this.getTestObject()));
    PlaywrightDriverManager manager = (PlaywrightDriverManager) this.getManagerStore().getManager("playwrightDriver");
    Assert.assertEquals(newPage, manager.getPageDriver().getAsyncPage());
  }

  /**
   * Make we can update the drive with a Page function.
   */
  // TODO: Figure out this test
  @Ignore
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void respectsNewPageViaOverrideFunc() {
//    Page newPage = getNewPage();
//    this.getTestObject().addDriverManager("Playwright driver", () -> new PageDriver(newPage));
//    Assert.assertEquals(newPage, this.getTestObject().getPageDriver().getAsyncPage());
  }

  /**
   * Get a new Page.
   * @return A new Page
   */
  private static Page getNewPage() {
    return PageDriverFactory.getPageDriverForBrowserWithDefaults(PlaywrightBrowser.WEBKIT).getAsyncPage();
  }
}
