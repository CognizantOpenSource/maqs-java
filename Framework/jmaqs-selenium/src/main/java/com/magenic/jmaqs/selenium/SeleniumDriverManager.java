/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.base.BaseTestObject;
import com.magenic.jmaqs.base.DriverManager;
import com.magenic.jmaqs.utilities.helper.StringProcessor;
import com.magenic.jmaqs.utilities.logging.Logger;
import com.magenic.jmaqs.utilities.logging.LoggingConfig;
import com.magenic.jmaqs.utilities.logging.LoggingEnabled;
import com.magenic.jmaqs.utilities.logging.MessageType;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.events.*;

import java.lang.reflect.Array;

public class SeleniumDriverManager extends DriverManager {
  /**
   * Initializes a new instance of the <see cref="SeleniumDriverManager"/> class
   * @param webDriver Function for getting an Selenium web driver
   * @param testObject The associated test object
   */
  public SeleniumDriverManager(WebDriver webDriver, BaseTestObject testObject) {
    super(webDriver, testObject);
  }

  public void close() {
  }

  private WebDriver webDriver;

  /**
   *
   */
  private BaseTestObject testObject;

  /**
   * Get the web driver
   * @return The web driver
   */
  public WebDriver getWebDriver() {
    WebDriver tempDriver;

    if (!this.isDriverInitialized() &&
        LoggingConfig.getLoggingEnabledSetting() != LoggingEnabled.NO) {
      //tempDriver = getDriver();
      tempDriver = (WebDriver) getDriver;
      tempDriver = new EventFiringWebDriver(tempDriver);
      //this.MapEvents(tempDriver as EventFiringWebDriver);
      this.mapEvents((EventFiringWebDriver)tempDriver);
      this.baseDriver = tempDriver;

      // Log the setup
      this.loggingStartup(tempDriver);
    }

    //return getBase() as WebDriver;
    return (WebDriver) getBase();
  }

  /**
   * Get the web driver
   * @return The web driver
   */
  public Object get() {
    return this.getWebDriver();
  }

  /**
   * Log a verbose message and include the automation specific call stack data
   * @param message The message text
   * @param args String format arguments
   */
  protected void logVerbose(String message, Object[] args) {
    StringBuilder messages = new StringBuilder();
    messages.append(StringProcessor.safeFormatter(message, args));

    Object methodInfo = Object[].class.getEnclosingMethod();
    String fullName = methodInfo.getClass().getTypeName() + "." + methodInfo.getClass().getName();

    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

    // for (String stackLevel : Environment.StackTrace.Split(new String[] { Environment.NewLine }, StringSplitOptions.None)) {
    // for (String stackLevel : stackTrace) {
    for (int i =0; i <= stackTrace.length; i++) {
      String trimmed = stackTrace[i].toString().trim();
      if (!trimmed.startsWith("at Microsoft.") &&
              !trimmed.startsWith("at System.") &&
              !trimmed.startsWith("at NUnit.") &&
              !trimmed.startsWith("at " + fullName)) {
        messages.append(stackTrace[i].toString().trim());
      }
    }

    Logger.logMessage(MessageType.VERBOSE, messages.toString());
  }

  /**
   * Have the driver cleanup after itself
   */
  protected void driverDispose() {
    Logger.logMessage(MessageType.VERBOSE, "Start dispose driver");

    // If we never created the driver we don't have any cleanup to do
    if (!this.isDriverInitialized()) {
      return;
    }

    try {
      WebDriver driver = this.getWebDriver();
      driver.quit();
    } catch (Exception e) {
      Logger.logMessage(MessageType.ERROR, StringProcessor.safeFormatter("Failed to close web driver because: {0}", e.getMessage()));
    }

    this.baseDriver = null;
    Logger.logMessage(MessageType.VERBOSE, "End dispose driver");
  }

