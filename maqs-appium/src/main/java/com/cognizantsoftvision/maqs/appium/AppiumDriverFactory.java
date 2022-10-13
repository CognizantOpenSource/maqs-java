/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.appium;

import com.cognizantsoftvision.maqs.appium.constants.PlatformType;
import com.cognizantsoftvision.maqs.utilities.helper.StringProcessor;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.mac.options.Mac2Options;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.windows.options.WindowsOptions;
import io.appium.java_client.windows.WindowsDriver;
import java.net.URL;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * The Appium Driver Factory class.
 */
public class AppiumDriverFactory {

  /**
   * Instantiates a new Appium driver factory.
   */
  private AppiumDriverFactory() {
  }

  /**
   * Gets default mobile driver.
   *
   * @return the default mobile driver
   */
  public static AppiumDriver getDefaultMobileDriver() {
    return getDefaultMobileDriver(AppiumConfig.getDeviceType());
  }

  /**
   * Gets default mobile driver.
   *
   * @param deviceType the device type
   * @return the default mobile driver
   */
  public static AppiumDriver getDefaultMobileDriver(PlatformType deviceType) {
    AppiumDriver appiumDriver;
    URL mobileHubUrl = AppiumConfig.getMobileHubUrl();
    Duration duration = AppiumConfig.getCommandTimeout();

    switch (deviceType) {
      case ANDROID:
        appiumDriver = getAndroidDriver(mobileHubUrl, getDefaultUIAutomator2Options(), duration);
        break;
      case IOS:
        appiumDriver = getIosDriver(mobileHubUrl, getDefaultMac2Options(), duration);
        break;
      case WINDOWS:
        appiumDriver = getWindowsDriver(mobileHubUrl, getDefaultWindowsOptions(), duration);
        break;
      default:
        throw new IllegalStateException(
            StringProcessor.safeFormatter("Mobile OS type '%s' is not supported " + deviceType));
    }

    if (deviceType != PlatformType.WINDOWS) {
      appiumDriver.manage().timeouts().implicitlyWait(
          Duration.ofMillis(AppiumConfig.getMobileTimeout().toMillis()));
    }

    return appiumDriver;
  }

  /**
   * Gets default mobile options.
   *
   * @return the default mobile options
   */
  public static DesiredCapabilities getDefaultMobileOptions() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, AppiumConfig.getDeviceName());
    capabilities.setCapability(CapabilityType.PLATFORM_NAME, AppiumConfig.getPlatformName());
    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, AppiumConfig.getPlatformVersion());
    return mergeCapabilities(capabilities, AppiumConfig.getCapabilitiesAsObjects());
  }

  /**
   * Gets default mobile options.
   *
   * @param capabilities the capabilities
   * @return the default mobile options
   */
  public static DesiredCapabilities getDefaultMobileOptions(Map<String, Object> capabilities) {
    return new DesiredCapabilities(capabilities);
  }

  public static WindowsOptions getDefaultWindowsOptions() {
    WindowsOptions options = new WindowsOptions();
    options.setCapability(MobileCapabilityType.DEVICE_NAME, AppiumConfig.getDeviceName());
    options.setPlatformName(AppiumConfig.getPlatformName());
    options.setPlatformVersion(AppiumConfig.getPlatformVersion());
    return options;
  }

  public static Mac2Options getDefaultMac2Options() {
    Mac2Options options = new Mac2Options();
    options.setCapability(MobileCapabilityType.DEVICE_NAME, AppiumConfig.getDeviceName());
    options.setPlatformName(AppiumConfig.getPlatformName());
    options.setPlatformVersion(AppiumConfig.getPlatformVersion());
    return options;
  }

  public static UiAutomator2Options getDefaultUIAutomator2Options() {
    UiAutomator2Options options = new UiAutomator2Options();
    options.setDeviceName(AppiumConfig.getDeviceName());
    return options;
  }

  /**
   * Gets android driver.
   *
   * @param mobileHub the mobile hub
   * @param options   the options
   * @param timeout   the timeout
   * @return the android driver
   */
  public static AppiumDriver getAndroidDriver(URL mobileHub,UiAutomator2Options options, Duration timeout) {
    return createDriver(() -> {
      AppiumDriver driver = new AndroidDriver(mobileHub, options);
      driver.manage().timeouts().implicitlyWait(Duration.ofMillis(timeout.toMillis()));
      return driver;
    });
  }

  /**
   * Gets ios driver.
   *
   * @param mobileHub the mobile hub
   * @param options   the options
   * @param timeout   the timeout
   * @return the ios driver
   */
  public static AppiumDriver getIosDriver(URL mobileHub, Mac2Options options, Duration timeout) {
    return createDriver(() -> {
      AppiumDriver driver = new IOSDriver(mobileHub, options);
      driver.manage().timeouts().implicitlyWait(Duration.ofMillis(timeout.toMillis()));
      return driver;
    });
  }

  /**
   * Gets Windows driver.
   *
   * @param mobileHub the mobile hub
   * @param options   the options
   * @param timeout   the timeout
   * @return the Windows driver
   */
  public static AppiumDriver getWindowsDriver(URL mobileHub, WindowsOptions options,
      Duration timeout) {
    return createDriver(() -> {
      AppiumDriver driver = new WindowsDriver(mobileHub, options);
      driver.manage().timeouts().implicitlyWait(Duration.ofMillis(timeout.toMillis()));
      return driver;
    });

  }

  /**
   * Merge capabilities desired capabilities.
   *
   * @param capabilities          original capabilities object
   * @param capabilitiesAsObjects Map of String, Object
   * @return merged capabilities object
   */
  public static DesiredCapabilities mergeCapabilities(DesiredCapabilities capabilities,
      Map<String, Object> capabilitiesAsObjects) {
    Consumer<String> mergeConsumer = (String s) -> capabilities.setCapability(s, capabilitiesAsObjects.get(s));
    capabilitiesAsObjects.keySet().iterator().forEachRemaining(mergeConsumer);
    return capabilities;
  }

  /**
   * Create driver appium driver.
   *
   * @param createFunction the create function
   * @return the appium driver
   */
  public static AppiumDriver createDriver(Supplier<AppiumDriver> createFunction) {
    AppiumDriver appiumDriver = null;

    try {
      appiumDriver = createFunction.get();
      return appiumDriver;
    } catch (Exception e) {
      if (e.getClass().isInstance(IllegalArgumentException.class)) {
        throw e;
      } else {
        try {
          Optional<AppiumDriver> driverOptional = Optional.ofNullable(appiumDriver);
          driverOptional.ifPresent(AppiumDriver::quit);
        } catch (Exception quitException) {
          throw new WebDriverException(
              "Appium driver setup and teardown failed. Your driver may be out of date",
              quitException);
        }
      }

      throw new WebDriverException("Your driver may be out of date or unsupported.", e);
    }
  }
}
