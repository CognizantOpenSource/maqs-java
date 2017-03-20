// --------------------------------------------------
// <copyright file="StringProcessor.java" company="Magenic">
// Copyright 2016 Magenic, All rights Reserved
// </copyright>
// <summary>Help utilities for string processing</summary>
// --------------------------------------------------

package magenic.maqs.utilities.Helper;

/**
 * Initializes a new instance of the StringProcessor class
 * 
 * @author Magenic Technologies, Inc.
 */
public final class StringProcessor
{
	/**
	 * Creates a string based on the arguments. If no args are applied, then we
	 * want to just return the message
	 * 
	 * @param message
	 *            The message being used
	 * @param args
	 *            The arguments being used
	 * @return A final string
	 */
	public static String safeFormatter(String message, Object... args)
	{
		try
		{
			return String.format(message, args);
		}
		catch (Exception e)
		{
			StringBuilder builder = new StringBuilder();
			builder.append(message);

			for (Object arg : args)
			{
				builder.append(arg.toString());
			}

			return builder.toString();
		}
	}
}
