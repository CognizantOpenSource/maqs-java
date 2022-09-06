/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.accessibility;


import com.deque.html.axecore.playwright.AxeBuilder;
import com.deque.html.axecore.selenium.ResultType;
import com.deque.html.axecore.utilities.axeresults.AxeResults;
import com.deque.html.axecore.utilities.axeresults.Check;
import com.deque.html.axecore.utilities.axeresults.CheckedNode;
import com.deque.html.axecore.utilities.axeresults.Rule;
import com.microsoft.playwright.Page;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Base64;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebElement;

/**
 * The HTML reporter class.
 */
public class PlaywrightReporter extends HtmlReporter {

  /**
   * Placeholder for wrap one tag string type.
   */
  private static final String WRAP_ONE = "wrapOne";

  /**
   * File path to resources java resources folder.
   */
  private static final String RESOURCES_FILE = "../maqs-accessibility/src/main/resources/";

  /**
   * Class constructor.
   */
  protected PlaywrightReporter() {
  }

  /**
   * Create an HTML accessibility report for an entire web page.
   * @param page the web driver used in the scan
   * @param destination the destination file the html report will go to
   * @throws IOException if an IO exception is thrown
   * @throws ParseException if a parse exception is thrown
   */
  public static void createAxeHtmlReport(Page page, String destination)
      throws IOException, ParseException {
    createAxeHtmlReport(page, destination, EnumSet.allOf(ResultType.class));
  }

  /**
   * Create an HTML accessibility report for an entire web page with specific Result types.
   * @param page the web driver used in the scan
   * @param destination the destination file the html report will go to
   * @param requestedResults the specified result types to include in the report
   * @throws IOException if an IO exception is thrown
   * @throws ParseException if a parse exception is thrown
   */
  public static void createAxeHtmlReport(Page page, String destination, Set<ResultType> requestedResults)
      throws IOException, ParseException {
    createAxeHtmlReport(page, new AxeBuilder(page).analyze(), destination, requestedResults);
  }

  /**
   * Create an HTML accessibility report for a specific element.
   * @param page the web driver used in the scan
   * @param element the element to be scanned
   * @param destination the destination file the html report will go to
   * @throws IOException if an IO exception is thrown
   * @throws ParseException if a parse exception is thrown
   */
  public static void createAxeHtmlReport(Page page, WebElement element, String destination)
      throws IOException, ParseException {
    createAxeHtmlReport(page, element, destination, EnumSet.allOf(ResultType.class));
  }

  /**
   * Create an HTML accessibility report for a specific element and specified result types.
   * @param page the web driver used in the scan
   * @param element the element to be scanned
   * @param destination the destination file the html report will go to
   * @param requestedResults the specified result types to include in the report
   * @throws IOException if an IO exception is thrown
   * @throws ParseException if a parse exception is thrown
   */
  public static void createAxeHtmlReport(Page page, WebElement element, String destination,
      Set<ResultType> requestedResults) throws IOException, ParseException {
  //    createAxeHtmlReport(page, new AxeBuilder(page).analyze(element), destination, requestedResults);
  }

  /**
   * Create an HTML accessibility report for an entire web page with already scanned results.
   * @param page the web driver used in the scan
   * @param results the results type variable used after scanning the web page
   * @param destination the destination file the html report will go to
   * @throws IOException if an IO exception is thrown
   * @throws ParseException if a parse exception is thrown
   */
  public static void createAxeHtmlReport(Page page, AxeResults results, String destination)
      throws IOException, ParseException {
    createAxeHtmlReport(page, results, destination, EnumSet.allOf(ResultType.class));
  }

  /**
   * Create an HTML accessibility report for an entire web page with specified Result types
   * and inputted already scanned results.
   * @param page the web driver used in the scan
   * @param results the results object created after scanning the web page
   * @param destination the destination file the html report will go to
   * @param requestedResults the specified result types to include in the report
   * @throws IOException if an IO exception is thrown
   * @throws ParseException if a parse exception is thrown
   */
  public static void createAxeHtmlReport(Page page, AxeResults results, String destination,
      Set<ResultType> requestedResults) throws IOException, ParseException {
    createAxeHtmlReportFile(page, results, destination, requestedResults);
  }

