// --------------------------------------------------
// <copyright file="FileLogger.java" company="Magenic">
// Copyright 2016 Magenic, All rights Reserved
// </copyright>
// <summary>Writes event logs to plain text file</summary>
// --------------------------------------------------

package com.magenic.jmaqs.utilities.Logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.magenic.jmaqs.utilities.Helper.Config;
import com.magenic.jmaqs.utilities.Helper.StringProcessor;

/**
 * Helper class for adding logs to a plain text file. Allows configurable file
 * path.
 *
 */

public class FileLogger extends Logger
{
	/**
	 * The default log file save location
	 */
	protected final String DEFAULTLOGFOLDER = System.getProperty("java.io.tmpdir");

	/**
	 * Initializes a new instance of the FileLogger class
	 */
	private static final String DEFAULTLOGNAME = "FileLog.txt";

	/**
	 * Creates a private boolean of append
	 */
	private boolean append;

	/**
	 * Create a private string for the path of the file
	 */
	private String filePath;

	/**
	 * Creates a private string for the directory of the folder
	 */
	private String directory;

	/**
	 * Initializes a new instance of the FileLogger class
	 */
	public FileLogger()
	{
		this(false, "", DEFAULTLOGNAME);
	}

	/**
	 * Initializes a new instance of the FileLogger class
	 * 
	 * @param append
	 *            Append document if true
	 */
	public FileLogger(boolean append)
	{
		this(append, "", DEFAULTLOGNAME);
	}

	/**
	 * Initializes a new instance of the FileLogger class
	 * 
	 * @param logFolder
	 *            Where log files should be saved
	 */
	public FileLogger(String logFolder)
	{
		this(false, logFolder, DEFAULTLOGNAME);
	}

	/**
	 * Initializes a new instance of the FileLogger class
	 * 
	 * @param append
	 *            Append document if true
	 * @param logFolder
	 *            Where log files should be saved
	 */
	public FileLogger(boolean append, String logFolder)
	{
		this(append, logFolder, DEFAULTLOGNAME);
	}

	/**
	 * Initializes a new instance of the FileLogger class
	 * 
	 * @param logFolder
	 *            Where log files should be saved
	 * @param name
	 *            File Name
	 */
	public FileLogger(String logFolder, String name)
	{
		this(false, logFolder, name);
	}

	/**
	 * Initializes a new instance of the FileLogger class
	 * 
	 * @param append
	 *            Append document if true
	 * @param logFolder
	 *            Where log files should be saved
	 * @param name
	 *            File Name
	 */
	public FileLogger(boolean append, String logFolder, String name)
	{
		if (logFolder == null || logFolder.isEmpty())
		{
			this.directory = this.DEFAULTLOGFOLDER;
		}
		else
		{
			this.directory = logFolder;
		}

		if (!Files.exists(Paths.get(this.directory)))
		{
			File dir = new File(this.directory);
			dir.mkdir();
		}

		this.append = append;

		if (!name.toLowerCase().endsWith(".txt"))
		{
			name += ".txt";
		}

		this.filePath = Paths.get(this.directory, makeValidFileName(name)).toString();
	}

	/**
	 * Gets a value indicating whether to append the value
	 * 
	 * @return a boolean
	 */
	public boolean getAppend()
	{
		return this.append;
	}

	/**
	 * sets a value indicating whether to append the value
	 * 
	 * @param bol
	 *            sets this.append to true or false
	 */
	public void setAppend(boolean bol)
	{
		this.append = bol;
	}

	/**
	 * Gets the FilePath value
	 * 
	 * @return returns the file path
	 */
	public String getFilePath()
	{
		return this.filePath;
	}

	/**
	 * Sets the FilePath value
	 * 
	 * @param path
	 *            sets the file path
	 */
	public void setFilePath(String path)
	{
		this.filePath = path;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see magenic.maqs.utilities.Logging.Logger#logMessage(java.lang.String,
	 * java.lang.Object[])
	 */
	@Override
	public void logMessage(String message, Object... args)
	{
		this.logMessage(MessageType.GENERIC, message, args);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * magenic.maqs.utilities.Logging.Logger#logMessage(magenic.maqs.utilities.
	 * Logging.MessageType, java.lang.String, java.lang.Object[])
	 */
	@Override
	public void logMessage(MessageType messageType, String message, Object... args)
	{
		FileWriter fw;
		BufferedWriter bw;
		PrintWriter writer;

		try
		{
			fw = new FileWriter(this.filePath, this.append);
			bw = new BufferedWriter(fw);
			writer = new PrintWriter(bw);
			writer.println(StringProcessor.safeFormatter("%s%s", Config.NEW_LINE, System.currentTimeMillis()));
			writer.print(StringProcessor.safeFormatter("%s:\t", messageType.toString()));

			writer.println(StringProcessor.safeFormatter(message, args));

			writer.flush();
			writer.close();
		}

		catch (IOException e)
		{
			// Failed to write to the event log, write error to the console
			// instead
			ConsoleLogger console = new ConsoleLogger();
			console.logMessage(MessageType.ERROR,
					StringProcessor.safeFormatter("Failed to write to event log because: %s", e));
			console.logMessage(messageType, message, args);
		}
	}

	/**
	 * Take a name sting and make it a valid file name
	 * 
	 * @param name
	 *            The string to cleanup
	 * @return returns the string of a valid filename
	 */
	private static String makeValidFileName(String name)
	{
		try
		{
			if (name == null || name.isEmpty())
				throw new Exception();
		}
		catch (Exception e)
		{
			System.out.println("Blank file name was provide");
		}

		// Replace invalid characters
		return name.replaceAll("[^a-zA-Z0-9\\._\\- ]+", "~");
	}
}
