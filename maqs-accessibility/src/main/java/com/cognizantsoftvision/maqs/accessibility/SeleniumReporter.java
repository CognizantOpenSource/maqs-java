/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.accessibility;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.ResultType;
import java.io.IOException;
import java.text.ParseException;
import java.util.EnumSet;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * The Selenium reporter class.
 * Sets up the report from selenium scans
 */
public class SeleniumReporter {

  /**
   * Class constructor.
   */
  private SeleniumReporter() {
  }

  /**
   * Create an HTML accessibility report for an entire web page.
   * @param webDriver the web driver used in the scan
   * @param destination the destination file the html report will go to
   * @throws IOException if an IO exception is thrown
   * @throws ParseException if a parse exception is thrown
   */
  public static void createAxeHtmlReport(WebDriver webDriver, String destination)
      throws IOException, ParseException {
    createAxeHtmlReport(webDriver, destination, EnumSet.allOf(ResultType.class));
  }

  /**
   * Create an HTML accessibility report for an entire web page with specific Result types.
   * @param webDriver the web driver used in the scan
   * @param destination the destination file the html report will go to
   * @param requestedResults the specified result types to include in the report
   * @throws IOException if an IO exception is thrown
   * @throws ParseException if a parse exception is thrown
   */
  public static void createAxeHtmlReport(WebDriver webDriver, String destination, Set<ResultType> requestedResults)
      throws IOException, ParseException {
    createAxeHtmlReport(webDriver, new AxeBuilder().analyze(webDriver), destination, requestedResults);
  }

  /**
   * Create an HTML accessibility report for a specific element.
   * @param webDriver the web driver used in the scan
   * @param element the element to be scanned
   * @param destination the destination file the html report will go to
   * @throws IOException if an IO exception is thrown
   * @throws ParseException if a parse exception is thrown
   */
  public static void createAxeHtmlReport(WebDriver webDriver, WebElement element, String destination)
      throws IOException, ParseException {
    createAxeHtmlReport(webDriver, element, destination, EnumSet.allOf(ResultType.class));
  }

  /**
   * Create an HTML accessibility report for a specific element and specified result types.
   * @param webDriver the web driver used in the scan
   * @param element the element to be scanned
   * @param destination the destination file the html report will go to
   * @param requestedResults the specified result types to include in the report
   * @throws IOException if an IO exception is thrown
   * @throws ParseException if a parse exception is thrown
   */
  public static void createAxeHtmlReport(WebDriver webDriver, WebElement element, String destination,
      Set<ResultType> requestedResults) throws IOException, ParseException {
    createAxeHtmlReport(webDriver, new AxeBuilder().analyze(webDriver, element), destination, requestedResults);
  }

  /**
   * Create an HTML accessibility report for an entire web page with already scanned results.
   * @param webDriver the web driver used in the scan
   * @param results the results type variable used after scanning the web page
   * @param destination the destination file the html report will go to
   * @throws IOException if an IO exception is thrown
   * @throws ParseException if a parse exception is thrown
   */
  public static void createAxeHtmlReport(WebDriver webDriver, Results results, String destination)
      throws IOException, ParseException {
    createAxeHtmlReport(webDriver, results, destination, EnumSet.allOf(ResultType.class));
  }

  /**
   * Create an HTML accessibility report for an entire web page with specified Result types
   * and inputted already scanned results.
   * @param webDriver the web driver used in the scan
   * @param results the results object created after scanning the web page
   * @param destination the destination file the html report will go to
   * @param requestedResults the specified result types to include in the report
   * @throws IOException if an IO exception is thrown
   * @throws ParseException if a parse exception is thrown
   */
  public static void createAxeHtmlReport(WebDriver webDriver, Results results, String destination,
      Set<ResultType> requestedResults) throws IOException, ParseException {
    HtmlReporter.createAxeHtmlReportFile(webDriver, results, destination, requestedResults);
  }
}
