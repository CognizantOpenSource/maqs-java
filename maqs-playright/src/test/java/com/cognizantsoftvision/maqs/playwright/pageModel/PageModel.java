/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright.pageModel;

import com.cognizantsoftvision.maqs.playwright.BasePlaywrightPageModel;
import com.cognizantsoftvision.maqs.playwright.IPlaywrightTestObject;
import com.cognizantsoftvision.maqs.playwright.PageDriver;
import com.cognizantsoftvision.maqs.playwright.PlaywrightConfig;
import com.cognizantsoftvision.maqs.playwright.PlaywrightSyncElement;
import com.cognizantsoftvision.maqs.utilities.logging.ILogger;
import com.cognizantsoftvision.maqs.utilities.performance.IPerfTimerCollection;

/**
 * The Playwright Page Model class for testing.
 */
public class PageModel extends BasePlaywrightPageModel {

  /**
   * The element page model that holds all the elements as a string value.
   */
  static ElementPageModel elementPageModel = new ElementPageModel();

  /**
   * Initializes a new instance of the PageModel class.
   * @param testObject The base Playwright test object
   */
  public PageModel(IPlaywrightTestObject testObject) {
    super(testObject);
  }

  /**
   * Gets the page url.
   * @return the page url string
   */
  public static String getUrl() {
    return PlaywrightConfig.getWebBase();
  }

  /**
   * gets the main header element.
   * @return the main header element
   */
  public PlaywrightSyncElement getMainHeader() {
    return new PlaywrightSyncElement(this.getPageDriver().getAsyncPage(), elementPageModel.mainHeader);
  }

  /**
   * gets the show dialog button element.
   * @return the show dialog button element
   */
  public PlaywrightSyncElement getShowDialog1() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.showDialog1);
  }

  /**
   * gets the close dialog element.
   * @return the close dialog element
   */
  public PlaywrightSyncElement getCloseButtonShowDialog() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.closeButtonShowDialog);
  }

  /**
   * gets the checkbox 1 element.
   * @return the checkbox 1 element
   */
  public PlaywrightSyncElement getCheckbox1() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.checkbox1);
  }

  /**
   * gets the checkbox 2 element.
   * @return the checkbox 2 element
   */
  public PlaywrightSyncElement getCheckbox2() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.checkbox2);
  }

  /**
   * gets the first name input element.
   * @return the first name input element
   */
  public PlaywrightSyncElement getFirstNameText() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.firstNameText);
  }

  /**
   * gets the disabled field element.
   * @return the disabled field element
   */
  public PlaywrightSyncElement getDisabledField() {
    return new PlaywrightSyncElement(this.getPageDriver(),elementPageModel.disabledField);
  }

  /**
   * gets the async page link element.
   * @return the async page link element
   */
  public PlaywrightSyncElement getAsyncPageLink() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.asyncPageLink);
  }

  /**
   * gets the async element that loads right away
   * @return the async element that loads right away
   */
  public PlaywrightSyncElement getAlwaysUpOnAsyncPage() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.alwaysUpOnAsyncPage);
  }

  /**
   * gets the training dropdown element.
   * @return the training dropdown element
   */
  public PlaywrightSyncElement getTrainingDropdown() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.trainingDropdown);
  }

  /**
   * gets the training link element.
   * @return the training link element
   */
  public PlaywrightSyncElement getTrainingOneLink() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.trainingOneLink);
  }

  /**
   * gets the footer element.
   * @return the footer element
   */
  public PlaywrightSyncElement getFooter() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.footer);
  }

  /**
   * gets the name dropdown element.
   * @return the name dropdown element
   */
  public PlaywrightSyncElement getNamesDropDown() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.namesDropDown);
  }

  /**
   * gets the name option 1 element.
   * @return the name option 1 element
   */
  public PlaywrightSyncElement getNamesDropDownFirstOption() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.namesDropDownFirstOption);
  }

  /**
   * gets the computer parts select element.
   * @return the computer arts select element
   */
  public PlaywrightSyncElement getComputerPartsSelection() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.computerPartsSelection);
  }

  /**
   * gets the computer part 2 element.
   * @return the computer part 2 element
   */
  public PlaywrightSyncElement getComputerPartsSecond() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel .computerPartsSecond);
  }

  /**
   * gets the computer part 4 element.
   * @return the computer part 4 element
   */
  public PlaywrightSyncElement getComputerPartsFourth() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.computerPartsFourth);
  }

  /**
   * gets the all computer parts options element.
   * @return the all computer parts options element
   */
  public PlaywrightSyncElement getComputerPartsAllOptions() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.computerPartsAllOptions);
  }

  /**
   * gets the HTML 5 draggable image element.
   * @return the HTML 5 draggable image element
   */
  public PlaywrightSyncElement getHtml5Draggable() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.html5Draggable);
  }

  /**
   * gets the HTML 5 drop location element
   * @return the HTML 5 drop location element
   */
  public PlaywrightSyncElement getHtml5Drop() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.html5Drop);
  }

  /**
   * gets the upload link element.
   * @return the upload link element
   */
  public PlaywrightSyncElement getUpload() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.photo);
  }

  /**
   * gets the date picker input element.
   * @return the date picker input element
   */
  public PlaywrightSyncElement getDatePickerInput() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.datePickerInput);
  }

  /**
   * gets the date picker days element.
    * @return the date picker days element
   */
  public PlaywrightSyncElement getDatePickerDays() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.datePickerDays);
  }

  /// <summary>
  /// Not a real element
  /// </summary>

  /**
   * gets the not real element.
   * @return the not real element
   */
  public PlaywrightSyncElement getNotReal() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.notReal);
  }

  /**
   * gets the flower table parent element.
   * @return the flower table parent element
   */
  public PlaywrightSyncElement getFlowerTablePlaywrightElement() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.flowerTable);
  }

  /**
   * Gets a child element, the second table caption.
   * @return a child element, the second table caption
   */
  public PlaywrightSyncElement getFlowerTableCaptionWithParent() {
    return new PlaywrightSyncElement(this.getFlowerTablePlaywrightElement(), elementPageModel.captionStrong);
  }

  /**
   * navigates to the open page.
   */
  public void openPage() {
    this.getPageDriver().navigateTo(getUrl());
  }

  /**
   * gets the page driver.
   * @return the page driver
   */
  public PageDriver getPageDriver() {
    return this.pageDriver;
  }

  /**
   * gets the logger.
   * @return the logger
   */
  public ILogger getLogger() {
    return this.log;
  }

  /**
   * gets the test object.
   * @return the test object
   */
  public IPlaywrightTestObject getTestObject() {
    return this.testObject;
  }

  /**
   * get the performance timer collection.
   * @return the performance timer collection
   */
  public IPerfTimerCollection getPerfTimerCollection() {
    return this.perfTimerCollection;
  }

  /**
   * check if the page has been loaded.
   * @return true if the page was loaded
   */
  @Override
  public boolean isPageLoaded() {
    return getFlowerTableCaptionWithParent().isVisible();
  }
}
