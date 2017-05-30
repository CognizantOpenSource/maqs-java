/*
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium.unitTests;

import com.magenic.jmaqs.selenium.baseSeleniumTest.BaseSeleniumTest;
import com.magenic.jmaqs.selenium.baseSeleniumTest.ElementHandler;
import com.magenic.jmaqs.selenium.baseSeleniumTest.SeleniumConfig;
import com.magenic.jmaqs.utilities.helper.ListProcessor;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit tests for ElementHandler class.
 */
public class ElementHandlerUnitTest extends BaseSeleniumTest {

  /**
   * Url for the site.
   */
  private static String siteUrl = SeleniumConfig.getWebSiteBase();

  /**
   * Automation site url.
   */
  private static String siteAutomationUrl = siteUrl + "Automation/";

  /**
   * Options for computer parts list.
   */
  private static By computerPartsListOptions = By.cssSelector("#computerParts > option");

  /**
   * Swagger link.
   */
  private static By swaggerLinkBy = By.cssSelector("#SwaggerPageLink > a");

  /**
   * First name textbox.
   */
  private static By firstNameTextBox = By.cssSelector("#TextFields > p:nth-child(1) > input[type=\"text\"]");

  /**
   * Female radio button.
   */
  private static By femaleRadioButton = By.cssSelector("#FemaleRadio");

  /**
   * First checkbox.
   */
  private static By checkbox = By.cssSelector("#Checkbox1");

  /**
   * Name dropdown list.
   */
  private static By nameDropdown = By.cssSelector("#namesDropdown");

  /**
   * Computer parts list.
   */
  private static By computerPartsList = By.cssSelector("#computerParts");

  /**
   * Employee link.
   */
  private static By employeeButton = By.cssSelector("#EmployeeButton > a");

  /**
   * Employee page title.
   */
  private static By employeePageTitle = By.cssSelector("body > div.container.body-content > h2");

  /**
   * Unit Test for creating a sorted comma delimited String.
   */
  @Test
  public void createSortedCommaDelimitedStringFromWebElementsTest() {
    String expectedText = "Hard Drive, Keyboard, Monitor, Motherboard, Mouse, Power Supply";
    navigateToUrl();
    Assert.assertEquals(ElementHandler.createCommaDelimitedString(getWebDriver(), computerPartsListOptions, true),
        expectedText, "Expected String does not match actual");
  }

  /**
   * Unit Test for creating a sorted comma delimited String.
   */
  @Test
  public void createCommaDelimitedStringFromWebElementsTest() {
    String expectedText = "Motherboard, Power Supply, Hard Drive, Monitor, Mouse, Keyboard";
    navigateToUrl();
    Assert.assertEquals(ElementHandler.createCommaDelimitedString(getWebDriver(), computerPartsListOptions),
        expectedText, "Expected String does not match actual");
  }

  /**
   * Unit test for entering text into a textbox and getting text from a textbox.
   */
  @Test
  public void setTextBoxAndVerifyValueTest() {
    String expectedValue = "Tester";
    navigateToUrl();
    ElementHandler.setTextBox(getSeleniumWait(), firstNameTextBox, expectedValue);
    String actualValue = ElementHandler.getElementAttribute(getSeleniumWait(), firstNameTextBox);
    verifyText(actualValue, expectedValue);
  }

  /**
   * Unit Test for checking a radio button.
   */
  @Test
  public void checkRadioButtonTest() {
    navigateToUrl();
    ElementHandler.clickButton(getSeleniumWait(), femaleRadioButton, false);
    Assert.assertTrue(getSeleniumWait().waitForClickableElement(femaleRadioButton).isSelected(),
        "Radio button was not selected");
  }

  /**
   * Unit Test for checking a checkbox.
   */
  @Test
  public void checkCheckBoxTest() {
    navigateToUrl();
    ElementHandler.checkCheckBox(getSeleniumWait(), checkbox, true);
    Assert.assertTrue(getSeleniumWait().waitForClickableElement(checkbox).isSelected(), "Checkbox was not enabled");
  }

  /**
   * Unit Test for get element attribute function.
   */
  @Test
  public void getElementAttributeTest() {
    String expectedText = "http://magenicautomation.azurewebsites.net/Swagger";
    navigateToUrl();
    String actualText = ElementHandler.getElementAttribute(getSeleniumWait(), swaggerLinkBy, "href");
    verifyText(actualText, expectedText);
  }

  /**
   * Unit Test for selecting an item from a dropdown and getting the selected item from a dropdown
   * (By actual value).
   */
  @Test
  public void selectItemFromDropDownTest() {
    String expectedSelection = "Emily";
    navigateToUrl();
    ElementHandler.selectDropDownOption(getSeleniumWait(), nameDropdown, expectedSelection);
    String actualSelection = ElementHandler.getSelectedOptionFromDropdown(getSeleniumWait(), nameDropdown);
    verifyText(actualSelection, expectedSelection);
  }

