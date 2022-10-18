/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.appium;

import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Base Appium Test unit test class.
 */
public class BaseAppiumTestUnitTest extends BaseAppiumTest {

  /**
   * Test get appium driver.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetAppiumDriver() {
    Assert.assertNotNull(this.getAppiumDriver(),
        "Checking that Appium Driver is not null through BaseAppiumTest");
  }

  /**
   * Test set appium driver.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testSetAppiumDriver() {
    int hashCode = this.getAppiumDriver().hashCode();
    this.setAppiumDriver(this.getMobileDriver());
    int hashCode1 = this.getAppiumDriver().hashCode();
    Assert.assertNotEquals(hashCode, hashCode1);
  }

  /**
   * Test get appium test object.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetAppiumTestObject() {
    Assert.assertNotNull(this.getTestObject(),
        "Checking that Appium Test Object is not null through BaseAppiumTest");
  }

  /**
   * Test get mobile driver.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetMobileDriver() {
    Assert.assertNotNull(this.getMobileDriver(),
        "Checking that Appium Driver is not null through BaseAppiumTest");
  }

}