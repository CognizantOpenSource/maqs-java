/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.appium;

import com.magenic.jmaqs.utilities.helper.Config;
import com.magenic.jmaqs.utilities.helper.ConfigSection;
import com.magenic.jmaqs.utilities.helper.StringProcessor;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Class AppiumConfig.
 */
public class AppiumConfig {

  /**
   * The appium configuration section.
   */
  private static final ConfigSection APPIUM_SECTION = ConfigSection.AppiumMaqs;

  /**
   * The appium capabilities configuration section.
   */
  private static final ConfigSection APPIUM_CAPS_SECTION = ConfigSection.AppiumCapsMaqs;

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
   * Gets the mobile device UDID.
   *
   * @return the mobile device UDID
   * @deprecated use flexible capabilities in config.xml instead.  Not longer needed.
   */
  @Deprecated
  public static String getMobileDeviceUdid() {
    return Config.getValueForSection(APPIUM_SECTION, "DeviceUDID");
  }

  /**
   * Gets the bundle ID.
   *
   * @return the bundle ID
   * @deprecated use flexible capabilities in config.xml instead.  Not longer needed.
   */
  @Deprecated
  public static String getBundleId() {
    return Config.getValueForSection(APPIUM_SECTION, "BundleID");
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
   * Is using mobile browser boolean.
   *
   * @return the boolean
   * @deprecated use flexible capabilities in config.xml instead.  Not longer needed.
   */
  @Deprecated
  public static boolean isUsingMobileBrowser() {
    String value = Config.getValueForSection(APPIUM_SECTION, "MobileBrowser", "NO");

    if (value.equalsIgnoreCase("YES")) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Gets save page source on fail.
   *
   * @return the save page source on fail
   */
  public static boolean getSavePageSourceOnFail() {
    return Config.getValueForSection(APPIUM_SECTION, "SavePageSourceOnFail")
        .equalsIgnoreCase("Yes");
  }

  /**
   * Gets soft assert screen shot.
   *
   * @return the soft assert screen shot
   */
  public static boolean getSoftAssertScreenShot() {
    return Config.getValueForSection(APPIUM_SECTION, "SoftAssertScreenShot")
        .equalsIgnoreCase("Yes");
  }

  /**
   * Gets the mobile hub url string.
   *
   * @return the mobile hub url string
   * @deprecated use AppiumConfig.getMobileHubUrl() instead.
   */
  @Deprecated
  public static String getMobileHubUrlString() {
    return Config.getValueForSection(APPIUM_SECTION, "MobileHubUrl");
  }

  /**
   * Gets the mobile hub url.
   *
   * @return the mobile hub url
   */
  public static URL getMobileHubUrl() {
    URL url = null;
    try {
      url = new URL(Config.getValueForSection(APPIUM_SECTION, "MobileHubUrl"));
    } catch (MalformedURLException e) {
      e.getStackTrace();
    }

    return url;
  }

  /**
   * Gets the mobile hub url.
   *
   * @param urlString the url string
   * @return the mobile hub url
   * @deprecated use flexible capabilities in config.xml instead.  Not longer needed.
   */
  @Deprecated
  public static URL getMobileHubUrl(String urlString) {
    URL url = null;
    try {
      url = new URL(urlString);
    } catch (MalformedURLException e) {
      e.getStackTrace();
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
    int timeoutValue = 0;
    try {
      timeoutValue = Integer.parseInt(value);
    } catch (NumberFormatException ex) {
      throw new NumberFormatException("MobileCommandTimeout in " + APPIUM_SECTION
          + " should be a number, but the current value is: " + value);
    }

    return Duration.ofMillis((long) timeoutValue);
  }

  /**
   * Gets mobile timeout.
   *
   * @return the mobile timeout
   */
  public static Duration getMobileTimeout() {
    return Duration.ofMillis(
        Integer.parseInt(Config.getValueForSection(APPIUM_SECTION, "MobileTimeout", "0")));
  }

  /**
   * Mobile device.
   *
   * @return the appium driver
   * @deprecated use {@link com.magenic.jmaqs.appium.AppiumDriverFactory} instead.
   */
  @Deprecated
  public static AppiumDriver mobileDevice() {
    return mobileDevice(getPlatformName());
  }

  /**
   * Mobile device.
   *
   * @param mobileDeviceOs the mobile device OS
   * @return the appium driver
   * @deprecated use {@link com.magenic.jmaqs.appium.AppiumDriverFactory} instead.
   */
  @Deprecated
  public static AppiumDriver mobileDevice(String mobileDeviceOs) {
    AppiumDriver appiumDriver = null;
    switch (mobileDeviceOs.toUpperCase()) {
      case "ANDROID":

        appiumDriver = new AndroidDriver(getMobileHubUrl(), getMobileCapabilities());
        break;

      case "IOS":

        appiumDriver = new IOSDriver(getMobileHubUrl(), getMobileCapabilities());
        break;

      default:

        throw new IllegalArgumentException(StringProcessor
            .safeFormatter("Remote browser type %s is not supported", mobileDeviceOs));

    }

    return appiumDriver;
  }

  /**
   * Gets the mobile capabilities.
   *
   * @return the mobile capabilities
   * @deprecated use flexible capabilities in config.xml instead.  Not longer needed.
   */
  @Deprecated
  private static DesiredCapabilities getMobileCapabilities() {

    DesiredCapabilities capabilities = null;

    String mobileDeviceOs = getPlatformName();
    capabilities = new DesiredCapabilities();

    switch (mobileDeviceOs.toUpperCase()) {
      case "ANDROID":

        capabilities.setCapability(CapabilityType.PLATFORM, Platform.ANDROID);
        capabilities.setCapability("appPackage", getBundleId());
        if (Config.getValue("AppActivity", null) != null) {
          capabilities.setCapability("appActivity", Config.getValue("AppActivity"));
        }
        break;

      case "IOS":

        capabilities.setCapability("bundleId", getBundleId());
        capabilities.setCapability("udid", getMobileDeviceUdid());
        break;

      default:
        throw new IllegalArgumentException(StringProcessor
            .safeFormatter("Mobile Device OS type %s is not supported", mobileDeviceOs));

    }

    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, getPlatformVersion());
    capabilities.setCapability("platformName", getPlatformName());

    capabilities.setCapability(CapabilityType.BROWSER_NAME, getDeviceName());
    capabilities.setCapability(CapabilityType.VERSION, getPlatformVersion());
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, getDeviceName());

    return capabilities;
  }

  /**
   * Sets the timeouts.
   *
   * @param driver the new timeouts
   * @deprecated use flexible capabilities in config.xml instead.  Not longer needed.
   */
  @Deprecated
  public static void setTimeouts(AppiumDriver driver) {
    int timeoutTime = Integer.parseInt(Config.getValue("Timeout", "0"));
    driver.manage().timeouts().pageLoadTimeout(timeoutTime, TimeUnit.MILLISECONDS);
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