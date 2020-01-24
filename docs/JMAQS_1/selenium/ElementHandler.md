# <img src="resources/maqslogo.ico" height="32" width="32"> ElementHandler Class
This class provides additional helper methods on top of Selenium's base interaction methods for common operations done when interacting with HTML elements.

## Get Text From Selected Dropdown Item
Gets the selected option's displayed text from a select element and returns it.
### Written as
```java
String getSelectedOptionFromDropdown(WebDriver webDriver, By by)
```
### Example
```java
// A by selector for a list of names from a dropdown
private static By nameDropdown = By.cssSelector("#namesDropdown");

// Gets the text of the current selected option
String nameSelected = ElementHandler.getSelectedOptionFromDropdown(this.getWebDriver(), nameDropdown);
```

## Get List of Text From Selected Dropdown Items
Gets all the selected options' displayed text from a select element and returns them in a list.
### Written as
```java
ArrayList<String> getSelectedOptionsFromDropdown(WebDriver webDriver, By by)
```
### Example
```java
// A by selector for a list of names from a dropdown
private static By nameDropdown = By.cssSelector("#namesDropdown");

ArrayList<String> listOfNamesSelected = ElementHandler.getSelectedOptionsFromDropdown(this.getWebDriver(), nameDropdown);
```

## Return an Element's Attribute's Value
Gets the value of an attribute for an element. By default, it gets the value of the "value" attribute.
### Written as
```java
String getElementAttribute(WebDriver webDriver, By by)
String getElementAttribute(WebDriver webDriver, By by, String attribute)
```
### Examples
```java
// By selector for the page title
private static By pageTitle = By.cssSelector(".title");

// Returns the value of the element's value
String pageTitleValue = ElementHandler.getElementAttribute(this.getWebDriver(), pageTitle);
```
```java
// By selector for the page title
private static By pageTitle = By.cssSelector(".title");

// Returns the value of the elements href
String pageTitleHrefValue =  ElementHandler.getElementAttribute(this.getWebDriver(), pageTitle, "href");
```

## Check a Checkbox
Checks or unchecks an element. It will check the element if the bool argument is true. It will uncheck the element if the bool argument is false.  If the box is already in state the action wants it to be in upon completing that action, then the action will not do any checking or unchecking.
### Written as
```java
void checkCheckBox(WebDriver webDriver, By by, boolean check)
```

### Example
```java
// By selector for a checkbox
private static By checkBox = By.id("CheckBox");

// Checkbox is initially unchecked, waits for the element to become clickable
UIWaitFactory.getWaitDriver(this.getWebDriver()).waitUntilClickableElement(checkbox);

// If the checkbox is unchecked, then the driver will check the checkbox
ElementHandler.checkCheckBox(this.getWebDriver(), checkbox, true);

// If the checkbox is checked, then the driver will uncheckcheck the checkbox
ElementHandler.checkCheckBox(this.getWebDriver(), checkbox, false);
```
In applications a checkbox will either be checked or unchecked.  Stating "click this checkbox" does not take into consideration the state of that checkbox.  

## Create a Comma Delimited String
Creates a collection of elements based on a selector, loops through the collection, gathering text, adding it to a list, removes any white space, sorts the list alphabetically, and then returns that list as a string with the values separated by commas.  By default it will retrieve the value from the value attribute.

### Written as
```java
String createCommaDelimitedString(WebDriver webDriver, By by)
String createCommaDelimitedString(WebDriver webDriver, By by, boolean sort)
```
### Examples
```java
// A by selector for a list of computer parts.
private static By computerParts = By.cssSelector("ul>#options");

// Returns the text of the elements as an ordered string
String computerOptions =  ElementHandler.createCommaDelimitedString(this.getWebDriver(), computerParts, true);
```
```java
// A by selector for a list of computer parts.
private static By computerParts = By.CssSelector("ul>#options");

// Returns the text of the elements without ordering them
String computerOptions =  ElementHandler.createCommaDelimitedString(this.getWebDriver(), computerParts);
```

## Click Button and Wait For Button Disappear
Clicks an element. If the bool argument is true, it will wait until the button disappears after clicking it, else it will immediately return.  If it waits for the button to disappear, it will throw an exception if it does not.  If it does not wait for the button to disappear, it will continue.
### Written as
```java
void clickButton(WebDriver webDriver, By by, boolean waitForButtonToDisappear)
```
### Example
```java
// By selector for a checkbox
private static By button = By.id("LargeButton");

ElementHandler.clickButton(this.getWebDriver(), button, true);
```

## Select Multiple Dropdown Options by Options Text
Selects multiple option elements from a list box using a list of strings of the option elements' displayed texts.

### Written as
```java
void selectMultipleElementsFromListBox(WebDriver webDriver, By by,
      ArrayList<String> elementsTextToSelect)
```

### Example
```java
// A by selector for a list of computer parts.
private static By computerParts = By.cssSelector("ul>#options");

ArrayList<String> itemsToSelect = new ArrayList<String>();
    itemsToSelect.add("Monitor");
    itemsToSelect.add("Hard Drive");
    itemsToSelect.add("Keyboard");

ElementHandler.selectMultipleElementsFromListBox(this.getWebDriver(), computerParts, itemsToSelect);
```

