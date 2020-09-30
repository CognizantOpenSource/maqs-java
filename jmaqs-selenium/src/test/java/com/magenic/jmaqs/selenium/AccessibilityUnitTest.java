package com.magenic.jmaqs.selenium;

import com.deque.html.axecore.results.AxeRuntimeException;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;
import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.utilities.helper.TestCategories;
import com.magenic.jmaqs.utilities.logging.FileLogger;
import com.magenic.jmaqs.utilities.logging.MessageType;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class AccessibilityUnitTest extends BaseSeleniumTest {
  /**
   * Axe JSON with an error.
   */
  private final String AxeResultWithError = "{\"error\":\"AutomationError\",\"results\":{\"testEngine\": { \"name\":\"axe-core\",\"version\":\"3.4.1\"}, \"testRunner\": { \"name\":\"axe\"}, \"testEnvironment\": { \"userAgent\":\"AutoAgent\",\"windowWidth\": 1200, \"windowHeight\": 646, \"orientationAngle\": 0, \"orientationType\":\"landscape-primary\"},\"timestamp\":\"2020-04-14T01:33:59.139Z\",\"url\":\"url\",\"toolOptions\":{\"reporter\":\"v1\"},\"violations\":[],\"passes\":[],\"incomplete\":[],\"inapplicable\": []}}";

  /**
   * Unit testing site URL - Login page.
   */
  private final static String TestSiteUrl = SeleniumConfig.getWebSiteBase();

  /**
   * Unit testing site URL - Automation page.
   */
  private final static String TestSiteAutomationUrl = TestSiteUrl + "Automation/";

  /**
   * Verify we get verbose message back.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void AccessibilityCheckVerbose() throws IOException {
    getWebDriver().navigate().to(TestSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();

    String filePath = ((FileLogger)getLogger()).getFilePath();
    AccessibilityUtilities.checkAccessibility(getTestObject(), false);
    String logContent = Files.lines(Paths.get(filePath),
        StandardCharsets.UTF_8).collect(Collectors.joining(System.lineSeparator()));

    Assert.assertTrue(logContent.contains("Found 19 items"), "Expected to find 19 pass matches.");
    Assert.assertTrue(logContent.contains("Found 51 items"), "Expected to find 51 inapplicable matches.");
    Assert.assertTrue(logContent.contains("Found 6 items"), "Expected to find 6 violations matches.");
    Assert.assertTrue(logContent.contains("Incomplete check for"), "Expected to find any incomplete matches.");
  }

  /**
   * Verify message levels are respected.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void AccessibilityCheckRespectsMessageLevel() {
    getWebDriver().navigate().to(TestSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();

    String filePath = ((FileLogger)getLogger()).getFilePath();
    FileLogger fileLogger = new FileLogger(filePath, "LevTest.txt", MessageType.WARNING);

    try {
      AccessibilityUtilities.checkAccessibility(getTestObject().getWebDriver(), fileLogger, false);

      String logContent = Files.lines(Paths.get(fileLogger.getFilePath()),
          StandardCharsets.UTF_8).collect(Collectors.joining(System.lineSeparator()));

      Assert.assertFalse(logContent.contains("Passes check for"),
          "Did not expect expected to check for pass matches.");
      Assert.assertFalse(logContent.contains("Inapplicable check for"),
          "Did not expect expected to check for inapplicable matches.");
      Assert.assertFalse(logContent.contains("Incomplete check for"),
          "Did not expected to find any incomplete matches.");
      Assert.assertTrue(logContent.contains("Found 6 items"), "Expected to find 6 violations matches.");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      File file = new File(filePath);
      Assert.assertTrue(file.delete());
    }
  }

  /**
   * Verify inapplicable only check respected.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void AccessibilityInapplicableCheckRespectsMessageLevel() {
    getWebDriver().navigate().to(TestSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();

    String filePath = ((FileLogger)getLogger()).getFilePath();
    FileLogger fileLogger = new FileLogger(filePath, getTestContext().getName() + ".txt", MessageType.INFORMATION);

    try {
      AccessibilityUtilities.checkAccessibilityInapplicable(getTestObject().getWebDriver(), fileLogger, MessageType.WARNING, false);
      String logContent = Files.lines(Paths.get(fileLogger.getFilePath()),
          StandardCharsets.UTF_8).collect(Collectors.joining(System.lineSeparator()));

      Assert.assertFalse(logContent.contains("Violations check"), "Did not expect violation check");
      Assert.assertFalse(logContent.contains("Passes check"), "Did not expect pass check");
      Assert.assertFalse(logContent.contains("Incomplete check"), "Did not expect incomplete check");

      Assert.assertTrue(logContent.contains("Inapplicable check"), "Did expect inapplicable check");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      File file = new File(filePath);
      Assert.assertTrue(file.delete());
    }
  }

  /**
   * Verify incomplete only check respected
   */
  @Test(groups = TestCategories.SELENIUM)
  public void AccessibilityIncompleteCheckRespectsMessageLevel() {
    getWebDriver().navigate().to(TestSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();

    String filePath = ((FileLogger)getLogger()).getFilePath();
    FileLogger fileLogger = new FileLogger(filePath, getTestContext().getName() + ".txt", MessageType.INFORMATION);

    try {
      AccessibilityUtilities.checkAccessibilityIncomplete(getTestObject().getWebDriver(), fileLogger, MessageType.WARNING, false);
      String logContent = Files.lines(Paths.get(fileLogger.getFilePath()),
          StandardCharsets.UTF_8).collect(Collectors.joining(System.lineSeparator()));
      // String logContent = File.ReadAllText(fileLogger.getFilePath());

      Assert.assertFalse(logContent.contains("Violations check"), "Did not expect violation check");
      Assert.assertFalse(logContent.contains("Passes check"), "Did not expect pass check");
      Assert.assertFalse(logContent.contains("Inapplicable check"), "Did not expect inapplicable check");

      Assert.assertTrue(logContent.contains("Incomplete check"), "Did expect incomplete check");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      File file = new File(filePath);
      Assert.assertTrue(file.delete());
    }
  }

  /**
   * Verify passes only check respected
   */
  @Test(groups = TestCategories.SELENIUM)
  public void AccessibilityPassesCheckRespectsMessageLevel() {
    getWebDriver().navigate().to(TestSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();

    String filePath = ((FileLogger)getLogger()).getFilePath();
    FileLogger fileLogger = new FileLogger(filePath, getTestContext().getName() + ".txt", MessageType.INFORMATION);

    try {
      AccessibilityUtilities.checkAccessibilityPasses(getTestObject().getWebDriver(), fileLogger, MessageType.SUCCESS);
      String logContent = Files.lines(Paths.get(fileLogger.getFilePath()),
          StandardCharsets.UTF_8).collect(Collectors.joining(System.lineSeparator()));
      // String logContent = File.ReadAllText(fileLogger.getFilePath());

      Assert.assertFalse(logContent.contains("Violations check"), "Did not expect violation check");
      Assert.assertFalse(logContent.contains("Inapplicable check"), "Did not expect inapplicable check");
      Assert.assertFalse(logContent.contains("Incomplete check"), "Did not expect incomplete check");

      Assert.assertTrue(logContent.contains("Passes check"), "Did expect pass check");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      File file = new File(filePath);
      Assert.assertTrue(file.delete());
    }
  }

  /**
   * Verify violation only check respected.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void AccessibilityViolationsCheckRespectsMessageLevel() {
    getWebDriver().navigate().to(TestSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();

    String filePath = ((FileLogger)getLogger()).getFilePath();
    FileLogger fileLogger = new FileLogger(filePath, getTestContext().getName() + ".txt", MessageType.INFORMATION);

    try {
      AccessibilityUtilities.checkAccessibilityViolations(getTestObject().getWebDriver(), fileLogger, MessageType.ERROR, false);
      String logContent = Files.lines(Paths.get(fileLogger.getFilePath()),
          StandardCharsets.UTF_8).collect(Collectors.joining(System.lineSeparator()));
      // String logContent = File.ReadAllText(fileLogger.getFilePath());

      Assert.assertFalse(logContent.contains("Passes check"), "Did not expect pass check");
      Assert.assertFalse(logContent.contains("Inapplicable check"), "Did not expect inapplicable check");
      Assert.assertFalse(logContent.contains("Incomplete check"), "Did not expect incomplete check");

      Assert.assertTrue(logContent.contains("Violations check"), "Did expect violation check");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      File file = new File(filePath);
      Assert.assertTrue(file.delete());
    }
  }

  /**
   * Verify accessibility exception will be thrown.
   */
      // [ExpectedException(typeof(ApplicationException), "Expected an accessibility exception to be thrown")]
    @Test(groups = TestCategories.SELENIUM, expectedExceptions = AxeRuntimeException.class)
  public void AccessibilityCheckThrows() {
    getWebDriver().navigate().to(TestSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();

    AccessibilityUtilities.checkAccessibility(getTestObject(), true);
  }

  /**
   * Verify accessibility does not throw when no exception are found
   */
  @Test(groups = TestCategories.SELENIUM)
  public void AccessibilityCheckNoThrowOnNoResults() {
    getWebDriver().navigate().to(TestSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();

    // There should be 0 incomplete items found
    AccessibilityUtilities.checkAccessibilityIncomplete(getTestObject().getWebDriver(),
        getTestObject().getLogger(), MessageType.WARNING, true);
  }

  /**
   * Verify we can get readable results directly
   */
  @Test(groups = TestCategories.SELENIUM)
  public void AccessibilityReadableResults() {
    getWebDriver().navigate().to(TestSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();

    AxeBuilder axeBuilder = new AxeBuilder();
    AxeReporter.getReadableAxeResults("TEST", getWebDriver(), axeBuilder.analyze(getWebDriver()).getViolations());
    String messages = AxeReporter.getAxeResultString();

    Assert.assertTrue(messages.contains("TEST check for"), "Expected header.");
    Assert.assertTrue(messages.contains("Found 6 items"), "Expected to find 6 violations matches.");
  }

  /**
   * Verify we can create and associate an accessibility HTML report.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void AccessibilityHtmlReport() {
    getWebDriver().navigate().to(TestSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();
    AccessibilityUtilities.createAccessibilityHtmlReport(getWebDriver(), getTestObject(), false);

    String file = getTestObject().getArrayOfAssociatedFiles().Last(x -> x.EndsWith(".html"));
    Assert.assertTrue(new FileInfo(file).Length > 0, "Accessibility report is empty");
  }

  /**
   * Verify we can create and associate multiple accessibility HTML reports.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void AccessibilityMultipleHtmlReports() {
    getWebDriver().navigate().to(TestSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();

    // Create 3 reports
    AccessibilityUtilities.createAccessibilityHtmlReport(getWebDriver(), getTestObject(), false);
    AccessibilityUtilities.createAccessibilityHtmlReport(getWebDriver(), getTestObject(), false);
    AccessibilityUtilities.createAccessibilityHtmlReport(getWebDriver(), getTestObject(), false);

    int count = getTestObject().getArrayOfAssociatedFiles().length(x -> x.EndsWith(".html"));
    Assert.assertEquals(count, 3, $"Expected 3 accessibility reports but see {count} instead");
  }

  /**
   * Verify we throw an exception if the scan has an error.
   */
  // [ExpectedException(typeof(ApplicationException))]
  @Test(groups = TestCategories.SELENIUM, expectedExceptions = AxeRuntimeException.class)
  public void AccessibilityHtmlReportWithError() {
    getWebDriver().navigate().to(TestSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();
    AccessibilityUtilities.createAccessibilityHtmlReport(getTestObject(), () -> new Results(
        JSONObject.Parse(AxeResultWithError), false), false);

    String file = getTestObject().getArrayOfAssociatedFiles().Last(x => x.EndsWith(".html"));
    Assert.assertTrue(new FileInfo(file).Length > 0, "Accessibility report is empty");
  }

  /**
   * Verify we throw an exception if the scan has an error and are using lazy elements.
   */
  // [ExpectedException(typeof(ApplicationException))]
  @Test(groups = TestCategories.SELENIUM, expectedExceptions = AxeRuntimeException.class)
  public void AccessibilityHtmlReportWithErrorFromLazyElement() {
    getWebDriver().navigate().to(TestSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();

    LazyWebElement foodTable = new LazyWebElement(getTestObject(), By.id("FoodTable"), "Food Table");

    AccessibilityUtilities.createAccessibilityHtmlReport(getTestObject(), () -> new Results(JSONObject.Parse(AxeResultWithError)), false);

    String file = getTestObject().getArrayOfAssociatedFiles().Last(x -> x.EndsWith(".html"));
    Assert.assertTrue(new FileInfo(file).Length > 0, "Accessibility report is empty");
  }

  /**
   * Verify we throw an exception if there are violations and we choose the throw exception option.
   */
  //    [ExpectedException(typeof(ApplicationException))]
  @Test(groups = TestCategories.SELENIUM, expectedExceptions = AxeRuntimeException.class)
  public void AccessibilityHtmlReportWithViolation() {
    getWebDriver().navigate().to(TestSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();
    AccessibilityUtilities.createAccessibilityHtmlReport(getWebDriver(), getTestObject(), true);
  }

  /**
   * Verify we can create an accessibility HTML report off a lazy element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void AccessibilityHtmlReportWithLazyElement() {
    getWebDriver().navigate().to(TestSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();

    WebElement foodTable = (WebElement) By.id("FoodTable");

    AccessibilityUtilities.createAccessibilityHtmlReport(getWebDriver(), getTestObject(), foodTable, false);

    String file = getTestObject().getArrayOfAssociatedFiles().Last(x -> x.EndsWith(".html"));
    Assert.assertTrue(new FileInfo(file).Length > 0, "Accessibility report is empty");
  }

  /**
   * Verify we can create an accessibility HTML report off a normal web element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void AccessibilityHtmlReportWithElement() {
    getWebDriver().navigate().to(TestSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();
    AccessibilityUtilities.createAccessibilityHtmlReport(getWebDriver(), getTestObject(),
        getWebDriver().findElement(By.id("FoodTable")), false);

    String file = getTestObject().getArrayOfAssociatedFiles().Last(x -> x.EndsWith(".html"));
    Assert.assertTrue(new FileInfo(file).Length > 0, "Accessibility report is empty");
  }

  /**
   * Verify we suppress the JS logging assoicated with running Axe.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void AccessibilityHtmlLogSuppression() throws IOException {
    // Make sure we are not using verbose logging
    getLogger().setLoggingLevel(MessageType.INFORMATION);

    getWebDriver().navigate().to(TestSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();
    AccessibilityUtilities.createAccessibilityHtmlReport(getWebDriver(), getTestObject(), false);

    // The script executed message should be suppressed when we run the accessibility check
    String fileString = Files.readString(Paths.get(((FileLogger)getLogger()).getFilePath()));
    Assert.assertFalse(fileString.contains("Script executed"),
        "Logging was not suppressed as expected");
  }
}