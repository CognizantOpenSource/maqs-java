/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices;

import com.magenic.jmaqs.base.BaseExtendableTest;

import com.magenic.jmaqs.utilities.helper.StringProcessor;
import com.magenic.jmaqs.utilities.logging.Logger;

import java.net.URISyntaxException;

import org.testng.ITestResult;

/**
 * Base web service test class.
 */
public abstract class BaseWebServiceTest extends BaseExtendableTest<WebServiceTestObject> {

  /**
   * Thread local storage of web service test object.
   */
  @Deprecated private ThreadLocal<WebServiceTestObject> webServiceTestObject = new ThreadLocal<WebServiceTestObject>();

  /**
   * Get the Web Service Driver.
   *
   * @return WebServiceDriver
   */
  public WebServiceDriver getWebServiceDriver() {
    return this.getTestObject().getWebServiceDriver();
  }

  /**
   * Get the webServiceTestObject for this test.
   *
   * @return The seleniumTestObject
   */
  @Deprecated public WebServiceTestObject getWebServiceTestObject() {
    return this.webServiceTestObject.get();

  }

  /**
   * Set the webServiceDriver
   *
   * @param webServiceDriver
   */
  public void setWebServiceDriver(WebServiceDriver webServiceDriver) {
    this.getTestObject().setWebServiceDriver(webServiceDriver);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.magenic.jmaqs.utilities.BaseTest.BaseTest#postSetupLogging()
   */
  @Override @Deprecated protected void postSetupLogging() throws Exception {

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
  @Override protected void beforeLoggingTeardown(ITestResult resultType) {
    // There is no before logging tear-down required
  }

  /**
   * Gets new WebServiceDriver
   * @return WebServiceDriver
   * @throws URISyntaxException
   */
  protected WebServiceDriver getWebServiceClient() throws URISyntaxException {
    return new WebServiceDriver(WebServiceConfig.getWebServiceUri());
  }

  /**
   * Creates a new test object
   */
  @Override protected void createNewTestObject() {
    Logger logger = this.createLogger();
    try {

      WebServiceTestObject webServiceTestObject = new WebServiceTestObject(
          this.getWebServiceClient(), logger, this.getFullyQualifiedTestClassName());
      this.setTestObject(webServiceTestObject);
    } catch (URISyntaxException e) {
      getLogger().logMessage(
          StringProcessor.safeFormatter("Test Object could not be created: %s", e.getMessage()));
    }
  }
}
