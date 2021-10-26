/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.utilities.helper.TestCategories;
import com.magenic.jmaqs.utilities.logging.FileLogger;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WrapsDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Unit tests for EventHandler class.
 */

public class EventHandlerUnitTest extends BaseSeleniumTest {

  /**
   * Url for the site.
   */
  private static final String siteUrl = SeleniumConfig.getWebSiteBase();

  /**
   * Automation site url.
   */
  private static final String siteAutomationUrl = siteUrl + "Automation/";

  /**
   * Home button.
   */
  private static final By home = By.cssSelector("#homeButton > a");

  /**
   * Alert button.
   */
  private static final By alertButton = By.id("javascriptAlertButton");

  /**
   * Alert button with confirm option.
   */
  private static final By alertWithConfirm = By.id("javascriptConfirmAlertButton");

  /**
   * Swagger link.
   */
  private static final By swaggerLinkBy = By.cssSelector("#SwaggerPageLink > a");

  /**
   * First name text box.
   */
  private final By firstNameTextBox = By.cssSelector("#TextFields > p:nth-child(1) > input[type=\"text\"]");

  /**
   * First checkbox.
   */
  private static final By checkbox = By.cssSelector("#Checkbox1");

  /**
   * Computer parts list.
   */
  private static final By computerPartsList = By.cssSelector("#computerParts");

