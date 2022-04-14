/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium;

import com.cognizantsoftvision.maqs.selenium.factories.UIWaitFactory;
import com.cognizantsoftvision.maqs.selenium.pageModel.AutomationPageModel;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Action Builder unit test class.
 */
public class ActionBuilderUnitTest extends BaseSeleniumTest {

  /**
   * Navigates to the specified url test page.
   * @param url the url to be navigated to
   */
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
    ActionBuilder.hoverOver(this.getWebDriver(), automationPageModel.automationDropDown);
    UIWaitFactory.getWaitDriver(
        this.getWebDriver()).waitForClickableElement(automationPageModel.iFrameDropDownButton).click();
    UIWaitFactory.getWaitDriver(
        this.getWebDriver()).waitForExactText(automationPageModel.iFramePageTitle, "Index");
  }

  /**
   * Tests pressing the modifier key.
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
   * Test the move slider functionality.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void moveSliderTest() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    this.navigateToUrl(automationPageModel.testSiteAutomationUrl);
    ActionBuilder.slideElement(this.getWebDriver(), automationPageModel.slider, 50);
    Assert.assertEquals(this.getWebDriver().findElement(
            automationPageModel.sliderLabelNumber).getAttribute("value"), "4");
  }

  /**
   * Tests right-clicking to trigger the context menu.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void rightClickToTriggerContextMenu() {
    AutomationPageModel automationPageModel = new AutomationPageModel(this.getTestObject());
    this.navigateToUrl(automationPageModel.testSiteAutomationUrl);
    ActionBuilder.rightClick(this.getWebDriver(), automationPageModel.rightClickableElementWithContextMenu);
    Assert.assertTrue(this.getWebDriver().findElement(automationPageModel.rightClickContextSaveText).isDisplayed());
  }
}
