// --------------------------------------------------
// <copyright file="Config.java" company="Magenic">
// Copyright 2016 Magenic, All rights Reserved
// </copyright>
// <summary>Helper class for getting application configuration values</summary>
// --------------------------------------------------

package com.magenic.jmaqs.utilities.Helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * Config class
 * 
 * @author Magenic Technologies, Inc.
 */
public final class Config
{
	/**
	 * Constant platform independent new line
	 */
	public static final String NEW_LINE = System.getProperty("line.separator");

	/**
	 * properties variable
	 */
	private static Properties configReader;

	/**
	 * Get the configuration value for a specific key
	 * 
	 * @param key
	 *            Config file key
	 * @return The configuration value - Returns the empty string if the key is
	 *         not found
	 */
	public static String getValue(String key)
	{
		return getValue(key, "");
	}

	/**
	 * Get the configuration value for a specific key
	 * 
	 * @param key
	 *            Config file key
	 * @param defaultValue
	 *            The default value - Returned the key cannot be found
	 * @return The configuration value
	 */
	public static String getValue(String key, String defaultValue)
	{

		try
		{
			getPropertiesFile();

		}
		catch (IOException e)
		{
			throw new RuntimeException(
					StringProcessor.safeFormatter("Exception loading the properties file : %s", configReader));
		}

		return configReader.getProperty(key, defaultValue);
	}

	/**
	 * Get the default wait timeout
	 * 
	 * @return The default wait timeout
	 */
	public static int getTimeout()
	{

		try
		{
			getPropertiesFile();
		}
		catch (IOException e)
		{
			throw new RuntimeException(
					StringProcessor.safeFormatter("Exception loading the properties file : %s", configReader));

		}

		return Integer.parseInt(configReader.getProperty("Timeout"));
	}

	/**
	 * Get the default wait time
	 * 
	 * @return The default wait time
	 */
	public static int getWaitTime()
	{

		try
		{
			getPropertiesFile();
		}
		catch (IOException e)
		{
			throw new RuntimeException(
					StringProcessor.safeFormatter("Exception loading the properties file : %s", configReader));
		}

		return Integer.parseInt(configReader.getProperty("WaitTime"));
	}
	
	public static String getLogPath(){
		try
		{
			getPropertiesFile();
		}
		catch (IOException e)
		{
			throw new RuntimeException(
					StringProcessor.safeFormatter("Exception loading the properties file : %s", configReader));
		}

		return configReader.getProperty("FileLoggerPath");
		
	}

	/**
	 * Get the properties file
	 * 
	 * @return The properties file object
	 * @throws IOException
	 */
	private static void getPropertiesFile() throws IOException
	{
		String configName = "config.properties";

		Properties config = new Properties();

		// Check if there is an external config (running from source or binary)
		// file included
		if ((new File(configName).exists()))
		{
			config.load(new FileInputStream(configName));
			configReader = config;
			return;
		}
		else
		{
			String path = Config.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String configPath = URLDecoder.decode(
					new File(new File(path).getParentFile().getPath() + File.separator + configName).getPath(),
					"utf-8");

			if (!(new File(configPath).exists()))
			{
				configPath = URLDecoder.decode(
						new File(new File(path).getParentFile().getParentFile().getPath() + File.separator + configName)
								.getPath(),
						"utf-8");
			}

			if ((new File(configPath).exists()))
			{
				config.load(new FileInputStream(configPath));
				configReader = config;
				return;

			}
		}

		// Load embedded config file
		InputStream in = Config.class.getResourceAsStream("/" + configName);
		config.load(in);

		configReader = config;
	}

}
