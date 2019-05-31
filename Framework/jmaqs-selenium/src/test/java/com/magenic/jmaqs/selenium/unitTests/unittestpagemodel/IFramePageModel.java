package com.magenic.jmaqs.selenium.unitTests.unittestpagemodel;

import com.magenic.jmaqs.selenium.baseSeleniumTest.SeleniumTestObject;
import com.magenic.jmaqs.selenium.baseSeleniumTest.SeleniumWait;
import org.openqa.selenium.By;

public class IFramePageModel {
  
  private SeleniumTestObject testObject;
  
  public By iframeLocator = By.id("mageniciFrame");
  
  public IFramePageModel(SeleniumTestObject testObject) {
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
