/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.microsoft.playwright.*;
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
   * Initializes a new instance of the com.cognizantsoftvision.maqs.playwright.PlaywrightSyncElement class.
   * @param page the playwright page to be set
   * @param selector the selector of the element
   */
  public PlaywrightSyncElement(Page page, String selector) {
    this.setParentPage(page);
    this.setSelector(selector);
  }

  /**
   * Initializes a new instance of the com.cognizantsoftvision.maqs.playwright.PlaywrightSyncElement class.
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
   * Initializes a new instance of the com.cognizantsoftvision.maqs.playwright.PlaywrightSyncElement class.
   * @param parent the parent Playwright sync element
   * @param selector Element selector
   */
  public PlaywrightSyncElement(PlaywrightSyncElement parent, String selector) {
    this.setParentLocator(parent.getParentLocator());
    this.setSelector(selector);
  }

  /**
   * Initializes a new instance of the com.cognizantsoftvision.maqs.playwright.PlaywrightSyncElement class.
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
   * Initializes a new instance of the PlaywrightSyncElement class.
   * @param frame The associated playwright frame locator
   * @param selector Element selector
   */
  public PlaywrightSyncElement(FrameLocator frame, String selector) {
    this.setParentFrameLocator(frame);
    this.setSelector(selector);
  }

  /**
   * Initializes a new instance of the com.cognizantsoftvision.maqs.playwright.PlaywrightSyncElement class.
   * @param testObject The associated playwright test object
   * @param selector Element selector
   * @param options Advanced locator options
   */
  public PlaywrightSyncElement(IPlaywrightTestObject testObject, String selector, Page.LocatorOptions options) {
    this(testObject.getPageDriver().getAsyncPage(), selector, options);
  }

  /**
   * Initializes a new instance of the PlaywrightSyncElement class.
   * @param driver The associated playwright page driver
   * @param selector Element selector
   */
  public PlaywrightSyncElement(PageDriver driver, String selector) {
    this(driver.getAsyncPage(), selector);
  }

  /**
   * Initializes a new instance of the com.cognizantsoftvision.maqs.playwright.PlaywrightSyncElement class.
   * @param driver The associated playwright page driver
   * @param selector Element selector
   * @param options Advanced locator options
   */
  public PlaywrightSyncElement(PageDriver driver, String selector, Page.LocatorOptions options) {
    this(driver.getAsyncPage(), selector, options);
  }

  /**
   * the underlying async page object
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
   * The underlying async page object
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
   * Sets the underlying async page object
   * @param newParentLocator the new parent locator
   */
  private void setParentLocator(Locator newParentLocator) {
    this.parentLocator = newParentLocator;
  }

  /**
   * The underlying async page object
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
   * Sets the underlying async page object
   * @param newParentLocator the new parent locator
   */
  private void setParentFrameLocator(FrameLocator newParentLocator) {
    this.parentFrameLocator = newParentLocator;
  }

  /// <summary>
  /// Gets the page locator options
  /// </summary>
  private Page.LocatorOptions pageOptions;

  public Page.LocatorOptions getPageOptions() {
    return this.pageOptions;
  }

  private void setPageOptions(Page.LocatorOptions newPageOptions) {
    this.pageOptions = newPageOptions;
  }

  /// <summary>
  /// Gets the page locator options
  /// </summary>
  private Locator.LocatorOptions locatorOptions;

  public Locator.LocatorOptions getLocatorOptions() {
    return this.locatorOptions;
  }

  private void setLocatorOptions(Locator.LocatorOptions newLocatorOptions) {
    this.locatorOptions = newLocatorOptions;
  }

  /**
   * The selector string
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

  /// <summary>
  /// ILocator for this element
  /// </summary>
  /// <returns></returns>
  public Locator elementLocator() {
    if (this.getParentPage() != null) {
      return getPageOptions() != null ? this.getParentPage().locator(getSelector(), getPageOptions())
          : this.getParentPage().locator(getSelector());
    } else if (this.getParentLocator() != null) {
      return getLocatorOptions() != null ? this.getParentLocator().locator(getSelector(), getLocatorOptions())
          : this.getParentLocator().locator(getSelector());
    } else if(this.getParentFrameLocator() != null) {
      return this.getParentLocator().locator(getSelector());
    }

    throw new NullPointerException("Both parent IPage and PlaywrightElement are null");
  }

  public void check() {
    elementLocator().check();
  }

  /// <inheritdoc cref = "ILocator.CheckAsync" />
  public void check(Locator.CheckOptions options) {
    elementLocator().check(options);
  }

  public void click() {
    elementLocator().click();
  }

  /// <inheritdoc cref = "ILocator.ClickAsync" />
  public void click(Locator.ClickOptions options) {
    elementLocator().click(options);
  }

  public void doubleClick() {
    elementLocator().dblclick();
  }

  /// <inheritdoc cref = "ILocator.DblClickAsync" />
  public void doubleClick(Locator.DblclickOptions options) {
    elementLocator().dblclick(options);
  }

  /// <inheritdoc cref = "ILocator.DispatchEventAsync" />
  public void dispatchEvent(String type) {
    elementLocator().dispatchEvent(type);
  }

  /// <inheritdoc cref = "ILocator.DispatchEventAsync" />
  public void dispatchEvent(String type, Object eventInit) {
    elementLocator().dispatchEvent(type, eventInit);
  }

  /// <inheritdoc cref = "ILocator.DispatchEventAsync" />
  public void dispatchEvent(String type, Object eventInit, Locator.DispatchEventOptions options) {
    elementLocator().dispatchEvent(type, eventInit, options);
  }

  public void dragTo(Locator target) {
    elementLocator().dragTo(target);
  }

  /// <inheritdoc cref = "ILocator.DragToAsync(ILocator, LocatorDragToOptions?)" />
  public void dragTo(Locator target, Locator.DragToOptions options) {
    elementLocator().dragTo(target, options);
  }

  public void fill(String value) {
    elementLocator().fill(value);
  }

  /// <inheritdoc cref = "ILocator.FillAsync" />
  public void fill(String value, Locator.FillOptions options) {
    elementLocator().fill(value, options);
  }

  public void focus() {
    elementLocator().focus();
  }

  /// <inheritdoc cref = "ILocator.FocusAsync" />
  public void focus(Locator.FocusOptions options) {
    elementLocator().focus(options);
  }

  public void hover() {
    elementLocator().hover();
  }

  /// <inheritdoc cref = "ILocator.HoverAsync" />
  public void hover(Locator.HoverOptions options) {
    elementLocator().hover(options);
  }

  public void press(String key) {
    elementLocator().press(key);
  }

  /// <inheritdoc cref = "ILocator.PressAsync" />
  public void press(String key, Locator.PressOptions options) {
    elementLocator().press(key, options);
  }

  public void setChecked(boolean checkedState) {
    elementLocator().setChecked(checkedState);
  }

  /// <inheritdoc cref = "ILocator.SetCheckedAsync" />
  public void setChecked(boolean checkedState, Locator.SetCheckedOptions options) {
    elementLocator().setChecked(checkedState, options);
  }

  public void setInputFiles(FilePayload files) {
    elementLocator().setInputFiles(files);
  }

  /// <inheritdoc cref = "ILocator.SetInputFilesAsync(FilePayload, LocatorSetInputFilesOptions)" />
  public void setInputFiles(FilePayload files, Locator.SetInputFilesOptions options) {
    elementLocator().setInputFiles(files, options);
  }

  public void setInputFiles(FilePayload[] files) {
    elementLocator().setInputFiles(files);
  }

  /// <inheritdoc cref = "ILocator.SetInputFilesAsync(IEnumerable{FilePayload}, LocatorSetInputFilesOptions)" />
  public void setInputFiles(FilePayload[] files, Locator.SetInputFilesOptions options) {
    elementLocator().setInputFiles(files, options);
  }

  public void setInputFiles(Path[] files) {
    elementLocator().setInputFiles(files);
  }

  /// <inheritdoc cref = "ILocator.SetInputFilesAsync(IEnumerable{string}, LocatorSetInputFilesOptions)" />
  public void setInputFiles(Path[] files, Locator.SetInputFilesOptions options) {
    elementLocator().setInputFiles(files, options);
  }

  public void setInputFiles(Path files) {
    elementLocator().setInputFiles(files);
  }

  /// <inheritdoc cref = "ILocator.SetInputFilesAsync(string, LocatorSetInputFilesOptions)" />
  public void setInputFiles(Path files, Locator.SetInputFilesOptions options) {
    elementLocator().setInputFiles(files, options);
  }

  public void tap() {
    elementLocator().tap();
  }

  /// <inheritdoc cref = "ILocator.TapAsync" />
  public void tap(Locator.TapOptions options) {
    elementLocator().tap(options);
  }

  public void type(String text) {
    elementLocator().type(text);
  }

  /// <inheritdoc cref = "ILocator.TypeAsync" />
  public void type(String text, Locator.TypeOptions options) {
    elementLocator().type(text, options);
  }

  public void uncheck() {
    elementLocator().uncheck();
  }

  /// <inheritdoc cref = "ILocator.UncheckAsync" />
  public void uncheck(Locator.UncheckOptions options) {
    elementLocator().uncheck(options);
  }

  public boolean isChecked() {
    return elementLocator().isChecked();
  }

  /// <inheritdoc cref = "ILocator.IsCheckedAsync"  />
  public boolean isChecked(Locator.IsCheckedOptions options) {
    return elementLocator().isChecked(options);
  }

  public boolean isDisabled() {
    return elementLocator().isDisabled();
  }

  /// <inheritdoc cref = "ILocator.IsDisabledAsync"  />
  public boolean isDisabled(Locator.IsDisabledOptions options) {
    return elementLocator().isDisabled(options);
  }

  public boolean isEditable() {
    return elementLocator().isEditable();
  }

  /// <inheritdoc cref = "ILocator.IsEditableAsync"  />
  public boolean isEditable(Locator.IsEditableOptions options) {
    return elementLocator().isEditable(options);
  }

  public boolean isEnabled() {
    return elementLocator().isEnabled();
  }

  /// <inheritdoc cref = "ILocator.IsEnabledAsync"  />
  public boolean isEnabled(Locator.IsEnabledOptions options) {
    return elementLocator().isEnabled(options);
  }

  public boolean isHidden() {
    return elementLocator().isHidden();
  }

  /// <inheritdoc cref = "ILocator.IsHiddenAsync"  />
  public boolean isHidden(Locator.IsHiddenOptions options) {
    return elementLocator().isHidden(options);
  }

  public boolean isVisible() {
    return elementLocator().isVisible();
  }

  /// <inheritdoc cref = "ILocator.IsVisibleAsync"  />
  public boolean isVisible(Locator.IsVisibleOptions options) {
    return elementLocator().isVisible(options);
  }

  /// <summary>
  /// Check that the element is eventually visible
  /// </summary>
  /// <returns>True if the element becomes visible within the page timeout</returns>
  public boolean isEventuallyVisible() {
    try {
      Locator.WaitForOptions options = new Locator.WaitForOptions();
      options.setState(WaitForSelectorState.VISIBLE);
      this.elementLocator().waitFor(options);
      return true;
    } catch (NullPointerException e) {
      return false;
    }
  }

  /// <summary>
  /// Check that the element stops being visible
  /// </summary>
  /// <returns>True if the element becomes is hidden or gone within the page timeout</returns>
  public boolean isEventuallyGone() {
    try {
      Locator.WaitForOptions options = new Locator.WaitForOptions();
      options.setState(WaitForSelectorState.HIDDEN);
      this.elementLocator().waitFor(options);
      return true;
    } catch (NullPointerException e) {
      return false;
    }
  }

  public List<String> selectOption(ElementHandle values) {
    return Collections.unmodifiableList(elementLocator().selectOption(values));
  }

  /// <inheritdoc cref = "ILocator.SelectOptionAsync(IElementHandle, LocatorSelectOptionOptions)"  />
  public List<String> selectOption(ElementHandle values, Locator.SelectOptionOptions options) {
    return Collections.unmodifiableList(elementLocator().selectOption(values, options));
  }

  public List<String> selectOption(ElementHandle[] values) {
    return Collections.unmodifiableList(elementLocator().selectOption(values));
  }

  /// <inheritdoc cref = "ILocator.SelectOptionAsync(IEnumerable{IElementHandle}, LocatorSelectOptionOptions)"  />
  public List<String> selectOption(ElementHandle[] values, Locator.SelectOptionOptions options) {
    return Collections.unmodifiableList(elementLocator().selectOption(values, options));
  }

  public List<String> selectOption(SelectOption values) {
    return Collections.unmodifiableList(elementLocator().selectOption(values));
  }

  /// <inheritdoc cref = "ILocator.SelectOptionAsync(IEnumerable{SelectOptionValue}, LocatorSelectOptionOptions)"  />
  public List<String> selectOption(SelectOption values, Locator.SelectOptionOptions options) {
    return Collections.unmodifiableList(elementLocator().selectOption(values, options));
  }

  public List<String> selectOption(String[] values) {
    return Collections.unmodifiableList(elementLocator().selectOption(values));
  }

  /// <inheritdoc cref = "ILocator.SelectOptionAsync(IEnumerable{string}, LocatorSelectOptionOptions)"  />
  public List<String> selectOption(String[] values, Locator.SelectOptionOptions options) {
    return Collections.unmodifiableList(elementLocator().selectOption(values, options));
  }

  public List<String> selectOption(SelectOption[] values) {
    return Collections.unmodifiableList(elementLocator().selectOption(values));
  }

  /// <inheritdoc cref = "ILocator.SelectOptionAsync(SelectOptionValue, LocatorSelectOptionOptions)"  />
  public List<String> selectOption(SelectOption[] values, Locator.SelectOptionOptions options) {
    return Collections.unmodifiableList(elementLocator().selectOption(values, options));
  }

  public List<String> selectOption(String values) {
    return Collections.unmodifiableList(elementLocator().selectOption(values));
  }

  /// <inheritdoc cref = "ILocator.SelectOptionAsync(string, LocatorSelectOptionOptions)"  />
  public List<String> selectOption(String values, Locator.SelectOptionOptions options) {
    return Collections.unmodifiableList(elementLocator().selectOption(values, options));
  }

  public Object evalOnSelectorAll(String expression) {
    return elementLocator().evaluateAll(expression);
  }

  /// <inheritdoc cref = "ILocator.EvaluateAllAsync"  />
  public Object evalOnSelectorAll(String expression, Object arg) {
    return elementLocator().evaluateAll(expression, arg);
  }

  public Object evaluate(String expression) {
    return elementLocator().evaluate(expression);
  }

  /// <inheritdoc cref = "ILocator.EvaluateAsync"  />
  public Object evaluate(String expression, Object arg) {
    return elementLocator().evaluate(expression, arg);
  }

  public String getAttribute(String name) {
    return elementLocator().getAttribute(name);
  }

  /// <inheritdoc cref = "ILocator.GetAttributeAsync"  />
  public String getAttribute(String name, Locator.GetAttributeOptions options) {
    return elementLocator().getAttribute(name, options);
  }

  public String textContent() {
    return elementLocator().textContent();
  }

  /// <inheritdoc cref = "ILocator.TextContentAsync"  />
  public String textContent(Locator.TextContentOptions options) {
    return elementLocator().textContent(options);
  }

  /// <inheritdoc cref = "ILocator.AllTextContentsAsync"  />
  public List<String> allTextContents() {
    return elementLocator().allTextContents();
  }

  /// <inheritdoc cref = "ILocator.AllInnerTextsAsync"  />
  public List<String> allInnerTexts() {
    return Collections.unmodifiableList(elementLocator().allInnerTexts());
  }

  public String innerHTML() {
    return elementLocator().innerHTML();
  }

  /// <inheritdoc cref = "ILocator.InnerHTMLAsync"  />
  public String innerHTML(Locator.InnerHTMLOptions options) {
    return elementLocator().innerHTML(options);
  }

  public String innerText() {
    return elementLocator().innerText();
  }

  /// <inheritdoc cref = "ILocator.InnerTextAsync"  />
  public String innerText(Locator.InnerTextOptions options) {
    return elementLocator().innerText(options);
  }

  public String inputValue() {
    return elementLocator().inputValue();
  }

  /// <inheritdoc cref = "ILocator.InputValueAsync"  />
  public String inputValue(Locator.InputValueOptions options) {
    return elementLocator().inputValue(options);
  }
}
