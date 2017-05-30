/*
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.maqs.appium.baseAppiumTest;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.magenic.jmaqs.utilities.helper.Config;
import com.magenic.jmaqs.utilities.helper.StringProcessor;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

// TODO: Auto-generated Javadoc
/**
 * Created by jasonedstrom on 2/21/17.
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
   * Mobile device.
   *
   * @return the appium driver
   */
  public static AppiumDriver mobileDevice() {
    return mobileDevice(getMobileDeviceOs());
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
    // TODO: close up
    String string = getMobileHubUrlString();
    return getMobileHubUrl(string);
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
   * @param mobileDeviceOS
   *          the mobile device OS
   * @return the appium driver
   */
  public static AppiumDriver mobileDevice(String mobileDeviceOS) {
    AppiumDriver appiumDriver = null;
    switch (mobileDeviceOS.toUpperCase()) {
      case "ANDROID":

        appiumDriver = new AndroidDriver(getMobileHubUrl(), getMobileCapabilities());
        break;

      case "IOS":

        appiumDriver = new IOSDriver(getMobileHubUrl(), getMobileCapabilities());
        break;

      default:

        throw new IllegalArgumentException(StringProcessor
            .safeFormatter("Remote browser type %s is not supported", mobileDeviceOS));

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

    String mobileDeviceOS = getMobileDeviceOs();
    capabilities = new DesiredCapabilities();

    switch (mobileDeviceOS.toUpperCase()) {
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
            .safeFormatter("Mobile Device OS type %s is not supported", mobileDeviceOS));

    }

    // TODO: Check capabilities
    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, getOsVersion());
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, getMobileDeviceOs());

    if ((Config.getValue("SauceLabs").toUpperCase()).equals("YES")) {

      capabilities.setCapability("appiumVersion", Config.getValue("AppiumVersion"));
      capabilities.setCapability("deviceName", getDeviceName());
      capabilities.setCapability("deviceOrientation", Config.getValue("Orientation"));
      capabilities.setCapability("browserName", "");
      capabilities.setCapability("platformName", getMobileDeviceOs());
      capabilities.setCapability("app", Config.getValue("AppLocation"));
      capabilities.setCapability("username", Config.getValue("SauceUsername"));
      capabilities.setCapability("accessKey", Config.getValue("SauceAccessKey"));

    } else {
      // capabilities.setCapability(CapabilityType.BROWSER_NAME, getDeviceName());
      capabilities.setCapability(CapabilityType.VERSION, getOsVersion());
      capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, getDeviceName());
    }

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
    driver.manage().timeouts().setScriptTimeout(timeoutTime, null);
    driver.manage().timeouts().pageLoadTimeout(timeoutTime, null);
  }
}
