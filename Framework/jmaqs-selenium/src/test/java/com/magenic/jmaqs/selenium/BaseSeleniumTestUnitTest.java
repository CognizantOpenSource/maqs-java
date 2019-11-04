/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The type Base selenium test unit test.
 */
public class BaseSeleniumTestUnitTest extends BaseSeleniumTest {

  @Test(groups = TestCategories.Selenium)
  public void testGetWebDriver() {
    Assert.assertNotNull(this.getWebDriver(),
        "Checking that Selenium Driver is not null through BaseSeleniumTest");
  }

  @Test(groups = TestCategories.Selenium)
  public void testSetWebDriver() throws Exception {
    int hashCode = this.getWebDriver().hashCode();
    this.setWebDriver(this.getBrowser());
    int hashCode1 = this.getWebDriver().hashCode();
    Assert.assertNotEquals(hashCode, hashCode1);
  }

  @Test(groups = TestCategories.Selenium)
  public void testGetSeleniumTestObject() {
    Assert.assertNotNull(this.getTestObject(),
        "Checking that Selenium Test Object is not null through BaseSeleniumTest");
  }

  @Test(groups = TestCategories.Selenium)
  public void testGetBrowser() {
    try {
      Assert.assertNotNull(this.getBrowser(),
          "Checking that Selenium Driver is not null through BaseSeleniumTest");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void postSetupLogging() throws Exception {

  }
}