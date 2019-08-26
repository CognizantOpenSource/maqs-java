/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.selenium;

/**
 * Known browser types.
 */
public enum BrowserType {
  /**
   * Chrome web browser.
   */
  Chrome,

  /**
   * Edge web browser.
   */
  Edge,

  /**
   * Firefox web browser.
   */
  Firefox,

  /**
   * Chrome web browser - run headless.
   */
  HeadlessChrome,

  /**
   * IE web browser.
   */
  IE,

  /**
   * Remote web browser - Used when executing on Grid or cloud based provides like Sauce Labs.
   */
  Remote
}