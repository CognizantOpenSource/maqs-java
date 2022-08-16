# <img src="resources/MAQS.jpg" height="32" width="32"> Base Playwright Test

## Overview
The Base Playwright Test has methods that set up the web driver, gets the page driver, tears down the Playwright driver, and creates a new test object. 

# Available calls
[GetPlaywrightDriver](#GetPlaywrightDriver)  
[SetPlaywrightDriver](#SetPlaywrightDriver)  
[GetPageDriver](#GetPageDriver)  
[BeforeLoggingTeardown](#BeforeLoggingTeardown)  
[CreateNewTestObject](#CreateNewTestObject)  

## GetPlaywrightDriver
This method gets the default Playwright page driver
```java
public PageDriver getPageDriver() {
    return this.getTestObject().getPageDriver();
  }
```

## SetPlaywrightDriver
This method sets the default Playwright driver
```java
public void setPlaywrightDriver(PageDriver pageDriver) {
    this.getTestObject().setPageDriver(pageDriver);
  }
```

## GetPageDriver
Gets new page driver
```java
protected PageDriver getPageDriver() {
    return PageDriverFactory.getDefaultPageDriver();
  }
```

## BeforeLoggingTeardown
Takes a screenshot if needed and tear down the Playwright driver. It is called during teardown.
```java
this.beforeLoggingTeardown(resultType);
```

## CreateNewTestObject
This method creates a new Playwright test object based on the page device.
```java
 protected override void createNewTestObject(){
    this.TestObject = new PlaywrightTestObject(() => this.getPageDriver(), this.createLogger(), this.getFullyQualifiedTestClassName());
}

this.createNewTestObject();
```