/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices.jdk11;

import com.magenic.jmaqs.utilities.helper.TestCategories;
import com.magenic.jmaqs.webservices.jdk11.models.Product;
import com.magenic.jmaqs.webservices.jdk8.BaseWebServiceTest;
import com.magenic.jmaqs.webservices.jdk8.MediaType;
import com.magenic.jmaqs.webservices.jdk8.WebServiceConfig;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.http.HttpResponse;
import org.springframework.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebServiceStatusCodeUnitTest extends BaseWebServiceTest {
  /**
   * String to hold the URL.
   */
  private static final String baseUrl = WebServiceConfig.getWebServiceUri();

  /**
   * Product object to be used in a test.
   */
  private final Product product = new Product(4, "ff", "ff", BigDecimal.valueOf(3.25));

  /**
   * The web service driver to be used in a test.
   */
  private final WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());

  /**
   * Test type parameterized Get request with expected status.
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void getTypeParamWithExpectedStatus() throws IOException, InterruptedException {
    Object res = webServiceDriver.get(baseUrl + "/api/XML_JSON/GetAllProducts",
            MediaType.APP_JSON, HttpStatus.OK, Product[].class);
    Assert.assertNotNull(res);
  }

  /**
   * Test Get request with expected status.
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void getWithExpectedStatus() throws IOException, InterruptedException {
    HttpResponse<String> res = webServiceDriver.get(
        baseUrl + "/api/XML_JSON/GetAllProducts", MediaType.APP_XML, HttpStatus.OK);
    Assert.assertNotNull(res);
  }

  /**
   * Test Get with response request with expected status.
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void getWithResponseWithExpectedStatus() throws IOException, InterruptedException {
    HttpResponse<String> res = webServiceDriver.get(
        baseUrl + "/api/XML_JSON/GetAllProducts", MediaType.APP_XML, HttpStatus.OK);
    Assert.assertNotNull(res);
  }

  /**
   * Test type parameterized Put request with expected status.
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putTypeParamWithExpectedStatus() throws IOException, InterruptedException {
    var res = webServiceDriver.put(baseUrl +"/api/XML_JSON/Put/1", MediaType.APP_JSON,
        WebServiceUtilities.createStringEntity(product, MediaType.APP_JSON), Product.class, true);
    Assert.assertNull(res);
  }

  /**
   * Test Put request with expected status.
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putWithExpectedStatus() throws IOException, InterruptedException {
    var res = webServiceDriver.put(baseUrl +"/api/XML_JSON/Put/1", MediaType.APP_JSON,
        WebServiceUtilities.createStringEntity(product, MediaType.APP_JSON), Product.class, HttpStatus.OK);
    Assert.assertNull(res);
  }

  /**
   * Test more params Put request with expected status.
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putMoreParamsWithExpectedStatus() throws IOException, InterruptedException {
    var res = webServiceDriver.put(baseUrl +"/api/XML_JSON/Put/1", MediaType.APP_JSON,
        WebServiceUtilities.createStringEntity(product, MediaType.APP_JSON), MediaType.APP_JSON, HttpStatus.OK);
    Assert.assertNotNull(res);
  }

  /**
   * Test more params Put with response request with expected status.
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putMoreParamsWithResponseWithExpectedStatus() throws IOException, InterruptedException {
    var res = webServiceDriver.put(baseUrl +"/api/XML_JSON/Put/1",
        MediaType.APP_JSON, WebServiceUtilities.createStringEntity(product, MediaType.APP_JSON),
        MediaType.APP_JSON, HttpStatus.OK);
    Assert.assertNotNull(res);
  }

  /**
   * Test Put with response request with expected status.
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putWithResponseWithExpectedStatus() throws IOException, InterruptedException {
    var res = webServiceDriver.put(baseUrl +"/api/XML_JSON/Put/1",
        MediaType.APP_JSON, WebServiceUtilities.createStringEntity(product, MediaType.APP_JSON), HttpStatus.OK);
    Assert.assertNotNull(res);
  }
}