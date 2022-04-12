/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.FilePayload;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.ViewportSize;
import com.microsoft.playwright.options.WaitForSelectorState;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * The Page Driver class.
 */
public class PageDriver {

  /**
   * the page driver initializer.
   * @param page to be set for the page driver
   */
  public PageDriver(Page page) {
    this.setAsyncPage(page);
  }

  /**
   * the underlying async page object.
   */
  private Page asyncPage;

  /**
   * gets the underlying async page object.
   * @return the page of the async page
   */
  public Page getAsyncPage() {
    return this.asyncPage;
  }

  /**
   * Sets the underlying async page object.
   * @param page the page to be set
   */
  private void setAsyncPage(Page page) {
    this.asyncPage = page;
  }

  /**
   * adds an init script.
   * @param script the script to be added
   */
  public void addInitScript(String script) {
    this.asyncPage.addInitScript(script);
  }

  /**
   * adds an init script.
   * @param path the path of the script
   */
  public void addInitScript(Path path) {
    this.asyncPage.addInitScript(path);
  }

  /**
   * brings the page to the front.
   */
  public void bringToFront() {
    this.asyncPage.bringToFront();
  }

  /**
   * check the element.
   * @param selector the element to be checked
   */
  public void check(String selector) {
    this.asyncPage.check(selector);
  }

  /**
   * check the element.
   * @param selector the element to be checked
   * @param options the check options
   */
  public void check(String selector, Page.CheckOptions options) {
    this.asyncPage.check(selector, options);
  }

  /**
   * click an element.
   * @param selector the element selector
   */
  public void click(String selector) {
    this.asyncPage.click(selector);
  }

  /**
   * click an element.
   * @param selector the element selector
   * @param options the click options
   */
  public void click(String selector, Page.ClickOptions options) {
    this.asyncPage.click(selector, options);
  }

  /**
   * close the page.
   */
  public void close() {
    close(null);
  }

  /**
   * close the page.
   * @param options the close options
   */
  public void close(Page.CloseOptions options) {
    this.asyncPage.close(options);
  }

  /**
   * double click an element.
   * @param selector the element to be double-clicked
   */
  public void doubleClick(String selector) {
    doubleClick(selector, null);
  }

  /**
   * double click an element.
   * @param selector the element to be double-clicked
   * @param options the click options
   */
  public void doubleClick(String selector, Page.ClickOptions options) {
    this.asyncPage.click(selector, options);
  }

  /**
   * dispatch an event on an element.
   * @param selector the element to dispatch the event
   * @param type the dispatch type
   */
  public void dispatchEvent(String selector, String type) {
    dispatchEvent(selector, type, null, null);
  }

  /**
   * dispatch an event on an element.
   * @param selector the element to dispatch the event
   * @param type the dispatch type
   * @param eventInit the event to initiate
   * @param options the dispatch event options
   */
  public void dispatchEvent(String selector, String type, Object eventInit, Page.DispatchEventOptions options) {
    this.asyncPage.dispatchEvent(selector, type, eventInit, options);
  }

  /**
   * drag and drop an element.
   * @param source the source element to be clicked
   * @param target the target to be dropped on
   */
  public void dragAndDrop(String source, String target) {
    this.asyncPage.dragAndDrop(source, target);
  }

  /**
   * drag and drop an element.
   * @param source the source element to be clicked
   * @param target the target to be dropped on
   * @param options the drag and drop options
   */
  public void dragAndDrop(String source, String target, Page.DragAndDropOptions options) {
    this.asyncPage.dragAndDrop(source, target, options);
  }

  /**
   * fill an element with a string value.
   * @param selector the element to be filled
   * @param value the value to be inputted
   */
  public void fill(String selector, String value) {
    this.asyncPage.fill(selector, value);
  }

  /**
   * fill an element with a string value.
   * @param selector the element to be filled
   * @param value the value to be inputted
   * @param options the fill options
   */
  public void fill(String selector, String value, Page.FillOptions options) {
    this.asyncPage.fill(selector, value, options);
  }

