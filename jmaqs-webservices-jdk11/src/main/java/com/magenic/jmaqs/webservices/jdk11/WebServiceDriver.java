/*
 * Copyright 2020 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices.jdk11;

import com.magenic.jmaqs.webservices.jdk8.MediaType;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The Web Service Driver.
 */
public class WebServiceDriver {
  /**
   * The base HTTP client control.
   */
  private HttpClient baseHttpClient;

  /**
   * the base HTTP request control.
   */
  private HttpRequest baseHttpRequest;

  /**
   * The URI to be stored for the Web Service.
   */
  private URI baseAddress;

  /**
   * Class Constructor that sets the http Client.
   * @param newHttpClient the http client to be set.
   */
  public WebServiceDriver(HttpClient newHttpClient) {
    this.baseHttpClient = newHttpClient;
  }

  /**
   * Class constructor that sets the HttpRequest.
   * @param newHttpRequest the new Http request to be set
   */
  public WebServiceDriver(HttpRequest newHttpRequest) {
    this.baseHttpClient = HttpClientFactory.getDefaultClient();
    this.baseHttpRequest = newHttpRequest;
  }

  /**
   * Class Constructor that sets the base address as a URI.
   * @param newBaseAddress The base URI address to use
   */
  public WebServiceDriver(URI newBaseAddress) {
    this.baseHttpClient = HttpClientFactory.getDefaultClient();
    this.baseHttpRequest = HttpRequestFactory.getRequest(newBaseAddress.toString());
  }

  /**
   * Class Constructor that sets the base address as a string.
   * @param newBaseAddress The base URI address to use
   * @throws URISyntaxException URI syntax is invalid
   */
  public WebServiceDriver(String newBaseAddress) throws URISyntaxException {
    this(new URI(newBaseAddress));
  }

  /**
   * Sets http client.
   * @param httpClient the http client
   */
  public void setHttpClient(HttpClient httpClient) {
    this.baseHttpClient = httpClient;
  }

  /**
   * Gets http client.
   * @param mediaType the media type
   * @return the http client
   */
  public HttpClient getHttpClient(MediaType mediaType) {
    return this.baseHttpClient;
  }

  /**
   * sets the Http Request.
   * @param httpRequest the new http request to be set
   */
  public void setHttpRequest(HttpRequest httpRequest) {
    this.baseHttpRequest = httpRequest;
  }

  /**
   * gets the http request.
   * @return the http request
   */
  public HttpRequest getHttpRequest() {
    return this.baseHttpRequest;
  }

  /// <summary>
  ///
  /// </summary>
  /// <typeparam name="T">The expected response type</typeparam>
  /// <param name="requestUri"></param>
  /// <param name="expectedMediaType"></param>
  /// <param name="content"></param>
  /// <param name="expectSuccess"></param>
  /// <returns></returns>

