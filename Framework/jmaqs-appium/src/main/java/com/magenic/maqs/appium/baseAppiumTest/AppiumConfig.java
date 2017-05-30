/*
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.maqs.appium.baseAppiumTest;

import com.magenic.jmaqs.utilities.helper.Config;
import com.magenic.jmaqs.utilities.helper.StringProcessor;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Class AppiumConfig ...
 *
 * @author jasonedstrom Created on 5/30/17
 */
public class AppiumConfig {

  /**
   * Gets the mobile device OS.
   *
   * @return the mobile device OS
   */
  public static String getMobileDeviceOs() {
    return Config.getValue("MobileOSType", "Android");
  }

  /**
   * Gets the mobile device UDID.
   *
   * @return the mobile device UDID
   */
  public static String getMobileDeviceUdid() {
    return Config.getValue("DeviceUDID");
  }

  /**
   * Gets the bundle ID.
   *
   * @return the bundle ID
   */
  public static String getBundleId() {
    return Config.getValue("BundleID");
  }

  /**
   * Gets the OS version.
   *
   * @return the OS version
   */
  public static String getOsVersion() {
    return Config.getValue("OSVersion");
  }

  /**
   * Gets the device name.
   *
   * @return the device name
   */
  public static String getDeviceName() {
    return Config.getValue("DeviceName");
  }

  /**
   * Checks if is using mobile browser.
   *
   * @return true, if is using mobile browser
   */
  public static boolean isUsingMobileBrowser() {
    String value = Config.getValue("MobileBrowser", "NO");

    if (value.equalsIgnoreCase("YES")) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Gets the mobile hub url string.
   *
   * @return the mobile hub url string
   */
  public static String getMobileHubUrlString() {
    return Config.getValue("MobileHubUrl");
  }

  /**
   * Gets the mobile hub url.
   *
   * @return the mobile hub url
   */
  public static URL getMobileHubUrl() {
    return getMobileHubUrl(getMobileHubUrlString());
  }

  /**
   * Gets the mobile hub url.
   *
   * @param urlString
   *          the url string
   * @return the mobile hub url
   */
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
   * Mobile device.
   *
   * @return the appium driver
   */
  public static AppiumDriver mobileDevice() {
    return mobileDevice(getMobileDeviceOs());
  }

  /**
   * Mobile device.
   *
   * @param mobileDeviceOs
   *          the mobile device OS
   * @return the appium driver
   */
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
   */
  private static DesiredCapabilities getMobileCapabilities() {

    DesiredCapabilities capabilities = null;

    String mobileDeviceOs = getMobileDeviceOs();
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
    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, getOsVersion());
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, getMobileDeviceOs());

    capabilities.setCapability(CapabilityType.BROWSER_NAME, getDeviceName());
    capabilities.setCapability(CapabilityType.VERSION, getOsVersion());
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, getDeviceName());

    return capabilities;
  }

  /**
   * Sets the timeouts.
   *
   * @param driver
   *          the new timeouts
   */
  public static void setTimeouts(AppiumDriver driver) {
    int timeoutTime = Integer.parseInt(Config.getValue("Timeout", "0"));
    driver.manage().timeouts().pageLoadTimeout(timeoutTime, null);
  }
}
