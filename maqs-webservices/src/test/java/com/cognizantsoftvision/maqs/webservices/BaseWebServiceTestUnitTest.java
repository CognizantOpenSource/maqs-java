/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.webservices;

import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import java.net.URISyntaxException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

/**
 * Unit tests the Base Web Service Test functionality.
 */
public class BaseWebServiceTestUnitTest extends BaseWebServiceTest {

  /**
   * Test getting the web service driver.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void testGetWebServiceDriver() {
    Assert.assertNotNull(this.getWebServiceDriver());
  }

  /**
   * Test getting the web service Test Object.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void testGetWebServiceTestObject() {
    Assert.assertNotNull(this.getTestObject());
  }

  /**
   * Tests setting the web service driver.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void testSetWebServiceDriver() {
    int hashCode = this.getWebServiceDriver().hashCode();
    try {
      this.setWebServiceDriver(this.getWebServiceClient());
    } catch (Exception e) {
      e.printStackTrace();
    }
    int hashCode1 = this.getWebServiceDriver().hashCode();
    Assert.assertNotEquals(hashCode, hashCode1);
  }

  /**
   * Tests getting the web service client.
   * @throws URISyntaxException if a URI syntax error occurs
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void testGetWebServiceClient() throws URISyntaxException {
    Assert.assertNotNull(this.getWebServiceClient());
  }
}