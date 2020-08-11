package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.utilities.helper.TestCategories;
import com.magenic.jmaqs.utilities.logging.FileLogger;
import com.magenic.jmaqs.utilities.logging.MessageType;
import javafx.application.Application;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.File;

public class AccessibilityUnitTest extends BaseSeleniumTest {
  /**
   * Axe JSON with an error.
   */
  private String AxeResultWithError = "{\"error\":\"AutomationError\",\"results\":{\"testEngine\": { \"name\":\"axe-core\",\"version\":\"3.4.1\"}, \"testRunner\": { \"name\":\"axe\"}, \"testEnvironment\": { \"userAgent\":\"AutoAgent\",\"windowWidth\": 1200, \"windowHeight\": 646, \"orientationAngle\": 0, \"orientationType\":\"landscape-primary\"},\"timestamp\":\"2020-04-14T01:33:59.139Z\",\"url\":\"url\",\"toolOptions\":{\"reporter\":\"v1\"},\"violations\":[],\"passes\":[],\"incomplete\":[],\"inapplicable\": []}}";

  /**
   * Unit testing site URL - Login page.
   */
  private static String TestSiteUrl = SeleniumConfig.getWebSiteBase();

  /**
   * Unit testing site URL - Automation page.
   */
  private static String TestSiteAutomationUrl = TestSiteUrl + "Automation/";

  /**
   * First dialog button.
   */
  private static By AutomationShowDialog1 = By.cssSelector("#showDialog1");

  /**
   * Verify we get verbose message back.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void AccessibilityCheckVerbose() {
    getWebDriver().navigate().to(TestSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();

    String filePath = ((FileLogger)getLogger()).getFilePath();
    AccessibilityUtilities.checkAccessibility(getTestObject(), false);
    String logContent = File.ReadAllText(filePath);

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

    String filePath = Path.GetDirectoryName(((FileLogger)getLogger()).getFilePath());
    FileLogger fileLogger = new FileLogger(filePath, "LevTest.txt", MessageType.WARNING);

    try {
      AccessibilityUtilities.checkAccessibility(getTestObject().getWebDriver(), fileLogger, false);

      String logContent = File.ReadAllText(fileLogger.getFilePath());

      Assert.assertFalse(logContent.contains("Passes check for"),
          "Did not expect expected to check for pass matches.");
      Assert.assertFalse(logContent.contains("Inapplicable check for"),
          "Did not expect expected to check for inapplicable matches.");
      Assert.assertFalse(logContent.contains("Incomplete check for"),
          "Did not expected to find any incomplete matches.");
      Assert.assertTrue(logContent.contains("Found 6 items"), "Expected to find 6 violations matches.");
    } finally {
      File.Delete(fileLogger.FilePath);
    }
  }

  /// <summary>
  ///
  /// </summary>

  /**
   * Verify inapplicable only check respected.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void AccessibilityInapplicableCheckRespectsMessageLevel() {
    getWebDriver().navigate().to(TestSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();

    String filePath = Path.GetDirectoryName(((FileLogger)getLogger()).getFilePath());
    FileLogger fileLogger = new FileLogger(filePath, getTestContext().getName() + ".txt", MessageType.INFORMATION);

    try {
      AccessibilityUtilities.checkAccessibilityInapplicable(getTestObject().getWebDriver(), fileLogger, MessageType.WARNING, false);
      String logContent = File.ReadAllText(fileLogger.getFilePath());

      SoftAssert.IsTrue(!logContent.contains("Violations check"), "Did not expect violation check");
      SoftAssert.IsTrue(!logContent.contains("Passes check"), "Did not expect pass check");
      SoftAssert.IsTrue(!logContent.contains("Incomplete check"), "Did not expect incomplete check");

      SoftAssert.IsTrue(logContent.contains("Inapplicable check"), "Did expect inapplicable check");
    } finally {
      File.Delete(fileLogger.getFilePath());
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

    String filePath = Path.GetDirectoryName(((FileLogger)getLogger()).getFilePath());
    FileLogger fileLogger = new FileLogger(filePath, getTestContext().getName() + ".txt", MessageType.INFORMATION);

    try {
      AccessibilityUtilities.checkAccessibilityIncomplete(getTestObject().getWebDriver(), fileLogger, MessageType.WARNING, false);
      String logContent = File.ReadAllText(fileLogger.getFilePath());

      SoftAssert.isTrue(!logContent.contains("Violations check"), "Did not expect violation check");
      SoftAssert.IsTrue(!logContent.contains("Passes check"), "Did not expect pass check");
      SoftAssert.IsTrue(!logContent.contains("Inapplicable check"), "Did not expect inapplicable check");

      SoftAssert.IsTrue(logContent.contains("Incomplete check"), "Did expect incomplete check");
    } finally {
      File.Delete(fileLogger.getFilePath());
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

    String filePath = Path.GetDirectoryName(((FileLogger)getLogger()).getFilePath());
    FileLogger fileLogger = new FileLogger(filePath, getTestContext().getName() + ".txt", MessageType.INFORMATION);

    try {
      AccessibilityUtilities.checkAccessibilityPasses(getTestObject().getWebDriver(), fileLogger, MessageType.SUCCESS);
      String logContent = File.ReadAllText(fileLogger.getFilePath());

      SoftAssert.IsTrue(!logContent.contains("Violations check"), "Did not expect violation check");
      SoftAssert.IsTrue(!logContent.contains("Inapplicable check"), "Did not expect inapplicable check");
      SoftAssert.IsTrue(!logContent.contains("Incomplete check"), "Did not expect incomplete check");

      SoftAssert.IsTrue(logContent.contains("Passes check"), "Did expect pass check");
    } finally {
      File.Delete(fileLogger.getFilePath());
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

    String filePath = Path.GetDirectoryName(((FileLogger)getLogger()).getFilePath());
    FileLogger fileLogger = new FileLogger(filePath, getTestContext().getName() + ".txt", MessageType.INFORMATION);

    try {
      AccessibilityUtilities.checkAccessibilityViolations(getTestObject().getWebDriver(), fileLogger, MessageType.ERROR, false);
      String logContent = File.ReadAllText(fileLogger.getFilePath());

      SoftAssert.IsTrue(!logContent.contains("Passes check"), "Did not expect pass check");
      SoftAssert.IsTrue(!logContent.contains("Inapplicable check"), "Did not expect inapplicable check");
      SoftAssert.IsTrue(!logContent.contains("Incomplete check"), "Did not expect incomplete check");

      SoftAssert.IsTrue(logContent.contains("Violations check"), "Did expect violation check");
    } finally {
      File.Delete(fileLogger.getFilePath());
    }
  }

  /**
   * Verify accessibility exception will be thrown.
   */
      // [ExpectedException(typeof(ApplicationException), "Expected an accessibility exception to be thrown")]
    @Test(groups = TestCategories.SELENIUM, expectedExceptions = Application.class)
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

