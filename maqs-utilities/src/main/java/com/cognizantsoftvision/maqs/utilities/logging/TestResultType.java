/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.utilities.logging;

/**
 * The type of result.
 */
public enum TestResultType {
  /**
   * The test passed.
   */
  PASS,

  /**
   * The test failed.
   */
  FAIL,

  /**
   * The test was inconclusive.
   */
  INCONCLUSIVE,

  /**
   * The test was skipped.
   */
  SKIP,

  /**
   * The test had an unexpected result.
   */
  OTHER,
}
