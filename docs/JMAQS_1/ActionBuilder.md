# <img src="resources/maqslogo.ico" height="32" width="32"> Action Builder
The action builder class contains many advanced methods using interactions with Selenium Actions class.  This includes actions that need to be performed synchronously.

## Hover Over an Element
Hovers the mouse over an element.

### Written as
```java
void HoverOver(SeleniumWait seleniumWait, By by)
```
### Examples
```java
private static By javascriptAlertButton = By.cssSelector(".javaScriptAlertButton");

ActionBuilder.HoverOver(this.getSeleniumWait(), this.javascriptAlertButton);
```
## Press Modifier Keys
Press modifier keys synchronously.  Each key will be pressed at the same time.  The Keys class is used to quickly write keys.

### Written as
```java
void pressModifierKey(SeleniumWait seleniumWait, CharSequence... key)
```
### Examples
```java
// Presses the arrow down, backspace, control, and divide keys all at once
ActionBuilder.pressModifierKey(this.getSeleniumWait(), Keys.chord(Keys.ARROW_DOWN, Keys.BACK_SPACE, Keys.CONTROL, Keys.DIVIDE))
```
```java
// Presses the Control key
ActionBuilder.pressModifierKey(this.getSeleniumWait(), Keys.CONTROL);
```

## Slide Element by X offset
Drags and drops an element by an X pixel offset.
### Written as
```java
void slideElement(SeleniumWait seleniumWait, By by, int pixelsOffset)
```
### Examples
```java
// Drags an element left 20 pixels and drops it
ActionBuilder.slideElement(this.getSeleniumWait(), element, -20);
```
```java
// Drags an element right 20 pixels and drops it
ActionBuilder.slideElement(this.getSeleniumWait(), element, 20);
```

## Right Click an Element
Performs a right click on an element

### Written as
```java
void rightClick(SeleniumWait seleniumWait, By by)
```
### Examples
```java
private static By titleImage = By.cssSelector(".title > img");

// Right clicks the title image
ActionBuilder.rightClick(this.getSeleniumWait(), titleImage);
```