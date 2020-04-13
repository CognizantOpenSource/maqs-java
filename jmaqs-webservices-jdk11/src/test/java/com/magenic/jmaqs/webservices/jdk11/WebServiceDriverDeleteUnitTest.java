package com.magenic.jmaqs.webservices.jdk11;

import com.magenic.jmaqs.utilities.helper.TestCategories;
import com.magenic.jmaqs.webservices.jdk8.MediaType;
import java.io.IOException;
import java.net.URISyntaxException;

import com.magenic.jmaqs.webservices.jdk8.WebServiceConfig;
import org.springframework.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class WebServiceDriverDeleteUnitTest {
  /**
   * Delete Json request to assert status code.
   * @throws URISyntaxException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteJSONSerializedVerifyStatusCode()
      throws URISyntaxException, IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.deleteWithResponse("/api/XML_JSON/Delete/1", MediaType.APP_JSON, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * Delete Json request to assert status code.
   * @throws URISyntaxException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteJSONSerializedVerifyStatusCodeWithoutHeaderOverride()
      throws URISyntaxException, IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.deleteWithResponse("/api/XML_JSON/Delete/2", MediaType.APP_JSON, false);
    Assert.assertEquals(result.statusCode(), HttpStatus.CONFLICT.value());
  }

  /**
   * Delete with JSON type.
   * @throws URISyntaxException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteJSONWithType() throws URISyntaxException, IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.delete("/api/XML_JSON/Delete/1", MediaType.APP_JSON, true);
    Assert.assertTrue(result.contains("200"));
  }

  /**
   * Delete XML request using status code
   * @throws URISyntaxException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteXMLSerializedVerifyStatusCode()
      throws URISyntaxException, IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.deleteWithResponse("/api/XML_JSON/Delete/1", MediaType.APP_XML, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * Verify that the response does not return a message.
   * @throws URISyntaxException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteXMLSerializedVerifyEmptyString()
      throws URISyntaxException, IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.delete("/api/XML_JSON/Delete/1", MediaType.APP_XML, true);
    Assert.assertTrue(result.contains("200"));
  }

  /**
   * Delete string request without content utility.
   * @throws URISyntaxException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteStringWithoutMakeContent()
      throws URISyntaxException, IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.delete("/api/String/Delete/1", MediaType.PLAIN_TEXT, true);
    Assert.assertTrue(result.contains("200"));
  }

  /**
   * Delete string request to verify status code.
   * @throws URISyntaxException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteStringMakeContentStatusCode()
      throws URISyntaxException, IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.deleteWithResponse("/api/String/Delete/1", MediaType.PLAIN_TEXT, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * Delete request to vi
   * @throws URISyntaxException if exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteExpectContentError()
      throws URISyntaxException, IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.deleteWithResponse("/api/String/Delete/43", MediaType.PLAIN_TEXT, false);
    Assert.assertEquals(result.statusCode(), HttpStatus.NOT_FOUND.value());
  }

  /**
   * Delete error.
   * @throws URISyntaxException if exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void deleteExpectError() throws URISyntaxException, IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.delete("/api/String/Delete/43", MediaType.PLAIN_TEXT, false);
    Assert.assertTrue(result.contains("404"));
  }
}
