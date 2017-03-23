//--------------------------------------------------
// <copyright file="SeleniumConfig.java" company="Magenic">
//  Copyright 2016 Magenic, All rights Reserved
// </copyright>
// <summary>Helper class for getting selenium specific configuration values</summary>
//--------------------------------------------------

package com.magenic.jmaqs.selenium.BaseSeleniumTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import magenic.maqs.utilities.Helper.Config;
import magenic.maqs.utilities.Helper.StringProcessor;

/**
 * Config class
 */
public final class SeleniumConfig {

	/**
	 * Get the browser. If no browser is provide in the project configuration
	 * file we default to Chrome. Browser are maximized by default
	 * 
	 * @return The web driver
	 */
	public static WebDriver browser() {
		return browser(getBrowserName());
	}

	/**
	 * Get the browser type name - Example: Chrome
	 * 
	 * @return The browser type
	 */
	public static String getBrowserName() {
		return Config.getValue("Browser", "Chrome");
	}

	/**
	 * Get the web site base url
	 * 
	 * @return The web site base url
	 */
	public static String getWebSiteBase() {
		return Config.getValue("WebSiteBase");
	}

	/**
	 * Get the hint path for the web driver
	 * 
	 * @return The hint path for the web driver
	 */
	public static String getDriverHintPath() {
		String defaultPath = new java.io.File("Resources").getAbsolutePath();
		return Config.getValue("WebDriverHintPath", defaultPath);
	}

	/**
	 * Get the remote browser type name
	 * 
	 * @return The browser type being used on grid
	 */
	public static String getRemoteBrowserName() {
		return Config.getValue("RemoteBrowser", "Chrome");
	}

	/**
	 * Get the remote platform type name
	 * 
	 * @return The platform (or OS) to run remote tests against
	 */
	public static String getRemotePlatform() {
		return Config.getValue("RemotePlatform");
	}

	/**
	 * Get the remote browser version
	 * 
	 * @return The browser version to run against on grid
	 */
	public static String getRemoteBrowserVersion() {
		return Config.getValue("RemoteBrowserVersion");
	}