  /**
   * Test that checks if the correct messages are logged when clicking an element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerClickElement() {
    // Navigate to the Automation site and set up the event handler
    this.navigateToAutomationSiteUrl();
    WebDriver webDriverWithHandler = getWebDriver();

    // Use the Event Firing Web Driver to click an element, then get the log text
    webDriverWithHandler.findElement(checkbox).click();
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before clicking element"),
        "Expected message to be logged before clicking element.");
    softAssert.assertTrue(logText.contains("Element clicked"), "Expected message to be logged after clicking element.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when changing the value
   * of an element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerChangeValueOf() {
    // Navigate to the Automation site and set up the event handler
    this.navigateToAutomationSiteUrl();
    WebDriver webDriverWithHandler = getWebDriver();

    // Use the Event Firing Web Driver to change the value of an element, then get the log text
    webDriverWithHandler.findElement(firstNameTextBox).clear();
    webDriverWithHandler.findElement(firstNameTextBox).sendKeys("Change Value");
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Element value before clear"),
        "Expected message to be logged before changing the value of element.");
    softAssert.assertTrue(logText.contains("Element value changed to"),
        "Expected message to be logged after changing the value of element.");
    softAssert.assertTrue(logText.contains("Element value before change"),
        "Expected message to be logged before changing the value of element.");
    softAssert.assertTrue(logText.contains("Element value changed to"),
        "Expected message to be logged after changing the value of element.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when finding an element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerFindBy() {
    // Navigate to the Automation site and set up the event handler
    this.navigateToAutomationSiteUrl();
    WebDriver webDriverWithHandler = getWebDriver();

    // Use the Event Firing Web Driver to find an element, then get the log text
    webDriverWithHandler.findElement(computerPartsList);
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before finding element By"),
        "Expected message to be logged before clicking element.");
    softAssert.assertTrue(logText.contains("Found element By"),
        "Expected message to be logged after clicking element.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when navigating back to
   * the previous page.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerNavigateBack() {
    // Navigate to the Automation site and set up the event handler
    this.navigateToAutomationSiteUrl();
    WebDriver webDriverWithHandler = getWebDriver();

    // Use the Event Firing Web Driver to navigate back to a page, then get the log text
    webDriverWithHandler.findElement(home).click();
    webDriverWithHandler.navigate().back();
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before navigating back to previous page"),
        "Expected message to be logged before navigating back to the previous page.");
    softAssert.assertTrue(logText.contains("Navigated back to previous page"),
        "Expected message to be logged after navigating back to the previous page.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when navigating forward
   * to a page.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerNavigateForward() {
    // Navigate to the Automation site and set up the event handler
    this.navigateToAutomationSiteUrl();
    WebDriver webDriverWithHandler = getWebDriver();

    // Use the Event Firing Web Driver to navigate forward to a page, then get the log text
    webDriverWithHandler.findElement(home).click();
    webDriverWithHandler.navigate().back();
    webDriverWithHandler.navigate().forward();
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before navigating forward to next page"),
        "Expected message to be logged before navigating forward to a page.");
    softAssert.assertTrue(logText.contains("Navigated forward to next page"),
        "Expected message to be logged after navigating forward to a page.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when refreshing a page.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerRefresh() {
    // Navigate to the Automation site and set up the event handler
    this.navigateToAutomationSiteUrl();
    WebDriver webDriverWithHandler = getWebDriver();

    // Use the Event Firing Web Driver to refresh the page, then get the log text
    webDriverWithHandler.navigate().refresh();
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before Refreshing the page"),
        "Expected message to be logged before refreshing a page.");
    softAssert.assertTrue(logText.contains("Page refreshed"), "Expected message to be logged after refreshing a page.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when navigating to a
   * page.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerNavigateTo() {
    // Navigate to the Automation site and set up the event handler
    this.navigateToAutomationSiteUrl();
    WebDriver webDriverWithHandler = getWebDriver();

    // Use the Event Firing Web Driver to navigate to a page, then get the log text
    webDriverWithHandler.navigate().to(siteAutomationUrl);
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before navigating to page"),
        "Expected message to be logged before navigating to a page.");
    softAssert.assertTrue(logText.contains("After navigating to page"),
        "Expected message to be logged after navigating to a page.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when running a script.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerScript() {
    // Navigate to the Automation site and set up the event handler
    this.navigateToAutomationSiteUrl();
    WebDriver webDriverWithHandler = getWebDriver();

    // Use the Event Firing Web Driver to execute a script, then get the log text
    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriverWithHandler;
    javascriptExecutor.executeScript("document.querySelector(\"#homeButton > a\");");
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before executing script"),
        "Expected message to be logged before running a script.");
    softAssert.assertTrue(logText.contains("Script executed"), "Expected message to be logged after running a script.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when switching windows.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerSwitchWindow() {
    // Navigate to the Automation site and set up the event handler
    this.navigateToAutomationSiteUrl();
    WebDriver webDriverWithHandler = this.getWebDriver();

    // Use the Event Firing Web Driver to open a new tab, then get the log text
    ((JavascriptExecutor) webDriverWithHandler).executeScript("window.open()");

    SeleniumUtilities.switchToWindow(this.getTestObject(), "");

    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before executing script: window.open()"),
        "Expected message to be logged before executing the script.");
    softAssert.assertTrue(logText.contains("Script executed: window.open()"),
        "Expected message to be logged after executing the script.");
    softAssert.assertTrue(logText.contains("Before switching to window"),
        "Expected message to be logged before switching windows.");
    softAssert.assertTrue(logText.contains("After switching to window"),
        "Expected message to be logged after switching windows.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when accepting an alert.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerAcceptAlert() {
    // Navigate to the Automation site and set up the event handler
    this.navigateToAutomationSiteUrl();
    WebDriver webDriverWithHandler = getWebDriver();

    // Use the Event Firing Web Driver to accept an alert, then get the log text
    UIWait waitDriver = UIWaitFactory.getWaitDriver(((WrapsDriver) this.getWebDriver()).getWrappedDriver());
    waitDriver.waitForClickableElement(alertButton).click();
    Alert alert = webDriverWithHandler.switchTo().alert();
    alert.accept();
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before accepting the alert"),
        "Expected message to be logged before accepting an alert.");
    softAssert
        .assertTrue(logText.contains("Alert accepted"), "Expected message to be logged after accepting an alert.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when dismissing an alert.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerAcceptDismiss() {
    // Navigate to the Automation site and set up the event handler
    this.navigateToAutomationSiteUrl();
    WebDriver webDriverWithHandler = getWebDriver();

    // Use the Event Firing Web Driver to dismiss an alert, then get the log text
    UIWait waitDriver = UIWaitFactory.getWaitDriver(((WrapsDriver)this.getWebDriver()).getWrappedDriver());
    waitDriver.waitForClickableElement(alertWithConfirm).click();
    Alert alert = webDriverWithHandler.switchTo().alert();
    alert.dismiss();
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before dismissing the Alert"),
        "Expected message to be logged before dismissing an alert.");
    softAssert
        .assertTrue(logText.contains("After dismissing the Alert"), "Expected message to be logged after dismissing an alert.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when getting the text
   * from an element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerGetText() {
    // Navigate to the Automation site and set up the event handler
    this.navigateToAutomationSiteUrl();
    WebDriver webDriverWithHandler = getWebDriver();

    // Use the Event Firing Web Driver to get the text from an element, then get the log text
    webDriverWithHandler.findElement(swaggerLinkBy).getText();
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());

    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before getting text from element"),
        "Expected message to be logged before getting text from an element.");
    softAssert.assertTrue(logText.contains("Got element text"),
        "Expected message to be logged after getting text from an element.");
    softAssert.assertAll();
  }

  /**
   * Test that checks if the correct messages are logged when taking a screenshot.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void eventHandlerScreenshot() {
    // Navigate to the Automation site and set up the event handler
    this.navigateToAutomationSiteUrl();
    SeleniumUtilities.captureScreenshot(this.getWebDriver(), this.getTestObject());

    // Use the Event Firing Web Driver to take a screenshot, then get the log text
    String logText = this.readTextFile(((FileLogger) this.getLogger()).getFilePath());
    // Assert the expected Event Handler logs exist.
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(logText.contains("Before screenshot capture"),
        "Expected message to be logged before taking a screenshot.");
    softAssert.assertTrue(logText.contains("After screenshot capture"),
        "Expected message to be logged after taking a screenshot.");
    softAssert.assertAll();
  }

  /**
   * Navigate to test page url and wait for page to load.
   */
  private void navigateToAutomationSiteUrl() {
    getWebDriver().navigate().to(siteAutomationUrl);
    UIWaitFactory.getWaitDriver(getWebDriver()).waitForPageLoad();
  }

  /**
   * Read a file and return it as a string.
   *
   * @param filePath The file path to read
   * @return The contents of the file
   */

  private String readTextFile(String filePath) {
    String text = "";
    try {
      text = new String(Files.readAllBytes(Paths.get(filePath)));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return text;
  }
}
