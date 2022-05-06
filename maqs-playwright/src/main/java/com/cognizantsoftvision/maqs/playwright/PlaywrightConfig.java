/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.cognizantsoftvision.maqs.utilities.helper.Config;
import com.cognizantsoftvision.maqs.utilities.helper.ConfigSection;
import java.awt.Dimension;

/**
 * The Playwright Config class.
 */
public class PlaywrightConfig {

  /**
   * Private playwright constructor.
   */
  protected PlaywrightConfig() {
  }

  /**
   * Get the website base url.
   * @return The website base url
   */
  public static String getWebBase() {
    return Config.getValueForSection(ConfigSection.PLAYWRIGHT_MAQS.toString(), "WebBase");
  }

  /**
   * Get the browser type.
   * @return The browser type
   */
  public static PlaywrightBrowser getBrowserType() {
    return getBrowserType(getBrowserName());
  }

  /**
   * Get the browser type based on the provided browser name.
   * @param browserName Name of the browser
   * @return The browser type
   */
  public static PlaywrightBrowser getBrowserType(String browserName) {

    switch (browserName.toUpperCase()) {
      case "CHROME":
        return PlaywrightBrowser.CHROME;
      case "CHROMIUM":
        return PlaywrightBrowser.CHROMIUM;
      case "FIREFOX":
        return PlaywrightBrowser.FIREFOX;
      case "EDGE":
        return PlaywrightBrowser.EDGE;
      case "WEBKIT":
        return PlaywrightBrowser.WEBKIT;
      default:
        throw new IllegalArgumentException("Browser type '" + browserName + "' is not supported");
    }
  }

  /**
   * Get if we should run Playwright headless.
   * @return True if we want to run Playwright headless
   */
  public static boolean getHeadless() {
    return Config.getValueForSection(ConfigSection.PLAYWRIGHT_MAQS.toString(), "Headless").equalsIgnoreCase("Yes");
  }

  /**
   * Get the browser type name - Example: Chrome.
   * @return The browser type
   */
  public static String getBrowserName() {
    return Config.getValueForSection(ConfigSection.PLAYWRIGHT_MAQS.toString(), "Browser", "Chrome");
  }

  /**
   * Get the initialized Playwright timeout.
   * @return The initialized timeout
   */
  public static double getCommandTimeout() {
    String value = Config.getValueForSection(
        ConfigSection.PLAYWRIGHT_MAQS.toString(), "CommandTimeout", "60000");

    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(
          "PlaywrightCommandTimeout should be a number but the current value is: " + value);
    }
  }

  /**
   * Get the timeout in milliseconds.
   * @return The timeout in milliseconds
   */
  public static double getTimeoutTime() {
    return Integer.parseInt(Config.getValueForSection(ConfigSection.PLAYWRIGHT_MAQS.toString(), "Timeout", "0"));
  }

  /**
   * Get if we want to capture video - This may bloat your test result.
   * @return True if we want to capture video
   */
  public static boolean getCaptureVideo() {
    return Config.getValueForSection(
        ConfigSection.PLAYWRIGHT_MAQS.toString(), "CaptureVideo", "NO").equalsIgnoreCase("Yes");
  }

  /**
   * Get if we want to capture screenshots - This may bloat your test result.
   * @return True if we want to capture screenshots
   */
  public static boolean getCaptureScreenshots() {
    return Config.getValueForSection(
        ConfigSection.PLAYWRIGHT_MAQS.toString(), "CaptureScreenshots", "NO").equalsIgnoreCase("Yes");
  }

  /**
   * Get if we want to capture snapshots - This may bloat your test result.
   * @return True if we want to capture snapshots
   */
  public static boolean getCaptureSnapshots() {
    return Config.getValueForSection(
        ConfigSection.PLAYWRIGHT_MAQS.toString(), "CaptureSnapshots", "NO").equalsIgnoreCase("Yes");
  }

  /**
   * get the browser size.
   * @return the dimensions that contain length and height of the browser size
   */
  public static Dimension getBrowserSize() {
    String size = Config.getValueForSection(
        ConfigSection.PLAYWRIGHT_MAQS.toString(), "BrowserSize", "MAXIMIZE".toUpperCase());
    Dimension dimension = new Dimension();

    if (size.equals("DEFAULT")) {
      dimension.setSize(1280, 720);
      return dimension;
    }
    return extractSizeFromString(size);
  }

  /**
   * Get if we want to use a proxy for the page traffic.
   * @return True if we want to use the proxy
   */
  public static boolean getUseProxy() {
    return Config.getValueForSection(
        ConfigSection.PLAYWRIGHT_MAQS.toString(), "UseProxy", "NO").equalsIgnoreCase("Yes");
  }

  /**
   * Get the proxy address to use.
   * @return The proxy address
   */
  public static String getProxyAddress() {
    return Config.getValueForSection(ConfigSection.PLAYWRIGHT_MAQS.toString(), "ProxyAddress");
  }

  /**
   * Get the window size as a string.
   * @param size The size in a #X# format
   * @return the dimensions that contain length and height of the browser size
   */
  private static Dimension extractSizeFromString(String size) {
    String[] sizes = size.split("[xX]");

    if (!size.toUpperCase().contains("X") || sizes.length != 2) {
      throw new IllegalArgumentException("Browser size is expected to be in an expected format: 1920x1080");
    }

    try {
      int width = Integer.parseInt(sizes[0]);
      int height = Integer.parseInt(sizes[1]);
      return new Dimension(width, height);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Length and Width must be a string that is an integer value: 400x400");
    }
  }
}
