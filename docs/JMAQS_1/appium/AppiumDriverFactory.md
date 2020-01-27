# <img src="resources/maqslogo.ico" height="32" width="32"> AppiumDriverFactory
The Appium Driver Factory class is a utility class for working with Selenium.

## Inheritance Hierarchy
```java
com.magenic.jmaqs.appium.AppiumDriverFactory
```
Package: com.magenic.jmaqs.appium;
Assembly: import com.magenic.jmaqs.appium.AppiumDriverFactory

## Syntax
Java
```java
public class AppiumDriverFactory
```

The AppiumDriverFactory type exposes the following members.

## Constructors
### MobileDriverManager
Initializes a new instance of the MobileDriverManager class
#### Written as
```java
private AppiumDriverFactory()
```

## Methods
[GetDefaultMobileDriver](#GetDefaultMobileDriver) - Gets the default mobile driver  
[GetDefaultMobileOptions](#GetDefaultMobileOptions) - Gets the default mobile Options  
[GetAndroidDriver](#GetAndroidDriver) - Get the android driver  
[GetIosDriver](#GetIosDriver) - get the IOS driver  
[GetWindowsDriver](#GetWindowsDriver) - Get the windows driver  
[MergeCapabilities](#MergeCapabilities) - Merge the capabilities    
[CreateDriver](#CreateDriver) - creates a new driver

##  GetDefaultMobileDriver
Gets the default mobile driver.
#### Written as
```java
AppiumDriver<WebElement> getDefaultMobileDriver()
```
#### Example
```java
AppiumDriver<WebElement> defaultMobileDriver = AppiumDriverFactory.getDefaultMobileDriver();
```

##  GetDefaultMobileOptions
Gets default mobile driver.
#### Written as
```java
AppiumDriver<WebElement> getDefaultMobileDriver(PlatformType deviceType)
```
##### Example
```java
final DesiredCapabilities defaultMobileOptions = AppiumDriverFactory.getDefaultMobileOptions();
```

## GetAndroidDriver
Gets the android driver.
#### Written as
```java
AppiumDriver<WebElement> getAndroidDriver(URL mobileHub, DesiredCapabilities options, Duration timeout)
```
#### Example:
```java
AppiumDriver<WebElement> androidDriver = AppiumDriverFactory.getAndroidDriver(
AppiumConfig.getMobileHubUrl(), capabilities, AppiumConfig.getMobileTimeout());
```

## GetIosDriver
Gets the ios driver.
#### Written as
```java
AppiumDriver<WebElement> getIosDriver(URL mobileHub, DesiredCapabilities options, Duration timeout)
```
#### Example
```java
AppiumDriver<WebElement> iosDriver = AppiumDriverFactory.getIosDriver(
AppiumConfig.getMobileHubUrl(), capabilities,AppiumConfig.getMobileTimeout());
```

## GetWindowsDriver
Gets the windows driver.
#### Written as
```java
AppiumDriver<WebElement> getWindowsDriver(URL mobileHub, DesiredCapabilities options, Duration timeout)
```
#### Written as
```java
AppiumDriver<WebElement> windowsDriver = AppiumDriverFactory.getWindowsDriver(
new URL("http://127.0.0.1:4723"), appCapabilities,AppiumConfig.getMobileTimeout());
```

##  MergeCapabilities
Merge the desired capabilities into one object.
#### Written as
```java
DesiredCapabilities mergeCapabilities(DesiredCapabilities capabilities, Map<String, Object> capabilitiesAsObjects)
```
#### Example
```java
DesiredCapabilities capabilities = AppiumDriverFactory.mergeCapabilities(capabilities, sauceLabsConfig.asMap());
```

##  CreateDriver
Create an appium driver.
#### Written as
```java
AppiumDriver<WebElement> createDriver(Supplier<AppiumDriver<WebElement>> createFunction)
```
#### Example
```java
AppiumDriver<WebElement> appiumDriver = AppiumDriverFactory.createDriver(appiumDriverSupplier);
```
