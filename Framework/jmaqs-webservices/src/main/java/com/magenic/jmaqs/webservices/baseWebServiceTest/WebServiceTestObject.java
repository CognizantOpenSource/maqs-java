/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices.baseWebServiceTest;

import com.magenic.jmaqs.utilities.logging.Logger;
import com.magenic.jmaqs.utilities.logging.LoggingEnabled;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

// TODO extend from TestObject 

/**
 * Web service test object class.
 */
public abstract class WebServiceTestObject {

  /**
   * Thread safe collection of loggers.
   */
  protected ConcurrentHashMap<String, Logger> loggers;
  
  /**
   * Thread safe collection of logged exceptions.
   */
  protected ConcurrentHashMap<String, ArrayList<String>> loggedExceptions;
  
  /**
   * The logging level.
   */
  protected LoggingEnabled loggingEnabledSetting;

  /**
   * The HTTP client wrapper.
   */
  public HttpClientWrapper webServiceWrapper;

  /**
   * Web service test object constructor.
   * 
   * @param webServiceWrapper
   *          The web service wrapper
   * @param loggers
   *          The logger
   */
  public WebServiceTestObject(HttpClientWrapper webServiceWrapper,
      ConcurrentHashMap<String, Logger> loggers) {
    this.webServiceWrapper = webServiceWrapper;
    this.loggers = loggers;
    loggedExceptions = new ConcurrentHashMap<String, ArrayList<String>>();
  }

}
