/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */
package com.magenic.jmaqs.selenium.unittestpagemodel;

import com.magenic.jmaqs.selenium.SeleniumTestObject;
import com.magenic.jmaqs.selenium.SeleniumWait;
import org.openqa.selenium.By;

public class PageElementsPageModel {
  
  private SeleniumTestObject testObject;
  
  public By showDialog1ButtonLocator = By.id("showDialog1");
  public By closeButtonShowDialogButtonLocator = By.id("CloseButtonShowDialog");
  public By disabledFieldLocator = By.name("disabledfield");
  public By uploadImageButtonLocator = By.id("photo");
  
  public PageElementsPageModel(SeleniumTestObject testObject) {
    this.testObject = testObject;
  }
  
  public void open(String url) {this.testObject.getWebDriver().get(url);}
  
  public SeleniumTestObject getSeleniumTestObject()
  {
    return this.testObject;
  }

  public SeleniumWait getSeleniumWait()
  {
    return this.testObject.getSeleniumWait();
  }
}
