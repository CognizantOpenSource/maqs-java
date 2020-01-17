# <img src="resources/maqslogo.ico" height="32" width="32"> SeleniumTestObject
Manages the interactions of the web driver and web manager.

## Inheritance Hierarchy
```java
AutoCloseable
    BaseTestObject
        com.magenic.jmaqs.appium.SeleniumTestObject
```
Package: com.magenic.jmaqs.appium;
<br> Assembly: import com.magenic.jmaqs.appium.SeleniumTestObject;

## Syntax
Java

```java
public class SeleniumTestObject extends BaseTestObject
```

The SeleniumTestObject type exposes the following members.

## Constructors
Instantiates a new Selenium test object.
#### Written as
```java
public SeleniumTestObject(Supplier<WebDriver> getDriverSupplier,
Logger logger, String fullyQualifiedTestName)
```
#### Example
```java
SeleniumTestObject testObject = new SeleniumTestObject((() 
-> finalDefaultBrowser), this.getLogger(),
this.getFullyQualifiedTestClassName());
```

## Methods

### GetWebDriver
Get the WebDriver Object.
#### Written as
```java
WebDriver getWebDriver()
```
#### Example
```java
WebElement elementDriver = UIWaitFactory.getWaitDriver(
    pageModel.getSeleniumTestObject().getWebDriver())
```

### GetWebManager
Gets the Selenium driver manager.
#### Written as
```java
SeleniumDriverManager getWebManager()
```
#### Example
```java
return this.getWebManager().getWebDriver();
```

### SetWebDriver
Sets the web driver.
#### Written as
```java
void setWebDriver(WebDriver driver)
```
#### Example
```java
this.getTestObject().setWebDriver(webDriver);
```
