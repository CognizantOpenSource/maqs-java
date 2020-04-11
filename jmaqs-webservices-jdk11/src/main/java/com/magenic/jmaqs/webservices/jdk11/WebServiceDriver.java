/*
 * Copyright 2020 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices.jdk11;

import com.magenic.jmaqs.webservices.jdk8.MediaType;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.magenic.jmaqs.webservices.jdk8.WebServiceUtilities;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.NotAcceptableStatusException;

import javax.print.attribute.standard.Media;

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
  /// Execute a web service delete
  /// </summary>
  /// <typeparam name="T">The expected response type</typeparam>
  /// <param name="requestUri">The request uri</param>
  /// <param name="expectedMediaType">The type of media being requested</param>
  /// <param name="expectSuccess">Assert a success code was returned</param>
  /// <returns>The response by deserialized as the <typeparamref name="T"/></returns>
  public <T> T delete(String requestUri, MediaType expectedMediaType, boolean expectSuccess, boolean getDeserializeResponse)
      throws IOException, InterruptedException {
    HttpResponse<String> response = this.deleteWithResponse(requestUri, expectedMediaType, expectSuccess);
    return WebServiceUtilities.deserializeResponse(response, this.supportedFormatters);
  }

  /// <summary>
  /// Execute a web service delete
  /// </summary>
  /// <typeparam name="T">The expected response type</typeparam>
  /// <param name="requestUri">The request uri</param>
  /// <param name="expectedMediaType">The type of media being requested</param>
  /// <param name="expectedStatus">Assert a specific status code was returned</param>
  /// <returns>The response by deserialized as the <typeparamref name="T"/></returns>
  public <T> T delete(String requestUri, MediaType expectedMediaType, HttpStatus expectedStatus, boolean getDeserializeResponse)
      throws IOException, InterruptedException {
    HttpResponse<String> response = this.deleteWithResponse(requestUri, expectedMediaType, expectedStatus);
    return WebServiceUtilities.deserializeResponse(response, this.supportedFormatters);
  }

  /// <summary>
  /// Execute a web service delete
  /// </summary>
  /// <param name="requestUri">The request uri</param>
  /// <param name="expectedMediaType">The type of media being requested</param>
  /// <param name="expectSuccess">Assert a success code was returned</param>
  /// <returns>The response as a string</returns>
  public String delete(String requestUri, MediaType expectedMediaType, boolean expectSuccess)
      throws IOException, InterruptedException {
    HttpResponse<String> response = this.deleteWithResponse(requestUri, expectedMediaType, expectSuccess);
    return response.toString();
  }

  /// <summary>
  /// Execute a web service delete
  /// </summary>
  /// <param name="requestUri">The request uri</param>
  /// <param name="expectedMediaType">The type of media being requested</param>
  /// <param name="expectedStatus">Assert a specific status code was returned</param>
  /// <returns>The response as a string</returns>
  public String delete(String requestUri, MediaType expectedMediaType, HttpStatus expectedStatus)
      throws IOException, InterruptedException {
    HttpResponse<String> response = deleteWithResponse(requestUri, expectedMediaType, expectedStatus);
    return response.toString();
  }

  /// <summary>
  /// Execute a web service delete
  /// </summary>
  /// <param name="requestUri">The request uri</param>
  /// <param name="expectedMediaType">The type of media being requested</param>
  /// <param name="expectSuccess">Assert a success code was returned</param>
  /// <returns>The http response message</returns>
  public HttpResponse<String> deleteWithResponse(String requestUri, MediaType expectedMediaType, boolean expectSuccess)
      throws IOException, InterruptedException {
    return this.deleteContent(requestUri, expectedMediaType, expectSuccess);
  }

  /// <summary>
  /// Execute a web service delete
  /// </summary>
  /// <param name="requestUri">The request uri</param>
  /// <param name="expectedMediaType">The type of media being requested</param>
  /// <param name="expectedStatus">Assert a specific status code was returned</param>
  /// <returns>The http response message</returns>
  public HttpResponse<String> deleteWithResponse(String requestUri, MediaType expectedMediaType, HttpStatus expectedStatus)
      throws IOException, InterruptedException {
    return this.deleteContent(requestUri, expectedMediaType, expectedStatus);
  }

  /// <summary>
  /// Do a web service delete for the given uri
  /// </summary>
  /// <param name="requestUri">The request uri</param>
  /// <param name="returnMediaType">The expected response media type</param>
  /// <param name="expectSuccess">Assert a success code was returned</param>
  /// <returns>A http response message</returns>
  protected HttpResponse<String> deleteContent(String requestUri, MediaType returnMediaType, boolean expectSuccess)
      throws IOException, InterruptedException {
    setHttpRequest(HttpRequestFactory.getRequest(requestUri, returnMediaType));
    HttpResponse<String> response = baseHttpClient.send(getHttpRequest(), HttpResponse.BodyHandlers.ofString());

    // Should we check for success
    if (expectSuccess) {
      ensureSuccessStatusCode(response);
    }
    return response;
  }

  /// <summary>
  /// Do a web service delete for the given uri
  /// </summary>
  /// <param name="requestUri">The request uri</param>
  /// <param name="returnMediaType">The expected response media type</param>
  /// <param name="expectedStatus">Assert a specific status code was returned</param>
  /// <returns>A http response message</returns>
  protected HttpResponse<String> deleteContent(String requestUri, MediaType returnMediaType, HttpStatus expectedStatus)
      throws IOException, InterruptedException {
    setHttpRequest(HttpRequestFactory.getRequest(requestUri, returnMediaType));
    HttpResponse<String> response = baseHttpClient.send(getHttpRequest(), HttpResponse.BodyHandlers.ofString());

    // We check for specific status
    ensureStatusCodesMatch(response, expectedStatus);
    return response;
  }

  /**
   * Ensure the HTTP response was successful, if not throw a user friendly error message.
   * @param response The HTTP response
   */
  public static void ensureSuccessStatusCode(HttpResponse<String> response) {
    // Make sure a response was returned
    if (response == null) {
      throw new NullPointerException(HttpStatus.NO_CONTENT.toString() + " Response was null");
    }

    // Check if it was a success and if not create a user friendly error message
    if (response.statusCode() != HttpStatus.OK.value()) {
      throw new NotAcceptableStatusException(
          String.format("Response did not indicate a success. %s Response code was: %s",
              System.lineSeparator(), response.statusCode()));
    }
  }

  /**
   * Ensure the HTTP response has specified status, if not throw a user friendly error message.
   * @param response The HTTP response
   * @param expectedStatus Assert a specific status code was returned
   */
  public static void ensureStatusCodesMatch(HttpResponse<String> response, HttpStatus expectedStatus) {
    // Make sure a response was returned
    if (response == null) {
      throw new NullPointerException(HttpStatus.NO_CONTENT.toString() + " Response was null");
    }

    // Check if it was a success and if not create a user friendly error message
    if (response.statusCode() != expectedStatus.hashCode()) {
      String body = response.body();
      throw new NotAcceptableStatusException(String.format("Response status did not match expected. %s "
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
