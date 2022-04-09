/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.cognizantsoftvision.maqs.playwright.pageModel.PageModel;
import com.cognizantsoftvision.maqs.playwright.pageModel.PageModelIFrame;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.options.FilePayload;
import com.microsoft.playwright.options.SelectOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/// <summary>
/// Test page driver
/// </summary>
public class PlaywrightSyncElementUnitTest extends BasePlaywrightTest {

  private static final Map<IPlaywrightTestObject, PageModel> models = new HashMap<>();

  /// <summary>
  /// Setup test and make sure we are on the correct test page
  /// </summary>
  @BeforeTest
  public void createPlaywrightPageModel() {
    var pageModel = new PageModel(this.getTestObject());
    pageModel.openPage();
    models.put(this.getTestObject(), pageModel);
  }

  /// <summary>
  /// Setup test and make sure we are on the correct test page
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void CleanupPlaywrightPageModel() {
    models.remove(this.getTestObject());
    Assert.assertNull(this.getTestObject());
  }

  /// <summary>
  /// Test check works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void CheckTest() {
    Assert.assertFalse(models.get(this.getTestObject()).getCheckbox1().isChecked());
    models.get(this.getTestObject()).getCheckbox1().check();
    Assert.assertTrue(models.get(this.getTestObject()).getCheckbox1().isChecked());
  }

  /// <summary>
  /// Test set check works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void SetCheckTest() {
    Assert.assertFalse(models.get(this.getTestObject()).getCheckbox2().isChecked());
    models.get(this.getTestObject()).getCheckbox2().setChecked(false);
    Assert.assertFalse(models.get(this.getTestObject()).getCheckbox2().isChecked());
    models.get(this.getTestObject()).getCheckbox2().setChecked(true);
    Assert.assertTrue(models.get(this.getTestObject()).getCheckbox2().isChecked());
  }

  /// <summary>
  /// Test click works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void ClickTest() {
    Assert.assertFalse(models.get(this.getTestObject()).getCloseButtonShowDialog().isVisible());
    models.get(this.getTestObject()).getShowDialog1().click();
    Assert.assertTrue(models.get(this.getTestObject()).getCloseButtonShowDialog().isEnabled());
  }

  /// <summary>
  /// Test select option works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void SelectOptionTest() {
    List<String> singleOption = models.get(this.getTestObject()).getNamesDropDown().selectOption("5");
    Assert.assertEquals(singleOption.size(), 1);
    Assert.assertEquals(singleOption.get(0), "5");

    singleOption = models.get(this.getTestObject()).getNamesDropDown()
        .selectOption(new SelectOption().setLabel("Jill"));
    Assert.assertEquals(singleOption.size(), 1);
    Assert.assertEquals(singleOption.get(0), "3");

    ElementHandle joe = this.getPageDriver().querySelector(models.get(this.getTestObject()).getNamesDropDown().getSelector());

    singleOption = models.get(this.getTestObject()).getNamesDropDown().selectOption(joe);
    Assert.assertEquals(singleOption.size(), 1);
    Assert.assertEquals(singleOption.get(0), "1");
  }

  /// <summary>
  /// Test select single option works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void SelectMultipleOptionTest() {
    List<String> multipleOptions = models.get(this.getTestObject()).getComputerPartsSelection()
        .selectOption(new String[]{"one", "five"});
    Assert.assertEquals(multipleOptions.size(), 2);
    Assert.assertEquals(multipleOptions.get(0), "one");
    Assert.assertEquals(multipleOptions.get(0), "five");

    ElementHandle second = models.get(this.getTestObject())
        .getComputerPartsSecond().elementLocator().elementHandle();
    ElementHandle fourth = models.get(this.getTestObject())
        .getComputerPartsFourth().elementLocator().elementHandle();

    multipleOptions = models.get(this.getTestObject()).getComputerPartsSelection()
        .selectOption(new ElementHandle[] { fourth, second });
    Assert.assertEquals(multipleOptions.size(), 2);
    Assert.assertEquals(multipleOptions.get(0), "two");
    Assert.assertEquals(multipleOptions.get(1), "four");
  }

  /// <summary>
  /// Test double click works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void DoubleClickTest() {
    models.get(this.getTestObject()).getNamesDropDown().doubleClick();
    Assert.assertFalse(models.get(this.getTestObject()).getNamesDropDownFirstOption().isVisible());
  }

  /// <summary>
  /// Test drag and drop works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void DragAndDropTest() {
    models.get(this.getTestObject()).getHtml5Draggable()
        .dragTo(models.get(this.getTestObject()).getHtml5Drop().elementLocator());
  }

  /// <summary>
  /// Test fill works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void FillTest() {
    models.get(this.getTestObject()).getFirstNameText().fill("Ted");
    Assert.assertEquals(models.get(this.getTestObject()).getFirstNameText().inputValue(), "Ted");
  }

  /// <summary>
  /// Test get attribute works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void GetAttributeTest() {
    Assert.assertEquals(models.get(this.getTestObject()).getShowDialog1().getAttribute("onclick"),
        "ShowProgressAnimation();");
  }

  /// <summary>
  /// Test that the press action works
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void PressTest() {
    Assert.assertFalse(models.get(this.getTestObject()).getCloseButtonShowDialog().isVisible());
    models.get(this.getTestObject()).getShowDialog1().press("Enter");
    Assert.assertTrue(models.get(this.getTestObject()).getCloseButtonShowDialog().isEnabled());
  }



  /// <summary>
  /// Test hover works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void HoverTest() {
    models.get(this.getTestObject()).getTrainingDropdown().hover();
    Assert.assertTrue(models.get(this.getTestObject()).getTrainingOneLink().isVisible());
  }

  /// <summary>
  /// Test inner HTML works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void InnerHTMLTest() {
    Assert.assertTrue(models.get(this.getTestObject()).getFooter().innerHTML().contains("Softvision"));
  }

  /// <summary>
  /// Test inner text works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void InnerTextTest() {
    Assert.assertTrue(models.get(this.getTestObject()).getFooter().innerText().contains("Softvision"));
  }

  /// <summary>
  /// Test is disabled works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void IsDisabledTest()
  {
    Assert.assertTrue(models.get(this.getTestObject()).getDisabledField().isDisabled());
    Assert.assertFalse(models.get(this.getTestObject()).getFirstNameText().isDisabled());
  }

  /// <summary>
  /// Test is editable works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void IsEditableTest() {
    Assert.assertFalse(models.get(this.getTestObject()).getDisabledField().isEditable());
    Assert.assertTrue(models.get(this.getTestObject()).getFirstNameText().isEditable());
  }

  /// <summary>
  /// Test is enabled works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void IsEnabledTest()
  {
    Assert.assertFalse(models.get(this.getTestObject()).getDisabledField().isEnabled());
    Assert.assertTrue(models.get(this.getTestObject()).getFirstNameText().isEnabled());
  }

  /// <summary>
  /// Test eventually gone works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void IsEventuallyGoneTest() {
    Assert.assertTrue(models.get(this.getTestObject()).getNotReal().isEventuallyGone());
    Assert.assertFalse(models.get(this.getTestObject()).getFirstNameText().isEventuallyGone());
  }

  /// <summary>
  /// Test eventually visible works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void IsEventuallyVisibleTest() {
    Assert.assertTrue(models.get(this.getTestObject()).getFirstNameText().isEventuallyVisible());
    Assert.assertFalse(models.get(this.getTestObject()).getNotReal().isEventuallyVisible());
  }

  /// <summary>
  /// Test is hidden works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void IsHiddenTest() {
    Assert.assertFalse(models.get(this.getTestObject()).getDisabledField().isHidden());
    Assert.assertTrue(models.get(this.getTestObject()).getTrainingOneLink().isHidden());
    Assert.assertTrue(models.get(this.getTestObject()).getNotReal().isHidden());
  }

  /// <summary>
  /// Test is visible works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void IsVisibleTest() {
    Assert.assertTrue(models.get(this.getTestObject()).getFirstNameText().isVisible());
    Assert.assertFalse(models.get(this.getTestObject()).getNotReal().isVisible());
  }


  ////// TODO this may not work
  /// <summary>
  /// Test that the tap action works
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void TapTest() {
    // Switch to a context that supports touch
    BrowserContext newBrowserContext = this.getPageDriver().getParentBrowser()
        .newContext(new Browser.NewContextOptions().setHasTouch(true));

    this.setPageDriver(PageDriverFactory.getNewPageDriverFromBrowserContext(newBrowserContext));
    this.getPageDriver().navigateTo(PageModel.getUrl());
    models.get(this.getTestObject()).overridePageDriver(this.getPageDriver());

    Assert.assertFalse(models.get(this.getTestObject()).getCloseButtonShowDialog().isVisible());
    models.get(this.getTestObject()).getShowDialog1().tap();
    Assert.assertTrue(models.get(this.getTestObject()).getCloseButtonShowDialog().isEnabled());
  }

  /// <summary>
  /// Test context works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void TextContentTest() {
    Assert.assertEquals(models.get(this.getTestObject()).getShowDialog1().textContent(), "Show dialog");
  }

  /// <summary>
  /// Test type and input value work as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void TypeAndInputValueTest() {
    models.get(this.getTestObject()).getFirstNameText().type("Ted");
    Assert.assertEquals(models.get(this.getTestObject()).getFirstNameText().inputValue(), "Ted");
  }

  /// <summary>
  /// Test uncheck works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void UncheckTest() {
    models.get(this.getTestObject()).getCheckbox2().uncheck();
  }


  /// <summary>
  /// Test eval on selector all works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void EvalOnSelectorAllTest() {
    Object results  = models.get(
        this.getTestObject()).getComputerPartsAllOptions().evalOnSelectorAll(
            "nodes -> nodes.map(n -> n.innerText)");
    Assert.assertEquals(((Map<?, ?>) results).size(), 6);
  }

  /// <summary>
  /// Test eval works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void EvaluateTest() {
    Assert.assertEquals(Integer.parseInt(models.get(
        this.getTestObject()).getShowDialog1().evaluate("1 + 2").toString()), 3);
  }

  /// <summary>
  /// Test dispatch works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void DispatchEventTest() {
    models.get(this.getTestObject()).getAsyncPageLink().dispatchEvent("click");
    Assert.assertTrue(models.get(this.getTestObject()).getAlwaysUpOnAsyncPage().isEventuallyVisible());
  }

  /// <summary>
  /// Test input file works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void SetInputFilesTest() {
    FilePayload filePayload = new FilePayload(
        "test.png", "image/png", this.getPageDriver().getAsyncPage().screenshot());
    models.get(this.getTestObject()).getUpload().setInputFiles(filePayload);
    Assert.assertNotNull(filePayload);
  }

  /// <summary>
  /// Test focus works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void FocusTest() {
    Assert.assertFalse(models.get(this.getTestObject()).getDatePickerDays().isVisible());
    models.get(this.getTestObject()).getDatePickerInput().focus();
    Assert.assertTrue(models.get(this.getTestObject()).getDatePickerDays().isVisible());
  }

  /// <summary>
  /// Inline frame test
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void InlineFrameTest() {
    PageModelIFrame frameModel = new PageModelIFrame(this.getTestObject());
    frameModel.openPage();

    Assert.assertFalse(frameModel.getCloseDialog().isVisible());
    frameModel.getShowDialog().click();
    Assert.assertTrue(frameModel.getCloseDialog().isVisible());
  }
}
