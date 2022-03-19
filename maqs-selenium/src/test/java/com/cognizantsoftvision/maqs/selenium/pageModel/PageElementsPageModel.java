/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium.pageModel;

import com.cognizantsoftvision.maqs.selenium.BaseSeleniumPageModel;
import com.cognizantsoftvision.maqs.selenium.LazyWebElement;
import com.cognizantsoftvision.maqs.selenium.SeleniumConfig;
import com.cognizantsoftvision.maqs.selenium.SeleniumTestObject;
import com.cognizantsoftvision.maqs.utilities.helper.exceptions.TimeoutException;
import org.openqa.selenium.By;

/**
 * The type Page elements page model.
 */
public class PageElementsPageModel extends BaseSeleniumPageModel {

  private final String pageAddress = SeleniumConfig.getWebSiteBase() + "/Automation";

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

  public By pageTitleLocator = By.cssSelector("body > div.container.body-content > h2");

  public By bodyLocator = By.cssSelector("body");

  public LazyWebElement body = new LazyWebElement(getTestObject(), bodyLocator, "Body");

  public  LazyWebElement pageTitle = new LazyWebElement(getTestObject(), pageTitleLocator , "Page Title");

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
    try {
      return pageTitle.doesExist();
    } catch (TimeoutException | InterruptedException e) {
      e.printStackTrace();
    }
    return false;
  }

  /**
   * Open.
   *
   */
  public void open() {
    open(pageAddress);
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
