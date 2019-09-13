/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import java.util.HashMap;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The WebDriverFactory test class
 */
public class WebDriverFactoryTest {

  @Test
  public void getDefaultBrowserTest() throws Exception {
    WebDriver driver = null;
    try {
      driver = WebDriverFactory.getDefaultBrowser();
      Assert.assertNotNull(driver);
    } finally {
      if (driver != null) {
        driver.quit();
      }
    }
  }

  @Test
  public void getDefaultChromeOptionsTest() {
    ChromeOptions options = WebDriverFactory.getDefaultChromeOptions();
    Assert.assertNotNull(options);
  }

  @Test
  public void getDefaultHeadlessChromeOptionsTest() {
    ChromeOptions options = WebDriverFactory.getDefaultHeadlessChromeOptions();
    Assert.assertNotNull(options);
  }

  @Test
  public void getDefaultInternetExplorerOptionsTest() {
    InternetExplorerOptions options = WebDriverFactory.getDefaultInternetExplorerOptions();
    Assert.assertNotNull(options);
  }

  @Test
  public void getDefaultFirefoxOptionsTest() {
    FirefoxOptions options = WebDriverFactory.getDefaultFirefoxOptions();
    Assert.assertNotNull(options);
  }

  @Test
  public void getDefaultEdgeOptionsTest() {
    EdgeOptions options = WebDriverFactory.getDefaultEdgeOptions();
    Assert.assertNotNull(options);
  }

  @Test
  public void getChromeDriverTest() {
    ChromeDriver driver = null;
    try {
      driver = (ChromeDriver) WebDriverFactory.getChromeDriver(new ChromeOptions());
      Assert.assertNotNull(driver);
    } finally {
      if (driver != null) {
        driver.quit();
      }
    }
  }

  @Test
  public void getHeadlessChromeDriverTest() {
    ChromeDriver driver = null;
    try {
      driver = (ChromeDriver) WebDriverFactory.getHeadlessChromeDriver(new ChromeOptions());
      Assert.assertNotNull(driver);
    } finally {
      if (driver != null) {
        driver.quit();
      }
    }
  }

  //@Ignore
  @Test
  public void getFirefoxDriverTest() {
    FirefoxDriver driver = null;
    try {
      driver = (FirefoxDriver) WebDriverFactory.getFirefoxDriver(new FirefoxOptions(), "MAXIMIZE");
      Assert.assertNotNull(driver);
    } finally {
      if (driver != null) {
        driver.quit();
      }
    }
  }

  @Test
  public void getInternetExplorerDriverTest() {
    InternetExplorerDriver driver = null;
    try {
      driver = (InternetExplorerDriver) WebDriverFactory
          .getInternetExplorerDriver(new InternetExplorerOptions(), "MAXIMIZE");
      Assert.assertNotNull(driver);
    } finally {
      if (driver != null) {
        driver.quit();
      }
    }
  }

  @Test
  public void getDefaultRemoteOptionsTest() {
    MutableCapabilities options = WebDriverFactory.getDefaultRemoteOptions();
    Assert.assertNotNull(options);
  }

  @Test
  public void setDriverOptionsTest() {
    MutableCapabilities options = new ChromeOptions();
    HashMap additionalCapabilities = new HashMap<String, Object>();
    additionalCapabilities.put("testKey", "testValue");
    options = WebDriverFactory.setDriverOptions(options, additionalCapabilities);

    Assert.assertNotNull(options);
    Assert.assertEquals(options.getCapability("testKey"), "testValue");
  }

  @Test
  public void setBrowserSizeTest() throws Exception {
    WebDriver driver = null;
    try {
      driver = WebDriverFactory.getDefaultBrowser();
      WebDriverFactory.setBrowserSize(driver, "1920x1080");
      Assert.assertEquals(driver.manage().window().getSize().width, 1920);
      Assert.assertEquals(driver.manage().window().getSize().height, 1080);
    } finally {
      if (driver != null) {
        driver.quit();
      }
    }
  }

  @Test
  public void getHeadlessWindowSizeStringMaximizeTest() {
    String size = WebDriverFactory.getHeadlessWindowSizeString("MAXIMIZE");
    Assert.assertEquals(size, "window-size=1920,1080");
  }

  @Test
  public void getHeadlessWindowSizeStringSpecificSizeTest() {
    String size = WebDriverFactory.getHeadlessWindowSizeString("123x456");
    Assert.assertEquals(size, "window-size=123,456");
  }

  @Test
  public void extractDimensionFromStringTest() {
    Dimension dimension = WebDriverFactory.extractDimensionFromString("123x456");
    Assert.assertEquals(dimension.width, 123);
    Assert.assertEquals(dimension.height, 456);
  }

  @Test
  public void getDriverLocationTest() {
    String driverLocation = WebDriverFactory.getDriverLocation("chromedriver.exe");
    Assert.assertFalse(driverLocation.isEmpty());
  }
}
