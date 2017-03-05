// <copyright file="SeleniumConfigTests.java" company="Magenic">
//  Copyright 2016 Magenic, All rights Reserved
// </copyright>
// <summary>Test class for config files</summary>
package magenic.maqs.selenium.UnitTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import magenic.maqs.selenium.BaseSeleniumTest.SeleniumConfig;

public class SeleniumConfigTests {
	/**
	 * Browser check
	 */
	@Test
	public void getBrowser() {

		WebDriver driver = SeleniumConfig.browser();

		Assert.assertNotNull(driver);
		driver.quit();

	}

	/**
	 * Browser name
	 */
	@Test
	public void getBrowserName() {

		String driverName = SeleniumConfig.getBrowserName();

		Assert.assertTrue(driverName.equalsIgnoreCase("Chrome"));
	}

	/**
	 * Web site base
	 */
	@Test
	public void getWebsiteBase() {

		String website = SeleniumConfig.getWebSiteBase();

		Assert.assertTrue(website.equalsIgnoreCase("http://magenicautomation.azurewebsites.net/"));
	}

	/**
	 * Driver hint path
	 */
	@Test
	public void getDriverHintPath() {

		String path = SeleniumConfig.getDriverHintPath();

		Assert.assertEquals(path, new java.io.File("Resources").getAbsolutePath());
	}

	/**
	 * Remote browser name test
	 */
	@Test
	public void getRemoteBrowserName() {

		String browser = SeleniumConfig.getRemoteBrowserName();

		Assert.assertEquals(browser, "Chrome");
	}

	/**
	 * Remote platform test
	 */
	@Test
	public void getRemotePlatform() {

		String platform = SeleniumConfig.getRemotePlatform();

		Assert.assertEquals(platform, "");
	}

	/**
	 * Remote browser version
	 */
	@Test
	public void getRemoteBrowserVersion() {

		String version = SeleniumConfig.getRemoteBrowserVersion();

		Assert.assertEquals(version, "");
	}

	/**
	 * Browser with string
	 */
	@Test
	public void GetBrowserWithString() {

		WebDriver driver = SeleniumConfig.browser("chrome");

		Assert.assertNotNull(driver);
		driver.quit();
	}

}
