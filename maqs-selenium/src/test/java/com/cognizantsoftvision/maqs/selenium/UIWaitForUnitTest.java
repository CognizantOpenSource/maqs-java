package com.cognizantsoftvision.maqs.selenium;

import com.cognizantsoftvision.maqs.selenium.factories.UIWaitFactory;
import com.cognizantsoftvision.maqs.selenium.pageModel.AsyncPageModel;
import com.cognizantsoftvision.maqs.selenium.pageModel.AutomationPageModel;
import com.cognizantsoftvision.maqs.selenium.pageModel.IFramePageModel;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The UI Wait For unit test class.
 */
public class UIWaitForUnitTest extends BaseSeleniumTest {

  /**
   * Tests the functionality that waits for the IFrame to load.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForIFrameToLoad() {
    IFramePageModel iFramePageModel = new IFramePageModel(this.getTestObject());
    this.getWebDriver().navigate().to(iFramePageModel.testSiteIFrameUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    wait.waitForPageLoad();
    WebDriverFactory.setBrowserSize(this.getWebDriver(), "Maximize");
    wait.waitForIframeToLoad(iFramePageModel.iframeLocator);

    this.getWebDriver().navigate().to(iFramePageModel.testSiteIFrameUrl);
    wait.waitForPageLoad();
    wait.waitForIframeToLoad(iFramePageModel.iframeLocator, 5000, 1000);
  }

  /**
   * Tests the functionality that waits for the attribute texts equals.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForAttributeTextEqualsFound() {
    AsyncPageModel asyncPageModel = new AsyncPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    wait.waitForVisibleElement(asyncPageModel.asyncLoadingTextDiv);
    WebElement element = wait.waitForAttributeTextEquals(
        asyncPageModel.asyncLoadingTextDiv, "style", "");
    Assert.assertNotNull(element);
    Assert.assertEquals(element.getText(), "Loaded");
  }

  /**
   * Tests the functionality that waits for a clickable element scrolls into view.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForClickableElementAndScrollIntoView() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
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
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
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
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
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
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.waitForEnabledElement(automationPageModel.flowerTableTitle));
    Assert.assertNotNull(wait.waitForEnabledElement(automationPageModel.flowerTableTitle, 10000, 1000));
  }

  /**
   * Tests the functionality that throws an exception if the enabled element is not found.
   */
  @Test(groups = TestCategories.SELENIUM, expectedExceptions = ElementNotInteractableException.class)
  public void waitForEnabledElementException() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    Assert.assertNotNull(wait.waitForEnabledElement(automationPageModel.disabledField));
  }

  /**
   * Verify WaitForClickableElement wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForClickableElement() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
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
    AsyncPageModel asyncPageModel = new AsyncPageModel(this.getTestObject());
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
    AsyncPageModel asyncPageModel = new AsyncPageModel(this.getTestObject());
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
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    WebElement element = wait.waitForContainsText(automationPageModel.automationNamesLabel, "Name");
    Assert.assertNotNull(element, "Null element was returned");
  }

  /**
   * Verify WaitForContainsText wait throws an exception if the method fails.
   */
  @Test(groups = TestCategories.SELENIUM, expectedExceptions = NotFoundException.class)
  public void waitForContainsTextException() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();

    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    wait.waitForContainsText(automationPageModel.notInPage, "Names");
  }

  /**
   * Verify WaitForAbsentElement wait works.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void waitForAbsentElement() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
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
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
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
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
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
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
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
    AsyncPageModel asyncPageModel = new AsyncPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(asyncPageModel.testSiteAsyncUrl);
    UIWait wait = UIWaitFactory.getWaitDriver(this.getWebDriver());
    wait.waitForPageLoad();
    wait.waitForVisibleElement(asyncPageModel.asyncLoadingTextDiv);

    Assert.assertNotNull(wait.waitForAttributeTextEquals(
        asyncPageModel.asyncLoadingTextDiv, "style", ""));
    Assert.assertNotNull(wait.waitForAttributeTextEquals(
        asyncPageModel.asyncLoadingTextDiv, "style", "", 10000, 1000));
  }
}
