/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.selenium.factories.UIWaitFactory;
import com.magenic.jmaqs.selenium.pageModels.AutomationPageModel;
import com.magenic.jmaqs.utilities.helper.TestCategories;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Unit tests for the ActionBuilder class.
 */
public class ActionBuilderUnitTest extends BaseSeleniumTest {

  /**
   * the Automation page model.
   */
  private AutomationPageModel automationPageModel;

  @BeforeMethod()
  public void navigateToTestPage() {
    automationPageModel = new AutomationPageModel(this.getTestObject());
    this.getWebDriver().navigate().to(automationPageModel.testSiteAutomationUrl);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForPageLoad();
  }

  @Test(groups = TestCategories.SELENIUM)
  public void hoverOverTest() {
    ActionBuilder.hoverOver(this.getWebDriver(), automationPageModel.manageDropdown);
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForClickableElement(automationPageModel.employeeButton)
        .click();
    UIWaitFactory.getWaitDriver(this.getWebDriver()).waitForExactText(automationPageModel.employeePageTitle, "Index");
  }

  @Test(groups = TestCategories.SELENIUM)
  public void pressModifierKeyTest() {
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

  @Test(groups = TestCategories.SELENIUM)
  public void moveSliderTest() {
    ActionBuilder.slideElement(this.getWebDriver(), automationPageModel.slider, 50);
    Assert.assertEquals(this.getWebDriver().findElement(automationPageModel.sliderLabelNumber).getAttribute("value"),
        "4");
  }

  @Test(groups = TestCategories.SELENIUM)
  public void rightClickToTriggerContextMenu() {
    ActionBuilder.rightClick(this.getWebDriver(), automationPageModel.rightClickableElementWithContextMenu);
    Assert.assertTrue(this.getWebDriver().findElement(automationPageModel.rightClickContextSaveText).isDisplayed());
  }
}
