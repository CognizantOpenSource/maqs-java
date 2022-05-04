/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.FilePayload;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

/**
 * The Playwright Synchronous Element class.
 */
public class PlaywrightSyncElement {

  /**
   * Initializes a new instance of the Playwright Sync Element class.
   * @param page the playwright page to be set
   * @param selector the selector of the element
   */
  public PlaywrightSyncElement(Page page, String selector) {
    this(page, selector, null);
  }

  /**
   * Initializes a new instance of the Playwright Sync Element class.
   * @param page The associated playwright page
   * @param selector Element selector
   * @param options Advanced locator options
   */
  public PlaywrightSyncElement(Page page, String selector, Page.LocatorOptions options) {
    this.setParentPage(page);
    this.setSelector(selector);
    this.setPageOptions(options);
  }

  /**
   * Initializes a new instance of the Playwright Sync Element class.
   * @param parent the parent Playwright sync element
   * @param selector Element selector
   */
  public PlaywrightSyncElement(PlaywrightSyncElement parent, String selector) {
    this(parent, selector, null);
  }

  /**
   * Initializes a new instance of the Playwright Sync Element class.
   * @param parent The parent playwright element
   * @param selector Sub element selector
   * @param options Advanced locator options
   */
  public PlaywrightSyncElement(PlaywrightSyncElement parent, String selector, Locator.LocatorOptions options) {
    this.setParentLocator(parent.elementLocator());
    this.setSelector(selector);
    this.setLocatorOptions(options);
  }

  /**
   * Initializes a new instance of the Playwright Sync Element class.
   * @param frame The associated playwright frame locator
   * @param selector Element selector
   */
  public PlaywrightSyncElement(FrameLocator frame, String selector) {
    this.setParentFrameLocator(frame);
    this.setSelector(selector);
  }

  /**
   * Initializes a new instance of the Playwright Sync Element class.
   * @param testObject The associated playwright test object
   * @param selector Element selector
   * @param options Advanced locator options
   */
  public PlaywrightSyncElement(IPlaywrightTestObject testObject, String selector, Page.LocatorOptions options) {
    this(testObject.getPageDriver().getAsyncPage(), selector, options);
  }

  /**
   * Initializes a new instance of the Playwright Sync Element class.
   * @param driver The associated playwright page driver
   * @param selector Element selector
   */
  public PlaywrightSyncElement(PageDriver driver, String selector) {
    this(driver, selector, null);
  }

  /**
   * Initializes a new instance of the Playwright Sync Element class.
   * @param driver The associated playwright page driver
   * @param selector Element selector
   * @param options Advanced locator options
   */
  public PlaywrightSyncElement(PageDriver driver, String selector, Page.LocatorOptions options) {
    this(driver.getAsyncPage(), selector, options);
  }

  /**
   * the underlying async page object.
   */
  private Page parentPage;

  /**
   * Gets the underlying async page object.
   * @return the async page object
   */
  public Page getParentPage() {
    return this.parentPage;
  }

  /**
   * Sets the underlying async page object.
   * @param newParentPage the new page object
   */
  private void setParentPage(Page newParentPage) {
    this.parentPage = newParentPage;
  }

  /**
   * The underlying async page object.
   */
  private Locator parentLocator;

  /**
   * Gets the underlying async page object.
   * @return the locator
   */
  public Locator getParentLocator() {
    return this.parentLocator;
  }

  /**
   * Sets the underlying async page object.
   * @param newParentLocator the new parent locator
   */
  private void setParentLocator(Locator newParentLocator) {
    this.parentLocator = newParentLocator;
  }

  /**
   * The underlying async page object.
   */
  private FrameLocator parentFrameLocator;

  /**
   * Gets the underlying async page object.
   * @return the locator
   */
  public FrameLocator getParentFrameLocator() {
    return this.parentFrameLocator;
  }

  /**
   * Sets the underlying async page object.
   * @param newParentLocator the new parent locator
   */
  private void setParentFrameLocator(FrameLocator newParentLocator) {
    this.parentFrameLocator = newParentLocator;
  }

  /**
   * the page locator options.
   */
  private Page.LocatorOptions pageOptions;

  /**
   * gets the page locator options.
   * @return the page locator options
   */
  public Page.LocatorOptions getPageOptions() {
    return this.pageOptions;
  }

