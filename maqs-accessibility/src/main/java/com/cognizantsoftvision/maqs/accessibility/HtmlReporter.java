/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.accessibility;

import com.deque.html.axecore.results.Check;
import com.deque.html.axecore.results.CheckedNode;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.ResultType;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;

/**
 * The HTML reporter class.
 */
public class HtmlReporter {

  /**
   * Placeholder for wrap one tag string type.
   */
  private static final String WRAP_ONE = "wrapOne";

  /**
   * File path to resources java resources folder.
   */
  private static final String RESOURCES_FILE = "../maqs-accessibility/src/main/resources/";

  private static final String DATA_SOURCE = "data:image/png;base64,";

  /**
   * Class constructor.
   */
  protected HtmlReporter() {
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
    createAxeHtmlReportFile(webDriver, results, destination, requestedResults);
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
  private static void createAxeHtmlReportFile(SearchContext context, Results results, String destination,
      Set<ResultType> requestedResults) throws IOException, ParseException {
    // Get the unwrapped element if we are using a wrapped element
    context = (context instanceof WrapsElement)
        ? ((WrapsElement) context).getWrappedElement() : context;

    final int violationCount = getCount(results.getViolations());
    final int incompleteCount = getCount(results.getIncomplete());
    final int passCount = getCount(results.getPasses());
    final int inapplicableCount = getCount(results.getInapplicable());

    String stringBuilder = String.valueOf(Files.readString(Paths.get(RESOURCES_FILE + "htmlReporterTags.html")));
    stringBuilder = stringBuilder.replace(System.lineSeparator(), "");

    Document doc = Jsoup.parse(stringBuilder);

    TakesScreenshot screenshot = getScreenShot(context);

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
      setImages(ResultType.Violations, doc, context, screenshot);
    }

    if (incompleteCount > 0 && requestedResults.contains(ResultType.Incomplete)) {
      getReadableAxeResults(results.getIncomplete(), ResultType.Incomplete, resultsFlex);
      setImages(ResultType.Incomplete, doc, context, screenshot);
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
    Objects.requireNonNull(script).appendChild(new DataNode(getJavascriptFileToString()));

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

    Element sectionButtonExpander = new Element("h2");
    sectionButtonExpando.addClass("buttonExpandoText");
    sectionButtonExpander.text("-");
    sectionButton.appendChild(sectionButtonExpander);

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
          String targetString = target.toString();
          targetString = targetString.contains("[[") && targetString.contains("]]")
              ? targetString.substring(2, targetString.length() - 2)
                : targetString.substring(1, targetString.length() - 1);

          htmlAndSelector.text(targetString);
          htmlAndSelector.html(targetString);
        }

        htmlAndSelectorWrapper.appendChild(htmlAndSelector);

        htmlAndSelectorWrapper = new Element("div");
        elementNodes.appendChild(htmlAndSelectorWrapper);
        addFixes(item, type, htmlAndSelectorWrapper);
        htmlAndSelectorWrapper.addClass("emFour");
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

  private static void setImages(ResultType resultType, Element doc,
      SearchContext searchContext, TakesScreenshot screenshot) throws IOException {
    if (!checkForNoWebDriver(searchContext) || screenshot == null) {
      return;
    }

    Element section = doc.getElementById(resultType.name() + "Section");
    Elements findings = section.getElementsByClass("findings");
    int count = 1;

    for (Element finding : findings) {
      for (Element table : finding.getElementsByClass("htmlTable")) {
        String elementName = resultType.name() + "Element" + count++;

        Element emThree = table.selectFirst("div.emThree");
        String selectorText = emThree.selectFirst("p.wrapTwo").text();
        By by = By.cssSelector(selectorText);
        WebElement foundElement = searchContext.findElement(By.cssSelector(selectorText));

        Point location = searchContext.findElement(by).getLocation();
        String imageString = getDataElementString(searchContext, by);

        Element image = new Element("img");
        image.attributes().put("src", imageString);
        image.attributes().put("alt", elementName);
        image.attributes().put("class", elementName);

        Element emFour = table.selectFirst("div.emFour");
        emFour.appendChild(image);
      }
    }
  }

  private static boolean checkForNoWebDriver(SearchContext searchContext) {
    if (searchContext instanceof WebDriver) {
      try {
        if (((WebDriver) searchContext).getCurrentUrl().contains("http")) {
          return true;
        }
      } catch (Exception e) {
        throw new WebDriverException("web driver is not open");
      }
    }
    return false;
  }

  private static TakesScreenshot getScreenShot(SearchContext context) {
    return (TakesScreenshot) context;
  }

  private static String getDataElementString(SearchContext webDriver, By by) {
    WebElement webElement = webDriver.findElement(by);
    String base64bytes = Base64.getEncoder().encodeToString(webElement.getScreenshotAs(OutputType.BYTES));
    return DATA_SOURCE + base64bytes;
  }

  private static String getDataElementString(
      TakesScreenshot screenshot, SearchContext webDriver, By by) throws IOException {
    // Get screenshot as a file
    File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);

    // Convert the screenshot into BufferedImage
    BufferedImage fullScreen = ImageIO.read(screenshotFile);

    // Find location of the web element on the page
    WebElement element = webDriver.findElement(by);
    Point location = webDriver.findElement(by).getLocation();

    // cropping the full image to get only the element screenshot
    BufferedImage bufferedImage = fullScreen.getSubimage(location.getX(), location.getY(),
        element.getSize().getWidth(), element.getSize().getHeight());

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, "PNG", out);
    String base64bytes = Base64.getEncoder().encodeToString(out.toByteArray());
    return DATA_SOURCE + base64bytes;
  }

  /**
   * Sets up the Context content and adds it to the element.
   * @param results the results to be used for the report
   * @param element the element that the content will be added to
   * @throws ParseException if an exception is thrown
   */
  private static void getContextContent(Results results, Element element) throws ParseException {
    element.text("Url: " + results.getUrl());
    element.appendChild(new Element("br"));
    element.appendText("Orientation: " + results.getTestEnvironment().getOrientationType());
    element.appendChild(new Element("br"));
    element.appendText("Size: " + results.getTestEnvironment().getwindowWidth() + " x  "
        + results.getTestEnvironment().getWindowHeight());
    element.appendChild(new Element("br"));
    element.appendText("Time: " + getDateFormat(results.getTimestamp()));
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
  private static String getCss(SearchContext context) throws IOException {
    String css = String.valueOf(Files.readAllLines(
        Paths.get(RESOURCES_FILE + "htmlReporter.css")));
    return  css.replace("url('", "url('" + getDataImageString(context));
  }

  /**
   * Gets the data image as a base 64 string.
   * @param context The web driver or element to take a screenshot of
   * @return the base 64 data image as a string
   */
  private static String getDataImageString(SearchContext context) {
    TakesScreenshot newScreen = (TakesScreenshot) context;
    return DATA_SOURCE + newScreen.getScreenshotAs(OutputType.BASE64);
  }

  /**
   * Gets the timestamp into a specified date format..
   * @param timestamp The time to be made into a date format
   * @return The timestamp as a specified date formatted string
   * @throws ParseException If parse exception occurs
   */
  private static String getDateFormat(String timestamp) throws ParseException {
    Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(timestamp);
    return new SimpleDateFormat("dd-MMM-yy HH:mm:ss Z").format(date);
  }

  /**
   * Gets the javascript file into a string format.
   * @return the javascript file script as a string
   * @throws IOException if an exception is thrown
   */
  private static String getJavascriptFileToString() throws IOException {
    return String.valueOf(Files.readAllLines(Paths.get(RESOURCES_FILE + "htmlReporterElements.js")));
  }
}