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
 * Test web service posts.
 */
public class WebServiceDriverPostUnitTest {
  /**
   * Post JSON request to verify status codes.
   * @throws IOException if the exception is thrown
   * @throws URISyntaxException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postJSONSerializedVerifyStatusCode()
      throws IOException, URISyntaxException, InterruptedException {
    Product p = new Product();
    p.setCategory("ff");
    p.setId(4);
    p.setName("ff");
    p.setPrice(3.25);

    var content = WebServiceUtilities.makeStringContent(p, MediaType.APP_JSON);
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.postWithResponse("/api/XML_JSON/Post",
        MediaType.APP_JSON, content, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * Post JSON stream request to verify status codes.
   * @throws IOException if the exception is thrown
   * @throws URISyntaxException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postJSONStreamSerializedVerifyStatusCode()
      throws IOException, URISyntaxException, InterruptedException {
    Product p = new Product();
    p.setCategory("ff");
    p.setId(4);
    p.setName("ff");
    p.setPrice(3.25);

    var content = WebServiceUtilities.makeStringContent(p, MediaType.APP_JSON);
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.postWithResponse("/api/XML_JSON/Post",
        MediaType.APP_JSON, content, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * Post XML request to verify status codes.
   * @throws URISyntaxException if the exception is thrown
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postXMLSerializedVerifyStatusCode()
      throws URISyntaxException, IOException, InterruptedException {
    Product p = new Product();
    p.setCategory("dd");
    p.setId(22);
    p.setName("dd");
    p.setPrice(3.25);

    var content = WebServiceUtilities.makeStringContent(p, MediaType.APP_XML);
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.postWithResponse("/api/XML_JSON/Post",
        MediaType.APP_XML, content, true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * Post with JSON.
   * @throws URISyntaxException if exception is thrown
   * @throws IOException if exception is thrown
   * @throws InterruptedException if exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postWithJson() throws URISyntaxException, IOException, InterruptedException {
    Product p = new Product();
    p.setCategory("ff");
    p.setId(4);
    p.setName("ff");
    p.setPrice(3.25);

    var content = WebServiceUtilities.makeStringContent(p, MediaType.APP_JSON);
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.post("/api/XML_JSON/Post", MediaType.APP_JSON, content, true);
    Assert.assertTrue(result.isEmpty());
  }

  /**
   * Post with JSON.
   * @throws URISyntaxException if exception is thrown
   * @throws IOException if exception is thrown
   * @throws InterruptedException if exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postWithXml() throws URISyntaxException, IOException, InterruptedException {
    Product p = new Product();
    p.setCategory("ss");
    p.setId(17);
    p.setName("ss");
    p.setPrice(3.25);

    var content = WebServiceUtilities.makeStringContent(p, MediaType.APP_XML);
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.post("/api/XML_JSON/Post", MediaType.APP_XML, content, true);
    Assert.assertTrue(result.isEmpty());
  }

  /**
   * Post XML to verify no string is returned.
   * @throws IOException if the exception is thrown
   * @throws URISyntaxException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postXMLSerializedVerifyEmptyStringError()
      throws IOException, URISyntaxException, InterruptedException {
    Product p = new Product();
    p.setCategory("ff");
    p.setId(5);
    p.setName("ff");
    p.setPrice(3.25);

    //var content = WebServiceUtilities.makeStringContent(p, MediaType.APP_XML);
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.post("/api/XML_JSON/Post", MediaType.APP_XML, "", false);
    Assert.assertTrue(result.contains("value is required"));
  }

  /**
   * Post string without utility.
   * @throws URISyntaxException if the exception is thrown
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postStringWithoutMakeContent()
      throws URISyntaxException, IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.post("/api/String", MediaType.PLAIN_TEXT, "Test", true);
    Assert.assertEquals("", result);
  }

  /**
   * Post stream without utility.
   * @throws URISyntaxException if the exception is thrown
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postStreamWithoutMakeContent()
      throws URISyntaxException, IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.post("/api/String", MediaType.PLAIN_TEXT, "Test", true);
    Assert.assertEquals("", result);
  }

  /**
   * Post string with utility.
   * @throws IOException if the exception is thrown
   * @throws URISyntaxException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postStringWithMakeContent()
      throws IOException, URISyntaxException, InterruptedException {
    var content = WebServiceUtilities.makeStringContent("Test", MediaType.PLAIN_TEXT);
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.post("/api/String", MediaType.PLAIN_TEXT, content, true);
    Assert.assertEquals("", result);
  }

  /**
   * Post string without utility to verify status code.
   * @throws URISyntaxException if the exception is thrown
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postStringWithoutContentStatusCode()
      throws URISyntaxException, IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.postWithResponse("/api/String",
        MediaType.PLAIN_TEXT, "Test", true);
    Assert.assertEquals(result.statusCode(), HttpStatus.OK.value());
  }

  /**
   * Verifying other http status codes.
   * @throws URISyntaxException if the exception is thrown
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postExpectContentError() throws URISyntaxException, IOException,
      InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.postWithResponse("/api/String",
        MediaType.PLAIN_TEXT, "", false);
    Assert.assertEquals(result.statusCode(), HttpStatus.BAD_REQUEST.value());
  }

  /**
   * Testing string returned.
   * @throws URISyntaxException if the exception is thrown
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postExpectStringError() throws URISyntaxException, IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.post("/api/String", MediaType.PLAIN_TEXT, "", false);
    Assert.assertEquals("{\"Message\":\"No data\"}", result);
  }

  /**
   * Testing string returned.
   * @throws URISyntaxException if the exception is thrown
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void postExpectStringErrorEmptyHttpContent()
      throws URISyntaxException, IOException, InterruptedException {
    WebServiceDriver webServiceDriver = new WebServiceDriver(WebServiceConfig.getWebServiceUri());
    var result = webServiceDriver.post("/api/String", MediaType.PLAIN_TEXT, "", false);
    Assert.assertEquals("{\"Message\":\"No data\"}", result);
  }
}