	/**
	 * Get the webdriver based for the provided browser, Browser are maximized
	 * by default
	 * 
	 * @param browser
	 *            The browser type we want to use
	 * @return A WebDriver
	 */
	public static WebDriver browser(String browser) {
		WebDriver webDriver = null;

		switch (browser.toUpperCase()) {
		case "INTERNET EXPLORER":
		case "INTERNETEXPLORER":
		case "IE":
			System.setProperty("webdriver.ie.driver",
					getDriverLocation("IEDriverServer.exe") + File.separator + "IEDriverServer.exe");
			webDriver = new InternetExplorerDriver();
			break;
		case "FIREFOX":
			String firefoxPath = getDriverLocation("firefox.exe",
					getProgramFilesFolder("Mozilla Firefox", "firefox.exe"), false);

			if (firefoxPath == null || firefoxPath.isEmpty()) {
				// Firefox driver location not found by framework so let
				// Selenium try to find it
				webDriver = new FirefoxDriver();
			} else {

				FirefoxBinary binary = new FirefoxBinary(new File(firefoxPath, "firefox.exe"));
				FirefoxProfile profile = new FirefoxProfile();
				webDriver = new FirefoxDriver(binary, profile);

			}

			break;
		case "CHROME":
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("test-type");
			chromeOptions.addArguments("--disable-web-security");
			chromeOptions.addArguments("--allow-running-insecure-content");
			chromeOptions.addArguments("--disable-extensions");

			System.setProperty("webdriver.chrome.driver",
					getDriverLocation("chromedriver.exe") + File.separator + "chromedriver.exe");
			webDriver = new ChromeDriver(chromeOptions);
			break;
		case "EDGE":
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.setPageLoadStrategy("Normal");

			System.setProperty("webdriver.edge.driver",
					getDriverLocation("MicrosoftWebDriver.exe",
							getProgramFilesFolder("Microsoft Web Driver", "MicrosoftWebDriver.exe")) + File.separator
							+ "MicrosoftWebDriver.exe");
			webDriver = new EdgeDriver(edgeOptions);
			break;

		case "PHANTOMJS":

			System.setProperty("phantomjs.binary.path",
					getDriverLocation("phantomjs.exe") + File.separator + "phantomjs.exe");
			webDriver = new PhantomJSDriver();

			break;

		case "REMOTE":
			// MalformedURLException exception is thrown if no protocol is
			// specified, or an unknown protocol is found, or spec is null.
			try {
				webDriver = new RemoteWebDriver(new URL(Config.getValue("HubUrl")), getRemoteCapabilities());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			throw new RuntimeException(StringProcessor.safeFormatter("Browser type %s is not supported", browser));
		}

		// Maximize the browser and than return it
		webDriver.manage().window().maximize();
		return webDriver;
	}

	/**
	 * Set the script and page timeouts
	 * 
	 * @param driver
	 *            Brings in a WebDriver
	 */
	public static void setTimeouts(WebDriver driver) {
		int timeoutTime = Integer.parseInt(Config.getValue("Timeout", "0"));
		driver.manage().timeouts().setScriptTimeout(timeoutTime, null);
		driver.manage().timeouts().pageLoadTimeout(timeoutTime, null);
	}

	/**
	 * Get the remote desired capability
	 * 
	 * @return The remote desired capability
	 */

	private static DesiredCapabilities getRemoteCapabilities() {
		DesiredCapabilities capabilities = null;
		String remoteBrowser = getRemoteBrowserName();
		String remotePlatform = getRemotePlatform();
		String remoteBrowserVersion = getRemoteBrowserVersion();

		switch (remoteBrowser.toUpperCase()) {
		case "INTERNET EXPLORER":
		case "INTERNETEXPLORER":
		case "IE":
			capabilities = DesiredCapabilities.internetExplorer();
			break;
		case "FIREFOX":
			capabilities = DesiredCapabilities.firefox();
			break;
		case "CHROME":
			capabilities = DesiredCapabilities.chrome();
			break;
		case "EDGE":
			capabilities = DesiredCapabilities.edge();
			break;
		case "SAFARI":
			capabilities = DesiredCapabilities.safari();
			break;
		default:
			// changed from exception to illegal argument exception
			throw new IllegalArgumentException(
					StringProcessor.safeFormatter("Remote browser type %s is not supported", remoteBrowser));
		}

		// Add a platform setting if one was provided
		if (remotePlatform.length() > 0) {
			capabilities.setCapability("platform", remotePlatform);
		}

		// Add a remote browser setting if one was provided
		if (remoteBrowserVersion.length() > 0) {
			capabilities.setCapability("version", remoteBrowserVersion);
		}

		return capabilities;
	}

	/**
	 * Get the web driver location
	 * 
	 * @param driverFile
	 *            The web drive file, including extension
	 * @param defaultHintPath
	 *            The default location for the specific driver
	 * @param mustExist
	 *            Do we need to know where this drive is located, if this is
	 *            true and the file is not found an error will be thrown
	 * @return The path to the web driver
	 */
	private static String getDriverLocation(String driverFile, String defaultHintPath, boolean mustExist) {
		// Get the hint path from the app.config
		String hintPath = getDriverHintPath();

		// Try the hintpath first
		if (!(hintPath == null || hintPath.isEmpty()) && Files.exists(Paths.get(hintPath, driverFile))) {
			return hintPath;
		}

		// Try the default hint path next
		if (!(defaultHintPath == null || defaultHintPath.isEmpty())
				&& Files.exists(Paths.get(defaultHintPath, driverFile))) {
			return Paths.get(defaultHintPath).toString();
		}

		// get the root path of the project

		Path path = Paths.get(new File("").getAbsolutePath());
		String testLocation = path.getParent().toString();
		// Try the test dll location
		if (Files.exists(Paths.get(testLocation, driverFile))) {
			return testLocation;
		}

		// We didn't find the web driver so throw an error if we need to know
		// where it is
		if (mustExist) {
			throw new RuntimeException(StringProcessor.safeFormatter("Unable to find driver for '%s'", driverFile));
		}

		return "";
	}

	/**
	 * Get the web driver location
	 * 
	 * @param driverFile
	 *            The web drive file, including extension
	 * @return The overloaded method
	 */
	private static String getDriverLocation(String driverFile) {

		return getDriverLocation(driverFile, "", true);
	}

	/**
	 * Get the web driver location
	 * 
	 * @param driverFile
	 *            The web drive file, including extension
	 * @param mustExist
	 *            Do we need to know where this drive is located, if this is
	 *            true and the file is not found an error will be thrown
	 * 
	 * @return The overloaded method
	 */
	private static String getDriverLocation(String driverFile, boolean mustExist) {

		return getDriverLocation(driverFile, "", mustExist);
	}

	/**
	 * Get the web driver location
	 * 
	 * @param driverFile
	 *            The web drive file, including extension
	 * @param defaultHintPath
	 *            The default location for the specific driver
	 * 
	 * @return The overloaded method
	 */
	private static String getDriverLocation(String driverFile, String defaultHintPath) {

		return getDriverLocation(driverFile, defaultHintPath, true);
	}

	/**
	 * Get the programs file folder which contains given file
	 * 
	 * @param folderName
	 *            The programs file sub folder
	 * @param file
	 *            The file we are looking for
	 * @return The parent folder of the given file or the empty String if the
	 *         file is not found
	 */
	private static String getProgramFilesFolder(String folderName, String file) {
		// Handle 64 bit systems first
		boolean is64bit = false;
		if (System.getProperty("os.name").contains("Windows")) {
			is64bit = (System.getenv("ProgramFiles(x86)") != null);
		} else {
			is64bit = (System.getProperty("os.arch").indexOf("64") != -1);
		}

		if (is64bit) {
			Path path = Paths.get(System.getenv("ProgramW6432"), folderName, file);

			if (Files.isRegularFile(path)) {
				return path.getParent().toString();
			}

			path = Paths.get(System.getenv("ProgramFiles(x86)"), folderName, file);

			if (Files.isRegularFile(path)) {
				return path.getParent().toString();
			}

		} else {
			Path path = Paths.get(System.getenv("ProgramFiles"), folderName, file);

			if (Files.isRegularFile(path)) {
				return path.getParent().toString();
			}
		}

		return "";
	}

}
