/*
 * Copyright 2019 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.magenic.jmaqs.utilities.helper.StringProcessor;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

/**
 * Web service utilities.
 */
public final class WebServiceUtilities {
  private static ObjectMapper objectMapper = new ObjectMapper();
  private static ObjectMapper xmlMapper = new XmlMapper();

  // private constructor
  private WebServiceUtilities() { }

  /**
   * Create a HTTP entity.
   * 
   * @param contentMessage The entity content as text
   * @param contentType The type of content
   * @return An HTTP entity
   */
  @Deprecated
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

  /**
   * Create the string entity
   * @param body The body being set in the entity
   * @param contentType  The content type of the message
   * @return The string entity
   */
  public static StringEntity createStringEntity(String body, ContentType contentType) {
    return new StringEntity(body, contentType);
  }

  /**
   * Create the string entity
   * @param body The body being set in the entity
   * @param encoding The charset encoding of the message
   * @param mediaType The mime type of the message
   * @return The string entity
   */
  public static StringEntity createStringEntity(String body, Charset encoding, String mediaType) {
    ContentType contentType = ContentType.create(mediaType, encoding);
    return createStringEntity(body, contentType);
  }

  /**
   * Creates the string content
   * @param body The body being set in the entity
   * @param contentType The content type of the
   * @param <T> The type of the body
   * @return The string entity
   * @throws JsonProcessingException If the json or xml failed to deserialize
   */
  public static <T> StringEntity createStringEntity(T body, ContentType contentType) throws JsonProcessingException {
    if (contentType.toString().toUpperCase().contains("XML")) {
      return new StringEntity(getXmlObjectAsString(body), contentType);
    }
    else if (contentType.toString().toUpperCase().contains("JSON")) {
      return new StringEntity(getJsonObjectAsString(body), contentType);
    }
    else {
      throw new IllegalArgumentException(
              StringProcessor.safeFormatter("Only xml and json conversions are currently supported"));
    }
  }

  /**
   * Gets the json object as a string
   * @param body The body being serialized to a string
   * @param <T> The type of the body
   * @return The body as a json string
   * @throws JsonProcessingException If the json serialization fails
   */
  public static <T> String getJsonObjectAsString(T body) throws JsonProcessingException {
    String json = objectMapper.writeValueAsString(body);
    return json;
  }

  /**
   * Gets the xml object as a string
   * @param body The body being serialized to a string
   * @param <T> The type of the body
   * @return The body as an xml string
   * @throws JsonProcessingException If the xml serialization fails
   */
  public static <T> String getXmlObjectAsString(T body) throws JsonProcessingException {
    String xml = xmlMapper.writeValueAsString(body);
    return xml;
  }

  /**
   * Deserialzizes the response to the object specified
   * @param message The httpresponse message being deserialized
   * @param <T> The type the message will be deserialized to
   * @return The json as the type specified
   * @throws IOException If the object can't be written to that type
   */
  public static <T> T deserializeJson(CloseableHttpResponse message, Type type) throws IOException {
    String responseEntity = getResponseBody(message);
    T jsonObject = objectMapper.readValue(responseEntity, objectMapper.getTypeFactory().constructType(type));
    return jsonObject;
  }

  /**
   * Deserializes the response to the object specified
   * @param message The http response message being deserialized
   * @param <T> The tyep the message will be deserialied to
   * @return The json as the type specified
   * @throws IOException If the object can't be written to that type
   */
  public static <T> T deserializeXml(CloseableHttpResponse message, Type type) throws IOException {
    String responseEntity = getResponseBody(message);
    T xmlObject = xmlMapper.readValue(responseEntity, xmlMapper.getTypeFactory().constructType(type));
    return xmlObject;
  }
}
