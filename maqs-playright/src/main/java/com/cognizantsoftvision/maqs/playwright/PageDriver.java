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

  public PageDriver(Page page) {
    this.setAsyncPage(page);
  }

  /// <summary>
  /// Gets the underlying async page object
  /// </summary>
  private Page asyncPage;

  public Page getAsyncPage() {
    return this.asyncPage;
  }

  private void setAsyncPage(Page page) {
    this.asyncPage = page;
  }

  /// <inheritdoc cref = "IPage.AddInitScriptAsync" />
  public void addInitScript(String script) {
//    this.AsyncPage.AddInitScriptAsync(script, scriptPath).Wait();
    this.asyncPage.addInitScript(script);
  }

  public void addInitScript(Path path) {
    this.asyncPage.addInitScript(path);
  }

  /// <inheritdoc cref = "IPage.BringToFrontAsync" />
  public void bringToFront() {
//    this.AsyncPage.BringToFrontAsync().Wait();
    this.asyncPage.bringToFront();
  }

  public void check(String selector) {
    check(selector, null);
  }

  /// <inheritdoc cref = "IPage.CheckAsync" />
  public void check(String selector, Page.CheckOptions options) {
//    this.AsyncPage.check(selector, options).Wait();
    this.asyncPage.check(selector, options);
  }

  /// <inheritdoc cref = "IPage.ClickAsync" />
  public void click(String selector) {
    click(selector, null);
  }

  /// <inheritdoc cref = "IPage.ClickAsync" />
  public void click(String selector, Page.ClickOptions options) {
//    this.AsyncPage.click(selector, options).Wait();
    this.asyncPage.click(selector, options);
  }

  /// <inheritdoc cref = "IPage.CloseAsync" />
  public void close() {
    close(null);
  }

  /// <inheritdoc cref = "IPage.CloseAsync" />
  public void close(Page.CloseOptions options) {
//    this.AsyncPage.CloseAsync(options).Wait();
    this.asyncPage.close(options);
  }

  /// <inheritdoc cref = "IPage.DblClickAsync" />
  public void doubleClick(String selector) {
    doubleClick(selector, null);
  }

  /// <inheritdoc cref = "IPage.DblClickAsync" />
  public void doubleClick(String selector, Page.ClickOptions options) {
//    this.AsyncPage.click(selector, options).Wait();
    this.asyncPage.click(selector, options);
  }

  public void dispatchEvent(String selector, String type) {
    dispatchEvent(selector, type, null, null);
  }

  /// <inheritdoc cref = "IPage.DispatchEventAsync" />
  public void dispatchEvent(String selector, String type, Object eventInit, Page.DispatchEventOptions options) {
//    this.AsyncPage.dispatchEvent(selector, type, eventInit, options).Wait();
    this.asyncPage.dispatchEvent(selector, type, eventInit, options);
  }

  public void dragAndDrop(String source, String target) {
    dragAndDrop(source, target, null);
  }

  /// <inheritdoc cref = "IPage.DragAndDropAsync" />
  public void dragAndDrop(String source, String target, Page.DragAndDropOptions options) {
//    this.AsyncPage.dragAndDrop(source, target, options).Wait();
    this.asyncPage.dragAndDrop(source, target, options);
  }

  public void fill(String selector, String value) {
    this.asyncPage.fill(selector, value);
  }

  /// <inheritdoc cref = "IPage.FillAsync" />
  public void fill(String selector, String value, Page.FillOptions options) {
//    this.AsyncPage.fill(selector, value, options).Wait();
    this.asyncPage.fill(selector, value, options);
  }

  public void focus(String selector) {
    focus(selector, null);
  }

  /// <inheritdoc cref = "IPage.FocusAsync" />
  public void focus(String selector, Page.FocusOptions options) {
//    this.AsyncPage.focus(selector, options).Wait();
    this.asyncPage.focus(selector, options);
  }

  public void hover(String selector) {
    //    this.AsyncPage.hover(selector, options).Wait();
    hover(selector, null);
  }

  /// <inheritdoc cref = "IPage.HoverAsync" />
  public void hover(String selector, Page.HoverOptions options) {
//    this.AsyncPage.hover(selector, options).Wait();
    this.asyncPage.hover(selector, options);
  }

  /// <inheritdoc cref = "IPage.PressAsync" />
  public void press(String selector, String key) {
    //    this.asyncPage.press(selector, key, options).Wait();
    press(selector, key, null);
  }

  /// <inheritdoc cref = "IPage.PressAsync" />
  public void press(String selector, String key, Page.PressOptions options) {
//    this.asyncPage.press(selector, key, options).Wait();
    this.asyncPage.press(selector, key, options);
  }

  public void setChecked(String selector, boolean checkedState) {
    setChecked(selector, checkedState, null);
  }

  /// <inheritdoc cref = "IPage.SetCheckedAsync" />
  public void setChecked(String selector, boolean checkedState, Page.SetCheckedOptions options) {
//    this.asyncPage.setChecked(selector, checkedState, options).Wait();
    this.asyncPage.setChecked(selector, checkedState, options);
  }

  /// <inheritdoc cref = "IPage.SetContentAsync" />
  public void setContent(String html) {
    setContent(html, null);
  }

  /// <inheritdoc cref = "IPage.SetContentAsync" />
  public void setContent(String html, Page.SetContentOptions options) {
//    this.asyncPage.setContent(html, options).Wait();
    this.asyncPage.setContent(html, options);
  }

  /// <inheritdoc cref = "IPage.SetExtraHTTPHeadersAsync" />
  public void setExtraHTTPHeaders(Map<String, String> headers) {
//    this.AsyncPage.setExtraHTTPHeaders(headers).Wait();
    this.asyncPage.setExtraHTTPHeaders(headers);
  }

  public void setInputFiles(String selector, FilePayload files) {
    setInputFiles(selector, files, null);
  }

  /// <inheritdoc cref = "IPage.SetInputFilesAsync(string, FilePayload, PageSetInputFilesOptions)" />
  public void setInputFiles(String selector, FilePayload files, Page.SetInputFilesOptions options) {
//    this.asyncPage.setInputFiles(selector, files, options).Wait();
    this.asyncPage.setInputFiles(selector, files, options);
  }

  public void setInputFiles(String selector, FilePayload[] files) {
    setInputFiles(selector, files, null);
  }

  /// <inheritdoc cref = "IPage.SetInputFilesAsync(string, IEnumerable{FilePayload}, PageSetInputFilesOptions)" />
  public void setInputFiles(String selector, FilePayload[] files, Page.SetInputFilesOptions options) {
//    this.asyncPage.setInputFiles(selector, files, options).Wait();
    this.asyncPage.setInputFiles(selector, files, options);
  }

  public void setInputFiles(String selector, Path[] files) {
    setInputFiles(selector, files, null);
  }

  /// <inheritdoc cref = "IPage.SetInputFilesAsync(string, IEnumerable{string}, PageSetInputFilesOptions)" />
  public void setInputFiles(String selector, Path[] files, Page.SetInputFilesOptions options) {
//    this.asyncPage.setInputFiles(selector, files, options).Wait();
    this.asyncPage.setInputFiles(selector, files, options);
  }

  public void setInputFiles(String selector, String files) {
    setInputFiles(selector, files, null);
  }

  /// <inheritdoc cref = "IPage.SetInputFilesAsync(string, String, PageSetInputFilesOptions)" />
  public void setInputFiles(String selector, String files, Page.SetInputFilesOptions options) {
//    this.asyncPage.setInputFiles(selector, Paths.get(files), options).Wait();
    this.asyncPage.setInputFiles(selector, Paths.get(files), options);
  }

  public ViewportSize getViewPortSize() {
    return this.getAsyncPage().viewportSize();
  }

  /// <inheritdoc cref = "IPage.SetViewportSizeAsync" />
  public void setViewportSize(int width, int height) {
//    this.AsyncPage.setViewportSize(width, height).Wait();
    this.asyncPage.setViewportSize(width, height);
  }

  public void tap(String selector) {
    //    this.AsyncPage.tap(selector, options).Wait();
    tap(selector, null);
  }

  /// <inheritdoc cref = "IPage.TapAsync" />
  public void tap(String selector, Page.TapOptions options) {
//    this.asyncPage.tap(selector, options).Wait();
    this.asyncPage.tap(selector, options);
  }

  public void type(String selector, String text) {
    //    this.AsyncPage.TypeAsync(selector, text, options).Wait();
    type(selector, text, null);
  }

  /// <inheritdoc cref = "IPage.TypeAsync" />
  public void type(String selector, String text, Page.TypeOptions options) {
//    this.AsyncPage.TypeAsync(selector, text, options).Wait();
    this.asyncPage.type(selector, text, options);
  }

  public void uncheck(String selector) {
    //    this.AsyncPage.uncheck(selector, options).Wait();
    uncheck(selector, null);
  }

  /// <inheritdoc cref = "IPage.UncheckAsync" />
  public void uncheck(String selector, Page.UncheckOptions options) {
//    this.AsyncPage.uncheck(selector, options).Wait();
    this.asyncPage.uncheck(selector, options);
  }

  public void waitForLoadState(LoadState state) {
    //    this.AsyncPage.waitForLoadState(state, options).Wait();
    waitForLoadState(state, null);
  }

  /// <inheritdoc cref = "IPage.WaitForLoadStateAsync" />
  public void waitForLoadState(LoadState state, Page.WaitForLoadStateOptions options) {
//    this.AsyncPage.waitForLoadState(state, options).Wait();
    this.asyncPage.waitForLoadState(state, options);
  }

  /// <inheritdoc cref = "IPage.WaitForTimeoutAsync" />
  public void waitForTimeout(float timeout) {
//    this.AsyncPage.waitForTimeout(timeout).Wait();
    this.asyncPage.waitForTimeout(timeout);
  }

  public void waitForURL(Predicate<String> url) {
    //    this.AsyncPage.waitForURL(url, options).Wait();
    this.asyncPage.waitForURL(url);
  }

  /// <inheritdoc cref = "IPage.WaitForURLAsync(Func{string, bool}, PageWaitForURLOptions)" />
  public void waitForURL(Predicate<String> url, Page.WaitForURLOptions options) {
//    this.AsyncPage.waitForURL(url, options).Wait();
    this.asyncPage.waitForURL(url, options);
  }

  public void waitForURL(Pattern url) {
    waitForURL(url, null);
  }

  /// <inheritdoc cref = "IPage.WaitForURLAsync(Regex, PageWaitForURLOptions)" />
  public void waitForURL(Pattern url, Page.WaitForURLOptions options) {
    this.asyncPage.waitForURL(url, options);
  }

  public void waitForURL(String url) {
    //    this.AsyncPage.waitForURL(url, options).Wait();
    this.asyncPage.waitForURL(url);
  }

  /// <inheritdoc cref = "IPage.WaitForURLAsync(string, PageWaitForURLOptions)" />
  public void waitForURL(String url, Page.WaitForURLOptions options) {
//    this.AsyncPage.waitForURL(url, options).Wait();
    this.asyncPage.waitForURL(url, options);
  }

  public boolean isChecked(String selector) {
    //    return this.AsyncPage.isChecked(selector, options).Result;
    return this.asyncPage.isChecked(selector);
  }

  /// <inheritdoc cref = "IPage.IsCheckedAsync"  />
  public boolean isChecked(String selector, Page.IsCheckedOptions options) {
//    return this.AsyncPage.isChecked(selector, options).Result;
    return this.asyncPage.isChecked(selector, options);
  }

  public boolean isDisabled(String selector) {
    //    return this.AsyncPage.isDisabled(selector, options).Result;
    return this.asyncPage.isDisabled(selector);
  }

  /// <inheritdoc cref = "IPage.IsDisabledAsync"  />
  public boolean isDisabled(String selector, Page.IsDisabledOptions options) {
//    return this.AsyncPage.isDisabled(selector, options).Result;
    return this.asyncPage.isDisabled(selector, options);
  }

  public boolean isEditable(String selector) {
    return this.asyncPage.isEditable(selector);
  }

  /// <inheritdoc cref = "IPage.IsEditableAsync"  />
  public boolean isEditable(String selector, Page.IsEditableOptions options) {
    return this.asyncPage.isEditable(selector, options);
  }

  public boolean isEnabled(String selector) {
    return this.asyncPage.isEnabled(selector);
  }

  /// <inheritdoc cref = "IPage.IsEnabledAsync"  />
  public boolean isEnabled(String selector, Page.IsEnabledOptions options) {
    return this.asyncPage.isEnabled(selector, options);
  }

  public boolean isHidden(String selector) {
    return this.asyncPage.isHidden(selector);
  }

  /// <inheritdoc cref = "IPage.IsHiddenAsync"  />
  public boolean isHidden(String selector, Page.IsHiddenOptions options) {
    return this.asyncPage.isHidden(selector, options);
  }

  public boolean isVisible(String selector) {
    return this.asyncPage.isVisible(selector);
  }

  /// <inheritdoc cref = "IPage.IsVisibleAsync"  />
  public boolean isVisible(String selector, Page.IsVisibleOptions options) {
    return this.asyncPage.isVisible(selector, options);
  }

  /// <summary>
  /// Check that the element is eventually visible
  /// </summary>
  /// <param name="selector">Element selector</param>
  /// <param name="options">Visible check options</param>
  /// <returns>True if the element becomes visible within the page timeout</returns>
  public boolean isEventuallyVisible(String selector) {
    try {
      this.getAsyncPage().waitForSelector(selector);
      return true;
    } catch (Exception e){
      return false;
    }
  }

  /// <summary>
  /// Check that the element is eventually visible
  /// </summary>
  /// <param name="selector">Element selector</param>
  /// <param name="options">Visible check options</param>
  /// <returns>True if the element becomes visible within the page timeout</returns>
  public boolean isEventuallyVisible(String selector, Page.IsVisibleOptions options) {
    try {
      Page.WaitForSelectorOptions selectorOptions = new Page.WaitForSelectorOptions();
      selectorOptions.setState(WaitForSelectorState.VISIBLE);
      selectorOptions.strict = options != null && options.strict;
      this.getAsyncPage().waitForSelector(selector, selectorOptions);
      return true;
    } catch (Exception e){
      return false;
    }
  }

  /// <summary>
  /// Check that the element stops being visible
  /// </summary>
  /// <param name="selector">Element selector</param>
  /// <param name="options">Visible check options</param>
  /// <returns>True if the element becomes is hidden or gone within the page timeout</returns>
  public boolean isEventuallyGone(String selector) {
    try {
      this.getAsyncPage().waitForSelector(selector);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /// <summary>
  /// Check that the element stops being visible
  /// </summary>
  /// <param name="selector">Element selector</param>
  /// <param name="options">Visible check options</param>
  /// <returns>True if the element becomes is hidden or gone within the page timeout</returns>
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

  public ElementHandle querySelector(String selector) {
    return this.asyncPage.querySelector(selector);
  }

  /// <inheritdoc cref = "IPage.QuerySelectorAsync"  />
  public ElementHandle querySelector(String selector, Page.QuerySelectorOptions options) {
    return this.asyncPage.querySelector(selector, options);
  }

  public ElementHandle waitForSelector(String selector) {
    //    return this.AsyncPage.WaitForSelectorAsync(selector, options).Result;
    return this.asyncPage.waitForSelector(selector);
  }

  /// <inheritdoc cref = "IPage.WaitForSelectorAsync"  />
  public ElementHandle waitForSelector(String selector, Page.WaitForSelectorOptions options) {
//    return this.AsyncPage.WaitForSelectorAsync(selector, options).Result;
    return this.asyncPage.waitForSelector(selector, options);
  }

  public ElementHandle addScriptTag() {
    //    return this.AsyncPage.AddScriptTagAsync(options).Result;
    return this.asyncPage.addScriptTag();
  }

  /// <inheritdoc cref = "IPage.AddScriptTagAsync"  />
  public ElementHandle addScriptTag(Page.AddScriptTagOptions options) {
//    return this.AsyncPage.AddScriptTagAsync(options).Result;
    return this.asyncPage.addScriptTag(options);
  }

  public ElementHandle addStyleTag() {
    return this.asyncPage.addStyleTag();
  }

  /// <inheritdoc cref = "IPage.AddStyleTagAsync"  />
  public ElementHandle addStyleTag(Page.AddStyleTagOptions options) {
    return this.asyncPage.addStyleTag(options);
  }

  /// <inheritdoc cref = "IPage.QuerySelectorAllAsync"  />
  public List<ElementHandle> querySelectorAll(String selector) {
//    return this.AsyncPage.QuerySelectorAllAsync(selector).Result;
    return Collections.unmodifiableList(this.asyncPage.querySelectorAll(selector));
  }

  public List<String> selectOption(String selector, ElementHandle values) {
    //    return this.AsyncPage.SelectOptionAsync(selector, values, options).Result;
    return this.asyncPage.selectOption(selector, values);
  }

  /// <inheritdoc cref = "IPage.SelectOptionAsync(String, IElementHandle, PageSelectOptionOptions)"  />
  public List<String> selectOption(String selector, ElementHandle values, Page.SelectOptionOptions options) {
//    return this.AsyncPage.SelectOptionAsync(selector, values, options).Result;
    return this.asyncPage.selectOption(selector, values, options);
  }

  public List<String> selectOption(String selector, ElementHandle[] values) {
    //    return this.AsyncPage.SelectOptionAsync(selector, values, options).Result;
    return this.asyncPage.selectOption(selector, values);
  }

  /// <inheritdoc cref = "IPage.SelectOptionAsync(String, IEnumerable{IElementHandle}, PageSelectOptionOptions)"  />
  public List<String> selectOption(String selector, ElementHandle[] values, Page.SelectOptionOptions options) {
//    return this.AsyncPage.SelectOptionAsync(selector, values, options).Result;
    return this.asyncPage.selectOption(selector, values, options);
  }

  public List<String> selectOption(String selector, SelectOption[] values) {
    //    return this.AsyncPage.SelectOptionAsync(selector, values, options).Result;
    return Collections.unmodifiableList(this.asyncPage.selectOption(selector, values));
  }

  /// <inheritdoc cref = "IPage.SelectOptionAsync(String, IEnumerable{SelectOptionValue}, PageSelectOptionOptions)"  />
  public List<String> selectOption(String selector, SelectOption[] values, Page.SelectOptionOptions options) {
//    return this.AsyncPage.SelectOptionAsync(selector, values, options).Result;
    return Collections.unmodifiableList(this.asyncPage.selectOption(selector, values, options));
  }

  public List<String> selectOption(String selector, String[] values) {
    //    return this.AsyncPage.SelectOptionAsync(selector, values, options).Result;
    return Collections.unmodifiableList(this.asyncPage.selectOption(selector, values));
  }

  /// <inheritdoc cref = "IPage.SelectOptionAsync(string, IEnumerable{string}, PageSelectOptionOptions)"  />
  public List<String> selectOption(String selector, String[] values, Page.SelectOptionOptions options ) {
//    return this.AsyncPage.SelectOptionAsync(selector, values, options).Result;
    return Collections.unmodifiableList(this.asyncPage.selectOption(selector, values, options));
  }

  public List<String> selectOption(String selector, SelectOption values) {
    //    return this.AsyncPage.SelectOptionAsync(selector, values, options).Result;
    return Collections.unmodifiableList(this.asyncPage.selectOption(selector, values));
  }

  /// <inheritdoc cref = "IPage.SelectOptionAsync(String, SelectOptionValue, PageSelectOptionOptions)"  />
  public List<String> selectOption(String selector, SelectOption values, Page.SelectOptionOptions options) {
//    return this.AsyncPage.SelectOptionAsync(selector, values, options).Result;
    return Collections.unmodifiableList(this.asyncPage.selectOption(selector, values, options));
  }

  public List<String> selectOption(String selector, String values) {
    //    return this.AsyncPage.SelectOptionAsync(selector, values, options).Result;
    return Collections.unmodifiableList(this.asyncPage.selectOption(selector, values));
  }

  /// <inheritdoc cref = "IPage.SelectOptionAsync(String, String, PageSelectOptionOptions)"  />
  public List<String> selectOption(String selector, String values, Page.SelectOptionOptions options) {
//    return this.AsyncPage.SelectOptionAsync(selector, values, options).Result;
    return Collections.unmodifiableList(this.asyncPage.selectOption(selector, values, options));
  }

  public Object evalOnSelectorAll(String selector, String expression) {
    //    return this.AsyncPage.EvalOnSelectorAllAsync(selector, expression, arg).Result;
    return this.asyncPage.evalOnSelector(selector, expression);
  }

  /// <inheritdoc cref = "IPage.EvalOnSelectorAllAsync"  />
  public Object evalOnSelectorAll(String selector, String expression, Object arg) {
//    return this.AsyncPage.EvalOnSelectorAllAsync(selector, expression, arg).Result;
    return this.asyncPage.evalOnSelector(selector, expression, arg);
  }

  public Object evalOnSelector(String selector, String expression) {
    //    return this.AsyncPage.EvalOnSelectorAsync(selector, expression, arg).Result;
    return this.asyncPage.evalOnSelector(selector, expression);
  }

  /// <inheritdoc cref = "IPage.EvalOnSelectorAsync"  />
  public Object evalOnSelector(String selector, String expression, Object arg) {
//    return this.AsyncPage.EvalOnSelectorAsync(selector, expression, arg).Result;
    return this.asyncPage.evalOnSelector(selector, expression, arg);
  }

  public Object evaluate(String expression) {
    //    return this.AsyncPage.EvaluateAsync(expression, arg).Result;
    return this.asyncPage.evaluate(expression);
  }

  /// <inheritdoc cref = "IPage.EvaluateAsync"  />
  public Object evaluate(String expression, Object arg) {
//    return this.AsyncPage.EvaluateAsync(expression, arg).Result;
    return this.asyncPage.evaluate(expression, arg);
  }

  public String getAttribute(String selector, String name) {
    //    return this.AsyncPage.GetAttributeAsync(selector, name, options).Result;
    return this.asyncPage.getAttribute(selector, name);
  }

  /// <inheritdoc cref = "IPage.GetAttributeAsync"  />
  public String getAttribute(String selector, String name, Page.GetAttributeOptions options) {
//    return this.AsyncPage.GetAttributeAsync(selector, name, options).Result;
    return this.asyncPage.getAttribute(selector, name, options);
  }

  public String textContent(String selector) {
    //    return this.AsyncPage.TextContentAsync(selector, options).Result;
    return this.asyncPage.textContent(selector);
  }

  /// <inheritdoc cref = "IPage.TextContentAsync"  />
  public String textContent(String selector, Page.TextContentOptions options) {
//    return this.AsyncPage.TextContentAsync(selector, options).Result;
    return this.asyncPage.textContent(selector, options);
  }

  /// <inheritdoc cref = "IPage.ContentAsync"  />
  public String content() {
//    return this.AsyncPage.ContentAsync().Result;
    return this.asyncPage.content();
  }

  public String innerHTML(String selector) {
    //    return this.AsyncPage.InnerHTMLAsync(selector, options).Result;
    return this.asyncPage.innerHTML(selector);
  }

  /// <inheritdoc cref = "IPage.InnerHTMLAsync"  />
  public String innerHTML(String selector, Page.InnerHTMLOptions options) {
//    return this.AsyncPage.InnerHTMLAsync(selector, options).Result;
    return this.asyncPage.innerHTML(selector, options);
  }

  public String innerText(String selector) {
    //    return this.AsyncPage.innerText(selector, options).Result;
    return this.asyncPage.innerText(selector);
  }

  /// <inheritdoc cref = "IPage.InnerTextAsync"  />
  public String innerText(String selector, Page.InnerTextOptions options) {
//    return this.AsyncPage.innerText(selector, options).Result;
    return this.asyncPage.innerText(selector, options);
  }

  public String inputValue(String selector) {
    //    return this.AsyncPage.InputValueAsync(selector, options).Result;
    return this.asyncPage.inputValue(selector);
  }

  /// <inheritdoc cref = "IPage.InputValueAsync"  />
  public String inputValue(String selector, Page.InputValueOptions options) {
//    return this.AsyncPage.InputValueAsync(selector, options).Result;
    return this.asyncPage.inputValue(selector, options);
  }

  /// <inheritdoc cref = "IPage.TitleAsync"  />
  public String title() {
//    return this.AsyncPage.title().Result;
    return this.asyncPage.title();
  }

  public Response navigateTo(String url) {
    //    return this.AsyncPage.navigate(url,options).Result;
    return this.asyncPage.navigate(url);
  }

  /// <inheritdoc cref = "IPage.GotoAsync"  />
  public Response navigateTo(String url, Page.NavigateOptions options) {
//    return this.AsyncPage.navigate(url,options).Result;
    return this.asyncPage.navigate(url,options);
  }

  /// <inheritdoc cref = "IPage.GoBackAsync"  />
  public Response goBack() {
    return this.asyncPage.goBack();
  }

  /// <inheritdoc cref = "IPage.GoBackAsync"  />
  public Response goBack(Page.GoBackOptions options) {
//    return this.AsyncPage.GoBackAsync(options).Result;
    return this.asyncPage.goBack(options);
  }

  public Response goForward() {
    //    return this.AsyncPage.goForward(options).Result;
    return this.asyncPage.goForward();
  }

  /// <inheritdoc cref = "IPage.GoForwardAsync"  />
  public Response goForward(Page.GoForwardOptions options) {
//    return this.AsyncPage.goForward(options).Result;
    return this.asyncPage.goForward(options);
  }

  public Response reload() {
    return this.asyncPage.reload();
  }

  /// <inheritdoc cref = "IPage.ReloadAsync"  />
  public Response reload(Page.ReloadOptions options) {
//    return this.AsyncPage.reload(options).Result;
    return this.asyncPage.reload(options);
  }

  /// <summary>
  /// Gets the underlying async page object
  /// </summary>
  private Browser parentBrowser;

  public Browser getParentBrowser() {
    return this.asyncPage.context().browser();
  }
}
