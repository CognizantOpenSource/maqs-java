/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.cognizantsoftvision.maqs.utilities.helper.Config;
import com.cognizantsoftvision.maqs.utilities.helper.ConfigSection;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Config Unit tests for playwright config content.
 */
@Test(singleThreaded = true)
public class PlaywrightConfigUnitTest {

  /**
   * String placeholder for "Browser".
   */
  private static final String browser = "Browser";

  /**
   * Get playwright section.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void getPlaywrightSection() {
    Map<String, String> config = Config.getSection(ConfigSection.PLAYWRIGHT_MAQS);
    Assert.assertNotNull(config);
  }

  /**
   * Get expected WebBase configuration.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void testGetBrowser() {
    Config.addTestSettingValues(Collections.singletonMap(browser, "Chrome"),
        ConfigSection.PLAYWRIGHT_MAQS, true);
    Assert.assertEquals(PlaywrightConfig.getBrowserName(), "Chrome");
  }

  /**
   * Test the config browser name.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void configBrowserName() {
    ArrayList<String> browserNames = new ArrayList<>();
    browserNames.add("Chromium");
    browserNames.add("Firefox");
    browserNames.add("Edge");
    browserNames.add("Webkit");
    browserNames.add("Chrome");

    for (String browserName : browserNames) {
      Config.addTestSettingValues(Collections.singletonMap(browser, browserName),
          ConfigSection.PLAYWRIGHT_MAQS, true);
      Assert.assertEquals(PlaywrightConfig.getBrowserName().toUpperCase(), browserName.toUpperCase());
    }
  }

  /**
   * Make sure error correct error is thrown if we use a bad browser name.
   */
  @Test(groups = TestCategories.PLAYWRIGHT, expectedExceptions = IllegalArgumentException.class,
      expectedExceptionsMessageRegExp = "Browser type 'IE' is not supported")
  public void configBadBrowserName() {
    Config.addTestSettingValues(Collections.singletonMap(browser, "IE"), ConfigSection.PLAYWRIGHT_MAQS, true);
    PlaywrightConfig.getBrowserType();
  }

  /**
   * Test the config browser enums.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void configBrowserEnum() {
    for (PlaywrightBrowser currentBrowser : PlaywrightBrowser.class.getEnumConstants()) {
      Config.addTestSettingValues(Collections.singletonMap(browser, currentBrowser.name()), ConfigSection.PLAYWRIGHT_MAQS, true);
      Assert.assertEquals(currentBrowser, PlaywrightConfig.getBrowserType());
    }
  }

  /**
   * Get expected UseProxy configuration.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void getUseProxy() {
    Assert.assertFalse(PlaywrightConfig.getUseProxy());
  }

  /**
   * Get expected proxy address configuration.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void getProxyAddress() {
    Assert.assertEquals(PlaywrightConfig.getProxyAddress(), "http://localhost:8002");
  }

  /**
   * Gets the command timeout.
   */
  @Test(groups = TestCategories.PLAYWRIGHT, expectedExceptions = IllegalArgumentException.class)
  public void getCommandTimeout() {
    Assert.assertEquals(PlaywrightConfig.getCommandTimeout(), 200000);

    String oldValue = Config.getValueForSection(ConfigSection.PLAYWRIGHT_MAQS, "CommandTimeout");
    try {
      Config.addTestSettingValues(Collections.singletonMap("CommandTimeout", "asdXasd"),
          ConfigSection.PLAYWRIGHT_MAQS, true);
      PlaywrightConfig.getCommandTimeout();
    } finally {
      Config.addTestSettingValues(Collections.singletonMap("CommandTimeout", oldValue),
          ConfigSection.PLAYWRIGHT_MAQS, true);
    }
  }

  /**
   * Get expected window size.
   */
  @Test(groups = TestCategories.PLAYWRIGHT, singleThreaded = true)
  public void getWindowSize() {
    String oldValue = Config.getValueForSection(ConfigSection.PLAYWRIGHT_MAQS, "BrowserSize");
    
    try {
      Config.addTestSettingValues(Collections.singletonMap("BrowserSize", "600x900"),
      ConfigSection.PLAYWRIGHT_MAQS, true);
      Assert.assertEquals(PlaywrightConfig.getBrowserSize().getHeight(), 900);
      Assert.assertEquals(PlaywrightConfig.getBrowserSize().getWidth(), 600);

      Config.addTestSettingValues(Collections.singletonMap("BrowserSize", "DEFAULT"),
          ConfigSection.PLAYWRIGHT_MAQS, true);
      Assert.assertEquals(PlaywrightConfig.getBrowserSize().getHeight(), 720);
      Assert.assertEquals(PlaywrightConfig.getBrowserSize().getWidth(), 1280);
    } finally {
      Config.addTestSettingValues(Collections.singletonMap("BrowserSize", oldValue),
      ConfigSection.PLAYWRIGHT_MAQS, true);
    }
  }

  /**
   * Gets window size Illegal argument exception.
   */
  @Test(groups = TestCategories.PLAYWRIGHT, singleThreaded = true,
      expectedExceptions = IllegalArgumentException.class)
  public void getWindowSizeIllegalArgumentException() {
    String oldValue = Config.getValueForSection(ConfigSection.PLAYWRIGHT_MAQS, "BrowserSize");

    try {
      Config.addTestSettingValues(Collections.singletonMap("BrowserSize", "600900"),
          ConfigSection.PLAYWRIGHT_MAQS, true);
      PlaywrightConfig.getBrowserSize();
    } finally {
      Config.addTestSettingValues(Collections.singletonMap("BrowserSize", oldValue),
          ConfigSection.PLAYWRIGHT_MAQS, true);
    }
  }

  /**
   * Gets window size number format exception.
   */
  @Test(groups = TestCategories.PLAYWRIGHT, singleThreaded = true,
      expectedExceptions = NumberFormatException.class)
  public void getWindowSizeNumberFormatException() {
    String oldValue = Config.getValueForSection(ConfigSection.PLAYWRIGHT_MAQS, "BrowserSize");

    try {
      Config.addTestSettingValues(Collections.singletonMap("BrowserSize", "asdXasd"),
          ConfigSection.PLAYWRIGHT_MAQS, true);
      PlaywrightConfig.getBrowserSize();
    } finally {
      Config.addTestSettingValues(Collections.singletonMap("BrowserSize", oldValue),
      ConfigSection.PLAYWRIGHT_MAQS, true);
    }
  }
}
