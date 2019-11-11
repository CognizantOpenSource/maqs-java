/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test with the web service driver directly.
 */
public class WebServiceDriverUnitTest {
  /**
   * Verifies that basic GET features work with the WebServiceDriver.
   * @throws Exception Web service get failed
   */
  @Test
  public void webServiceGetVerificationTest() throws Exception {
    WebServiceDriver client = new WebServiceDriver("http://magenicautomation.azurewebsites.net");
    CloseableHttpResponse response = client.getContent("/api/String/1", ContentType.TEXT_PLAIN,
        true);
    String responseString = WebServiceUtils.getResponseBody(response);

    Assert.assertTrue(responseString.contains("Tomato Soup"),
        "Was expecting a result with Tomato Soup but instead got - " + response.toString());
  }
}
