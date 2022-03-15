/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.webservices.soap;

import com.cognizantsoftvision.maqs.base.BaseExtendableTest;
import com.cognizantsoftvision.maqs.utilities.helper.StringProcessor;
import com.cognizantsoftvision.maqs.utilities.logging.Logger;
import com.cognizantsoftvision.maqs.webservices.WebServiceConfig;
import com.cognizantsoftvision.maqs.webservices.WebServiceTestObject;
import java.net.URISyntaxException;
import javax.xml.soap.SOAPException;
import org.testng.ITestResult;

/**
 * The Base Soap Web Service Test class.
 */
public class BaseSoapWebServiceTest extends BaseExtendableTest<WebServiceTestObject> {

  /**
   * Get the Web Service Driver.
   *
   * @return WebServiceDriver
   */
  public SoapWebServiceDriver getWebServiceDriver() {
    return (SoapWebServiceDriver) this.getTestObject().getWebServiceDriver();
  }

  /**
   * Set the webServiceDriver.
   *
   * @param webServiceDriver the webservice driver object
   */
  public void setWebServiceDriver(SoapWebServiceDriver webServiceDriver) {
    this.getTestObject().setWebServiceDriver(webServiceDriver);
  }

  /*
   * (non-Javadoc)
   *
   * @see com.cognizantsoftvision.maqs.utilities.BaseTest.BaseTest# beforeLoggingTeardown
   * (org.testng.ITestResult)
   */
  @Override
  protected void beforeLoggingTeardown(ITestResult resultType) {
    // There is no logging tear-down required before
  }

  /**
   * Gets new WebServiceDriver.
   *
   * @return WebServiceDriver
   * @throws URISyntaxException when URI is incorrect
   */
  protected SoapWebServiceDriver getWebServiceClient() throws URISyntaxException, SOAPException {
    return new SoapWebServiceDriver(WebServiceConfig.getWebServiceUri());
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
    } catch (URISyntaxException | SOAPException e) {
      getLogger().logMessage(StringProcessor.safeFormatter(
          "Test Object could not be created: %s", e.getMessage()));
    }
  }
}
