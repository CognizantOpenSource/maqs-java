/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium;

import com.cognizantsoftvision.maqs.base.BaseGenericTest;
import com.cognizantsoftvision.maqs.selenium.constants.BrowserType;
import com.cognizantsoftvision.maqs.selenium.constants.RemoteBrowserType;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import java.time.Duration;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Selenium configuration tests.
 */
public class SeleniumConfigUnitTest extends BaseGenericTest {

  /**
   * Remote capabilities username identifier.
   */
  private String username = "username";
  /**
   * Remote browser access key identifier.
   */
  private String accessKey = "accessKey";
  /**
   * Remote browser name identifier.
   */
  private String browserName = "browserName";
  /**
   * Remote version platform identifier.
   */
  private String platform = "platform";
  /**
   * Remote browser version identifier.
   */
  private String version = "version";

  /**
   * Browser name.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void getBrowserName() {

    String driverName = SeleniumConfig.getBrowserName();

    Assert.assertTrue(driverName.equalsIgnoreCase("HEADLESSCHROME"));
  }
}
