/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.cucumber.steps;

import com.cognizantsoftvision.maqs.base.BaseGenericTest;
import com.cognizantsoftvision.maqs.cucumber.ScenarioContext;
import com.cognizantsoftvision.maqs.cucumber.unittestpagemodel.DummyBaseGenericStep;
import com.cognizantsoftvision.maqs.utilities.helper.TestCategories;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test class for BaseGenericStep class.
 */
public class BaseGenericStepUnitTest extends BaseGenericTest {

    /**
     * Verifies the Logger is not null
     */
    @Test(groups = TestCategories.CUCUMBER)
    public void testGetLogger() {
        DummyBaseGenericStep stepClass = new DummyBaseGenericStep();
        ScenarioContext.put(ScenarioContext.MAQS_HOLDER, this);
        Assert.assertNotNull(stepClass.getLogger(), "Checking that Logger is not null through BaseGenericStep");
    }

    /**
     * Verifies the Test Object is not null
     */
    @Test(groups = TestCategories.CUCUMBER)
    public void testGetTestObject() {
        DummyBaseGenericStep stepClass = new DummyBaseGenericStep();
        ScenarioContext.put(ScenarioContext.MAQS_HOLDER, this);
        Assert.assertNotNull(stepClass.getTestObject(), "Checking that Test Object is not null through BaseGenericStep");
    }
}