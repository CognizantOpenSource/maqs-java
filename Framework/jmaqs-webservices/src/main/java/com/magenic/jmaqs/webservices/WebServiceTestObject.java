/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices;

import com.magenic.jmaqs.base.BaseTestObject;
import com.magenic.jmaqs.utilities.logging.Logger;

/**
 * Web service test object class.
 */
public class WebServiceTestObject extends BaseTestObject {

  /**
   * The Web Service Driver.
   */
  private WebServiceDriver webServiceDriver;
  
  /**
   * Initializes a new instance of the WebServiceTestObject.
   * @param newWebServiceDriver The web service driver.
   * @param logger The Logger Object.
   * @param fullyQualifiedTestName The fully qualified test name.
   */
  public WebServiceTestObject(WebServiceDriver newWebServiceDriver,
                    Logger logger, String fullyQualifiedTestName) {
    super(logger, fullyQualifiedTestName);
    this.webServiceDriver = newWebServiceDriver;
  }
  
  /**
   * Get the web service wrapper for the WebServiceTestObject.
   * @return A web service driver
   */
  public WebServiceDriver getWebServiceDriver() {
    return this.webServiceDriver;
  }

  /**
   * Set the web service wrapper for the WebServiceTestObject.
   * @param newWebServiceDriver The web service driver
   */
  public void setWebServiceDriver(WebServiceDriver newWebServiceDriver) {
    this.webServiceDriver = newWebServiceDriver;
  }

}