  /**
   * Unit Test for selecting an item from a dropdown and getting the selected item from a dropdown
   * (By list value).
   */
  @Test
  public void selectItemFromDropDownByValueTest() {
    String expectedSelection = "Jack";
    navigateToUrl();
    ElementHandler.selectDropDownOptionByValue(getSeleniumWait(), nameDropdown, "two");
    String actualSelection = ElementHandler.getSelectedOptionFromDropdown(getSeleniumWait(), nameDropdown);
    verifyText(actualSelection, expectedSelection);
  }

  /**
   * Unit Test for selecting multiple items from a list box and getting all selected items in a list
   * box(By actual value).
   */
  @Test
  public void selectMultipleItemsFromListBoxTest() {
    final StringBuilder results = new StringBuilder();
    ArrayList<String> itemsToSelect = new ArrayList<String>();
    itemsToSelect.add("Monitor");
    itemsToSelect.add("Hard Drive");
    itemsToSelect.add("Keyboard");
    navigateToUrl();
    ElementHandler.selectMultipleElementsFromArrayListBox(getSeleniumWait(), computerPartsList, itemsToSelect);
    ArrayList<String> selectedItems = ElementHandler.getSelectedOptionsFromDropdown(getSeleniumWait(),
        computerPartsList);
    ListProcessor.listOfStringsComparer(itemsToSelect, selectedItems, results, false);
    if (results.length() > 0) {
      Assert.fail(results.toString());
    }
  }

  /**
   * Unit Test for selecting multiple items from a list box and getting all selected items in a list
   * box(By list value).
   */
  @Test
  public void selectMultipleItemsFromListBoxTestByValue() {
    ArrayList<String> itemsToSelect = new ArrayList<String>();
    itemsToSelect.add("one");
    itemsToSelect.add("four");
    itemsToSelect.add("five");
    navigateToUrl();
    ElementHandler.selectMultipleElementsFromArrayListBoxByValue(getSeleniumWait(), computerPartsList, itemsToSelect);
    ArrayList<String> selectedItems = ElementHandler.getSelectedOptionsFromDropdown(getSeleniumWait(),
        computerPartsList);

    if (selectedItems.size() != 3) {
      Assert.fail("Does not contain 3 elements: " + selectedItems.toString());
    }
  }

  /**
   * Unit test for ClickElementByJavaScript using a hover dropdown, where dropdown is not visible.
   */
  @Test
  public void clickElementByJavascriptFromHoverDropdown() {
    navigateToUrl();
    ElementHandler.clickElementByJavaScript(getWebDriver(), employeeButton);
    getSeleniumWait().waitForPageLoad();
    getSeleniumWait().waitForExactText(employeePageTitle, "Index");
  }

  /**
   * Test to verify scrolling into view.
   */
  @Test
  public void scrollIntoView() {
    navigateToUrl();
    ElementHandler.scrollIntoView(getWebDriver(), checkbox);
  }

  /**
   * Test to verify scrolling into view.
   */
  @Test
  public void scrollIntoViewWithCoords() {
    navigateToUrl();
    ElementHandler.scrollIntoView(getWebDriver(), checkbox, 50, 0);
  }

  /**
   * Test to verify scrolling into view.
   */
  @Test
  public void executingScrolling() {
    navigateToUrl();
    ElementHandler.executeScrolling(getWebDriver(), 50, 0);
  }

  /**
   * Unit test for ClickElementByJavaScript where the element is not present.
   */
  @Test(expectedExceptions = NoSuchElementException.class)
  public void clickElementByJavascriptFromHoverDropdownNotFound() {
    navigateToUrl();
    ElementHandler.clickElementByJavaScript(getWebDriver(), By.cssSelector(".NotPresent"));
  }

  /**
   * Verify slow type text is correctly typed.
   */
  @Test
  public void slowTypeTest() {
    navigateToUrl();
    ElementHandler.slowType(getSeleniumWait(), firstNameTextBox, "Test input slowtype");
    Assert.assertEquals(getSeleniumWait().waitForClickableElement(firstNameTextBox).getAttribute("value"),
        "Test input slowtype");
  }

  /**
   * Verify two Strings are equal. If not fail test.
   *
   * @param actualValue
   *          Actual displayed text
   * @param expectedValue
   *          Expected text
   */
  private static void verifyText(String actualValue, String expectedValue) {
    Assert.assertEquals(actualValue, expectedValue, "Values are not equal");
  }

  /**
   * Navigate to test page url and wait for page to load.
   */
  private void navigateToUrl() {

    getWebDriver().navigate().to(siteAutomationUrl);
    getSeleniumWait().waitForPageLoad();

  }

}