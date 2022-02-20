/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.webservices;

import com.cognizantsoftvision.maqs.base.BaseGenericTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit tests for Media Type functionality.
 */
public class MediaTypeUnitTest extends BaseGenericTest {
  /**
   * Tests the string value of App_Json
   */
  @Test
  public void testTestToStringAppJson() {
    Assert.assertEquals(MediaType.APP_JSON.toString(), MediaType.APP_JSON.getMediaTypeString());
  }

  /**
   * Tests the string value of App_Xml
   */
  @Test
  public void testTestToStringAppXml() {
    Assert.assertEquals(MediaType.APP_XML.toString(), MediaType.APP_XML.getMediaTypeString());
  }

  /**
   * Tests the string value of Plain_Text
   */
  @Test
  public void testTestToStringPlainText() {
    Assert.assertEquals(MediaType.PLAIN_TEXT.toString(), MediaType.PLAIN_TEXT.getMediaTypeString());
  }

  /**
   * Test the string value of Image_Png
   */
  @Test
  public void testTestToStringImagePng() {
    Assert.assertEquals(MediaType.IMAGE_PNG.toString(), MediaType.IMAGE_PNG.getMediaTypeString());
  }
}