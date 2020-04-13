/*
 * Copyright 2020 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices.jdk11;

import com.magenic.jmaqs.webservices.jdk8.MediaType;
import com.magenic.jmaqs.webservices.jdk8.WebServiceConfig;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Http request factory.
 */
public class HttpRequestFactory {
  /**
   * class constructor.
   */
  private HttpRequestFactory() { }

  /**
   * Gets a default TTP client based on configuration values.
   * @return A HTTP client
   */
  public static HttpRequest getDefaultRequest() {
    return getRequest(WebServiceConfig.getWebServiceUri());
  }

  /**
   * Gets a HTTP client based on configuration values.
   * @param baseUri Base service uri
   * @return A HTTP client
   */
  public static HttpRequest getRequest(String baseUri) {
    return getRequest(WebServiceConfig.getWebServiceUri(), baseUri);
  }

  /**
   * Gets a HTTP client based on configuration values.
   * @param baseAddress the base url address
   * @param baseUri Base service uri
   * @return a HTTP Request
   */
  public static HttpRequest getRequest(String baseAddress, String baseUri) {
    return getRequest(baseAddress, baseUri, WebServiceConfig.getWebServiceTimeOut());
  }

  /**
   * Gets a HTTP client based on configuration values.
   * @param baseUri Base service uri
   * @param mediaType the media type being used
   * @return a HTTP Request
   */
  public static HttpRequest getRequest(String baseUri, MediaType mediaType) {
    return getRequest(WebServiceConfig.getWebServiceUri(), baseUri, WebServiceConfig.getWebServiceTimeOut(), mediaType);
  }

  /**
   * Gets a HTTP Request based on configuration values.
   * @param baseAddress the base url address
   * @param baseUri Base service uri
   * @param timeout Web service timeout
   * @return a HTTP Request
   */
  public static HttpRequest getRequest(String baseAddress, String baseUri, int timeout) {
    return getRequest(baseAddress, baseUri, timeout, MediaType.APP_JSON);
  }


  public static HttpRequest getRequest(String baseUri, MediaType returnMediaType, RequestMethod requestType) {
    return setUpRequest(WebServiceConfig.getWebServiceUri(), baseUri,
        WebServiceConfig.getWebServiceTimeOut(), returnMediaType, "", requestType);
  }

  public static HttpRequest getRequest(String baseUri, MediaType returnMediaType, RequestMethod requestType, List<String> header) {
    return setUpRequest(WebServiceConfig.getWebServiceUri(), baseUri,
        WebServiceConfig.getWebServiceTimeOut(), returnMediaType, "", header, requestType);
  }

  /**
   * Gets a HTTP Request based on configuration values.
   * @param baseAddress the base website address
   * @param baseUri Base service uri
   * @param timeout Web service timeout
   * @param mediaType the media type being used
   * @return a HTTP Request
   */
  public static HttpRequest getRequest(String baseAddress, String baseUri, int timeout, MediaType mediaType) {
    return setUpRequest(baseAddress, baseUri, timeout, mediaType, "", RequestMethod.GET);
  }

  /**
   * Gets a HTTP Request based on configuration values.
   * @param baseAddress the base website address
   * @param baseUri Base service uri
   * @param timeout Web service timeout
   * @param mediaType the media type being used
   * @return a HTTP Request
   */
  public static HttpRequest getRequest(String baseAddress, String baseUri, int timeout, MediaType mediaType, List<String> header) {
    return setUpRequest(baseAddress, baseUri, timeout, mediaType, "", header, RequestMethod.GET);
  }

  /**
   * Gets a HTTP Request based on configuration values.
   * @param baseAddress the base url address
   * @param baseUri Base service uri
   * @param timeout Web service timeout
   * @param mediaType media/content type to be received
   * @param content the content to be used in a POST/PUT/PATCH
   * @param requestType the type of request to be done
   * @return A HTTP Request
   */
  public static HttpRequest setUpRequest(String baseAddress, String baseUri, int timeout,
      MediaType mediaType, String content, RequestMethod requestType) {
    HttpRequest.Builder builder = HttpRequest.newBuilder()
        .uri(URI.create(baseAddress + baseUri))
        .timeout(Duration.ofSeconds(timeout))
        .version(HttpClient.Version.HTTP_2)
        .header("Content-Type", mediaType.toString());
   return doRequest(builder, content, requestType);
  }

  /**
   * Gets a HTTP Request based on configuration values.
   * @param baseAddress the base url address
   * @param baseUri Base service uri
   * @param timeout Web service timeout
   * @param mediaType media/content type to be received
   * @param content the content to be used in a POST/PUT/PATCH
   * @param requestType the type of request to be done
   * @return A HTTP Request
   */
  public static HttpRequest setUpRequest(String baseAddress, String baseUri, int timeout,
      MediaType mediaType, String content, List<String> header, RequestMethod requestType) {
    HttpRequest.Builder builder = HttpRequest.newBuilder()
        .uri(URI.create(baseAddress + baseUri))
        .timeout(Duration.ofSeconds(timeout))
        .version(HttpClient.Version.HTTP_2)
        .setHeader(header.get(0), header.get(1))
        .header("Content-Type", mediaType.toString());
    return doRequest(builder, content, requestType);
  }

  /**
   * Runs the web service request.
   * @param builder the Http Request builder to be built
   * @param content the content to be used in a POST/PUT/PATCH
   * @param requestType the type of request to be done
   * @return A HTTP Request
   */
  private static HttpRequest doRequest(HttpRequest.Builder builder, String content, RequestMethod requestType) {
    if (requestType.equals(RequestMethod.POST)) {
      return builder.POST(HttpRequest.BodyPublishers.ofString(content)).build();
    } else if (requestType.equals(RequestMethod.PUT)) {
      return builder.PUT(HttpRequest.BodyPublishers.ofString(content)).build();
    } else if (requestType.equals(RequestMethod.DELETE)) {
      return builder.DELETE().build();
    } else if (requestType.equals(RequestMethod.PATCH)) {
      return builder.method("PATCH", HttpRequest.BodyPublishers.ofString(content)).build();
    }
    return builder.GET().build();
  }
}
