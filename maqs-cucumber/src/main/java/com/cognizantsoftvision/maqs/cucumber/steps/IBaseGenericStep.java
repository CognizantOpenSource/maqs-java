/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.cucumber.steps;

import com.cognizantsoftvision.maqs.base.BaseTestObject;
import com.cognizantsoftvision.maqs.utilities.logging.ILogger;

public interface IBaseGenericStep {

  /**
   * Get the logger.
   *
   * @return The logger
   */
  ILogger getLogger();

  /**
   * Get the generic test object.
   *
   * @return The generic test object
   */
  BaseTestObject getTestObject();
}
