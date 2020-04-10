package com.magenic.jmaqs.webservices.jdk11;

import com.magenic.jmaqs.utilities.helper.TestCategories;
import com.magenic.jmaqs.webservices.jdk11.models.Product;
import com.magenic.jmaqs.webservices.jdk8.WebServiceConfig;
import com.magenic.jmaqs.webservices.jdk8.MediaType;
import java.io.IOException;
import java.net.URISyntaxException;
import org.springframework.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test web service gets using the base test driver.
 */
public class WebServiceDriverPutUnitTest {
  /**
   * Verify the string status code.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putJSONSerializedVerifyStatusCode()
      throws URISyntaxException, IOException, InterruptedException {
    Product p = new Product();
    p.setCategory("ff");
    p.setId(4);
    p.setName("ff");
    p.setPrice(3.25);

    var content = WebServiceUtilities.makeStringContent(p, MediaType.APP_JSON);
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.putWithResponse("/api/XML_JSON/Put/1", MediaType.APP_JSON, content, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /// <summary>
  /// Put With JSON Type
  /// </summary>
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putJSONWithType() throws URISyntaxException, IOException, InterruptedException {
    Product p = new Product();
    p.setCategory("ff");
    p.setId(4);
    p.setName("ff");
    p.setPrice(3.25);

    var content = WebServiceUtilities.makeStringContent(p, MediaType.APP_JSON);
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.put("/api/XML_JSON/Put/1", MediaType.APP_JSON, content, true);
    Assert.assertNull(result);
  }

  /// <summary>
  /// Verify the stream status code
  /// </summary>
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putJSONStreamSerializedVerifyStatusCode()
      throws URISyntaxException, IOException, InterruptedException {
    Product p = new Product();
    p.setCategory("ff");
    p.setId(4);
    p.setName("ff");
    p.setPrice(3.25);

    var content = WebServiceUtilities.makeStringContent(p, MediaType.APP_JSON);
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.putWithResponse("/api/XML_JSON/Put/1", MediaType.APP_JSON, content, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /// <summary>
  /// XML string verify status code
  /// </summary>
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putXMLSerializedVerifyStatusCode()
      throws URISyntaxException, IOException, InterruptedException {
    Product p = new Product();
    p.setCategory("ff");
    p.setId(4);
    p.setName("ff");
    p.setPrice(3.25);

    var content = WebServiceUtilities.makeStringContent(p, MediaType.APP_XML);
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.putWithResponse("/api/XML_JSON/Put/1", MediaType.APP_XML, content, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /// <summary>
  /// XML stream verify status code
  /// </summary>
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putXMLStreamSerializedVerifyStatusCode()
      throws URISyntaxException, IOException, InterruptedException {
    Product p = new Product();
    p.setCategory("ff");
    p.setId(4);
    p.setName("ff");
    p.setPrice(3.25);

    var content = WebServiceUtilities.makeStringContent(p, MediaType.APP_XML);
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.putWithResponse("/api/XML_JSON/Put/1",
        MediaType.APP_XML, content, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /// <summary>
  /// Verify put returns an empty string
  /// </summary>
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putXMLSerializedVerifyEmptyString()
      throws URISyntaxException, IOException, InterruptedException {
    Product p = new Product();
    p.setCategory("ff");
    p.setId(4);
    p.setName("ff");
    p.setPrice(3.25);

    var content = WebServiceUtilities.makeStringContent(p, MediaType.APP_XML);
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.put("/api/XML_JSON/Put/1", MediaType.APP_XML, content,true);
    Assert.assertEquals(result, "");
  }

  /// <summary>
  /// String without using the utility
  /// </summary>
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putStringWithoutMakeContent()
      throws URISyntaxException, IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.put("/api/String/Put/1", MediaType.PLAIN_TEXT, "Test",
        MediaType.PLAIN_TEXT, true, true);
    Assert.assertEquals(result, "");
  }

  /// <summary>
  /// Stream without using the utility
  /// </summary>
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putStreamWithoutMakeContent()
      throws URISyntaxException, IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.put("/api/String/Put/1", MediaType.PLAIN_TEXT,
        "Test", MediaType.PLAIN_TEXT, false, true);
    Assert.assertEquals(result, "");
  }

  /// <summary>
  /// String using the utility
  /// </summary>
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putStringWithMakeStringContent()
      throws URISyntaxException, IOException, InterruptedException {
    var content = WebServiceUtilities.makeStringContent("Test", MediaType.PLAIN_TEXT);
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.put("/api/String/Put/1", MediaType.PLAIN_TEXT, content, true);
    Assert.assertEquals(result, "");
  }

  /// <summary>
  /// Stream using the utility
  /// </summary>
  @Test(groups = TestCategories.WEB_SERVICE)
  public void PutStringWithMakeStreamContent()
  {
    StreamContent content = WebServiceUtils.MakeStreamContent("Test", Encoding.UTF8, "text/plain");
    var result = this.WebServiceDriver.Put("/api/String/Put/1", MediaType.PLAIN_TEXT, content, true);
    Assert.AreEqual(string.Empty, result);
  }

  /// <summary>
  /// Make stream content with a stream
  /// </summary>
  @Test(groups = TestCategories.WEB_SERVICE)
  public void PutStreamWithMakeStreamContent() {
    MemoryStream stream = new MemoryStream();
    StreamWriter writer = new StreamWriter(stream, Encoding.UTF8);
    writer.Write("TestStream");
    writer.Flush();
    stream.Position = 0;

    StreamContent content = WebServiceUtils.MakeStreamContent(stream, "text/plain");
    var result = this.WebServiceDriver.Put("/api/String/Put/1", "text/plain", content, true);
    Assert.AreEqual(string.Empty, result);
  }

  /// <summary>
  /// Put string without utility
  /// </summary>
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putStringWithoutContentStatusCode()
      throws URISyntaxException, IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.putWithResponse("/api/String/Put/1", MediaType.PLAIN_TEXT,
        "Test", MediaType.PLAIN_TEXT, true, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /// <summary>
  /// Put string with utility
  /// </summary>
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putStringMakeContentStatusCode()
      throws URISyntaxException, IOException, InterruptedException {
    var content = WebServiceUtilities.makeStringContent("Test", MediaType.PLAIN_TEXT);
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.putWithResponse("/api/String/Put/1", MediaType.PLAIN_TEXT, content, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /// <summary>
  /// Test other status codes
  /// </summary>
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putExpectContentError() throws URISyntaxException, IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.putWithResponse("/api/String/Put/1", MediaType.PLAIN_TEXT,
        null, false);
    Assert.assertEquals(result.statusCode(), HttpStatus.CONFLICT.value());
  }

  /// <summary>
  /// Test string response
  /// </summary>
  @Test(groups = TestCategories.WEB_SERVICE)
  public void putExpectStringError() throws URISyntaxException, IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.put("/api/String/Put/1", MediaType.PLAIN_TEXT, "", false);
    Assert.assertEquals("{\"Message\":\"No Product found for name = 1 \"}", result);
  }
}
