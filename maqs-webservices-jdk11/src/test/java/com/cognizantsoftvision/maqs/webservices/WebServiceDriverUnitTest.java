/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.webservices;

import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import java.net.http.HttpRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebServiceDriverUnitTest {

  /**
   * Verifies that basic setting the Http Client works
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void setHttpClient() {
    WebServiceDriver11 webServiceDriver1 = new WebServiceDriver11(
        HttpClientFactory.getDefaultClient());
    webServiceDriver1.setHttpClient(webServiceDriver1.getHttpClient());
    WebServiceDriver11 webServiceDriver2 = new WebServiceDriver11(
        webServiceDriver1.getHttpClient());
    Assert.assertNotNull(webServiceDriver1.getHttpClient(),
        "Driver 1 is null");
    Assert.assertNotNull(webServiceDriver2, "Driver 2 is null");
  }

  /**
   * Verifies that basic setting the Http Request Builder works
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void setHttpRequest() {
    WebServiceDriver11 webServiceDriver = new WebServiceDriver11(
        HttpClientFactory.getDefaultClient());
    webServiceDriver.setHttpRequestBuilder(HttpRequest.newBuilder());
    Assert.assertNotNull(webServiceDriver.getHttpRequestBuilder());
  }
}