## Select Multiple Dropdown Options by Options Value
Selects multiple option elements from a list box using a list of strings of the option elements' value attribute texts.
### Written as
```java
void selectMultipleElementsFromListBoxByValue(WebDriver webDriver, By by, ArrayList<String> values)
```
### Example
```java
// A by selector for a list of computer parts.
private static By computerParts = By.cssSelector("ul>#options");

ArrayList<String> itemsToSelect = new ArrayList<String>();
    itemsToSelect.add("1");
    itemsToSelect.add("2");

ElementHandler.selectMultipleElementsFromListBoxByValue(this.getWebDriver(), computerParts, itemsToSelect);
```

## Select Dropdown Option by Option's Text
Selects an option element from a select element using the option's displayed text.
### Written as
```java
void selectDropDownOption(WebDriver webDriver, By by, String option)
```
### Example
```java
// A by selector for a list of computer parts.
private static By computerParts = By.cssSelector("ul>#options");

// Selects the element inside the computer parts options where the text matches "Motherboard"
ElementHandler.selectDropDownOption(this.getWebDriver(), computerParts, "Motherboard");
```

## Select Dropdown Option by Value
Selects an option element from a select element using the option's value attribute text.

### Written as
```java
void selectDropDownOptionByValue(WebDriver webDriver, By by, String value)
```
### Example
```java
// A by selector for a list of computer parts.
private static By computerParts = By.cssSelector("ul>#options");

// Selects the element where the value attribute is equal to "1"
ElementHandler.selectDropDownOptionByValue(computerParts , "1");
```

## Set Text Box
Enters text into an element. It also clears the element before entering anything. If the tabOff  is not set or is set to true, then the last key sent will be a tab, else it won't send a tab key at the end of typing the string argument.
### Written as
```java
void setTextBox(WebDriver webDriver, By by, String textToEnter)
void setTextBox(WebDriver webDriver, By by, String textToEnter, boolean tabOff)
```
### Example
```java
// By selector for a textField
private static By textField = By.id("textBox");

// Sends the words "hello, world" to the text box, and then sends tab
ElementHandler.setTextBox(this.getWebDriver(), textField , "hello, world");

// Sends the words "hello, world" to the text box, and does not send tab
ElementHandler.setTextBox(this.getWebDriver(), textField , "hello, world", false);
```

## Click Element With JavaScript
Clicks an element using JavaScript's [click][1] method where using the normal Selenium Click method may not work correctly, such as hidden or hover triggered elements.
### Written as
```java
void clickElementByJavaScript(WebDriver webDriver, By by)
```
### Example
```java
private static By button = By.id("LargeButton");

ElementHandler.clickElementByJavaScript(this.getWebDriver(), button);
```

## Scroll until Element is in View
Uses the JavaScript [scrollIntoView][2] method to scroll an element into the view.
### Written as
```java
void scrollIntoView(WebDriver webDriver, By by)
void scrollIntoView(WebElement webElement, By by)
```
### Example
```java
// By selector for the page title
private static By pageTitle = By.cssSelector(".title");

// Scrolls until the page title is in view
ElementHandler.scrollIntoView(this.getWebDriver(), pageTitle);
```

## Scrolls Until Element is in View then Scroll to X and Y Coordinates
Uses the JavaScript [scrollIntoView][2] and [scroll][3] methods to scroll an element into view and then scroll based on an offset from the location of that element using the x and y arguments.
### Written as
```java
void scrollIntoView(WebDriver webDriver, By by, int x, int y)
void scrollIntoView(WebElement webElement, By by, int x, int y)
```
### Example
```java
// By selector for the page title
private static By pageTitle = By.cssSelector(".title");

// Scrolls to the page title and then scrolls by the x and y offsets
ElementHandler.scrollIntoView(this.getWebDriver(), pageTitle , -20, 150);
```

## Scroll By X and Y
Executes horizontal and vertical scrolling using the JavaScript [scroll][3] method based on the x and y arguments.
### Written as
```java
void executeScrolling(WebDriver webDriver, int x, int y)
void executeScrolling(WebElement webElement, int x, int y)
```

### Example
```java
// Scrolls by the x and y offsets
ElementHandler.executeScrolling(this.getWebDriver(), 50, -100);
```

## Type Text Slowly
Slowly types a string. Useful in scenarios where the normal Selenium SendKeys method types too quickly and causes issues. It sends key presses every 500 milliseconds.
### Written as
```java
void slowType(WebDriver webDriver, By by, String textToEnter)
```

### Example
```java
// By selector for a textField
private static By textField = By.id("textBox");

ElementHandler.slowType(this.getWebDriver(), textField, "hello, world");
```

## Send Secret Keys Without Logging
Sends keys to an element without logging. Useful to prevent secret fields (passwords) from being logged.
### Written as
```java
void sendSecretKeys(WebDriver webDriver, By by, String textToEnter, Logger logger)
```

### Example
```java
 private static By passwordField = By.id("passwordField");

 ElementHandler.sendSecretKeys(this.getWebDriver(), passwordField, "superSecretPassword", this.getLogger());
 ```


[1]: https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement/click
[2]: https://developer.mozilla.org/en-US/docs/Web/API/Element/scrollIntoView
[3]: https://developer.mozilla.org/en-US/docs/Web/API/Window/scroll
