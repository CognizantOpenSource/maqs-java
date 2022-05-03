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

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * The Page Driver unit tests.
 */
public class PageDriverUnitTest extends BasePlaywrightTest {

  /**
   * the selector string class.
   */
  ElementPageModel elementPageModel = new ElementPageModel();

  /**
   * Sets up the page model.
   */
  @BeforeMethod
  public void setUp() {
    this.getPageDriver().navigateTo(PageModel.getUrl());
  }

  /**
   * Setup test and make sure we are on the correct test page.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void createPlaywrightPageModel() {
    Assert.assertNotNull(this.getPageDriver().navigateTo(PageModel.getUrl()));
  }

  /**
   * Test check works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void checkTest() {
    Assert.assertFalse(this.getPageDriver().isChecked(elementPageModel.checkbox1));
    this.getPageDriver().check(elementPageModel.checkbox1);
    Assert.assertTrue(this.getPageDriver().isChecked(elementPageModel.checkbox1));
  }

  /**
   * Test set check works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void setCheckTest() {
    Assert.assertFalse(this.getPageDriver().isChecked(elementPageModel.checkbox2));
    this.getPageDriver().setChecked(elementPageModel.checkbox2, false);
    Assert.assertFalse(this.getPageDriver().isChecked(elementPageModel.checkbox2));
    this.getPageDriver().setChecked(elementPageModel.checkbox2, true);
    Assert.assertTrue(this.getPageDriver().isChecked(elementPageModel.checkbox2));
  }

  /**
   * Test click works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void clickTest() {
    Assert.assertFalse(this.getPageDriver().isVisible(elementPageModel.closeButtonShowDialog));
    this.getPageDriver().click(elementPageModel.showDialog1);
    Assert.assertTrue(this.getPageDriver().isEnabled(elementPageModel.closeButtonShowDialog));
  }

  /**
   * Test select option works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void selectOptionTest() {
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

    singleOption = this.getPageDriver().selectOption(elementPageModel.namesDropDown,  new SelectOption[]{options});
    Assert.assertEquals(singleOption.size(), 1);
    Assert.assertEquals(singleOption.get(0), "3");
  }

  /**
   * Test select single option works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void selectMultipleOptionTest() {
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

  /**
   * Test close works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void closeTest() {
    Assert.assertFalse(this.getPageDriver().getAsyncPage().isClosed());
    this.getPageDriver().close();
    Assert.assertTrue(this.getPageDriver().getAsyncPage().isClosed());
  }

  /**
   * Test content works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void contentTest() {
    Assert.assertTrue(this.getPageDriver().content().contains("Softvision"));
  }

  /**
   *  Test double-click works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void doubleClickTest() {
    this.getPageDriver().doubleClick(elementPageModel.namesDropDown);
    Assert.assertFalse(this.getPageDriver().isVisible(elementPageModel.namesDropDownFirstOption));
  }

  /**
   * Test drag and drop works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void dragAndDropTest() {
    this.getPageDriver().dragAndDrop(elementPageModel.html5Draggable, elementPageModel.html5Drop);
  }

  /**
   * Test fill works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void fillTest() {
    this.getPageDriver().fill(elementPageModel.firstNameText, "Ted");
    Assert.assertEquals(this.getPageDriver().inputValue(elementPageModel.firstNameText), "Ted");
  }

  /**
   * Test get attribute works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void getAttributeTest() {
    Assert.assertEquals(this.getPageDriver().getAttribute(elementPageModel.showDialog1, "onclick"),
        "ShowProgressAnimation();");
  }

  /**
   * Test that the press action works.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void pressTest() {
    Assert.assertFalse(this.getPageDriver().isVisible(elementPageModel.closeButtonShowDialog));
    this.getPageDriver().press(elementPageModel.showDialog1, "Enter");
    Assert.assertTrue(this.getPageDriver().isEnabled(elementPageModel.closeButtonShowDialog));
  }

  /**
   * Test query selector works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void querySelectorTest() {
    ElementHandle queryResult = this.getPageDriver().querySelector(elementPageModel.showDialog1);
    Assert.assertTrue(queryResult.isVisible());
  }

  /**
   * Test query select all works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void querySelectorAllTest() {
    List<ElementHandle> results = this.getPageDriver().querySelectorAll("DIV");
    Assert.assertTrue(results.size() > 1, "Selector should have found multiple results");
  }

  /**
   * Test hover works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void hoverTest() {
    this.getPageDriver().hover(elementPageModel.trainingDropdown);
    Assert.assertTrue(this.getPageDriver().isVisible(elementPageModel.trainingOneLink));
  }

  /**
   * Test inner HTML works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void innerHTMLTest() {
    Assert.assertTrue(this.getPageDriver().innerHTML(elementPageModel.footer).contains("Softvision"));
  }

  /**
   * Test inner text works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void innerTextTest() {
    Assert.assertTrue(this.getPageDriver().innerText(elementPageModel.footer).contains("Softvision"));
  }

  /**
   * Test is disabled works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void isDisabledTest() {
    Assert.assertTrue(this.getPageDriver().isDisabled(elementPageModel.disabledField));
    Assert.assertFalse(this.getPageDriver().isDisabled(elementPageModel.firstNameText));
  }

  /**
   * Test is editable works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void isEditableTest() {
    Assert.assertFalse(this.getPageDriver().isEditable(elementPageModel.disabledField));
    Assert.assertTrue(this.getPageDriver().isEditable(elementPageModel.firstNameText));
  }

  /**
   * Test is enabled works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void isEnabledTest() {
    Assert.assertFalse(this.getPageDriver().isEnabled(elementPageModel.disabledField));
    Assert.assertTrue(this.getPageDriver().isEnabled(elementPageModel.firstNameText));
  }

  /// <summary>
  /// Test eventually gone works as expected
  /// </summary>
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void isEventuallyGoneTest() {
    Assert.assertTrue(this.getPageDriver().isEventuallyGone(elementPageModel.notReal));
    Assert.assertFalse(this.getPageDriver().isEventuallyGone(elementPageModel.firstNameText));
  }

  /**
   * Test eventually visible works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void isEventuallyVisibleTest() {
    Assert.assertTrue(this.getPageDriver().isEventuallyVisible(elementPageModel.firstNameText));
    Assert.assertFalse(this.getPageDriver().isEventuallyVisible("NotReal"));
  }

  /**
   * Test is hidden works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void isHiddenTest() {
    Assert.assertFalse(this.getPageDriver().isHidden(elementPageModel.disabledField));
    Assert.assertTrue(this.getPageDriver().isHidden(elementPageModel.trainingOneLink));
    Assert.assertTrue(this.getPageDriver().isHidden("NotReal"));
  }

  /**
   * Test is visible works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void isVisibleTest() {
    Assert.assertTrue(this.getPageDriver().isVisible(elementPageModel.firstNameText));
    Assert.assertFalse(this.getPageDriver().isVisible("NotReal"));
  }

  /**
   * Test set size works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void setViewportSizeTest() {
    this.getPageDriver().setViewportSize(600, 300);
    Assert.assertEquals(this.getPageDriver().getAsyncPage().viewportSize().height, 300);
    Assert.assertEquals(this.getPageDriver().getAsyncPage().viewportSize().width, 600);
  }

  /**
   * Test that the tap action works.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void tapTest() {
    // Switch to a context that supports touch
    BrowserContext newBrowserContext = this.getPageDriver().getParentBrowser()
        .newContext(new Browser.NewContextOptions().setHasTouch(true));

    this.setPageDriver(PageDriverFactory.getNewPageDriverFromBrowserContext(newBrowserContext));
    this.getPageDriver().navigateTo(PageModel.getUrl());

    Assert.assertFalse(this.getPageDriver().isVisible(elementPageModel.closeButtonShowDialog));
    this.getPageDriver().tap(elementPageModel.showDialog1);
    Assert.assertTrue(this.getPageDriver().isEnabled(elementPageModel.closeButtonShowDialog));
  }

  /**
   * Test content works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void textContentTest() {
    Assert.assertEquals(this.getPageDriver().textContent(elementPageModel.showDialog1), "Show dialog");
  }

  /**
   *  Test title works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void titleTest() {
    Assert.assertEquals(this.getPageDriver().title(), "Automation - Magenic Automation Test Site");
  }

  /**
   * Test type and input value work as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void typeAndInputValueTest() {
    this.getPageDriver().type(elementPageModel.firstNameText, "Ted");
    Assert.assertEquals(this.getPageDriver().inputValue(elementPageModel.firstNameText), "Ted");
  }

  /**
   * Test uncheck works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void uncheckTest() {
    this.getPageDriver().uncheck(elementPageModel.checkbox2);
  }

  /**
   * Test wait for load state works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void waitForLoadStateTest() {
    this.getPageDriver().waitForLoadState(LoadState.LOAD);
    Assert.assertTrue(this.getPageDriver().isVisible(elementPageModel.checkbox2));
  }

  /**
   * Test wait for selector works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void waitForSelectorTest() {
    Assert.assertNotNull(this.getPageDriver().waitForSelector(elementPageModel.checkbox2));
    Assert.assertTrue(this.getPageDriver().isVisible(elementPageModel.checkbox2));
  }

  /**
   * Test wait for timeout works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void waitForTimeoutTest() {
    LocalDateTime before = LocalDateTime.now();
    this.getPageDriver().waitForTimeout(1001);
    LocalDateTime afterWait = LocalDateTime.now();

    int duration = before.getSecond() - afterWait.getSecond();
    //Assert.assertTrue(afterWait > before.AddMilliseconds(800) && afterWait < before.AddMilliseconds(1200),
    Assert.assertTrue(afterWait.isAfter(before.plusSeconds(1)) && afterWait.isBefore(before.plusSeconds(2)),
        "Sleep should have been about 1 second but was " + duration + " seconds");
  }

  /**
   * Test wait for url works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void waitForUrlAndNavigationTest() {
    this.getPageDriver().click(elementPageModel.asyncPageLink);
    this.getPageDriver().waitForURL("**/async.html");

