# <img src="resources/maqslogo.ico" height="32" width="32"> SeleniumDriverManager
Manages the Selenium Driver.

## Inheritance Hierarchy
```java
AutoClosable
    DriverManager
        com.magenic.jmaqs.selenium.SeleniumDriverManager
```
Package: com.magenic.jmaqs.selenium;
<br> Assembly: import com.magenic.jmaqs.selenium.SeleniumDriverManager;

## Syntax
Java
```java
public class SeleniumDriverManager extends DriverManager
```
The SeleniumDriverManager type exposes the following members.

## Constructors
Initializes a new instance of the SeleniumDriverManager class.
#### Written as
```java
public <T> SeleniumDriverManager(Supplier<T> getDriver, BaseTestObject testObject)
```

# Methods

## SeleniumDriverManager
Initializes a new instance of the SeleniumDriverManager class.
#### Written as
```java
SeleniumDriverManager(Supplier<T> getDriver, BaseTestObject testObject)
```
#### Example
```java
SeleniumDriverManager seleniumDriverManager = new SeleniumDriverManager(getDriver, this.getTestObject());
```

## Close
Closes the WebDriver
#### Written as
```java
void close() 
```
#### Example
```java
this.close();
```

## GetWebDriver
Get the web driver.
#### Written as
```java
WebDriver getWebDriver()
```
#### Example
```java
WebDriver driver = this.getWebDriver();
```

## LogVerbose
Log a verbose message and include the automation specific call stack data.
#### Written as
```java
void logVerbose(String message, Object... args)
```
#### Example
```java
seleniumDriverManager.logVerbose("Logging verbose messaging");
```

## LoggingSetup
Logging startup.
#### Written as
```java
void loggingStartup(WebDriver webDriver)
```
#### Example
```java
this.loggingStartup(tempDriver);
```
