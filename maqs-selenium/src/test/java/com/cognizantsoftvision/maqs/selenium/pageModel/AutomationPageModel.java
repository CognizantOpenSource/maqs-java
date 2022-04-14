/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium.pageModel;

import com.cognizantsoftvision.maqs.selenium.LazyWebElement;
import com.cognizantsoftvision.maqs.selenium.SeleniumConfig;
import com.cognizantsoftvision.maqs.selenium.SeleniumTestObject;
import com.cognizantsoftvision.maqs.selenium.UIWait;
import com.cognizantsoftvision.maqs.utilities.helper.exceptions.TimeoutException;
import org.openqa.selenium.By;

/**
 * The Automation page model.
 */
public class AutomationPageModel extends HeaderPageModel {

  /**
   * Unit testing site URL - Automation page.
   */
  public final String testSiteAutomationUrl = SeleniumConfig.getWebSiteBase();

  /**
   * The automation Page Header.
   */
  public final By automationPageHeader = By.cssSelector("#ItemsToAutomate > h2");

  /**
   * First dialog button.
   */
  public final By automationShowDialog1 = By.cssSelector("#showDialog1");

  /**
   * Flower table title.
   */
  public final By flowerTableTitle = By.cssSelector("#FlowerTable > caption > strong");

  /**
   * Flower table.
   */
  public final By flowerTable = By.cssSelector("#FlowerTable TD");

  /**
   * Food table.
   */
  public final By foodTable = By.cssSelector("#FoodTable");

  /**
   * Gets the disabled item.
   */
  public final By disabledField = By.cssSelector("#disabledField > INPUT");

  /**
   * Names label.
   */
  public final By automationNamesLabel = By.cssSelector("#Dropdown > p > strong > label");

  /**
   * Alert button.
   */
  public final By alert = By.id("javascriptAlertButton");

  /**
   * Alert button with confirm option.
   */
  public final By alertWithConfirm = By.id("javascriptConfirmAlertButton");

  /**
   * Error 500 link.
   */
  public final By errorLinkBy = By.cssSelector("#ErrorPageLink > a");

  /**
   * First name text box.
   */
  public final By firstNameTextBox = By.cssSelector("#TextFields > p:nth-child(1) > input[type=\"text\"]");

  /**
   * First checkbox.
   */
  public final By checkbox = By.cssSelector("#Checkbox1");

  /**
   * Computer parts list.
   */
  public final By computerPartsList = By.cssSelector("#computerParts");

  /**
   * Options for computer parts list.
   */
  public final By computerPartsListOptions = By.cssSelector("#computerParts > option");

  /**
   * List box option 1.
   */
  public final By listBoxOption1 = By.cssSelector("#computerParts > option:nth-child(1)");

  /**
   * List box option 2.
   */
  public final By listBoxOption2 = By.cssSelector("#computerParts > option:nth-child(2)");

  /**
   * Female radio button.
   */
  public final By femaleRadioButton = By.cssSelector("#FemaleRadio");
  
  /**
   * Name dropdown list.
   */
  public final By nameDropdown = By.cssSelector("#namesDropdown");

  /**
   * The body selector.
   */
  public By bodyLocator = By.cssSelector("body");

  /**
   *  The body lazy element.
   */
  public LazyWebElement body = new LazyWebElement(getTestObject(), bodyLocator, "Body");

  /**
   * Slider element.
   */
  public final By slider = By.cssSelector("#slider > span");

  /**
   * Slider value label.
   */
  public final By sliderLabelNumber = By.cssSelector("#sliderNumber");

  /**
   * Element with context menu for testing right click.
   */
  public final By rightClickableElementWithContextMenu = By.cssSelector("#rightclickspace");

  /**
   * Text within context menu triggered by right click on specific element.
   */
  public final By rightClickContextSaveText = By.cssSelector("#RightClickSaveText");

  /// <summary>
  /// Training 1 login link
  /// </summary>
  public final By trainingLink = By.cssSelector("A[href='../Training1/LoginPage.html']");

  /**
   * Instantiates a new Automation page model.
   *
   * @param testObject the test object
   */
  public AutomationPageModel(SeleniumTestObject testObject) {
    super(testObject);
  }

  /**
   * Opens the page to the specified url.
   *
   */
  public void open() {
    open(testSiteAutomationUrl);
  }

  /**
   * Is page loaded boolean.
   *
   * @return the boolean
   */
  @Override
  public boolean isPageLoaded() {
    try {
      UIWait wait = new UIWait(this.getWebDriver());
      wait.waitForPageLoad();
      return wait.waitUntilVisibleElement(automationPageHeader);
    } catch (TimeoutException e) {
      e.printStackTrace();
    }
    return false;
  }
}