/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.webservices;

import com.cognizantsoftvision.maqs.base.BaseTestObject;
import com.cognizantsoftvision.maqs.base.DriverManager;
<<<<<<<< HEAD:maqs-webservices/src/main/java/com/cognizantsoftvision/maqs/webservices/WebServiceDriverManager.java
import java.net.http.HttpClient;
========
>>>>>>>> main:maqs-webservices-jdk8/src/main/java/com/cognizantsoftvision/maqs/webservices/WebServiceDriverManager.java
import java.util.function.Supplier;

/**
 * Web Service Driver Manager Class.
 */
public class WebServiceDriverManager extends DriverManager<HttpClient> {
  /**
   * Web Service Driver variable.
   */
  private WebServiceDriver webServiceDriver;

  /**
   * Instantiates a new Web Service Driver Manager.
   *
   * @param getDriverFunction Function that specifies how to get the driver.
   * @param baseTestObject    The Base Test Object.
   */
  public WebServiceDriverManager(Supplier<HttpClient> getDriverFunction, BaseTestObject baseTestObject) {
    super(getDriverFunction, baseTestObject);
  }

  /**
   * Instantiates a new Web Service Driver Manager.
   *
   * @param driver         Web Service Driver
   * @param baseTestObject The Base Test Object.
   */
  public WebServiceDriverManager(WebServiceDriver driver, BaseTestObject baseTestObject) {
    super(driver::getHttpClient, baseTestObject);
    this.webServiceDriver = driver;
  }

  /**
   * Get the Web Service Driver.
   *
   * @return The Web Service Driver.
   */
  public WebServiceDriver getWebServiceDriver() {
    // Create default Web Service Driver if null.
    if (this.webServiceDriver == null) {
      this.webServiceDriver = new WebServiceDriver(getBase());
    }

    return this.webServiceDriver;
  }

  /**
   * Overrides the Web Service Driver.
   *
   * @param driver Web Service Driver
   */
  public void overrideDriver(WebServiceDriver driver) {
    this.webServiceDriver = driver;
  }

  /**
   * Close Method sets Base Driver to Null.
   */
  public void close() {
    this.setBaseDriver(null);
  }
}
