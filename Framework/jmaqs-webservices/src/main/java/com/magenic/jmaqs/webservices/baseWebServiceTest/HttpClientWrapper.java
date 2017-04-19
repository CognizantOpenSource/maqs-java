/* 
 * Copyright 2017 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.webservices.baseWebServiceTest;

import com.beust.jcommander.internal.Lists;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

/**
 * The HTTP client wrapper.
 */
public class HttpClientWrapper {
  
  /**
   * The base HTTP client control.
   */
  private CloseableHttpClient baseHttpClient;

  /**
   * The URI to be stored for the Web Service.
   */
  private URI baseAddress;

  /**
   * Constructor.
   * 
   * @param baseAddress
   *          The base URI address to use
   * @throws URISyntaxException
   *           URI syntax is invalid
   */
  public HttpClientWrapper(String baseAddress, ContentType mediaType) throws URISyntaxException {
    this(new URI(baseAddress), mediaType);
  }

  /**
   * Constructor.
   * 
   * @param baseAddress
   *          The base URI address to use
   */
  public HttpClientWrapper(URI baseAddress, ContentType mediaType) {
    Header header = new BasicHeader(HttpHeaders.CONTENT_TYPE, mediaType.toString());
    this.baseHttpClient = HttpClientBuilder.create().setDefaultHeaders(Lists.newArrayList(header))
        .setDefaultRequestConfig(getRequestConfig()).build();

    this.baseAddress = baseAddress;
  }

  /**
   * Get the Http client.
   * 
   * @return a HttpClient to interact with
   */
  public HttpClient getHttpClient() {
    return this.baseHttpClient;
  }

  /**
   * Get the base URI.
   * 
   * @return A URI containing the Web Service Address
   */
  public URI getBaseWebServiceAddress() {
    return this.baseAddress;
  }

  /**
   * Sets the base Web Service address for the HttpClientWrapper.
   * 
   * @param address
   *          The string to set the URI to
   * @throws URISyntaxException
   *           thrown when the string can't be parsed into a URI object
   */
  public void setBaseWebServiceAddress(String address) throws URISyntaxException {
    this.baseAddress = new URI(address);
  }

  /**
   * Execute an HTTP get.
   * 
   * @param requestUri
   *          The request uri
   * @param expectSuccess
   *          Do we expect the request to succeed
   * @return The HTTP response
   * @throws Exception
   *           An error occurred getting the response
   */
  public CloseableHttpResponse getWithResponse(String requestUri, boolean expectSuccess)
      throws Exception {

    return getContent(requestUri, expectSuccess);
  }

  protected CloseableHttpResponse getContent(String requestUri, boolean expectSuccess)
      throws Exception {
    HttpGet newGet = new HttpGet(new URIBuilder(this.baseAddress).setPath(requestUri).build());

    CloseableHttpResponse response = this.baseHttpClient.execute(newGet);

    // Should we check for success
    if (expectSuccess) {
      ensureSuccessStatusCode(response);
    }

    return response;
  }

  protected HttpEntity createEntity(String contentMessage, ContentType contentType) {
    HttpEntity newEntity = new StringEntity(contentMessage, contentType);

    return newEntity;
  }

  protected CloseableHttpResponse putContent(String requestUri, HttpEntity content,
      boolean expectSuccess) throws Exception {
    HttpPut newPut = new HttpPut(new URIBuilder(this.baseAddress).setPath(requestUri).build());
    newPut.setEntity(content);
    CloseableHttpResponse response = this.baseHttpClient.execute(newPut);

    // Should we check for success
    if (expectSuccess) {
      ensureSuccessStatusCode(response);
    }

    return response;
  }

  protected CloseableHttpResponse postContent(String requestUri, HttpEntity content,
      boolean expectSuccess) throws Exception {
    HttpPost newPost = new HttpPost(new URIBuilder(this.baseAddress).setPath(requestUri).build());
    newPost.setEntity(content);
    CloseableHttpResponse response = this.baseHttpClient.execute(newPost);

    // Should we check for success
    if (expectSuccess) {
      ensureSuccessStatusCode(response);
    }

    return response;
  }

  protected CloseableHttpResponse deleteContent(String requestUri, String returnMediaType,
      boolean expectSuccess) throws Exception {
    HttpDelete newDelete = new HttpDelete(
        new URIBuilder(this.baseAddress).setPath(requestUri).build());
    CloseableHttpResponse response = this.baseHttpClient.execute(newDelete);

    // Should we check for success
    if (expectSuccess) {
      ensureSuccessStatusCode(response);
    }

    return response;
  }

  private static void ensureSuccessStatusCode(HttpResponse response) throws Exception {
    // Make sure a response was returned
    if (response == null) {
      throw new NullPointerException("Response was null");
    }

    // Check if it was a success and if not create a user friendly error
    // message
    if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
      String body = response.getStatusLine().toString();

      throw new Exception(
          String.format("Response did not indicate a success. %s Response code was: %s ",
              System.lineSeparator(), body));
    }

  }

  /**
   * Get the request configuration.
   * 
   * @return The request configuration
   */
  private RequestConfig getRequestConfig() {

    return RequestConfig.copy(RequestConfig.DEFAULT)
        .setConnectionRequestTimeout(WebServiceConfig.getWebServiceTimeOut() * 1000)
        .setConnectTimeout(WebServiceConfig.getWebServiceTimeOut() * 1000).build();
  }
}
