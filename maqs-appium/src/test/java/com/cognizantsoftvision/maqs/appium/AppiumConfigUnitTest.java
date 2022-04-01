/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.appium;

import com.cognizantsoftvision.maqs.appium.constants.PlatformType;
import com.cognizantsoftvision.maqs.base.BaseGenericTest;
import com.cognizantsoftvision.maqs.utilities.helper.Config;
import com.cognizantsoftvision.maqs.utilities.helper.ConfigSection;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * The type Appium config unit test.
 */
public class AppiumConfigUnitTest extends BaseGenericTest {

  /**
   * The Username.
   */
  private String username = "username";
  /**
   * The Browser name.
   */
  private String browserName = "browserName";
  /**
   * The Access key.
   */
  private String accessKey = "accessKey";
  /**
   * The Device orientation.
   */
  private String deviceOrientation = "deviceOrientation";

  /**
   * Test get os version.
   *
   * @throws Exception the exception
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetOSVersion() throws Exception {
    String osVersion = AppiumConfig.getPlatformVersion();
    Assert.assertTrue(osVersion.equalsIgnoreCase("11.0"));
  }
}
