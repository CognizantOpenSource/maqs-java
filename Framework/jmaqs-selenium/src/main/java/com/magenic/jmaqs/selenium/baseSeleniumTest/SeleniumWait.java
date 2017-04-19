/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium.baseSeleniumTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Selenium waiter This is the tool-class used for waiting. It can be used to wait for many
 * circumstances (element exist, not exist, be visible, to equal a desired value, etc..
 */
public class SeleniumWait {
  // TODO these values need to be removed, and instead accessed from the Config/Execution properties
  // once implemented
  
  /**
   * The default timeout.
   */
  private static final int DEFAULT_TIMEOUT = 20;
  
  /**
   * The clear time.
   */
  private static final int CLEARTIME = 5;
  
  /**
   * The default retry time.
   */
  private static final int DEFAULT_FLUENT_RETRY_TIME = 250;
  
  /**
   * One thousand.
   */
  private static final int ONE_THOUSAND = 1000;

  /**
   * The default value of the Header size... Your header's size may be different.
   */
  private static final int HEADER_SIZE = 90;

  /**
   * The default value of the page's body. Your page might have a different Header value.
   */
  private static final By BODY_BY = By.cssSelector("BODY");


  /**
   * The Webdriver that the test is currently running on.
   */
  private WebDriver browser;
  
  
  /**
   * The retry time.
   */
  private int fluentRetryTime;


  /**
   * The implicit wait timeout.
   */
  private int implicitWaitTimeout;

  /**
   * Constructor for SeleniumWait object.
   * 
   * @param browser
   *          WebDriver
   */
  public SeleniumWait(WebDriver browser) {
    this(browser, DEFAULT_TIMEOUT);
  }

  /**
   * Constructor for SeleniumWait object.
   * 
   * @param browser
   *          WebDriver
   * @param implicitWaitTimeout
   *          int value of seconds to wait before timing out
   */
  public SeleniumWait(WebDriver browser, int implicitWaitTimeout) {
    this(browser, implicitWaitTimeout, DEFAULT_FLUENT_RETRY_TIME);
  }

  /**
   * Constructor for SeleniumWait object.
   * 
   * @param browser
   *          WebDriver
   * @param implicitWaitTimeout
   *          int value of seconds to wait before timing out
   * @param fluentRetryTime
   *          int value of seconds to use for fluent retry
   */
  public SeleniumWait(WebDriver browser, int implicitWaitTimeout, int fluentRetryTime) {
    this.browser = browser;
    this.implicitWaitTimeout = implicitWaitTimeout;
    this.fluentRetryTime = fluentRetryTime;
  }

  /**
   * Wait for the specified element to be present on the pages DOM. The first element located with
   * the specified By value is returned.
   * 
   * @param by
   *          Selector to wait for, and return
   * @return WebElement - first one found with by
   */
  public WebElement waitForElement(final By by) {
    return this.waitForElement(by, this.implicitWaitTimeout);
  }

  /**
   * Wait for the specified element to be present on the pages DOM. The first element located with
   * the specified By value is returned.
   * 
   * @param by
   *          Selector to wait for and return
   * @param assertFound
   *          boolean - true to assert the element was found
   * @return WebElement
   */
  public WebElement waitForElement(final By by, boolean assertFound) {
    WebElement element = this.waitForElement(by);

    if (element == null && assertFound) {
      Assert.fail(String.format(
          "The selector with value [%s] couldn't be found." + System.getProperty("line.separator"),
          by.toString()));
    }
    return element;
  }

  /**
   * Wait for the specified element to be present on the pages DOM. The first element located with
   * the specified By value is returned.
   * 
   * @param by
   *          Selector to wait for, and return
   * @param timeOutSeconds
   *          - the number of seconds to wait for the selector to be displayed before failing
   * 
   *          Note: Missing selectors will not be printed to the console.
   * 
   * @return WebElement - first one found with by
   */
  public WebElement waitForElement(final By by, int timeOutSeconds) {
    return this.waitForElement(by, timeOutSeconds, false);
  }

  /**
   * Wait for the specified element to be present on the pages DOM. The first element located with
   * the specified By value is returned.
   * 
   * @param by
   *          Selector to wait for, and return
   * @param timeOutSeconds
   *          - the number of seconds to wait for the selector to be displayed before failing
   * @param printMissingToConsole
   *          - boolean: true to print missing selectors to the console
   * @return WebElement - first one found with by
   */
  public WebElement waitForElement(final By by, int timeOutSeconds, boolean printMissingToConsole) {
    WebElement element;
    try {
      this.setImplicitWait(timeOutSeconds);

      WebDriverWait wait = new WebDriverWait(browser, timeOutSeconds);
      element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));

      this.resetImplicitWait();

      return element;
    } catch (Exception e) {
      if (printMissingToConsole) {
        this.printSelectorNotFoundError(by.toString(), e);
      }
    }

    this.resetImplicitWait();
    return null;
  }

  /**
   * Wait for the element to exist and be visible.
   *
   * @param by
   *          Selector to wait for
   * @return WebElement of the selector provided
   */
  public WebElement waitForVisibleElement(final By by) {
    WebElement element = this.waitForElement(by, this.implicitWaitTimeout);
    Assert.assertTrue(element.isDisplayed());
    return element;
  }

  /**
   * Wait for the element to exist and be visible.
   *
   * @param by
   *          Selector to wait for
   * @return boolean true if element is found
   */
  public boolean waitUntilVisibleElement(final By by) {
    return waitForVisibleElement(by) != null;
  }

  /**
   * Wait for the element to not be displayed or visible.
   * 
   * @param by
   *          Selector to not be displayed or visible
   */
  public void waitForAbsentElement(final By by) {
    this.waitForAbsentElement(by, this.implicitWaitTimeout);
  }

  /**
   * Wait for the element to not be displayed or visible.
   * 
   * @param by
   *          Selector to not be displayed or visible
   * @param waitTime
   *          int value of seconds to wait before timing out
   */
  public void waitForAbsentElement(final By by, final int waitTime) {
    Assert.assertTrue(this.fluentWaitInvisible(by, waitTime),
        String.format("The selector [%s] was still found when it should have disappeared."
            + System.getProperty("line.separator"), by));
  }

  /**
   * Wait for the element to not be displayed or visible.
   * 
   * @param element
   *          WebElement to be invisible/not displayed
   */
  public void waitForAbsentElement(WebElement element) {
    Assert.assertTrue(this.fluentWaitStale(element));
  }

  /**
   * Wait for the element to not be displayed or visible.
   * 
   * @param by
   *          Selector to not be displayed or visible
   * @param waitTime
   *          int value of seconds to wait before timing out
   * @return boolean - true if not displayed
   */
  public boolean waitUntilAbsentElement(final By by, final int waitTime) {
    return this.fluentWaitInvisible(by, waitTime);
  }

  /**
   * Wait for a selector to present, and then return a list of all WebElements that are located by
   * that selector.
   * 
   * @param by
   *          Selector value to wait for
   * @return List of WebElements - all web elements found by the specified selector
   */
  public List<WebElement> waitForElements(final By by) {
    return this.waitForElements(by, this.implicitWaitTimeout);
  }

  /**
   * Wait for a selector to present, and then return a list of all WebElements that are located by
   * that selector.
   * 
   * @param by
   *          Selector value to wait for
   * @param timeOutInSeconds
   *          - number of seconds
   * @return List of WebElements - all web elements found by the specified selector
   */
  public List<WebElement> waitForElements(final By by, int timeOutInSeconds) {
    List<WebElement> elements;
    try {
      this.setImplicitWait(timeOutInSeconds);

      WebDriverWait waiter = new WebDriverWait(browser, timeOutInSeconds);
      elements = waiter.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
      this.resetImplicitWait();

      return elements;
    } catch (Exception e) {
      this.printSelectorNotFoundError(by.toString(), e);
    }

    this.resetImplicitWait();
    return null;
  }

  /**
   * Wait for the specified element to have the specified text.
   * 
   * @param by
   *          By selector to examine
   * @param text
   *          String to search for in the text
   * @return WebElement - element, null if not found and assert == false
   */
  public WebElement waitForExactText(final By by, final String text) {
    return waitForExactText(by, text, true);
  }

  /**
   * Wait for the specified element to have the specified text.
   * 
   * @param by
   *          By selector to examine
   * @param text
   *          String to search for in the text
   * @param assertFound
   *          boolean - true to assert the text matches
   * @return WebElement - element, null if not found and assert == false
   */
  public WebElement waitForExactText(final By by, final String text, boolean assertFound) {
    WebElement element = this.browser.findElement(by);

    if (element == null && assertFound) {
      Assert.fail(String.format(
          "The specified selector [%s] couldn't be found." + System.getProperty("line.separator"),
          by.toString()));
    }

    if (!waitUntilExactText(by, text) && assertFound) {
      String displayedText = element.getText();

      Assert.assertEquals(displayedText, text,
          String.format(
              "The observed text [%s] didn't equal the expected text [%s] for selector [%s]."
                  + System.getProperty("line.separator"),
              displayedText, text, by.toString()));
    }

    return element;
  }

  /**
   * Wait for the exact text to be present in the specified element.
   * 
   * @param by
   *          Selector to examine for the specified text
   * @param text
   *          String value to verify the specified selector contains
   * @return boolean - true if the text was found in the element - else false
   */
  public boolean waitUntilExactText(final By by, final String text) {
    return this.waitUntilExactText(by, text, this.implicitWaitTimeout);
  }

  /**
   * Wait for the exact text to be present in the specified element.
   * 
   * @param by
   *          Selector to examine for the specified text
   * @param text
   *          String value to verify the specified selector contains
   * @param timeOut
   *          int value of seconds to wait before timing out
   * @return boolean - true if the text was found in the element - else false
   */
  public boolean waitUntilExactText(final By by, final String text, int timeOut) {
    boolean present = false;
    try {
      this.setImplicitWait(timeOut);
      new WebDriverWait(browser, timeOut) {
      }.until(new ExpectedCondition<Boolean>() {
        public Boolean apply(WebDriver browser) {
          return doesTextMatch(by, text);
        }
      });
      present = doesTextMatch(by, text);

      this.resetImplicitWait();

      return present;
    } catch (Exception e) {
      this.printSelectorNotFoundError(by.toString(), e);
    }

    this.resetImplicitWait();
    return false;
  }

  /**
   * Wait for an element to contain a specified text.
   * 
   * @param by
   *          Selector to check the containing text
   * @param text
   *          String that should be contained within the selector
   * @return boolean - true if the text is contained in the selector, else false
   */
  public boolean waitUntilContainsText(final By by, final String text) {
    return this.waitUntilContainsText(by, text, this.implicitWaitTimeout);
  }

  /**
   * Wait for an element to contain a specified text.
   * 
   * @param by
   *          Selector to check the containing text
   * @param text
   *          String that should be contained within the selector
   * @param timeOut
   *          int value of seconds to wait before timing out
   * @return boolean - true if the text is contained in the selector, else false
   */
  public boolean waitUntilContainsText(final By by, final String text, int timeOut) {
    WebElement element = this.waitForElement(by, timeOut);

    if (element != null) {
      return element.getText().contains(text);
    }
    return false;
  }

  /**
   * Wait for an element to contain a specified text.
   * 
   * @param by
   *          Selector to check the containing text
   * @param text
   *          String that should be contained within the selector
   * @param assertFound
   *          boolean to assert the selector is found and contains the text
   * @return WebElement
   */
  public WebElement waitForContainsText(final By by, final String text, boolean assertFound) {
    WebElement element = this.waitForElement(by);

    if (!this.waitUntilContainsText(by, text, this.implicitWaitTimeout) && assertFound) {
      if (element == null) {
        Assert.fail(
            String.format("The selector [%s] wasn't found." + System.getProperty("line.separator"),
                by.toString()));
      } else {
        Assert.fail(String.format("The expected text [%s] wasn't contained in the selector [%s]."
            + System.getProperty("line.separator"), text, by.toString()));
      }
    }

    return element;
  }

  /**
   * Wait for an attribute of the specified selector to be present.
   * 
   * @param by
   *          Selector to look for
   * @param attribute
   *          String value of the attribute to look at on the specified selector
   * @param text
   *          String value of the text to look for in the attribute
   */
  public void waitForAttribute(final By by, final String attribute, final String text) {
    if (!waitUntilAttributeTextEquals(by, attribute, text)) {
      WebElement element = waitForElement(by);

      if (element == null) {
        Assert.fail("The specifed element couldn't be found. [" + by.toString() + "]");
      } else {
        Assert.fail(String.format(
            "The expected text [%s] wasn't contained in the attribute [%s] for selector [%s]."
                + System.getProperty("line.separator"),
            text, attribute, by.toString()));
      }
    }
  }

  /**
   * Wait for an attribute of the specified selector to be present and equal the desired value.
   * 
   * @param by
   *          Selector to look for
   * @param attribute
   *          String value of the attribute to look at on the specified selector
   * @param text
   *          String value of the text to look for in the attribute
   * @return true if the attribute with the specified text value is found, else false
   */
  public boolean waitUntilAttributeTextEquals(final By by, final String attribute,
      final String text) {
    return this.waitUntilAttributeTextEquals(by, attribute, text, this.implicitWaitTimeout);
  }

  /**
   * Wait for an attribute of the specified selector to be present.
   * 
   * @param by
   *          Selector to look for
   * @param attribute
   *          String value of the attribute to look at on the specified selector
   * @param text
   *          String value of the text to look for in the attribute
   * @param timeOut
   *          int version of the timeout in seconds
   * @return true if the attribute with the specified text value is found, else false
   */
  public boolean waitUntilAttributeTextEquals(final By by, final String attribute,
      final String text, int timeOut) {
    return waitUntilAttribute(by, attribute, text, timeOut, false);
  }

  /**
   * Wait for an attribute of the specified selector to be present.
   * 
   * @param by
   *          Selector to look for
   * @param attribute
   *          String value of the attribute to look at on the specified selector
   * @param text
   *          String value of the text to look for in the attribute
   * @param assertFound
   *          - true to assert the element is found, false will return null if it isn't found
   * @return Webelement of the selector that is found
   */
  public WebElement waitForAttributeTextEquals(final By by, final String attribute,
      final String text, boolean assertFound) {
    return this.waitForAttributeTextEquals(by, attribute, text, this.implicitWaitTimeout,
        assertFound);
  }

  /**
   * Wait for an attribute of the specified selector to be present and equal the desired value.
   * 
   * @param by
   *          Selector to look for
   * @param attribute
   *          String value of the attribute to look at on the specified selector
   * @param text
   *          String value of the text to look for in the attribute
   * @param timeOut
   *          int version of the timeout in seconds
   * @param assertFound
   *          - true to assert the element is found, false will return null if it isn't found
   * @return WebElement of the selector that is found
   */
  public WebElement waitForAttributeTextEquals(final By by, final String attribute,
      final String text, int timeOut, boolean assertFound) {
    if (this.waitUntilAttributeTextEquals(by, attribute, text, timeOut)) {
      return this.browser.findElement(by);
    }
    if (assertFound) {
      Assert.fail(
          String.format("Selector [%s] couldn't be found." + System.getProperty("line.separator"),
              by.toString()));
    }

    // selector/element not found, and assert==false, so return null.
    return null;
  }

  /**
   * Wait for an attribute of the specified selector to be present, and contain the specified text.
   * 
   * @param by
   *          Selector to look for
   * @param attribute
   *          String value of the attribute to look at on the specified selector
   * @param text
   *          String value of the text to look for in the attribute
   * @return true if the attribute with the specified text value is found, else false
   */
  public boolean waitUntilAttributeTextContains(final By by, final String attribute,
      final String text) {
    return waitUntilAttributeTextContains(by, attribute, text, this.implicitWaitTimeout);
  }

  /**
   * Wait for an attribute of the specified selector to be present, and contain the specified text.
   * 
   * @param by
   *          Selector to look for
   * @param attribute
   *          String value of the attribute to look at on the specified selector
   * @param text
   *          String value of the text to look for in the attribute
   * @param timeOut
   *          int version of the timeout in seconds
   * @return true if the attribute with the specified text value is found, else false
   */
  public boolean waitUntilAttributeTextContains(final By by, final String attribute,
      final String text, int timeOut) {
    return waitUntilAttribute(by, attribute, text, timeOut, true);
  }

  /**
   * Wait for an attribute of the specified selector to be present, and contain the specified text.
   * 
   * @param by
   *          Selector to look for
   * @param attribute
   *          String value of the attribute to look at on the specified selector
   * @param text
   *          String value of the text to look for in the attribute
   * @param assertFound
   *          boolean - true to assert the selector/attribute is found. null is returned if false,
   *          and not found
   * @return WebElement of the selector/attribute found
   */
  public WebElement waitForAttributeTextContains(final By by, final String attribute,
      final String text, boolean assertFound) {
    return this.waitForAttributeTextContains(by, attribute, text, this.implicitWaitTimeout,
        assertFound);
  }

  /**
   * Wait for an attribute of the specified selector to be present, and contain the specified text.
   * 
   * @param by
   *          Selector to look for
   * @param attribute
   *          String value of the attribute to look at on the specified selector
   * @param text
   *          String value of the text to look for in the attribute
   * @param timeOut
   *          int version of the timeout in seconds
   * @param assertFound
   *          boolean - true to assert the selector/attribute is found. null is returned if false,
   *          and not found
   * @return WebElement of the selector/attribute found
   */
  public WebElement waitForAttributeTextContains(final By by, final String attribute,
      final String text, int timeOut, boolean assertFound) {
    if (this.waitUntilAttributeTextContains(by, attribute, text, timeOut)) {
      return this.browser.findElement(by);
    }
    if (assertFound) {
      Assert.fail(
          String.format("Selector [%s] couldn't be found." + System.getProperty("line.separator"),
              by.toString()));
    }

    // selector/element not found, and assert==false, so return null.
    return null;
  }

  /**
   * Scroll an element into view, and wait for it to be clickable.
   * 
   * @param by
   *          Selector to wait for and focus on
   * @return WebElement
   */
  public WebElement waitForClickableElementAndScrollIntoView(final By by) {
    return this.waitForClickableElementAndScrollIntoView(by, this.implicitWaitTimeout);
  }

  /**
   * Scroll an element into view, and wait for it to be clickable.
   * 
   * @param by
   *          Selector to wait for and focus on
   * @param timeOut
   *          int value of seconds to wait for before timing out
   * @return WebElement
   */
  public WebElement waitForClickableElementAndScrollIntoView(final By by, final int timeOut) {
    scrollIntoView(by);
    waitUntilPageLoad();
    return waitForClickableElement(by, timeOut);
  }

  /**
   * Scroll an element into view, and wait for it to be clickable.
   * 
   * @param by
   *          Selector to wait for and focus on
   * @return boolean - true if the element is found and clickable
   */
  public boolean waitUntilClickableElementAndScrollIntoView(final By by) {
    return this.waitUntilClickableElementAndScrollIntoView(by, this.implicitWaitTimeout);
  }

  /**
   * Scroll an element into view, and wait for it to be clickable.
   * 
   * @param by
   *          Selector to wait for and focus on
   * @param timeOut
   *          int value of seconds to wait for before timing out
   * @return boolean - true if the element is found and clickable
   */
  public boolean waitUntilClickableElementAndScrollIntoView(final By by, final int timeOut) {
    scrollIntoView(by);
    waitUntilPageLoad();
    return waitUntilClickableElement(by, timeOut);
  }

  /**
   * Wait for the specified selector to be clickable.
   * 
   * @param by
   *          Selector to wait for
   * @return boolean - true if found and clickable, else false
   */
  public boolean waitUntilClickableElement(final By by) {
    return this.waitUntilClickableElement(by, this.implicitWaitTimeout);
  }

  /**
   * Wait for the specified selector to be clickable.
   * 
   * @param by
   *          Selector to wait for
   * @param timeOut
   *          integer of seconds to wait before timing out
   * @return boolean - true if found and clickable, else false
   */
  public boolean waitUntilClickableElement(final By by, int timeOut) {
    try {
      if (waitForClickableElement(by, timeOut) == null) {
        return false;
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Wait for the element specified by the provided selector to be clickable.
   * 
   * @param by
   *          Selector to wait for to be clickable
   * @return WebElement that is located, or null if none is found
   */
  public WebElement waitForClickableElement(final By by) {
    return this.waitForClickableElement(by, this.implicitWaitTimeout);
  }

  /**
   * Wait for the element specified by the provided selector to be clickable.
   * 
   * @param by
   *          Selector to wait for to be clickable
   * @param timeOutInSeconds
   *          int value of the number of seconds to wait before timing out
   * @return WebElement that is located, or null if none is found
   */
  public WebElement waitForClickableElement(final By by, int timeOutInSeconds) {
    this.setImplicitWait(timeOutInSeconds);

    final WebElement element = browser.findElement(by);
    try {
      new WebDriverWait(browser, timeOutInSeconds) {
      }.until(new ExpectedCondition<WebElement>() {
        public WebElement apply(WebDriver browserObject) {
          if (isClickable(browserObject, by)) {
            return element;
          }
          return null;
        }
      });
      this.resetImplicitWait();
      if (isClickable(browser, by)) {
        return element;
      }
      return null;
    } catch (Exception e) {
      this.printSelectorNotFoundError(by.toString(), e);
    }

    this.resetImplicitWait();
    return null;
  }

  /**
   * Fluently wait for an element to be removed.
   * 
   * @param by
   *          Selector to wait to be removed
   * @return boolean - true if the selector is no longer present, else false
   */
  public boolean fluentWaitInvisible(final By by) {
    return this.fluentWaitInvisible(by, CLEARTIME);
  }

  /**
   * Fluently wait for an element to be removed.
   * 
   * @param by
   *          Selector to wait to be removed
   * @param clearTime
   *          int value of how long to wait for the selector to be removed
   * @return boolean - true if the selector is no longer present, else false
   */
  public boolean fluentWaitInvisible(final By by, int clearTime) {
    boolean isNotVisible = false;

    try {
      browser.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

      new WebDriverWait(browser, clearTime) {
      }.until(new ExpectedCondition<Boolean>() {
        public Boolean apply(WebDriver browserObject) {
          return !isDisplayed(browserObject, by);
        }
      });
      isNotVisible = !isDisplayed(browser, by);
      this.setImplicitWait(implicitWaitTimeout);

      return isNotVisible;
    } catch (Exception e) {
      System.out
          .println("Wait for certain text to be present failed for selector: " + by.toString());
      e.printStackTrace();
    }
    return false;
  }

  /**
   * Wait for the page to load by waiting for the source to stop changing for at least a second.
   * Asserts the page loads
   */
  public void waitForPageLoad() {
    if (!this.waitUntilPageLoad()) {
      Assert.fail("Page load took longer than timeout configuration.");
    }
  }

  /**
   * Wait for the page to load by waiting for the source to stop changing for at least a second.
   * 
   * @return true if it's successfully loaded, false if timed out and not finished loading
   */
  public boolean waitUntilPageLoad() {
    String before = this.browser.getPageSource();
    String after = "";
    int counter = this.implicitWaitTimeout;

    do {
      try {
        counter--;
        before = this.browser.getPageSource();
        Thread.sleep(ONE_THOUSAND);
        after = this.browser.getPageSource();
      } catch (InterruptedException e) {
        System.out.println("Failed to wait for the page to laod");
        e.printStackTrace();
      }
    } while (!before.equals(after) && counter > 0);

    return before.equals(after);
  }

  /**
   * Wait for an attribute of the specified selector to be present, and contain the specified text.
   * 
   * @param by
   *          Selector to look for
   * @param attribute
   *          String value of the attribute to look at on the specified selector
   * @param text
   *          String value of the text to look for in the attribute
   * @param timeOut
   *          int version of the timeout in seconds
   * @param contains
   *          boolean true if checking if contains, false if exact match
   * @return true if the attribute with the specified text value is found, else false
   */
  private boolean waitUntilAttribute(final By by, final String attribute, final String text,
      int timeOut, final boolean contains) {
    boolean isPresent = false;
    try {
      this.setImplicitWait(timeOut);

      new WebDriverWait(browser, timeOut) {
      }.until(new ExpectedCondition<Boolean>() {
        public Boolean apply(WebDriver browserObject) {
          return attributeMatches(browserObject, by, attribute, text, contains);
        }
      });
      isPresent = attributeMatches(browser, by, attribute, text, contains);
      this.resetImplicitWait();

      return isPresent;
    } catch (Exception e) {
      this.printSelectorNotFoundError(by.toString(), e);
    }

    this.resetImplicitWait();
    return false;
  }

  /**
   * Scroll the webpage so the selector is visible.
   * 
   * @param by
   *          Selector to make visible
   */
  private void scrollIntoView(By by) {
    WebElement element = waitForElement(by, true);
    int counter = 0;
    final int max = 5;

    try {
      ((JavascriptExecutor) this.browser).executeScript("arguments[0].scrollIntoView(true);",
          element);
    } catch (Exception e) {
      System.out.println("Failed to do JavaScript scroll into view..." + e.getStackTrace()
          + System.getProperty("line.separator"));
    }

    Coordinates coord = ((Locatable) element).getCoordinates();
    while (coord.inViewPort().getY() < HEADER_SIZE && counter < max) {
      waitForElement(BODY_BY, 0).sendKeys(Keys.ARROW_UP);
      counter++;
    }
  }

  /**
   * Fluent wait for an element to be removed.
   * 
   * @param element
   *          WebElement to be removed
   * @return true if removed
   */
  private boolean fluentWaitStale(WebElement element) {
    try {
      return new WebDriverWait(this.browser, this.implicitWaitTimeout)
          .pollingEvery(fluentRetryTime, TimeUnit.MILLISECONDS)
          .until(ExpectedConditions.stalenessOf(element));
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Check if the specified selector is displayed.
   * 
   * @param browser
   *          Web Driver
   * @param by
   *          Selector to look for
   * @return boolean - true if selector is found, else false
   */
  private static boolean isDisplayed(WebDriver browser, By by) {
    try {
      WebElement item = browser.findElement(by);

      if (item != null) {
        return item.isDisplayed();
      }

      return false;
    } catch (Throwable e) {
      return false;
    }
  }

  /**
   * Check if the element is clickable.
   * 
   * @param browser
   *          WebDriver
   * @param by
   *          Selector to check
   * @return boolean - true if clickable, else false
   */
  private static boolean isClickable(WebDriver browser, By by) {
    try {
      WebElement element = browser.findElement(by);
      return element.isDisplayed() && element.isEnabled();
    } catch (NullPointerException e) {
      return false;
    }
  }

  /**
   * Check the text value of an attribute.
   * 
   * @param browser
   *          - web driver
   * @param by
   *          Selector to get the attribute from
   * @param attribute
   *          String value of the attribute to examine
   * @param text
   *          String to check against the attribute
   * @param contains
   *          boolean - true to see if the string is contained, false if exact match
   * @return boolean - true if they match, else false
   */
  private static boolean attributeMatches(WebDriver browser, By by, String attribute, String text,
      boolean contains) {
    try {
      if (contains) {
        return browser.findElement(by).getAttribute(attribute).contains(text);
      } else {
        return browser.findElement(by).getAttribute(attribute).equals(text);
      }
    } catch (NullPointerException e) {
      return false;
    }
  }

  /**
   * Print error if/when selector isn't found.
   * 
   * @param output
   *          String to print to the console
   * @param e
   *          Exception to print to the console
   */
  private void printSelectorNotFoundError(String output, Exception e) {
    System.out.println("Wait for element [" + output + "] failed because: " + e.getMessage());
  }

  /**
   * Checks if the text of the elements are equal TODO - should have fuzzy validation incorporated.
   * 
   * @param by
   *          Selector to examine
   * @param text
   *          Text that is being compared to the selector
   * @return boolean - true if equal
   */
  private boolean doesTextMatch(By by, String text) {
    try {
      WebElement element = this.waitForElement(by);

      // TODO we probably should include an option to use fuzzy validation here, so it isn't a
      // hard-compare.
      if (element != null && element.getText().equals(text)) {
        return true;
      }
    } catch (Exception e) {
      return false;
    }
    return false;
  }

  /**
   * Set the implicit wait time.
   * 
   * @param wait
   *          int value of seconds to wait
   */
  private void setImplicitWait(int wait) {
    // implicit wait must first be set to zero to nullify it
    browser.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

    browser.manage().timeouts().implicitlyWait(wait, TimeUnit.SECONDS);
    browser.manage().timeouts().setScriptTimeout(wait, TimeUnit.SECONDS);
  }

  /**
   * Reset the implicit wait time.
   */
  private void resetImplicitWait() {
    setImplicitWait(this.implicitWaitTimeout);
  }

}
