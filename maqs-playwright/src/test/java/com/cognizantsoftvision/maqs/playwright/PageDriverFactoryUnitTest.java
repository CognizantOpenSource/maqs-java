/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.cognizantsoftvision.maqs.utilities.helper.Config;
import com.cognizantsoftvision.maqs.utilities.helper.ConfigSection;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import com.microsoft.playwright.Browser;
import java.util.ArrayList;
import java.util.Collections;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Page driver factory tests.
 */
public class PageDriverFactoryUnitTest {

  /**
   * Check that we can connect to all browser types.
   * Hold off on Edge as it is not natively on build server
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void canRunAllBrowsers() {
    ArrayList<PlaywrightBrowser> browserList = new ArrayList<>();
    browserList.add(PlaywrightBrowser.CHROMIUM);
    browserList.add(PlaywrightBrowser.FIREFOX);
    browserList.add(PlaywrightBrowser.WEBKIT);
    browserList.add(PlaywrightBrowser.CHROME);

    for (PlaywrightBrowser singleBrowser : browserList) {
      Browser browser = PageDriverFactory.getBrowserWithDefaults(singleBrowser);
      Assert.assertTrue(browser.isConnected());
    }
  }

  /**
   * Test set check works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void browserWithNoContext() {
    Browser browser = PageDriverFactory.getBrowserWithDefaults(PlaywrightBrowser.CHROMIUM);
    Assert.assertEquals(browser.contexts().size(), 0);

    PageDriver pageDriver = PageDriverFactory.getPageDriverFromBrowser(browser);
    Assert.assertFalse(pageDriver.getAsyncPage().isClosed());
  }

  @Test(groups = TestCategories.PLAYWRIGHT, singleThreaded = true)
  public void defaultOptionsUseProxy() {
    String oldProxyValue = Config.getValueForSection(ConfigSection.PLAYWRIGHT_MAQS, "UseProxy");

    try {
      Config.addTestSettingValues(Collections.singletonMap("UseProxy", "Yes"),
          ConfigSection.PLAYWRIGHT_MAQS, true);
      Assert.assertEquals(PageDriverFactory.getDefaultOptions().proxy.server, PlaywrightConfig.getProxyAddress());
    } finally {
      Config.addTestSettingValues(Collections.singletonMap("UseProxy", oldProxyValue),
        ConfigSection.PLAYWRIGHT_MAQS, true);
    }
  }
}
