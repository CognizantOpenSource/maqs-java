/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright.pageModel;

import com.cognizantsoftvision.maqs.playwright.*;
import com.cognizantsoftvision.maqs.playwright.pageModel.ElementPageModel;
import com.cognizantsoftvision.maqs.utilities.logging.Logger;
import com.cognizantsoftvision.maqs.utilities.performance.IPerfTimerCollection;

/// <summary>
/// Playwright page model class for testing
/// </summary>
public class PageModel extends BasePlaywrightPageModel {

  static ElementPageModel elementPageModel = new ElementPageModel();

  /// <summary>
  /// Initializes a new instance of the <see cref="PageModel"/> class
  /// </summary>
  /// <param name="testObject">The base Playwright test object</param>
  public PageModel(IPlaywrightTestObject testObject) {
    super(testObject);
  }

  /// <summary>
  /// Get page url
  /// </summary>
  public static String getUrl() {
    return PlaywrightConfig.getWebBase();
  }

  /// <summary>
  /// Main
  /// </summary>
  public PlaywrightSyncElement getMainHeader() {
    return new PlaywrightSyncElement(this.getPageDriver().getAsyncPage(), elementPageModel.mainHeader);
  }

  /// <summary>
  /// Should dialog button
  /// </summary>
  public PlaywrightSyncElement getShowDialog1() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.showDialog1);
  }

  /// <summary>
  /// Close dialog
  /// </summary>
  public PlaywrightSyncElement getCloseButtonShowDialog() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.closeButtonShowDialog);
  }

  /// <summary>
  /// Checkbox 1
  /// </summary>
  public PlaywrightSyncElement getCheckbox1() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.checkbox1);
  }

  /// <summary>
  /// Checkbox 2
  /// </summary>
  public PlaywrightSyncElement getCheckbox2() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.checkbox2);
  }

  /// <summary>
  /// First name input
  /// </summary>
  public PlaywrightSyncElement getFirstNameText() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.firstNameText);
  }

  /// <summary>
  /// Main
  /// </summary>
  public PlaywrightSyncElement getDisabledField() {
    return new PlaywrightSyncElement(this.getPageDriver(),elementPageModel.disabledField);
  }

  /// <summary>
  /// Async link
  /// </summary>
  public PlaywrightSyncElement getAsyncPageLink() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.asyncPageLink);
  }

  /// <summary>
  /// Async element that load right away
  /// </summary>
  public PlaywrightSyncElement getAlwaysUpOnAsyncPage() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.alwaysUpOnAsyncPage);
  }

  /// <summary>
  /// Trainging dropdown
  /// </summary>
  public PlaywrightSyncElement getTrainingDropdown() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.trainingDropdown);
  }

  /// <summary>
  /// Training link
  /// </summary>
  public PlaywrightSyncElement getTrainingOneLink() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.trainingOneLink);
  }

  /// <summary>
  /// Footer
  /// </summary>
  public PlaywrightSyncElement getFooter() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.footer);
  }

  /// <summary>
  /// Name dropdown
  /// </summary>
  public PlaywrightSyncElement getNamesDropDown() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.namesDropDown);
  }

  /// <summary>
  /// Name option 1
  /// </summary>
  public PlaywrightSyncElement getNamesDropDownFirstOption() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.namesDropDownFirstOption);
  }

  /// <summary>
  /// Computer parts select element
  /// </summary>
  public PlaywrightSyncElement getComputerPartsSelection() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.computerPartsSelection);
  }

  /// <summary>
  /// Computer part 2
  /// </summary>
  public PlaywrightSyncElement getComputerPartsSecond() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel .computerPartsSecond);
  }

  /// <summary>
  /// Computer part 4
  /// </summary>
  public PlaywrightSyncElement getComputerPartsFourth() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.computerPartsFourth);
  }

  /// <summary>
  /// All computer parts options
  /// </summary>
  public PlaywrightSyncElement getComputerPartsAllOptions() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.computerPartsAllOptions);
  }

  /// <summary>
  /// HTML 5 draggable image
  /// </summary>
  public PlaywrightSyncElement getHtml5Draggable() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.html5Draggable);
  }

  /// <summary>
  /// HTML 5 drop location
  /// </summary>
  public PlaywrightSyncElement getHtml5Drop() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.html5Drop);
  }

  /// <summary>
  /// Upload link
  /// </summary>
  public PlaywrightSyncElement getUpload() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.photo);
  }

  /// <summary>
  /// Date picker input
  /// </summary>
  public PlaywrightSyncElement getDatePickerInput() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.datePickerInput);
  }

  /// <summary>
  /// Date picker days
  /// </summary>
  public PlaywrightSyncElement getDatePickerDays() {
    return new PlaywrightSyncElement(this.getPageDriver(), elementPageModel.datePickerDays);
  }

  /// <summary>
  /// Not a real element
  /// </summary>
  public PlaywrightSyncElement getNotReal() {
    return new PlaywrightSyncElement(this.getPageDriver(), "#NotReal");
  }

  /// <summary>
  /// Gets a parent element
  /// </summary>
  public PlaywrightSyncElement getFlowerTablePlaywrightElement() {
    return new PlaywrightSyncElement(this.getPageDriver(), "#FlowerTable");
  }

  /// <summary>
  /// Gets a child element, the second table caption
  /// </summary>
  public PlaywrightSyncElement getFlowerTableCaptionWithParent() {
    return new PlaywrightSyncElement(this.getFlowerTablePlaywrightElement(), "CAPTION > Strong");
  }

  /// <summary>
  /// Open the page
  /// </summary>
  public void openPage() {
    this.getPageDriver().navigateTo(getUrl());
  }

  /// <summary>
  /// Get page driver
  /// </summary>
  /// <returns>The page driver</returns>
  public PageDriver getPageDriver() {
    return this.pageDriver;
  }

  /// <summary>
  /// Get logger
  /// </summary>
  /// <returns>The logger</returns>
  public Logger getLogger() {
    return this.log;
  }

  /// <summary>
  /// Get test object
  /// </summary>
  /// <returns>The test object</returns>
  public IPlaywrightTestObject getTestObject() {
    return this.testObject;
  }

  /// <summary>
  /// Get performance timer collection
  /// </summary>
  /// <returns>The performance timer collection</returns>
  public IPerfTimerCollection getPerfTimerCollection() {
    return this.perfTimerCollection;
  }

  /// <summary>
  /// Check if the page has been loaded
  /// </summary>
  /// <returns>True if the page was loaded</returns>
  @Override
  public boolean isPageLoaded() {
    return getFlowerTableCaptionWithParent().isVisible();
  }
}