  /**
   * focus on an element.
   * @param selector the selector to focus on
   */
  public void focus(String selector) {
    focus(selector, null);
  }

  /**
   * focus on an element.
   * @param selector the selector to focus on
   * @param options the focus options
   */
  public void focus(String selector, Page.FocusOptions options) {
    this.asyncPage.focus(selector, options);
  }

  /**
   * mouse hover an element.
   * @param selector the selector to hover over
   */
  public void hover(String selector) {
    hover(selector, null);
  }

  /**
   * mouse hover an element.
   * @param selector the selector to hover over
   * @param options the hover options
   */
  public void hover(String selector, Page.HoverOptions options) {
    this.asyncPage.hover(selector, options);
  }

  /**
   * key press a web element.
   * @param selector the element selector
   * @param key the key to be pressed
   */
  public void press(String selector, String key) {
    press(selector, key, null);
  }

  /**
   * key press a web element.
   * @param selector the element selector
   * @param key the key to be pressed
   * @param options the press options
   */
  public void press(String selector, String key, Page.PressOptions options) {
    this.asyncPage.press(selector, key, options);
  }

  /**
   * set checked an element.
   * @param selector the selector to be checked
   * @param checkedState if the element should be checked
   */
  public void setChecked(String selector, boolean checkedState) {
    setChecked(selector, checkedState, null);
  }

  /**
   * set checked an element.
   * @param selector the selector to be checked
   * @param checkedState if the element should be checked
   * @param options the set checked options
   */
  public void setChecked(String selector, boolean checkedState, Page.SetCheckedOptions options) {
    this.asyncPage.setChecked(selector, checkedState, options);
  }

  /**
   * sets content on the page.
   * @param html of the page
   */
  public void setContent(String html) {
    setContent(html, null);
  }

  /**
   * sets content on the page.
   * @param html of the page
   * @param options the set content options
   */
  public void setContent(String html, Page.SetContentOptions options) {
    this.asyncPage.setContent(html, options);
  }

  /**
   * sets the extra http headers.
   * @param headers the headers to be set
   */
  public void setExtraHTTPHeaders(Map<String, String> headers) {
    this.asyncPage.setExtraHTTPHeaders(headers);
  }

  /**
   * set the input files.
   * @param selector the selector of the input of files
   * @param files the files to be inputted
   */
  public void setInputFiles(String selector, FilePayload files) {
    setInputFiles(selector, files, null);
  }

  /**
   * set the input files.
   * @param selector the selector of the input of files
   * @param files the files to be inputted
   * @param options the set input files options
   */
  public void setInputFiles(String selector, FilePayload files, Page.SetInputFilesOptions options) {
    this.asyncPage.setInputFiles(selector, files, options);
  }

  /**
   * set the input files.
   * @param selector the selector of the input of files
   * @param files the files to be inputted
   */
  public void setInputFiles(String selector, FilePayload[] files) {
    setInputFiles(selector, files, null);
  }

  /**
   * set the input files.
   * @param selector the selector of the input of files
   * @param files the files to be inputted
   * @param options the set input files options
   */
  public void setInputFiles(String selector, FilePayload[] files, Page.SetInputFilesOptions options) {
    this.asyncPage.setInputFiles(selector, files, options);
  }

  /**
   * set the input files.
   * @param selector the selector of the input of files
   * @param files the files to be inputted
   */
  public void setInputFiles(String selector, Path[] files) {
    setInputFiles(selector, files, null);
  }

  /**
   * set the input files.
   * @param selector the selector of the input of files
   * @param files the files to be inputted
   * @param options the set input files options
   */
  public void setInputFiles(String selector, Path[] files, Page.SetInputFilesOptions options) {
    this.asyncPage.setInputFiles(selector, files, options);
  }

  /**
   * set the input files.
   * @param selector the selector of the input of files
   * @param files the files to be inputted
   */
  public void setInputFiles(String selector, String files) {
    setInputFiles(selector, files, null);
  }

