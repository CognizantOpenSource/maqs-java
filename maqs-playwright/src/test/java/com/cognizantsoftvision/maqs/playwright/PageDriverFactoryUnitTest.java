/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

  /**
   * Test set check works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void tempTestingRemove() {

    Browser browser = PageDriverFactory.getBrowserWithDefaults(PlaywrightBrowser.CHROMIUM);
    Page page = browser.newPage();
    
    //// You may want to do this: Page page = PageDriverFactory.getDefaultPageDriver().getAsyncPage();
    page.navigate("http://playwright.dev");
    System.out.println(page.title());
    Assert.assertNotEquals(page.title(), "Other Title");
  }
}
