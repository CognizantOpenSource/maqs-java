/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.cucumber.steps;

import com.cognizantsoftvision.maqs.cucumber.ScenarioContext;
import com.cognizantsoftvision.maqs.selenium.BaseSeleniumTest;
import com.cognizantsoftvision.maqs.selenium.SeleniumTestObject;
import org.openqa.selenium.WebDriver;

/**
 * Base Selenium cucumber step.
 */
public class BaseSeleniumStep extends BaseGenericStep {

  /**
   * Get the web driver.
   *
   * @return The web driver
   */
  public WebDriver getDriver() {
    return getTestObject().getWebDriver();
  }

  /**
   * Get the Selenium test object.
   *
   * @return Selenium test object
   */
  @Override
  public SeleniumTestObject getTestObject() {
    return (SeleniumTestObject) ScenarioContext.get(
        ScenarioContext.MAQS_HOLDER, BaseSeleniumTest.class).getTestObject();
  }
}



