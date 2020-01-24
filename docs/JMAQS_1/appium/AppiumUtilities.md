# <img src="resources/maqslogo.ico" height="32" width="32"> AppiumUtilities
Class for the appium utilities

## Inheritance Hierarchy

```java
com.magenic.jmaqs.appium.AppiumUtilities
```
Package: com.magenic.jmaqs.appium;
Assembly: import com.magenic.jmaqs.appium.AppiumUtilities

## Syntax
Java
```java
public class AppiumUtilities
```

## Methods
[CaptureScreenshot](#CaptureScreenshot)  -  To capture a screenshot during execution  
[SavePageSource](#SavePageSource)        -  To capture a page source during execution  
[KillDriver](#KillDriver)                -  Kills the appium driver.  

## CaptureScreenshot
To capture a screenshot during execution.

#### Written as
```java
boolean captureScreenshot(AppiumDriver<WebElement> appiumDriver, AppiumTestObject testObject)
```
#### Example
```java
AppiumDriver<WebElement> appiumDriver = AppiumDriverFactory.getDefaultMobileDriver();
AppiumTestObject testObject = new AppiumTestObject(appiumDriver, fileLogger,
        this.getTestObject().getFullyQualifiedTestName());
boolean isSuccess = AppiumUtilities.captureScreenshot(appiumDriver, testObject);
```

## SavePageSource
To capture Page Source during execution.

#### Written as
```java
boolean savePageSource(AppiumDriver<WebElement> appiumDriver,AppiumTestObject testObject)
```
#### Example
```java
AppiumDriver<WebElement> appiumDriver = AppiumDriverFactory.getDefaultMobileDriver();
AppiumTestObject testObject = new AppiumTestObject(appiumDriver, fileLogger, 
            this.getTestObject().getFullyQualifiedTestName());
boolean isSuccess = AppiumUtilities.savePageSource(appiumDriver, testObject);
```

## KillDriver
Makes sure the driver is shut down.

#### Written as
```java
void killDriver(AppiumDriver<WebElement> appiumDriver)
```
#### Example:
```java
AppiumDriver<WebElement> appiumDriver = AppiumDriverFactory.getDefaultMobileDriver();
AppiumUtilities.killDriver(appiumDriver);
```