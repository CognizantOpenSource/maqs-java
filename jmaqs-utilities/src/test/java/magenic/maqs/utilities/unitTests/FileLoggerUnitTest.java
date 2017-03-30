// --------------------------------------------------
// <copyright file="FileLoggerUnitTest.java" company="Magenic">
// Copyright 2016 Magenic, All rights Reserved
// </copyright>
// <summary>Unit test for FileLogger.java</summary>
// --------------------------------------------------
package com.magenic.jmaqs.utilities.unitTests;

import java.io.File;

import org.testng.annotations.Test;

import com.magenic.jmaqs.utilities.Logging.FileLogger;
import com.magenic.jmaqs.utilities.Logging.MessageType;

/**
 * Unit test class for FileLogger.java
 * 
 * @author BrentS
 *
 */
public class FileLoggerUnitTest
{
	/**
	 * Test logging to a new file
	 */
	@Test
	public void fileLoggerNoAppendTest()
	{
		FileLogger logger = new FileLogger(false, "", "WriteToFileLogger");
		logger.logMessage(MessageType.WARNING, "Hello, this is a test.");
		File file1 = new File(logger.getFilePath());
		file1.delete();
	}

	/**
	 * Test logging to an existing file
	 */
	@Test
	public void fileLoggerAppendFileTest()
	{
		FileLogger logger = new FileLogger(true, "", "WriteToExistingFileLogger");
		logger.logMessage(MessageType.WARNING, "This is a test to write to an existing file.");
		File file1 = new File(logger.getFilePath());
		file1.delete();
	}
}
