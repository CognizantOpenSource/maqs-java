/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.webservices;

import com.cognizantsoftvision.maqs.base.BaseGenericTest;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import java.net.URISyntaxException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test with the web service driver directly.
 */
public class WebServiceDriverUnitTest extends BaseGenericTest {
  /**
   * Verifies that basic GET features work with the WebServiceDriver.
   *
   * @throws Exception Web service get failed
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void webServiceGetVerificationTest() throws Exception {
    WebServiceDriver client = new WebServiceDriver("http://magenicautomation.azurewebsites.net");
    CloseableHttpResponse response = client
        .getContent("/api/String/1", ContentType.TEXT_PLAIN, true);
    String responseString = WebServiceUtilities.getResponseBody(response);

    Assert.assertTrue(responseString.contains("Tomato Soup"),
        "Was expecting a result with Tomato Soup but instead got - " + response.toString());
  }

  /**
   * Verifies that basic GET features work with the WebServiceDriver.
   *
   * @throws Exception Web service get failed
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void setWebServiceAddress() throws Exception {
    WebServiceDriver webServiceDriver = new WebServiceDriver(
        "http://magenicautomation.azurewebsites.net");

    webServiceDriver.setBaseWebServiceAddress("http://magenicautomation.azurewebsites.net");
    Assert.assertEquals(webServiceDriver.getBaseWebServiceAddress().toString(),
        "http://magenicautomation.azurewebsites.net", "Addresses don't match");
  }

  /**
   * Verifies that basic GET features work with the WebServiceDriver.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void setHttpClient() throws URISyntaxException {
    WebServiceDriver webServiceDriver1 = new WebServiceDriver(
        "http://magenicautomation.azurewebsites.net");
    webServiceDriver1.setHttpClient(webServiceDriver1.getHttpClient(MediaType.APP_JSON.toString()));
    WebServiceDriver webServiceDriver2 = new WebServiceDriver(
        webServiceDriver1.getHttpClient(MediaType.APP_XML.toString()));
    Assert.assertNotNull(webServiceDriver1.getHttpClient(MediaType.APP_JSON.toString()),
        "Driver 1 is null");
    Assert.assertNotNull(webServiceDriver2, "Driver 2 is null");
  }
}