  /**
   * set the input files.
   * @param selector the selector of the input of files
   * @param files the files to be inputted
   * @param options the set input files options
   */
  public void setInputFiles(String selector, String files, Page.SetInputFilesOptions options) {
    this.asyncPage.setInputFiles(selector, Paths.get(files), options);
  }

  /**
   * gets the viewport size.
   * @return the viewport size that contains size variables
   */
  public ViewportSize getViewPortSize() {
    return this.getAsyncPage().viewportSize();
  }

  /**
   * sets the viewport size.
   * @param width the viewport width
   * @param height the viewport height
   */
  public void setViewportSize(int width, int height) {
    this.asyncPage.setViewportSize(width, height);
  }

  /**
   * tap an element.
   * @param selector the selector of the text box
   */
  public void tap(String selector) {
    tap(selector, null);
  }

  /**
   * tap an element.
   * @param selector the selector of the text box
   * @param options the type options
   */
  public void tap(String selector, Page.TapOptions options) {
    this.asyncPage.tap(selector, options);
  }

  /**
   * type in a text box element.
   * @param selector the selector of the text box
   * @param text the text to be entered
   */
  public void type(String selector, String text) {
    type(selector, text, null);
  }

  /**
   * type in a text box element.
   * @param selector the selector of the text box
   * @param text the text to be entered
   * @param options the type options
   */
  public void type(String selector, String text, Page.TypeOptions options) {
    this.asyncPage.type(selector, text, options);
  }

  /**
   * uncheck a element.
   * @param selector the selector to be unchecked
   */
  public void uncheck(String selector) {
    uncheck(selector, null);
  }

  /**
   * uncheck a element.
   * @param selector the selector to be unchecked
   * @param options the uncheck options
   */
  public void uncheck(String selector, Page.UncheckOptions options) {
    this.asyncPage.uncheck(selector, options);
  }

  /**
   * waits for a load state.
   * @param state the state to be waited for
   */
  public void waitForLoadState(LoadState state) {
    waitForLoadState(state, null);
  }

  /**
   * waits for a load state.
   * @param state the state to be waited for
   * @param options the wait for load state options
   */
  public void waitForLoadState(LoadState state, Page.WaitForLoadStateOptions options) {
    this.asyncPage.waitForLoadState(state, options);
  }

  /**
   * waits for a timeout.
   * @param timeout the time to be waited for
   */
  public void waitForTimeout(float timeout) {
    this.asyncPage.waitForTimeout(timeout);
  }

  /**
   * waits for url.
   * @param url the url to be waited for
   */
  public void waitForURL(Predicate<String> url) {
    this.asyncPage.waitForURL(url);
  }

  /**
   * waits for url.
   * @param url the url to be waited for
   * @param options the wait for url options
   */
  public void waitForURL(Predicate<String> url, Page.WaitForURLOptions options) {
    this.asyncPage.waitForURL(url, options);
  }

  /**
   * waits for url.
   * @param url the url to be waited for
   */
  public void waitForURL(Pattern url) {
    this.asyncPage.waitForURL(url);
  }

  /**
   * waits for url.
   * @param url the url to be waited for
   * @param options the wait for url options
   */
  public void waitForURL(Pattern url, Page.WaitForURLOptions options) {
    this.asyncPage.waitForURL(url, options);
  }

  /**
   * waits for url.
   * @param url the url to be waited for
   */
  public void waitForURL(String url) {
    this.asyncPage.waitForURL(url);
  }

  /**
   * waits for url.
   * @param url the url to be waited for
   * @param options the wait for url options
   */
  public void waitForURL(String url, Page.WaitForURLOptions options) {
    this.asyncPage.waitForURL(url, options);
  }

  /**
   * checks if an element is checked.
   * @param selector the element selector
   * @return true if element is checked
   */
  public boolean isChecked(String selector) {
    return this.asyncPage.isChecked(selector);
  }

