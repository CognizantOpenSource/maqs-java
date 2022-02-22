/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium;

import com.cognizantsoftvision.maqs.selenium.factories.UIWaitFactory;
import com.cognizantsoftvision.maqs.selenium.pageModels.AutomationPageModel;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit tests for the ActionBuilder class.
 */
public class ActionBuilderUnitTest extends BaseSeleniumTest {

  /**
   * the Automation page model.
   */
  private AutomationPageModel automationPageModel;

  /**
   * Sets up the test and navigates to the test page.
   */
  public void navigateToTestPage() {
    automationPageModel = new AutomationPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();
  }

  @Test(groups = TestCategories.SELENIUM)
  public void hoverOverTest() {
    navigateToTestPage();
    ActionBuilder.hoverOver(this.getWebDriver(), automationPageModel.manageDropdown);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForClickableElement(automationPageModel.employeeButton)
        .click();
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForExactText(automationPageModel.employeePageTitle, "Index");
  }

  /**
   * Tests pressing the modifier key.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void pressModifierKeyTest() {
    navigateToTestPage();
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForClickableElement(automationPageModel.listBoxOption1)
        .click();
    ActionBuilder.pressModifierKey(this.getWebDriver(), Keys.CONTROL);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForClickableElement(automationPageModel.listBoxOption2)
        .click();

    Assert.assertTrue(
        UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForClickableElement(automationPageModel.listBoxOption1)
            .isSelected());
    Assert.assertTrue(
        UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForClickableElement(automationPageModel.listBoxOption2)
            .isSelected());
  }

  /**
   * Tests moving the slider.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void moveSliderTest() {
    navigateToTestPage();
    ActionBuilder.slideElement(this.getWebDriver(), automationPageModel.slider, 50);
    Assert.assertEquals(this.getWebDriver().findElement(automationPageModel.sliderLabelNumber).getAttribute("value"),
        "4");
  }

  /**
   * Tests right-clicking to trigger the context menu.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void rightClickToTriggerContextMenu() {
    navigateToTestPage();
    ActionBuilder.rightClick(this.getWebDriver(), automationPageModel.rightClickableElementWithContextMenu);
    Assert.assertTrue(this.getWebDriver().findElement(automationPageModel.rightClickContextSaveText).isDisplayed());
  }
}