  /**
   * Execute a web service put.
   * @param requestUri The request uri
   * @param expectedMediaType The type of media being requested
   * @param content The put content
   * @param type the
   * @param expectSuccess Assert a success code was returned
   * @return The response to deserialize as the Http Response
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  public HttpResponse<String> put(String requestUri, MediaType expectedMediaType, String content,
      Type type, boolean expectSuccess) throws IOException, InterruptedException {
    HttpResponse<String> response = this.putWithResponse(requestUri, expectedMediaType, content, expectSuccess);
    return WebServiceUtilities.deserializeResponse(response, expectedMediaType, type);
  }

  /// <summary>
  /// Execute a web service put
  /// </summary>
  /// <typeparam name="T">The expected response type</typeparam>
  /// <param name="requestUri">The request uri</param>
  /// <param name="expectedMediaType">The type of media being requested</param>
  /// <param name="content">The put content</param>
  /// <param name="expectedStatus">Assert a specific status code was returned</param>
  /// <returns>The response deserialized as - <typeparamref name="T"/></returns>
  public HttpResponse<String> put(String requestUri, MediaType expectedMediaType, String content,
      Type type,  HttpStatus expectedStatus) throws IOException, InterruptedException {
    HttpResponse<String> response = this.putWithResponse(requestUri, expectedMediaType, content, expectedStatus);
    return WebServiceUtilities.deserializeResponse(response, expectedMediaType, type);
  }

  /// <summary>
  /// Execute a web service put
  /// </summary>
  /// <param name="requestUri">The request uri</param>
  /// <param name="expectedMediaType">The type of media being requested</param>
  /// <param name="content">The put content</param>
  /// <param name="expectSuccess">Assert a success code was returned</param>
  /// <returns>The response body as a string</returns>
  public String put(String requestUri, MediaType expectedMediaType,
      String content, boolean expectSuccess) throws IOException, InterruptedException {
    HttpResponse<String> response = this.putWithResponse(requestUri, expectedMediaType, content, expectSuccess);
    return response.body();
  }

  /// <summary>
  /// Execute a web service put
  /// </summary>
  /// <param name="requestUri">The request uri</param>
  /// <param name="expectedMediaType">The type of media being requested</param>
  /// <param name="content">The put content</param>
  /// <param name="contentEncoding">How to encode the put content</param>
  /// <param name="postMediaType">The type of the media being put</param>
  /// <param name="contentAsString">If true pass content as StringContent, else pass as StreamContent</param>
  /// <param name="expectSuccess">Assert a success code was returned</param>
  /// <returns>The response body as a string</returns>
  public String put(String requestUri, MediaType expectedMediaType, String content, MediaType postMediaType,
      boolean contentAsString, boolean expectSuccess) throws IOException, InterruptedException {
    HttpResponse<String> response = this.putWithResponse(requestUri, expectedMediaType,
        content, postMediaType, contentAsString, expectSuccess);
    return response.body();
  }

  /// <summary>
  /// Execute a web service put
  /// </summary>
  /// <param name="requestUri">The request uri</param>
  /// <param name="expectedMediaType">The type of media being requested</param>
  /// <param name="content">The put content</param>
  /// <param name="contentEncoding">How to encode the put content</param>
  /// <param name="postMediaType">The type of the media being put</param>
  /// <param name="expectedStatus">Assert a specific status code was returned</param>
  /// <param name="contentAsString">If true pass content as StringContent, else pass as StreamContent</param>
  /// <returns>The response body as a string</returns>
  public String put(String requestUri, MediaType expectedMediaType, String content, MediaType postMediaType,
      HttpStatus expectedStatus, boolean contentAsString) throws IOException, InterruptedException {
    HttpResponse<String> response = this.putWithResponse(requestUri, expectedMediaType,
        content, postMediaType, expectedStatus, contentAsString);
    return response.body();
  }

  /// <summary>
  /// Execute a web service put
  /// </summary>
  /// <param name="requestUri">The request uri</param>
  /// <param name="expectedMediaType">The type of media being requested</param>
  /// <param name="content">The put content</param>
  /// <param name="contentEncoding">How to encode the put content</param>
  /// <param name="postMediaType">The type of the media being put</param>
  /// <param name="contentAsString">If true pass content as StringContent, else pass as StreamContent</param>
  /// <param name="expectSuccess">Assert a success code was returned</param>
  /// <returns>The http response message</returns>
  public HttpResponse<String> putWithResponse(String requestUri, MediaType expectedMediaType,
      Object content, MediaType postMediaType, boolean contentAsString, boolean expectSuccess)
      throws IOException, InterruptedException {
    String httpContent = createContent(content, postMediaType, contentAsString);
    return this.putWithResponse(requestUri, expectedMediaType, httpContent, expectSuccess);
  }

  /// <summary>
  /// Execute a web service put
  /// </summary>
  /// <param name="requestUri">The request uri</param>
  /// <param name="expectedMediaType">The type of media being requested</param>
  /// <param name="content">The put content</param>
  /// <param name="contentEncoding">How to encode the put content</param>
  /// <param name="postMediaType">The type of the media being put</param>
  /// <param name="expectedStatus">Assert a specific status code was returned</param>
  /// <param name="contentAsString">If true pass content as StringContent, else pass as StreamContent</param>
  /// <returns>The http response message</returns>
  public HttpResponse<String> putWithResponse(String requestUri, MediaType expectedMediaType,
      Object content, MediaType postMediaType, HttpStatus expectedStatus, boolean contentAsString)
      throws IOException, InterruptedException {
    String httpContent = createContent(content, postMediaType, contentAsString);
    return this.putWithResponse(requestUri, expectedMediaType, httpContent, expectedStatus);
  }

  /// <summary>
  /// Execute a web service put
  /// </summary>
  /// <param name="requestUri">The request uri</param>
  /// <param name="expectedMediaType">The type of media being requested</param>
  /// <param name="content">The put content</param>
  /// <param name="expectSuccess">Assert a success code was returned</param>
  /// <returns>The http response message</returns>
  public HttpResponse<String> putWithResponse(String requestUri, MediaType expectedMediaType,
      String content, boolean expectSuccess) throws IOException, InterruptedException {
    return this.putContent(requestUri, expectedMediaType, content, expectSuccess);
  }

  /// <summary>
  /// Execute a web service put
  /// </summary>
  /// <param name="requestUri">The request uri</param>
  /// <param name="expectedMediaType">The type of media being requested</param>
  /// <param name="content">The put content</param>
  /// <param name="expectedStatus">Assert a specific status code was returned</param>
  /// <returns>The http response message</returns>
  public HttpResponse<String> putWithResponse(String requestUri, MediaType expectedMediaType, String content,
      HttpStatus expectedStatus) throws IOException, InterruptedException {
    return this.putContent(requestUri, expectedMediaType, content, expectedStatus);
  }

  /// <summary>
  /// Do a web service put for the given uri, content and media type
  /// </summary>
  /// <param name="requestUri">The request uri</param>
  /// <param name="responseMediaType">The response media type</param>
  /// <param name="content">The put body</param>
  /// <param name="expectSuccess">Assert a success code was returned</param>
  /// <returns>A http response message</returns>
  protected HttpResponse<String> putContent(String requestUri, MediaType responseMediaType,
      String content, boolean expectSuccess) throws IOException, InterruptedException {
    this.checkIfMediaTypeNotPresent(responseMediaType.toString());
    setHttpRequest(HttpRequestFactory.getRequest(requestUri, responseMediaType, content, RequestMethod.PUT));
    HttpResponse<String> response = baseHttpClient.send(getHttpRequest(), HttpResponse.BodyHandlers.ofString());

    // Should we check for success
    if (expectSuccess) {
      ensureSuccessStatusCode(response);
    }

    return response;
  }

  /// <summary>
  /// Do a web service put for the given uri, content and media type
  /// </summary>
  /// <param name="requestUri">The request uri</param>
  /// <param name="responseMediaType">The response media type</param>
  /// <param name="content">The put body</param>
  /// <param name="expectedStatus">Assert a specific status code was returned</param>
  /// <returns>A http response message</returns>
  protected HttpResponse<String> putContent(String requestUri, MediaType responseMediaType,
      String content, HttpStatus expectedStatus) throws IOException, InterruptedException {
    this.checkIfMediaTypeNotPresent(responseMediaType.toString());
    setHttpRequest(HttpRequestFactory.getRequest(requestUri, responseMediaType, content, RequestMethod.PUT));
    HttpResponse<String> response = baseHttpClient.send(getHttpRequest(), HttpResponse.BodyHandlers.ofString());

    // We check for specific status
    ensureStatusCodesMatch(response, expectedStatus);

    return response;
  }

  /**
   * Ensure the HTTP response was successful, if not throw a user friendly error message.
   * @param response The HTTP response
   * @throws HttpResponseException if the HttpResponse is null
   */
  private static void ensureSuccessStatusCode(HttpResponse<String> response) throws HttpResponseException {
    // Make sure a response was returned
    if (response == null) {
      throw new HttpResponseException(HttpStatus.SC_NO_CONTENT, "Response was null");
    }

    // Check if it was a success and if not create a user friendly error message
    if (response.statusCode() != HttpStatus.SC_OK) {
      String body = response.body();

      throw new HttpResponseException(response.statusCode(),
          String.format("Response did not indicate a success. %s Response code was: %s",
              System.lineSeparator(), body));
    }
  }