    Assert.assertNotNull(this.getPageDriver().goBack());
    this.getPageDriver().waitForURL("**/Static/Automation/");
    Assert.assertEquals(PageModel.getUrl(), this.getPageDriver().getAsyncPage().url());

    Assert.assertNotNull(this.getPageDriver().goForward());
    this.getPageDriver().waitForURL("**/async.html");
    Assert.assertEquals(PageModel.getUrl() + "async.html", this.getPageDriver().getAsyncPage().url());
  }

  /**
   * Test reload works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void reloadTest() {
    String asyncItemSelector = "#Label";
    this.getPageDriver().click(elementPageModel.asyncPageLink);
    Assert.assertTrue(this.getPageDriver().isEventuallyVisible(asyncItemSelector));
    Assert.assertNotNull(this.getPageDriver().reload());
    Assert.assertFalse(this.getPageDriver().isVisible(asyncItemSelector));
  }

  /**
   * Test set content works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void setContentTest() {
    String guid = "a" + UUID.randomUUID();
    this.getPageDriver().setContent("<html><body><div id='" + guid + "'>TEST</div></body></html>");
    this.getPageDriver().waitForTimeout(1000);
    Assert.assertTrue(this.getPageDriver().isEventuallyVisible("#" + guid));
  }

  /**
   * Test eval on select works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void evalOnSelectorTest() {
    Assert.assertEquals(this.getPageDriver().evalOnSelector(
        elementPageModel.computerPartsFourth, "node => node.innerText"), "Monitor");
  }

  /**
   * Test eval on selector all works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void evalOnSelectorAllTest() {
     Object results = this.getPageDriver().evalOnSelectorAll(
        elementPageModel.computerPartsAllOptions, "nodes => nodes.map(n => n.innerText)");
    Assert.assertEquals(((ArrayList<?>) results).size(), 6);
  }

  /**
   * Test eval works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void evaluateTest() {
    Assert.assertEquals(Integer.parseInt(this.getPageDriver().evaluate("1 + 2").toString()), 3);
  }

  /**
   * Test dispatch works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void dispatchEventTest() {
    this.getPageDriver().dispatchEvent(elementPageModel.asyncPageLink, "click");
    Assert.assertTrue(this.getPageDriver().isEventuallyVisible(elementPageModel.alwaysUpOnAsyncPage));
  }

  /**
   * Test input file works as expected
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void setInputFilesTest() {
    FilePayload filePayload = new FilePayload(
        "test.png", "image/png", this.getPageDriver().getAsyncPage().screenshot());
    this.getPageDriver().setInputFiles("#photo", filePayload);
    Assert.assertNotNull(filePayload);

    this.getPageDriver().setInputFiles("#photo", new FilePayload[]{filePayload});
    Assert.assertNotNull(filePayload);
  }

  /**
   * Test focus works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void focusTest() {
    Assert.assertFalse(this.getPageDriver().isVisible(elementPageModel.datePickerDays));
    this.getPageDriver().focus(elementPageModel.datePickerInput);
    Assert.assertTrue(this.getPageDriver().isVisible(elementPageModel.datePickerDays));
  }

  @Test(groups = TestCategories.PLAYWRIGHT)
  public void getUrlTest() {
    Assert.assertEquals(this.getPageDriver().getUrl(),
        "https://cognizantopensource.github.io/maqs-dotnet-templates/Static/Automation/");
  }

  @Test(groups = TestCategories.PLAYWRIGHT)
  public void isClosedTest() {
    this.getPageDriver().close();
    Assert.assertTrue(this.getPageDriver().isClosed());
  }

  /**
   * Test bring to front works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void bringToFrontTest() {
    // Switch to a context that supports touch
    PageDriver newBrowserContext = PageDriverFactory.getNewPageDriverFromBrowserContext(
        this.getPageDriver().getParentBrowser().contexts().get(0));
    this.getPageDriver().bringToFront();
    Assert.assertFalse(newBrowserContext.getAsyncPage().isClosed());
  }

  /**
   * Test add script works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void addInitScriptTest() {
    this.getPageDriver().addInitScript(elementPageModel.renameHeaderFunc);
    this.getPageDriver().reload();
    this.getPageDriver().evaluate("changeMainHeaderName();");
    Assert.assertEquals(this.getPageDriver().innerText(elementPageModel.mainHeader), "NEWNAME");
  }

  /**
   * Test add script works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void addInitScriptFromFileTest() {
    File renameHeaderFile = new File("src/test/resources/renameHeaderFunction.js");
    this.getPageDriver().addInitScript(Paths.get(renameHeaderFile.getAbsolutePath()));
    this.getPageDriver().reload();
    this.getPageDriver().evaluate("changeMainHeaderName();");
    Assert.assertEquals(this.getPageDriver().innerText(elementPageModel.mainHeader), "NEWNAME");
  }

  /**
   * Test add script tag works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void addScriptTagTest() {
    this.getPageDriver().addScriptTag(new Page.AddScriptTagOptions().setContent(elementPageModel.renameHeaderFunc));
    this.getPageDriver().evaluate("changeMainHeaderName();");
    Assert.assertEquals(this.getPageDriver().innerText(elementPageModel.mainHeader), "NEWNAME");
  }

  /**
   * Test add style works as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void addStyleTagTest() {
    Assert.assertTrue(this.getPageDriver().isEventuallyVisible(elementPageModel.checkbox1));
    this.getPageDriver().addStyleTag(new Page.AddStyleTagOptions().setContent("html {display: none;}"));
    this.getPageDriver().waitForTimeout(1001);
    Assert.assertTrue(this.getPageDriver().isEventuallyGone(elementPageModel.checkbox1));
  }


  /**
   * Test set extra headers work as expected.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void setExtraHTTPHeadersTest() {
    this.getPageDriver().setExtraHTTPHeaders(Collections.singletonMap("sample", "value"));
    this.getPageDriver().click(elementPageModel.asyncPageLink);
    this.getPageDriver().isEventuallyVisible(elementPageModel.alwaysUpOnAsyncPage);
  }
}
