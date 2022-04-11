/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright.pageModel;

import com.cognizantsoftvision.maqs.playwright.BasePlaywrightPageModel;
import com.cognizantsoftvision.maqs.playwright.IPlaywrightTestObject;
import com.cognizantsoftvision.maqs.playwright.PlaywrightConfig;
import com.cognizantsoftvision.maqs.playwright.PlaywrightSyncElement;
import com.microsoft.playwright.FrameLocator;

/**
 * The Playwright IFrame Page Model class for testing.
 */
public class PageModelIFrame extends BasePlaywrightPageModel {

  /**
   * Initializes a new instance of the PageModel IFrame class
   * @param testObject The base Playwright test object
   */
  public PageModelIFrame(IPlaywrightTestObject testObject) {
    super(testObject);
  }

  /**
   * holds the selector string values.
   */
  ElementPageModel elementPageModel = new ElementPageModel();

  /**
   * gets the page url.
   * @return the page url
   */
  public static String getUrl() {
    return PlaywrightConfig.getWebBase() + "iFrame.html";
  }

  /**
   * gets the test frame locator.
   * @return the test frame locator
   */
  private FrameLocator getFrame() {
    return this.getPageDriver().getAsyncPage().frameLocator(elementPageModel.frame);
  }

  /**
   * gets the loaded label.
   * @return the loaded label
   */
  public PlaywrightSyncElement getShowDialog() {
    return new PlaywrightSyncElement(getFrame(), elementPageModel.showDialog1);
  }

  /**
   * gets the close dialog element.
   * @return the close dialog element
   */
  public PlaywrightSyncElement getCloseDialog() {
    return new PlaywrightSyncElement(getFrame(), elementPageModel.closeButtonShowDialog);
  }

  /**
   * navigates to the page.
   */
  public void openPage() {
    this.getPageDriver().navigateTo(getUrl());
  }

  /**
   * check if the page has been loaded.
   * @return true if the page was loaded
   */
  @Override
  public boolean isPageLoaded() {
    return getShowDialog().isEventuallyVisible();
  }
}
