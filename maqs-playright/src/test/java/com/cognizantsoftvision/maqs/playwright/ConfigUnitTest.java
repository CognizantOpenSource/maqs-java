/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.cognizantsoftvision.maqs.utilities.helper.Config;
import com.cognizantsoftvision.maqs.utilities.helper.ConfigSection;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import java.util.Collections;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * The Config Unit tests for playwright config content.
 */
public class ConfigUnitTest {

  /// <summary>
  /// Get expected WebBase configuration
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void testGetBrowser() {
    Assert.assertEquals(PlaywrightConfig.getBrowserName(), "Chrome");
  }

  @DataProvider (name = "browserType")
  public Object[][] browserType() {
    return new Object[][] {{"Chrome"}, {"Chromium"}, {"Firefox"}, {"Edge"}, {"Webkit"}};
  }

  @Test(groups = TestCategories.PLAYWRIGHT, dataProvider = "browserType")
  public void ConfigBrowserName(String browserName) {
    Config.addTestSettingValues(Collections.singletonMap("Browser", browserName),
        ConfigSection.PLAYWRIGHT_MAQS, true);
    Assert.assertEquals(PlaywrightConfig.getBrowserName(), browserName);
  }

  @DataProvider (name = "playwrightBrowserType")
  public Object[][] playwrightBrowserType() {
    return new Object[][] {{"Chrome", PlaywrightBrowser.CHROME}, {"Chromium", PlaywrightBrowser.CHROMIUM},
        {"Firefox", PlaywrightBrowser.FIREFOX}, {"Edge", PlaywrightBrowser.EDGE},
        {"Webkit", PlaywrightBrowser.WEBKIT}, {null, PlaywrightBrowser.CHROME} };
  }

  @Test(groups = TestCategories.PLAYWRIGHT, dataProvider = "playwrightBrowserType")
  public void ConfigBrowserEnum(String browser, PlaywrightBrowser browserEnum) {
    Config.addTestSettingValues(Collections.singletonMap("Browser", browser), ConfigSection.PLAYWRIGHT_MAQS, true);
    Assert.assertEquals(browserEnum, PlaywrightConfig.getBrowserType());
  }

  /// <summary>
  /// Make sure error correct error is thrown if we use a bad browser name
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT, expectedExceptions = IllegalArgumentException.class)
  public void configBadBrowserName() {
    Config.addTestSettingValues(Collections.singletonMap("Browser", "IE"), ConfigSection.PLAYWRIGHT_MAQS, true);
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
