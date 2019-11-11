/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices;

import com.magenic.jmaqs.utilities.helper.TestCategories;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

/**
 * Test with the web service driver directly.
 */
public class WebServiceDriverUnitTest {
  /**
   * Verifies that basic GET features work with the WebServiceDriver.
   * @throws Exception Web service get failed
   */
  @Test(groups = TestCategories.WebService)
  public void webServiceGetVerificationTest() throws Exception {
    WebServiceDriver client = new WebServiceDriver("http://magenicautomation.azurewebsites.net");
    CloseableHttpResponse response = client.getContent("/api/String/1", ContentType.TEXT_PLAIN,
        true);
    String responseString = WebServiceUtils.getResponseBody(response);

    Assert.assertTrue(responseString.contains("Tomato Soup"),
        "Was expecting a result with Tomato Soup but instead got - " + response.toString());
  }

  /**
   * Verifies that basic GET features work with the WebServiceDriver.
   * @throws Exception Web service get failed
   */
  @Test(groups = TestCategories.WebService)
  public void setWebServiceAddress() throws Exception {
    WebServiceDriver webServiceDriver = new WebServiceDriver("http://magenicautomation.azurewebsites.net");

    webServiceDriver.setBaseWebServiceAddress("http://magenicautomation.azurewebsites.net");
    Assert.assertEquals(webServiceDriver.getBaseWebServiceAddress().toString(),
            "http://magenicautomation.azurewebsites.net", "Addresses don't match");
  }

  /**
   * Verifies that basic GET features work with the WebServiceDriver.
   */
  @Test(groups = TestCategories.WebService)
  public void setHttpClient() throws URISyntaxException {
    WebServiceDriver webServiceDriver1 = new WebServiceDriver(
            "http://magenicautomation.azurewebsites.net");
    webServiceDriver1.setHttpClient(webServiceDriver1.getHttpClient(
            MediaType.APP_JSON.toString()));
    WebServiceDriver webServiceDriver2 = new WebServiceDriver(
            webServiceDriver1.getHttpClient(
                    MediaType.APP_XML.toString()));
    Assert.assertNotNull(webServiceDriver1.getHttpClient(
            MediaType.APP_JSON.toString()), "Driver 1 is null");
    Assert.assertNotNull(webServiceDriver2, "Driver 2 is null");
  }
}
