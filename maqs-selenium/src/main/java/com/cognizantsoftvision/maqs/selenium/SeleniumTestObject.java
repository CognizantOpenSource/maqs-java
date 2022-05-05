/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.selenium;

import com.cognizantsoftvision.maqs.base.BaseTestObject;
import com.cognizantsoftvision.maqs.base.exceptions.MAQSRuntimeException;
import com.cognizantsoftvision.maqs.utilities.logging.ILogger;
import com.cognizantsoftvision.maqs.utilities.logging.MessageType;
import java.util.function.Supplier;
import org.openqa.selenium.WebDriver;

/**
 * The SeleniumTestObject Class.
 */
public class SeleniumTestObject extends BaseTestObject implements ISeleniumTestObject {

  /**
   * Instantiates a new Selenium test object.
   *
   * @param getDriverSupplier      the get driver supplier
   * @param logger                 the logger
   * @param fullyQualifiedTestName the fully qualified test name
   */
  public SeleniumTestObject(Supplier<WebDriver> getDriverSupplier, ILogger logger, String fullyQualifiedTestName) {
    super(logger, fullyQualifiedTestName);
    this.getManagerStore()
        .put((SeleniumDriverManager.class).getCanonicalName(), new SeleniumDriverManager(getDriverSupplier, this));
  }

  /**
   * Instantiates a new Selenium test object.
   *
   * @param webDriver              the web driver
   * @param logger                 the logger
   * @param fullyQualifiedTestName the fully qualified test name
   */
  public SeleniumTestObject(WebDriver webDriver, ILogger logger, String fullyQualifiedTestName) {
    this(() -> webDriver, logger, fullyQualifiedTestName);
  }

  /**
   * {@inheritDoc}
   */
  public WebDriver getWebDriver() {
    return this.getWebManager().getWebDriver();
  }

  /**
   * {@inheritDoc}
   */
  public void setWebDriver(WebDriver driver) {
    String name = SeleniumDriverManager.class.getCanonicalName();
    if (this.getManagerStore().containsKey(name)) {
      try {
        this.getManagerStore().get(name).close();
        this.getManagerStore().remove(name);
      } catch (Exception e) {
        getLogger().logMessage(MessageType.ERROR, "Failed to remove DriverManager: %s", e.getMessage());
        throw new MAQSRuntimeException(e.getMessage(), e);
      }
    }

    this.getManagerStore().put(name, new SeleniumDriverManager((() -> driver), this));
  }

  /**
   * {@inheritDoc}
   */
  public void setWebDriver(Supplier<WebDriver> webDriverSupplier) {
    this.getManagerStore()
        .put(SeleniumDriverManager.class.getCanonicalName(), new SeleniumDriverManager(webDriverSupplier, this));
  }

  /**
   * {@inheritDoc}
   */
  public SeleniumDriverManager getWebManager() {
    return (SeleniumDriverManager) this.getManagerStore().get(SeleniumDriverManager.class.getCanonicalName());
  }
}
