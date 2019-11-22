package com.magenic.jmaqs.webservices;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

import static org.testng.Assert.*;

public class BaseWebServiceTestUnitTest extends BaseWebServiceTest {

  @Test public void testGetWebServiceDriver() {
    Assert.assertNotNull(this.getWebServiceDriver());
  }

  @Test public void testGetWebServiceTestObject() {
    Assert.assertNotNull(this.getTestObject());
  }

  @Test public void testSetWebServiceDriver() {
    int hashCode = this.getWebServiceDriver().hashCode();
    try {
      this.setWebServiceDriver(this.getWebServiceClient());
    } catch (Exception e) {
      e.printStackTrace();
    }
    int hashCode1 = this.getWebServiceDriver().hashCode();
    Assert.assertNotEquals(hashCode, hashCode1);
  }

  @Test public void testGetWebServiceClient() throws URISyntaxException {
    Assert.assertNotNull(this.getWebServiceClient());
  }
}