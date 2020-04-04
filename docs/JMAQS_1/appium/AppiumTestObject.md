# <img src="resources/maqslogo.ico" height="32" width="32"> Appium Test Object

## Overview
Takes care of the base test context data.

[GetAppiumDriver](#GetAppiumDriver)  
[SetAppiumDriver](#SetAppiumDriver)  
[GetAppiumManager](#GetAppiumManager)     

## GetAppiumDriver
Gets the web service driver
```java
AppiumDriver<WebElement> driver = this.AppiumManager.getAppiumDriver();
```

## SetAppiumDriver
Sets the web service driver
```java
appiumTestObject.setAppiumDriver(AppiumDriverFactory.getDefaultMobileDriver());
```

## GetAppiumManager
Gets the web service driver manager
```java
MoblieDriverManager mobileDriver = this.AppiumManager.getMobileDriver();
```
