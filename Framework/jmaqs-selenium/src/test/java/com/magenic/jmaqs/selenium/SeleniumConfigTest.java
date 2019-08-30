/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.selenium.SeleniumConfig;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Selenium configuration tests.
 */
public class SeleniumConfigTest {
  /**
   * Browser check.
   */
  @Test
  public void getBrowser() throws Exception {

    WebDriver driver = SeleniumConfig.browser();

    Assert.assertNotNull(driver);
    driver.quit();

  }

  /**
   * Browser name.
   */
  @Test
  public void getBrowserName() {

    String driverName = SeleniumConfig.getBrowserName();

    Assert.assertTrue(driverName.equalsIgnoreCase("HEADLESSCHROME"));
  }

  /**
   * Web site base.
   */
  @Test
  public void getWebsiteBase() {

    String website = SeleniumConfig.getWebSiteBase();

    Assert.assertTrue(website.equalsIgnoreCase("http://magenicautomation.azurewebsites.net/"));
  }

  /**
   * Hub Url.
   */
  @Test
  public void getHubUrl() {

    String hubUrl = SeleniumConfig.getHubUrl();

    Assert.assertTrue(hubUrl.equalsIgnoreCase("http://ondemand.saucelabs.com:80/wd/hub"));
  }

  /**
   * Command timeout.
   */
  @Test
  public void getCommandTimeout() {

    int timeout = SeleniumConfig.getCommandTimeout();

    Assert.assertNotNull(timeout);
  }

  /**
   * Driver hint path.
   */
  @Test
  public void getDriverHintPath() {

    String path = SeleniumConfig.getDriverHintPath();

    Assert.assertEquals(path, new java.io.File("Resources").getAbsolutePath());
  }

  /**
   * Remote browser name test.
   */
  @Test
  public void getRemoteBrowserName() {

    String browser = SeleniumConfig.getRemoteBrowserName();

    Assert.assertEquals(browser, "Chrome");
  }

  /**
   * Remote browser check.
   */
  @Test
  public void getRemoteBrowser() throws Exception {

    WebDriver driver = SeleniumConfig.getRemoteBrowser();

    Assert.assertNotNull(driver);
    driver.quit();

  }

  /**
   * Remote browser with string.
   */
  @Test
  public void getRemoteBrowserWithString() throws Exception {

    WebDriver driver = SeleniumConfig.getRemoteBrowser("HEADLESSCHROME");

    Assert.assertNotNull(driver);
    driver.quit();
  }

  /**
   * Remote platform test.
   */
  @Test
  public void getRemotePlatform() {

    String platform = SeleniumConfig.getRemotePlatform();

    Assert.assertEquals(platform, "");
  }

  /**
   * Remote browser version.
   */
  @Test
  public void getRemoteBrowserVersion() {

    String version = SeleniumConfig.getRemoteBrowserVersion();

    Assert.assertEquals(version, "");
  }

  /**
   * Browser with string.
   */
  @Test
  public void getBrowserWithString() throws Exception {

    WebDriver driver = SeleniumConfig.browser("HEADLESSCHROME");

    Assert.assertNotNull(driver);
    driver.quit();
  }

}
