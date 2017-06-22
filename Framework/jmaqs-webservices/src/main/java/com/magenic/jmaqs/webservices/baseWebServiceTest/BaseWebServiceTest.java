/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices.baseWebServiceTest;

import com.magenic.jmaqs.baseTest.BaseGenericTest;

import org.testng.ITestResult;

/**
 * Base web service test class.
 */
public class BaseWebServiceTest extends BaseGenericTest {

  /**
   * Thread local storage of web service test object.
   */
  private ThreadLocal<WebServiceTestObject> webServiceTestObject = new ThreadLocal<WebServiceTestObject>();

  /**
   * Get HttpClientWrapper.
   * 
   * @return WebDriver
   */
  public HttpClientWrapper getHttpClientWrapper() {
    return this.webServiceTestObject.get().webServiceWrapper;
  }

  /**
   * Get the webServiceTestObject for this test.
   * 
   * @return The seleniumTestObject
   */
  public WebServiceTestObject getWebServiceTestObject() {
    return this.webServiceTestObject.get();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.magenic.jmaqs.utilities.BaseTest.BaseGenericTest#postSetupLogging()
   */
  @Override
  protected void postSetupLogging() throws Exception {

    HttpClientWrapper wrapper = new HttpClientWrapper(WebServiceConfig.getWebServiceUri());
    webServiceTestObject.set(
        new WebServiceTestObject(wrapper, this.getLogger(), this.getFullyQualifiedTestClassName()));
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.magenic.jmaqs.utilities.BaseTest.BaseGenericTest# beforeLoggingTeardown
   * (org.testng.ITestResult)
   */
  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {
    // There is no before logging tear-down required
  }
}
