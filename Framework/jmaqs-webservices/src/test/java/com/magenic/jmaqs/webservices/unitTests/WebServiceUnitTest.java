/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices.unitTests;

import com.magenic.jmaqs.webservices.baseWebServiceTest.BaseWebServiceTest;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Web service wrapper unit tests.
 */
public class WebServiceUnitTest extends BaseWebServiceTest {
  @Test
  public void webServiceGetVerificationTest() throws Exception {

    CloseableHttpResponse response = this.getHttpClientWrapper().getContent("/api/String/1",
        ContentType.TEXT_PLAIN, true);
    HttpEntity entity = response.getEntity();
    String responseString = EntityUtils.toString(entity);
    Assert.assertTrue(responseString.contains("Tomato Soup"),
        "Was expecting a result with Tomato Soup but instead got - " + response.toString());
  }

}