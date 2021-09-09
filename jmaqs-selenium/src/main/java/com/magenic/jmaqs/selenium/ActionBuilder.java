/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Contains methods for interactions using Selenium Actions class.
 */
public class ActionBuilder {

  /**
   * Private constructor.
   */
  private ActionBuilder() {
  }

  /**
   * Performs a hover over on an element.
   *
   * @param webDriver The web driver
   * @param by        By selector for the element
   */
  public static void hoverOver(WebDriver webDriver, By by) {
    WebElement element = UIWaitFactory.getWaitDriver(webDriver).waitForClickableElement(by);
    new Actions(webDriver).moveToElement(element).build().perform();
  }

  /**
   * Press non-alphanumeric key. Ex. Control, Home, etc.
   *
   * @param webDriver The web driver
   * @param key       The key to press. NOTE: Use the Keys class
   */
  public static void pressModifierKey(WebDriver webDriver, CharSequence... key) {
    new Actions(webDriver).sendKeys(key).build().perform();
  }

  /**
   * Slider method which will take an offset of X pixels.
   *
   * @param webDriver    The web driver
   * @param by           By selector for the element
   * @param pixelsOffset Integer of pixels to be moved (Positive or negative)
   */
  public static void slideElement(WebDriver webDriver, By by, int pixelsOffset) {
    new Actions(webDriver).dragAndDropBy(UIWaitFactory.getWaitDriver(webDriver).waitForClickableElement(by),
        pixelsOffset, 0).build().perform();
  }

  /**
   * Performs a right-click on an element.
   *
   * @param webDriver The web driver
   * @param by        By selector for the element
   */
  public static void rightClick(WebDriver webDriver, By by) {
    WebElement element = UIWaitFactory.getWaitDriver(webDriver).waitForClickableElement(by);
    new Actions(webDriver).contextClick(element).build().perform();
  }
}