  /**
   * checks if an element is checked.
   * @param selector the element selector
   * @param options the is checked options
   * @return true if element is checked
   */
  public boolean isChecked(String selector, Page.IsCheckedOptions options) {
    return this.asyncPage.isChecked(selector, options);
  }

  /**
   * checks if an element is disabled.
   * @param selector the element selector
   * @return true if element is disabled
   */
  public boolean isDisabled(String selector) {
    return this.asyncPage.isDisabled(selector);
  }

  /**
   * checks if an element is disabled.
   * @param selector the element selector
   * @param options the is disabled options
   * @return true if element is disabled
   */
  public boolean isDisabled(String selector, Page.IsDisabledOptions options) {
    return this.asyncPage.isDisabled(selector, options);
  }

  /**
   * checks if an element is editable.
   * @param selector the element selector
   * @return true if element is editable
   */
  public boolean isEditable(String selector) {
    return this.asyncPage.isEditable(selector);
  }

  /**
   * checks if an element is editable.
   * @param selector the element selector
   * @param options the is editable options
   * @return true if element is editable
   */
  public boolean isEditable(String selector, Page.IsEditableOptions options) {
    return this.asyncPage.isEditable(selector, options);
  }

  /**
   * checks if an element is enabled.
   * @param selector the element selector
   * @return true if element is enabled
   */
  public boolean isEnabled(String selector) {
    return this.asyncPage.isEnabled(selector);
  }

  /**
   * checks if an element is enabled.
   * @param selector the element selector
   * @param options the is enabled options
   * @return true if element is enabled
   */
  public boolean isEnabled(String selector, Page.IsEnabledOptions options) {
    return this.asyncPage.isEnabled(selector, options);
  }

  /**
   * checks if an element is hidden.
   * @param selector the element selector
   * @return true if element is hidden
   */
  public boolean isHidden(String selector) {
    return this.asyncPage.isHidden(selector);
  }

  /**
   * checks if an element is hidden.
   * @param selector the element selector
   * @param options the is hidden options
   * @return true if element is hidden
   */
  public boolean isHidden(String selector, Page.IsHiddenOptions options) {
    return this.asyncPage.isHidden(selector, options);
  }

  /**
   * checks if an element is visible.
   * @param selector the element selector
   * @return true if element is visible
   */
  public boolean isVisible(String selector) {
    return this.asyncPage.isVisible(selector);
  }

  /**
   * checks if an element is visible.
   * @param selector the element selector
   * @param options the is visible options
   * @return true if element is visible
   */
  public boolean isVisible(String selector, Page.IsVisibleOptions options) {
    return this.asyncPage.isVisible(selector, options);
  }

