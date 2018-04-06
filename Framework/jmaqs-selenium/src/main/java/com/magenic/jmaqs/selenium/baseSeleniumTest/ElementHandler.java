/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium.baseSeleniumTest;

import com.magenic.jmaqs.utilities.helper.ListProcessor;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Contains functions for interacting with WebElement objects.
 */
public class ElementHandler {

  /**
   * Get selected option from drop down.
   * 
   * @param seleniumWait
   *          SeleniumWait Object
   * @param by
   *          By selector for the element
   * @return Text of the selected option in drop down
   */

  public static String getSelectedOptionFromDropdown(SeleniumWait seleniumWait, By by) {

    Select select = new Select(seleniumWait.waitForClickableElement(by));
    return select.getFirstSelectedOption().getText();
  }

  /**
   * Get a ArrayList of the items selected in a drop down.
   * 
   * @param seleniumWait
   *          SeleniumWait Object
   * @param by
   *          By selector for the element
   * @return String array list of selected items in drop down
   */
  public static ArrayList<String> getSelectedOptionsFromDropdown(SeleniumWait seleniumWait, By by) {

    ArrayList<WebElement> elements;
    ArrayList<String> selectedItems = new ArrayList<String>();
    Select select = new Select(seleniumWait.waitForClickableElement(by));

    // Get a ArrayList of WebElement objects for all selected options from
    // the dropdown
    elements = (ArrayList<WebElement>) select.getAllSelectedOptions();

    // Add the text of each element that is not null to the selectedItems
    // ArrayList
    for (WebElement element : elements) {
      if (element != null) {
        selectedItems.add(element.getText());
      }
    }

    return selectedItems;
  }

  /**
   * Get the value of a specific attribute for an element.
   * 
   * @param seleniumWait
   *          SeleniumWait Object
   * @param by
   *          By selector for the element
   * @return The text in the textbox
   */
  public static String getElementAttribute(SeleniumWait seleniumWait, By by) {
    return getElementAttribute(seleniumWait, by, "value");
  }

  /**
   * Get the value of a specific attribute for an element.
   * 
   * @param seleniumWait
   *          SeleniumWait Object
   * @param by
   *          By selector for the element
   * @param attribute
   *          The attribute to get the value for
   * @return The text in the textbox
   */
  public static String getElementAttribute(SeleniumWait seleniumWait, By by, String attribute) {

    return seleniumWait.waitForVisibleElement(by).getAttribute(attribute);
  }

  /**
   * Check or Uncheck a checkbox NOTE: If checkbox is already in desired state, this method takes no
   * action
   * 
   * @param seleniumWait
   *          SeleniumWait Object
   * @param by
   *          By selector for the element
   * @param check
   *          True to check the checkbox. False to uncheck the checkbox
   */
  public static void checkCheckBox(SeleniumWait seleniumWait, By by, boolean check) {
    WebElement element = seleniumWait.waitForClickableElement(by);

    if (check && !element.isSelected()) {
      element.click();
    } else if (!check && element.isSelected()) {
      element.click();
    }
  }

  /**
   * Create a comma delimited String from a ArrayList of WebElement objects.
   * 
   * @param webDriver
   *          The WebDriver
   * @param by
   *          By selector for the elements
   * @param sort
   *          True to create an alphabetically sorted comma delimited String
   * @return Returns a comma delimited String
   */
  public static String createCommaDelimitedString(WebDriver webDriver, By by, boolean sort) {
    ArrayList<String> unsortedList = new ArrayList<String>();

    for (WebElement element : webDriver.findElements(by)) {
      unsortedList.add(element.getText().trim());
    }

    return ListProcessor.createCommaDelimitedString(unsortedList, sort);
  }

  /**
   * Create a comma delimited String from a ArrayList of WebElement objects.
   * 
   * @param webDriver
   *          The WebDriver
   * @param by
   *          By selector for the elements
   * @return Returns a comma delimited String
   */
  public static String createCommaDelimitedString(WebDriver webDriver, By by) {

    return createCommaDelimitedString(webDriver, by, false);
  }

  /**
   * Clicks an element
   * 
   * @param seleniumWait
   *          SeleniumWait Object
   * @param by
   *          By selector for the element
   * @param waitForButtonToDisappear
   *          If True, wait for element to disappear. If False, Do not wait
   */
  public static void clickButton(SeleniumWait seleniumWait, By by,
      boolean waitForButtonToDisappear) {
    seleniumWait.waitForClickableElement(by).click();

    if (waitForButtonToDisappear) {
      seleniumWait.waitForAbsentElement(by);
    }
  }

  /**
   * Select multiple items from a ArrayList box.
   * 
   * @param seleniumWait
   *          SeleniumWait Object
   * @param by
   *          By selector for the element
   * @param elementsTextToSelect
   *          ArrayList items as Strings to select from ArrayList box
   */
  public static void selectMultipleElementsFromArrayListBox(SeleniumWait seleniumWait, By by,
      ArrayList<String> elementsTextToSelect) {
    Select selectItem = new Select(seleniumWait.waitForClickableElement(by));

    // Select all desired items in the ArrayListbox
    for (String text : elementsTextToSelect) {
      selectItem.selectByVisibleText(text);
    }
  }

  /**
   * Select multiple items from a ArrayList box.
   * 
   * @param seleniumWait
   *          SeleniumWait Object
   * @param by
   *          By selector for the element
   * @param values
   *          ArrayList items as Strings to select from ArrayList box
   */
  public static void selectMultipleElementsFromArrayListBoxByValue(SeleniumWait seleniumWait, By by,
      ArrayList<String> values) {
    Select selectItem = new Select(seleniumWait.waitForClickableElement(by));

    // Select all desired items in the Listbox
    for (String value : values) {
      selectItem.selectByValue(value);
    }
  }

