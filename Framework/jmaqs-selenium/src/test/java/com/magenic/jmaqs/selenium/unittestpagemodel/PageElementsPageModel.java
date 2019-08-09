package com.magenic.jmaqs.selenium.unitTests.unittestpagemodel;

import com.magenic.jmaqs.selenium.baseSeleniumTest.SeleniumTestObject;
import com.magenic.jmaqs.selenium.baseSeleniumTest.SeleniumWait;
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