  /**
   * Check that the element is eventually visible.
   * @param selector Element selector
   * @return True if the element becomes visible within the page timeout
   */
  public boolean isEventuallyVisible(String selector) {
    try {
      this.getAsyncPage().waitForSelector(selector);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Check that the element is eventually visible.
   * @param selector Element selector
   * @param options Visible check options
   * @return True if the element becomes visible within the page timeout
   */
  public boolean isEventuallyVisible(String selector, Page.IsVisibleOptions options) {
    try {
      Page.WaitForSelectorOptions selectorOptions = new Page.WaitForSelectorOptions();
      selectorOptions.setState(WaitForSelectorState.VISIBLE);
      selectorOptions.strict = options != null && options.strict;
      this.getAsyncPage().waitForSelector(selector, selectorOptions);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Check that the element stops being visible.
   * @param selector Element selector
   * @return True if the element becomes is hidden or gone within the page timeout
   */
  public boolean isEventuallyGone(String selector) {
    try {
      this.getAsyncPage().waitForSelector(selector);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Check that the element stops being visible.
   * @param selector Element selector
   * @param options Visible check options
   * @return True if the element becomes is hidden or gone within the page timeout
   */
  public boolean isEventuallyGone(String selector, Page.IsVisibleOptions options) {
    try {
      Page.WaitForSelectorOptions selectorOptions = new Page.WaitForSelectorOptions();
      selectorOptions.setState(WaitForSelectorState.HIDDEN);
      selectorOptions.strict = options != null && options.strict;
      this.getAsyncPage().waitForSelector(selector, selectorOptions);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * query the selector.
   * @param selector the selector to be queried
   * @return the element return handle
   */
  public ElementHandle querySelector(String selector) {
    return this.asyncPage.querySelector(selector);
  }

  /**
   * query the selector.
   * @param selector the selector to be queried
   * @param options the query selector options
   * @return the element return handle
   */
  public ElementHandle querySelector(String selector, Page.QuerySelectorOptions options) {
    return this.asyncPage.querySelector(selector, options);
  }

  /**
   * waits for a selector.
   * @param selector the selector to be waited for
   * @return the element handle of the wait for function
   */
  public ElementHandle waitForSelector(String selector) {
    return this.asyncPage.waitForSelector(selector);
  }

  /**
   * waits for a selector.
   * @param selector the selector to be waited for
   * @param options the wait for selector options
   * @return the element handle of the wait for function
   */
  public ElementHandle waitForSelector(String selector, Page.WaitForSelectorOptions options) {
    return this.asyncPage.waitForSelector(selector, options);
  }

  /**
   * adds a style tag.
   * @return the element handle of the style tag
   */
  public ElementHandle addScriptTag() {
    return this.asyncPage.addScriptTag();
  }

  /**
   * adds a script tag.
   * @param options the add style tag options
   * @return the element handle of the script tag
   */
  public ElementHandle addScriptTag(Page.AddScriptTagOptions options) {
    return this.asyncPage.addScriptTag(options);
  }

  /**
   * adds a style tag.
   * @return the element handle of the style tag
   */
  public ElementHandle addStyleTag() {
    return this.asyncPage.addStyleTag();
  }

  /**
   * adds a style tag.
   * @param options the add style tag options
   * @return the element handle of the style tag
   */
  public ElementHandle addStyleTag(Page.AddStyleTagOptions options) {
    return this.asyncPage.addStyleTag(options);
  }

  /**
   * query for all the selectors.
   * @param selector the selector to be queried
   * @return the list of element handles
   */
  public List<ElementHandle> querySelectorAll(String selector) {
    return Collections.unmodifiableList(this.asyncPage.querySelectorAll(selector));
  }

  /**
   * select options for an element.
   * @param selector the selector that will have its option selected
   * @param values the values to be selected
   * @return a list of the options
   */
  public List<String> selectOption(String selector, ElementHandle values) {
    return this.asyncPage.selectOption(selector, values);
  }

  /**
   * select options for an element.
   * @param selector the selector that will have its option selected
   * @param values the values to be selected
   * @param options the select options
   * @return a list of the options
   */
  public List<String> selectOption(String selector, ElementHandle values, Page.SelectOptionOptions options) {
    return this.asyncPage.selectOption(selector, values, options);
  }

  /**
   * select options for an element.
   * @param selector the selector that will have its option selected
   * @param values the values to be selected
   * @return a list of the options
   */
  public List<String> selectOption(String selector, ElementHandle[] values) {
    return this.asyncPage.selectOption(selector, values);
  }

  /**
   * select options for an element.
   * @param selector the selector that will have its option selected
   * @param values the values to be selected
   * @param options the select options
   * @return a list of the options
   */
  public List<String> selectOption(String selector, ElementHandle[] values, Page.SelectOptionOptions options) {
    return this.asyncPage.selectOption(selector, values, options);
  }

  /**
   * select options for an element.
   * @param selector the selector that will have its option selected
   * @param values the values to be selected
   * @return a list of the options
   */
  public List<String> selectOption(String selector, SelectOption[] values) {
    return Collections.unmodifiableList(this.asyncPage.selectOption(selector, values));
  }

  /**
   * select options for an element.
   * @param selector the selector that will have its option selected
   * @param values the values to be selected
   * @param options the select options
   * @return a list of the options
   */
  public List<String> selectOption(String selector, SelectOption[] values, Page.SelectOptionOptions options) {
    return Collections.unmodifiableList(this.asyncPage.selectOption(selector, values, options));
  }

  /**
   * select options for an element.
   * @param selector the selector that will have its option selected
   * @param values the values to be selected
   * @return a list of the options
   */
  public List<String> selectOption(String selector, String[] values) {
    return Collections.unmodifiableList(this.asyncPage.selectOption(selector, values));
  }

  /**
   * select options for an element.
   * @param selector the selector that will have its option selected
   * @param values the values to be selected
   * @param options the select options
   * @return a list of the options
   */
  public List<String> selectOption(String selector, String[] values, Page.SelectOptionOptions options) {
    return Collections.unmodifiableList(this.asyncPage.selectOption(selector, values, options));
  }

  /**
   * select options for an element.
   * @param selector the selector that will have its option selected
   * @param values the values to be selected
   * @return a list of the options
   */
  public List<String> selectOption(String selector, SelectOption values) {
    return Collections.unmodifiableList(this.asyncPage.selectOption(selector, values));
  }

  /**
   * select options for an element.
   * @param selector the selector that will have its option selected
   * @param values the values to be selected
   * @param options the select options
   * @return a list of the options
   */
  public List<String> selectOption(String selector, SelectOption values, Page.SelectOptionOptions options) {
    return Collections.unmodifiableList(this.asyncPage.selectOption(selector, values, options));
  }

  /**
   * select options for an element.
   * @param selector the selector that will have its option selected
   * @param values the values to be selected
   * @return a list of the options
   */
  public List<String> selectOption(String selector, String values) {
    return Collections.unmodifiableList(this.asyncPage.selectOption(selector, values));
  }

  /**
   * select options for an element.
   * @param selector the selector that will have its option selected
   * @param values the values to be selected
   * @param options the select options
   * @return a list of the options
   */
  public List<String> selectOption(String selector, String values, Page.SelectOptionOptions options) {
    return Collections.unmodifiableList(this.asyncPage.selectOption(selector, values, options));
  }

  /**
   * evaluates on all the selector.
   * @param selector the selector to be evaluated
   * @param expression the expression to be used
   * @return the object of the evaluation
   */
  public Object evalOnSelectorAll(String selector, String expression) {
    return this.asyncPage.evalOnSelector(selector, expression);
  }

  /**
   * evaluates on all the selector.
   * @param selector the selector to be evaluated
   * @param expression the expression to be used
   * @param arg the objects used in the evaluation
   * @return the object of the evaluation
   */
  public Object evalOnSelectorAll(String selector, String expression, Object arg) {
    return this.asyncPage.evalOnSelector(selector, expression, arg);
  }

  /**
   * evaluates on a selector.
   * @param selector the selector to be evaluated
   * @param expression the expression to be used
   * @return the object of the evaluation
   */
  public Object evalOnSelector(String selector, String expression) {
    return this.asyncPage.evalOnSelector(selector, expression);
  }

  /**
   * evaluates on a selector.
   * @param selector the selector to be evaluated
   * @param expression the expression to be used
   * @param arg the objects used in the evaluation
   * @return the object of the evaluation
   */
  public Object evalOnSelector(String selector, String expression, Object arg) {
    return this.asyncPage.evalOnSelector(selector, expression, arg);
  }

  /**
   * evaluates an expression.
   * @param expression the expression to be evaluated
   * @return the evaluated object
   */
  public Object evaluate(String expression) {
    return this.asyncPage.evaluate(expression);
  }

  /**
   * evaluates an expression.
   * @param expression the expression to be evaluated
   * @param arg objects to be evaluated
   * @return the evaluated object
   */
  public Object evaluate(String expression, Object arg) {
    return this.asyncPage.evaluate(expression, arg);
  }

  /**
   * gets the attribute of a selector.
   * @param selector to get the attribute of
   * @param name the name of the attribute
   * @return the string value of the attribute
   */
  public String getAttribute(String selector, String name) {
    return this.asyncPage.getAttribute(selector, name);
  }

  /**
   * gets the attribute of a selector.
   * @param selector to get the attribute of
   * @param name the name of the attribute
   * @param options the get attribute options
   * @return the string value of the attribute
   */
  public String getAttribute(String selector, String name, Page.GetAttributeOptions options) {
    return this.asyncPage.getAttribute(selector, name, options);
  }

  /**
   * gets the text content of an element.
   * @param selector of the element to get the text content
   * @return the text content as a string
   */
  public String textContent(String selector) {
    return this.asyncPage.textContent(selector);
  }

  /**
   * gets the text content of an element.
   * @param selector of the element to get the text content
   * @param options the text content options
   * @return the text content as a string
   */
  public String textContent(String selector, Page.TextContentOptions options) {
    return this.asyncPage.textContent(selector, options);
  }

  /**
   * gets the content of the element.
   * @return the element content
   */
  public String content() {
    return this.asyncPage.content();
  }

  /**
   * gets the inner html of an element.
   * @param selector the selector for the element
   * @return the inner html string of the object
   */
  public String innerHTML(String selector) {
    return this.asyncPage.innerHTML(selector);
  }

  /**
   * gets the inner html of an element.
   * @param selector the selector for the element
   * @param options the inner text options
   * @return the inner html string of the object
   */
  public String innerHTML(String selector, Page.InnerHTMLOptions options) {
    return this.asyncPage.innerHTML(selector, options);
  }

  /**
   * gets the inner text of an element.
   * @param selector the selector for the element
   * @return the inner text string of the object
   */
  public String innerText(String selector) {
    return this.asyncPage.innerText(selector);
  }

  /**
   * gets the inner text of an element.
   * @param selector the selector for the element
   * @param options the inner text options
   * @return the inner text string of the object
   */
  public String innerText(String selector, Page.InnerTextOptions options) {
    return this.asyncPage.innerText(selector, options);
  }

  /**
   * inputs value on an element.
   * @param selector the selector to be inputted
   * @return the string return of the input
   */
  public String inputValue(String selector) {
    return this.asyncPage.inputValue(selector);
  }

  /**
   * inputs value on an element.
   * @param selector the selector to be inputted
   * @param options the input options
   * @return the string return of the input
   */
  public String inputValue(String selector, Page.InputValueOptions options) {
    return this.asyncPage.inputValue(selector, options);
  }

  /**
   * gets the title string.
   * @return the title string
   */
  public String title() {
    return this.asyncPage.title();
  }

  /**
   * navigating to with the page.
   * @return the response of going back
   */
  public Response navigateTo(String url) {
    return this.asyncPage.navigate(url);
  }

  /**
   * navigating to with the page.
   * @param options the go to options
   * @return the response of going back
   */
  public Response navigateTo(String url, Page.NavigateOptions options) {
    return this.asyncPage.navigate(url,options);
  }

  /**
   * navigating back with the page.
   * @return the response of going back
   */
  public Response goBack() {
    return this.asyncPage.goBack();
  }

  /**
   * navigating back with the page.
   * @param options the go back options
   * @return the response of going back
   */
  public Response goBack(Page.GoBackOptions options) {
    return this.asyncPage.goBack(options);
  }

  /**
   * navigating forward with the page.
   * @return the response of going forward
   */
  public Response goForward() {
    return this.asyncPage.goForward();
  }

  /**
   * navigating forward with the page.
   * @param options the go forward options
   * @return the response of going forward
   */
  public Response goForward(Page.GoForwardOptions options) {
    return this.asyncPage.goForward(options);
  }

  /**
   * reloads the page.
   * @return the response of reloading the page
   */
  public Response reload() {
    return this.asyncPage.reload();
  }

  /**
   * reloads the page with options.
   * @param options the reload options
   * @return the response of reloading the page
   */
  public Response reload(Page.ReloadOptions options) {
    return this.asyncPage.reload(options);
  }

  /**
   * gets the parent browser.
   * @return the parent browser
   */
  public Browser getParentBrowser() {
    return this.asyncPage.context().browser();
  }
}
