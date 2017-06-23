package com.magenic.jmaqs.appium.pagemodels;

import com.magenic.jmaqs.appium.baseAppiumTest.AppiumTestObject;
import org.openqa.selenium.By;

public class MainPageModel extends BasePageModel {

  private static By loginUsernameLabel = By.id("usernameView");

  private static By loginPasswordLabel = By.id("passView");


  public MainPageModel(AppiumTestObject appiumTestObject) {
    super(appiumTestObject);
  }

  public String getUsernameValueText() {
    return this.appiumTestObject.getAppiumWait().waitForElement(loginUsernameLabel).getText();
  }

  public String getPasswordValueText() {
    return this.appiumTestObject.getAppiumWait().waitForElement(loginPasswordLabel).getText();
  }
}
