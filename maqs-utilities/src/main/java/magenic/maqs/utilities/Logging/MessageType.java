// --------------------------------------------------
// <copyright file="MessageType.java" company="Magenic">
// Copyright 2016 Magenic, All rights Reserved
// </copyright>
// <summary>Message type enumeration</summary>
// --------------------------------------------------

package magenic.maqs.utilities.Logging;

/**
 * The type of message
 * 
 * @author Magenic Technologies, Inc.
 */
public enum MessageType
{
	/**
	 * Error message
	 */
	ERROR,

	/**
	 * Warning message
	 */
	WARNING,

	/**
	 * Success message
	 */
	SUCCESS,

	/**
	 * Informational mesage
	 */
	INFORMATION,

	/**
	 * Generic message - Our default message type
	 */
	GENERIC;
}
