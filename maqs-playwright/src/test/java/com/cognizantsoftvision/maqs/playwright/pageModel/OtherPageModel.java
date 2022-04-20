/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright.pageModel;

import com.cognizantsoftvision.maqs.playwright.BasePlaywrightPageModel;
import com.cognizantsoftvision.maqs.playwright.IPlaywrightTestObject;
import com.cognizantsoftvision.maqs.playwright.PageDriver;
import com.cognizantsoftvision.maqs.playwright.PlaywrightConfig;
import com.cognizantsoftvision.maqs.playwright.PlaywrightSyncElement;

/**
 * The Other Playwright page model class for testing.
 */
public class OtherPageModel extends BasePlaywrightPageModel {

  static ElementPageModel pageModel;

  /**
   * sets up the page model other class.
   * @param testObject the test object to be used
   */
  protected OtherPageModel(IPlaywrightTestObject testObject) {
    super(testObject);
  }

  /**
   * sets up the page model other class.
   * @param testObject the test object to be used
   * @param otherDriver the page driver to be used
   */
  public OtherPageModel(IPlaywrightTestObject testObject, PageDriver otherDriver) {
    super(testObject, otherDriver);
  }

  /**
   * gets the page url.
    * @return the page url
   */
  public static String getUrl(){
    return PlaywrightConfig.getWebBase() + "async.html";
  }

  /**
   * gets the root body element.
   * @return the root body element
   */
  private PlaywrightSyncElement getBody() {
    return new PlaywrightSyncElement(this.getPageDriver().getAsyncPage(), pageModel.body);
  }

  /**
   * gets the loaded label element.
    * @return the loaded label element
   */
  public PlaywrightSyncElement getLoadedPlaywrightElement() {
    return new PlaywrightSyncElement(getBody(), pageModel.loadedAsyncText);
  }

  /**
   * navigates to the page url.
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
    return this.getLoadedPlaywrightElement().isEventuallyVisible();
  }
}
