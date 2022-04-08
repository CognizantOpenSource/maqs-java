/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

import com.cognizantsoftvision.maqs.utilities.logging.LoggingConfig;
import com.cognizantsoftvision.maqs.utilities.logging.LoggingEnabled;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Proxy;
import com.microsoft.playwright.options.ViewportSize;
import java.io.File;

/// <summary>
/// Factory for creating page drivers
/// </summary>
public class PageDriverFactory {

  private PageDriverFactory() {
  }

  /// <summary>
  /// Get the default page based on the test run configuration
  /// </summary>
  /// <returns>A page</returns>
  public static PageDriver getDefaultPageDriver() {
    return getPageDriverForBrowserWithDefaults(PlaywrightConfig.getBrowserType());
  }

  /// <summary>
  /// Get the default page (for the specified browser type) based on the test run configuration
  /// </summary>
  /// <param name="browser">The type of browser</param>
  /// <returns>A page</returns>
  public static Browser getBrowserWithDefaults(PlaywrightBrowser browser) {
    Playwright playwright = Playwright.create();
    
    switch (browser) {
      case CHROMIUM:
      case CHROME:
        return getChromiumBasedBrowser(playwright, getDefaultChromeOptions());
      case EDGE:
        return getChromiumBasedBrowser(playwright, getDefaultEdgeOptions());
      case FIREFOX:
        return getFirefoxBasedBrowser(playwright, getDefaultOptions());
      case WEBKIT:
        return getWebkitBasedBrowser(playwright, getDefaultOptions());
      default:
        throw new IllegalArgumentException("Browser type '" + browser + "' is not supported");
    }
  }

  /// <summary>
  /// Get the default page (for the specified browser type) based on the test run configuration
  /// </summary>
  /// <param name="browser">The type of browser</param>
  /// <returns>A page</returns>
  public static PageDriver getPageDriverForBrowserWithDefaults(PlaywrightBrowser browser) {
    Browser asyncBrowser = getBrowserWithDefaults(browser);
    return getPageDriverFromBrowser(asyncBrowser);
  }

  /// <summary>
  ///
  /// </summary>
  /// <param name="browser">The current browser</param>
  /// <returns>A page</returns>
  public static PageDriver getPageDriverFromBrowser(Browser browser) {
    BrowserContext context;

    // Get resolution
    ViewportSize size = new ViewportSize(
        PlaywrightConfig.getBrowserSize().width, PlaywrightConfig.getBrowserSize().height);

    // Default to the first context, if at least one context exists
    if (!browser.contexts().isEmpty()) {
      return getNewPageDriverFromBrowserContext(browser.contexts().get(0));
    }

    if (LoggingConfig.getLoggingEnabledSetting() != LoggingEnabled.NO) {
      if (PlaywrightConfig.getCaptureVideo()) {
        Browser.NewContextOptions options = new Browser.NewContextOptions();
        options.setRecordVideoDir(new File(LoggingConfig.getLogDirectory() + "/videos/").toPath());
        options.viewportSize = java.util.Optional.of(size);
        context = browser.newContext(options);
      } else {
        Browser.NewContextOptions options = new Browser.NewContextOptions();
        options.viewportSize = java.util.Optional.of(size);
        context = browser.newContext(options);
      }

      // Start tracing before creating / navigating a page.
      Tracing.StartOptions options = new Tracing.StartOptions();
      options.setScreenshots(PlaywrightConfig.getCaptureScreenshots());
      options.setSnapshots(PlaywrightConfig.getCaptureSnapshots());
      context.tracing().start(options);
    } else {
      Browser.NewContextOptions options = new Browser.NewContextOptions();
      options.setViewportSize(size);
      context = browser.newContext(options);
    }
    return getNewPageDriverFromBrowserContext(context);
  }

  /// <summary>
  /// Get a new page driver for the given browser context
  /// </summary>
  /// <param name="context">The current browser's context</param>
  /// <returns>A page</returns>
  public static PageDriver getNewPageDriverFromBrowserContext(BrowserContext context) {
    Page page = context.newPage();
    page.setDefaultTimeout(PlaywrightConfig.getTimeoutTime());
    page.setDefaultNavigationTimeout(PlaywrightConfig.getTimeoutTime());

    return new PageDriver(page);
  }

  /// <summary>
  /// Get Chromium browser
  /// </summary>
  /// <param name="playwright">Playw</param>
  /// <param name="options">Browser options</param>
  /// <returns>A Chromium browser</returns>
  public static Browser getChromiumBasedBrowser(Playwright playwright, BrowserType.LaunchOptions options) {
    return playwright.chromium().launch(options);
  }

  /// <summary>
  /// Get Firefox browser
  /// </summary>
  /// <param name="playwright">Playw</param>
  /// <param name="options">Browser options</param>
  /// <returns>A Firefox browser</returns>
  public static Browser getFirefoxBasedBrowser(Playwright playwright, BrowserType.LaunchOptions options) {
    return playwright.firefox().launch(options);
  }

  /// <summary>
  /// Get Webkit browser
  /// </summary>
  /// <param name="playwright">Playw</param>
  /// <param name="options">Browser options</param>
  /// <returns>A Webkit browser</returns>
  public static Browser getWebkitBasedBrowser(Playwright playwright, BrowserType.LaunchOptions options) {
    return playwright.webkit().launch(options);
  }

  /// <summary>
  /// Get the default Chrome options
  /// </summary>
  /// <returns>The default Chrome options</returns>
  public static BrowserType.LaunchOptions getDefaultChromeOptions() {
    BrowserType.LaunchOptions options = getDefaultOptions();
    options.channel = "chrome";

    return options;
  }

  /// <summary>
  /// Get the default Edge options
  /// </summary>
  /// <returns>The default Chrome options</returns>
  public static BrowserType.LaunchOptions getDefaultEdgeOptions() {
    BrowserType.LaunchOptions options = getDefaultOptions();
    options.channel = "msedge";
    return options;
  }


  /// <summary>
  /// Get the default options
  /// </summary>
  /// <returns>The default options</returns>
  public static BrowserType.LaunchOptions getDefaultOptions() {
    // Check if we should add proxy
    if(PlaywrightConfig.getUseProxy()) {
      BrowserType.LaunchOptions options = new BrowserType.LaunchOptions();
      options.proxy = new Proxy(PlaywrightConfig.getProxyAddress());
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
