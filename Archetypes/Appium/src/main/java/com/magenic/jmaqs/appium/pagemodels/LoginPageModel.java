package com.magenic.jmaqs.appium.pagemodels;

import com.magenic.jmaqs.appium.baseAppiumTest.AppiumTestObject;
import org.openqa.selenium.By;

/**
 * The type Login page model.
 */
public class LoginPageModel extends BasePageModel {

  /**
   * Field usernameField
   */
  private static By usernameField = By.id("email");

  /**
   * Field passwordField
   */
  private static By passwordField = By.id("password");

  /**
   * Field loginButton
   */
  private static By loginButton = By.id("email_sign_in_button");

  /**
   * Field errorMessage
   */
  private static By errorMessage = By.id("snackbar_text");

  /**
   * Instantiates a new Login page model.
   *
   * @param appiumTestObject the appium test object
   */
  public LoginPageModel(AppiumTestObject appiumTestObject) {
    super(appiumTestObject);
  }

  public void enterCredentials(String username, String password) {
    this.appiumTestObject.getAppiumWait().waitForElement(usernameField).sendKeys(username);
    this.appiumTestObject.getAppiumWait().waitForElement(passwordField).sendKeys(password);
  }

  public void loginWithInvalidCredentials(String username, String password) {

    enterCredentials(username, password);
    this.appiumTestObject.getAppiumWait().waitForElement(loginButton).click();
    this.appiumTestObject.getAppiumWait().waitForElement(errorMessage);

  }

  public MainPageModel loginWithValidCredentials(String username, String password) {

    enterCredentials(username, password);
    this.appiumTestObject.getAppiumWait().waitForElement(loginButton).click();

    return new MainPageModel(this.appiumTestObject);
  }

  public String getErrorMessage() {
    return this.appiumTestObject.getAppiumWait().waitForElement(errorMessage).getText();
  }
}
