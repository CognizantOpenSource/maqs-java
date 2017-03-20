// --------------------------------------------------
// <copyright file="ListProcessorUnitTests.java" company="Magenic">
// Copyright 2016 Magenic, All rights Reserved
// </copyright>
// <summary>unit tests for list processing </summary>
// --------------------------------------------------
package magenic.maqs.utilities.unitTests;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import magenic.maqs.utilities.Helper.Config;
import magenic.maqs.utilities.Helper.ListProcessor;
import magenic.maqs.utilities.Helper.StringProcessor;

/**
 * Unit tests for the ListProcessor class
 */
public class ListProcessorUnitTest
{
	/**
	* Unit Test for creating a comma delimited string
	*/

	@Test
	public void createCommaDelimitedStringTest()
	{
		ArrayList<String> stringList = new ArrayList<String>();
		
		stringList.add("Maine");
		stringList.add("Massachusetts");
		stringList.add("New Hampshire");
		stringList.add("Connecticut");
		stringList.add("Rhode Island");
		stringList.add("Vermont");
		String expectedText = "Maine, Massachusetts, New Hampshire, Connecticut, Rhode Island, Vermont";

		String actualText = ListProcessor.createCommaDelimitedString(stringList,false);

		if (!expectedText.equals(actualText))
		{
			Assert.fail(StringProcessor.safeFormatter("Expected string %s does not match Actual string %s",
					expectedText, actualText));
		}
	}

	/**
	* Unit Test for creating a sorted comma delimited string
	*/
	
	@Test
	public void createSortedCommaDelimitedStringTest()
	{
		ArrayList<String> stringList = new ArrayList<String>();
		stringList.add("Maine");
		stringList.add("Massachusetts");
		stringList.add("New Hampshire");
		stringList.add("Connecticut");
		stringList.add("Rhode Island");
		stringList.add("Vermont");
		String expectedText = "Connecticut, Maine, Massachusetts, New Hampshire, Rhode Island, Vermont";

		String actualText = ListProcessor.createCommaDelimitedString(stringList, true);

		if (!expectedText.equals(actualText))
		{
			Assert.fail(StringProcessor.safeFormatter("Expected string %s does not match Actual string %s",
					expectedText, actualText));
		}
	}

	/**
	* Unit Test for comparing two lists of strings
	*/
	
	@Test
	public void listOfStringsComparerTest()
	{
		StringBuilder results = new StringBuilder();
		ArrayList<String> expectedList = new ArrayList<String>();
		ArrayList<String> actualList = new ArrayList<String>();
		expectedList.add("Maine");
		expectedList.add("Massachusetts");
		expectedList.add("New Hampshire");
		expectedList.add("Connecticut");
		expectedList.add("Rhode Island");
		expectedList.add("Vermont");
		

		actualList.add("Massachusetts");
		actualList.add("Connecticut");
		actualList.add("Rhode Island");
		actualList.add("Vermont");
		actualList.add("Maine");
		actualList.add("New Hampshire");

		ListProcessor.listOfStringsComparer(expectedList, actualList, results, false);

		if (results.length() > 0)
		{
			Assert.fail(StringProcessor.safeFormatter("%s%s", results.toString(), Config.NEW_LINE));
		}
	}
	  
	/**
	 * Unit Test for comparing two lists of strings by order
	 */
	@Test
	public void listOfStringsComparerByOrderTest()
	{
		StringBuilder results = new StringBuilder();
		ArrayList<String> expectedList = new ArrayList<String>();
		ArrayList<String> actualList = new ArrayList<String>();
		expectedList.add("Maine");
		expectedList.add("Massachusetts");
		expectedList.add("New Hampshire");
		expectedList.add("Connecticut");
		expectedList.add("Rhode Island");
		expectedList.add("Vermont");

		actualList.add("Maine");
		actualList.add("Massachusetts");
		actualList.add("New Hampshire");
		actualList.add("Connecticut");
		actualList.add("Rhode Island");
		actualList.add("Vermont");

		ListProcessor.listOfStringsComparer(expectedList, actualList, results, true);

		if (results.length() > 0)
		{
			Assert.fail(StringProcessor.safeFormatter("%s%s", results.toString(), Config.NEW_LINE));
		}
	}

}