  /**
   * Ensure the HTTP response has specified status, if not throw a user friendly error message.
   * @param response The HTTP response
   * @param expectedStatus Assert a specific status code was returned
   * @throws HttpResponseException if the HttpResponse is null
   */
  private static void ensureStatusCodesMatch(HttpResponse<String> response, HttpStatus expectedStatus)
      throws HttpResponseException {
    // Make sure a response was returned
    if (response == null) {
      throw new HttpResponseException(HttpStatus.SC_NO_CONTENT, "Response was null");
    }

    // Check if it was a success and if not create a user friendly error message
    if (response.statusCode() != expectedStatus.hashCode()) {
      String body = response.body();
      throw new HttpResponseException(response.statusCode(),
          String.format("Response status did not match expected. %s "
                  + "Response code was: %s %s Expected code was: %s %s"
                  + "Body: %s", System.lineSeparator(), response.statusCode(),
              System.lineSeparator(), expectedStatus.hashCode(), System.lineSeparator(), body));
    }
  }

  /**
   * Check if the media type is supported.
   * @param mediaType Media type to add
   */
  private void checkIfMediaTypeNotPresent(String mediaType) {
    // Make sure a media type was passed in
    if (mediaType.isEmpty()) {
      return;
    }

    // Look for the media type
    for (MediaType media : MediaType.values()) {
      if (media.toString().equals(mediaType)) {
        return;
      }
    }

    // Add the type or throw exception???
    throw new UnsupportedOperationException("Media Type is not supported");
  }
}