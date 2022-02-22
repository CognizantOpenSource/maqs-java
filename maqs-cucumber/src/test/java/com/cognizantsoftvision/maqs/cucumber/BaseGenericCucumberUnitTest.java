/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.cucumber;

import com.cognizantsoftvision.maqs.base.BaseGenericTest;
import com.cognizantsoftvision.maqs.base.BaseTest;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test class for BaseGenericCucumber class.
 */
public class BaseGenericCucumberUnitTest {

    /**
     * Verifies the specific base test object is a BaseGenericTest Object
     */
    @Test(groups = TestCategories.CUCUMBER)
    public void testCreateSpecificBaseTest() {
        BaseGenericCucumber genericCucumber = new BaseGenericCucumber();
        BaseTest testObject = genericCucumber.createSpecificBaseTest();
        Assert.assertTrue(testObject instanceof BaseGenericTest);
    }
}