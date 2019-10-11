/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.appium;

import io.appium.java_client.AppiumDriver;
import java.util.Map;
import java.util.function.Consumer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AppiumDriverFactoryTest {

  private static final String MESSAGE = "Test method not implemented yet.";

  @Test
  public void testGetDefaultMobileDriver() {
    AppiumDriver<WebElement> defaultMobileDriver = AppiumDriverFactory.getDefaultMobileDriver();
    Assert.assertNotNull(defaultMobileDriver, "Checking if default driver is null");
  }

  @Test
  public void testTestGetDefaultMobileDriver() {

    throw new UnsupportedOperationException(MESSAGE);
  }

  @Test
  public void testGetDefaultMobileOptions() {
    final DesiredCapabilities defaultMobileOptions = AppiumDriverFactory.getDefaultMobileOptions();
    Consumer<String> assertionConsumer = (String s) -> {
      Assert.assertNotNull(defaultMobileOptions.is(s),
          String.format("Checking if capability key %s is not null", s));
    };
    defaultMobileOptions.getCapabilityNames().forEach(assertionConsumer);
  }

  @Test
  public void testTestGetDefaultMobileOptions() {
    final Map<String, Object> capabilitiesAsObjects = AppiumConfig.getCapabilitiesAsObjects();
    DesiredCapabilities capabilities = AppiumDriverFactory
        .getDefaultMobileOptions(capabilitiesAsObjects);
    Consumer<String> assertionConsumer = (String s) -> {
      Assert.assertNotNull(capabilities.is(s),
          String.format("Checking if capability key %s is not null", s));
      Assert.assertEquals(capabilities.getCapability(s), capabilitiesAsObjects.get(s),
          String.format("Checking if capability value for key %s matches", s));
    };
    capabilities.getCapabilityNames().forEach(assertionConsumer);
  }

  @Test
  public void testGetAndroidDriver() {
    throw new UnsupportedOperationException(MESSAGE);
  }

  @Test
  public void testGetIOSDriver() {
    throw new UnsupportedOperationException(MESSAGE);
  }

  @Test
  public void testGetWindowsDriver() {
    throw new UnsupportedOperationException(MESSAGE);
  }

  @Test
  public void testCreateDriver() {
    throw new UnsupportedOperationException(MESSAGE);
  }
}