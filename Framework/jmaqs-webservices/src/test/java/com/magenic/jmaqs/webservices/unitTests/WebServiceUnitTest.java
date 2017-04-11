/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices.unitTests;

import com.magenic.jmaqs.webservices.baseWebServiceTest.HttpClientWrapper;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebServiceUnitTest {

  /**
   * Verifies that basic GET features work with the HttpClientWrapper
   * 
   * @throws Exception
   *           Web service get failed
   */
  @Test
  public void webServiceGetVerificationTest() throws Exception {
    HttpClientWrapper client = new HttpClientWrapper("http://magenicautomation.azurewebsites.net",
        ContentType.TEXT_PLAIN);
    CloseableHttpResponse response = client.getWithResponse("/api/String/1", true);
    HttpEntity entity = response.getEntity();
    String responseString = EntityUtils.toString(entity);
    Assert.assertTrue(responseString.contains("Tomato Soup"),
        "Was expecting a result with Tomato Soup but instead got - " + response.toString());
  }
}