  /**
   * Creates an HTML accessibility report.
   * @param context the Search Context to be used in the scan
   * @param results the results object created after scanning the web page
   * @param destination the destination file the html report will go to
   * @param requestedResults the specified result types to include in the report
   * @throws IOException if an IO exception is thrown
   * @throws ParseException if a parse exception is thrown
   */
  private static void createAxeHtmlReportFile(Page context, AxeResults results, String destination,
      Set<ResultType> requestedResults) throws IOException, ParseException {
    final int violationCount = getCount(results.getViolations());
    final int incompleteCount = getCount(results.getIncomplete());
    final int passCount = getCount(results.getPasses());
    final int inapplicableCount = getCount(results.getInapplicable());

    String stringBuilder = String.valueOf(Files.readString(Paths.get(RESOURCES_FILE + "htmlReporterTags.html")));
    stringBuilder = stringBuilder.replace(System.lineSeparator(), "");

    Document doc = Jsoup.parse(stringBuilder);

    doc.select("style").append(getCss(context));

    Element contentArea = doc.select("content").first();

    Element reportTitle = new Element("h1");
    reportTitle.text("Accessibility Check");
    Objects.requireNonNull(contentArea).appendChild(reportTitle);

    Element metaFlex = new Element("div");
    metaFlex.attributes().put("id", "metadata");
    contentArea.appendChild(metaFlex);

    Element contextGroup = new Element("div");
    contextGroup.attributes().put("id", "context");
    metaFlex.appendChild(contextGroup);

    Element contextHeader = new Element("h3");
    contextHeader.text("Context:");
    contextGroup.appendChild(contextHeader);

    Element contextContent = new Element("div");
    contextContent.addClass("emOne");
    contextContent.attributes().put("id", "reportContext");
    getContextContent(results, contextContent);
    contextGroup.appendChild(contextContent);

    Element imgGroup = new Element("div");
    imgGroup.attributes().put("id", "image");
    metaFlex.appendChild(imgGroup);

    Element imageHeader = new Element("h3");
    imageHeader.appendText("Image:");
    imgGroup.appendChild(imageHeader);

    Element imageContent = new Element("img");
    imageContent.addClass("thumbnail");
    imageContent.attributes().put("id", "screenshotThumbnail");
    imageContent.attributes().put("alt", "A Screenshot of the page");
    imageContent.attributes().put("width", "33%");
    imageContent.attributes().put("height", "auto");
    imgGroup.appendChild(imageContent);

    Element countsGroup = new Element("div");
    countsGroup.attributes().put("id", "counts");
    metaFlex.appendChild(countsGroup);

    Element countsHeader = new Element("h3");
    countsHeader.appendText("Counts:");
    countsGroup.appendChild(countsHeader);

    Element countsContent = new Element("div");
    countsContent.addClass("emOne");
    getCountContent(violationCount, incompleteCount, passCount, inapplicableCount, requestedResults, countsContent);
    countsGroup.appendChild(countsContent);

    Element resultsFlex = new Element("div");
    resultsFlex.attributes().put("id", "results");
    contentArea.appendChild(resultsFlex);

    if (results.isErrored()) {
      Element errorHeader = new Element("h2");
      errorHeader.appendText("SCAN ERRORS:");
      contentArea.appendChild(errorHeader);

      Element errorContent = new Element("div");
      errorContent.attributes().put("id", "ErrorMessage");
      errorContent.appendText(results.getErrorMessage());
      contentArea.appendChild(errorContent);
    }

    if (violationCount > 0 && requestedResults.contains(ResultType.Violations)) {
      getReadableAxeResults(results.getViolations(), ResultType.Violations, resultsFlex);
    }

    if (incompleteCount > 0 && requestedResults.contains(ResultType.Incomplete)) {
      getReadableAxeResults(results.getIncomplete(), ResultType.Incomplete, resultsFlex);
    }

    if (passCount > 0 && requestedResults.contains(ResultType.Passes)) {
      getReadableAxeResults(results.getPasses(), ResultType.Passes, resultsFlex);
    }

    if (inapplicableCount > 0 && requestedResults.contains(ResultType.Inapplicable)) {
      getReadableAxeResults(results.getInapplicable(), ResultType.Inapplicable, resultsFlex);
    }

    Element modal = new Element("div");
    modal.attributes().put("id", "modal");
    contentArea.appendChild(modal);

    Element modalClose = new Element("div");
    modalClose.text("X");
    modalClose.attributes().put("id", "modalclose");
    modal.appendChild(modalClose);

    Element modalImage = new Element("img");
    modalImage.attributes().put("id", "modalimage");
    modal.appendChild(modalImage);

    Element script = doc.select("script").first();
    Objects.requireNonNull(script).appendChild(new DataNode(HtmlReporter.getJavascriptFileToString()));

    FileUtils.writeStringToFile(new File(destination), doc.outerHtml(), StandardCharsets.UTF_8);
  }

