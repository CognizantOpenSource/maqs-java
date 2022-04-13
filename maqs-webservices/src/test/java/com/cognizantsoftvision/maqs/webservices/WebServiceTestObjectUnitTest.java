/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.webservices;

import com.cognizantsoftvision.maqs.base.BaseGenericTest;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.function.Supplier;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Web Service Test Object unit test class.
 */
public class WebServiceTestObjectUnitTest extends BaseGenericTest {

  /**
   * Test object creation with driver.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void testWebServiceTestObjectCreationWithDriver() {
    WebServiceDriver defaultBrowser = getWebServiceDriver();
    WebServiceTestObject testObject = new WebServiceTestObject(
        defaultBrowser, this.getLogger(), this.getFullyQualifiedTestClassName());
    Assert.assertNotNull(testObject, "Checking that the Web service test object via driver is not null");
  }

  /**
   * Test object creation with supplier.
   */
  @Test(groups = TestCategories.SELENIUM)
  public void testWebServiceTestObjectCreationWithSupplier() {
    Supplier<HttpClient> httpClientSupplier = getHttpClientSupplier();
    WebServiceTestObject testObject = new WebServiceTestObject(
        httpClientSupplier, this.getLogger(), this.getFullyQualifiedTestClassName());
    Assert.assertNotNull(testObject, "Checking that the Web service test object via driver is not null");
  }

  /**
   * tests getting the web service driver.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void testGetWebServiceDriver() {
    WebServiceDriver testObject = getWebServiceDriver();
    Assert.assertNotNull(testObject, "Checking that the Web service test object via driver is null");
  }

  /**
   * tests getting the web service driver manager.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void testGetWebServiceDriverManager() {
    WebServiceDriver defaultBrowser = getWebServiceDriver();
    try (WebServiceTestObject testObject = new WebServiceTestObject(
        defaultBrowser, this.getLogger(), this.getFullyQualifiedTestClassName())) {
      Assert.assertNotNull(testObject.getWebServiceDriverManager(),
          "Checking that the Web service driver manager is null");
    }
  }

  /**
   * tests overriding the web service driver with a web service driver.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void testSetWebServiceDriverWithDriver() {
    WebServiceDriver serviceDriver = getWebServiceDriver();
    try (WebServiceTestObject testObject = new WebServiceTestObject(
        serviceDriver, this.getLogger(), this.getFullyQualifiedTestClassName())) {
      testObject.setWebServiceDriver(serviceDriver);
      Assert.assertNotNull(testObject.getWebServiceDriver(), "the web service driver is null");
      int hashCode = testObject.getWebServiceDriver().hashCode();
      testObject.setWebServiceDriver(getWebServiceDriver());
      int hashCode1 = testObject.getWebServiceDriver().hashCode();
      Assert.assertNotEquals(hashCode, hashCode1);
    }
  }

  /**
   * tests overriding the web service driver with a closeable http client.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void testSetWebServiceDriverWithHttpClient() {
    WebServiceDriver serviceDriver = getWebServiceDriver();
    HttpClient client = HttpClient.newBuilder().build();
    try (WebServiceTestObject testObject = new WebServiceTestObject(
        serviceDriver, this.getLogger(), this.getFullyQualifiedTestClassName())) {
      testObject.setWebServiceDriver(client);
      Assert.assertNotNull(testObject.getWebServiceDriver(), "the web service driver is null");
      int hashCode = testObject.getWebServiceDriver().hashCode();
      testObject.setWebServiceDriver(client);
      int hashCode1 = testObject.getWebServiceDriver().hashCode();
      Assert.assertNotEquals(hashCode, hashCode1);
    }
  }

  /**
   * tests overriding the web service driver with a supplier.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void testSetWebServiceDriverSupplier() {
    Supplier<HttpClient> httpClientSupplier = getHttpClientSupplier();
    try (WebServiceTestObject testObject = new WebServiceTestObject(
        httpClientSupplier, this.getLogger(), this.getFullyQualifiedTestClassName())) {
      Assert.assertNotNull(testObject.getWebServiceDriver(), "the web service driver is null");
      int hashCode = testObject.getWebServiceDriver().hashCode();
      testObject.setWebServiceDriver(getHttpClientSupplier());
      int hashCode1 = testObject.getWebServiceDriver().hashCode();
      Assert.assertNotEquals(hashCode, hashCode1);
    }
  }

  /**
   * gets a Http client supplier.
   *
   * @return a http client supplier.
   */
  private Supplier<HttpClient> getHttpClientSupplier() {
    HttpClient client = HttpClient.newHttpClient();
    return () -> client;
  }

  /**
   * gets the web service driver via the config uri.
   *
   * @return a web service driver.
   */
  private WebServiceDriver getWebServiceDriver() {
    return new WebServiceDriver(HttpRequest.newBuilder(URI.create(WebServiceConfig.getWebServiceUri())));
  }
}