  /**
   * Log that the web driver setup
   * @param webDriver The web driver
   */
  private void loggingStartup(WebDriver webDriver) {
    try {
      WebDriver driver = Extend.getLowLevelDriver(webDriver);
      String browserType;

      // Get info on what type of browser we are using
      RemoteWebDriver asRemoteDrive = (RemoteWebDriver) driver;

      if (asRemoteDrive != null) {
        browserType = asRemoteDrive.getCapabilities().toString();
      } else {
        browserType = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
      }

      //if (SeleniumConfig.getBrowserName().equals("Remote", StringComparison.CurrentCultureIgnoreCase))
      if (SeleniumConfig.getBrowserName().equalsIgnoreCase("Remote")) {
        Logger.logMessage(MessageType.INFORMATION, "Remote driver: {0}" + browserType);
      } else {
        Logger.logMessage(MessageType.INFORMATION, "Local driver: " + browserType);
      }

      webDriver.manage().timeouts().wait(SeleniumConfig.getWaitDriver(webDriver));
      //webDriver.setWaitDriver(SeleniumConfig.getWaitDriver(webDriver));
    } catch (Exception e) {
      Logger.logMessage(MessageType.ERROR, "Failed to start driver because: {0}", e.getMessage());
      System.out.println(StringProcessor.safeFormatter("Failed to start driver because: {0}", e.getMessage()));
    }
  }