  /**
   * Sets up the results into html elements for the report.
   * @param results A list of the Rule results found
   * @param type The result type that is being created
   * @param body The main html page element body
   */
  private static void getReadableAxeResults(List<Rule> results, ResultType type, Element body) {
    Element resultWrapper = new Element("div");
    resultWrapper.addClass("resultWrapper");
    body.appendChild(resultWrapper);

    Element sectionButton = new Element("button");
    sectionButton.addClass("sectionbutton active");
    resultWrapper.appendChild(sectionButton);

    Element sectionButtonHeader = new Element("h2");
    sectionButtonHeader.addClass("buttonInfoText");
    sectionButtonHeader.text(type.name() + ": " + getCount(results));
    sectionButton.appendChild(sectionButtonHeader);

    Element sectionButtonExpando = new Element("h2");
    sectionButtonExpando.addClass("buttonExpandoText");
    sectionButtonExpando.text("-");
    sectionButton.appendChild(sectionButtonExpando);

    Element section = new Element("div");
    section.addClass("majorSection");
    section.attributes().put("id", type.name() + "Section");
    resultWrapper.appendChild(section);

    int loops = 1;

    for (Rule element : results) {
      Element childEl = new Element("div");
      childEl.addClass("findings");
      childEl.appendText(loops++ + ": " + element.getHelp());
      section.appendChild(childEl);

      Element content = new Element("div");
      content.addClass("emTwo");
      content.text("Description: " + element.getDescription());
      content.appendChild(new Element("br"));
      content.appendText("Help: " + element.getHelp());
      content.appendChild(new Element("br"));
      content.appendText("Help URL: ");

      Element link = new Element("a");
      link.attributes().put("href", element.getHelpUrl());
      link.text(element.getHelpUrl());

      content.appendChild(link);
      content.appendChild(new Element("br"));

      if (!element.getImpact().isEmpty()) {
        content.appendText("Impact: " + element.getImpact());
        content.appendChild(new Element("br"));
      }

      content.appendText("Tags: ").append(String.join(", ", element.getTags()));
      content.appendChild(new Element("br"));

      if (!element.getNodes().isEmpty()) {
        content.appendText("Element(s):");
      }

      Element childEl2 = new Element("div");
      childEl2.addClass("emTwo");
      childEl.appendChild(content);

      for (CheckedNode item : element.getNodes()) {
        Element elementNodes = new Element("div");
        elementNodes.addClass("htmlTable");
        childEl.appendChild(elementNodes);

        Element htmlAndSelectorWrapper = new Element("div");
        htmlAndSelectorWrapper.addClass("emThree");
        htmlAndSelectorWrapper.text("Html:");
        htmlAndSelectorWrapper.appendChild(new Element("br"));
        elementNodes.appendChild(htmlAndSelectorWrapper);

        Element htmlAndSelector = new Element("p");
        htmlAndSelector.addClass(WRAP_ONE);
        htmlAndSelector.html(item.getHtml());
        htmlAndSelector.text(item.getHtml());
        htmlAndSelectorWrapper.appendChild(htmlAndSelector);
        htmlAndSelectorWrapper.appendText("Selector:");

        htmlAndSelector = new Element("p");
        htmlAndSelector.addClass("wrapTwo");

        for (Object target : Collections.singletonList(item.getTarget())) {
          String targetString = target.toString().replace("[", "").replace("]", "");
          htmlAndSelector.text(targetString);
          htmlAndSelector.html(targetString);
        }

        htmlAndSelectorWrapper.appendChild(htmlAndSelector);
        addFixes(item, type, htmlAndSelectorWrapper);
      }
    }
  }

