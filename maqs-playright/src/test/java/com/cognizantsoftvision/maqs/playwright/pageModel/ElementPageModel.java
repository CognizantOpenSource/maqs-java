/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright.pageModel;

/**
 * The Test Page Model class.
 */
public class ElementPageModel {

  /**
   * Main selector.
   */
  public final String mainHeader = "H2";

  /**
   * Rename header Javascript function.
   */
  // TODO:
  public final String renameHeaderFunc = "function changeMainHeaderName() document.querySelector('H2').innerHTML = 'NEWNAME';}";

  /**
   * Should dialog button selector
   */
  public final String showDialog1 = "#showDialog1";

  /**
   * Close dialog selector.
   */
  public final String closeButtonShowDialog = "#CloseButtonShowDialog";

  /**
   * Checkbox 1 selector.
   */
  public final String checkbox1 = "#Checkbox1";

  /**
   * Checkbox 2 selector.
   */
  public final String checkbox2 = "#Checkbox2";

  /**
   * First name input selector.
   */
  public final String firstNameText = "INPUT[name='firstname']";

  /**
   * Main selector.
   */
  public final String disabledField = "#disabledField INPUT";

  /**
   * Async link selector.
   */
  public final String asyncPageLink = "#AsyncPageLink A";

  /**
   * Async element that load right away selector.
   */
  public final String alwaysUpOnAsyncPage = ".roundedcorners";

  /**
   * Training dropdown selector
   */
  public final String trainingDropdown = "#TrainingDropdown";

  /**
   * Training link selector.
   */
  public final String trainingOneLink = "A[href='../Training1/LoginPage.html']";

  /**
   * Footer selector.
   */
  public final String footer = "FOOTER";

  /**
   * Name dropdown selector.
   */
  public final String namesDropDown = "#namesDropdown";

  /**
   * Name option 1 selector.
   */
  public final String namesDropDownFirstOption = "#namesDropdown > OPTION[value='1']";

  /**
   * Computer parts select element selector.
   */
  public final String computerPartsSelection = "#computerParts";

  /**
   * Computer part 2 selector
   */
  public final String computerPartsSecond = "#computerParts option[value='two']";

  /**
   * Computer part 4 selector.
   */
  public final String computerPartsFourth = "#computerParts option[value='four']";

  /**
   * All computer parts options selector
   */
  public final String computerPartsAllOptions = "#computerParts option";
  
  /**
   * HTML 5 draggable image selector.
   */
  public final String html5Draggable = "#draggablleImageHTML5";

  /**
   * HTML 5 drop selector.
   */
  public final String html5Drop = "#div2";

  /**
   * the photo element.
   */
  public final String photo = "#photo";

  /**
   * the date picker input.
   */
  public final String datePickerInput = "#datepicker INPUT";

  /**
   * the date picker days.
   */
  public final String datePickerDays = ".datepicker-days";

  /**
   * the not reach element.
   */
  public final String notReal = "NotReal";

  /**
   * the flower table.
   */
  public final String flowerTable = "#FlowerTable";

  /**
   * the caption strong element.
   */
  public final String captionStrong = "CAPTION > Strong";

  /**
   * the frame element.
   */
  public final String frame = "#frame";
}