  /**
   * sets the page locator options.
   * @param newPageOptions the page locator options
   */
  private void setPageOptions(Page.LocatorOptions newPageOptions) {
    this.pageOptions = newPageOptions;
  }

  /**
   * the page locator options.
   */
  private Locator.LocatorOptions locatorOptions;

  /**
   * gets the page locator options.
   * @return the locator options
   */
  public Locator.LocatorOptions getLocatorOptions() {
    return this.locatorOptions;
  }

  /**
   * set the locator options.
   * @param newLocatorOptions the locator options
   */
  private void setLocatorOptions(Locator.LocatorOptions newLocatorOptions) {
    this.locatorOptions = newLocatorOptions;
  }

  /**
   * The selector string.
   */
  private String selector;

  /**
   * Gets the selector string.
   * @return the selector string
   */
  public String getSelector() {
    return this.selector;
  }

  /**
   * Sets the selector string.
   * @param newSelector the new selector string
   */
  private void setSelector(String newSelector) {
    this.selector = newSelector;
  }

  /**
   * locator for this element.
   * @return the locator of the element
   */
  public Locator elementLocator() {
    if (this.getParentPage() != null) {
      return getPageOptions() != null ? this.getParentPage().locator(getSelector(), getPageOptions())
          : this.getParentPage().locator(getSelector());
    } else if (this.getParentLocator() != null) {
      return getLocatorOptions() != null ? this.getParentLocator().locator(getSelector(), getLocatorOptions())
          : this.getParentLocator().locator(getSelector());
    } else if (this.getParentFrameLocator() != null) {
      return this.getParentFrameLocator().locator(getSelector());
    }
    throw new NullPointerException("Both parent Page and PlaywrightElement are null");
  }

  /**
   * checks the element.
   */
  public void check() {
    this.check(null);
  }

  /**
   * checks the element.
   * @param options the check options
   */
  public void check(Locator.CheckOptions options) {
    elementLocator().check(options);
  }

  /**
   * click the element.
   */
  public void click() {
    this.click(null);
  }

  /**
   * click the element.
   * @param options the click options
   */
  public void click(Locator.ClickOptions options) {
    elementLocator().click(options);
  }

  /**
   * double click the element.
   */
  public void doubleClick() {
    this.doubleClick(null);
  }

  /**
   * double click the element.
   * @param options the double click options
   */
  public void doubleClick(Locator.DblclickOptions options) {
    elementLocator().dblclick(options);
  }

  /**
   * dispatch an event.
   * @param type the type to be dispatched
   */
  public void dispatchEvent(String type) {
    this.dispatchEvent(type, null);
  }

  /**
   * dispatch an event.
   * @param type the type to be dispatched
   * @param eventInit the event to be initialized
   */
  public void dispatchEvent(String type, Object eventInit) {
    this.dispatchEvent(type, eventInit, null);
  }

  /**
   * dispatch an event.
   * @param type the type to be dispatched
   * @param eventInit the event to be initialized
   * @param options the dispatch event options
   */
  public void dispatchEvent(String type, Object eventInit, Locator.DispatchEventOptions options) {
    elementLocator().dispatchEvent(type, eventInit, options);
  }

  /**
   * drag to an element.
   * @param target the element locator
   */
  public void dragTo(Locator target) {
    this.dragTo(target, null);
  }

  /**
   * drag to an element.
   * @param target the element locator
   * @param options the drag to options
   */
  public void dragTo(Locator target, Locator.DragToOptions options) {
    elementLocator().dragTo(target, options);
  }

  /**
   * fill the element.
   * @param value the value to be entered
   */
  public void fill(String value) {
    this.fill(value,null);
  }

  /**
   * fill the element.
   * @param value the value to be entered
   * @param options the fill options
   */
  public void fill(String value, Locator.FillOptions options) {
    elementLocator().fill(value, options);
  }

  /**
   * focus on an element.
   */
  public void focus() {
    this.focus(null);
  }

  /**
   * focus on an element.
   * @param options the focus options
   */
  public void focus(Locator.FocusOptions options) {
    elementLocator().focus(options);
  }

  /**
   * hover over.
   */
  public void hover() {
    this.hover(null);
  }

