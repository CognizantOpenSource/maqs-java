/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium;

import com.cognizantsoftvision.maqs.selenium.factories.UIWaitFactory;
import com.cognizantsoftvision.maqs.selenium.unittestpagemodel.AutomationPageModel;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit tests for the ActionBuilder class.
 */
public class ActionBuilderUnitTest extends BaseSeleniumTest {

  /**
   * Url for the automation page.
   */
  private static String siteAutomationUrl = SeleniumConfig.getWebSiteBase() + "Automation/";

  /**
   * Manage dropdown selector.
   */
  private static By manageDropdown = By
      .cssSelector("body > div.navbar > div > div > ul > li:nth-child(2) > a");

  /**
   * Employee link.
   */
  private static By employeeButton = By.cssSelector("#EmployeeButton > a");

  /**
   * Employee page title.
   */
  private static By employeePageTitle = By.cssSelector("body > div.container.body-content > h2");

  /**
   * List box option 1.
   */
  private static By listBoxOption1 = By.cssSelector("#computerParts > option:nth-child(1)");

  /**
   * List box option 2.
   */
  private static By listBoxOption2 = By.cssSelector("#computerParts > option:nth-child(2)");

  /**
   * Slider element.
   */
  private static By slider = By.cssSelector("#slider > span");

  /**
   * Slider value label.
   */
  private static By sliderLabelNumber = By.cssSelector("#sliderNumber");

  /**
   * Element with context menu for testing right click.
   */
  private static By rightClickableElementWithContextMenu = By.cssSelector("#rightclickspace");

  /**
   * Text within context menu triggered by right click on specific element.
   */
  private static By rightClickContextSaveText = By.cssSelector("#RightClickSaveText");

  private void navigateToUrl(String url) {
    this.getWebDriver().navigate().to(url);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();
  }

  /**
   * Test hover over functionality.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void hoverOverTest() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    this.navigateToUrl(automationPageModel.testSiteAutomationUrl);
    ActionBuilder.hoverOver(this.getWebDriver(), automationPageModel.manageDropdown);
    UIWaitFactory.getWaitDriver(
        this.getWebDriver()).waitForClickableElement(automationPageModel.employeeButton).click();
    UIWaitFactory.getWaitDriver(
        this.getWebDriver()).waitForExactText(automationPageModel.employeePageTitle, "Index");
  }

  /**
   * Test press modifier key functionality.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void pressModifierKeyTest() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    this.navigateToUrl(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(
        this.getWebDriver()).waitForClickableElement(automationPageModel.listBoxOption1).click();
    ActionBuilder.pressModifierKey(this.getWebDriver(), Keys.CONTROL);
    UIWaitFactory.getWaitDriver(
        this.getWebDriver()).waitForClickableElement(automationPageModel.listBoxOption2).click();

    Assert.assertTrue(UIWaitFactory.getWaitDriver(
        this.getWebDriver()).waitForClickableElement(automationPageModel.listBoxOption1).isSelected());
    Assert.assertTrue(UIWaitFactory.getWaitDriver(
        this.getWebDriver()).waitForClickableElement(automationPageModel.listBoxOption2).isSelected());
  }

  /**
   * Test move slider functionality.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void moveSliderTest() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    this.navigateToUrl(automationPageModel.testSiteAutomationUrl);
    ActionBuilder.slideElement(this.getWebDriver(), slider, 50);
    Assert.assertEquals(this.getWebDriver().findElement(
            automationPageModel.sliderLabelNumber).getAttribute("value"), "4");
  }

  /**
   * Test move right click to trigger context menu functionality.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void rightClickToTriggerContextMenu() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    this.navigateToUrl(automationPageModel.testSiteAutomationUrl);
    ActionBuilder.rightClick(this.getWebDriver(), automationPageModel.rightClickableElementWithContextMenu);
    Assert.assertTrue(this.getWebDriver().findElement(automationPageModel.rightClickContextSaveText).isDisplayed());
  }
}
