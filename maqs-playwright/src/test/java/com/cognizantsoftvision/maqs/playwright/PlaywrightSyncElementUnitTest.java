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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * The Playwright Sync Element unit test.
 */
public class PlaywrightSyncElementUnitTest extends BasePlaywrightTest {

  /**
   * the models map.
   */
  private static final Map<IPlaywrightTestObject, PageModel> models = new HashMap<>();

  /**
   * Setup test and make sure we are on the correct test page.
   */
  @BeforeMethod
  public void createPlaywrightPageModel() {
    PageModel pageModel = new PageModel(this.getTestObject());
    pageModel.openPage();
    models.put(this.getTestObject(), pageModel);
  }

  /**
   * Setup test and make sure we are on the correct test page
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void cleanupPlaywrightPageModel() {
    models.remove(this.getTestObject());
    Assert.assertNull(this.getTestObject());

  }

  /**
   * Test check works as expected
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void checkTest() {
    Assert.assertFalse(models.get(this.getTestObject()).getCheckbox1().isChecked());
    models.get(this.getTestObject()).getCheckbox1().check();
    Assert.assertTrue(models.get(this.getTestObject()).getCheckbox1().isChecked());
  }

  /**
   * Test set check works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void setCheckTest() {
    Assert.assertFalse(models.get(this.getTestObject()).getCheckbox2().isChecked());
    models.get(this.getTestObject()).getCheckbox2().setChecked(false);
    Assert.assertFalse(models.get(this.getTestObject()).getCheckbox2().isChecked());
    models.get(this.getTestObject()).getCheckbox2().setChecked(true);
    Assert.assertTrue(models.get(this.getTestObject()).getCheckbox2().isChecked());
  }

  /**
   * Test click works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void clickTest() {
    Assert.assertFalse(models.get(this.getTestObject()).getCloseButtonShowDialog().isVisible());
    models.get(this.getTestObject()).getShowDialog1().click();
    Assert.assertTrue(models.get(this.getTestObject()).getCloseButtonShowDialog().isEnabled());
  }

  /**
   * Test select option works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void selectOptionTest() {
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

  /**
   * Test select single option works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void selectMultipleOptionTest() {
    List<String> multipleOptions = models.get(this.getTestObject()).getComputerPartsSelection()
        .selectOption(new String[]{"one", "five"});
    Assert.assertEquals(multipleOptions.size(), 2);
    Assert.assertEquals(multipleOptions.get(0), "one");
    Assert.assertEquals(multipleOptions.get(1), "five");

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

  /**
   * Test double-click works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void doubleClickTest() {
    models.get(this.getTestObject()).getNamesDropDown().doubleClick();
    Assert.assertFalse(models.get(this.getTestObject()).getNamesDropDownFirstOption().isVisible());
  }

  /**
   * Test drag and drop works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void dragAndDropTest() {
    models.get(this.getTestObject()).getHtml5Draggable()
        .dragTo(models.get(this.getTestObject()).getHtml5Drop().elementLocator());
  }

  /**
   * Test fill works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void fillTest() {
    models.get(this.getTestObject()).getFirstNameText().fill("Ted");
    Assert.assertEquals(models.get(this.getTestObject()).getFirstNameText().inputValue(), "Ted");
  }

  /**
   * Test get attribute works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void getAttributeTest() {
    Assert.assertEquals(models.get(this.getTestObject()).getShowDialog1().getAttribute("onclick"),
        "ShowProgressAnimation();");
  }

  /**
   * Test that the press action works.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void pressTest() {
    Assert.assertFalse(models.get(this.getTestObject()).getCloseButtonShowDialog().isVisible());
    models.get(this.getTestObject()).getShowDialog1().press("Enter");
    Assert.assertTrue(models.get(this.getTestObject()).getCloseButtonShowDialog().isEnabled());
  }

  /**
   * Test hover works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void hoverTest() {
    models.get(this.getTestObject()).getTrainingDropdown().hover();
    Assert.assertTrue(models.get(this.getTestObject()).getTrainingOneLink().isVisible());
  }

  /**
   * Test inner HTML works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void innerHTMLTest() {
    Assert.assertTrue(models.get(this.getTestObject()).getFooter().innerHTML().contains("Softvision"));
  }

  /**
   * Test inner text works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void innerTextTest() {
    Assert.assertTrue(models.get(this.getTestObject()).getFooter().innerText().contains("Softvision"));
  }

  /**
   * Test is disabled works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void isDisabledTest() {
    Assert.assertTrue(models.get(this.getTestObject()).getDisabledField().isDisabled());
    Assert.assertFalse(models.get(this.getTestObject()).getFirstNameText().isDisabled());
  }

  /**
   * Test is editable works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void isEditableTest() {
    Assert.assertFalse(models.get(this.getTestObject()).getDisabledField().isEditable());
    Assert.assertTrue(models.get(this.getTestObject()).getFirstNameText().isEditable());
  }

  /**
   * Test is enabled works as expected
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void isEnabledTest() {
    Assert.assertFalse(models.get(this.getTestObject()).getDisabledField().isEnabled());
    Assert.assertTrue(models.get(this.getTestObject()).getFirstNameText().isEnabled());
  }

  /**
   * Test eventually gone works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void isEventuallyGoneTest() {
    Assert.assertTrue(models.get(this.getTestObject()).getNotReal().isEventuallyGone());
    Assert.assertFalse(models.get(this.getTestObject()).getFirstNameText().isEventuallyGone());
  }

  /**
   * Test eventually visible works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void isEventuallyVisibleTest() {
    Assert.assertTrue(models.get(this.getTestObject()).getFirstNameText().isEventuallyVisible());
    Assert.assertFalse(models.get(this.getTestObject()).getNotReal().isEventuallyVisible());
  }

  /**
   * Test is hidden works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void isHiddenTest() {
    Assert.assertFalse(models.get(this.getTestObject()).getDisabledField().isHidden());
    Assert.assertTrue(models.get(this.getTestObject()).getTrainingOneLink().isHidden());
    Assert.assertTrue(models.get(this.getTestObject()).getNotReal().isHidden());
  }

  /**
   * Test is visible works as expected
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void isVisibleTest() {
    Assert.assertTrue(models.get(this.getTestObject()).getFirstNameText().isVisible());
    Assert.assertFalse(models.get(this.getTestObject()).getNotReal().isVisible());
  }

  /**
   * Test that the tap action works.
   * TODO this may not work
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void tapTest() {
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

  /**
   * Test context works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void textContentTest() {
    Assert.assertEquals(models.get(this.getTestObject()).getShowDialog1().textContent(), "Show dialog");
  }

  /**
   * Test type and input value work as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void typeAndInputValueTest() {
    models.get(this.getTestObject()).getFirstNameText().type("Ted");
    Assert.assertEquals(models.get(this.getTestObject()).getFirstNameText().inputValue(), "Ted");
  }

  /**
   * Test uncheck works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void uncheckTest() {
    models.get(this.getTestObject()).getCheckbox2().uncheck();
  }

  /**
   * Test eval on selector all works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void evalOnSelectorAllTest() {
    Object results  = models.get(
        this.getTestObject()).getComputerPartsAllOptions().evalOnSelectorAll(
            "nodes => nodes.map(n => n.innerText)");
    Assert.assertEquals(((Map<?, ?>) results).size(), 6);
  }

  /**
   * Test eval works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void evaluateTest() {
    Assert.assertEquals(Integer.parseInt(models.get(
        this.getTestObject()).getShowDialog1().evaluate("1 + 2").toString()), 3);
  }

  /**
   * Test dispatch works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void dispatchEventTest() {
    models.get(this.getTestObject()).getAsyncPageLink().dispatchEvent("click");
    Assert.assertTrue(models.get(this.getTestObject()).getAlwaysUpOnAsyncPage().isEventuallyVisible());
  }

  /**
   * Test input file works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void setInputFilesTest() {
    FilePayload filePayload = new FilePayload(
        "test.png", "image/png", this.getPageDriver().getAsyncPage().screenshot());
    models.get(this.getTestObject()).getUpload().setInputFiles(filePayload);
    Assert.assertNotNull(filePayload);
  }

  /**
   * Test focus works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void focusTest() {
    Assert.assertFalse(models.get(this.getTestObject()).getDatePickerDays().isVisible());
    models.get(this.getTestObject()).getDatePickerInput().focus();
    Assert.assertTrue(models.get(this.getTestObject()).getDatePickerDays().isVisible());
  }

  /**
   * Inline frame test.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void inlineFrameTest() {
    PageModelIFrame frameModel = new PageModelIFrame(this.getTestObject());
    frameModel.openPage();

    Assert.assertFalse(frameModel.getCloseDialog().isVisible());
    frameModel.getShowDialog().click();
    Assert.assertTrue(frameModel.getCloseDialog().isVisible());
  }
}
