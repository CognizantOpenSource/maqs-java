/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices.baseWebServiceTest;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

/**
 * Web service utilities.
 */
public final class WebServiceUtils {
  /**
   * Create a HTTP entity.
   * 
   * @param contentMessage
   *          The entity content as text
   * @param contentType
   *          The type of content
   * @return An HTTP entity
   */
  public static HttpEntity createEntity(String contentMessage, ContentType contentType) {
    HttpEntity newEntity = new StringEntity(contentMessage, contentType);

    return newEntity;
  }

  /**
   * Get the body from a HTTP response.
   * 
   * @param response
   *          The response
   * @return The response body
   * @throws ParseException
   *           There was an error parsing the response as a string
   * @throws IOException
   *           There was a problem reading the response
   */
  public static String getResponseBody(CloseableHttpResponse response)
      throws ParseException, IOException {
    HttpEntity entity = response.getEntity();
    return EntityUtils.toString(entity);
  }
}