  /**
   * hover over.
   * @param options the hover options
   */
  public void hover(Locator.HoverOptions options) {
    elementLocator().hover(options);
  }

  /**
   * press a key.
   * @param key the key to be pressed
   */
  public void press(String key) {
    this.press(key, null);
  }

  /**
   * press a key.
   * @param key the key to be pressed
   * @param options the press options
   */
  public void press(String key, Locator.PressOptions options) {
    elementLocator().press(key, options);
  }

  /**
   * set the element as checked.
   * @param checkedState if the element should be checked
   */
  public void setChecked(boolean checkedState) {
    this.setChecked(checkedState, null);
  }

  /**
   * set the element as checked.
   * @param checkedState if the element should be checked
   * @param options the set checked options
   */
  public void setChecked(boolean checkedState, Locator.SetCheckedOptions options) {
    elementLocator().setChecked(checkedState, options);
  }

  /**
   * sets the input files.
   * @param files the files to be inputted
   */
  public void setInputFiles(FilePayload files) {
    this.setInputFiles(files, null);
  }

  /**
   * sets the input files.
   * @param files the files to be inputted
   * @param options the set input files options
   */
  public void setInputFiles(FilePayload files, Locator.SetInputFilesOptions options) {
    elementLocator().setInputFiles(files, options);
  }

  /**
   * sets the input files.
   * @param files the files to be inputted
   */
  public void setInputFiles(FilePayload[] files) {
    this.setInputFiles(files, null);
  }

  /**
   * sets the input files.
   * @param files the files to be inputted
   * @param options the set input files options
   */
  public void setInputFiles(FilePayload[] files, Locator.SetInputFilesOptions options) {
    elementLocator().setInputFiles(files, options);
  }

  /**
   * sets the input files.
   * @param files the files to be inputted
   */
  public void setInputFiles(Path[] files) {
    this.setInputFiles(files, null);
  }

  /**
   * sets the input files.
   * @param files the files to be inputted
   * @param options the set input files options
   */
  public void setInputFiles(Path[] files, Locator.SetInputFilesOptions options) {
    elementLocator().setInputFiles(files, options);
  }

  /**
   * sets the input files.
   * @param files the files to be inputted
   */
  public void setInputFiles(Path files) {
    this.setInputFiles(files, null);
  }

  /**
   * sets the input files.
   * @param files the files to be inputted
   * @param options the set input files options
   */
  public void setInputFiles(Path files, Locator.SetInputFilesOptions options) {
    elementLocator().setInputFiles(files, options);
  }

  /**
   * tap the element.
   */
  public void tap() {
    this.tap(null);
  }

  /**
   * tap the element.
   * @param options the tap options
   */
  public void tap(Locator.TapOptions options) {
    elementLocator().tap(options);
  }

  /**
   * the typing text into the element.
   * @param text the text of the type
   */
  public void type(String text) {
    this.type(text, null);
  }

  /**
   * the typing text into the element.
   * @param text the text of the type
   * @param options the type options
   */
  public void type(String text, Locator.TypeOptions options) {
    elementLocator().type(text, options);
  }

  /**
   * uncheck an element.
   */
  public void uncheck() {
    this.uncheck(null);
  }

  /**
   * uncheck an element.
   * @param options the is checked options
   */
  public void uncheck(Locator.UncheckOptions options) {
    elementLocator().uncheck(options);
  }

  /**
   * checks if an element is checked.
   * @return true if element is checked
   */
  public boolean isChecked() {
    return this.isChecked(null);
  }

  /**
   * checks if an element is checked.
   * @param options the is checked options
   * @return true if element is checked
   */
  public boolean isChecked(Locator.IsCheckedOptions options) {
    return elementLocator().isChecked(options);
  }

  /**
   * checks if an element is disabled.
   * @return true if element is disabled
   */
  public boolean isDisabled() {
    return this.isDisabled(null);
  }

  /**
   * checks if an element is disabled.
   * @param options the is disabled options
   * @return true if element is disabled
   */
  public boolean isDisabled(Locator.IsDisabledOptions options) {
    return elementLocator().isDisabled(options);
  }

  /**
   * checks if an element is editable.
   * @return true if element is editable
   */
  public boolean isEditable() {
    return this.isEditable(null);
  }

