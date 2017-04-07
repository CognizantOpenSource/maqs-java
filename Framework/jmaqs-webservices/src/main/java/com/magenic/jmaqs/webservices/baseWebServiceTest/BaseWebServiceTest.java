/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices.baseWebServiceTest;

import com.magenic.jmaqs.baseTest.BaseGenericTest;

import org.apache.http.client.HttpClient;
import org.testng.ITestResult;

public class BaseWebServiceTest extends BaseGenericTest {
  // The HttpClient
  private HttpClientWrapper webServiceWrapper;

  /**
   * Constructor
   */
  // TODO Update constructor to take in the WebServiceTestObject
  public BaseWebServiceTest(HttpClientWrapper webServiceWrapper) {
    super();
    this.webServiceWrapper = webServiceWrapper;
  }

  /**
   * @return A HttpClient object to interact with
   */
  public HttpClient getHttpClient() {
    return (HttpClient) webServiceWrapper.getHttpClient();
  }

  /**
   * @return A String containing the default Web Service URI
   */
  protected String getBaseWebServiceUrl() {
    return WebServiceConfig.getWebServiceUri();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.magenic.jmaqs.utilities.BaseTest.BaseGenericTest#postSetupLogging()
   */
  @Override
  protected void postSetupLogging() {
    // TODO Fill in once more info on how this is designed works.

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.magenic.jmaqs.utilities.BaseTest.BaseGenericTest#beforeLoggingTeardown
   * (org.testng.ITestResult)
   */
  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {
    // TODO Fill in once more info on how this is designed works.

  }

}
