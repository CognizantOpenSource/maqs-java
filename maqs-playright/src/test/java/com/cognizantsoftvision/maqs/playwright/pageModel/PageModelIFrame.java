/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright.pageModel;

import com.cognizantsoftvision.maqs.playwright.BasePlaywrightPageModel;
import com.cognizantsoftvision.maqs.playwright.IPlaywrightTestObject;
import com.cognizantsoftvision.maqs.playwright.PlaywrightConfig;
import com.cognizantsoftvision.maqs.playwright.PlaywrightSyncElement;
import com.microsoft.playwright.FrameLocator;

public class PageModelIFrame extends BasePlaywrightPageModel {

  /// <summary>
  /// Initializes a new instance of the <see cref="PageModel"/> class
  /// </summary>
  /// <param name="testObject">The base Playwright test object</param>
  /// <param name="otherDriver">Page driver to use instead of the default</param>
  public PageModelIFrame(IPlaywrightTestObject testObject) {
    super(testObject);
  }

  /// <summary>
  /// Get page url
  /// </summary>
  public static String getUrl() {
    return PlaywrightConfig.getWebBase() + "iFrame.html";
  }


  /// <summary>
  /// Test frame
  /// </summary>
  private FrameLocator getFrame() {
    return this.getPageDriver().getAsyncPage().frameLocator("#frame");
  }

  /// <summary>
  /// Get loaded label
  /// </summary>
  public PlaywrightSyncElement getShowDialog() {
    return new PlaywrightSyncElement(Frame, "#showDialog1");
  }

  /// <summary>
  /// Get loaded label
  /// </summary>
  public PlaywrightSyncElement getCloseDialog() {
    return new PlaywrightSyncElement(Frame, "#CloseButtonShowDialog");
  }


  /// <summary>
  /// Open the page
  /// </summary>
  public void openPage() {
    this.getPageDriver().navigateTo(getUrl());
  }

  @Override
  public boolean isPageLoaded() {
    return getShowDialog().isEventuallyVisible();
  }
}
