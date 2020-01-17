# <img src="resources/maqslogo.ico" height="32" width="32"> WebDriverFactory
Factory class that is used for Web driver configuration.

## Inheritance Hierarchy
```java
com.magenic.jmaqs.selenium.WebDriverFactory
```
Package: com.magenic.jmaqs.selenium;
<br> Assembly: import com.magenic.jmaqs.selenium.WebDriverFactory;

## Syntax
Java
```java
public class WebDriverFactory
```
The UIWait type exposes the following members.

## Constructors
Instantiates a new UIWait object.
#### Written as
```java
private WebDriverFactory() 
```

# Methods

## GetDefaultBrowser
Get the default web driver based on the test run configuration.
#### Written as
```java
WebDriver getDefaultBrowser()
```
#### Example
```java
driver = WebDriverFactory.getDefaultBrowser();
```

## GetBrowserWithDefaultConfiguration
Get the default web driver (for the specified browser type) based on the test run configuration.
#### Written as
```java
WebDriver getBrowserWithDefaultConfiguration(BrowserType browser) 
```
#### Example
```java
return getBrowserWithDefaultConfiguration(SeleniumConfig.getBrowserType());
```

## GetDefaultChromeOptions
Get the default Chrome options.
#### Written as
```java
ChromeOptions getDefaultChromeOptions()
```
#### Example
```java
webDriver = getChromeDriver(getDefaultChromeOptions(), size);
```

#### GetDefaultHeadlessChromeOptions
Get the default headless Chrome options.
#### Written as
```java
ChromeOptions getDefaultHeadlessChromeOptions()
```
#### Example
```java
ChromeOptions options = WebDriverFactory.getDefaultHeadlessChromeOptions();
```

#### GetDefaultHeadlessChromeOptions
Get the default headless Chrome options.
#### Written as
```java
ChromeOptions getDefaultHeadlessChromeOptions()
```
#### Example
```java
ChromeOptions options = WebDriverFactory.getDefaultHeadlessChromeOptions();
```

## GetDefaultInternetExplorerOptions
Get the default IE options.
#### Written as
```java
InternetExplorerOptions getDefaultInternetExplorerOptions()
```
#### Example
```java
webDriver = getInternetExplorerDriver(getDefaultInternetExplorerOptions(), size);
```

## GetDefaultFirefoxOptions
Get the default Firefox options.
#### Written as
```java
FirefoxOptions getDefaultFirefoxOptions()
```
#### Example
```java
webDriver = getFirefoxDriver(getDefaultFirefoxOptions(), size);
```

## GetDefaultEdgeOptions
Get the default Edge options.
#### Written as
```java
EdgeOptions getDefaultEdgeOptions()
```
#### Example
```java
webDriver = getEdgeDriver(getDefaultEdgeOptions(), size);
```

## GetChromeDriver
Initialize a new Chrome driver.
#### Written as
```java
WebDriver getChromeDriver(ChromeOptions chromeOptions, String size)
```
#### Example
```java
webDriver = getChromeDriver(getDefaultChromeOptions(), size);
```

## GetHeadlessChromeDriver
Initialize a new headless Chrome driver.
#### Written as
```java
WebDriver getHeadlessChromeDriver(ChromeOptions headlessChromeOptions) 
```
#### Example
```java
webDriver = getHeadlessChromeDriver(getDefaultHeadlessChromeOptions(size));
```

## GetFirefoxDriver
Initialize a new Firefox driver.
#### Written as
```java
WebDriver getFirefoxDriver(FirefoxOptions firefoxOptions, String size)
```
#### Example
```java
webDriver = getFirefoxDriver(getDefaultFirefoxOptions(), size);
```

## GetEdgeDriver
Initialize a new Edge driver.
#### Written as
```java
WebDriver getEdgeDriver(EdgeOptions edgeOptions, String size)
```
#### Example
```java
webDriver = getEdgeDriver(getDefaultEdgeOptions(), size);
```

## GetInternetExplorerDriver
Get a new IE driver.
#### Written as
```java
WebDriver getInternetExplorerDriver(InternetExplorerOptions internetExplorerOptions, String size)
```
#### Example
```java
webDriver = getInternetExplorerDriver(getDefaultInternetExplorerOptions(), size);
```

## GetDefaultRemoteOptions
Get the default remote driver options.
<br> Default values are pulled from the configuration.
#### Written as
```java
MutableCapabilities getDefaultRemoteOptions()
```
#### Example
```java
MutableCapabilities options = WebDriverFactory.getDefaultRemoteOptions();
```

## GetRemoteOptions
Get the remote driver options.
#### Written as
```java
MutableCapabilities getRemoteOptions(RemoteBrowserType remoteBrowser)
```
#### Example
```java
MutableCapabilities options = WebDriverFactory.getRemoteOptions(RemoteBrowserType.IE);
```

## setDriverOptions
Add additional capabilities to the driver options.
#### Written as
```java
void setDriverOptions(MutableCapabilities driverOptions, Map<String, Object> additionalCapabilities)
```
#### Example
```java
HashMap additionalCapabilities = new HashMap<String, Object>();
additionalCapabilities.put("testKey", "testValue");
WebDriverFactory.setDriverOptions(options, additionalCapabilities);
```

## SetBrowserSize
Sets the browser size based on the provide string value. 
<br>Browser size is expected to be: MAXIMIZE, DEFAULT, or #x# (such as 1920x1080).
<br>MAXIMIZE just maximizes the browser.
<br>DEFAULT does not change the current size.
#### Written as
```java
void setBrowserSize(WebDriver webDriver, String size)
```
#### Example
```java
WebDriverFactory.setBrowserSize(driver, "1920x1080");
WebDriverFactory.setBrowserSize(driver, "MAXIMIZE");
```

## GetHeadlessWindowSizeString
Get the browser/browser size as a string.
#### Written as
```java
String getHeadlessWindowSizeString(String size)
```
#### Example
```java
String size = WebDriverFactory.getHeadlessWindowSizeString("MAXIMIZE");
```

## ExtractDimensionFromString
Get the window size as a Dimension object.
#### Written as
```java
Dimension extractDimensionFromString(String size) 
```
#### Example
```java
Dimension dimension = WebDriverFactory.extractDimensionFromString("123x456");
```

## GetDriverLocation
Get the web driver location.
#### Written as
```java
String getDriverLocation(String driverFile)
```
#### Example
```java
String driverLocation = WebDriverFactory.getDriverLocation(WebDriverFile.CHROME.getFile());
```

## GetWindowsEdgeDriverLocation
Get the file path for the Edge driver pre-installed on the system.
#### Written as
```java
String getWindowsEdgeDriverLocation(String file) 
```
#### Example
```java
String driverLocation = WebDriverFactory.getWindowsEdgeDriverLocation("testFile");
```