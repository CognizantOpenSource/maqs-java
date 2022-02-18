/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.webservices.WebServiceDriverRequestUnitTest;

import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import com.cognizantsoftvision.maqs.webservices.BaseWebServiceTest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests the web service driver Delete functionality.
 */
public class WebServiceDriverDeleteUnitTest extends BaseWebServiceTest {
  /**
   * Verify delete works.
   *
   * @throws Exception
   *           There was a problem with the test
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void webServiceDelete() throws Exception {
    CloseableHttpResponse response = this.getWebServiceDriver()
        .deleteContent("/api/XML_JSON/Delete/1", ContentType.TEXT_PLAIN, false);
    Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
  }

  /**
   * Verify delete works.
   *
   * @throws Exception
   *           There was a problem with the test
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void webServiceStringDelete() throws Exception {
    CloseableHttpResponse response = this.getWebServiceDriver()
        .deleteContent("/api/String/Delete/1", ContentType.TEXT_PLAIN, false);
    Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
  }

  /**
   * Verify delete throws an error.
   *
   * @throws Exception
   *           There was a problem with the test
   */
  @Test(groups = TestCategories.WEB_SERVICE)
  public void webServiceStringDeleteError() throws Exception {
    CloseableHttpResponse response = this.getWebServiceDriver()
        .deleteContent("/api/String/Delete/", ContentType.TEXT_PLAIN, false);
    Assert.assertEquals(response.getStatusLine().getStatusCode(), 405);
  }
}
