//--------------------------------------------------
// <copyright file="TestResultType.java" company="Magenic">
//  Copyright 2016 Magenic, All rights Reserved
// </copyright>
// <summary>Test result type enumeration</summary>
//--------------------------------------------------

package com.magenic.jmaqs.utilities.Logging;

/**
 * The type of result
 * 
 *@author Magenic Technologies, Inc.
 */
public enum TestResultType
{
	/**
	 * The test passed
	 */
    PASS,

    /**
     * The test failed
     */
    FAIL,

    /**
     * The test was inconclusive
     */
    INCONCLUSIVE,

    /**
     * The test was skipped
     */
    SKIP,

    /**
     * The test had an unexpected result
     */
    OTHER,
}
