//--------------------------------------------------
// <copyright file="ElementHandlerUnitTests.java" company="Magenic">
//  Copyright 2017 Magenic, All rights Reserved
// </copyright>
// <summary>Unit Tests for Element Handler class</summary>
//--------------------------------------------------

package com.magenic.jmaqs.selenium.UnitTests;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.magenic.jmaqs.selenium.BaseSeleniumTest.ElementHandler;
import com.magenic.jmaqs.selenium.BaseSeleniumTest.SeleniumConfig;
import com.magenic.jmaqs.selenium.BaseSeleniumTest.SeleniumWait;

import magenic.maqs.utilities.Helper.ListProcessor;

/**
 * Unit tests for ElementHandler class
 * 
 * @author SudeepA
 *
 */
public class ElementHandlerUnitTests {

	/**
	 * Url for the site
	 */
	private static String siteUrl = SeleniumConfig.getWebSiteBase();

	/**
	 * Automation site url
	 */
	private static String siteAutomationUrl = siteUrl + "Automation/";

	/**
	 * Options for computer parts list
	 */
	private static By computerPartsListOptions = By.cssSelector("#computerParts > option");

	/**
	 * Swagger link
	 */
	private static By swaggerLinkBy = By.cssSelector("#SwaggerPageLink > a");

	/**
	 * First name textbox
	 */
	private static By firstNameTextBox = By.cssSelector("#TextFields > p:nth-child(1) > input[type=\"text\"]");

	/**
	 * Female radio button
	 */
	private static By femaleRadioButton = By.cssSelector("#FemaleRadio");

	/**
	 * First checkbox
	 */
	private static By checkbox = By.cssSelector("#Checkbox1");

	/**
	 * Name dropdown list
	 */
	private static By nameDropdown = By.cssSelector("#namesDropdown");

	/**
	 * Computer parts list
	 */
	private static By computerPartsList = By.cssSelector("#computerParts");

	/**
	 * Manage dropdown selector
	 */
	private static By manageDropdown = By.cssSelector(
			"body > div.navbar.navbar-inverse.navbar-fixed-top > div > div.navbar-collapse.collapse > ul > li:nth-child(2) > a");

	/**
	 * Employee link
	 */
	private static By employeeButton = By.cssSelector("#EmployeeButton > a");

	/**
	 * Employee page title
	 */
	private static By employeePageTitle = By.cssSelector("body > div.container.body-content > h2");

	/**
	 * Did the logging folder exist at the start of the test run
	 */
	private static boolean loggingFolderExistsBeforeRun = false;

	/**
	 * Selenium Wait
	 */
	private SeleniumWait seleniumWait;

	/**
	 * The Webdriver
	 */
	private WebDriver webDriver;

	/**
	 * set up method before each test
	 */
	@BeforeMethod
	private void setUp() {
		webDriver = SeleniumConfig.browser();
		seleniumWait = new SeleniumWait(webDriver);
	}

	/**
	 * tear down method after each test
	 */
	@AfterMethod
	private void tearDown() {
		webDriver.quit();
	}

	/**
	 * Unit Test for creating a sorted comma delimited String
	 */
	@Test
	public void createSortedCommaDelimitedStringFromWebElementsTest() {
		String expectedText = "Hard Drive, Keyboard, Monitor, Motherboard, Mouse, Power Supply";
		navigateToUrl();
		Assert.assertEquals(ElementHandler.createCommaDelimitedString(webDriver, computerPartsListOptions, true),
				expectedText, "Expected String does not match actual");
	}

	/**
	 * Unit Test for creating a sorted comma delimited String
	 */
	@Test
	public void createCommaDelimitedStringFromWebElementsTest() {
		String expectedText = "Motherboard, Power Supply, Hard Drive, Monitor, Mouse, Keyboard";
		navigateToUrl();
		Assert.assertEquals(ElementHandler.createCommaDelimitedString(webDriver, computerPartsListOptions),
				expectedText, "Expected String does not match actual");
	}

	/**
	 * Unit test for entering text into a textbox and getting text from a
	 * textbox
	 */
	@Test
	public void setTextBoxAndVerifyValueTest() {
		String expectedValue = "Tester";
		navigateToUrl();
		ElementHandler.setTextBox(seleniumWait, firstNameTextBox, expectedValue);
		String actualValue = ElementHandler.getElementAttribute(seleniumWait, firstNameTextBox);
		verifyText(actualValue, expectedValue);
	}

	/**
	 * Unit Test for checking a radio button
	 */
	@Test
	public void checkRadioButtonTest() {
		navigateToUrl();
		ElementHandler.clickButton(seleniumWait, femaleRadioButton, false);
		Assert.assertTrue(seleniumWait.waitForClickableElement(femaleRadioButton).isSelected(),
				"Radio button was not selected");
	}

	/**
	 * Unit Test for checking a checkbox
	 */
	@Test
	public void checkCheckBoxTest() {
		navigateToUrl();
		ElementHandler.checkCheckBox(seleniumWait, checkbox, true);
		Assert.assertTrue(seleniumWait.waitForClickableElement(checkbox).isSelected(), "Checkbox was not enabled");
	}

