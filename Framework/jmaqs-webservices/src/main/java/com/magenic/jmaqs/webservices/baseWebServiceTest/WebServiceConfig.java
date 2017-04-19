/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices.baseWebServiceTest;

import com.magenic.jmaqs.utilities.helper.Config;

/**
 * Web service specific configuration settings.
 */
public final class WebServiceConfig {
  /**
   * Grabs the URI for the Web Service.
   * 
   * @return A String containing the URI for the WebService to test
   */
  public static String getWebServiceUri() {
    return Config.getValue("WebServiceURI");
  }

  /**
   * Gets the expected time out in seconds.
   * 
   * @return an Integer containing the Time Out value for the web service test, or -1 if none are
   *         configured
   */
  public static int getWebServiceTimeOut() {
    return Integer.parseInt(Config.getValue("WebServiceTimeOut", "-1"));
  }
}
