/*
 * Copyright 2022 (C) Cognizant SoftVision, All rights Reserved
 */

package com.cognizantsoftvision.maqs.cucumber.unittestpagemodel;

import com.cognizantsoftvision.maqs.base.BaseGenericTest;
import com.cognizantsoftvision.maqs.base.BaseTest;
import com.cognizantsoftvision.maqs.cucumber.BaseCucumberTestNG;

/**
 * A Dummy BaseCucumberTestNG class for testing
 */
public class DummyBaseCucumberTestNG extends BaseCucumberTestNG {
    /**
     * Create a test object.
     *
     * @return A generic base test object
     */
    @Override
    public BaseTest createSpecificBaseTest() {
        return new BaseGenericTest();
    }
}
