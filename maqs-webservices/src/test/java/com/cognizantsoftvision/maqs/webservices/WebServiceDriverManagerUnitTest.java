/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.webservices;

import com.cognizantsoftvision.maqs.base.BaseGenericTest;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit Tests for Web Service Driver Manager.
 */
public class WebServiceDriverManagerUnitTest extends BaseGenericTest {

  /**
   * Test for Getting Web Service Driver using Supplier in constructor.
   *
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void getWebServiceDriverWithSupplierTest() {
    WebServiceDriver webServiceDriver = new WebServiceDriver(
        HttpRequest.newBuilder(WebServiceConfig.getWebServiceUri()));
    webServiceDriver.setHttpClient(webServiceDriver.getHttpClient());

    try (WebServiceDriverManager driverManager = new WebServiceDriverManager(
        webServiceDriver::getHttpClient, this.getTestObject())) {
      Assert.assertNotNull(driverManager.getWebServiceDriver(), "Expected Web Service Driver to not be null");
    }
  }

  /**
   * Test for Getting Web Service Driver using Web Service Driver in constructor.
   *
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void getWebServiceDriverTest() {
    WebServiceDriver webServiceDriver = new WebServiceDriver(
        HttpRequest.newBuilder(WebServiceConfig.getWebServiceUri()));
    try (WebServiceDriverManager driverManager = new WebServiceDriverManager(webServiceDriver, this.getTestObject())) {
      Assert.assertNotNull(driverManager.getWebServiceDriver(), "Expected Web Service Driver to not be null");
    }
  }

  /**
   * Test for Getting Web Service Driver when Driver is null and instantiates
   * default Driver.
   *
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void getWebServiceDriverNullDriver() {
    WebServiceDriver webServiceDriver = new WebServiceDriver(
        HttpRequest.newBuilder(WebServiceConfig.getWebServiceUri()));
    try (WebServiceDriverManager driverManager = new WebServiceDriverManager(webServiceDriver, this.getTestObject())) {
      // Set the Driver to be null then check Get Web Service Driver creates default Driver.
      driverManager.overrideDriver(null);
      Assert.assertNotNull(driverManager.getWebServiceDriver(),
          "Expected Default Web Service Driver to be created.");
    }
  }

  /**
   * Test for Overriding Web Service Driver.
   *
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void overrideWebServiceDriverTest() {
    WebServiceDriver webServiceDriver = new WebServiceDriver(
        HttpRequest.newBuilder(WebServiceConfig.getWebServiceUri()));
    WebServiceDriver webServiceDriver2 = new WebServiceDriver(
        HttpRequest.newBuilder(URI.create("http://www.google.com/")));

    try (WebServiceDriverManager driverManager = new WebServiceDriverManager(webServiceDriver, this.getTestObject())) {
      driverManager.overrideDriver(webServiceDriver2);

      Assert.assertEquals(driverManager.getWebServiceDriver().getBaseWebServiceAddress().toString(),
          "http://www.google.com/");
    }
  }

  /**
   * Test for Closing Web Service Driver.
   *
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void closeWebServiceDriverTest() {
    WebServiceDriver webServiceDriver = new WebServiceDriver(
        HttpRequest.newBuilder(WebServiceConfig.getWebServiceUri()));
    WebServiceDriverManager driverManager = new WebServiceDriverManager(webServiceDriver, this.getTestObject());

    driverManager.close();
    Assert.assertNull(driverManager.getBaseDriver(), "Expected Base Driver to be null.");
  }
}
