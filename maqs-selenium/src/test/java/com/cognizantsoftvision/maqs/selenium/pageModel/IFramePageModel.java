/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium.pageModel;

import com.cognizantsoftvision.maqs.selenium.ISeleniumTestObject;
import com.cognizantsoftvision.maqs.selenium.SeleniumConfig;
import org.openqa.selenium.By;

/**
 * The IFrame page model.
 */
public class IFramePageModel extends HeaderPageModel {

  /**
   * Unit testing site URL - IFrame page.
   */
  public final String testSiteIFrameUrl = SeleniumConfig.getWebSiteBase() + "iFrame.html";

  /**
   * The Iframe locator.
   */
  public By iframeLocator = By.cssSelector("#frame");

  /**
   * Instantiates a new IFrame page model.
   *
   * @param testObject the test object
   */
  public IFramePageModel(ISeleniumTestObject testObject) {
    super(testObject);
  }

  /**
   * Opens the page to the specified url.
   */
  public void open() {
    open(testSiteIFrameUrl);
  }
}