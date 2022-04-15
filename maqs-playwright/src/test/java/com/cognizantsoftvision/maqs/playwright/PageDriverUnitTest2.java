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
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * The Page Driver unit tests.
 */
public class PageDriverUnitTest2 extends BasePlaywrightTest {

  /**
   * the selector string class.
   */
  ElementPageModel elementPageModel = new ElementPageModel();

  /**
   * the page model class.
   */
  PageModel pageModel;

  /**
   * Sets up the page model.
   */
  @BeforeTest
  public void setUp() {
    pageModel = new PageModel(this.getTestObject());
  }

  /**
   * Setup test and make sure we are on the correct test page.
   */
  @Test(groups = TestCategories.PLAYWRIGHT)
  public void createPlaywrightPageModel() {
    this.getPageDriver().navigateTo(PageModel.getUrl());
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
}
