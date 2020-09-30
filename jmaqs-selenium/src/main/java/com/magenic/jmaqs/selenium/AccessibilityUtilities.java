package com.magenic.jmaqs.selenium;

import com.deque.html.axecore.results.AxeRuntimeException;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.ResultType;
import com.magenic.jmaqs.utilities.logging.FileLogger;
import com.magenic.jmaqs.utilities.logging.Logger;
import com.magenic.jmaqs.utilities.logging.LoggingConfig;
import com.magenic.jmaqs.utilities.logging.MessageType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Supplier;

/**
 * Utilities class for Accessibility Functionality.
 */
public class AccessibilityUtilities {

  /**
   * private constructor.
   */
  private AccessibilityUtilities() { }

  /**
   * Create a HTML accessibility report for an entire web page.
   * @param webDriver The WebDriver
   * @param testObject The TestObject to associate the report with
   * @param throwOnViolation Should violations cause and exception to be thrown
   */
  public static void createAccessibilityHtmlReport(WebDriver webDriver, SeleniumTestObject testObject,
      boolean throwOnViolation) {
    AxeBuilder axeBuilder = new AxeBuilder();
    createAccessibilityHtmlReport(webDriver, testObject, () -> axeBuilder.analyze(webDriver), throwOnViolation);
  }

  /**
   * Create a HTML accessibility report for a specific web element and all of it's children.
   * @param webDriver The WebDriver
   * @param testObject The TestObject to associate the report with
   * @param element The WebElement you want to use as the root for your accessibility scan
   * @param throwOnViolation Should violations cause and exception to be thrown
   */
  public static void createAccessibilityHtmlReport(WebDriver webDriver, SeleniumTestObject testObject,
      WebElement element, boolean throwOnViolation) {

    AxeBuilder axeBuilder = new AxeBuilder();

    // If we are using a lazy element go get the raw element instead
    LazyWebElement raw = (LazyWebElement) element;

    if (raw != null) {
      element = ((LazyWebElement)element).getRawExistingElement();
    }

    createAccessibilityHtmlReport(element, testObject, () -> axeBuilder.analyze(webDriver, element), throwOnViolation);
  }

  /**
   * Create a HTML accessibility report.
   * @param context The scan context, this is either a web driver or web element
   * @param testObject The TestObject to associate the report with
   * @param getResults Function for getting the accessibility scan results
   * @param throwOnViolation Should violations cause and exception to be thrown
   */
  public static void createAccessibilityHtmlReport(SearchContext context, SeleniumTestObject testObject,
      Supplier<Results> getResults, boolean throwOnViolation) {
    // If we are using a lazy element go get the raw element instead
    LazyWebElement raw = (LazyWebElement) context;

    if (raw != null) {
      context = ((LazyWebElement)context).getRawExistingElement();
    }

    // Check to see if the logger is not verbose and not already suspended
    boolean restoreLogging = testObject.getLogger().getLoggingLevel() != MessageType.VERBOSE && testObject.getLogger().getLoggingLevel() != MessageType.SUSPENDED;

    Results results;
    String report = getAccessibilityReportPath(testObject);
    testObject.getLogger().logMessage(MessageType.INFORMATION, "Running accessibility check");

    try {
      // Suspend logging if we are not verbose or already suspended
      if (restoreLogging) {
        testObject.getLogger().suspendLogging();
      }

      results = getResults.get();
      context.createAxeHtmlReport(results, report);
    } finally {
      // Restore logging if we suspended it
      if (restoreLogging) {
        testObject.getLogger().continueLogging();
      }
    }

    // Add the report
    testObject.addAssociatedFile(report);
    testObject.getLogger().logMessage(MessageType.INFORMATION, "Ran accessibility check and created HTML report: " + report);

    // Throw exception if we found violations and we want that to cause an error
    if (throwOnViolation && !results.getViolations().isEmpty()) {
      throw new AxeRuntimeException("Accessibility violations, see: " + report + " for more details.");
    }

    // Throw exception if the accessibility check had any errors
    if (results.getError() == null) {
      throw new AxeRuntimeException("Accessibility check failure, see: " + report + " for more details.");
    }
  }

  /**
   * Run axe accessibility and log the results.
   * @param testObject The test object which contains the web driver and logger you wish to use
   * @param throwOnViolation Should violations cause and exception to be thrown
   */
  public static void checkAccessibility(SeleniumTestObject testObject, boolean throwOnViolation) {
    checkAccessibility(testObject.getWebDriver(), testObject.getLogger(), throwOnViolation);
  }

  /**
   * Run axe accessibility and log the results.
   * @param webDriver The web driver that is on the page you want to run the accessibility check on
   * @param logger Where you want the check logged to
   * @param throwOnViolation Should violations cause and exception to be thrown
   */
  public static void checkAccessibility(WebDriver webDriver, Logger logger, boolean throwOnViolation) {
    MessageType type = logger.getLoggingLevel();

    // Look at passed
    if (type.getValue() >= MessageType.SUCCESS.getValue()) {
      checkAccessibilityPasses(webDriver, logger, MessageType.SUCCESS);
    }

    // Look at incomplete
    if (type.getValue() >= MessageType.INFORMATION.getValue()) {
      checkAccessibilityIncomplete(webDriver, logger, MessageType.INFORMATION, throwOnViolation);
    }

    // Look at inapplicable
    if (type.getValue() >= MessageType.VERBOSE.getValue()) {
      checkAccessibilityInapplicable(webDriver, logger, MessageType.VERBOSE, throwOnViolation);
    }

    // Look at violations
    MessageType messageType = throwOnViolation ? MessageType.ERROR : MessageType.WARNING;
    checkAccessibilityViolations(webDriver, logger, messageType, throwOnViolation);
  }

