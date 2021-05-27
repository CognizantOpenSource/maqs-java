/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices.jdk11;

import com.magenic.jmaqs.webservices.jdk8.WebServiceConfig;
import com.magenic.jmaqs.utilities.helper.TestCategories;
import com.magenic.jmaqs.webservices.jdk11.models.Product;
import com.magenic.jmaqs.webservices.jdk8.MediaType;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.http.HttpResponse;
import org.springframework.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test web service gets using the base test driver.
 */
public class WebServiceDriverPutUnitTest {
  /**
   * String to hold the URL.
   */
  private static final String baseUrl = WebServiceConfig.getWebServiceUri();

  private final Product product = new Product(4, "ff", "ff", BigDecimal.valueOf(3.25));

  /**
   * Verify the string status code.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putJSONSerializedVerifyStatusCode()
      throws IOException, InterruptedException {
    String content = WebServiceUtilities.createStringEntity(product, MediaType.APP_JSON);
    WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());
    HttpResponse<String> result = webServiceDriver.put(baseUrl + "/api/XML_JSON/Put/1",
        MediaType.APP_JSON, content, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * Put With JSON Type.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putJSONWithType()
      throws IOException, InterruptedException {
    String content = WebServiceUtilities.createStringEntity(product, MediaType.APP_JSON);
    WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());
    HttpResponse<String> result = webServiceDriver.put(baseUrl + "/api/XML_JSON/Put/1",
        MediaType.APP_JSON, content, true);
    Assert.assertEquals(result.body(), "");
  }

  /**
   * Verify the stream status code.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putJSONStringSerializedVerifyStatusCode()
      throws IOException, InterruptedException {
    String content = WebServiceUtilities.createStringEntity(product, MediaType.APP_JSON);
    WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());
    HttpResponse<String> result = webServiceDriver.put(baseUrl + "/api/XML_JSON/Put/1",
        MediaType.APP_JSON, content, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * XML string verify status code.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putXMLSerializedVerifyStatusCode() throws IOException, InterruptedException {
    String content = WebServiceUtilities.createStringEntity(product, MediaType.APP_XML);
    WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());
    HttpResponse<String> result = webServiceDriver.put(
        baseUrl + "/api/XML_JSON/Put/1", MediaType.APP_XML, content, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * XML stream verify status code.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putXMLStringSerializedVerifyStatusCode() throws IOException, InterruptedException {
    String content = WebServiceUtilities.createStringEntity(product, MediaType.APP_XML);
    WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());
    HttpResponse<String> result = webServiceDriver.put(
        baseUrl + "/api/XML_JSON/Put/1", MediaType.APP_XML, content, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * Verify put returns an empty string.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putXMLSerializedVerifyEmptyString()
      throws IOException, InterruptedException {
    String content = WebServiceUtilities.createStringEntity(product, MediaType.APP_XML);
    WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());
    HttpResponse<String> result = webServiceDriver.put(
        baseUrl + "/api/XML_JSON/Put/1", MediaType.APP_XML, content,true);
    Assert.assertEquals(result.body(), "");
  }

  /**
   * String without using the utility.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putStringWithoutMakeContent()
      throws IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());
    HttpResponse<String> result = webServiceDriver.put(baseUrl + "/api/String/Put/1", MediaType.PLAIN_TEXT,
        "Test", MediaType.PLAIN_TEXT, HttpStatus.OK, true);
    Assert.assertEquals(result.body(), "");
  }

  /**
   * String using the utility.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putStringWithMakeStringContent()
      throws IOException, InterruptedException {
    String content = WebServiceUtilities.createStringEntity("Test", MediaType.PLAIN_TEXT);
    WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());
    HttpResponse<String> result = webServiceDriver.put(baseUrl + "/api/String/Put/1",
        MediaType.PLAIN_TEXT, content, true);
    Assert.assertEquals(result.body(), "");
  }

  /**
   * Put string without utility.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putStringWithoutContentStatusCode()
      throws IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());
    HttpResponse<String> result = webServiceDriver.put(
        baseUrl + "/api/String/Put/1", MediaType.PLAIN_TEXT,
        "Test", MediaType.PLAIN_TEXT, true, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * Put string with utility.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putStringMakeContentStatusCode()
      throws IOException, InterruptedException {
    String content = WebServiceUtilities.createStringEntity("Test", MediaType.PLAIN_TEXT);
    WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());
    HttpResponse<String> result = webServiceDriver.put(baseUrl + "/api/String/Put/1",
        MediaType.PLAIN_TEXT, content, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * Test other status codes.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putExpectContentError() throws IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());
    HttpResponse<String> result = webServiceDriver.put(
        baseUrl + "/api/String/Put/1", MediaType.APP_JSON, "", false);
    Assert.assertEquals(result.statusCode(), HttpStatus.CONFLICT.value());
  }

  /**
   * Test string response.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putExpectStringError() throws IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());
    HttpResponse<String> result = webServiceDriver.put(baseUrl + "/api/String/Put/1", MediaType.APP_JSON,
        "", false);
    Assert.assertEquals(result.body(), "{\"Message\":\"No Product found for name = 1 \"}");
  }
}
