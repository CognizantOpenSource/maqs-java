/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices.jdk11;

import com.magenic.jmaqs.utilities.helper.TestCategories;
import com.magenic.jmaqs.webservices.jdk11.models.Product;
import com.magenic.jmaqs.webservices.jdk8.MediaType;
import com.magenic.jmaqs.webservices.jdk8.WebServiceConfig;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.http.HttpResponse;
import org.springframework.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebServiceDriverStatusCodeUnitTest {
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
   * Test type parameterized Patch request with expected status.
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void patchTypeParamWithExpectedStatus() throws IOException, InterruptedException {
    var req = WebServiceUtilities.createStringEntity(product, MediaType.APP_JSON);
    var res = this.webServiceDriver.patch(baseUrl +"/api/XML_JSON/Patch/1",
        MediaType.APP_JSON, req, Product.class, HttpStatus.OK);
    Assert.assertNotNull(res);
  }

  /**
   * Test Patch request with expected status.
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void patchWithExpectedStatus() throws IOException, InterruptedException {
    var req = WebServiceUtilities.createStringEntity(product, MediaType.APP_JSON);
    var res = this.webServiceDriver.patch(
        baseUrl+ "/api/XML_JSON/Patch/1", MediaType.APP_JSON, req, HttpStatus.OK);
    Assert.assertNotNull(res);
  }

  /**
   * Test more params Patch request with expected status.
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void patchMoreParamsWithExpectedStatus() throws IOException, InterruptedException {
    var req = WebServiceUtilities.createStringEntity(product, MediaType.APP_JSON);
    var res = this.webServiceDriver.patch(baseUrl + "/api/XML_JSON/Patch/1",
        MediaType.APP_JSON, req, MediaType.APP_JSON, HttpStatus.OK);
    Assert.assertNotNull(res);
  }

  /**
   * Test more params Patch with response request with expected status.
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void PatchMoreParamsWithResponseWithExpectedStatus()
      throws IOException, InterruptedException {
    var res = this.webServiceDriver.patch(baseUrl + "/api/XML_JSON/Patch/1",
        MediaType.APP_JSON, WebServiceUtilities.serializeJson(product), MediaType.APP_JSON, HttpStatus.OK);
    Assert.assertNotNull(res);
  }

  /**
   * Test Patch with response request with expected status.
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void patchWithResponseWithExpectedStatus() throws IOException, InterruptedException {
    var req = WebServiceUtilities.createStringEntity(product, MediaType.APP_JSON);
    var res = this.webServiceDriver.patch(
        baseUrl + "/api/XML_JSON/Patch/1", MediaType.APP_JSON, req, HttpStatus.OK);
    Assert.assertNotNull(res);
  }
}
