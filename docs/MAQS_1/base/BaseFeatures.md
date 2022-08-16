# <img src="resources/MAQS.jpg" height="32" width="32"> Base Features

## Overview
MAQS provides support for testing, base is the foundation for this support.  

## Base Test
Base for tests without a defined system under test
```java
MaqsBase basetest = new MaqsBase();
```

## ExtendableTest
Base code that extends the base test objects for Selenium, Appium, WebServices, and Database drivers or connections.

## TestObject
Base test context data
```java
BaseTestObject baseTestObject = new BaseTestObject(this.createLogger(), this.getFullyQualifiedTestClassName()));
```

## DriverManager
Base driver manager object
```java
DriverManager managerToKeep = getDriverManager();
```

## ManagerDictionary
 Driver manager dictionary allows you to get, add, override, remove, clears, and disposes the driver. 
 ```java
 this.setManagerStore(getManagerDictionary());
 ```