  /**
   * Add the fixes for the specified result type.
   * @param resultsNode The fixes from the results in this specific result type
   * @param type The result type fixes
   * @param htmlAndSelectorWrapper The element that the fixes will be appended to
   */
  private static void addFixes(CheckedNode resultsNode, ResultType type, Element htmlAndSelectorWrapper) {
    Element htmlAndSelector = new Element("div");

    List<Check> anyCheckResults = resultsNode.getAny();
    List<Check> allCheckResults = resultsNode.getAll();
    List<Check> noneCheckResults = resultsNode.getNone();

    int checkResultsCount = anyCheckResults.size() + allCheckResults.size() + noneCheckResults.size();

    // Add fixes if this is for violations
    if (ResultType.Violations.equals(type) && checkResultsCount > 0) {
      htmlAndSelector.text("To solve:");
      htmlAndSelectorWrapper.appendChild(htmlAndSelector);

      htmlAndSelector = new Element("p");
      htmlAndSelector.addClass("wrapTwo");
      htmlAndSelectorWrapper.appendChild(htmlAndSelector);

      if (!allCheckResults.isEmpty() || !noneCheckResults.isEmpty()) {
        fixAllIssues(htmlAndSelectorWrapper, allCheckResults, noneCheckResults);
      }

      if (!anyCheckResults.isEmpty()) {
        fixAnyIssues(htmlAndSelectorWrapper, anyCheckResults);
      }
    }
  }

  /**
   * Adds the issues in the all category in the list of Checks.
   * @param htmlAndSelectorWrapper The element that all the content will be appended to
   * @param allCheckResults A list of the all check results
   * @param noneCheckResults A list of the none check results
   */
  private static void fixAllIssues(Element htmlAndSelectorWrapper,
      List<Check> allCheckResults, List<Check> noneCheckResults) {
    Element htmlAndSelector = new Element("p");
    htmlAndSelector.addClass(WRAP_ONE);
    htmlAndSelector.text("Fix at least one of the following issues:");

    Element htmlSet = new Element("ul");

    for (var checkResult : allCheckResults) {
      Element bulletPoints = new Element("li");
      bulletPoints.text(checkResult.getImpact().toUpperCase() + ": " + checkResult.getMessage());
      htmlSet.appendChild(bulletPoints);
    }

    for (var checkResult : noneCheckResults) {
      Element bulletPoints = new Element("li");
      bulletPoints.text(checkResult.getImpact().toUpperCase() + ": " + checkResult.getMessage());
      htmlSet.appendChild(bulletPoints);
    }

    htmlAndSelector.appendChild(htmlSet);
    htmlAndSelectorWrapper.appendChild(htmlAndSelector);
  }

