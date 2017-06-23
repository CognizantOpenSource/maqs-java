package com.magenic.jmaqs.appium.test;

import com.magenic.jmaqs.appium.baseAppiumTest.AppiumConfig;
import com.magenic.jmaqs.appium.baseAppiumTest.BaseAppiumTest;
import com.magenic.jmaqs.appium.pagemodels.LoginPageModel;
import com.magenic.jmaqs.appium.pagemodels.MainPageModel;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by jasonedstrom on 6/23/17.
 */
public class AppiumTest extends BaseAppiumTest {

  @Test
  public void applicationInstalledTest() {
    Assert.assertTrue(this.getAppiumDriver().isAppInstalled(AppiumConfig.getBundleId()),
        "Expected application to be installed");
  }

  @Test
  public void invalidLoginTest()
  {
    String expectedError = "Wrong username or password";
    LoginPageModel page = new LoginPageModel(this.getAppiumTestObject());
    page.loginWithInvalidCredentials("Not", "Valid");
    String errorMessage = page.getErrorMessage();
    Assert.assertEquals(errorMessage, expectedError, "Expected error message to be equal");
  }

  @Test
  public void validLoginTest()
  {
    String username = "Magenic";
    String password = "MAQS";
    LoginPageModel page = new LoginPageModel(this.getAppiumTestObject());
    MainPageModel mainPageModel = page.loginWithValidCredentials(username, password);
    String loggedInPassword = mainPageModel.getPasswordValueText();
    String loggedInUsername = mainPageModel.getUsernameValueText();

    Assert.assertEquals(loggedInUsername, username, "Expected username value to be equal");
    Assert.assertEquals(loggedInPassword, password, "Expected password value to be equal");
  }
}