    String messages = AccessibilityUtilities.getReadableAxeResults("TEST", getWebDriver(), getWebDriver().Analyze().Violations);

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

    int count = getTestObject().getArrayOfAssociatedFiles().count(x -> x.EndsWith(".html"));
    Assert.assertEquals(count, 3, $"Expected 3 accessibility reports but see {count} instead");
  }

  /**
   * Verify we throw an exception if the scan has an error.
   */
  // [ExpectedException(typeof(ApplicationException))]
  @Test(groups = TestCategories.SELENIUM)
  public void AccessibilityHtmlReportWithError() {
    getWebDriver().navigate().to(TestSiteUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();
    AccessibilityUtilities.createAccessibilityHtmlReport(getTestObject(), () -> new AxeResult(JObject.Parse(AxeResultWithError), false), false);

    String file = getTestObject().getArrayOfAssociatedFiles().Last(x => x.EndsWith(".html"));
    Assert.assertTrue(new FileInfo(file).Length > 0, "Accessibility report is empty");
  }

  /**
   * Verify we throw an exception if the scan has an error and are using lazy elements.
   */
  // [ExpectedException(typeof(ApplicationException))]
  @Test(groups = TestCategories.SELENIUM)
  public void AccessibilityHtmlReportWithErrorFromLazyElement() {
    getWebDriver().navigate().to(TestSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();

    LazyWebElement foodTable = new LazyWebElement(getTestObject(), By.id("FoodTable"), "Food Table");

    AccessibilityUtilities.createAccessibilityHtmlReport(getTestObject(), () -> new AxeResult(JObject.Parse(AxeResultWithError)), false);

    String file = getTestObject().getArrayOfAssociatedFiles().Last(x -> x.EndsWith(".html"));
    Assert.assertTrue(new FileInfo(file).Length > 0, "Accessibility report is empty");
  }

  /**
   * Verify we throw an exception if there are violations and we choose the throw exception option.
   */
  //    [ExpectedException(typeof(ApplicationException))]
  @Test(groups = TestCategories.SELENIUM)
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
  public void AccessibilityHtmlLogSuppression() {
    // Make sure we are not using verbose logging
    getLogger().setLoggingLevel(MessageType.INFORMATION);

    getWebDriver().navigate().to(TestSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(getWebDriver());
    wait.waitForPageLoad();
    AccessibilityUtilities.createAccessibilityHtmlReport(getWebDriver(), getTestObject(), false);

    // The script executed message should be suppressed when we run the accessablity check
    Assert.assertFalse(File.ReadAllText(((FileLogger)getLogger()).getFilePath()).Contains("Script executed"),
        "Logging was not suppressed as expected");
  }
}