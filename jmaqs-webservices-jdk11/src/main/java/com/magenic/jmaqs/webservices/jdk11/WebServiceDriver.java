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
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.NotAcceptableStatusException;

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

  /**
   * Execute a web service delete.
   * @param requestUri The request uri
   * @param expectedMediaType The type of media being requested
   * @param type the request type type being done
   * @param expectSuccess Assert a success code was returned
   * @param <T> The expected response type
   * @return The response by deserialize as the T
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  public <T> T delete(String requestUri, MediaType expectedMediaType, Type type, boolean expectSuccess)
      throws IOException, InterruptedException {
    HttpResponse<String> response = this.deleteWithResponse(requestUri, expectedMediaType, expectSuccess);
    return WebServiceUtilities.deserializeResponse(response, expectedMediaType, type);
  }

  /**
   * Execute a web service delete.
   * @param requestUri The request uri
   * @param expectedMediaType The type of media being requested
   * @param expectedStatus Assert a specific status code was returned
   * @param type the request type type being done
   * @param <T> The expected response type
   * @return The response by deserialize as the T
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  public <T> T delete(String requestUri, MediaType expectedMediaType, HttpStatus expectedStatus, Type type)
      throws IOException, InterruptedException {
    HttpResponse<String> response = this.deleteWithResponse(requestUri, expectedMediaType, expectedStatus);
    return WebServiceUtilities.deserializeResponse(response, expectedMediaType, type);
  }

  /**
   * Execute a web service delete.
   * @param requestUri The request uri
   * @param expectedMediaType The type of media being requested
   * @param expectSuccess Assert a success code was returned
   * @return The response as a string
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  public String delete(String requestUri, MediaType expectedMediaType, boolean expectSuccess)
      throws IOException, InterruptedException {
    HttpResponse<String> response = this.deleteWithResponse(requestUri, expectedMediaType, expectSuccess);
    return response.toString();
  }

  /**
   * Execute a web service delete.
   * @param requestUri The request uri
   * @param expectedMediaType The type of media being requested
   * @param expectedStatus Assert a specific status code was returned
   * @return The response as a string
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  public String delete(String requestUri, MediaType expectedMediaType, HttpStatus expectedStatus)
      throws IOException, InterruptedException {
    HttpResponse<String> response = deleteWithResponse(requestUri, expectedMediaType, expectedStatus);
    return response.toString();
  }

  /**
   * Execute a web service delete.
   * @param requestUri The request uri
   * @param expectedMediaType The type of media being requested
   * @param expectSuccess Assert a success code was returned
   * @return The http response message
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  public HttpResponse<String> deleteWithResponse(String requestUri, MediaType expectedMediaType, boolean expectSuccess)
      throws IOException, InterruptedException {
    return this.deleteContent(requestUri, expectedMediaType, expectSuccess);
  }

  /**
   * Execute a web service delete.
   * @param requestUri The request uri
   * @param expectedMediaType The type of media being requested
   * @param header custom header to be set
   * @param expectSuccess Assert a success code was returned
   * @return The http response message
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  public HttpResponse<String> deleteWithResponse(String requestUri, MediaType expectedMediaType, List<String> header, boolean expectSuccess)
      throws IOException, InterruptedException {
    return this.deleteContent(requestUri, expectedMediaType, header, expectSuccess);
  }

  /**
   * Execute a web service delete.
   * @param requestUri The request uri
   * @param expectedMediaType The type of media being requested
   * @param expectedStatus Assert a specific status code was returned
   * @return The http response message
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  public HttpResponse<String> deleteWithResponse(String requestUri, MediaType expectedMediaType, HttpStatus expectedStatus)
      throws IOException, InterruptedException {
    return this.deleteContent(requestUri, expectedMediaType, expectedStatus);
  }

  /**
   * Do a web service delete for the given uri.
   * @param requestUri The request uri
   * @param returnMediaType The expected response media type
   * @param header custom header to be set
   * @param expectSuccess Assert a success code was returned
   * @return A http response message
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  protected HttpResponse<String> deleteContent(String requestUri, MediaType returnMediaType, List<String> header
      , boolean expectSuccess)
      throws IOException, InterruptedException {
    setHttpRequest(HttpRequestFactory.getRequest(requestUri, returnMediaType, RequestMethod.DELETE, header));
    HttpResponse<String> response = baseHttpClient.send(getHttpRequest(), HttpResponse.BodyHandlers.ofString());

    // Should we check for success
    if (expectSuccess) {
      ensureSuccessStatusCode(response);
    }
    return response;
  }

  /**
   * Do a web service delete for the given uri.
   * @param requestUri The request uri
   * @param returnMediaType The expected response media type
   * @param expectSuccess Assert a success code was returned
   * @return A http response message
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  protected HttpResponse<String> deleteContent(String requestUri, MediaType returnMediaType, boolean expectSuccess)
      throws IOException, InterruptedException {
    setHttpRequest(HttpRequestFactory.getRequest(requestUri, returnMediaType, RequestMethod.DELETE));
    HttpResponse<String> response = baseHttpClient.send(getHttpRequest(), HttpResponse.BodyHandlers.ofString());

    // Should we check for success
    if (expectSuccess) {
      ensureSuccessStatusCode(response);
    }
    return response;
  }

  /**
   * Do a web service delete for the given uri.
   * @param requestUri The request uri
   * @param returnMediaType The expected response media type
   * @param expectedStatus Assert a specific status code was returned
   * @return A http response message
   * @throws IOException if an exception is thrown
   * @throws InterruptedException if an exception is thrown
   */
  protected HttpResponse<String> deleteContent(String requestUri, MediaType returnMediaType, HttpStatus expectedStatus)
      throws IOException, InterruptedException {
    setHttpRequest(HttpRequestFactory.getRequest(requestUri, returnMediaType, RequestMethod.DELETE));
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
