package com.cognizantsoftvision.maqs.webservices.soap;

import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;
import javax.xml.soap.SOAPException;

/**
 * The Soap Web Service Driver Factory unit test class.
 */
public class SoapWebServiceDriverFactoryUnitTest {

  @Test(groups = TestCategories.WEB_SERVICE)
  public void getDefaultMessage() throws SOAPException {
    Assert.assertNotNull(SoapWebServiceDriverFactory.getDefaultMessage());
  }

  @Test(groups = TestCategories.WEB_SERVICE)
  public void getDefaultSoapDriver() throws SOAPException {
    Assert.assertNotNull(SoapWebServiceDriverFactory.getDefaultSoapDriver());
  }
}
