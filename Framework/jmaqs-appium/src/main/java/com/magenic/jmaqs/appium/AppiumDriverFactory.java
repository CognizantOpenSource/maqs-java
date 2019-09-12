package com.magenic.jmaqs.appium;

import io.appium.java_client.AppiumDriver;
import java.net.URI;
import java.time.Duration;
import java.util.Map;
import java.util.function.Function;
import org.openqa.selenium.WebElement;

public class AppiumDriverFactory {

  public static AppiumDriver<WebElement> getDefaultMobileDriver() {
    return getDefaultMobileDriver(AppiumConfig.getDeviceType());
  }

  public static AppiumDriver<WebElement> getDefaultMobileDriver(PlatformType deviceType) {

  }

  public static AppiumDriver<WebElement> createDriver(Function<> createFunction) {

  }

  public static AppiumDriver<WebElement> getAndroidDriver(URI mobileHub, AppiumOptions options,
      Duration timeout) {

  }

  public static AppiumDriver<WebElement> getIOSDriver(URI mobileHub, AppiumOptions options,
      Duration timeout) {

  }

  public static AppiumOptions getDefaultMobileOptions() {

  }

  public static AppiumOptions getDefaultMobileOptions(Map<String, Object> capabilities) {

  }

  public static AppiumDriver<WebElement> getWindowsDriver(URI mobileHub, AppiumOptions options,
      Duration timeout) {

  }
}
