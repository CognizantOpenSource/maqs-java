/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

import com.cognizantsoftvision.maqs.base.ITestObject;
import com.microsoft.playwright.Page;
import java.util.function.Supplier;

public interface IPlaywrightTestObject extends ITestObject {

  PageDriver getPageDriver();

  /// <summary>
  /// Gets the Playwright page manager
  /// </summary>
  PageDriverManager getPageManager();


  /// <summary>
  /// Override the function for creating a Playwright page
  /// </summary>
  /// <param name="getPage">Function for creating a page</param>
  void overridePageDriver(Supplier<Page> getPage);

  /// <summary>
  /// Override the Playwright page
  /// </summary>
  /// <param name="page">New page</param>
  void overridePageDriver(Page page);

  /// <summary>
  /// Override the Playwright page driver
  /// </summary>
  /// <param name="pageDriver">New page driver</param>
  void overridePageDriver(PageDriver pageDriver);
}
