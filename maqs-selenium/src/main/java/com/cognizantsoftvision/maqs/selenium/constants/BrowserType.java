/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium.constants;

/**
 * Known browser types.
 */
public enum BrowserType {
  /**
   * None Web Browser.
   */
  NONE,

  /**
   * Chrome web browser.
   */
  CHROME,

  /**
   * Edge web browser.
   */
  EDGE,

  /**
   * Firefox web browser.
   */
  FIREFOX,

  /**
   * Chrome web browser - run headless.
   */
  HEADLESS_CHROME,

  /**
   * IE web browser.
   */
  IE,

  /**
   * Remote web browser - Used when executing on Grid or cloud based provides like Sauce Labs.
   */
  REMOTE
}