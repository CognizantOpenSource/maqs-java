/*
 * Copyright 2020 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium.unittestpagemodel;

import com.magenic.jmaqs.selenium.BaseSeleniumPageModel;
import com.magenic.jmaqs.selenium.SeleniumTestObject;
import org.openqa.selenium.By;

/**
 * The type Page elements page model.
 */
public class PageElementsPageModel extends BaseSeleniumPageModel {

  /**
   * The Show dialog 1 button locator.
   */
  public By showDialog1ButtonLocator = By.id("showDialog1");
  /**
   * The Close button show dialog button locator.
   */
  public By closeButtonShowDialogButtonLocator = By.id("CloseButtonShowDialog");
  /**
   * The Disabled field locator.
   */
  public By disabledFieldLocator = By.name("disabledfield");
  /**
   * The Upload image button locator.
   */
  public By uploadImageButtonLocator = By.id("photo");

  /**
   * Instantiates a new Page elements page model.
   *
   * @param testObject the test object
   */
  public PageElementsPageModel(SeleniumTestObject testObject) {
    super(testObject);
  }

  @Override
  public boolean isPageLoaded() {
    return false;
  }

  /**
   * Open.
   *
   * @param url the url
   */
  public void open(String url) {
    this.getWebDriver().get(url);
  }

}
