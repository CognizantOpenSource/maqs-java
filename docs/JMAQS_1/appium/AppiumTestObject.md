# <img src="resources/maqslogo.ico" height="32" width="32"> AppiumTestObject
Appium test context data

## Inheritance Hierarchy
```java
BaseTestObject
    com.magenic.jmaqs.appium.AppiumTestObject
```
Package: com.magenic.jmaqs.appium;  
Assembly: import com.magenic.jmaqs.appium.AppiumTestObject

## Syntax
Java
```java
public class AppiumTestObject
```

The AppiumTestObject type exposes the following members.

## Constructors
[AppiumTestObject](#AppiumTestObject)  -  Initializes a new instance of the AppiumTestObject class

## Properties
[AppiumDriver]() - Gets the Appium driver  
[AppiumManager]() - Gets the Appium driver manager    
[Log]() - (Inherited from BaseTestObject.)  
[ManagerStore]() - (Inherited from BaseTestObject.)  
[Objects]() - (Inherited from BaseTestObject.)  
[PerfTimerCollection]() - (Inherited from BaseTestObject.)  
[Values]()  - (Inherited from BaseTestObject.)  

## Methods 
[GetAppiumDriver](#GetAppiumDriver)  -  Gets the Appium driver  
[GetAppiumManager](#GetAppiumManager)  - Gets the Appium manager  
[SetAppiumDriver](#SetAppiumDriver)  -  Sets the Appium driver

##  AppiumTestObject
Instantiates a new Appium test object.
#### Written as
 ```java
public AppiumTestObject(AppiumDriver<WebElement> appiumDriver, Logger logger, String fullyQualifiedTestName)
```
#### Example
```java
AppiumDriver<WebElement> defaultMobileDriver = AppiumDriverFactory.getDefaultMobileDriver();
AppiumTestObject appiumTestObject = new AppiumTestObject(defaultMobileDriver,
new ConsoleLogger(), method.getName());
```

##  GetAppiumDriver
Gets the Appium Driver
#### Written as
```java
AppiumDriver<WebElement> getAppiumDriver()
```
#### Example
 ```java
AppiumDriver appiumDriver = appiumTestObject.getAppiumDriver();
```

##  GetAppiumManager
Returns a Mobile Driver Manager.
#### Written as
```java
MobileDriverManager getAppiumManager()
```
#### Example
```java
MobileDriverManager appiumManager = appiumTestObject.getAppiumManager();
```

##  SetAppiumDriver
##### With a Web Element Parameter
```java
AppiumTestObject appiumTestObject = new AppiumTestObject(defaultMobileDriver,
    consoleLogger,testName);
appiumTestObject.setAppiumDriver(AppiumDriverFactory.getDefaultMobileDriver());
```
#### With a Supplier Parameter
```java
AppiumTestObject appiumTestObject = new AppiumTestObject(defaultMobileDriver,
    consoleLogger,testName);
appiumTestObject.setAppiumDriver(AppiumDriverFactory::getDefaultMobileDriver);
```
