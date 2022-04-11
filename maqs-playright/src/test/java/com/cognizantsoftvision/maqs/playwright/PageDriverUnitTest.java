/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.cognizantsoftvision.maqs.playwright.pageModel.ElementPageModel;
import com.cognizantsoftvision.maqs.playwright.pageModel.PageModel;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.FilePayload;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.SelectOption;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/// <summary>
/// Test page driver
/// </summary>
public class PageDriverUnitTest extends BasePlaywrightTest {

  ElementPageModel elementPageModel = new ElementPageModel();

  PageModel pageModel;

  /**
   * Sets up the page model.
   */
  @BeforeTest
  public void setUp() {
    pageModel = new PageModel(this.getTestObject());
  }

  /// <summary>
  /// Setup test and make sure we are on the correct test page
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void CreatePlaywrightPageModel() {
    this.getPageDriver().navigateTo(PageModel.getUrl());
  }

  /// <summary>
  /// Test check works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void CheckTest() {
    Assert.assertFalse(this.getPageDriver().isChecked(elementPageModel.checkbox1));
    this.getPageDriver().check(elementPageModel.checkbox1);
    Assert.assertTrue(this.getPageDriver().isChecked(elementPageModel.checkbox1));
  }

  /// <summary>
  /// Test set check works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void SetCheckTest() {
    Assert.assertFalse(this.getPageDriver().isChecked(elementPageModel.checkbox2));
    this.getPageDriver().setChecked(elementPageModel.checkbox2, false);
    Assert.assertFalse(this.getPageDriver().isChecked(elementPageModel.checkbox2));
    this.getPageDriver().setChecked(elementPageModel.checkbox2, true);
    Assert.assertTrue(this.getPageDriver().isChecked(elementPageModel.checkbox2));
  }

  /// <summary>
  /// Test click works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void ClickTest() {
    Assert.assertFalse(this.getPageDriver().isVisible(elementPageModel.closeButtonShowDialog));
    this.getPageDriver().click(elementPageModel.showDialog1);
    Assert.assertTrue(this.getPageDriver().isEnabled(elementPageModel.closeButtonShowDialog));
  }

  /// <summary>
  /// Test select option works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void SelectOptionTest() {
    List<String> singleOption = this.getPageDriver().selectOption(elementPageModel.namesDropDown, "5");
    Assert.assertEquals(singleOption.size(), 1);
    Assert.assertEquals(singleOption.get(0), "5");

    SelectOption options = new SelectOption();
    options.setLabel("Jill");

    singleOption = this.getPageDriver().selectOption(elementPageModel.namesDropDown, options);
    Assert.assertEquals(singleOption.size(), 1);
    Assert.assertEquals(singleOption.get(0), "3");

    ElementHandle joe = this.getPageDriver().querySelector(elementPageModel.namesDropDownFirstOption);

    singleOption = this.getPageDriver().selectOption(elementPageModel.namesDropDown, joe);
    Assert.assertEquals(singleOption.size(), 1);
    Assert.assertEquals(singleOption.get(0), "1");
  }

  /// <summary>
  /// Test select single option works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void SelectMultipleOptionTest() {
    List<String> multipleOptions = this.getPageDriver().selectOption(elementPageModel.computerPartsSelection, new String[]{"one", "five"});
    Assert.assertEquals( multipleOptions.size(), 2);
    Assert.assertEquals(multipleOptions.get(0), "one");
    Assert.assertEquals(multipleOptions.get(1), "five");

    ElementHandle second = this.getPageDriver().querySelector(elementPageModel.computerPartsSecond);
    ElementHandle fourth = this.getPageDriver().querySelector(elementPageModel.computerPartsFourth);

    multipleOptions = this.getPageDriver().selectOption(
        elementPageModel.computerPartsSelection, new ElementHandle[]{second, fourth});
    Assert.assertEquals(multipleOptions.size(), 2);
    Assert.assertEquals(multipleOptions.get(0), "two");
    Assert.assertEquals(multipleOptions.get(1), "four");
  }

  /// <summary>
  /// Test close works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void CloseTest() {
    Assert.assertFalse(this.getPageDriver().getAsyncPage().isClosed());
    this.getPageDriver().close();
    Assert.assertTrue(this.getPageDriver().getAsyncPage().isClosed());
  }

  /// <summary>
  /// Test content works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void ContentTest() {
    Assert.assertTrue(this.getPageDriver().content().contains("Softvision"));
  }

  /// <summary>
  /// Test double click works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void DoubleClickTest() {
    this.getPageDriver().doubleClick(elementPageModel.namesDropDown);
    Assert.assertFalse(this.getPageDriver().isVisible(elementPageModel.namesDropDownFirstOption));
  }

  /// <summary>
  /// Test drag and drop works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void DragAndDropTest() {
    this.getPageDriver().dragAndDrop(elementPageModel.html5Draggable, elementPageModel.html5Drop);
  }

  /// <summary>
  /// Test fill works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void FillTest() {
    this.getPageDriver().fill(elementPageModel.firstNameText, "Ted");
    Assert.assertEquals(this.getPageDriver().inputValue(elementPageModel.firstNameText), "Ted");
  }

  /// <summary>
  /// Test get attribute works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void GetAttributeTest() {
    Assert.assertEquals(this.getPageDriver().getAttribute(elementPageModel.showDialog1, "onclick"),
        "ShowProgressAnimation();");
  }

  /// <summary>
  /// Test that the press action works
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void PressTest() {
    Assert.assertFalse(this.getPageDriver().isVisible(elementPageModel.closeButtonShowDialog));
    this.getPageDriver().press(elementPageModel.showDialog1, "Enter");
    Assert.assertTrue(this.getPageDriver().isEnabled(elementPageModel.closeButtonShowDialog));
  }

  /// <summary>
  /// Test query selector works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void QuerySelectorTest() {
    ElementHandle queryResult = this.getPageDriver().querySelector(elementPageModel.showDialog1);
    Assert.assertTrue(queryResult.isVisible());
  }

  /// <summary>
  /// Test query select all works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void QuerySelectorAllTest() {
    List<ElementHandle> results = this.getPageDriver().querySelectorAll("DIV");
    Assert.assertTrue(results.size() > 1, "Selector should have found multiple results");
  }

  /// <summary>
  /// Test hover works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void HoverTest() {
    this.getPageDriver().hover(elementPageModel.trainingDropdown);
    Assert.assertTrue(this.getPageDriver().isVisible(elementPageModel.trainingOneLink));
  }

  /// <summary>
  /// Test inner HTML works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void InnerHTMLTest() {
    Assert.assertTrue(this.getPageDriver().innerHTML(elementPageModel.footer).contains("Softvision"));
  }

  /// <summary>
  /// Test inner text works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void InnerTextTest() {
    Assert.assertTrue(this.getPageDriver().innerText(elementPageModel.footer).contains("Softvision"));
  }

  /// <summary>
  /// Test is disabled works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void IsDisabledTest() {
    Assert.assertTrue(this.getPageDriver().isDisabled(elementPageModel.disabledField));
    Assert.assertFalse(this.getPageDriver().isDisabled(elementPageModel.firstNameText));
  }

  /// <summary>
  /// Test is editable works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void IsEditableTest() {
    Assert.assertFalse(this.getPageDriver().isEditable(elementPageModel.disabledField));
    Assert.assertTrue(this.getPageDriver().isEditable(elementPageModel.firstNameText));
  }

  /// <summary>
  /// Test is enabled works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void IsEnabledTest() {
    Assert.assertFalse(this.getPageDriver().isEnabled(elementPageModel.disabledField));
    Assert.assertTrue(this.getPageDriver().isEnabled(elementPageModel.firstNameText));
  }

  /// <summary>
  /// Test eventually gone works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void IsEventuallyGoneTest() {
    Assert.assertTrue(this.getPageDriver().isEventuallyGone("NotReal"));
    Assert.assertFalse(this.getPageDriver().isEventuallyGone(elementPageModel.firstNameText));
  }

  /// <summary>
  /// Test eventually visible works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void IsEventuallyVisibleTest() {
    Assert.assertTrue(this.getPageDriver().isEventuallyVisible(elementPageModel.firstNameText));
    Assert.assertFalse(this.getPageDriver().isEventuallyVisible("NotReal"));
  }

  /// <summary>
  /// Test is hidden works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void IsHiddenTest() {
    Assert.assertFalse(this.getPageDriver().isHidden(elementPageModel.disabledField));
    Assert.assertTrue(this.getPageDriver().isHidden(elementPageModel.trainingOneLink));
    Assert.assertTrue(this.getPageDriver().isHidden("NotReal"));
  }

  /// <summary>
  /// Test is visible works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void isVisibleTest() {
    Assert.assertTrue(this.getPageDriver().isVisible(elementPageModel.firstNameText));
    Assert.assertFalse(this.getPageDriver().isVisible("NotReal"));
  }

  /// <summary>
  /// Test set size works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void SetViewportSizeTest() {
    this.getPageDriver().setViewportSize(600, 300);
    Assert.assertEquals(this.getPageDriver().getAsyncPage().viewportSize().height, 300);
    Assert.assertEquals(this.getPageDriver().getAsyncPage().viewportSize().width, 600);
  }

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

    Assert.assertFalse(this.getPageDriver().isVisible(elementPageModel.closeButtonShowDialog));
    this.getPageDriver().tap(elementPageModel.showDialog1);
    Assert.assertTrue(this.getPageDriver().isEnabled(elementPageModel.closeButtonShowDialog));
  }

  /// <summary>
  /// Test content works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void TextContentTest() {
    Assert.assertEquals(this.getPageDriver().textContent(elementPageModel.showDialog1), "Show dialog");
  }

  /// <summary>
  /// Test title works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void TitleTest() {
    Assert.assertEquals(this.getPageDriver().title(), "Automation - Magenic Automation Test Site");
  }

  /// <summary>
  /// Test type and input value work as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void TypeAndInputValueTest() {
    this.getPageDriver().type(elementPageModel.firstNameText, "Ted");
    Assert.assertEquals(this.getPageDriver().inputValue(elementPageModel.firstNameText), "Ted");
  }

  /// <summary>
  /// Test uncheck works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void UncheckTest()
  {
    this.getPageDriver().uncheck(elementPageModel.checkbox2);
  }

  /// <summary>
  /// Test wait for load state works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void WaitForLoadStateTest() {
    this.getPageDriver().waitForLoadState(LoadState.LOAD);
    Assert.assertTrue(this.getPageDriver().isVisible(elementPageModel.checkbox2));
  }

  /// <summary>
  /// Test wait for selector works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void WaitForSelectorTest() {
    this.getPageDriver().waitForSelector(elementPageModel.checkbox2);
    Assert.assertTrue(this.getPageDriver().isVisible(elementPageModel.checkbox2));
  }

  /// <summary>
  /// Test wait for timeout works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void WaitForTimeoutTest() {
    LocalDateTime before = LocalDateTime.now();
    this.getPageDriver().waitForTimeout(1000);
    LocalDateTime afterWait = LocalDateTime.now();

    int duration = before.getSecond() - afterWait.getSecond();
    //Assert.assertTrue(afterWait > before.AddMilliseconds(800) && afterWait < before.AddMilliseconds(1200),
    Assert.assertTrue(afterWait.isAfter(before.plusSeconds(8)) && afterWait.isAfter(before.plusSeconds(12)),
        "Sleep should have been about 1 second but was " + duration + " seconds");
  }

  /// <summary>
  /// Test wait for url works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void WaitForUrlAndNavigationTest() {
    this.getPageDriver().click(elementPageModel.asyncPageLink);
    this.getPageDriver().waitForURL("**/async.html");

    this.getPageDriver().goBack();
    this.getPageDriver().waitForURL("**/Static/Automation/");
    Assert.assertEquals(PageModel.getUrl(), this.getPageDriver().getAsyncPage().url());

    this.getPageDriver().goForward();
    this.getPageDriver().waitForURL("**/async.html");
    Assert.assertEquals(PageModel.getUrl() + "async.html", this.getPageDriver().getAsyncPage().url());
  }

  /// <summary>
  /// Test reload works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void ReloadTest() {
    String asyncItemSelector = "#Label";
    this.getPageDriver().click(elementPageModel.asyncPageLink);
    Assert.assertTrue(this.getPageDriver().isEventuallyVisible(asyncItemSelector));
    this.getPageDriver().reload();
    Assert.assertFalse(this.getPageDriver().isVisible(asyncItemSelector));
  }

  /// <summary>
  /// Test set content works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void SetContentTest() {
    String guid = UUID.randomUUID().toString();
    this.getPageDriver().setContent("<html><body><div id='" + guid + "'>TEST</div></body></html>");
    this.getPageDriver().waitForTimeout(1000);
    Assert.assertTrue(this.getPageDriver().isEventuallyVisible("#" + guid));
  }

  /// <summary>
  /// Test eval on select works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void EvalOnSelectorTest() {
    Assert.assertEquals(this.getPageDriver().evalOnSelector(
        elementPageModel.computerPartsFourth, "node => node.innerText"), "Monitor");
  }

  /// <summary>
  /// Test eval on selector all works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void EvalOnSelectorAllTest() {
    Assert.assertEquals(this.getPageDriver().evalOnSelectorAll(
        elementPageModel.computerPartsAllOptions, "nodes => nodes.map(n => n.innerText)"),
        6);
  }

  /// <summary>
  /// Test eval works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void EvaluateTest() {
    Assert.assertEquals(Integer.parseInt(this.getPageDriver().evaluate("1 + 2").toString()), 3);
  }

  /// <summary>
  /// Test dispatch works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void DispatchEventTest() {
    this.getPageDriver().dispatchEvent(elementPageModel.asyncPageLink, "click");
    Assert.assertTrue(this.getPageDriver().isEventuallyVisible(elementPageModel.alwaysUpOnAsyncPage));
  }

  /// <summary>
  /// Test input file works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void SetInputFilesTest() {
    FilePayload filePayload = new FilePayload("test.png", "image/png", this.getPageDriver().getAsyncPage().screenshot());
    this.getPageDriver().setInputFiles("#photo", filePayload);
    Assert.assertNotNull(filePayload);
  }

  /// <summary>
  /// Test focus works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void FocusTest() {
    Assert.assertFalse(this.getPageDriver().isVisible(".datepicker-days"));
    this.getPageDriver().focus("#datepicker INPUT");
    Assert.assertTrue(this.getPageDriver().isVisible(".datepicker-days"));
  }

  /// <summary>
  /// Test bring to front works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void BringToFrontTest() {
    // Switch to a context that supports touch
    PageDriver newBrowserContext = PageDriverFactory.getNewPageDriverFromBrowserContext(
        this.getPageDriver().getParentBrowser().contexts().get(0));
    this.getPageDriver().bringToFront();
    Assert.assertFalse(newBrowserContext.getAsyncPage().isClosed());
  }

  /// <summary>
  /// Test add script works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void AddInitScriptTest() {
    this.getPageDriver().addInitScript(elementPageModel.renameHeaderFunc);
    this.getPageDriver().reload();
    this.getPageDriver().evaluate("changeMainHeaderName();");
    Assert.assertEquals(this.getPageDriver().innerText(elementPageModel.mainHeader), "NEWNAME");
  }

  /// <summary>
  /// Test add script tag works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void AddScriptTagTest() {
    this.getPageDriver().addScriptTag(new Page.AddScriptTagOptions().setContent(elementPageModel.renameHeaderFunc));
    this.getPageDriver().evaluate("changeMainHeaderName();");
    Assert.assertEquals(this.getPageDriver().innerText(elementPageModel.mainHeader), "NEWNAME");
  }

  /// <summary>
  /// Test add style works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void AddStyleTagTest() {
    Assert.assertTrue(this.getPageDriver().isEventuallyVisible(elementPageModel.mainHeader));
    this.getPageDriver().addStyleTag(new Page.AddStyleTagOptions().setContent("html {display: none;}"));
    Assert.assertTrue(this.getPageDriver().isEventuallyGone(elementPageModel.mainHeader));
  }

  /// <summary>
  /// Test set extra headers work as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void setExtraHTTPHeadersTest() {
    // TODO: Finish up method
    this.getPageDriver().setExtraHTTPHeaders(Collections.singletonMap("sample", "value"));
//    this.getPageDriver().getAsyncPage().RequestFinished += AsyncPage_RequestFinished;
//    this.getPageDriver().getAsyncPage().waitForRequestFinished() += AsyncPage_RequestFinished;

    this.getPageDriver().click(elementPageModel.asyncPageLink);
    this.getPageDriver().isEventuallyVisible(elementPageModel.alwaysUpOnAsyncPage);
  }
}
