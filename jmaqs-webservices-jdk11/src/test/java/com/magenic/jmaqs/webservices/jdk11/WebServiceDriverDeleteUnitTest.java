/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices.jdk11;

import com.magenic.jmaqs.utilities.helper.TestCategories;
import com.magenic.jmaqs.webservices.jdk8.MediaType;
import com.magenic.jmaqs.webservices.jdk8.WebServiceConfig;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebServiceDriverDeleteUnitTest {
  /**
   * String to hold the URL.
   */
  private static final String baseUrl = WebServiceConfig.getWebServiceUri();

  /**
   * Delete Json request to assert status code.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteJSONSerializedVerifyStatusCode()
      throws IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());
    HttpResponse<String> result = webServiceDriver.deleteWithResponse(
        baseUrl + "/api/XML_JSON/Delete/1", MediaType.APP_JSON, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * Delete Json request to assert status code.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteJSONSerializedVerifyStatusCodeWithoutHeaderOverride()
      throws IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());
    HttpResponse<String> result = webServiceDriver.deleteWithResponse(
        baseUrl + "/api/XML_JSON/Delete/2", MediaType.APP_JSON, false);
    Assert.assertEquals(result.statusCode(), HttpStatus.CONFLICT.value());
  }

  /**
   * Delete Json request to assert status code.
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteJSONSerializedVerifyStatusCodeWithHeaderOverride()
      throws IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());

    Map<String, String> header = new HashMap<>();
    header.put("header1", "pass");
    header.put("header2", "word");

    HttpResponse<String> result = webServiceDriver.deleteWithResponse(
        baseUrl + "/api/XML_JSON/Delete/2", MediaType.APP_JSON, header, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value() );
  }

  /**
   * Delete with JSON type.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteJSONWithType() throws IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());
    String result = webServiceDriver.delete(baseUrl + "/api/XML_JSON/Delete/1",
        MediaType.APP_JSON, true);
    Assert.assertTrue(result.contains("200"));
  }

  /**
   * Delete XML request using status code
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteXMLSerializedVerifyStatusCode()
      throws IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());
    HttpResponse<String> result = webServiceDriver.deleteWithResponse(
        baseUrl + "/api/XML_JSON/Delete/1", MediaType.APP_XML, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * Verify that the response does not return a message.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteXMLSerializedVerifyEmptyString()
      throws IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());
    String result = webServiceDriver.delete(baseUrl + "/api/XML_JSON/Delete/1",
        MediaType.APP_XML, true);
    Assert.assertTrue(result.contains("200"));
  }

  /**
   * Delete string request without content utility.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteStringWithoutMakeContent()
      throws IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());
    String result = webServiceDriver.delete(baseUrl + "/api/String/Delete/1",
        MediaType.PLAIN_TEXT, true);
    Assert.assertTrue(result.contains("200"));
  }

  /**
   * Delete string request to verify status code.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteStringMakeContentStatusCode()
      throws IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());
    HttpResponse<String> result = webServiceDriver.deleteWithResponse(
        baseUrl + "/api/String/Delete/1", MediaType.PLAIN_TEXT, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * Delete request to vi
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteExpectContentError()
      throws IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());
    HttpResponse<String> result = webServiceDriver.deleteWithResponse(
        baseUrl + "/api/String/Delete/43", MediaType.PLAIN_TEXT, false);
    Assert.assertEquals(result.statusCode(), HttpStatus.NOT_FOUND.value());
  }

  /**
   * Delete error.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteExpectError() throws IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());
    String result = webServiceDriver.delete(baseUrl + "/api/String/Delete/43",
        MediaType.PLAIN_TEXT, false);
    Assert.assertTrue(result.contains(String.valueOf(HttpStatus.NOT_FOUND.value())));
    Assert.assertTrue(result.contains("404"));
  }
}