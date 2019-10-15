/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.base.BaseTestObject;
import com.magenic.jmaqs.base.DriverManager;
import com.magenic.jmaqs.base.TestObject;
import com.magenic.jmaqs.utilities.helper.StringProcessor;
import com.magenic.jmaqs.utilities.logging.Logger;
import com.magenic.jmaqs.utilities.logging.LoggingConfig;
import com.magenic.jmaqs.utilities.logging.LoggingEnabled;
import com.magenic.jmaqs.utilities.logging.MessageType;
import jdk.internal.jline.internal.Log;
import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.events.*;

import java.sql.Driver;
import java.util.function.Supplier;

public class SeleniumDriverManager extends DriverManager {
  /// <summary>
  /// Initializes a new instance of the <see cref="SeleniumDriverManager"/> class
  /// </summary>
  /// <param name="getDriver">Function for getting an Selenium web driver</param>
  /// <param name="testObject">The associated test object</param>
  public SeleniumDriverManager(Supplier<Object> getDriver, BaseTestObject testObject) {
    super(getDriver, testObject);
  }

  public void close() {

  }

  private WebDriver webDriver;

  /**
   *
   */
  private BaseTestObject testObject;

  /// <summary>
  /// Get the web driver
  /// </summary>
  /// <returns>The web driver</returns>
  public WebDriver GetWebDriver()
  {
    WebDriver tempDriver;

    if (!this.isDriverInitialized() &&
        LoggingConfig.getLoggingEnabledSetting() != LoggingEnabled.NO) {
      //tempDriver = getDriver();
      tempDriver = (WebDriver) getDriver;
      tempDriver = new EventFiringWebDriver(tempDriver);
      //this.MapEvents(tempDriver as EventFiringWebDriver);
      this.MapEvents((EventFiringWebDriver)tempDriver);
      this.baseDriver = tempDriver;

      // Log the setup
      this.LoggingStartup(tempDriver);
    }

    //return getBase() as WebDriver;
    return (WebDriver) getBase();
  }

  /// <summary>
  /// Get the web driver
  /// </summary>
  /// <returns>The web driver</returns>
  // @Override
  public Object Get() {
    return this.GetWebDriver();
  }

  /// <summary>
  /// Log a verbose message and include the automation specific call stack data
  /// </summary>
  /// <param name="message">The message text</param>
  /// <param name="args">String format arguments</param>
  /*protected void LogVerbose(String message, Object[] args)
  {
    StringBuilder messages = new StringBuilder();
    messages.append(StringProcessor.safeFormatter(message, args));

    //var methodInfo = MethodBase.GetCurrentMethod();
    String methodInfo = new Throwable().getStackTrace()[0].getMethodName();
    //var fullName = methodInfo.DeclaringType.FullName + "." + methodInfo.Name;


    for (String stackLevel ; Environment.StackTrace.Split(new String[] { Environment.NewLine }, StringSplitOptions.None))
    {
      String trimmed = stackLevel.Trim();
      if (!trimmed.startsWith("at Microsoft.") && !trimmed.startsWith("at System.") && !trimmed.startsWith("at NUnit.") && !trimmed.startsWith("at " + fullName))
      {
        messages.append(stackLevel);
      }
    }

    Logger.logMessage(MessageType.VERBOSE, messages.toString());
  } */

  /// <summary>
  /// Have the driver cleanup after itself
  /// </summary>
  // @Override
  protected void DriverDispose()
  {
    Logger.logMessage(MessageType.VERBOSE, "Start dispose driver");

    // If we never created the driver we don't have any cleanup to do
    if (!this.isDriverInitialized())
    {
      return;
    }

    try
    {
      WebDriver driver = this.GetWebDriver();
      driver.close();
    }
    catch (Exception e)
    {
      Logger.logMessage(MessageType.ERROR, StringProcessor.safeFormatter("Failed to close web driver because: {0}", e.getMessage()));
    }

    this.baseDriver = null;
    Logger.logMessage(MessageType.VERBOSE, "End dispose driver");
  }

  /// <summary>
  /// Log that the web driver setup
  /// </summary>
  /// <param name="webDriver">The web driver</param>
  private void LoggingStartup(WebDriver webDriver)
  {
    try
    {
      WebDriver driver = Extend.GetLowLevelDriver(webDriver);
      String browserType;

      // Get info on what type of browser we are using
      RemoteWebDriver asRemoteDrive = (RemoteWebDriver) driver;

      if (asRemoteDrive != null)
      {
        browserType = asRemoteDrive.getCapabilities().toString();
      }
      else
      {
        browserType = driver.getClass().toString();
      }

      //if (SeleniumConfig.getBrowserName().equals("Remote", StringComparison.CurrentCultureIgnoreCase))
      if(SeleniumConfig.getBrowserName().equalsIgnoreCase("Remote"))
      {
        Logger.logMessage(MessageType.INFORMATION, "Remote driver: {0}" + browserType);
      }
      else
      {
        Logger.logMessage(MessageType.INFORMATION, "Local driver: " + browserType);
      }

      webDriver.SetWaitDriver(SeleniumConfig.getWaitDriver(webDriver));
    }
    catch (Exception e)
    {
      Logger.logMessage(MessageType.ERROR, "Failed to start driver because: {0}", e.getMessage());
      System.out.println(StringProcessor.safeFormatter("Failed to start driver because: {0}", e.getMessage()));
    }
  }

  /// <summary>
  /// Map selenium events to log events
  /// </summary>
  /// <param name="eventFiringDriver">The event firing web driver that we want mapped</param>
  // TODO connect to Event Handler
  private void MapEvents(EventFiringWebDriver eventFiringDriver)
  {/*
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
