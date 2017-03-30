// --------------------------------------------------
// <copyright file="BaseGenericTest.java" company="Magenic">
// Copyright 2017 Magenic, All rights Reserved
// </copyright>
// <summary>Message type enumeration</summary>
// --------------------------------------------------

package com.magenic.jmaqs.utilities.unitTests;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.magenic.jmaqs.utilities.BaseTest.BaseGenericTest;
import com.magenic.jmaqs.utilities.Logging.FileLogger;

/**
 * Unit test class for BaseGenericTest class
 * 
 * @author ColeBo
 */
@Test
public class BaseGenericTestUnitTest extends BaseGenericTest
{
	/**
	 * Verify fully qualified test name
	 */
	@Test
	public void fullyQualifiedTestNameTest()
	{
		String testName = this.getFullyQualifiedTestClassName();
		
		Assert.assertEquals(testName, "com.magenic.jmaqs.utilities.unitTests.BaseGenericTestUnitTest.fullyQualifiedTestNameTest");
	}
	
	/**
	 * Validate setting a new logger
	 */
	@Test
	public void FileLoggerTest()
	{
		this.setLogger(new FileLogger());
		
		if (!(this.getLogger() instanceof FileLogger))
		{
			Assert.fail("FileLogger was not set.");
		}
	}
	
	/* (non-Javadoc)
	 * @see com.magenic.jmaqs.utilities.BaseTest.BaseGenericTest#postSetupLogging()
	 */
	@Override
	protected void postSetupLogging()
	{
	
	}

	/* (non-Javadoc)
	 * @see com.magenic.jmaqs.utilities.BaseTest.BaseGenericTest#beforeLoggingTeardown(org.testng.ITestResult)
	 */
	@Override
	protected void beforeLoggingTeardown(ITestResult resultType)
	{
		
	}

}
