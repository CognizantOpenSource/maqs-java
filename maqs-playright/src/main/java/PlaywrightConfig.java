/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

import com.cognizantsoftvision.maqs.utilities.helper.Config;
import com.cognizantsoftvision.maqs.utilities.helper.ConfigSection;
import java.awt.Dimension;

/// <summary>
/// Config class
/// </summary>
public class PlaywrightConfig {

  /// <summary>
  /// Loads when class is loaded
  /// </summary>
  PlaywrightConfig() {
    checkConfig();
  }

  /// <summary>
  /// Ensure required fields are in the config
  /// </summary>
  private static void checkConfig() {
//    var validator = new ConfigValidation()
//    {
//      RequiredFields = new List<string>()
//      {
//        "Timeout"
//      }
//    };
//    Config.Validate(ConfigSection.PLAYWRIGHT_MAQS, validator);
  }

  /// <summary>
  /// Get the web site base url
  /// </summary>
  /// <returns>The web site base url</returns>
  public static String getWebBase() {
    return Config.getValueForSection(ConfigSection.PLAYWRIGHT_MAQS.toString(), "WebBase");
  }

  /// <summary>
  /// Get the browser type
  /// </summary>
  /// <returns>The browser type</returns>
  public static PlaywrightBrowser getBrowserType() {
    return getBrowserType(getBrowserName());
  }

  /// <summary>
  /// Get the browser type based on the provide browser name
  /// </summary>
  /// <param name="browserName">Name of the browser</param>
  /// <returns>The browser type</returns>
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

  /// <summary>
  /// Get if we should run Playwright headless
  /// </summary>
  /// <returns>True if we want to run Playwright headless</returns>
  public static boolean getHeadless() {
    return Config.getValueForSection(ConfigSection.PLAYWRIGHT_MAQS.toString(), "Headless").equals("Yes");
  }

  /// <summary>
  /// Get the browser type name - Example: Chrome
  /// </summary>
  /// <returns>The browser type</returns>
  public static String getBrowserName() {
    return Config.getValueForSection(ConfigSection.PLAYWRIGHT_MAQS.toString(), "Browser", "Chrome");
  }

  /// <summary>
  /// Get the initialize Playwright timeout
  /// </summary>
  /// <returns>The initialize timeout</returns>
  public static double getCommandTimeout() {
    String value = Config.getValueForSection(
        ConfigSection.PLAYWRIGHT_MAQS.toString(), "CommandTimeout", "60000");

    try {
      return Integer.parseInt(value);
    } catch(NumberFormatException e) {
      throw new IllegalArgumentException(
          "PlaywrightCommandTimeout should be a number but the current value is: " + value);
    }
  }

  /// <summary>
  /// Get the timeout in milliseconds
  /// </summary>
  /// <returns>The timeout in milliseconds</returns>
  public static int getTimeoutTime() {
    return Integer.parseInt(Config.getValueForSection(ConfigSection.PLAYWRIGHT_MAQS.toString(), "Timeout"));
  }

  /// <summary>
  /// Get if we want to capture video - This may bloat your test result
  /// </summary>
  /// <returns>True if we want to capture video</returns>
  public static boolean getCaptureVideo() {
    return Config.getValueForSection(
        ConfigSection.PLAYWRIGHT_MAQS.toString(), "CaptureVideo", "NO").equals("Yes");
  }

  /// <summary>
  /// Get if we want to capture screenshots - This may bloat your test result
  /// </summary>
  /// <returns>True if we want to capture screenshots</returns>
  public static boolean getCaptureScreenshots() {
    return Config.getValueForSection(
        ConfigSection.PLAYWRIGHT_MAQS.toString(), "CaptureScreenshots", "NO").equals("Yes");
  }

  /// <summary>
  /// Get if we want to capture snapshots - This may bloat your test result
  /// </summary>
  /// <returns>True if we want to capture snapshots</returns>
  public static boolean getCaptureSnapshots() {
    return Config.getValueForSection(
        ConfigSection.PLAYWRIGHT_MAQS.toString(), "CaptureSnapshots", "NO").equals("Yes");
  }

  /// <summary>
  /// get the browser size
  /// </summary>
  /// <returns></returns>
  public static Dimension getBrowserSize() {
    String size = Config.getValueForSection(
        ConfigSection.PLAYWRIGHT_MAQS.toString(), "BrowserSize", "DEFAULT").toUpperCase();
    Dimension dimension = new Dimension();

    if (size.equals("DEFAULT")) {
      dimension.setSize(1280, 720);
      return dimension;
    }
    return extractSizeFromString(size);
  }

  /// <summary>
  /// Get if we want to use a proxy for the page traffic
  /// </summary>
  /// <returns>True if we want to use the proxy</returns>
  public static boolean getUseProxy() {
    return Config.getValueForSection(
        ConfigSection.PLAYWRIGHT_MAQS.toString(), "UseProxy", "NO").equals("Yes");
  }

  /// <summary>
  /// Get the proxy address to use
  /// </summary>
  /// <returns>The proxy address</returns>
  public static String getProxyAddress() {
    return Config.getValueForSection(ConfigSection.PLAYWRIGHT_MAQS.toString(), "ProxyAddress");
  }

  /// <summary>
  /// Get the window size as a string
  /// </summary>
  /// <param name="size">The size in a #X# format</param>
  /// <param name="width">The browser width</param>
  /// <param name="height">The browser height</param>
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
