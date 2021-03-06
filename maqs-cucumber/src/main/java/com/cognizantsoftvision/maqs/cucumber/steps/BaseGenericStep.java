/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.cucumber.steps;

import com.cognizantsoftvision.maqs.base.BaseTest;
import com.cognizantsoftvision.maqs.base.BaseTestObject;
import com.cognizantsoftvision.maqs.cucumber.ScenarioContext;
import com.cognizantsoftvision.maqs.utilities.logging.ILogger;

/**
 * Base generic cucumber step.
 */
public abstract class BaseGenericStep implements IBaseGenericStep {

  /**
   * Get the logger.
   *
   * @return The logger
   */
  public ILogger getLogger() {
    return ScenarioContext.get(ScenarioContext.MAQS_HOLDER, BaseTest.class).getLogger();
  }

  /**
   * Get the generic test object.
   *
   * @return The generic test object
   */
  public BaseTestObject getTestObject() {
    return (BaseTestObject) ScenarioContext.get(ScenarioContext.MAQS_HOLDER, BaseTest.class).getTestObject();
  }
}
