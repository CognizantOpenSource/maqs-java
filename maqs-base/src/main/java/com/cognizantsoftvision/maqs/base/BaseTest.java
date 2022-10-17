/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.base;

import static java.lang.System.out;

import com.cognizantsoftvision.maqs.base.exceptions.MAQSRuntimeException;
import com.cognizantsoftvision.maqs.utilities.helper.StringProcessor;
import com.cognizantsoftvision.maqs.utilities.logging.ConsoleLogger;
import com.cognizantsoftvision.maqs.utilities.logging.FileLogger;
import com.cognizantsoftvision.maqs.utilities.logging.ILogger;
import com.cognizantsoftvision.maqs.utilities.logging.LoggerFactory;
import com.cognizantsoftvision.maqs.utilities.logging.LoggingConfig;
import com.cognizantsoftvision.maqs.utilities.logging.LoggingEnabled;
import com.cognizantsoftvision.maqs.utilities.logging.MessageType;
import com.cognizantsoftvision.maqs.utilities.performance.IPerfTimerCollection;
import com.cognizantsoftvision.maqs.utilities.performance.PerfTimerCollection;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.testng.ITestContext;
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
   * The Collection of Base Test Objects to use.
   */
  ConcurrentManagerHashMap baseTestObjects;

  /**
   * The Performance Timer Collection.
   */
  private IPerfTimerCollection perfTimerCollection;

  /**
   * The TestNG Test Context.
   */
  private ITestContext testContextInstance;

  /**
   * The Fully Qualified Test Class Name.
   */
  ThreadLocal<String> fullyQualifiedTestClassName = new ThreadLocal<>();

  /**
   * Initializes a new instance of the BaseTest class.
   */
  protected BaseTest() {
    this.loggedExceptions = new ConcurrentHashMap<>();
    this.baseTestObjects = new ConcurrentManagerHashMap();
  }

  /**
   * Gets the Performance Timer Collection.
   *
   * @return Performance Timer Collection
   */
  public IPerfTimerCollection getPerfTimerCollection() {
    return this.perfTimerCollection;
  }

  /**
   * Sets the Performance Timer Collection.
   *
   * @param perfTimerCollection Performance Timer Collection to use
   */
  public void setPerfTimerCollection(PerfTimerCollection perfTimerCollection) {
    this.perfTimerCollection = perfTimerCollection;
  }

  /**
   * Gets the Logger for this test.
   *
   * @return Logger object
   */
  public ILogger getLogger() {
    return this.getTestObject().getLogger();
  }

  /**
   * Set the Logger for this test.
   *
   * @param log The Logger object
   */
  public void setLogger(ILogger log) {
    this.getTestObject().setLogger(log);
  }

  /**
   * Gets the Logging Enabled setting.
   *
   * @return Logging Enabled setting
   */
  public LoggingEnabled getLoggingEnabled() {
    return this.loggingEnabledSetting;
  }

  /**
   * Set the Logging Enabled setting.
   *
   * @param setting The LoggingEnabled enum
   */
  protected void setLoggingEnabled(LoggingEnabled setting) {
    this.loggingEnabledSetting = setting;
  }

  /**
   * Get logged exceptions for this test.
   *
   * @return ArrayList of logged exceptions for this test
   */
  public List<String> getLoggedExceptions() {
    ArrayList<String> result;
    if (!this.loggedExceptions.containsKey(this.fullyQualifiedTestClassName.get())) {
      result = new ArrayList<>();
    } else {
      result = this.loggedExceptions.get(this.fullyQualifiedTestClassName.get());
    }
    return result;
  }

  /**
   * Set Logged Exception List - Add/Update entry in Hash Map with test class name as key.
   *
   * @param loggedExceptionList ArrayList of logged exceptions to use.
   */
  public void setLoggedExceptions(List<String> loggedExceptionList) {
    this.loggedExceptions.put(this.fullyQualifiedTestClassName.get(), (ArrayList<String>) loggedExceptionList);
  }

  /**
   * Gets the Driver Store.
   *
   * @return The Driver Store
   */
  public ManagerStore getManagerStore() {
    return this.getTestObject().getManagerStore();
  }

  /**
   * Gets the TestNG Test Context.
   *
   * @return The TestNG Test Context
   */
  public ITestContext getTestContext() {
    return this.testContextInstance;
  }

  /**
   * Sets the TestNG Test context.
   *
   * @param testContext The TestNG Test Context to use
   */
  public void setTestContext(ITestContext testContext) {
    this.testContextInstance = testContext;
  }

  /**
   * Get the BaseTestObject for this test.
   *
   * @return The BaseTestObject
   */
  public ITestObject getTestObject() {
    if (!this.baseTestObjects.containsKey(this.fullyQualifiedTestClassName.get())) {
      this.createNewTestObject();
    }

    return this.baseTestObjects.get(this.fullyQualifiedTestClassName.get());
  }

  /**
   * Sets the Test Object.
   *
   * @param baseTestObject The Base Test Object to use
   */
  public void setTestObject(BaseTestObject baseTestObject) {
    String key = this.fullyQualifiedTestClassName.get();
    if (this.baseTestObjects.containsKey(key)) {
      this.baseTestObjects.replace(key, baseTestObject);
    } else {
      this.baseTestObjects.put(key, baseTestObject);
    }
  }

  /**
   * Setup before a test.
   *
   * @param method      The initial executing Method object
   * @param testContext The initial executing Test Context object
   */
  @BeforeMethod(alwaysRun = true)
  public void setup(Method method, ITestContext testContext) {
    this.testContextInstance = testContext;

    // Get the Fully Qualified Test Class Name and set it in the object
    String testName = method.getDeclaringClass() + "." + method.getName();
    customSetup(testName, testContext);
  }

  /**
   * Setup before a test with custom name.
   *
   * @param testName    User provide name of test
   * @param testContext The initial executing Test Context object
   */
  public void customSetup(String testName, ITestContext testContext) {
    this.testContextInstance = testContext;

    testName = testName.replaceFirst("class ", "");
    this.fullyQualifiedTestClassName.set(testName);

    this.createNewTestObject();
  }

  /**
   * Cleanup after a test.
   */
  @AfterMethod(alwaysRun = true)
  public void teardown() {
    try {
      this.beforeLoggingTeardown(testResult);
    } catch (Exception e) {
      this.tryToLog(MessageType.WARNING, "Failed before logging teardown because: %s", e.getMessage());
    }

    // Log the test result
    if (testResult.getStatus() == ITestResult.SUCCESS) {
      this.tryToLog(MessageType.SUCCESS, "Test Passed");
    } else if (testResult.getStatus() == ITestResult.FAILURE) {
      if (this.getLoggingEnabled() == LoggingEnabled.YES && this.getLogger() instanceof FileLogger) {
        String stackTrace = ExceptionUtils.getStackTrace(testResult.getThrowable());
        this.tryToLog(MessageType.ERROR, stackTrace, "");
      }
      this.tryToLog(MessageType.ERROR, "Test Failed");
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

    // Get the Fully Qualified Test Name
    String fullyQualifiedTestName = this.fullyQualifiedTestClassName.get();

    try {
      ITestObject baseTestObject = this.getTestObject();
      // Release logged messages
      this.loggedExceptions.remove(fullyQualifiedTestName);

      // Release the Base Test Object
      this.baseTestObjects.remove(fullyQualifiedTestName, baseTestObject);
    } catch (Exception e) {
      throw new MAQSRuntimeException("there was an issue cleaning up", e);
    }

    // Create console logger to log subsequent messages
    this.setTestObject(new BaseTestObject(new ConsoleLogger(), fullyQualifiedTestName));
    this.fullyQualifiedTestClassName.remove();
  }

  /**
   * Set the test result after each test execution.
   *
   * @param testResult The result object
   */
  @AfterMethod(alwaysRun = true)
  public void setTestResult(ITestResult testResult) {
    this.testContextInstance = testResult.getTestContext();
    this.testResult = testResult;
  }

  /**
   * Steps to take before logging teardown results.
   *
   * @param resultType The test result
   */
  protected abstract void beforeLoggingTeardown(ITestResult resultType);

  /**
   * Setup logging data.
   *
   * @return Logger
   */
  protected ILogger createLogger() {
    this.loggingEnabledSetting = LoggingConfig.getLoggingEnabledSetting();
    this.setLoggedExceptions(new ArrayList<>());

    if (this.loggingEnabledSetting != LoggingEnabled.NO) {
      log = LoggingConfig.getLogger(
          StringProcessor.safeFormatter("%s - %s", this.fullyQualifiedTestClassName.get(),
              DateTimeFormatter.ofPattern(Logger.DEFAULT_DATE_TIME_FORMAT, Locale.getDefault()).format(LocalDateTime.now(Clock.systemUTC()))));
    } else {
      log = new ConsoleLogger();
      try {
        if (this.loggingEnabledSetting != LoggingEnabled.NO) {

          return LoggerFactory.getLogger(
              StringProcessor.safeFormatter("%s - %s", this.fullyQualifiedTestClassName.get(),
                  DateTimeFormatter.ofPattern("uuuu-MM-dd-HH-mm-ss-SSSS", Locale.getDefault()).format(LocalDateTime.now(Clock.systemUTC()))));
        } else {
          return LoggerFactory.getConsoleLogger();
        }
      } catch (Exception e) {
        ILogger newLogger = LoggerFactory.getConsoleLogger();
        newLogger.logMessage(MessageType.WARNING, "");
        return newLogger;
      }
    }
  }

  /**
   * Get the fully qualified test name.
   *
   * @return The test name including class
   */
  protected String getFullyQualifiedTestClassName() {
    return this.fullyQualifiedTestClassName.get();
  }

  /**
   * Try to log a message - Do not fail if the message is not logged.
   *
   * @param messageType The type of message
   * @param message     The message text
   * @param args        String format arguments
   */
  protected void tryToLog(MessageType messageType, String message, Object... args) {
    // Get the formatted message
    String formattedMessage = StringProcessor.safeFormatter(message, args);

    try {
      // Write to the log
      this.getLogger().logMessage(messageType, formattedMessage);

      // If this was an error and written to a file, add it to the console
      // output as well
      if (messageType == MessageType.ERROR && !(this.getLogger() instanceof ConsoleLogger)) {
        out.println(formattedMessage);
      }
    } catch (Exception e) {
      out.println(formattedMessage);
      out.println("Logging failed because: " + e.getMessage());
    }
  }

  /**
   * Log a verbose message and include the automation specific call stack data.
   *
   * @param message The message text
   * @param args    String format arguments
   */
  protected void logVerbose(String message, Object... args) {
    StringBuilder messages = new StringBuilder();
    messages.append(StringProcessor.safeFormatter(message, args)).append(System.lineSeparator());

    for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
      // If the stack trace element is from this package (excluding this method) append the stack trace line
      if (element.toString().startsWith("com.cognizantsoftvision")
          && !element.toString().contains("BaseTest.logVerbose")) {
        messages.append(element).append(System.lineSeparator());
      }
    }

    this.getLogger().logMessage(MessageType.VERBOSE, messages.toString());
  }

  /**
   * Create a Base test object.
   */
  protected void createNewTestObject() {
    ILogger newLogger = this.createLogger();
    this.setTestObject(new BaseTestObject(newLogger, this.fullyQualifiedTestClassName.get()));
  }
}
