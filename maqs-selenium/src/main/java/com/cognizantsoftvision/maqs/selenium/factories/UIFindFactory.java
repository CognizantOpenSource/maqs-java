/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium.factories;

import com.cognizantsoftvision.maqs.selenium.UIFind;
import org.openqa.selenium.SearchContext;

/**
 * The UI Find Factory class.
 * Factory class for creating UIFind objects for the test.
 */
public class UIFindFactory {

  // private constructor so class can't be instantiated
  private UIFindFactory() {
  }

  /**
   * Initializes a new instance of {@link UIFind}.
   *
   * @param searchItem The search item that is used for
   *                   finding elements
   * @return The UIFind instance
   */
  public static UIFind getFind(SearchContext searchItem) {
    return new UIFind(searchItem);
  }
}