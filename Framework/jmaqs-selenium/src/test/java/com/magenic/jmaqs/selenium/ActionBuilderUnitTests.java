package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.utilities.helper.TestCategories;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ActionBuilderUnitTests extends BaseSeleniumTest {

  private static String siteUrl = SeleniumConfig.getWebSiteBase();
  private static String siteAutomationUrl = siteUrl + "Automation/";
  private static By manageDropdown = By.cssSelector("body > div.navbar.navbar-inverse.navbar-fixed-top > div > div.navbar-collapse.collapse > ul > li:nth-child(2) > a");
  private static By employeeButton = By.cssSelector("#EmployeeButton > a");
  private static By employeePageTitle = By.cssSelector("body > div.container.body-content > h2");
  private static By listBoxOption1 = By.cssSelector("#computerParts > option:nth-child(1)");
  private static By listBoxOption2 = By.cssSelector("#computerParts > option:nth-child(2)");
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

  @Test(groups = TestCategories.Selenium)
  public void pressModifierKeyTest() {
    this.navigateToUrl(siteAutomationUrl);

    this.getSeleniumWait().waitForClickableElement(listBoxOption1).click();
    ActionBuilder.pressModifierKey(this.getSeleniumWait(), Keys.CONTROL.toString());
    this.getSeleniumWait().waitForClickableElement(listBoxOption2).click();

    Assert.assertTrue(this.getSeleniumWait().waitForClickableElement(listBoxOption1).isSelected());
    Assert.assertTrue(this.getSeleniumWait().waitForClickableElement(listBoxOption2).isSelected());
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

  private void navigateToUrl(String url)
  {
    this.getWebDriver().navigate().to(url);
    this.getSeleniumWait().waitForPageLoad();
  }
}
