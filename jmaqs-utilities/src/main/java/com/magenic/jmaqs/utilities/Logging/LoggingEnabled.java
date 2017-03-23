//--------------------------------------------------
// <copyright file="LoggingEnabled.java" company="Magenic">
//  Copyright 2016 Magenic, All rights Reserved
// </copyright>
// <summary>When to enable logging enumeration</summary>
//--------------------------------------------------

package com.magenic.jmaqs.utilities.Logging;

/**
 * The type of message
 */
public enum LoggingEnabled
{
	/**
	 * Yes log
	 */
    YES,

    /**
     * Only save a log when there is a failure
     */
    ONFAIL,

    /**
     * No, don't log
     */
    NO,
}