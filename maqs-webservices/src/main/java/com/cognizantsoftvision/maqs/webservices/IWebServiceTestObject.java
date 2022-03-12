/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.webservices;

import com.cognizantsoftvision.maqs.base.ITestObject;
import java.net.http.HttpClient;
import java.util.function.Supplier;

/**
 * The Web Service Test Object interface class.
 */
public interface IWebServiceTestObject extends ITestObject {

  /**
   * Get the web service wrapper for the WebServiceTestObject.
   *
   * @return A web service driver
   */
  WebServiceDriver getWebServiceDriver();

  /**
   * Gets the Web Service driver managers.
   *
   * @return the web service manager.
   */
  WebServiceDriverManager getWebServiceDriverManager();

  /**
   * overrides the web service driver using the WebServiceDriver.
   *
   * @param driver the Web Service Driver to be committed.
   */
  void setWebServiceDriver(WebServiceDriver driver);

  /**
   * overrides the web service driver using a closable Http Client.
   *
   * @param httpClient the Http Client to be set.
   */
  void setWebServiceDriver(HttpClient httpClient);

  /**
   * overrides the Web Service driver using a supplier.
   *
   * @param webServiceSupplier the web service driver supplier.
   */
  void setWebServiceDriver(Supplier<HttpClient> webServiceSupplier);
}
