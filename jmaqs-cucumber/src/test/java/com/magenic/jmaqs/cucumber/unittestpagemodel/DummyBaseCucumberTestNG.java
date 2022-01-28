/*
 * Copyright 2021 (C) Magenic, All rights Reserved
 */

package com.magenic.jmaqs.cucumber.unittestpagemodel;

import com.cognizantsoftvision.maqs.base.BaseGenericTest;
import com.cognizantsoftvision.maqs.base.BaseTest;
import com.magenic.jmaqs.cucumber.BaseCucumberTestNG;

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
