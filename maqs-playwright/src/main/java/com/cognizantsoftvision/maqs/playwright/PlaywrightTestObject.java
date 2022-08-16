/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.playwright;

import com.cognizantsoftvision.maqs.base.BaseTestObject;
import com.cognizantsoftvision.maqs.base.exceptions.MAQSRuntimeException;
import com.cognizantsoftvision.maqs.utilities.logging.ILogger;
import com.cognizantsoftvision.maqs.utilities.logging.MessageType;
import java.util.function.Supplier;

/**
 * The Playwright Test Object class.
 */
public class PlaywrightTestObject extends BaseTestObject implements IPlaywrightTestObject {

  /**
   * Initializes a new instance of the PlaywrightTestObject class.
   *
   * @param getDriverSupplier Function for getting a Playwright page
   * @param logger The test's logger
   * @param fullyQualifiedTestName The test's fully qualified test name
   */
  public PlaywrightTestObject(Supplier<PageDriver> getDriverSupplier, ILogger logger, String fullyQualifiedTestName) {
    super(logger, fullyQualifiedTestName);
    this.getManagerStore().put((PlaywrightDriverManager.class).getCanonicalName(),
        new PlaywrightDriverManager(getDriverSupplier, this));
  }

  /**
   * Initializes a new instance of the PlaywrightTestObject class.
   * @param pageDriver The test's Playwright page
   * @param logger The test's logger
   * @param fullyQualifiedTestName The test's fully qualified test name
   */
  public PlaywrightTestObject(PageDriver pageDriver, ILogger logger, String fullyQualifiedTestName) {
    this(() -> pageDriver,  logger,  fullyQualifiedTestName);
  }

  /**
   * {@inheritDoc}
   */
  public PageDriver getPageDriver() {
    return this.getPageManager().getPageDriver();
  }

  /**
   * {@inheritDoc}
   */
  public void setPageDriver(PageDriver driver) {
    String name = PlaywrightDriverManager.class.getCanonicalName();
    if (this.getManagerStore().containsKey(name)) {
      try {
        this.getManagerStore().get(name).close();
        this.getManagerStore().remove(name);
      } catch (Exception e) {
        getLogger().logMessage(MessageType.ERROR, "Failed to remove DriverManager: %s", e.getMessage());
        throw new MAQSRuntimeException(e.getMessage(), e);
      }
    }
    this.getManagerStore().put(name, new PlaywrightDriverManager((() -> driver), this));
  }

  /**
   * {@inheritDoc}
   */
  public void setPageDriver(Supplier<PageDriver> pageDriverSupplier) {
    this.getManagerStore().put(
        PlaywrightDriverManager.class.getCanonicalName(), new PlaywrightDriverManager(pageDriverSupplier, this));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void overridePageDriver(PageDriver pageDriver) {
    this.getPageManager().overrideDriver(pageDriver);
  }

  /**
   * {@inheritDoc}
   */
  public PlaywrightDriverManager getPageManager() {
    return (PlaywrightDriverManager) this.getManagerStore().get(PlaywrightDriverManager.class.getCanonicalName());
  }
}
