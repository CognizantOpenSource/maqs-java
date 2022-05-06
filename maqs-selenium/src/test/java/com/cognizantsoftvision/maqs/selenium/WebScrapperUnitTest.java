/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.utilities.helper.TestCategories;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class WebScrapperUnitTest extends BaseSeleniumTest{

  /**
   * Setup before a test
   */
  @BeforeMethod
  public void navigateToTestPage() {
    this.getWebDriver().navigate().to(SeleniumConfig.getWebSiteBase() + "Automation");
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();
  }

  @Test(groups = TestCategories.SELENIUM)
  public void scrapeWebPage() throws IOException {
    WebScrapper.scrapWebPage(this.getWebDriver(), "ScrappedWebPage", "",false);
  }
}
