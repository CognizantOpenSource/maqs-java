package com.cognizantsoftvision.maqs.webservices.soap;

import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The Base Soap Web Service unit test class.
 */
public class BaseSoapWebServiceUnitTest extends BaseSoapWebServiceTest {

  /**
   * Test getting the web service driver.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void testGetSoapWebServiceDriver() {
    Assert.assertNotNull(this.getWebServiceDriver());
  }

  /**
   * Test getting the web service Test Object.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void testGetSoapWebServiceTestObject() {
    Assert.assertNotNull(this.getTestObject());
  }

  /**
   * Tests setting the web service driver.
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void testSetSoapWebServiceDriver() {
    int hashCode = this.getWebServiceDriver().hashCode();

    try {
      this.setWebServiceDriver(this.getWebServiceClient());
    } catch (Exception e) {
      e.printStackTrace();
    }
    int hashCode1 = this.getWebServiceDriver().hashCode();
    Assert.assertNotEquals(hashCode, hashCode1);
  }
}
