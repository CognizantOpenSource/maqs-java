/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices.baseWebServiceTest;

import com.magenic.jmaqs.utilities.logging.Logger;
import com.magenic.jmaqs.utilities.logging.LoggingEnabled;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

// TODO extend from TestObject 
public abstract class WebServiceTestObject {

  protected ConcurrentHashMap<String, Logger> loggers;
  protected ConcurrentHashMap<String, ArrayList<String>> loggedExceptions;
  protected LoggingEnabled loggingEnabledSetting;

  public HttpClientWrapper webServiceWrapper;

  /**
   * Web service test object constructor
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