	/**
	 * Unit Test for get element attribute function
	 */
	@Test
	public void getElementAttributeTest() {
		String expectedText = "http://magenicautomation.azurewebsites.net/Swagger";
		navigateToUrl();
		String actualText = ElementHandler.getElementAttribute(seleniumWait, swaggerLinkBy, "href");
		verifyText(actualText, expectedText);
	}

	/**
	 * Unit Test for selecting an item from a dropdown and getting the selected
	 * item from a dropdown (By actual value)
	 */
	@Test
	public void selectItemFromDropDownTest() {
		String expectedSelection = "Emily";
		navigateToUrl();
		ElementHandler.selectDropDownOption(seleniumWait, nameDropdown, expectedSelection);
		String actualSelection = ElementHandler.getSelectedOptionFromDropdown(seleniumWait, nameDropdown);
		verifyText(actualSelection, expectedSelection);
	}

	/**
	 * Unit Test for selecting an item from a dropdown and getting the selected
	 * item from a dropdown (By list value)
	 */
	@Test
	public void selectItemFromDropDownByValueTest() {
		String expectedSelection = "Jack";
		navigateToUrl();
		ElementHandler.selectDropDownOptionByValue(seleniumWait, nameDropdown, "two");
		String actualSelection = ElementHandler.getSelectedOptionFromDropdown(seleniumWait, nameDropdown);
		verifyText(actualSelection, expectedSelection);
	}

	/**
	 * Unit Test for selecting multiple items from a list box and getting all
	 * selected items in a list box(By actual value)
	 */
	@Test
	public void selectMultipleItemsFromListBoxTest() {
		StringBuilder results = new StringBuilder();
		ArrayList<String> itemsToSelect = new ArrayList<String>();
		itemsToSelect.add("Monitor");
		itemsToSelect.add("Hard Drive");
		itemsToSelect.add("Keyboard");
		navigateToUrl();
		ElementHandler.selectMultipleElementsFromArrayListBox(seleniumWait, computerPartsList, itemsToSelect);
		ArrayList<String> selectedItems = ElementHandler.getSelectedOptionsFromDropdown(seleniumWait,
				computerPartsList);
		ListProcessor.listOfStringsComparer(itemsToSelect, selectedItems, results, false);
		if (results.length() > 0) {
			Assert.fail(results.toString());
		}
	}

	/**
	 * Unit Test for selecting multiple items from a list box and getting all
	 * selected items in a list box(By list value)
	 */
	@Test
	public void selectMultipleItemsFromListBoxTestByValue() {
		ArrayList<String> itemsToSelect = new ArrayList<String>();
		itemsToSelect.add("one");
		itemsToSelect.add("four");
		itemsToSelect.add("five");
		navigateToUrl();
		ElementHandler.selectMultipleElementsFromArrayListBoxByValue(seleniumWait, computerPartsList, itemsToSelect);
		ArrayList<String> selectedItems = ElementHandler.getSelectedOptionsFromDropdown(seleniumWait,
				computerPartsList);

		if (selectedItems.size() != 3) {
			Assert.fail("Does not contain 3 elements: " + selectedItems.toString());
		}
	}

	/**
	 * Unit test for ClickElementByJavaScript using a hover dropdown, where
	 * dropdown is not visible
	 */
	@Test
	public void clickElementByJavascriptFromHoverDropdown() {
		navigateToUrl();
		ElementHandler.clickElementByJavaScript(webDriver, employeeButton);
		seleniumWait.waitForPageLoad();
		seleniumWait.waitForExactText(employeePageTitle, "Index");
	}

	/**
	 * Test to verify scrolling into view
	 */
	@Test
	public void scrollIntoView() {
		navigateToUrl();
		ElementHandler.scrollIntoView(webDriver, checkbox);
	}

	/**
	 * Test to verify scrolling into view
	 */
	@Test
	public void scrollIntoViewWithCoords() {
		navigateToUrl();
		ElementHandler.scrollIntoView(webDriver, checkbox, 50, 0);
	}

	/**
	 * Test to verify scrolling into view
	 */
	@Test
	public void executingScrolling() {
		navigateToUrl();
		ElementHandler.executeScrolling(webDriver, 50, 0);
	}

	/**
	 * Unit test for ClickElementByJavaScript where the element is not present
	 */
	@Test(expectedExceptions = NoSuchElementException.class)
	public void clickElementByJavascriptFromHoverDropdownNotFound() {
		navigateToUrl();
		ElementHandler.clickElementByJavaScript(webDriver, By.cssSelector(".NotPresent"));
	}

	/**
	 * Verify slow type text is correctly typed
	 */
	@Test
	public void slowTypeTest() {
		navigateToUrl();
		ElementHandler.slowType(seleniumWait, firstNameTextBox, "Test input slowtype");
		Assert.assertEquals(seleniumWait.waitForClickableElement(firstNameTextBox).getAttribute("value"),
				"Test input slowtype");
	}

	/**
	 * Verify two Strings are equal. If not fail test
	 * 
	 * @param actualValue
	 *            Actual displayed text
	 * @param expectedValue
	 *            Expected text
	 */
	private static void verifyText(String actualValue, String expectedValue) {
		Assert.assertEquals(actualValue, expectedValue, "Values are not equal");
	}

	/**
	 * Navigate to test page url and wait for page to load
	 */
	private void navigateToUrl() {

		webDriver.navigate().to(siteAutomationUrl);
		seleniumWait.waitForPageLoad();

	}

}