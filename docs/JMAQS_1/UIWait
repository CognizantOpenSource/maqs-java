# <img src="resources/maqslogo.ico" height="32" width="32"> Wait Methods
UI Wait has functions to wait for an element to exist, to not exist, 
<br> to be visible, to equal a desired value, and many others.

## Inheritance Hierarchy
```java
com.magenic.jmaqs.selenium.UIWait
```
Package: com.magenic.jmaqs.selenium;
<br> Assembly: import com.magenic.jmaqs.selenium.UIWait;

## Syntax
Java
```java
public class UIWait
```
The UIWait type exposes the following members.

## Constructors
Instantiates a new UIWait object.
#### Written as
```java
public UIWait(WebDriver driver)
```
#### Example
```java
waitDriver = new UIWait(baseDriver);
```

# Methods

## GetWaitDriver
Get the WebDriverWait for use outside of this instance class.
#### Written as
```java
WebDriverWait getWaitDriver()
```
#### Example
```java
return this.waitForPresentElement(by, this.getWaitDriver());
```

## SetWaitDriver
Sets the WebDriverWait.
#### Written as
```java
void setWaitDriver(WebDriverWait waiter)
```
#### Example
```java
this.setWaitDriver(this.getNewWaitDriver());
```

## ResetWaitDriver
Resets wait default wait driver.
#### Written as
```java
WebDriverWait resetWaitDriver
```
#### Example
```java
WebDriverWait = this.resetWaitDriver();
```

## WaitForPresentElement
Waits For the element to be present.
#### Written as
```java
WebElement waitForPresentElement(By by, WebDriverWait wait)
```
#### Example
```java
return this.waitForPresentElement(by, this.getWaitDriver());
```

## WaitForVisibleElement
Wait for the specified element to be visible on the pages DOM. 
<br>The first element located with the specified By value is returned.
#### Written as
```java
WebElement waitForVisibleElement(final By by)
```
#### Example
```java
WebElement element = UIWaitFactory.getWaitDriver(webDriver).waitForVisibleElement(by);
```

## WaitUntilVisibleElement
Wait until the element exists and is visible.
#### Written as
```java
boolean waitUntilVisibleElement(final By by, WebDriverWait wait) 
```
#### Example
```java
return waitUntilVisibleElement(by, this.getWaitDriver());
```

## WaitForEnabledElement
Wait for the specified element to be present and enabled. 
<br>The first element located with the specified By value is returned.
#### Written as
```java
WebElement waitForEnabledElement(final By by, final int timeOutInMillis,
    final int sleepInMillis)
```
#### Example
```java
return waitForEnabledElement(by, this.timeout, this.fluentRetryTime);
```

## WaitUntilEnabledElement
Wait for the specified element to be present and enabled.
#### Written as
```java
boolean waitUntilEnabledElement(final By by, final int timeOutInMillis,
      final int sleepInMillis)
```
#### Example
```java
return waitUntilEnabledElement(by, this.timeout, this.fluentRetryTime);
```

## WaitUntilDisabledElement
Waits until the element is disabled.
#### Written as
```java
boolean waitUntilDisabledElement(By by, final int timeOutInMillis,
      final int sleepInMillis)
```
#### Example
```java
return waitUntilDisabledElement(by, this.timeout, this.fluentRetryTime);
```

## WaitForAbsentElement
Wait until the element is not displayed or visible.
#### Written as
```java
void waitForAbsentElement(final By by)
```
#### Example
```java
UIWaitFactory.getWaitDriver(webDriver).waitForAbsentElement(by);
```

## WaitUntilAbsentElement
Wait until the element is not displayed or visible.
#### Written as
```java
boolean waitUntilAbsentElement(By by, final int timeOutInMillis, final int sleepInMillis)
```
#### Example
```java
boolean isAbsent = this.waitUntilAbsentElement(by, timeOutInMillis, sleepInMillis);
```

## WaitForElements
Wait for a selector to present, and then return a list 
<br>of all WebElements that are located bythat selector.
#### Written as
```java
List<WebElement> waitForElements(final By by, WebDriverWait wait)
```
#### Example
```java
return this.waitForElements(by, getWaitDriver());
```

## WaitForExactText
Wait for the specified element to have the Exact text.
#### Written as
```java
WebElement waitForExactText(final By by, final String text)
```
#### Example
```java
WebElement exactText = this.getWebDriver()).waitForExactText(employeePageTitle, "Index");
```

## WaitUntilExactText
Wait until the exact text is present in the specified element.
#### Written as
```java
boolean waitUntilExactText(final By by, final String text) 
```
#### Example
```java
bool exactText = this.getWebDriver().waitUntilExactText(by, text);
```

## WaitForContainsText
Wait for an element to contain a specified text.
#### Written as
```java
WebElement waitForContainsText(final By by, final String text) 
```
#### Example
```java
WebElement containsText = this.getWebDriver().waitForContainsText(By, "Header Text")
```

##  WaitUntilContainsText
Wait until the element contains the specified text.
#### Written as
```java
boolean waitUntilContainsText(final By by, final String text, WebDriverWait wait)
```
#### Example
```java
return this.waitUntilContainsText(by, text, getWaitDriver());
```

