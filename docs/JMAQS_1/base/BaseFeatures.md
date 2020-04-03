# <img src="resources/maqslogo.ico" height="32" width="32"> Base Basics

## Overview
MAQS provides support for testing, base is the foundation for this support.  

## BaseTest
 Base for tests without a defined system under test
 ```java
  MaqsBase basetest = new MaqsBase();
 ```

## ExtenableTest
Base code that extends the base test objects for Selenium, Appium, WebServices, Database, Email, and MongoDB drivers or connections.

## TestObject
Base test context data
```java
BaseTestObject baseTestObject = new BaseTestObject(new ConsoleLogger(), string.Empty);
```

## DriverManager
Base driver manager object
```java
DriverManager managerToKeep = GetManager();
```

## ManagerDictionary
 Driver manager dictionary allows you to get, add, override, remove, clears, and disposes the driver. 
 ```java
 this.ManagerStore = new ManagerDictionary();
 ```