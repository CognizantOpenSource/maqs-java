/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright.pageModel;

import com.cognizantsoftvision.maqs.playwright.BasePlaywrightPageModel;
import com.cognizantsoftvision.maqs.playwright.IPlaywrightTestObject;
import com.cognizantsoftvision.maqs.playwright.PageDriver;
import com.cognizantsoftvision.maqs.playwright.PlaywrightConfig;
import com.cognizantsoftvision.maqs.playwright.PlaywrightSyncElement;

/// <summary>
/// Playwright page model class for testing
/// </summary>
public class PageModelOther extends BasePlaywrightPageModel {

  protected PageModelOther(IPlaywrightTestObject testObject) {
    super(testObject);
  }

  public PageModelOther(IPlaywrightTestObject testObject, PageDriver otherDriver) {
    super(testObject, otherDriver);
  }

  /// <summary>
  /// Get page url
  /// </summary>
  public static String getUrl(){
    return PlaywrightConfig.getWebBase() + "async.html";
  }

  /// <summary>
  /// Root body
  /// </summary>
  private PlaywrightSyncElement getBody() {
    return new PlaywrightSyncElement(this.getPageDriver().getAsyncPage(), "BODY");
  }

  /// <summary>
  /// Get loaded label
  /// </summary>
  public PlaywrightSyncElement getLoadedPlaywrightElement() {
    return new PlaywrightSyncElement(getBody(), "#loading-div-text[style='']");
  }

  /// <summary>
  /// Open the page
  /// </summary>
  public void openPage() {
    this.getPageDriver().navigateTo(getUrl());
  }

  /// <summary>
  /// Check if the page has been loaded
  /// </summary>
  /// <returns>True if the page was loaded</returns>
  @Override
  public boolean isPageLoaded() {
    return this.getLoadedPlaywrightElement().isEventuallyVisible();
  }
}
