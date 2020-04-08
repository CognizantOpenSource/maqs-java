/*
 * Copyright 2020 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices.jdk11;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.magenic.jmaqs.webservices.jdk8.MediaType;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.magenic.jmaqs.webservices.jdk8.WebServiceUtilities;
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
   * @param baseAddress The base URI address to use
   */
  public WebServiceDriver(URI baseAddress) {
    setHttpClient(HttpClientFactory.getDefaultClient());
  }

  /**
   * Class Constructor that sets the base address as a string.
   * @param baseAddress The base URI address to use
   * @throws URISyntaxException URI syntax is invalid
   */
  public WebServiceDriver(String baseAddress) throws URISyntaxException {
    this(new URI(baseAddress));
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
   * Execute a web service post.
   * @param requestUri The request uri
   * @param expectedMediaType The type of media being requested
   * @param content The post content
   * @param type the object to deserialize into
   * @param expectSuccess Assert a success code was returned
   * @param <T> The expected response type
   * @return The response to deserialize as - T
   * @throws IOException if exception is thrown
   * @throws InterruptedException if exception is thrown
   */
  public <T> T  post(String requestUri, MediaType expectedMediaType, String content,
      Type type, boolean expectSuccess) throws IOException, InterruptedException {
    HttpResponse<String> response = this.postWithResponse(requestUri, expectedMediaType, content, expectSuccess);
    return WebServiceUtilities.deserializeResponse(response, expectedMediaType, type);
  }

  /**
   * Execute a web service post.
   * @param requestUri The request uri
   * @param expectedMediaType The type of media being requested
   * @param content The post content
   * @param type the object to deserialize into
   * @param expectedStatus Assert a specific status code was returned
   * @param <T> The expected response type
   * @return The response will deserialize as - T
   * @throws IOException if exception is thrown
   * @throws InterruptedException if exception is thrown
   */
  public <T> T post(String requestUri, MediaType expectedMediaType, String content, Type type, HttpStatus expectedStatus)
      throws IOException, InterruptedException {
    HttpResponse<String> response = this.postWithResponse(requestUri, expectedMediaType, content, expectedStatus);
    return WebServiceUtilities.deserializeResponse(response, expectedMediaType, type);
  }

  /**
   * Execute a web service post.
   * @param requestUri The request uri
   * @param expectedMediaType The type of media being requested
   * @param content The post content
   * @param expectSuccess Assert a success code was returned
   * @return The response body as a string
   * @throws IOException if exception is thrown
   * @throws InterruptedException if exception is thrown
   */
  public String post(String requestUri, MediaType expectedMediaType, String content, boolean expectSuccess)
      throws IOException, InterruptedException {
    HttpResponse<String> response = this.postWithResponse(requestUri, expectedMediaType, content, expectSuccess);
    return response.body();
  }

  /**
   * Execute a web service post.
   * @param requestUri The request uri
   * @param expectedMediaType The type of media being requested
   * @param content The post content
   * @param expectedStatus Assert a specific status was returned
   * @return The response body as a string
   * @throws IOException if exception is thrown
   * @throws InterruptedException if exception is thrown
   */
  public String post(String requestUri, MediaType expectedMediaType, String content,
      HttpStatus expectedStatus) throws IOException, InterruptedException {
    HttpResponse<String> response = this.postWithResponse(requestUri, expectedMediaType, content, expectedStatus);
    return response.body();
  }

  /**
   * Execute a web service post.
   * @param requestUri The request uri
   * @param expectedMediaType The type of media being requested
   * @param content How to encode the post content
   * @param postMediaType The type of the media being posted
   * @param contentAsString If true pass content as String
   * @param expectSuccess Assert a success code was returned
   * @return The response body as a string
   * @throws IOException if exception is thrown
   * @throws InterruptedException if exception is thrown
   */
  public String post(String requestUri, MediaType expectedMediaType, Object content,
      MediaType postMediaType, boolean contentAsString, boolean expectSuccess)
      throws IOException, InterruptedException {
    HttpResponse<String> response = this.postWithResponse(requestUri, expectedMediaType, content,
        postMediaType, contentAsString, expectSuccess);
    return response.body();
  }

  /**
   * Execute a web service post.
   * @param requestUri The request uri
   * @param expectedMediaType The type of media being requested
   * @param content The post content
   * @param postMediaType The type of the media being posted
   * @param expectedStatus Assert a specific status code was returned
   * @param contentAsString If true pass content as StringContent, else pass as StreamContent
   * @return The response body as a string
   * @throws IOException if exception is thrown
   * @throws InterruptedException if exception is thrown
   */
  public String post(String requestUri, MediaType expectedMediaType, String content,
      MediaType postMediaType, HttpStatus expectedStatus, boolean contentAsString)
      throws IOException, InterruptedException {
    HttpResponse<String> response = this.postWithResponse(requestUri, expectedMediaType, content,
        postMediaType, expectedStatus, contentAsString);
    return response.body();
  }

  /**
   * Execute a web service post.
   * @param requestUri The request uri
   * @param expectedMediaType The type of media being requested
   * @param content How to encode the post content
   * @param postMediaType The type of the media being posted
   * @param contentAsString If true pass content as String
   * @param expectSuccess Assert a success code was returned
   * @return The response body as a string
   * @throws IOException if exception is thrown
   * @throws InterruptedException if exception is thrown
   */
  public HttpResponse<String> postWithResponse(String requestUri, MediaType expectedMediaType, Object content,
      MediaType postMediaType, boolean contentAsString, boolean expectSuccess)
      throws IOException, InterruptedException {
    String httpContent = createContent(content, postMediaType, contentAsString);
    return this.postWithResponse(requestUri, expectedMediaType, httpContent, expectSuccess);
  }

  /**
   * Execute a web service post.
   * @param requestUri The request uri
   * @param expectedMediaType The type of media being requested
   * @param content The post content
   * @param postMediaType The type of the media being posted
   * @param expectedStatus Assert a specific status code was returned
   * @param contentAsString If true pass content as String
   * @return The response body as a string
   * @throws IOException if exception is thrown
   * @throws InterruptedException if exception is thrown
   */
  public HttpResponse<String> postWithResponse(String requestUri, MediaType expectedMediaType,
      Object content, MediaType postMediaType,
      HttpStatus expectedStatus, boolean contentAsString) throws IOException, InterruptedException {
    String httpContent = createContent(content, postMediaType, contentAsString);
    return this.postWithResponse(requestUri, expectedMediaType, httpContent, expectedStatus);
  }

  /**
   * Execute a web service post.
   * @param requestUri The request uri
   * @param expectedMediaType The type of media being requested
   * @param content The post content
   * @param expectSuccess Assert a success code was returned
   * @return The http response message
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  public HttpResponse<String> postWithResponse(String requestUri, MediaType expectedMediaType,
      String content, boolean expectSuccess) throws IOException, InterruptedException {
    return this.postContent(requestUri, expectedMediaType, content, expectSuccess);
  }

  /**
   * Execute a web service post.
   * @param requestUri The request uri
   * @param expectedMediaType The type of media being requested
   * @param content The post content
   * @param expectedStatus Assert a specific status code was returned
   * @return The http response message
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  public HttpResponse<String> postWithResponse(String requestUri, MediaType expectedMediaType, String content, HttpStatus expectedStatus)
      throws IOException, InterruptedException {
    return this.postContent(requestUri, expectedMediaType, content, expectedStatus);
  }

  /**
   * Do a web service post for the given uri, content and media type.
   * @param requestUri The request uri
   * @param responseMediaType The response media type
   * @param content The post body
   * @param expectSuccess Assert a success code was returned
   * @return A http response message
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  protected HttpResponse<String> postContent(String requestUri, MediaType responseMediaType,
      String content, boolean expectSuccess) throws IOException, InterruptedException {
    this.checkIfMediaTypeNotPresent(responseMediaType.toString());
    setHttpRequest(HttpRequestFactory.getRequest(requestUri, responseMediaType, content, RequestMethod.POST));
    HttpResponse<String> response = baseHttpClient.send(getHttpRequest(), HttpResponse.BodyHandlers.ofString());

    // Should we check for success
    if (expectSuccess) {
      ensureSuccessStatusCode(response);
    }

    return response;
  }

  /**
   * Do a web service post for the given uri, content and media type.
   * @param requestUri The request uri
   * @param responseMediaType The response media type
   * @param content The post body
   * @param expectedStatus Assert a specific status code was returned
   * @return A http response message
   * @throws IOException if the exception is thrown
   * @throws InterruptedException if the exception is thrown
   */
  protected HttpResponse<String> postContent(String requestUri, MediaType responseMediaType, String content, HttpStatus expectedStatus)
      throws IOException, InterruptedException {
    this.checkIfMediaTypeNotPresent(responseMediaType.toString());
    setHttpRequest(HttpRequestFactory.getRequest(requestUri, responseMediaType, content, RequestMethod.POST));
    HttpResponse<String> response = baseHttpClient.send(getHttpRequest(), HttpResponse.BodyHandlers.ofString());

    // We check for specific status
    ensureStatusCodesMatch(response, expectedStatus);

    return response;
  }

  /**
   * Create http content.
   * @param content The content as a string
   * @param postMediaType The type of the media being posted
   * @param contentAsString If true pass content as String
   * @return The content as String
   * @throws JsonProcessingException if the exception is thrown
   */
  private static String createContent(Object content, MediaType postMediaType,
      boolean contentAsString) throws JsonProcessingException {
    if (contentAsString) {
      return WebServiceUtilities.createStringEntity(content, postMediaType);
    }
    return content.toString();
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