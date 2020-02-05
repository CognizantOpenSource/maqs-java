/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.utilities.helper.TestCategories;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The UI Find functionality unit test.
 */
public class UIFindUnitTest extends BaseSeleniumTest  {
  /**
   * Url for the site.
   */
  private static String siteUrl = SeleniumConfig.getWebSiteBase();

  /**
   * Automation site url.
   */
  private static String siteAutomationUrl = siteUrl + "Automation/";

  /**
   * the flower table title.
   */
  private By flowerTableTitle = By.cssSelector("#FlowerTable > caption > strong");

  /**
   * Test to find an element.
   */
  @Test(groups = TestCategories.Selenium)
  public void findElement() {
    UIFind find = new UIFind((SearchContext) flowerTableTitle);
    WebElement element = find.findElement(flowerTableTitle);
    Assert.assertNotNull(element);
  }

  /**
   * Test to find an element by text.
   */
  @Test(groups = TestCategories.Selenium)
  public void findElementWithText() {
    UIFind find = new UIFind((SearchContext) flowerTableTitle);
    WebElement element = find.findElementWithText(flowerTableTitle, "Flower Table");
    Assert.assertEquals(element.getText(), "Flower Table");
  }

  /**
   * Test to find an index of an element with text.
   */
  @Test(groups = TestCategories.Selenium)
  public void findIndexOfElementWithText() {
    UIFind find = new UIFind((SearchContext) flowerTableTitle);
    int element = find.findIndexOfElementWithText(flowerTableTitle, "Flower Table");
    Assert.assertEquals(element, 5);

    List<WebElement> elements = find.findElements(flowerTableTitle, false);
    int index = find.findIndexOfElementWithText(elements, "Flower Table");
    Assert.assertEquals(index, 7);
  }

  /**
   * Test to find multiple elements.
   */
  @Test(groups = TestCategories.Selenium)
  public void findElements() {
    UIFind find = new UIFind((SearchContext) flowerTableTitle);
    List<WebElement> elements = find.findElements(flowerTableTitle, false);
    Assert.assertEquals(elements.size(), 5);
  }
}
