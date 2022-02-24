/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.webservices;

import com.cognizantsoftvision.maqs.base.BaseTestObject;
import com.cognizantsoftvision.maqs.utilities.logging.Logger;
<<<<<<<< HEAD:maqs-webservices/src/main/java/com/cognizantsoftvision/maqs/webservices/WebServiceTestObject.java
import java.net.http.HttpClient;
========
>>>>>>>> main:maqs-webservices-jdk8/src/main/java/com/cognizantsoftvision/maqs/webservices/WebServiceTestObject.java
import java.util.function.Supplier;

/**
 * Web service test object class.
 */
public class WebServiceTestObject extends BaseTestObject {
  /**
   * Instantiates a new Web Service Test Object with a supplier.
   * 
   * @param getDriverSupplier      the get driver supplier.
   * @param logger                 the Logger.
   * @param fullyQualifiedTestName the fully qualified test name.
   */
  public WebServiceTestObject(Supplier<HttpClient> getDriverSupplier,
      Logger logger, String fullyQualifiedTestName) {
    super(logger, fullyQualifiedTestName);
    this.getManagerStore().put((WebServiceDriverManager.class).getCanonicalName(),
        new WebServiceDriverManager(getDriverSupplier, this));
  }

  /**
   * Initializes a new instance of the WebServiceTestObject.
   * 
   * @param driver                 The web service driver.
   * @param logger                 The Logger Object.
   * @param fullyQualifiedTestName The fully qualified test name.
   */
  public WebServiceTestObject(WebServiceDriver driver,
      Logger logger, String fullyQualifiedTestName) {
    super(logger, fullyQualifiedTestName);
    this.getManagerStore().put((WebServiceDriverManager.class).getCanonicalName(),
        new WebServiceDriverManager(driver, this));
  }

  /**
   * Get the web service wrapper for the WebServiceTestObject.
   * 
   * @return A web service driver
   */
  public WebServiceDriver getWebServiceDriver() {
    return this.getWebServiceDriverManager().getWebServiceDriver();
  }

  /**
   * Gets the Web Service driver managers.
   *
   * @return the web service manager.
   */
  public WebServiceDriverManager getWebServiceDriverManager() {
    return (WebServiceDriverManager) this.getManagerStore()
        .get(WebServiceDriverManager.class.getCanonicalName());
  }

  /**
   * overrides the web service driver using the WebServiceDriver.
   * 
   * @param driver the Web Service Driver to be committed.
   */
  public void setWebServiceDriver(WebServiceDriver driver) {
    this.getManagerStore().put(WebServiceDriverManager.class.getCanonicalName(),
        new WebServiceDriverManager(driver, this));
  }

  /**
   * overrides the web service driver using a closable Http Client.
   * 
   * @param httpClient the Http Client to be set.
   */
  public void setWebServiceDriver(HttpClient httpClient) {
    this.getManagerStore().put(WebServiceDriverManager.class.getCanonicalName(),
        new WebServiceDriverManager((() -> httpClient), this));
  }

  /**
   * overrides the Web Service driver using a supplier.
   * 
   * @param webServiceSupplier the web service driver supplier.
   */
  public void setWebServiceDriver(Supplier<HttpClient> webServiceSupplier) {
    this.getManagerStore().put(WebServiceDriverManager.class.getCanonicalName(),
        new WebServiceDriverManager(webServiceSupplier, this));
  }
}
