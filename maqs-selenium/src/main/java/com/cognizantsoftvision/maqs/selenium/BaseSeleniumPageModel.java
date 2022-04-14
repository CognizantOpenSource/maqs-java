/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium;

import com.cognizantsoftvision.maqs.selenium.factories.UIWaitFactory;
import com.cognizantsoftvision.maqs.utilities.logging.ILogger;
import com.cognizantsoftvision.maqs.utilities.performance.IPerfTimerCollection;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * The Base Selenium Page Model class.
 */
public abstract class BaseSeleniumPageModel {

  /**
   * The lazy Element store.
   */
  private final HashMap<String, LazyWebElement> lazyElementStore;

  /**
   * The selenium test object.
   */
  private final ISeleniumTestObject testObject;

  /**
   * The web driver.
   */
  private WebDriver webDriver;

  /**
   * Instantiates a new Base selenium page model.
   *
   * @param testObject the test object
   */
  protected BaseSeleniumPageModel(ISeleniumTestObject testObject) {
    this.testObject = testObject;
    this.webDriver = testObject.getWebDriver();
    this.lazyElementStore = new HashMap<>();
  }

  /**
   * Gets logger.
   *
   * @return the logger
   */
  protected ILogger getLogger() {
    return this.testObject.getLogger();
  }

  /**
   * Gets the lazy element store.
   *
   * @return The lazy element store
   */
  protected HashMap<String, LazyWebElement> getLazyElementStore() {
    return this.lazyElementStore;
  }

  /**
   * Gets test object.
   *
   * @return the test object
   */
  public ISeleniumTestObject getTestObject() {
    return testObject;
  }

  /**
   * Gets web driver.
   *
   * @return the web driver
   */
  public WebDriver getWebDriver() {
    return webDriver;
  }

  /**
   * Sets web driver.
   *
   * @param webDriver the web driver
   */
  public void setWebDriver(WebDriver webDriver) {
    this.webDriver = webDriver;
  }

  /**
   * Gets the per timer collection.
   *
   * @return The perf timer collection
   */
  public IPerfTimerCollection getPerfTimerCollection() {
    return this.testObject.getPerfTimerCollection();
  }

  /**
   * Is page loaded boolean.
   *
   * @return the boolean
   */
  public abstract boolean isPageLoaded();

  /**
   * Waits for the page to load.
   */
  public void waitForPageLoad() {
    UIWaitFactory.getWaitDriver(getWebDriver()).waitForPageLoad();
  }

  /**
   * Gets lazy element.
   *
   * @param parent the parent
   * @param by     the by
   * @return the lazy element
   */
  protected LazyWebElement getLazyElement(LazyWebElement parent, By by) {
    return getLazyElement(parent, by, "");
  }

  /**
   * Gets lazy element.
   *
   * @param parent           the parent
   * @param by               the by
   * @param userFriendlyName the user-friendly name
   * @return the lazy element
   */
  protected LazyWebElement getLazyElement(LazyWebElement parent, By by, String userFriendlyName) {
    String lazyElementStoreKey = by.toString() + userFriendlyName;
    this.lazyElementStore.putIfAbsent(lazyElementStoreKey, new LazyWebElement(parent, by, userFriendlyName));
    return this.lazyElementStore.get(lazyElementStoreKey);
  }

  /**
   * Gets lazy element.
   *
   * @param by the by
   * @return the lazy element
   */
  protected LazyWebElement getLazyElement(By by) {
    return getLazyElement(by, "");
  }

  /**
   * Gets lazy element.
   *
   * @param by               the by
   * @param userFriendlyName the user-friendly name
   * @return the lazy element
   */
  protected LazyWebElement getLazyElement(By by, String userFriendlyName) {
    String lazyElementStoreKey = by.toString() + userFriendlyName;
    this.lazyElementStore.putIfAbsent(lazyElementStoreKey, new LazyWebElement(this.testObject, by, userFriendlyName));
    return this.lazyElementStore.get(lazyElementStoreKey);
  }
}