// --------------------------------------------------
// <copyright file="ConfigUnitTests.java" company="Magenic">
// Copyright 2016 Magenic, All rights Reserved
// </copyright>
// <summary>Unit test configuration tests</summary>
// --------------------------------------------------

package magenic.maqs.utilities.unitTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.magenic.jmaqs.utilities.Helper.Config;

/**
 * Configuration unit test class
 *
 */
public class ConfigUnitTest
{
	/**
	* Gets a value from a string
	*/
	@Test
	public void getValueWithString()
	{
		String value = Config.getValue("WaitTime");
		Assert.assertEquals(value, "100");
	}

	/**
	 * Gets a value with a string or default 
	 */
	@Test
	public void getValueWithStringAndDefault()
	{

		String value = Config.getValue("DoesNotExist", "Default");

		Assert.assertEquals(value, "Default");
	}
}
