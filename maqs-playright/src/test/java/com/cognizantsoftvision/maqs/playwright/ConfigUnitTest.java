/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.cognizantsoftvision.maqs.utilities.helper.Config;
import com.cognizantsoftvision.maqs.utilities.helper.ConfigSection;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ConfigUnitTest {

  /// <summary>
  /// Get expected WebBase configuration
  /// </summary>
  @Test()
  public void testGetBrowser() {
    Assert.assertEquals(PlaywrightConfig.getBrowserName(), "Chrome");
  }

        [DataTestMethod]
      [DataRow("Chrome")]
      [DataRow("Chromium")]
      [DataRow("Firefox")]
      [DataRow("Edge")]
      [DataRow("Webkit")]

  public void ConfigBrowserName(String browserName) {
    Config.addaddTestSettingValues("Browser", browserName, ConfigSection.PLAYWRIGHT_MAQS);
    Assert.assertEquals(PlaywrightConfig.getBrowserName(), browserName);
  }

        [DataTestMethod]
      [DataRow("Chromium", PlaywrightBrowser.hromium)]
      [DataRow("Firefox", PlaywrightBrowser.Firefox)]
      [DataRow("Edge", PlaywrightBrowser.Edge)]
      [DataRow("Webkit", PlaywrightBrowser.Webkit)]
      [DataRow(null, PlaywrightBrowser.Chrome)]
      [DataRow("Chrome", PlaywrightBrowser.Chrome)]
  public void ConfigBrowserEnum(string browser, PlaywrightBrowser browserEnum) {
    Config.AddTestSettingValue("Browser", browser, ConfigSection.PLAYWRIGHT_MAQS);
    Assert.AreEqual(browserEnum, PlaywrightConfig.getBrowserType());
  }

  /// <summary>
  /// Make sure error correct error is thrown if we use a bad browser name
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT, expectedExceptions = IllegalArgumentException.class)
  public void configBadBrowserName() {
    //Config.addAddTestSettingValue("Browser", "IE", ConfigSection.PlaywrightMaqs);
    PlaywrightBrowser type = PlaywrightConfig.getBrowserType();
    Assert.fail("IE returned type: " + type);
  }

  /// <summary>
  /// Get expected UseProxy configuration
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void getUseProxy() {
    Assert.assertFalse(PlaywrightConfig.getUseProxy());
  }

  /// <summary>
  /// Get expected proxy address configuration
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void getProxyAddress() {
    Assert.assertEquals(PlaywrightConfig.getProxyAddress(), "http://localhost:8002");
  }
}
