package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.utilities.helper.TestCategories;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ActionBuilderUnitTests extends BaseSeleniumTest {

  private static String siteUrl = SeleniumConfig.getWebSiteBase();
  private static String siteAutomationUrl = siteUrl + "Automation/";
  private static By manageDropdown = By.cssSelector("body > div.navbar.navbar-inverse.navbar-fixed-top > div > div.navbar-collapse.collapse > ul > li:nth-child(2) > a");
  private static By employeeButton = By.cssSelector("#EmployeeButton > a");
  private static By automationPageHeader = By.cssSelector("body > div.container.body-content > h2");
  private static By dialogButton2 = By.cssSelector("#showDialog2");
  private static By employeePageTitle = By.cssSelector("body > div.container.body-content > h2");
  private static By slider = By.cssSelector("#slider > span");
  private static By sliderLabelNumber = By.cssSelector("#sliderNumber");
  private static By rightClickableElementWithContextMenu = By.cssSelector("#rightclickspace");
  private static By rightClickContextSaveText = By.cssSelector("#RightClickSaveText");

  @Test(groups = TestCategories.Selenium)
  public void hoverOverTest() {
    this.navigateToUrl(siteAutomationUrl);
    ActionBuilder.hoverOver(this.getSeleniumWait(), manageDropdown);
    this.getSeleniumWait().waitForClickableElement(employeeButton).click();
    this.getSeleniumWait().waitForExactText(employeePageTitle, "Index");
  }

  // this isnt a valid test at all
  @Test(groups = TestCategories.Selenium)
  public void pressModifierKeyTest() {
    this.navigateToUrl(siteAutomationUrl);
    ActionBuilder.pressModifierKey(this.getSeleniumWait(), Keys.END);
    this.getSeleniumWait().waitForVisibleElement(dialogButton2);
    ActionBuilder.pressModifierKey(this.getSeleniumWait(), Keys.HOME);
    Assert.assertEquals(this.getSeleniumWait().waitForVisibleElement(automationPageHeader).getText(), "Elements to be automated", "Elements are not the same");
  }

  @Test(groups = TestCategories.Selenium)
  public void moveSliderTest() {
    this.navigateToUrl(siteAutomationUrl);
    ActionBuilder.slideElement(this.getSeleniumWait(), slider, 50);
    Assert.assertEquals(this.getWebDriver().findElement(sliderLabelNumber).getAttribute("value"), "4");
  }

  @Test(groups = TestCategories.Selenium)
  public void rightClickToTriggerContextMenu() {
    this.navigateToUrl(siteAutomationUrl);
    ActionBuilder.rightClick(this.getSeleniumWait(), rightClickableElementWithContextMenu);
    Assert.assertTrue(this.getWebDriver().findElement(rightClickContextSaveText).isDisplayed());
  }

  @Test(groups = TestCategories.Selenium, expectedExceptions = TimeoutException.class)
  public void rightClickToTriggerContextMenuNotFound() {
    this.navigateToUrl(siteAutomationUrl);
    ActionBuilder.rightClick(this.getSeleniumWait(), By.cssSelector(".none"));
  }

  private void navigateToUrl(String url)
  {
    this.getWebDriver().navigate().to(url);
    this.getSeleniumWait().waitForPageLoad();
  }
}
