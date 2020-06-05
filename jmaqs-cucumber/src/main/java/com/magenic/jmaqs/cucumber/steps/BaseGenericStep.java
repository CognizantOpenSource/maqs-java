package com.magenic.jmaqs.cucumber.steps;

import com.magenic.jmaqs.base.BaseTest;
import com.magenic.jmaqs.cucumber.ScenarioContext;
import com.magenic.jmaqs.utilities.logging.Logger;

public class BaseGenericStep {

    public Logger getLogger()
    {
        return ScenarioContext.get("BASE_JMAQS_TEST", BaseTest.class).getLogger();
    }
}
