package com.magenic.jmaqs.selenium;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.selenium.ResultType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

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

  private final ObjectMapper mapper = new ObjectMapper();

  private final static File integrationTestTargetFile = new File("src/test/resources/html/integration-test-target.html");
  private final static String integrationTestTargetUrl = integrationTestTargetFile.getAbsolutePath();

  private final static File integrationTestJsonResultFile = new File("src/test/java/results/sampleResults.json");
  private final static String integrationTestJsonResultUrl = integrationTestJsonResultFile.getAbsolutePath();

  private final String mainElementSelector = "main";

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
  @Before
  public void setup() {
    this.getWebDriver().get("file:///" + new File(integrationTestTargetUrl).getAbsolutePath());
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();
  }

  @Test()
  public void htmlReportFullPage() throws IOException, ParseException {
    String path = createReportPath();
    HtmlReporter.createAxeHtmlReport(this.getWebDriver(), path);
    validateReport(path, 5, 46, 0, 57);

    File file = new File(path);

    if (file.exists()) {
      Assert.assertTrue("File was not deleted", file.delete());
    }
  }

  @Test()
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
      Assert.assertTrue("File was not deleted", file.delete());
    }
  }

  @Test()
  public void htmlPassesInapplicableViolationsOnlyReportFullPage() throws IOException, ParseException {
    String path = createReportPath();
    HtmlReporter.createAxeHtmlReport(this.getWebDriver(), path,
        Arrays.asList(ResultType.Passes, ResultType.Inapplicable, ResultType.Violations));

    // Check Passes
    validateReport(path, 5, 46, 0, 57);
    assertResultNotWritten(path, Collections.singletonList(ResultType.Incomplete));


    File file = new File(path);

    if (file.exists()) {
      Assert.assertTrue("File was not deleted", file.delete());
    }
  }

  @Test()
  public void htmlReportOnElement() throws IOException, ParseException {
    String path = createReportPath();
    HtmlReporter.createAxeHtmlReport(this.getWebDriver(),
        this.getWebDriver().findElement(By.cssSelector("main")), path);
    validateReport(path, 3, 16, 0, 69);

    File file = new File(path);

    if (file.exists()) {
      Assert.assertTrue("File was not deleted", file.delete());
    }
  }

  @Test
  public void reportSampleResults() throws IOException, ParseException {
    String path = createReportPath();
    Results results = mapper.readValue(new File(integrationTestJsonResultUrl), Results.class);

    HtmlReporter.createAxeHtmlReport(this.getWebDriver(), results, path);
    validateReport(path, 3, 5, 2, 4);

    String text = new String(Files.readAllBytes(Paths.get(path)));
    Document doc = Jsoup.parse(text);

    String errorMessage = doc.selectFirst("#ErrorMessage").text();
    Assert.assertEquals("AutomationError", errorMessage);

    String reportContext = doc.selectFirst("#reportContext").text();
    Assert.assertTrue("URL is not in the document", reportContext.contains("Url: https://www.google.com/"));
    Assert.assertTrue("Orientation is not in the document", reportContext.contains("Orientation: landscape-primary"));
    Assert.assertTrue("Size is not in the document", reportContext.contains("Size: 1200 x 646"));
    Assert.assertTrue("Time is not in the document", reportContext.contains("Time: 14-Apr-20 01:33:59 -0500"));
    Assert.assertTrue("User Agent is not in the document", reportContext.contains("User agent: AutoAgent"));
    Assert.assertTrue("Using is not in the document", reportContext.contains("Using: axe-core (3.4.1)"));

    File file = new File(path);

    if (file.exists()) {
      Assert.assertTrue("File was not deleted", file.delete());
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
    Assert.assertTrue("Expected to find 'Using: axe-core'", text.contains("Using: axe-core"));

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
    Assert.assertEquals("Expected " + count + " " + resultType, count, liNodes.size());
  }

  private void assertResultCount(String text, int count, ResultType resultType) {
    Assert.assertTrue("Expected to find '" + resultType + ": " + count, text.contains(resultType + ": " + count));
  }

  private void assertResultNotWritten(String path, List<ResultType> resultTypeArray) throws IOException {
    String text = Files.lines(Paths.get(path), StandardCharsets.UTF_8)
        .collect(Collectors.joining(System.lineSeparator()));

    for (ResultType resultType : resultTypeArray) {
      Assert.assertFalse("Expected to not find '" + resultType  + ": '", text.contains(resultType + ": "));
    }
  }
}
