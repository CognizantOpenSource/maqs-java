/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.webservices;

import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.time.Duration;

/**
 * The Http Client Factory class.
 */
public class HttpClientFactory {

  /**
   * private constructor.
   */
  private HttpClientFactory() {

  }

  /**
   * Gets the HTTP client based on configuration values.
   * @return A HTTP client
   */
  public static HttpClient getDefaultClient() {
    return getClient(WebServiceConfig.getWebServiceTimeOut());
  }

  /**
   * Gets the HTTP client based on configuration values.
   * @param timeout Web service timeout
   * @return A HTTP client
   */
  public static HttpClient getClient(int timeout) {
    return getClient(Duration.ofSeconds(timeout), WebServiceConfig.getUseProxy(),
        WebServiceConfig.getProxyAddress());
  }

  /**
   * Gets the HTTP client based on configuration values.
   * @param timeout Web service timeout
   * @param useProxy Use a proxy
   * @param proxyAddress The proxy address to use
   * @return A HTTP client
   */
  public static HttpClient getClient(Duration timeout, boolean useProxy, String proxyAddress) {
    HttpClient.Builder builder = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .followRedirects(HttpClient.Redirect.NORMAL)
        .connectTimeout(timeout);

    // sets up proxy settings
    if (useProxy) {
      builder.proxy(ProxySelector.of(new InetSocketAddress(proxyAddress, WebServiceConfig.getProxyPort())));
    }
    return builder.build();
  }
}
