/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.appium;

import com.cognizantsoftvision.maqs.appium.constants.PlatformType;
import com.cognizantsoftvision.maqs.base.BaseGenericTest;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.MobileCapabilityType;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

/**
 * The Appium Driver Factory unit test class.
 */
public class AppiumDriverFactoryUnitTest extends BaseGenericTest {

  private static Map<String, Object> appiumCapsMaqs;

  /**
   * The Sauce labs config.
   */
  private static DesiredCapabilities sauceLabsConfig;

  /**
   * Sets up.
   */
  @BeforeClass
  public void setUp() {
    Map<String, Object> capabilitiesAsObjects = AppiumConfig.getCapabilitiesAsObjects();
    appiumCapsMaqs = capabilitiesAsObjects;

    // The Sauce labs config.
    DesiredCapabilities sauceLabsConfig = new DesiredCapabilities();
    sauceLabsConfig.setCapability("username", capabilitiesAsObjects.get("username"));
    sauceLabsConfig.setCapability("accessKey", capabilitiesAsObjects.get("accessKey"));
    sauceLabsConfig.setCapability("deviceOrientation", "portrait");
  }

  /**
   * Test get default mobile driver.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetDefaultMobileDriver() {
    AppiumDriver defaultMobileDriver = AppiumDriverFactory.getDefaultMobileDriver();
    Assert.assertNotNull(defaultMobileDriver, "Checking if default driver is null");
  }

  /**
   * Test test get default mobile driver android.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testTestGetDefaultMobileDriverAndroid() {
    AppiumDriver defaultMobileDriver = AppiumDriverFactory.getDefaultMobileDriver(PlatformType.ANDROID);
    Assert.assertNotNull(defaultMobileDriver, "Checking if default driver is null");
  }

  /**
   * Test get default mobile options.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetDefaultMobileOptions() {
    final DesiredCapabilities defaultMobileOptions = AppiumDriverFactory.getDefaultMobileOptions();
    // Consumer is used by the iterator for bulk processing and verification of the keys in the Map.
    // More elegant solution oppose to a for each.
    Consumer<String> assertionConsumer = defaultMobileOptions::is;
    defaultMobileOptions.getCapabilityNames().forEach(assertionConsumer);
  }

  /**
   * Test test get default mobile options.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testTestGetDefaultMobileOptions() {
    final Map<String, Object> capabilitiesAsObjects = AppiumConfig.getCapabilitiesAsObjects();
    DesiredCapabilities capabilities = AppiumDriverFactory.getDefaultMobileOptions(capabilitiesAsObjects);
    // Consumer is used by the iterator for bulk processing and verification of the
    // keys in the Map.
    // More elegant solution oppose to a for each.
    Consumer<String> assertionConsumer = (String s) -> {
      capabilities.is(s);
      Assert.assertEquals(capabilities.getCapability(s), capabilitiesAsObjects.get(s),
          String.format("Checking if capability value for key %s matches", s));
    };
    capabilities.getCapabilityNames().forEach(assertionConsumer);
  }

  /**
   * Test get android driver.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetAndroidDriver() {
    UiAutomator2Options options = new UiAutomator2Options();
    options.setPlatformName("Android");
    options.setPlatformVersion("6.0");
    options.setDeviceName("Android GoogleAPI Emulator");
    options.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

    for(Map.Entry<String, Object> entry:  appiumCapsMaqs.entrySet()) {
      options.setCapability(entry.getKey(), entry.getValue());
    }

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
    capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0");
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android GoogleAPI Emulator");
    capabilities = AppiumDriverFactory.mergeCapabilities(capabilities, sauceLabsConfig.asMap());
    AppiumDriver androidDriver = AppiumDriverFactory.getAndroidDriver(AppiumConfig.getMobileHubUrl(),
       capabilities, AppiumConfig.getMobileTimeout());
    Assert.assertNotNull(androidDriver, "Checking if android driver is null");
  }

  /**
   * Test get ios driver.
   */
  @Test(groups = TestCategories.APPIUM)
  public void testGetIOSDriver() {
//    Mac2Options options = new Mac2Options();
//    options.setPlatformName("iOS");
//    options.setPlatformVersion("12.2");
//    options.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone X Simulator");
//    options.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
//
//    for(Map.Entry<String, Object> entry:  appiumCapsMaqs.entrySet()) {
//      options.setCapability(entry.getKey(), entry.getValue());
//    }

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
    capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.2");
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone X Simulator");
    capabilities = AppiumDriverFactory.mergeCapabilities(capabilities, sauceLabsConfig.asMap());
    AppiumDriver iosDriver = AppiumDriverFactory.getIosDriver(AppiumConfig.getMobileHubUrl(),
        capabilities, AppiumConfig.getMobileTimeout());
    Assert.assertNotNull(iosDriver, "Checking if ios driver is null");
  }

  /**
   * Test get Windows driver.
   */
  @Test(groups = TestCategories.APPIUM)
  @Ignore("Work on Windows implementation")
  public void testGetWindowsDriver() {
//    WindowsOptions options = new WindowsOptions();
//    options.setApp("Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
//    options.setCapability(MobileCapabilityType.UDID, "0C0E26E7-966B-4C89-A765-32C5C997A456");

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
    capabilities.setCapability(MobileCapabilityType.UDID, "0C0E26E7-966B-4C89-A765-32C5C997A456");
    AppiumDriver windowsDriver = null;

    try {
      windowsDriver = AppiumDriverFactory.getWindowsDriver(new URL("http://127.0.0.1:4723"),
          capabilities, AppiumConfig.getMobileTimeout());
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    Assert.assertNotNull(windowsDriver, "Checking if windows app driver is null");
  }

  /**
   * Test create driver.
   */
  @Test(groups = TestCategories.APPIUM, expectedExceptions = WebDriverException.class)
  public void testCreateDriverException() {
    Supplier<AppiumDriver> appiumDriverSupplier = () -> {
      try {
        return new AppiumDriver(new URL("http://127.0.0.1:4723"),
            new DesiredCapabilities());
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
      return null;
    };
    AppiumDriverFactory.createDriver(appiumDriverSupplier);
  }
}