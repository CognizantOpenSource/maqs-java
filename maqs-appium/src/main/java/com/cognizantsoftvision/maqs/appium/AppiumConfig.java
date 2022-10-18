/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.appium;

import com.cognizantsoftvision.maqs.appium.constants.PlatformType;
import com.cognizantsoftvision.maqs.appium.exceptions.AppiumConfigException;
import com.cognizantsoftvision.maqs.utilities.helper.Config;
import com.cognizantsoftvision.maqs.utilities.helper.ConfigSection;
import com.cognizantsoftvision.maqs.utilities.helper.StringProcessor;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * The Appium Config class.
 */
public class AppiumConfig {

  /**
   * The appium configuration section.
   */
  private static final ConfigSection APPIUM_SECTION = ConfigSection.APPIUM_MAQS;

  /**
   * The appium capabilities configuration section.
   */
  private static final ConfigSection APPIUM_CAPS_SECTION = ConfigSection.APPIUM_CAPS_MAQS;

  private AppiumConfig() {

  }

  /**
   * Gets the mobile device OS.
   *
   * @return the mobile device OS
   */
  public static String getPlatformName() {
    return Config.getValueForSection(APPIUM_SECTION, "PlatformName", "Android");
  }

  /**
   * Gets the OS version.
   *
   * @return the OS version
   */
  public static String getPlatformVersion() {
    return Config.getValueForSection(APPIUM_SECTION, "PlatformVersion");
  }

  /**
   * Gets the device name.
   *
   * @return the device name
   */
  public static String getDeviceName() {
    return Config.getValueForSection(APPIUM_SECTION, "DeviceName");
  }

  /**
   * Gets save page source on fail.
   *
   * @return the save page source on fail
   */
  public static boolean getSavePageSourceOnFail() {
    return Config.getValueForSection(APPIUM_SECTION, "SavePageSourceOnFail").equalsIgnoreCase("Yes");
  }

  /**
   * Gets soft assert screenshot.
   *
   * @return the soft assert screenshot
   */
  public static boolean getSoftAssertScreenShot() {
    return Config.getValueForSection(APPIUM_SECTION, "SoftAssertScreenShot").equalsIgnoreCase("Yes");
  }

  /**
   * Gets the mobile hub url string.
   *
   * @return the mobile hub url string
   */
  public static String getMobileHubUrlString() {
    return Config.getValueForSection(APPIUM_SECTION, "MobileHubUrl");
  }

  /**
   * Gets the mobile hub url.
   *
   * @return the mobile hub url
   */
  public static URL getMobileHubUrl() {
    URL url;
    try {
      url = new URL(getMobileHubUrlString());
    } catch (MalformedURLException e) {
      throw new AppiumConfigException(e);
    }
    return url;
  }

  /**
   * Gets command timeout.
   *
   * @return the command timeout
   */
  public static Duration getCommandTimeout() {
    String value = Config.getValueForSection(APPIUM_SECTION, "MobileCommandTimeout", "60000");
    long timeoutValue;
    try {
      timeoutValue = Integer.parseInt(value);
    } catch (NumberFormatException ex) {
      throw new NumberFormatException(
          "MobileCommandTimeout in " + APPIUM_SECTION + " should be a number, but the current value is: " + value);
    }

    return Duration.ofMillis(timeoutValue);
  }

  /**
   * Gets mobile timeout.
   *
   * @return the mobile timeout
   */
  public static Duration getMobileTimeout() {
    return Duration.ofMillis(Integer.parseInt(Config.getValueForSection(APPIUM_SECTION, "MobileTimeout", "0")));
  }

  /**
   * Gets capabilities as strings.
   *
   * @return the capabilities as strings
   */
  public static Map<String, String> getCapabilitiesAsStrings() {
    return Config.getSection(APPIUM_CAPS_SECTION);
  }

  /**
   * Gets capabilities as objects.
   *
   * @return the capabilities as objects
   */
  public static Map<String, Object> getCapabilitiesAsObjects() {
    return new HashMap<>(getCapabilitiesAsStrings());
  }

  /**
   * Gets device type.
   *
   * @return the device type
   */
  public static PlatformType getDeviceType() {
    return getDeviceType(getPlatformName());
  }

  /**
   * Gets device type.
   *
   * @param platformName the platform name
   * @return the device type
   */
  public static PlatformType getDeviceType(String platformName) {
    switch (platformName.toUpperCase().trim()) {
      case "ANDROID":
        return PlatformType.ANDROID;
      case "IOS":
        return PlatformType.IOS;
      case "WIN":
      case "WINDOWS":
        return PlatformType.WINDOWS;
      default:
        throw new IllegalArgumentException(
            StringProcessor.safeFormatter("Device type '{0}' is not supported", platformName));
    }
  }
}
