package com.magenic.jmaqs.webservices.jdk11;

import com.magenic.jmaqs.base.BaseTestObject;
import com.magenic.jmaqs.base.DriverManager;
import java.net.http.HttpClient;
import java.util.function.Supplier;

/**
 * Web Service Driver Manager Class.
 */
public class WebServiceDriverManager extends DriverManager {
  /**
   * Cached copy of the driver.
   */
  private WebServiceDriver webServiceDriver;

  /**
   * Initializes a new instance of the WebServiceDriverManager class.
   * @param getDriver Function for creating an http client
   * @param baseTestObject The associated test object
   */
  public WebServiceDriverManager(Supplier<HttpClient> getDriver, BaseTestObject baseTestObject) {
    super(getDriver, baseTestObject);
    this.webServiceDriver = new WebServiceDriver((getDriver.get()));
  }

  /**
   * Initializes a new instance of the WebServiceDriverManager class
   * @param httpClient Function for creating an http client
   * @param baseTestObject The associated test object
   */
  public WebServiceDriverManager(HttpClient httpClient, BaseTestObject baseTestObject) {
    super(() -> httpClient, baseTestObject);
  }

  /**
   * Initializes a new instance of the WebServiceDriverManager class.
   * @param getDriver Function for creating an http client
   * @param baseTestObject The associated test object
   */
  public WebServiceDriverManager(WebServiceDriver getDriver, BaseTestObject baseTestObject) {
    super(() -> getDriver, baseTestObject);
    this.webServiceDriver = getDriver;
  }

  /**
   * Override the web service driver.
   * @param driver A new http driver
   */
  public void overrideDriver(WebServiceDriver driver) {
    this.webServiceDriver = driver;
  }

  /**
   * Get the Web Service Driver.
   * @return  The Web Service Driver.
   */
  public WebServiceDriver getWebServiceDriver() {
    // Create default Web Service Driver if null.
    if (this.webServiceDriver == null) {
      // If Base Driver Supplier returns Web Service Driver, use that Web Service Driver.
      if (getBase() instanceof WebServiceDriver) {
        this.webServiceDriver = (WebServiceDriver) getBase();
      } else {
        // If Base Driver Supplier returns HttpClient, use that to instantiate Web Service Driver.
        this.webServiceDriver = new WebServiceDriver((HttpClient) getBase());
      }
    }
    return this.webServiceDriver;
  }

  /**
   * Close Method sets Base Driver to Null.
   */
  public void close() {
    this.setBaseDriver(null);
  }
}
