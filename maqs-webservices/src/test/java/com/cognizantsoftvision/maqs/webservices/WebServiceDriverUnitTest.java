/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.webservices;

import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import java.net.http.HttpRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Web Service Driver unit test class.
 */
public class WebServiceDriverUnitTest {

  /**
   * Verifies that basic setting the Http Client works
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void setHttpClient() {
    WebServiceDriver webServiceDriver1 = new WebServiceDriver(HttpClientFactory.getDefaultClient());
    webServiceDriver1.setHttpClient(webServiceDriver1.getHttpClient());
    WebServiceDriver webServiceDriver2 = new WebServiceDriver(webServiceDriver1.getHttpClient());
    Assert.assertNotNull(webServiceDriver1.getHttpClient(), "Driver 1 is null");
    Assert.assertNotNull(webServiceDriver2, "Driver 2 is null");
  }

  /**
   * Verifies that basic setting the Http Request Builder works
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void setHttpRequest() {
    WebServiceDriver webServiceDriver = new WebServiceDriver(HttpClientFactory.getDefaultClient());
    webServiceDriver.setHttpRequestBuilder(HttpRequest.newBuilder());
    Assert.assertNotNull(webServiceDriver.getHttpRequestBuilder());
  }
}
