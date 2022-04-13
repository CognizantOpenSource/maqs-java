# <img src="resources/MAQS.jpg" height="32" width="32"> Appium Driver Factory

## Overview
Takes care of the Appium driver set up, teardown and optimization.

[GetDefaultMobileDriver](#GetDefaultMobileDriver)  
[GetDefaultMobileOptions](#GetDefaultMobileOptions)  
[GetAndroidDriver](#GetAndroidDriver)   
[GetIOSDriver](#GetIOSDriver)   
[GetWindowsDriver](#GetWindowsDriver)   
[MergeCapabilities](#MergeCapabilities)  
[CreateDriver](#CreateDriver)

## GetDefaultMobileDriver
Gets default mobile driver
```java
AppiumDriver<WebElement> defaultMobileDriver = AppiumDriverFactory.getDefaultMobileDriver();
```

## GetDefaultMobileOptions
Gets default mobile options
```java
DesiredCapabilities capabilities = getDefaultMobileOptions();

DesiredCapabilities capabilities = AppiumDriverFactory.getDefaultMobileOptions(capabilitiesAsObjects);
```

## GetAndroidDriver
Gets the android driver
```java
AppiumDriver<WebElement> appiumDriver = getAndroidDriver(mobileHubUrl, capabilities, duration);
```

## GetIOSDriver
Gets the IOS driver
```java
AppiumDriver<WebElement> appiumDriver = getIosDriver(mobileHubUrl, capabilities, duration);
```

## GetWindowsDriver
Gets the Windows driver
```java
Gets windows driver
AppiumDriver<WebElement> appiumDriver = getWindowsDriver(mobileHubUrl, capabilities, duration);
```

## MergeCapabilities
Merge capabilities desired capabilities
```java
DesiredCapabilities capabilities = AppiumDriverFactory.mergeCapabilities(capabilities, sauceLabsConfig.asMap());
```

## CreateDriver
Create an appium driver
```java
AppiumDriver<WebElement> = AppiumDriverFactory.createDriver(appiumDriverSupplier);
```