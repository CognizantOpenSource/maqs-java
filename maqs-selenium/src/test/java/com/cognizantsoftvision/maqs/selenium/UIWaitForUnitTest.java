package com.cognizantsoftvision.maqs.selenium;

import com.cognizantsoftvision.maqs.selenium.factories.UIWaitFactory;
import com.cognizantsoftvision.maqs.selenium.unittestpagemodel.AsyncPageModel;
import com.cognizantsoftvision.maqs.selenium.unittestpagemodel.AutomationPageModel;
import com.cognizantsoftvision.maqs.selenium.unittestpagemodel.IFramePageModel;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The UI Wait For Unit Test Class.
 */
public class UIWaitForUnitTest extends BaseSeleniumTest {

  /**
   * The Automation Page Model.
   */
  AutomationPageModel automationPageModel;

  /**
   * The IFrame Page Model.
   */
  IFramePageModel iFramePageModel;

  /**
   * The Asynchronous Page Model.
   */
  AsyncPageModel asyncPageModel;

  /**
   * Sets up the page models for the test.
   */
  public void setUp() {
    automationPageModel = new AutomationPageModel(this.getTestObject());
    iFramePageModel = new IFramePageModel(this.getTestObject());
    asyncPageModel = new AsyncPageModel(this.getTestObject());
  }

  /**
   * Tests the functionality that waits for the IFrame to load.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForIFrameToLoad() {
    setUp();
    this.getWebDriver().navigate().to(iFramePageModel.testSiteIFrameUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    wait.waitForPageLoad();
    WebDriverFactory.setBrowserSize(this.getWebDriver(), "Maximize");
    wait.waitForIframeToLoad(iFramePageModel.iframeLocator);
  }

  /**
   * Tests the functionality that waits for the attribute texts equals.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForAttributeTextEqualsFound() {
    setUp();
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    WebElement element = wait.waitForAttributeTextEquals(asyncPageModel.asyncLoadingTextDiv, "style", "display: block;");
    Assert.assertNotNull(element);
    Assert.assertEquals(element.getText(), "Loaded");
  }

  /**
   * Tests the functionality that waits for a clickable element scrolls into view.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForClickableElementAndScrollIntoView() {
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.waitForClickableElementAndScrollIntoView(
        automationPageModel.automationShowDialog1));
    Assert.assertNotNull(wait.waitForClickableElementAndScrollIntoView(
        automationPageModel.automationShowDialog1, 10000, 1000));
  }

  /**
   * Tests the functionality that waits for the present element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForPresentElement() {
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.waitForPresentElement(automationPageModel.flowerTableTitle));
    Assert.assertNotNull(wait.waitForPresentElement(automationPageModel.flowerTableTitle, 10000, 1000));
  }

  /**
   * Tests the functionality that waits for elements.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForElements() {
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertEquals(wait.waitForElements(automationPageModel.flowerTable).size(), 20);
    Assert.assertEquals(wait.waitForElements(automationPageModel.flowerTable, 10000, 1000).size(), 20);
  }

  /**
   * Tests the functionality that waits for an enabled element.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForEnabledElement() {
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.waitForEnabledElement(automationPageModel.flowerTableTitle));
    Assert.assertNotNull(wait.waitForEnabledElement(automationPageModel.flowerTableTitle, 10000, 1000));
  }

  /**
   * Verify WaitForClickableElement wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForClickableElement() {
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    WebElement element = wait.waitForClickableElement(automationPageModel.homeButton);
    Assert.assertNotNull(element, "Null element was returned");
    element = wait.waitForClickableElement(automationPageModel.homeButton, 10000, 1000);
    Assert.assertNotNull(element, "Null element was returned");
  }

  /**
   * Verify WaitForVisibleElement wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForVisibleElement() {
    setUp();
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    WebElement element = wait.waitForVisibleElement(asyncPageModel.asyncDropdownCssSelector);
    Assert.assertNotNull(element, "Null element was returned");
    element = wait.waitForVisibleElement(asyncPageModel.asyncDropdownCssSelector, 10000, 1000);
    Assert.assertNotNull(element, "Null element was returned");
  }

  /**
   * Verify WaitForExactText wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForExactText() {
    setUp();
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    WebElement element = wait.waitForExactText(asyncPageModel.asyncOptionsLabel, "Options");
    Assert.assertNotNull(element, "Null element was returned");
  }

  /**
   * Verify WaitForContainsText wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForContainsText() {
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    WebElement element = wait.waitForContainsText(automationPageModel.automationNamesLabel, "Name");
    Assert.assertNotNull(element, "Null element was returned");
  }

  /**
   * Verify WaitForAbsentElement wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForAbsentElement() {
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    wait.waitForAbsentElement(automationPageModel.notInPage);
  }

  /**
   * Verify WaitForAbsentElement wait fails.
   */
  @Test(groups = TestCategories.SELENIUM, expectedExceptions = TimeoutException.class)
  public void waitForAbsentElementFail() {
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    wait.waitForPageLoad();
    wait.waitForAbsentElement(automationPageModel.homeButton);
  }

  /**
   * Verify WaitForPageLoad wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForPageLoad() {
    setUp();
    getWebDriver().navigate().to(automationPageModel.testSiteUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    wait.waitForPageLoad();
  }

  /**
   * Verify that WaitForAttributeTextEquals throws an exception for instances
   * where the attribute is not found. An attribute check that should have failed
   * to find the given string equal to an elements attribute passed
   */
  @Test(groups = TestCategories.SELENIUM, expectedExceptions = NotFoundException.class)
  public void waitForAttributeEqualsDoesNotFind() {
    setUp();
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    WebElement element = wait.waitForAttributeTextEquals(
        automationPageModel.foodTable, "Flower Table", "Summary");
    Assert.assertEquals(element.getAttribute("Text"), "Flower Table");
  }

  /**
   * Verify that WaitForAttributeTextEquals can find an attribute value after
   * waiting.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForAttributeEqualsFound() {
    setUp();
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.waitForAttributeTextEquals(
        asyncPageModel.asyncLoadingTextDiv, "style", "display: block;"));
    Assert.assertNotNull(wait.waitForAttributeTextEquals(
        asyncPageModel.asyncLoadingTextDiv, "style", "display: block;", 10000, 1000));
  }
}