## WaitUntilAttributeTextEquals
Wait until an attribute of the specified selector to be present and equal the desired value.
#### Written as
```java
boolean waitUntilAttributeTextEquals(final By by, final String attribute,
      final String text)
```
#### Example
```java
boolean attributeText = this.waitUntilAttributeTextEquals(By, attribute, text);
```

## WaitForAttributeTextEquals
Wait for an attribute of the specified selector to be present and equal the desired value.
#### Written as
```java
WebElement waitForAttributeTextEquals(final By by, final String attribute,
      final String text, WebDriverWait wait)
```
#### Example
```java
return this.waitForAttributeTextEquals(by, attribute, text, getWaitDriver());
```

## WaitUntilAttributeTextContains
Wait for an attribute of the specified selector to be present, and contain the specified text.
#### Written 
```java
boolean waitUntilAttributeTextContains(final By by, final String attribute,
      final String text)
```
#### Example
```java
boolean textContains = this.waitUntilAttributeTextContains(by, attribute, text);
```

## WaitUntilAttribute
Wait for an attribute of the specified selector to be present, and contain the specified text.
#### Written as
```java
boolean waitUntilAttribute(final By by, final String attribute, final String text,
      WebDriverWait wait, final boolean contains)
```
#### Example
```java
return this.waitUntilAttribute(by, attribute, text, getWaitDriver(), false);
```

## WaitForClickableElementAndScrollIntoView
Scroll an element into view, and wait for it to be clickable.
#### Written as
```java
WebElement waitForClickableElementAndScrollIntoView(final By by, WebDriverWait wait)
```
#### Example
```java
return this.waitForClickableElementAndScrollIntoView(by, getWaitDriver());
```

## WaitUntilClickableElementAndScrollIntoView
Scroll an element into view, and wait for it to be clickable.
#### Written as
```java
boolean waitUntilClickableElementAndScrollIntoView(final By by, WebDriverWait wait)
```
#### Example
```java
return this.waitUntilClickableElementAndScrollIntoView(by, getWaitDriver());
```

## WaitUntilClickableElement
Wait for the specified selector to be clickable.
#### Written as
```java
boolean waitUntilClickableElement(final By by, WebDriverWait wait) 
```
#### Example
```java
return this.waitUntilClickableElement(by, getWaitDriver());
```

## WaitForClickableElement
Wait for the element specified by the provided selector to be clickable.
#### Written as
```java
WebElement waitForClickableElement(final By by)
```
#### Example
```java
WebElement element = UIWaitFactory.getWaitDriver(webDriver)
    .waitForClickableElement(by);
```

## WaitForPageLoad
Wait for the page to load by waiting for the source to stop changing for at least a second.
#### Written as
```java
void waitForPageLoad() 
```
#### Example
```java
UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();
```

## WaitUntilPageLoad
Wait for the page to load by waiting for the source to stop changing for at least a second.
#### Written as
```java
boolean waitUntilPageLoad()
```
#### Example
```java
boolean loaded = waitUntilPageLoad();
```

## WaitUntilIframeToLoad
Waits for the Frame to be available.
#### Written as 
```java
boolean waitUntilIframeToLoad(By by, WebDriverWait wait)
```
#### Example
```java
boolean IframeLoaded = return waitUntilIframeToLoad(by, this.getWaitDriver());
```

## WaitForIframeToLoad
Waits for the Frame to be available.
#### Written as
```java
void waitForIframeToLoad(By by, WebDriverWait wait)
```
#### Example
```java
waitForIframeToLoad(by, wait);
```

## GetWebDriver
Returns the webDriver of this SeleniumWait object.
#### Written as
```java
WebDriver getWebDriver()
```
#### Example
```java
WebDriver newWebDriver = this.getWebDriver();
```

## GetNewWaitDriver
Gets a new WaitDriver using the default timeout.
#### Written as
```java
WebDriverWait getNewWaitDriver()
```
#### Example
```java
WebDriverWait webDriver = this.setWaitDriver(this.getNewWaitDriver());
```

## ScrollIntoView
Scroll the web page so the selector is visible.
#### Written as
```java
void scrollIntoView(By by)
```
#### Example
```java
scrollIntoView(by);
```

## AttributeMatches
Check the text value of an attribute.
#### Written as
```java
static boolean attributeMatches(WebDriver driver, By by,
    String attribute, String text, boolean contains)
```
#### Example
```java
(ExpectedCondition<Boolean>) f -> attributeMatches(f, by, attribute, text, contains));
```

## DoesTextMatch
Checks if the text of the elements are equal 
#### Written as
```java
boolean doesTextMatch(By by, String text) 
```
#### Example
```java
boolean textMatch = wait.until((ExpectedCondition<Boolean>) function -> doesTextMatch(by, text));
```

## DoesContainsText
Checks if the text of the elements are equal.
#### Written as
```java
boolean doesContainsText(By by, String text)
```
#### Example
```java
boolean containstext = wait.until((ExpectedCondition<Boolean>) function -> doesContainsText(by, text));
```
