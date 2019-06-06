/* 
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices.baseWebServiceTest;

import com.magenic.jmaqs.utilities.helper.Config;
import com.magenic.jmaqs.utilities.helper.ConfigSection;

/**
 * Web service specific configuration settings.
 */
public final class WebServiceConfig {
  /**
   * The web service configuration section.
   */
  public static final ConfigSection WEBSERVICE_SECTION = ConfigSection.WebServiceMaqs;

  /**
   * Grabs the URI for the Web Service.
   * 
   * @return A String containing the URI for the WebService to test
   */
  public static String getWebServiceUri() {
    return Config.getValueForSection(WEBSERVICE_SECTION, "WebServiceUri");
  }

  /**
   * Gets the expected time out in seconds.
   * 
   * @return an Integer containing the Time Out value for the web service test, or -1 if none are
   *         configured
   */
  public static int getWebServiceTimeOut() {
    return Integer.parseInt(Config.getValueForSection(WEBSERVICE_SECTION,"WebServiceTimeOut", "-1"));
  }
}