  /**
   * Adds the issues in the Any category in the list of Checks.
   * @param htmlAndSelectorWrapper The element that all the content will be appended to
   * @param anyCheckResults A list of the Any check results
   */
  private static void fixAnyIssues(Element htmlAndSelectorWrapper, List<Check> anyCheckResults) {
    Element htmlAndSelector = new Element("p");
    htmlAndSelector.addClass(WRAP_ONE);
    htmlAndSelector.text("Fix at least one of the following issues:");

    Element htmlSet = new Element("ul");

    for (var checkResult : anyCheckResults) {
      Element bulletPoints = new Element("li");
      bulletPoints.text(checkResult.getImpact().toUpperCase() + ": " + checkResult.getMessage());
      htmlSet.appendChild(bulletPoints);
    }

    htmlAndSelector.appendChild(htmlSet);
    htmlAndSelectorWrapper.appendChild(htmlAndSelector);
  }

  /**
   * Sets up the Context content and adds it to the element.
   * @param results the results to be used for the report
   * @param element the element that the content will be added to
   * @throws ParseException if an exception is thrown
   */
  private static void getContextContent(AxeResults results, Element element) throws ParseException {
    element.text("Url: " + results.getUrl());
    element.appendChild(new Element("br"));
    element.appendText("Orientation: " + results.getTestEnvironment().getOrientationType());
    element.appendChild(new Element("br"));
    element.appendText("Size: " + results.getTestEnvironment().getwindowWidth() + " x  "
        + results.getTestEnvironment().getWindowHeight());
    element.appendChild(new Element("br"));
    element.appendText("Time: " + HtmlReporter.getDateFormat(results.getTimestamp()));
    element.appendChild(new Element("br"));
    element.appendText("User agent: " + results.getTestEnvironment().getUserAgent());
    element.appendChild(new Element("br"));
    element.appendText("Using: " + results.getTestEngine().getName() + " ("
        + results.getTestEngine().getVersion() + ")");
  }

  /**
   * Gets the count of the number of rules that came up in the scan.
   * @param results The list of rules to be looped through
   * @return The count of all the rules
   */
  private static int getCount(List<Rule> results) {
    int count = 0;
    for (Rule item : results) {
      count += item.getNodes().size();

      // Still add one if no targets are included
      if (item.getNodes().isEmpty()) {
        count++;
      }
    }
    return count;
  }

  /**
   * Sets up the count content for the html report.
   * @param violationCount The count for the violations in the scan
   * @param incompleteCount The count for incomplete in the scan
   * @param passCount The count for passes in the scan
   * @param inapplicableCount The count for inapplicable in the scan
   * @param requestedResults The result types that will be included on the html report
   * @param element The element that all the content will be appended to
   */
  private static void getCountContent(int violationCount, int incompleteCount, int passCount,
      int inapplicableCount, Set<ResultType> requestedResults, Element element) {
    if (requestedResults.contains(ResultType.Violations)) {
      element.text(" Violations: " + violationCount);
      element.appendChild(new Element("br"));
    }

    if (requestedResults.contains(ResultType.Incomplete)) {
      element.appendText(" Incomplete: " + incompleteCount);
      element.appendChild(new Element("br"));
    }

    if (requestedResults.contains(ResultType.Passes)) {
      element.appendText(" Passes: " + passCount);
      element.appendChild(new Element("br"));
    }

    if (requestedResults.contains(ResultType.Inapplicable)) {
      element.appendText(" Inapplicable: " + inapplicableCount);
    }
  }

  /**
   * Gets the CSS file into a string format.
   * @return the CSS file script as a string
   * @throws IOException if an exception is thrown
   */
  private static String getCss(Page context) throws IOException {
    String css = String.valueOf(Files.readAllLines(
        Paths.get(RESOURCES_FILE + "htmlReporter.css")));
    return  css.replace("url('", "url('" + getDataImageString(context));
  }

  /**
   * Gets the data image as a base 64 string.
   * @param context The web driver or element to take a screenshot of
   * @return the base 64 data image as a string
   */
  private static String getDataImageString(Page context) {
    //TakesScreenshot newScreen = (TakesScreenshot) context;
    //return "data:image/png;base64," + newScreen.getScreenshotAs(OutputType.BASE64);
    byte[] bytes = context.screenshot();
    return "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes);
  }
}