# <img src="resources/maqslogo.ico" height="32" width="32"> BaseAppiumTest
Generic base Appium test class

## Inheritance Hierarchy

```java
BaseTest  
    BaseExtendableTest<AppiumTestObject>  
        com.magenic.jmaqs.appium.BaseAppiumTest
```
Package: com.magenic.jmaqs.appium;
Assembly: import com.magenic.jmaqs.appium.BaseAppiumTest

## Syntax
Java

```java
public abstract class BaseAppiumTest extends 
    BaseExtendableTest<AppiumTestObject>
```
The BaseAppiumTest type exposes the following members.

## Constructors
### BaseAppiumTest
Initializes a new instance of the BaseAppiumTest class. 
<br>Setup the web driver for each test class
#### Written as
```java
BaseAppiumTest()
```
#### Example
```java
public class BaseAppiumTestUnitTest extends BaseAppiumTest {
```

## Methods
[BeforeLoggingTeardown](#BeforeLoggingTeardown)  - Preps for the logging teardown  
[CreateNewTestObject](#CreateNewTestObject)  - creates a new Test object  
[GetAppiumDriver](#GetAppiumDriver)  -  Gets the appium driver  
[SetAppiumDriver](#SetAppiumDriver)  -  Sets the appium driver  
[GetMobileDriver](#GetMobileDriver)  -  Gets the mobile driver  

## BeforeLoggingTeardown
Steps to do before logging teardown results.
#### Written as
```java
void beforeLoggingTeardown(ITestResult resultType) 
```
#### Example
```java
this.beforeLoggingTeardown(testResult);
```

## CreateNewTestObject
Creates a new Appium Test object
#### Written as
```java
void createNewTestObject()
```
#### Example
```java
 this.createNewTestObject();
```

## GetAppiumDriver
Gets the appium driver.
#### Written as
```java
AppiumDriver<WebElement> getAppiumDriver()
```
#### Example
```java
this.getAppiumDriver()
```

## SetAppiumDriver
Sets appium driver.
#### Written as
```java
void setAppiumDriver(AppiumDriver<WebElement> mobileDriver)
```
#### Example
```java
this.setAppiumDriver(this.getMobileDriver());
```

## GetMobileDriver
Gets new mobile driver.
#### Written as
```java
AppiumDriver<WebElement> getMobileDriver()
```
#### Example
```java
this.setAppiumDriver(this.getMobileDriver());
```
