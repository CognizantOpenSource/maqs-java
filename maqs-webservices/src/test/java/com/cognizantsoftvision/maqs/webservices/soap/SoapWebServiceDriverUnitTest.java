package com.cognizantsoftvision.maqs.webservices.soap;

import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import com.cognizantsoftvision.maqs.webservices.WebServiceConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import java.io.IOException;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;

/**
 * The Soap Web Service Driver unit test class.
 */
public class SoapWebServiceDriverUnitTest extends BaseSoapWebServiceTest {

  @Test(groups = TestCategories.WEB_SERVICE)
  public void getDefaultSoapMessage()
      throws SOAPException, ParserConfigurationException, IOException,
      SAXException {
    SoapWebServiceDriver driver = new SoapWebServiceDriver(WebServiceConfig.getWebServiceUri());
    Assert.assertNotNull(driver.getDefaultSoapMessage("Test String"));
  }

  @Test(groups = TestCategories.WEB_SERVICE)
  public void getSoapBodyStringEntity()
      throws SOAPException, ParserConfigurationException, IOException,
      SAXException {
    SoapWebServiceDriver driver = new SoapWebServiceDriver(WebServiceConfig.getWebServiceUri());
    Assert.assertNotNull(driver.getDefaultSoapMessage("Test String"));
    Assert.assertNotNull(driver.getSoapBodyStringEntity());
  }

  @Test(groups = TestCategories.WEB_SERVICE)
  public void addNameSpaceToMessage() throws SOAPException {
    SoapWebServiceDriver driver = new SoapWebServiceDriver(WebServiceConfig.getWebServiceUri());
    driver.addNameSpaceToMessage("Test", "Value");
  }

  @Test(groups = TestCategories.WEB_SERVICE)
  public void getUnMarshaller() throws SOAPException, JAXBException {
    SoapWebServiceDriver driver = new SoapWebServiceDriver(WebServiceConfig.getWebServiceUri());
    Assert.assertNotNull(driver.getUnMarshaller("Test"));
  }

  @Test(groups = TestCategories.WEB_SERVICE)
  public void addObjectToSoapMessage()
      throws JAXBException, SOAPException, IOException,
      ParserConfigurationException, SAXException {
    SoapWebServiceDriver driver = new SoapWebServiceDriver(WebServiceConfig.getWebServiceUri());
    driver.addObjectToSoapMessage("Test String");
    Assert.assertEquals(driver.getSoapBodyString(), "Test String");
  }
}