  /**
   * checks if an element is editable.
   * @param options the is editable options
   * @return true if element is editable
   */
  public boolean isEditable(Locator.IsEditableOptions options) {
    return elementLocator().isEditable(options);
  }

  /**
   * checks if an element is enabled.
   * @return true if element is enabled
   */
  public boolean isEnabled() {
    return this.isEnabled(null);
  }

  /**
   * checks if an element is enabled.
   * @param options the is enabled options
   * @return true if element is enabled
   */
  public boolean isEnabled(Locator.IsEnabledOptions options) {
    return elementLocator().isEnabled(options);
  }

  /**
   * checks if an element is hidden.
   * @return true if element is hidden
   */
  public boolean isHidden() {
    return this.isHidden(null);
  }

  /**
   * checks if an element is hidden.
   * @param options the is hidden options
   * @return true if element is hidden
   */
  public boolean isHidden(Locator.IsHiddenOptions options) {
    return elementLocator().isHidden(options);
  }

  /**
   * checks if an element is visible.
   * @return true if element is visible
   */
  public boolean isVisible() {
    return this.isVisible(null);
  }

  /**
   * checks if an element is visible.
   * @param options the is visible options
   * @return true if element is visible
   */
  public boolean isVisible(Locator.IsVisibleOptions options) {
    return elementLocator().isVisible(options);
  }

  /**
   * Check that the element is eventually visible.
   * @return True if the element becomes visible within the page timeout
   */
  public boolean isEventuallyVisible() {
    try {
      Locator.WaitForOptions options = new Locator.WaitForOptions();
      options.setState(WaitForSelectorState.VISIBLE);
      this.elementLocator().waitFor(options);
      return true;
    } catch (NullPointerException | TimeoutError e) {
      return false;
    }
  }

  /**
   * Check that the element stops being visible.
   * @return True if the element becomes is hidden or gone within the page timeout
   */
  public boolean isEventuallyGone() {
    try {
      Locator.WaitForOptions options = new Locator.WaitForOptions();
      options.setState(WaitForSelectorState.HIDDEN);
      this.elementLocator().waitFor(options);
      return true;
    } catch (NullPointerException | TimeoutError e) {
      return false;
    }
  }

  /**
   * select an option of an element.
   * @param values the values to be selected
   * @return a list of the selected options
   */
  public List<String> selectOption(ElementHandle values) {
    return Collections.unmodifiableList(this.selectOption(values, null));
  }

  /**
   * select an option of an element.
   * @param values the values to be selected
   * @param options the select options
   * @return a list of the selected options
   */
  public List<String> selectOption(ElementHandle values, Locator.SelectOptionOptions options) {
    return Collections.unmodifiableList(elementLocator().selectOption(values, options));
  }

  /**
   * select an option of an element.
   * @param values the values to be selected
   * @return a list of the selected options
   */
  public List<String> selectOption(ElementHandle[] values) {
    return Collections.unmodifiableList(this.selectOption(values, null));
  }

  /**
   * select an option of an element.
   * @param values the values to be selected
   * @param options the select options
   * @return a list of the selected options
   */
  public List<String> selectOption(ElementHandle[] values, Locator.SelectOptionOptions options) {
    return Collections.unmodifiableList(elementLocator().selectOption(values, options));
  }

  /**
   * select an option of an element.
   * @param values the values to be selected
   * @return a list of the selected options
   */
  public List<String> selectOption(SelectOption values) {
    return Collections.unmodifiableList(this.selectOption(values, null));
  }

  /**
   * select an option of an element.
   * @param values the values to be selected
   * @param options the select options
   * @return a list of the selected options
   */
  public List<String> selectOption(SelectOption values, Locator.SelectOptionOptions options) {
    return Collections.unmodifiableList(elementLocator().selectOption(values, options));
  }

  /**
   * select an option of an element.
   * @param values the values to be selected
   * @return a list of the selected options
   */
  public List<String> selectOption(String[] values) {
    return Collections.unmodifiableList(this.selectOption(values, null));
  }

  /**
   * select an option of an element.
   * @param values the values to be selected
   * @param options the select options
   * @return a list of the selected options
   */
  public List<String> selectOption(String[] values, Locator.SelectOptionOptions options) {
    return Collections.unmodifiableList(elementLocator().selectOption(values, options));
  }

