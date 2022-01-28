/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.cognizantsoftvision.maqs.cucumber;

import com.cognizantsoftvision.maqs.base.BaseTest;
import com.cognizantsoftvision.maqs.selenium.BaseSeleniumTest;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test class for BaseSeleniumCucumber class.
 */
public class BaseSeleniumCucumberUnitTest {

    /**
     * Verifies the specific base test object is a BaseSeleniumTest Object
     */
    @Test(groups = TestCategories.CUCUMBER)
    public void testCreateSpecificBaseTest() {
        BaseSeleniumCucumber seleniumCucumber = new BaseSeleniumCucumber();
        BaseTest testObject = seleniumCucumber.createSpecificBaseTest();
        Assert.assertTrue(testObject instanceof BaseSeleniumTest);
    }
}