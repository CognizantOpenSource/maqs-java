package com.magenic.jmaqs.appium;

import com.magenic.jmaqs.utilities.helper.StringProcessor;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.windows.WindowsDriver;
import java.net.URL;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * The type Appium driver factory.
 */
public class AppiumDriverFactory {

  /**
   * Instantiates a new Appium driver factory.
   */
  private AppiumDriverFactory() {
  }

  public static AppiumDriver<WebElement> getDefaultMobileDriver() {
    return getDefaultMobileDriver(AppiumConfig.getDeviceType());
  }

  public static AppiumDriver<WebElement> getDefaultMobileDriver(PlatformType deviceType) {
    AppiumDriver<WebElement> appiumDriver;
    URL mobileHubUrl = AppiumConfig.getMobileHubUrl();
    Duration duration = AppiumConfig.getCommandTimeout();
    DesiredCapabilities capabilities = getDefaultMobileOptions();

    switch (deviceType) {
      case ANDROID:
        appiumDriver = getAndroidDriver(mobileHubUrl, capabilities, duration);
        break;
      case IOS:
        appiumDriver = getIOSDriver(mobileHubUrl, capabilities, duration);
        break;
      case WINDOWS:
        appiumDriver = getWindowsDriver(mobileHubUrl, capabilities, duration);
        break;
      default:
        throw new IllegalStateException(
            StringProcessor.safeFormatter("Mobile OS type '%s' is not supported" + deviceType));
    }

    if (deviceType != PlatformType.WINDOWS) {
      AppiumConfig.setTimeouts(appiumDriver);
    }

    return appiumDriver;
  }

  public static AppiumDriver<WebElement> createDriver(
      Supplier<AppiumDriver<WebElement>> createFunction) {
    AppiumDriver<WebElement> appiumDriver = null;

    try {
      appiumDriver = createFunction.get();
    } catch (Exception e) {
      if (e.getClass().isInstance(IllegalArgumentException.class)) {
        throw e;
      } else {
        try {

          if (appiumDriver != null) {
            appiumDriver.quit();
          }

        } catch (Exception quitException) {
          throw new WebDriverException(
              "Appium driver setup and teardown failed. Your driver may be out of date",
              quitException);
        }
      }
    }
  }

  public static AppiumDriver<WebElement> getAndroidDriver(URL mobileHub,
      DesiredCapabilities options, Duration timeout) {

      return createDriver(() -> {
        AppiumDriver<WebElement> driver = new AndroidDriver<>(mobileHub, options);
        driver.manage().timeouts().implicitlyWait(timeout.toMillis(), TimeUnit.MILLISECONDS);
        return driver;
      });
  }

  public static AppiumDriver<WebElement> getIOSDriver(URL mobileHub, DesiredCapabilities options,
      Duration timeout) {
    return createDriver(() -> {
      AppiumDriver<WebElement> driver = new IOSDriver<>(mobileHub, options);
      driver.manage().timeouts().implicitlyWait(timeout.toMillis(), TimeUnit.MILLISECONDS);
      return driver;
    });
  }

  public static DesiredCapabilities getDefaultMobileOptions() {

  }

  public static DesiredCapabilities getDefaultMobileOptions(Map<String, Object> capabilities) {

  }

  public static AppiumDriver<WebElement> getWindowsDriver(URL mobileHub,
      DesiredCapabilities options, Duration timeout) {
    return createDriver(() -> {
      AppiumDriver<WebElement> driver = new WindowsDriver<>(mobileHub, options);
      driver.manage().timeouts().implicitlyWait(timeout.toMillis(), TimeUnit.MILLISECONDS);
      return driver;
    });

  }
}