  /**
   * select an option of an element.
   * @param values the values to be selected
   * @return a list of the selected options
   */
  public List<String> selectOption(SelectOption[] values) {
    return this.selectOption(values, null);
  }

  /**
   * select an option of an element.
   * @param values the values to be selected
   * @param options the select options
   * @return a list of the selected options
   */
  public List<String> selectOption(SelectOption[] values, Locator.SelectOptionOptions options) {
    return Collections.unmodifiableList(elementLocator().selectOption(values, options));
  }

  /**
   * select an option of an element.
   * @param values the values to be selected
   * @return a list of the selected options
   */
  public List<String> selectOption(String values) {
    return this.selectOption(values, null);
  }

  /**
   * select an option of an element.
   * @param values the values to be selected
   * @param options the select options
   * @return a list of the selected options
   */
  public List<String> selectOption(String values, Locator.SelectOptionOptions options) {
    return Collections.unmodifiableList(elementLocator().selectOption(values, options));
  }

  /**
   * evaluate an expression on all selectors.
   * @param expression the expression to be evaluated
   * @return the object output of the evaluation
   */
  public Object evalOnSelectorAll(String expression) {
    return this.evalOnSelectorAll(expression, null);
  }

  /**
   * evaluate an expression on all selectors.
   * @param expression the expression to be evaluated
   * @param arg the args to be used
   * @return the object output of the evaluation
   */
  public Object evalOnSelectorAll(String expression, Object arg) {
    return elementLocator().evaluateAll(expression, arg);
  }

  /**
   * evaluate an expression.
   * @param expression the expression to be evaluated
   * @return object from the evaluated element
   */
  public Object evaluate(String expression) {
    return this.evaluate(expression, null);
  }

  /**
   * evaluate an expression.
   * @param expression the expression to be evaluated
   * @param arg the arg to be used
   * @return object from the evaluated element
   */
  public Object evaluate(String expression, Object arg) {
    return elementLocator().evaluate(expression, arg);
  }

  /**
   * get the attributes of an element.
   * @param name the name of the element
   * @return the attribute as a string
   */
  public String getAttribute(String name) {
    return this.getAttribute(name, null);
  }

  /**
   * get the attributes of an element.
   * @param name the name of the element
   * @param options the get attribute options
   * @return the attribute as a string
   */
  public String getAttribute(String name, Locator.GetAttributeOptions options) {
    return elementLocator().getAttribute(name, options);
  }

  /**
   * get the text content of the element.
   * @return the text content
   */
  public String textContent() {
    return this.textContent(null);
  }

  /**
   * get the text content of the element.
   * @param options the text content options
   * @return the text content
   */
  public String textContent(Locator.TextContentOptions options) {
    return elementLocator().textContent(options);
  }

  /**
   * the text contents of all elements in  list.
   * @return a list of the text contents
   */
  public List<String> allTextContents() {
    return elementLocator().allTextContents();
  }

  /**
   * the inner text value of all elements in the list.
   * @return a list of the inner text as a string
   */
  public List<String> allInnerTexts() {
    return Collections.unmodifiableList(elementLocator().allInnerTexts());
  }

  /**
   * the inner html value.
   * @return the inner html as a string
   */
  public String innerHTML() {
    return this.innerHTML(null);
  }

  /**
   * the inner html value.
   * @param options the inner html options
   * @return the inner html as a string
   */
  public String innerHTML(Locator.InnerHTMLOptions options) {
    return elementLocator().innerHTML(options);
  }

  /**
   * the inner text value.
   * @return the inner text string
   */
  public String innerText() {
    return this.innerText(null);
  }

  /**
   * the inner text value.
   * @param options the inner text options
   * @return the inner text string
   */
  public String innerText(Locator.InnerTextOptions options) {
    return elementLocator().innerText(options);
  }

  /**
   * input value on the element locator.
   * @return the string input value
   */
  public String inputValue() {
    return this.inputValue(null);
  }

  /**
   * input value on the element locator.
   * @param options the input value options
   * @return the string input value
   */
  public String inputValue(Locator.InputValueOptions options) {
    return elementLocator().inputValue(options);
  }
}
