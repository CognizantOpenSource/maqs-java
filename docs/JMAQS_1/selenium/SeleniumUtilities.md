# <img src="resources/maqslogo.ico" height="32" width="32"> SeleniumUtilities
The SeleniumUtilities class is a utility class for working with Selenium

## Inheritance Hierarchy
```java
com.magenic.jmaqs.selenium.SeleniumUtilities
```
Package: com.magenic.jmaqs.selenium;
<br> Assembly: import com.magenic.jmaqs.selenium.SeleniumUtilities;

## Syntax
Java

```java
public class SeleniumUtilities
```

The SeleniumUtilities type exposes the following members.

## Constructors
Creates an instance of the Selenium Utilities class.
#### Written as
```java
private SeleniumUtilities()
```

## MobileDriverManager
Initializes a new instance of the MobileDriverManager class  

#### Written as
```java
MobileDriverManager(Supplier<AppiumDriver<WebElement>> getDriverFunction, BaseTestObject baseTestObject)
```
#### Example
```java
MobileDriverManager mobileDriverManager = new MobileDriverManager(supplier, this.getTestObject());
```

# Methods
[CaptureScreenshot](#CaptureScreenshot) - Capture a screenshot.  
[SavePageSource](#SavePageSource) - Capture the page DOM.  
[WebElementToWebDriver](#WebElementToWebDriver) - Get the web driver from a web element  
[KillDriver](#KillDriver) - Make sure a web driver gets closed  

## CaptureScreenshot
Capture a screenshot.  
*By default screenshots ends up in the log folder, but one of the 'CaptureScreenshot' functions allows you to choose a different directory.*
```java
String username = "NOT";
String password = "Valid";
LoginPageModel page = new LoginPageModel(this.TestObject);
page.OpenLoginPage();

SeleniumUtilities.CaptureScreenshot(this.WebDriver, this.TestObject, "LoginPage");
```
## SavePageSource
Capture the page DOM.  
*By default page source ends up in the log folder, but one of the 'SavePageSource' functions allows you to choose a different directory.*
```java
String username = "NOT";
String password = "Valid";
LoginPageModel page = new LoginPageModel(this.TestObject);
page.OpenLoginPage();

SeleniumUtilities.SavePageSource(this.WebDriver, this.TestObject);
```
## WebElementToWebDriver
Get the web driver from a web element
```java
EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(webDriver);
LoginPageModel page = new LoginPageModel(this.TestObject);
page.OpenLoginPage();

WebElement input = UIWaitFactory.getWaitDriver(eventFiringWebDriver)
        .waitForVisibleElement(By.tagName("div"));

WebDriver webElementToWebDriver = SeleniumUtilities.webElementToWebDriver(input);
```
## KillDriver
Make sure a web driver gets closed
```java
WebDriver webDriver = WebDriverFactory.GetBrowserWithDefaultConfiguration(BrowserType.HeadlessChrome);

try {
      webDriver.close();
    } finally {
      webDriver.quit();
    }
```
