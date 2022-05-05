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
import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

/**
 * Page driver factory tests.
 */
public class PageDriverFactoryUnitTest {

  /**
   * Data provider for config browser name test.
   * @return browser name data
   */
  @DataProvider(name = "browserType")
  public Object[] browserType() {
    ArrayList<PlaywrightBrowser> data = new ArrayList<>();
    data.add(PlaywrightBrowser.CHROMIUM);
    data.add(PlaywrightBrowser.FIREFOX);
    data.add(PlaywrightBrowser.WEBKIT);
    data.add(PlaywrightBrowser.CHROME);
    return new Object[] {data};
  }

  /**
   * Check that we can connect to all browser types.
   * *Hold off on Edge as it is not natively on build server
   * @param browserType the list of playwright browsers
   */
  @Test(groups = TestCategories.PLAYWRIGHT, dataProvider = "browserType")
  public void canMakeAllBrowsers(List<PlaywrightBrowser> browserType) {
    for (PlaywrightBrowser singleBrowser : browserType) {
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
    Assert.assertEquals(0, browser.contexts().size());

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