  /**
   * Run axe accessibility and log the results.
   * @param webDriver The web driver that is on the page you want to run the accessibility check on
   * @param logger Where you want the check logged to
   * @param checkType What kind of check is being run
   * @param getResults Function for getting Axe results
   * @param loggingLevel What level should logging the check take, this gets used if the check doesn't throw an exception
   * @param throwOnResults Throw error if any results are found
   */
  public static void checkAccessibility(WebDriver webDriver, Logger logger, String checkType,
      Supplier<List<Rule>> getResults, MessageType loggingLevel, boolean throwOnResults) {
    logger.logMessage(MessageType.INFORMATION, "Running accessibility check");

    if (AxeReporter.getReadableAxeResults(checkType, webDriver, getResults.get()) && throwOnResults) {
      throw new AxeRuntimeException(AxeReporter.getAxeResultString());
    } else {
      logger.logMessage(loggingLevel, AxeReporter.getAxeResultString());
    }
  }

  /**
   * Run axe accessibility and log the results.
   * @param webDriver The web driver that is on the page you want to run the accessibility check on
   * @param logger Where you want the check logged to
   * @param loggingLevel What level should logging the check take, this gets used if the check doesn't throw an exception
   */
  public static void checkAccessibilityPasses(WebDriver webDriver, Logger logger, MessageType loggingLevel) {
    AxeBuilder axeBuilder = new AxeBuilder();
    checkAccessibility(webDriver, logger, ResultType.Passes.getKey(),
        () -> axeBuilder.analyze(webDriver).getPasses(), loggingLevel, false);
  }

  /**
   * Run axe accessibility and log the results.
   * @param webDriver The web driver that is on the page you want to run the accessibility check on
   * @param logger Where you want the check logged to
   * @param loggingLevel What level should logging the check take, this gets used if the check doesn't throw an exception
   * @param throwOnInapplicable Should inapplicable cause and exception to be thrown
   */
  public static void checkAccessibilityInapplicable(WebDriver webDriver, Logger logger,
      MessageType loggingLevel, boolean throwOnInapplicable) {
    AxeBuilder axeBuilder = new AxeBuilder();
    checkAccessibility(webDriver, logger, ResultType.Inapplicable.getKey(),
        () -> axeBuilder.analyze(webDriver).getInapplicable(), loggingLevel, throwOnInapplicable);
  }

  /**
   * Run axe accessibility and log the results.
   * @param webDriver The web driver that is on the page you want to run the accessibility check on
   * @param logger Where you want the check logged to
   * @param loggingLevel What level should logging the check take, this gets used if the check doesn't throw an exception
   * @param throwOnIncomplete Should incomplete cause and exception to be thrown
   */
  public static void checkAccessibilityIncomplete(WebDriver webDriver, Logger logger,
      MessageType loggingLevel, boolean throwOnIncomplete) {
    AxeBuilder axeBuilder = new AxeBuilder();
    checkAccessibility(webDriver, logger, ResultType.Incomplete.getKey(),
        () -> axeBuilder.analyze(webDriver).getIncomplete(), loggingLevel, throwOnIncomplete);
  }

  /**
   * Run axe accessibility and log the results.
   * @param webDriver The web driver that is on the page you want to run the accessibility check on
   * @param logger Where you want the check logged to
   * @param loggingLevel What level should logging the check take, this gets used if the check doesn't throw an exception
   * @param throwOnViolation Should violations cause and exception to be thrown
   */
  public static void checkAccessibilityViolations(WebDriver webDriver, Logger logger,
      MessageType loggingLevel, boolean throwOnViolation) {
    AxeBuilder axeBuilder = new AxeBuilder();
    checkAccessibility(webDriver, logger, ResultType.Violations.getKey(), () -> axeBuilder.analyze(webDriver).getViolations(), loggingLevel, throwOnViolation);
  }

  /**
   * Get a unique file name that we can user for the accessibility HTML report.
   * @param testObject The TestObject to associate the report with
   * @return A unique HTML file name, includes full path
   */
  private static String getAccessibilityReportPath(SeleniumTestObject testObject) {
    String logDirectory = testObject.getLogger() instanceof FileLogger ? ((FileLogger)testObject.getLogger()).getFilePath() : LoggingConfig.getLogDirectory();
    String reportBaseName = testObject.getLogger() instanceof FileLogger ? ((FileLogger)testObject.getLogger()).getFilePath() + "_Axe" : "AxeReport";
    String reportFile = Path.Combine(logDirectory, reportBaseName + ".html");
    int reportNumber = 0;

    while (File.Exists(reportFile)) {
      reportFile = Path.Combine(logDirectory, reportBaseName + reportNumber++ + ".html");
    }

    return reportFile;
  }
}