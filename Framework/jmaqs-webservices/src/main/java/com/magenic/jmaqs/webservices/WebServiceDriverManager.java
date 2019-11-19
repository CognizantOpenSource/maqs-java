/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices;

import com.magenic.jmaqs.base.BaseTestObject;
import com.magenic.jmaqs.base.DriverManager;

import java.net.URISyntaxException;
import java.util.function.Supplier;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * Web Service Driver Manager Class
 */
public class WebServiceDriverManager extends DriverManager {
  /**
   * Web Service Driver variable
   */
  private WebServiceDriver webServiceDriver;

  /**
   * Instantiates a new Web Service Driver Manager.
   *
   * @param getDriverFunction Function that specifies how to get the driver.
   * @param baseTestObject    The Base Test Object.
   */
  public WebServiceDriverManager(Supplier<CloseableHttpClient> getDriverFunction,
      BaseTestObject baseTestObject) {
    super(getDriverFunction, baseTestObject);
    this.webServiceDriver = new WebServiceDriver(getDriverFunction.get());
  }

  /**
   * Instantiates a new Web Service Driver Manager.
   *
   * @param driver            Web Service Driver
   * @param baseTestObject    The Base Test Object.
   */
  public WebServiceDriverManager(WebServiceDriver driver, BaseTestObject baseTestObject) {
    super(() -> driver, baseTestObject);
    this.webServiceDriver = driver;
  }

  /**
   * Get the Web Service Driver.
   *
   * @return  The Web Service Driver.
   */
  public WebServiceDriver getWebServiceDriver() throws URISyntaxException {
    // Create default Web Service Driver and instantiate Base HTTP Client.
    if (this.webServiceDriver == null) {
      this.webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
      this.webServiceDriver.setHttpClient(this.webServiceDriver.getHttpClient(MediaType.APP_JSON.getMediaTypeString()));
    }

    return this.webServiceDriver;
  }

  /**
   * Overrides the Web Service Driver.
   *
   * @param driver  Web Service Driver
   */
  public void overrideDriver(WebServiceDriver driver) {
    this.webServiceDriver = driver;
  }

  /**
   * Close Method
   */
  public void close() {
    this.setBaseDriver(null);
  }
}
