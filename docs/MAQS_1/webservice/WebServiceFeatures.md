# <img src="resources/MAQS.jpg" height="32" width="32"> Web Service Features

## Overview
MAQS provides support for testing web services.  


## BaseWebServiceTest
BaseWebServiceTest is an abstract test class you can extend.  Extending the class allows you to automatically use MAQS' web service testing capabilities.
```java
public class MyWebServiceTests extends BaseWebServiceTest {}
```

## WebServiceDriver
The WebServiceDriver is an object that allows you to interact with web services.  
This driver wraps common web service interactions, making web service testing relatively easy.  
The driver is also thread safe, which means you can run multiple web service tests in parallel.  
*Information, such as web service base URL is pulled from the MAQS configuration.
```java
HttpResponse<String> response = this.getWebServiceDriver().getContent(
    url + "/api/XML_JSON/GetProduct/1",ContentType.APPLICATION_JSON, true);
Product jsonProduct = WebServiceUtilities.getResponseBody(response, ContentType.APPLICATION_JSON, Product.class);
```
## Log
There is also logger (also thread safe) that can be used to add log message to your log.
```java
this.getTestObject().getLog().logMessage("I am testing with MAQS");
```
## TestObject
The TestObject can be thought of as your test context.  It holds all the MAQS test execution related data. This includes the web service driver, logger, soft asserts, performance timers, plus more.
```java
HttpResponse<String> response = this.getWebServiceDriver().getContent(
    url + "/api/XML_JSON/GetProduct/1",ContentType.APPLICATION_JSON, true);
Product jsonProduct = WebServiceUtilities.getResponseBody(response, ContentType.APPLICATION_JSON, Product.class);
this.getTestObject().getLog().logMessage("I am testing with MAQS");
```
*Notes:*  
* *Most of the test object objects are already accessible on the test lever. For example **this.Log** and **this.TestObject.Log** both access the same logger.*
* *You seldom what you use the test object directly.  It is usually only used when you want to share your test MAQS context with another piece of code*

## Sample code
```java
package com.cognizantsoftvision.maqs.webservices;

import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import com.cognizantsoftvision.maqs.webservices.BaseWebServiceTest;
import com.cognizantsoftvision.maqs.webservices.HttpClientFactory;
import com.cognizantsoftvision.maqs.webservices.MediaType;
import com.cognizantsoftvision.maqs.webservices.WebServiceConfig;
import com.cognizantsoftvision.maqs.webservices.WebServiceDriver;
import com.cognizantsoftvision.maqs.webservices.WebServiceUtilities;
import com.cognizantsoftvision.maqs.webservices.models.Product;
import java.io.IOException;
import java.net.http.HttpResponse;
import org.springframework.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Web service wrapper unit tests.
 */
public class WebServiceUnitTest extends BaseWebServiceTest {

  /**
   * String to hold the URL.
   */
  private static final String baseUrl = WebServiceConfig.getWebServiceUri();
  
  /**
   * Test Json Get deserialize a single product.
   * @throws IOException if exception is thrown
   * @throws InterruptedException if exception is thrown
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void getProductJsonDeserialize() throws IOException, InterruptedException {
    HttpResponse<String> response = webServiceDriver.get(
        baseUrl + "/api/XML_JSON/GetProduct/2", MediaType.APP_JSON, false);
    Product products = WebServiceUtilities.getResponseBody(response, MediaType.APP_JSON, Product.class);
    Assert.assertEquals(products.getName(),"Yo-yo", "Expected 3 products to be returned");
  }
}
```