// --------------------------------------------------
// <copyright file="ConsoleLogger.java" company="Magenic
// Copyright 2016 Magenic, All rights Reserved
// </copyright>
// <summary>Helper class for console logging</summary>
// --------------------------------------------------

package com.magenic.jmaqs.utilities.Logging;

import com.magenic.jmaqs.utilities.Helper.StringProcessor;

/**
 * Helper class for logging to the console
 * 
 */
public class ConsoleLogger extends Logger
{
	/**
	 * Write the formatted message (one line) to the console as a generic
	 * message
	 * 
	 * @param message
	 *            The message text
	 * @param args
	 *            String format arguments
	 */
	@Override
	public void logMessage(String message, Object... args)
	{
		this.writeLine(message, args);
	}

	/**
	 * Write the formatted message (one line) to the console as the specified
	 * type
	 * 
	 * @param messageType
	 *            The type of message
	 * @param message
	 *            The message text
	 * @param args
	 *            String format arguments
	 * 
	 */
	@Override
	public void logMessage(MessageType messageType, String message, Object... args)
	{
		this.writeLine(messageType, message, args);
	}

	/**
	 * Write the formatted message to the console as a generic message
	 * 
	 * @param message
	 *            The message text
	 * @param args
	 *            String format arguments
	 */
	public void write(String message, Object... args)
	{
		this.writeToConsole(MessageType.GENERIC, false, message, args);
	}

	/**
	 * Write the formatted message followed by a line break to the console as a
	 * generic message
	 * 
	 * @param message
	 *            The message text
	 * @param args
	 *            String format arguments
	 */
	public void writeLine(String message, Object... args)
	{
		this.writeToConsole(MessageType.GENERIC, true, message, args);
	}

	/**
	 * Write the formatted message to the console as the given message type
	 * 
	 * @param type
	 *            The type of message
	 * @param message
	 *            The message text
	 * @param args
	 *            Message string format arguments
	 */
	public void write(MessageType type, String message, Object... args)
	{
		this.writeToConsole(type, false, message, args);
	}

	/**
	 * Write the formatted message followed by a line break to the console as
	 * the given message type
	 * 
	 * @param type
	 *            The type of message
	 * @param message
	 *            The message text
	 * @param args
	 *            Message string format arguments
	 */
	public void writeLine(MessageType type, String message, Object... args)
	{
		this.writeToConsole(type, true, message, args);
	}

	/**
	 * write the message to the console
	 * 
	 * 
	 * @param type
	 *            The type of message
	 * @param line
	 *            Is this a write-line command, else it is just a write
	 * @param message
	 *            The log message
	 * @param args
	 *            Message string format arguments
	 */
	private void writeToConsole(MessageType type, boolean line, String message, Object... args)
	{
		// Just return if there is no message
		if (message == null || message.isEmpty())
		{
			return;
		}

		String result = StringProcessor.safeFormatter(message, args);
		try
		{
			// If this a write-line command
			if (line)
			{
				System.out.println(result);
			}
			else
			{
				System.out.print(result);
			}
		}
		catch (Exception e)
		{
			System.out.println(
					StringProcessor.safeFormatter("Failed to write to the console because: %s", e.getMessage()));
		}

	}

}
