/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.webservices;

import com.cognizantsoftvision.maqs.base.BaseExtendableTest;
import com.cognizantsoftvision.maqs.utilities.helper.StringProcessor;
import com.cognizantsoftvision.maqs.utilities.logging.Logger;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;

import org.testng.ITestResult;

/**
 * Base web service test class.
 */
public class BaseWebServiceTest extends BaseExtendableTest<WebServiceTestObject> {

  /**
   * Get the Web Service Driver.
   *
   * @return WebServiceDriver
   */
  public WebServiceDriver getWebServiceDriver() {
    return this.getTestObject().getWebServiceDriver();
  }

  /**
   * Set the webServiceDriver.
   *
   * @param webServiceDriver the webservice driver object
   */
  public void setWebServiceDriver(WebServiceDriver webServiceDriver) {
    this.getTestObject().setWebServiceDriver(webServiceDriver);
  }

  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {
    // No logging tear-down required here.
  }

  /**
   * Gets new WebServiceDriver.
   *
   * @return WebServiceDriver
   * @throws URISyntaxException when URI is incorrect
   */
  protected WebServiceDriver getWebServiceClient() throws URISyntaxException {
    return new WebServiceDriver(HttpRequest.newBuilder(URI.create(WebServiceConfig.getWebServiceUri())));
  }

  /**
   * Creates a new test object.
   */
  @Override
  protected void createNewTestObject() {
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
