// --------------------------------------------------
// <copyright file="WebServiceConfig.java" company="Magenic">
// Copyright 2017 Magenic, All rights Reserved
// </copyright>
// --------------------------------------------------

package com.magenic.jmaqs.webservices.BaseWebServiceTest;

import com.magenic.jmaqs.utilities.Helper.Config;

public final class WebServiceConfig {
	/**
	 * Grabs the URI for the Web Service.
	 * 
	 * @return A String containing the URI for the WebService to test
	 */
	public static String GetWebServiceUri() {
		return Config.getValue("WebServiceURI");
	}

	/**
	 * Gets the expected time out in seconds.
	 * 
	 * @return an Integer containing the Time Out value for the web service
	 *         test, or -1 if none are configured
	 */
	public static int GetWebServiceTimeOut() {
		return Integer.parseInt(Config.getValue("WebServiceTimeOut", "-1"));
	}
}
