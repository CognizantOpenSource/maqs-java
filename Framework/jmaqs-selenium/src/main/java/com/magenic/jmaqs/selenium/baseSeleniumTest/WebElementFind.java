/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium.baseSeleniumTest;

import com.magenic.jmaqs.utilities.helper.StringProcessor;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Web element find helper class.
 */
public class WebElementFind {

  /**
   * General Find Element.
   * 
   * @param driver
   *          Web Driver
   * @param by
   *          Css Selector
   * @param throwException
   *          optional assert parameter - throws an assert exception if no element is found
   * @return Returns A Web Element
   * 
   */
  public static WebElement findElement(WebDriver driver, By by, boolean throwException) {
    // returns the 1st element in the collection if it is not null or empty
    List<WebElement> elementList = elemList(driver, by, throwException);
    if (elementList == null || elementList.size() == 0) {
      return null;
    }

    WebElement element = elementList.get(0);

    return element;
  }

  /**
   * General Find Element.
   * 
   * @param driver
   *          Web Driver
   * @param by
   *          Css Selector
   * @return Returns A Web Element
   */
  public static WebElement findElement(WebDriver driver, By by) {
    return findElement(driver, by, true);
  }

  /**
   * Find a specified Web Element by text.
   * 
   * @param driver
   *          Web Driver
   * @param by
   *          Css Selector
   * @param text
   *          Text to search the Web Element Collection
   * @param throwException
   *          optional assert parameter - throws an exception if no element is found
   * @return Returns a Web Element
   */
  public static WebElement findElementWithText(WebDriver driver, By by, String text,
      boolean throwException) {
    // loop through elementList collection to find text match -- returns if
    // found, else null
    List<WebElement> elementList = elemList(driver, by, throwException);

    if (elementList == null || elementList.size() == 0) {
      return null;
    }

    for (int i = 0; i < elementList.size(); i++) {
      if (elementList.get(i).toString().equals(text)) {
        WebElement element = elementList.get(i);
        return element;
      }
    }

    return null;
  }

  /**
   * Find a specified Web Element by text.
   * 
   * @param driver
   *          Web Driver
   * @param by
   *          Css Selector
   * @param text
   *          Text to search the Web Element Collection
   * @return Returns a Web Element
   */
  public static WebElement findElementWithText(WebDriver driver, By by, String text) {
    return findElementWithText(driver, by, text, true);

  }

  /**
   * Finds the index of the specified Web Element.
   * 
   * @param driver
   *          Web Driver
   * @param by
   *          Css Selector
   * @param text
   *          Text to search the Web Element Collection
   * @param throwException
   *          optional assert parameter - throws an exception if no element is found
   * @return Returns the index of a Web Element
   */
  public static int findIndexOfElementWithText(WebDriver driver, By by, String text,
      boolean throwException) {
    // return -1 if index not found.. assert a fail if true
    List<WebElement> elementList = elemList(driver, by, throwException);
    int index = -1;

    if (elementList == null || (elementList.size() == 0)) {
      return index;
    }

    for (int i = 0; i < elementList.size(); i++) {
      if (elementList.get(i).toString().equals(text)) {
        return index = i;
      }
    }

    return index;
  }

  /**
   * Finds the index of the specified Web Element.
   * 
   * @param driver
   *          Web Driver
   * @param by
   *          Css Selector
   * @param text
   *          Text to search the Web Element Collection
   * @return Returns the index of a Web Element
   */
  public static int findIndexOfElementWithText(WebDriver driver, By by, String text) {
    return findIndexOfElementWithText(driver, by, text, true);
  }

  /**
   * Find the Index of the Specified Web Element Collection.
   * 
   * @param list
   *          ICollection of Web Elements
   * @param text
   *          Text to search the Web Element Collection
   * @param throwException
   *          optional assert parameter - throws an exception if no element is found
   * @return Returns the index of the Web Element in the inputted WebElement Collection
   */
  public static int findIndexOfElementWithText(List<WebElement> list, String text,
      boolean throwException) {
    int index = -1;

    // if list size was null or empty and assert was true
    if ((list == null || list.size() == 0) && throwException == true) {
      throw new NotFoundException(StringProcessor
          .safeFormatter("Empty or null Element Collection passed in %s", list.toString()));
    }

    // if throwException was true and list size > 0
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).toString().equals(text)) {
        list.get(i);
        return index = i;
      }
    }

    // if throwException is == false and no match was found
    if (throwException == false) {
      return index;
    }

    // if throwException is == true and no match was found
    throw new NotFoundException(StringProcessor
        .safeFormatter("Text did not match any element in collection %s", list.toString()));
  }

  /**
   * Find the Index of the Specified Web Element Collection.
   * 
   * @param list
   *          ICollection of Web Elements
   * @param text
   *          Text to search the Web Element Collection
   * @return Returns the index of the Web Element in the inputted WebElement Collection
   */
  public static int findIndexOfElementWithText(List<WebElement> list, String text) {
    return findIndexOfElementWithText(list, text, true);

  }

  /**
   * Function to get the Web Collection.
   * 
   * @param driver
   *          Web Driver
   * @param by
   *          Css Selector
   * @param throwException
   *          optional assert parameter - throws an exception if no element is found
   * @return Returns a Web Element Collection
   */
  private static List<WebElement> elemList(WebDriver driver, By by, boolean throwException) {
    List<WebElement> elems = driver.findElements(by);

    if (elems.size() > 0 || throwException == false) {
      return elems;
    }

    throw new NotFoundException(
        StringProcessor.safeFormatter("No result found for By %s", by.toString()));
  }

}
