/*
 * Copyright 2020 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

import com.magenic.jmaqs.selenium.unittestpagemodel.PageElementsPageModel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BaseSeleniumPageModelUnitTest extends BaseSeleniumTest {

  @Test
  public void testGetLogger() {
    PageElementsPageModel pageElementsPageModel = new PageElementsPageModel(getTestObject());
    Assert.assertNotNull(pageElementsPageModel.getLogger());
  }

  @Test
  public void testGetTestObject() {
    PageElementsPageModel pageElementsPageModel = new PageElementsPageModel(getTestObject());
    Assert.assertNotNull(pageElementsPageModel.getTestObject());
  }

  @Test
  public void testGetWebDriver() {
    PageElementsPageModel pageElementsPageModel = new PageElementsPageModel(getTestObject());
    Assert.assertNotNull(pageElementsPageModel.getWebDriver());
  }

  @Test
  public void testSetWebDriver() {
    throw new UnsupportedOperationException("Test not implemented yet");
  }

  @Test
  public void testIsPageLoaded() {
    throw new UnsupportedOperationException("Test not implemented yet");
  }

  @Test
  public void testGetLazyElement() {
    throw new UnsupportedOperationException("Test not implemented yet");
  }

  @Test
  public void testGetLazyElement1() {
    throw new UnsupportedOperationException("Test not implemented yet");
  }

  @Test
  public void testGetLazyElement2() {
    throw new UnsupportedOperationException("Test not implemented yet");
  }

  @Test
  public void testGetLazyElement3() {
    throw new UnsupportedOperationException("Test not implemented yet");
  }
}