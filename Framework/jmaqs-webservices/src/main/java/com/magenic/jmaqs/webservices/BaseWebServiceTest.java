/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices;

import com.magenic.jmaqs.base.BaseExtendableTest;

import com.magenic.jmaqs.utilities.logging.Logger;
import java.net.URISyntaxException;
import org.testng.ITestResult;

/**
 * Base web service test class.
 */
public class BaseWebServiceTest extends BaseExtendableTest<WebServiceTestObject> {

  /**
   * Thread local storage of web service test object.
   */
  @Deprecated
  private ThreadLocal<WebServiceTestObject> webServiceTestObject = new ThreadLocal<WebServiceTestObject>();

  /**
   * Get the Web Service Driver.
   * 
   * @return WebDriver
   */
  public WebServiceDriver getWebServiceDriver() {
    return this.webServiceTestObject.get().getWebServiceDriver();
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
   * @see com.magenic.jmaqs.utilities.BaseTest.BaseTest#postSetupLogging()
   */
  @Override
  @Deprecated
  protected void postSetupLogging() throws Exception {

    WebServiceDriver wrapper = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    webServiceTestObject.set(
        new WebServiceTestObject(wrapper, this.getLogger(), this.getFullyQualifiedTestClassName()));
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.magenic.jmaqs.utilities.BaseTest.BaseTest# beforeLoggingTeardown
   * (org.testng.ITestResult)
   */
  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {
    // There is no before logging tear-down required
  }

  @Override
  protected void createNewTestObject() {

    //FIXME: Workaround to get module working.  Must Refactor.
    Logger logger = this.createLogger();
    try {
      WebServiceDriver httpClientWrapper = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
      WebServiceTestObject webServiceTestObject = new WebServiceTestObject(httpClientWrapper, logger, this.getFullyQualifiedTestClassName());
      this.setTestObject(webServiceTestObject);
      this.webServiceTestObject.set(webServiceTestObject);
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

  }
}