  /**
   * Select an option from a drop down using displayed text.
   * 
   * @param seleniumWait
   *          SeleniumWait Object
   * @param by
   *          By selector for the element
   * @param option
   *          The option to select in drop down
   */
  public static void selectDropDownOption(SeleniumWait seleniumWait, By by, String option) {
    Select select = new Select(seleniumWait.waitForClickableElement(by));
    select.selectByVisibleText(option);
  }

  /**
   * Select an option from a drop down using the value attribute.
   * 
   * @param seleniumWait
   *          SeleniumWait Object
   * @param by
   *          By selector for the element
   * @param value
   *          The value attribute for the option to select
   */
  public static void selectDropDownOptionByValue(SeleniumWait seleniumWait, By by, String value) {
    Select select = new Select(seleniumWait.waitForClickableElement(by));
    select.selectByValue(value);
  }

  /**
   * Enter text into a textbox. NOTE: This function clears the textbox before entering text.
   * 
   * @param seleniumWait
   *          SeleniumWait Object
   * @param by
   *          By selector for the element
   * @param textToEnter
   *          Text to enter into the textbox
   * @param tabOff
   *          true to press the Tab key after entering text
   */
  public static void setTextBox(SeleniumWait seleniumWait, By by, String textToEnter,
      boolean tabOff) {
    if (!textToEnter.isEmpty() && textToEnter != null) {
      if (tabOff) {
        textToEnter += Keys.TAB;
      }

      WebElement element = seleniumWait.waitForVisibleElement(by);
      element.clear();
      element.sendKeys(textToEnter);
    } else {
      throw new RuntimeException("String is either null or empty");
    }
  }

  /**
   * Enter text into a textbox. NOTE: This function clears the textbox before entering text.
   * 
   * @param seleniumWait
   *          SeleniumWait Object
   * @param by
   *          By selector for the element
   * @param textToEnter
   *          Text to enter into the textbox
   */
  public static void setTextBox(SeleniumWait seleniumWait, By by, String textToEnter) {

    setTextBox(seleniumWait, by, textToEnter, true);
  }

  /**
   * Method to click an element via JavaScript Used for scenarios where normal click can't reach,
   * such as hidden or hover triggered elements.
   * 
   * @param webDriver
   *          The WebDriver
   * @param by
   *          By selector for the element
   */
  public static void clickElementByJavaScript(WebDriver webDriver, By by) {
    WebElement element = webDriver.findElement(by);
    JavascriptExecutor executor = (JavascriptExecutor) webDriver;
    executor.executeScript("arguments[0].click();", element);
  }

  /**
   * JavaScript method to scroll an element into the view.
   * 
   * @param webDriver
   *          The WebDriver
   * @param by
   *          By selector for the element
   */
  public static void scrollIntoView(WebDriver webDriver, By by) {
    WebElement element = webDriver.findElement(by);
    ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
  }

  /**
   * JavaScript method to scroll an element into the view.
   * 
   * @param webElement
   *          The web element
   * @param by
   *          By element
   */
  public static void scrollIntoView(WebElement webElement, By by) {
    WebElement element = webElement.findElement(by);
    WebDriver webDriver = SeleniumUtilities.webElementToWebDriver(element);

    ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
  }

  /**
   * JavaScript method to scroll an element into the view.
   * 
   * @param webDriver
   *          The web driver
   * @param by
   *          By element
   * @param x
   *          Horizontal direction
   * @param y
   *          Vertical direction
   */
  public static void scrollIntoView(WebDriver webDriver, By by, int x, int y) {
    scrollIntoView(webDriver, by);
    executeScrolling(webDriver, x, y);
  }

  /**
   * JavaScript method to scroll an element into the view.
   * 
   * @param webElement
   *          The web element
   * @param by
   *          By element
   * @param x
   *          Horizontal direction
   * @param y
   *          Vertical direction
   */
  public static void scrollIntoView(WebElement webElement, By by, int x, int y) {
    scrollIntoView(webElement, by);
    executeScrolling(webElement, x, y);
  }

  /**
   * JavaScript method to scroll an element into the view.
   * 
   * @param webDriver
   *          The web driver
   * @param x
   *          Horizontal direction
   * @param y
   *          Vertical direction
   */

  public static void executeScrolling(WebDriver webDriver, int x, int y) {
    String scrollCommand = String.format("scroll(%s, %s);", x, y);
    ((JavascriptExecutor) webDriver).executeScript(scrollCommand);
  }

  /**
   * JavaScript method to scroll an element into the view.
   * 
   * @param webElement
   *          The web element
   * @param x
   *          Horizontal direction
   * @param y
   *          Vertical direction
   */
  public static void executeScrolling(WebElement webElement, int x, int y) {
    WebDriver webDriver = SeleniumUtilities.webElementToWebDriver(webElement);
    executeScrolling(webDriver, x, y);
  }

  /**
   * Method to slowly type a String Used for scenarios where normal SendKeys types too quickly and
   * causes issues with a website.
   * 
   * @param seleniumWait
   *          SeleniumWait Object
   * @param by
   *          The By element to use
   * @param textToEnter
   *          The String being entered into the text input field
   */
  public static void slowType(SeleniumWait seleniumWait, By by, String textToEnter) {
    for (char singleLetter : textToEnter.toCharArray()) {
      seleniumWait.waitForClickableElement(by).sendKeys(Character.toString(singleLetter));

      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        // Wait
      }
    }
  }

}
