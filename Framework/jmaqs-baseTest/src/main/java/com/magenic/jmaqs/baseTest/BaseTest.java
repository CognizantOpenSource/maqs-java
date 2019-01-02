/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.baseTest;

import com.magenic.jmaqs.utilities.helper.StringProcessor;
import com.magenic.jmaqs.utilities.logging.ConsoleLogger;
import com.magenic.jmaqs.utilities.logging.FileLogger;
import com.magenic.jmaqs.utilities.logging.Logger;
import com.magenic.jmaqs.utilities.logging.LoggingConfig;
import com.magenic.jmaqs.utilities.logging.LoggingEnabled;
import com.magenic.jmaqs.utilities.logging.MessageType;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Base test class.
 */
public abstract class BaseTest {

  /**
   * All logged exceptions caught and saved to be thrown later.
   */
  protected ConcurrentHashMap<String, ArrayList<String>> loggedExceptions;

  /**
   * Logging Enabled Setting from Config file.
   */
  protected LoggingEnabled loggingEnabledSetting;

  /**
   * The test result object.
   */
  private ITestResult testResult;

  /**
   * Thread local storage of TestObject.
   */
  private ThreadLocal<TestObject> testObject = new ThreadLocal<TestObject>();

  /**
   * Initializes a new instance of the BaseTest class.
   */
  public BaseTest() {
    loggedExceptions = new ConcurrentHashMap<String, ArrayList<String>>();
  }

  /**
   * Gets the Logger for this test.
   * 
   * @return Logger object
   */
  public Logger getLogger() {
    return this.testObject.get().getLogger();
  }

  /**
   * Set the Logger for this test.
   * 
   * @param log
   *          The Logger object
   */
  public void setLogger(Logger log) {
    testObject.get().setLogger(log);
  }

  /**
   * Gets the Logging Enabled setting.
   * 
   * @return Logging Enabled setting
   */
  public LoggingEnabled getLoggingEnabledSetting() {
    return this.loggingEnabledSetting;
  }

  /**
   * Set the Logging Enabled setting.
   * 
   * @param setting
   *          The LoggingEnabled enum
   */
  public void setLoggingEnabled(LoggingEnabled setting) {
    this.loggingEnabledSetting = setting;
  }

  /**
   * Get logged exceptions for this test.
   * 
   * @return ArrayList of logged exceptions for this test
   */
  public ArrayList<String> getLoggedExceptions() {
    if (!this.loggedExceptions.containsKey(this.getFullyQualifiedTestClassName())) {
      return new ArrayList<String>();
    } else {
      return this.loggedExceptions.get(this.getFullyQualifiedTestClassName());
    }
  }

  public void setLoggedException(ArrayList<String> exceptions) {
    this.loggedExceptions.put(this.getFullyQualifiedTestClassName(), exceptions);
  }

  /**
   * Get the TestObject for this test.
   * 
   * @return The TestObject
   */
  public TestObject getTestObject() {
    return this.testObject.get();
  }

  /**
   * Setup before a test.
   * 
   * @param method
   *          The initial executing Method object
   * @throws Exception
   *           Throws exception if get logger fails
   */
  @BeforeMethod
  public void setup(Method method) throws Exception {

    String testName = method.getDeclaringClass() + "." + method.getName();
    testName = testName.replaceFirst("class ", "");

    this.testObject.set(new TestObject(testName));
    this.testObject.get().setLogger(this.setupLogging());

    this.postSetupLogging();
  }

  /**
   * Cleanup after a test.
   */
  @AfterMethod
  public void teardown() {
    try {
      this.beforeLoggingTeardown(testResult);
    } catch (Exception e) {
      this.tryToLog(MessageType.WARNING, "Failed before logging teardown because: %s",
          e.getMessage());
    }

    // Log the test result
    if (testResult.getStatus() == ITestResult.SUCCESS) {
      this.tryToLog(MessageType.SUCCESS, "Test Passed");
    } else if (testResult.getStatus() == ITestResult.FAILURE) {
      this.tryToLog(MessageType.ERROR, "Test Passed");
    } else if (testResult.getStatus() == ITestResult.SKIP) {
      this.tryToLog(MessageType.INFORMATION, "Test was skipped");
    } else {
      this.tryToLog(MessageType.WARNING, "Test had an unexpected result.");
    }

    // Cleanup log files we don't want
    try {
      if ((this.getLogger() instanceof FileLogger) && testResult.getStatus() == ITestResult.SUCCESS
          && this.loggingEnabledSetting == LoggingEnabled.ONFAIL) {
        Files.delete(Paths.get(((FileLogger) this.getLogger()).getFilePath()));
      }
    } catch (Exception e) {
      this.tryToLog(MessageType.WARNING, "Failed to cleanup log files because: %s", e.getMessage());
    }

    // Release logged messages
    this.loggedExceptions.remove(this.getFullyQualifiedTestClassName());
  }

  /**
   * Set the test result after each test execution.
   * 
   * @param testResult
   *          The result object
   */
  @AfterMethod
  public void setTestResult(ITestResult testResult) {
    this.testResult = testResult;
  }

  /**
   * Overload function for doing post setup logging.
   */
  protected abstract void postSetupLogging() throws Exception;

  /**
   * Steps to do before logging teardown results.
   * 
   * @param resultType
   *          The test result
   */
  protected abstract void beforeLoggingTeardown(ITestResult resultType);

  /**
   * Setup logging data.
   * 
   * @return Logger
   */
  protected Logger setupLogging() {
    Logger log;

    this.loggingEnabledSetting = LoggingConfig.getLoggingEnabledSetting();

    if (this.loggingEnabledSetting != LoggingEnabled.NO) {
      log = LoggingConfig
          .getLogger(StringProcessor.safeFormatter("%s - %s", this.getFullyQualifiedTestClassName(),
              DateTimeFormatter.ofPattern("uuuu-MM-dd-HH-mm-ss-SSSS", Locale.getDefault())
                  .format(LocalDateTime.now(Clock.systemUTC()))));
    } else {
      log = new ConsoleLogger();
    }

    return log;
  }

  /**
   * Get the fully qualified test name.
   * 
   * @return The test name including class
   */
  protected String getFullyQualifiedTestClassName() {
    return this.testObject.get().getFullyQualifiedTestName();
  }

  /**
   * Try to log a message - Do not fail if the message is not logged.
   * 
   * @param messageType
   *          The type of message
   * @param message
   *          The message text
   * @param args
   *          String format arguments
   */
  protected void tryToLog(MessageType messageType, String message, Object... args) {
    // Get the formatted message
    String formattedMessage = StringProcessor.safeFormatter(message, args);

    try {
      // Write to the log
      this.getLogger().logMessage(messageType, formattedMessage);

      // If this was an error and written to a file, add it to the console
      // output as well
      if (messageType == MessageType.ERROR && this.getLogger() instanceof FileLogger) {
        System.out.println(formattedMessage);
      }
    } catch (Exception e) {
      System.out.println(formattedMessage);
      System.out.println("Logging failed because: " + e.getMessage());
    }
  }
}
