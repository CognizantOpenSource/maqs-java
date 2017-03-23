//--------------------------------------------------
// <copyright file="StringProcessorUnitTests.java" company="Magenic">
//  Copyright 2016 Magenic, All rights Reserved
// </copyright>
// <summary>Unit tests for the string processor</summary>
//--------------------------------------------------
package magenic.maqs.utilities.unitTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.magenic.jmaqs.utilities.Helper.StringProcessor;

/**
 * Unit tests for the StringProcessor class
 */
public class StringProcessorUnitTest
{
    /**
     * Test method for checking string format
     */
    @Test
    public void StringFormatterCheckForStringFormat()
    {
        String message = StringProcessor.safeFormatter("This %s should return %s", "Test", "Test");
        Assert.assertEquals("This Test should return Test", message);
    }
}