  /**
   * Map selenium events to log events
   * @param eventFiringDriver The event firing web driver that we want mapped
   */
  // TODO connect to Event Handler
  private void mapEvents(EventFiringWebDriver eventFiringDriver) {
    /*
    LoggingEnabled enbled = LoggingConfig.getLoggingEnabledSetting();

    if (enbled == LoggingEnabled.YES || enbled == LoggingEnabled.ONFAIL)
    {
      eventFiringDriver.ElementClicked += this.WebDriver_ElementClicked;
      eventFiringDriver.ElementClicking += this.WebDriver_ElementClicking;
      eventFiringDriver.ElementValueChanged += this.WebDriver_ElementValueChanged;
      eventFiringDriver.ElementValueChanging += this.WebDriver_ElementValueChanging;
      eventFiringDriver.FindElementCompleted += this.WebDriver_FindElementCompleted;
      eventFiringDriver.FindingElement += this.WebDriver_FindingElement;
      eventFiringDriver.ScriptExecuted += this.WebDriver_ScriptExecuted;
      eventFiringDriver.ScriptExecuting += this.WebDriver_ScriptExecuting;
      eventFiringDriver.Navigated += this.WebDriver_Navigated;
      eventFiringDriver.Navigating += this.WebDriver_Navigating;
      eventFiringDriver.NavigatedBack += this.WebDriver_NavigatedBack;
      eventFiringDriver.NavigatedForward += this.WebDriver_NavigatedForward;
      eventFiringDriver.NavigatingBack += this.WebDriver_NavigatingBack;
      eventFiringDriver.NavigatingForward += this.WebDriver_NavigatingForward;
      eventFiringDriver.ExceptionThrown += this.WebDriver_ExceptionThrown;
    }*/
  }
/*
  /// <summary>
  /// Event for webdriver that is navigating forward
  /// </summary>
  /// <param name="sender">Sender object</param>
  /// <param name="e">Event object</param>
  private void WebDriver_NavigatingForward(Object sender, WebDriverNavigationEventArgs e)
  {
    this.LogVerbose("Navigating to: {0}", e.Url);
  }

  /// <summary>
  /// Event for webdriver that is navigating back
  /// </summary>
  /// <param name="sender">Sender object</param>
  /// <param name="e">Event object</param>
  private void WebDriver_NavigatingBack(Object sender, WebDriverNavigationEventArgs e)
  {
    this.LogVerbose("Navigating back to: {0}", e.Url);
  }

  /// <summary>
  /// Event for webdriver that has navigated forward
  /// </summary>
  /// <param name="sender">Sender object</param>
  /// <param name="e">Event object</param>
  private void WebDriver_NavigatedForward(Object sender, WebDriverNavigationEventArgs e)
  {
    Logger.logMessage(MessageType.INFORMATION, "Navigated Forward: {0}", e.Url);
  }

  /// <summary>
  /// Event for webdriver that is navigated back
  /// </summary>
  /// <param name="sender">Sender object</param>
  /// <param name="e">Event object</param>
  private void WebDriver_NavigatedBack(Object sender, WebDriverNavigationEventArgs e)
  {
    Logger.logMessage(MessageType.INFORMATION, "Navigated back: {0}", e.Url);
  }

  /// <summary>
  /// Event for webdriver that is navigating
  /// </summary>
  /// <param name="sender">Sender object</param>
  /// <param name="e">Event object</param>
  private void WebDriver_Navigating(Object sender, WebDriverNavigationEventArgs e)
  {
    this.LogVerbose("Navigating to: {0}", e.Url);
  }

  /// <summary>
  /// Event for webdriver that is script executing
  /// </summary>
  /// <param name="sender">Sender object</param>
  /// <param name="e">Event object</param>
  private void WebDriver_ScriptExecuting(Object sender, WebDriverScriptEventArgs e)
  {
    this.LogVerbose("Script executing: {0}", e.Script);
  }

  /// <summary>
  /// Event for webdriver that is finding an element
  /// </summary>
  /// <param name="sender">Sender object</param>
  /// <param name="e">Event object</param>
  private void WebDriver_FindingElement(Object sender, FindElementEventArgs e)
  {
    this.LogVerbose("Finding element: {0}", e.FindMethod);
  }

  /// <summary>
  /// Event for webdriver that is changing an element value
  /// </summary>
  /// <param name="sender">Sender object</param>
  /// <param name="e">Event object</param>
  private void WebDriver_ElementValueChanging(Object sender, WebElementEventArgs e)
  {
    this.LogVerbose("Value of element changing: {0}", e.Element);
  }

  /// <summary>
  /// Event for webdriver that is clicking an element
  /// </summary>
  /// <param name="sender">Sender object</param>
  /// <param name="e">Event object</param>
  private void WebDriver_ElementClicking(Object sender, WebElementEventArgs e)
  {
    Logger.logMessage(MessageType.INFORMATION, "Element clicking: {0} Text:{1} Location: X:{2} Y:{3}", e.Element, e.Element.Text, e.Element.Location.X, e.Element.Location.Y);
  }

  /// <summary>
  /// Event for webdriver when an exception is thrown
  /// </summary>
  /// <param name="sender">Sender object</param>
  /// <param name="e">Event object</param>
  private void WebDriver_ExceptionThrown(Object sender, WebDriverExceptionEventArgs e)
  {
    // First chance handler catches these when it is a real error - These are typically retry loops
    Logger.logMessage(MessageType.VERBOSE, "Exception thrown: {0}", e.ThrownException);
  }

  /// <summary>
  /// Event for webdriver that has navigated
  /// </summary>
  /// <param name="sender">Sender object</param>
  /// <param name="e">Event object</param>
  private void WebDriver_Navigated(Object sender, WebDriverNavigationEventArgs e)
  {
    Logger.logMessage(MessageType.INFORMATION, "Navigated to: {0}", e.Url);
  }

  /// <summary>
  /// Event for webdriver has executed a script
  /// </summary>
  /// <param name="sender">Sender object</param>
  /// <param name="e">Event object</param>
  private void WebDriver_ScriptExecuted(Object sender, WebDriverScriptEventArgs e)
  {
    Logger.logMessage(MessageType.INFORMATION, "Script executed: {0}", e.Script);
  }

  /// <summary>
  /// Event for webdriver that is finished finding an element
  /// </summary>
  /// <param name="sender">Sender object</param>
  /// <param name="e">Event object</param>
  private void WebDriver_FindElementCompleted(Object sender, FindElementEventArgs e)
  {
    Logger.logMessage(MessageType.INFORMATION, "Found element: {0}", e.FindMethod);
  }

  /// <summary>
  /// Event for webdriver that has changed an element value
  /// </summary>
  /// <param name="sender">Sender object</param>
  /// <param name="e">Event object</param>
  private void WebDriver_ElementValueChanged(Object sender, WebElementEventArgs e)
  {
    String element = e.Element.GetAttribute("value");
    Logger.logMessage(MessageType.INFORMATION, "Element value changed: {0}", element);
  }

  /// <summary>
  /// Event for webdriver that has clicked an element
  /// </summary>
  /// <param name="sender">Sender object</param>
  /// <param name="e">Event object</param>
  private void WebDriver_ElementClicked(Object sender, WebElementEventArgs e)
  {
    try
    {
      this.LogVerbose("Element clicked: {0} Text:{1} Location: X:{2} Y:{3}", e.element, e.Element.Text, e.Element.Location.X, e.Element.Location.Y);
    }
    catch
    {
      this.LogVerbose("Element clicked");
    }

  } */
}
