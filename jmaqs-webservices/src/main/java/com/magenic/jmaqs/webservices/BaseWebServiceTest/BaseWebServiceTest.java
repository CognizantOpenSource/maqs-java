// --------------------------------------------------
// <copyright file="BaseWebServiceTest.java" company="Magenic">
// Copyright 2017 Magenic, All rights Reserved
// </copyright>
// --------------------------------------------------

package com.magenic.jmaqs.webservices.BaseWebServiceTest;

import org.testng.ITestResult;

import com.magenic.jmaqs.utilities.BaseTest.BaseGenericTest;

import sun.net.www.http.HttpClient;

public class BaseWebServiceTest extends BaseGenericTest {
	// The HttpClient
	private HttpClientWrapper webServiceWrapper;

	/**
	 * Constructor
	 */
	// TODO Update constructor to take in the WebServiceTestObject
	public BaseWebServiceTest(HttpClientWrapper webServiceWrapper) {
		super();
		this.webServiceWrapper = webServiceWrapper;
	}

	/**
	 * @return A HttpClient object to interact with
	 */
	public HttpClient getHttpClient() {
		return (HttpClient) webServiceWrapper.getHttpClient();
	}

	/**
	 * @return A String containing the default Web Service URI
	 */
	protected String GetBaseWebServiceUrl() {
		return WebServiceConfig.GetWebServiceUri();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see magenic.maqs.utilities.BaseTest.BaseGenericTest#postSetupLogging()
	 */
	@Override
	protected void postSetupLogging() {
		// TODO Fill in once more info on how this is designed works.

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * magenic.maqs.utilities.BaseTest.BaseGenericTest#beforeLoggingTeardown
	 * (org.testng.ITestResult)
	 */
	@Override
	protected void beforeLoggingTeardown(ITestResult resultType) {
		// TODO Fill in once more info on how this is designed works.

	}

}
