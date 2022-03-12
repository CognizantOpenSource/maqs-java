/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.base;

import com.cognizantsoftvision.maqs.utilities.logging.Logger;

/**
 * The Driver Manager interface class.
 * @param <T> the type of driver being used
 */
public interface IDriverManager<T> extends AutoCloseable {

   /**
   * Gets base driver.
   */
   Object getBaseDriver();

   /**
   * Sets base driver.
   */
   void setBaseDriver(T baseDriver);

   /**
   * Is driver initialized boolean.
   */
   boolean isDriverInitialized();

   /**
   * Gets logger.
   */
   Logger getLogger();


   /**
   * Gets test object.
   */
   BaseTestObject getTestObject();
}
