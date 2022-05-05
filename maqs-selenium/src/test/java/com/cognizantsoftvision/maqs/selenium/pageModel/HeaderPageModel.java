/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium.pageModel;

import com.cognizantsoftvision.maqs.selenium.BaseSeleniumPageModel;
import com.cognizantsoftvision.maqs.selenium.ISeleniumTestObject;
import com.cognizantsoftvision.maqs.selenium.SeleniumConfig;
import com.cognizantsoftvision.maqs.selenium.UIWait;
import org.openqa.selenium.By;

/**
 * The Header page model.
 */
public class HeaderPageModel extends BaseSeleniumPageModel {

  /**
   * Unit testing site URL - Login page.
   */
  public final String testSiteUrl = SeleniumConfig.getWebSiteBase();

  /**
   * Home button css selector.
   */
  public final By homeButton = By.cssSelector("#homeButton > a");

  /**
   * Manage dropdown selector.
   */
  public final By automationDropDown = By
      .cssSelector("body > div.navbar > div > div > ul > li:nth-child(2) > a");

  /**
   * IFrame dropdown link button.
   */
  public final By iFrameDropDownButton = By.cssSelector("#iFrameButton");

  /**
   * Employee page title.
   */
  public final By iFramePageTitle = By.cssSelector("#iFrameButton > a");

  /**
   * Home button css selector.
   */
  public final By dropdownToggleClassSelector = By.className("dropdown-toggle");

  /**
   * Selector that is not in page.
   */
  public final By notInPage = By.cssSelector("NotInPage");

  /**
   * Instantiates a new Main page model.
   *
   * @param testObject the test object
   */
  public HeaderPageModel(ISeleniumTestObject testObject) {
    super(testObject);
  }

  /**
   * Opens the page to the specified url.
   *
   */
  public void open() {
    open(testSiteUrl);
  }

  /**
   * Open.
   *
   * @param url the url
   */
  public void open(String url) {
    this.getTestObject().getWebDriver().get(url);
  }

  /**
   * Checks if the page is loaded.
   * @return A boolean if the page has loaded
   */
  @Override
  public boolean isPageLoaded() {
    return new UIWait(this.getWebDriver()).waitUntilVisibleElement(homeButton);
  }
}