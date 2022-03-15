package com.cognizantsoftvision.maqs.webservices.soap;

import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import com.cognizantsoftvision.maqs.webservices.MediaType;
import com.cognizantsoftvision.maqs.webservices.WebServiceConfig;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import javax.xml.bind.JAXBException;
import javax.xml.soap.SOAPException;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

/**
 * The Soap Utilities unit test class.
 */
public class SoapUtilitiesUnitTest {

  /**
   * String to hold the URL.
   */
  private static final String baseUrl = WebServiceConfig.getWebServiceURIString();

  @Test(groups = TestCategories.WEB_SERVICE)
  public void getResponseBodyAsObjects()
      throws SOAPException, IOException, InterruptedException, JAXBException {
    SoapWebServiceDriver driver = new SoapWebServiceDriver(WebServiceConfig.getWebServiceUri());
    HttpResponse<String> res = driver.get(
        baseUrl + "/api/XML_JSON/GetAllProducts", MediaType.APP_XML, HttpStatus.OK);

    SoapUtilities.getResponseBodyAsObjects(res, driver.getCachedUnMarshaller());
  }
}
