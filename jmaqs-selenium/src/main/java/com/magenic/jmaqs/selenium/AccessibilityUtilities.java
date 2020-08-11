package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.utilities.logging.Logger;
import com.magenic.jmaqs.utilities.logging.MessageType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccessibilityUtilities {
  /// <summary>
  /// Create a HTML accessibility report for an entire web page
  /// </summary>
  /// <param name="webDriver">The WebDriver</param>
  /// <param name="testObject">The TestObject to associate the report with</param>
  /// <param name="throwOnViolation">Should violations cause and exception to be thrown</param>
  public static void createAccessibilityHtmlReport(WebDriver webDriver, SeleniumTestObject testObject,
      boolean throwOnViolation) {
    createAccessibilityHtmlReport(webDriver, testObject, () -> webDriver.Analyze(), throwOnViolation);
  }

  /// <summary>
  /// Create a HTML accessibility report for a specific web element and all of it's children
  /// </summary>
  /// <param name="webDriver">The WebDriver</param>
  /// <param name="testObject">The TestObject to associate the report with</param>
  /// <param name="element">The WebElement you want to use as the root for your accessibility scan</param>
  /// <param name="throwOnViolation">Should violations cause and exception to be thrown</param>
  public static void createAccessibilityHtmlReport(WebDriver webDriver, SeleniumTestObject testObject,
      WebElement element, boolean throwOnViolation) {
    // If we are using a lazy element go get the raw element instead
    LazyWebElement raw = (LazyWebElement) element;

    if (raw != null) {
      element = ((LazyWebElement)element).getRawExistingElement();
    }

    createAccessibilityHtmlReport(element, testObject, () -> webDriver.Analyze(element), throwOnViolation);
  }

  /// <summary>
  /// Create a HTML accessibility report
  /// </summary>
  /// <param name="context">The scan context, this is either a web driver or web element</param>
  /// <param name="testObject">The TestObject to associate the report with</param>
  /// <param name="getResults">Function for getting the accessibility scan results</param>
  /// <param name="throwOnViolation">Should violations cause and exception to be thrown</param>
  public static void createAccessibilityHtmlReport(SearchContext context, SeleniumTestObject testObject,
      Func<AxeResult> getResults, boolean throwOnViolation) {
    // If we are using a lazy element go get the raw element instead
    LazyWebElement raw = (LazyWebElement) context;

    if (raw != null) {
      context = ((LazyWebElement)context).getRawExistingElement();
    }

    // Check to see if the logger is not verbose and not already suspended
    boolean restoreLogging = testObject.getLogger().getLoggingLevel() != MessageType.VERBOSE && testObject.getLogger().getLoggingLevel() != MessageType.SUSPENDED;

    Result results;
    String report = GetAccessibilityReportPath(testObject);
    testObject.getLogger().logMessage(MessageType.INFORMATION, "Running accessibility check");

    try {
      // Suspend logging if we are not verbose or already suspended
      if (restoreLogging) {
        testObject.getLogger().suspendLogging();
      }

      results = getResults();
      context.createAxeHtmlReport(results, report);
    } finally {
      // Restore logging if we suspended it
      if (restoreLogging) {
        testObject.getLogger().continueLogging();
      }
    }

    // Add the report
    testObject.addAssociatedFile(report);
    testObject.getLogger().logMessage(MessageType.INFORMATION, $"Ran accessibility check and created HTML report: {report} ");

    // Throw exception if we found violations and we want that to cause an error
    if (throwOnViolation &&  results.Violations.Length > 0) {
      throw new ApplicationException($"Accessibility violations, see: {report} for more details.");
    }

    // Throw exception if the accessibility check had any errors
    if (results.Error.Length > 0) {
      throw new ApplicationException($"Accessibility check failure, see: {report} for more details.");
    }
  }

  /// <summary>
  /// Run axe accessibility and log the results
  /// </summary>
  /// <param name="testObject">The test object which contains the web driver and logger you wish to use</param>
  /// <param name="throwOnViolation">Should violations cause and exception to be thrown</param>
  public static void checkAccessibility(SeleniumTestObject testObject, boolean throwOnViolation) {
    checkAccessibility(testObject.getWebDriver(), testObject.getLogger(), throwOnViolation);
  }

  /// <summary>
  /// Run axe accessibility and log the results
  /// </summary>
  /// <param name="webDriver">The web driver that is on the page you want to run the accessibility check on</param>
  /// <param name="logger">Where you want the check logged to</param>
  /// <param name="throwOnViolation">Should violations cause and exception to be thrown</param>
  public static void checkAccessibility(WebDriver webDriver, Logger logger, boolean throwOnViolation) {
    MessageType type = logger.getLoggingLevel();

    // Look at passed
    if (type >= MessageType.SUCCESS) {
      checkAccessibilityPasses(webDriver, logger, MessageType.SUCCESS);
    }

    // Look at incomplete
    if (type >= MessageType.INFORMATION) {
      checkAccessibilityIncomplete(webDriver, logger, MessageType.INFORMATION, throwOnViolation);
    }

    // Look at inapplicable
    if (type >= MessageType.VERBOSE) {
      checkAccessibilityInapplicable(webDriver, logger, MessageType.VERBOSE, throwOnViolation);
    }

    // Look at violations
    MessageType messageType = throwOnViolation ? MessageType.ERROR : MessageType.WARNING;
    checkAccessibilityViolations(webDriver, logger, messageType, throwOnViolation);
  }

  /// <summary>
  /// Run axe accessibility and log the results
  /// </summary>
  /// <param name="webDriver">The web driver that is on the page you want to run the accessibility check on</param>
  /// <param name="logger">Where you want the check logged to</param>
  /// <param name="checkType">What kind of check is being run</param>
  /// <param name="getResults">Function for getting Axe results</param>
  /// <param name="loggingLevel">What level should logging the check take, this gets used if the check doesn't throw an exception</param>
  /// <param name="throwOnResults">Throw error if any results are found</param>
  public static void checkAccessibility(WebDriver webDriver, Logger logger, String checkType,
      Func<AxeResultItem[]> getResults, MessageType loggingLevel, boolean throwOnResults) {
    logger.logMessage(MessageType.INFORMATION, "Running accessibility check");

    if (getReadableAxeResults(checkType, webDriver, getResults(), out string axeText) && throwOnResults) {
      throw new ApplicationException(axeText);
    } else {
      logger.logMessage(loggingLevel, axeText);
    }
  }

  /// <summary>
  /// Run axe accessibility and log the results
  /// </summary>
  /// <param name="webDriver">The web driver that is on the page you want to run the accessibility check on</param>
  /// <param name="logger">Where you want the check logged to</param>
  /// <param name="loggingLevel">What level should logging the check take, this gets used if the check doesn't throw an exception</param>
  public static void checkAccessibilityPasses(WebDriver webDriver, Logger logger, MessageType loggingLevel) {
    checkAccessibility(webDriver, logger, AccessibilityCheckType.Passes.ToString(), () -> webDriver.Analyze().Passes, loggingLevel);
  }

  ///AccessibilityCheckType
  ///
  /// <summary>
  /// Run axe accessibility and log the results
  /// </summary>
  /// <param name="webDriver">The web driver that is on the page you want to run the accessibility check on</param>
  /// <param name="logger">Where you want the check logged to</param>
  /// <param name="loggingLevel">What level should logging the check take, this gets used if the check doesn't throw an exception</param>
  /// <param name="throwOnInapplicable">Should inapplicable cause and exception to be thrown</param>
  public static void checkAccessibilityInapplicable(WebDriver webDriver, Logger logger,
      MessageType loggingLevel, boolean throwOnInapplicable) {
    checkAccessibility(webDriver, logger, AccessibilityCheckType.Inapplicable.ToString(), () -> webDriver.Analyze().Inapplicable, loggingLevel, throwOnInapplicable);
  }

  ///AccessibilityCheckType
  ///
  /// <summary>
  /// Run axe accessibility and log the results
  /// </summary>
  /// <param name="webDriver">The web driver that is on the page you want to run the accessibility check on</param>
  /// <param name="logger">Where you want the check logged to</param>
  /// <param name="loggingLevel">What level should logging the check take, this gets used if the check doesn't throw an exception</param>
  /// <param name="throwOnIncomplete">Should incomplete cause and exception to be thrown</param>
  public static void checkAccessibilityIncomplete(WebDriver webDriver, Logger logger,
      MessageType loggingLevel, boolean throwOnIncomplete) {
    checkAccessibility(webDriver, logger, AccessibilityCheckType.Incomplete.ToString(), () -> webDriver.Analyze().Incomplete, loggingLevel, throwOnIncomplete);
  }

  /// <summary>
  /// Run axe accessibility and log the results
  /// </summary>
  /// <param name="webDriver">The web driver that is on the page you want to run the accessibility check on</param>
  /// <param name="logger">Where you want the check logged to</param>
  /// <param name="loggingLevel">What level should logging the check take, this gets used if the check doesn't throw an exception</param>
  /// <param name="throwOnViolation">Should violations cause and exception to be thrown</param>
  public static void checkAccessibilityViolations(WebDriver webDriver, Logger logger,
      MessageType loggingLevel, boolean throwOnViolation) {
    checkAccessibility(webDriver, logger, AccessibilityCheckType.Violations.ToString(), () -> webDriver.Analyze().Violations, loggingLevel, throwOnViolation);
  }
}
