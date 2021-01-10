/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.accessibility;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.selenium.ResultType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magenic.jmaqs.selenium.BaseSeleniumTest;
import com.magenic.jmaqs.selenium.SeleniumConfig;
import com.magenic.jmaqs.selenium.UIWait;
import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.utilities.helper.TestCategories;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class HTMLReporterUnitTest extends BaseSeleniumTest {

  //private final ObjectMapper mapper = new ObjectMapper();

  private final static File integrationTestTargetFile = new File("src/test/resources/integration-test-target.html");
  private final static String integrationTestTargetUrl = integrationTestTargetFile.getAbsolutePath();

  private final static File integrationTestJsonResultFile = new File("src/test/java/resources/sampleResults.json");
  private final static String integrationTestJsonResultUrl = integrationTestJsonResultFile.getAbsolutePath();

  /**
   * Unit testing site URL - Login page.
   */
  private final static String TestSiteUrl = SeleniumConfig.getWebSiteBase();

  /**
   * Unit testing site URL - Automation page.
   */
  private final static String TestSiteAutomationUrl = TestSiteUrl + "Automation/";

  /**
   * Sets up the tests and navigates to teh integration test site.
   */
  @BeforeMethod
  public void setup() {
    this.getWebDriver().get("file:///" + new File(integrationTestTargetUrl).getAbsolutePath());
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();
  }

  @Test(groups = TestCategories.ACCESSIBILITY)
  public void htmlReportFullPage() throws IOException, ParseException {
    String path = createReportPath();
    HtmlReporter.createAxeHtmlReport(this.getWebDriver(), path);
    validateReport(path, 5, 46, 0, 57);

    File file = new File(path);

    if (file.exists()) {
      Assert.assertTrue(file.delete(), "File was not deleted");
    }
  }

  @Test(groups = TestCategories.ACCESSIBILITY)
  public void htmlViolationsOnlyReportFullPage() throws IOException, ParseException {
    String path = createReportPath();
    HtmlReporter.createAxeHtmlReport(this.getWebDriver(), path,
        Collections.singletonList(ResultType.Violations));

    // Check violations
    validateReport(path, 5, 0, 0, 0);
    assertResultNotWritten(path,
        Arrays.asList( ResultType.Passes, ResultType.Inapplicable, ResultType.Incomplete));

    File file = new File(path);

    if (file.exists()) {
      Assert.assertTrue(file.delete(), "File was not deleted");
    }
  }

  @Test(groups = TestCategories.ACCESSIBILITY)
  public void htmlPassesInapplicableViolationsOnlyReportFullPage() throws IOException, ParseException {
    String path = createReportPath();
    HtmlReporter.createAxeHtmlReport(this.getWebDriver(), path,
        Arrays.asList(ResultType.Passes, ResultType.Inapplicable, ResultType.Violations));

    // Check Passes
    validateReport(path, 5, 46, 0, 57);
    assertResultNotWritten(path, Collections.singletonList(ResultType.Incomplete));


    File file = new File(path);

    if (file.exists()) {
      Assert.assertTrue(file.delete(), "File was not deleted");
    }
  }

  @Test(groups = TestCategories.ACCESSIBILITY)
  public void htmlReportOnElement() throws IOException, ParseException {
    String path = createReportPath();
    HtmlReporter.createAxeHtmlReport(this.getWebDriver(),
        this.getWebDriver().findElement(By.cssSelector("main")), path);
    validateReport(path, 3, 16, 0, 69);

    File file = new File(path);

    if (file.exists()) {
      Assert.assertTrue(file.delete(), "File was not deleted");
    }
  }

  @Test(groups = TestCategories.ACCESSIBILITY)
  public void reportSampleResults() throws IOException, ParseException {
    String path = createReportPath();
    Results results = new ObjectMapper().readValue(new File(integrationTestJsonResultUrl), Results.class);

    HtmlReporter.createAxeHtmlReport(this.getWebDriver(), results, path);
    validateReport(path, 3, 5, 2, 4);

    String text = new String(Files.readAllBytes(Paths.get(path)));
    Document doc = Jsoup.parse(text);

    String errorMessage = doc.selectFirst("#ErrorMessage").text();
    Assert.assertEquals(errorMessage, "AutomationError");

    String reportContext = doc.selectFirst("#reportContext").text();
    Assert.assertTrue( reportContext.contains("Url: https://www.google.com/"), "URL is not in the document");
    Assert.assertTrue(reportContext.contains("Orientation: landscape-primary"), "Orientation is not in the document");
    Assert.assertTrue(reportContext.contains("Size: 1200 x 646"), "Size is not in the document");
    Assert.assertTrue(reportContext.contains("Time: 14-Apr-20 01:33:59 -0500"), "Time is not in the document");
    Assert.assertTrue(reportContext.contains("User agent: AutoAgent"), "User Agent is not in the document");
    Assert.assertTrue( reportContext.contains("Using: axe-core (3.4.1)"), "Using is not in the document");

    File file = new File(path);

    if (file.exists()) {
      Assert.assertTrue( file.delete(), "File was not deleted");
    }
  }

  private String createReportPath() {
    // TODO: generate ideal path to place report for testing
    return FileSystems.getDefault().getPath("results").toString() + UUID.randomUUID().toString() + ".html";
  }

  private void validateReport(String path, int violationCount, int passCount, int incompleteCount, int inapplicableCount)
      throws IOException {
    String text = Files.lines(Paths.get(path), StandardCharsets.UTF_8)
        .collect(Collectors.joining(System.lineSeparator()));

    Document doc = Jsoup.parse(text);

    // Check violations
    String xpath = "#ViolationsSection > div > div.htmlTable";
    assertElementCount(doc, violationCount, xpath, ResultType.Violations);

    // Check passes
    xpath = "#PassesSection > div > div.htmlTable";
    assertElementCount(doc, passCount, xpath, ResultType.Passes);

    // Check inapplicable
    xpath = "#InapplicableSection > div.findings";
    assertElementCount(doc, inapplicableCount, xpath, ResultType.Inapplicable);

    // Check incomplete
    xpath = "#IncompleteSection > div > div.htmlTable";
    assertElementCount(doc, incompleteCount, xpath, ResultType.Incomplete);

    // Check header data
    Assert.assertTrue(text.contains("Using: axe-core"), "Expected to find 'Using: axe-core'");

    if (violationCount != 0) {
      assertResultCount(text, violationCount, ResultType.Violations);
    }

    if (incompleteCount != 0) {
      assertResultCount(text, incompleteCount, ResultType.Incomplete);
    }

    if (passCount != 0) {
      assertResultCount(text, passCount, ResultType.Passes);
    }

    if (inapplicableCount != 0) {
      assertResultCount(text, inapplicableCount, ResultType.Inapplicable);
    }
  }

  private void assertElementCount(Document doc, int count, String xpath, ResultType resultType) {
    Elements liNodes = doc.select(xpath) != null ? doc.select(xpath) : new Elements();
    Assert.assertEquals(count, liNodes.size(), "Expected " + count + " " + resultType);
  }

  private void assertResultCount(String text, int count, ResultType resultType) {
    Assert.assertTrue(text.contains(resultType + ": " + count),
        "Expected to find '" + resultType + ": " + count);
  }

  private void assertResultNotWritten(String path, List<ResultType> resultTypeArray) throws IOException {
    String text = Files.lines(Paths.get(path), StandardCharsets.UTF_8)
        .collect(Collectors.joining(System.lineSeparator()));

    for (ResultType resultType : resultTypeArray) {
      Assert.assertFalse(text.contains(resultType + ": "),
          "Expected to not find '" + resultType  + ": '");
    }
  }
}
