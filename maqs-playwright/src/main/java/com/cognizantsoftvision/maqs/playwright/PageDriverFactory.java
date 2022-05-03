/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.cognizantsoftvision.maqs.utilities.logging.LoggingConfig;
import com.cognizantsoftvision.maqs.utilities.logging.LoggingEnabled;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.options.ViewportSize;
import java.awt.Dimension;
import java.io.File;

/**
 * The Page Driver Factory class.
 * Factory for creating page drivers
 */
public class PageDriverFactory {
  public static Playwright playwright = Playwright.create();

  private PageDriverFactory() {
  }

  /**
   * Get the default page based on the test run configuration.
   * @return the default page driver
   */
  public static PageDriver getDefaultPageDriver() {
    return getPageDriverForBrowserWithDefaults(PlaywrightConfig.getBrowserType());
  }

  /**
   * Get the default page (for the specified browser type) based on the test run configuration.
   * @param browser The type of browser
   * @return the requested browser
   */
  public static Browser getBrowserWithDefaults(PlaywrightBrowser browser) {

    switch  (browser) {
      case CHROMIUM:
        return getChromiumBasedBrowser(playwright, getDefaultOptions());
      case CHROME:
        return getChromiumBasedBrowser(playwright, getDefaultChromeOptions());
      case EDGE:
        return getChromiumBasedBrowser(playwright, getDefaultEdgeOptions());
      case FIREFOX:
        return getFirefoxBasedBrowser(playwright, getDefaultOptions());
      case WEBKIT:
        return getWebkitBasedBrowser(playwright, getDefaultOptions());
      default:
        throw new UnsupportedOperationException("Browser type '" + browser + "' is not supported");
    }
  }

  /**
   * Get the default page (for the specified browser type) based on the test run configuration.
   * @param browser The type of browser
   * @return the page browser
   */
  public static PageDriver getPageDriverForBrowserWithDefaults(PlaywrightBrowser browser) {
    Browser asyncBrowser = getBrowserWithDefaults(browser);
    return getPageDriverFromBrowser(asyncBrowser);
  }

  /**
   * Gets the page driver from the browser.
   * @param browser the current browser
   * @return the page driver
   */
  public static PageDriver getPageDriverFromBrowser(Browser browser) {
    BrowserContext context;

    // Get resolution
    Dimension resolution = PlaywrightConfig.getBrowserSize();
    ViewportSize size = new ViewportSize(resolution.width, resolution.height);

    // Default to the first context, if at least one context exists
    if (!browser.contexts().isEmpty()) {
      return getNewPageDriverFromBrowserContext(browser.contexts().get(0));
    }

    if (LoggingConfig.getLoggingEnabledSetting() != LoggingEnabled.NO) {
      if (PlaywrightConfig.getCaptureVideo()) {
        Browser.NewContextOptions options = new Browser.NewContextOptions();
        ////options.setRecordVideoDir(new File(LoggingConfig.getLogDirectory() + "/videos/").toPath());
        //// options.viewportSize = java.util.Optional.of(size);
        context = browser.newContext(options);
      } else {
        Browser.NewContextOptions options = new Browser.NewContextOptions();
        options.viewportSize = java.util.Optional.of(size);
        context = browser.newContext(options);
      }

      // Start tracing before creating / navigating a page.
      //// Tracing.StartOptions options = new Tracing.StartOptions();
      //// options.setScreenshots(PlaywrightConfig.getCaptureScreenshots());
      //// options.setSnapshots(PlaywrightConfig.getCaptureSnapshots());
      //// context.tracing().start(options);
    } else {
      Browser.NewContextOptions options = new Browser.NewContextOptions();
      options.setViewportSize(size);
      context = browser.newContext(options);
    }
    return getNewPageDriverFromBrowserContext(context);
  }

  /**
   * Get a new page driver for the given browser context.
   * @param context The current browser's context
   * @return the page driver
   */
  public static PageDriver getNewPageDriverFromBrowserContext(BrowserContext context) {
    Page page = context.newPage();
    
    /// 2 DO Reenable
    //// page.setDefaultTimeout(PlaywrightConfig.getTimeoutTime());
    //// page.setDefaultNavigationTimeout(PlaywrightConfig.getTimeoutTime());
    return new PageDriver(page);
  }

  /**
   * Get the chromium based browser.
   * @param playwright the playwright object
   * @param options the browser options
   * @return a chromium based browser
   */
  public static Browser getChromiumBasedBrowser(Playwright playwright, BrowserType.LaunchOptions options) {
    return playwright.chromium().launch(options);
  }

  /**
   * Get the firefox browser.
   * @param playwright the playwright object
   * @param options the browser options
   * @return a firefox based browser
   */
  public static Browser getFirefoxBasedBrowser(Playwright playwright, BrowserType.LaunchOptions options) {
    return playwright.firefox().launch(options);
  }

  /**
   * Get the webkit browser.
   * @param playwright the playwright object
   * @param options the browser options
   * @return a webkit based browser
   */
  public static Browser getWebkitBasedBrowser(Playwright playwright, BrowserType.LaunchOptions options) {
    return playwright.webkit().launch(options);
  }

  /**
   * Get the default Chrome options.
   * @return The default Chrome options
   */
  public static BrowserType.LaunchOptions getDefaultChromeOptions() {
    BrowserType.LaunchOptions options = getDefaultOptions();
    options.channel = "chrome";
    options.headless = PlaywrightConfig.getHeadless();
    return options;
  }

  /**
   * Get the default Edge options.
   * @return the default Edge options
   */
  public static BrowserType.LaunchOptions getDefaultEdgeOptions() {
    BrowserType.LaunchOptions options = getDefaultOptions();
    options.channel = "msedge";
    options.headless = PlaywrightConfig.getHeadless();
    return options;
  }

  /**
   * Get the default options.
   * @return the default options
   */
  public static BrowserType.LaunchOptions getDefaultOptions() {
    // Check if we should add proxy
    if (PlaywrightConfig.getUseProxy()) {
      BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
      options.proxy.server = PlaywrightConfig.getProxyAddress();
      options.headless = PlaywrightConfig.getHeadless();
      options.timeout = PlaywrightConfig.getCommandTimeout();
      return options;
    }

    // Return options without proxy
    BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
    options.headless = PlaywrightConfig.getHeadless();
    options.timeout = PlaywrightConfig.getCommandTimeout();
    return options;
  }
}
