# <img src="resources/MAQS.jpg" height="32" width="32"> Playwright Features

## Overview
MAQS provides support for an additional way to test web applications.	

## BaseTest
Base Playwright Test is an abstract test class you can extend.  
Extending the class allows you to automatically use MAQS' web application testing capabilities.
```java
public class MyPlaywrightTests extends BasePlaywrightTest {}
```

## Driver
The PlaywrightDriver is an object that allows you to interact with Playwright browser instances.
This driver wraps common web application interactions, making Playwright testing relatively easy.
The driver is also thread safe, which means you can run multiple Playwright tests in parallel.
*Information, such as the OS version is pulled from the MAQS configuration.
```java
PageDriver driver = PageDriverFactory.getDefaultPageDriver();
```

## DriverManager
Driver that manages the driver manager.

## Log
There is also logger (also thread safe) that can be used to add log message to your log.
```java
this.getLogger().logMessage("I am testing with MAQS");
```

## TestObject
The TestObject can be thought of as your test context.
It holds all the MAQS test execution related data.
This includes the web page driver, logger, soft asserts, performance timers, plus more.

```java
this.getPageDriver().navigateTo("http://magenicautomation.azurewebsites.net/");
this.getLogger().logMessage("I am testing with MAQS");
```
*Notes:*  
* *Most of the test object objects are already accessible on the test level. For example **this.getLog()** and **this.getTestObject.getLog()** both access the same logger.*
* *You seldom use the test object directly. It is usually only used when you want to share your test MAQS context with another piece of code*

## Config
Stores methods for interacting with the config.xml

## Sample code
```java
package com.COMPANY.TESTING;

import com.cognizantsoftvision.maqs.playwright.BasePlaywrightTest;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The type Base Playwright test.
 */
public class BasePlaywrightTestUnitTest extends BasePlaywrightTest {
/**
   * Test get Playwright driver.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void PlaywrightTest() {
      PageModel page = new PageModel(this.getTestObject());
      page.OpenPage();
    }   
}
```