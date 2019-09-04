/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Contains methods for interactions using selenium Actions class.
 */
public class ActionBuilder {

  /**
   * Performs a hover over on an element.
   * @param seleniumWait The seleniumWait
   * @param by By selector for the element
   */
  public static void hoverOver(SeleniumWait seleniumWait, By by) {
    Actions builder = new Actions(seleniumWait.getWebDriver());
    WebElement element = seleniumWait.waitForClickableElement(by);
    builder.moveToElement(element).build().perform();
  }

  /**
   * Press non alphanumeric key. Ex. Control, Home, etc.
   * @param seleniumWait The seleniumWait
   * @param key The key to press. NOTE: Use the Keys class
   */
  public static void pressModifierKey(SeleniumWait seleniumWait, CharSequence... key) {
    Actions builder = new Actions(seleniumWait.getWebDriver());
    builder.sendKeys(key).build().perform();
  }

  /**
   * Slider method which will take an offset of X pixels.
   * @param seleniumWait The seleniumWait
   * @param by By selector for the element
   * @param pixelsOffset Integer of pixels to be moved (Positive or negative)
   */
  public static void slideElement(SeleniumWait seleniumWait, By by, int pixelsOffset) {
    Actions builder = new Actions(seleniumWait.getWebDriver());
    builder.dragAndDropBy(seleniumWait.getWebDriver().findElement(by), pixelsOffset, 0).build().perform();
  }

  /**
   * Performs a right-click on an element.
   * @param seleniumWait The seleniumWait
   * @param by By selector for the element
   */
  public static void rightClick(SeleniumWait seleniumWait, By by) {
    Actions builder = new Actions(seleniumWait.getWebDriver());
    WebElement element = seleniumWait.waitForClickableElement(by);
    builder.contextClick(element).build().perform();
  }
}
