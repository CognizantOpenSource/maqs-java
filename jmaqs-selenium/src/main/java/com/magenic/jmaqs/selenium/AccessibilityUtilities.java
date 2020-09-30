package com.magenic.jmaqs.selenium;

import com.deque.html.axecore.results.AxeRuntimeException;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.ResultType;
import com.magenic.jmaqs.utilities.logging.Logger;
import com.magenic.jmaqs.utilities.logging.MessageType;
import org.openqa.selenium.WebDriver;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;

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
}